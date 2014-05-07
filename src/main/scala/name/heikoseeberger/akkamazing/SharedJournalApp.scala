/*
 * Copyright 2014 Heiko Seeberger
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package name.heikoseeberger.akkamazing

import akka.actor.{ Actor, ActorLogging, ActorIdentity, ActorSystem, Identify, Props, ReceiveTimeout, RootActorPath }
import akka.cluster.{ Cluster, Member }
import akka.cluster.ClusterEvent.{ InitialStateAsEvents, MemberUp }
import akka.persistence.journal.leveldb.{ SharedLeveldbJournal, SharedLeveldbStore }
import scala.concurrent.duration.DurationInt

/**
 * The shared journal is a single point of failure and must not be used in production.
 * This app must be running in order for persistence and cluster sharding to work.
 */
object SharedJournalApp extends BaseApp {

  override def run(system: ActorSystem, opts: Map[String, String]): Unit = {
    val sharedJournal = system.actorOf(Props(new SharedLeveldbStore), "shared-journal")
    SharedLeveldbJournal.setStore(sharedJournal, system)
  }
}

object SharedJournalSetter {

  def props: Props =
    Props(new SharedJournalSetter)
}

/**
 * This actor must be started and registered as a cluster event listener by all actor systems
 * that need to use the shared journal, e.g. in order to use persistence or cluster sharding.
 */
class SharedJournalSetter extends Actor with ActorLogging {

  override def preStart(): Unit =
    Cluster(context.system).subscribe(self, InitialStateAsEvents, classOf[MemberUp])

  override def receive: Receive =
    waiting

  def waiting: Receive = {
    case MemberUp(member) if member hasRole "shared-journal" => onSharedJournalMemberUp(member)
  }

  def becomeIdentifying(): Unit = {
    context.setReceiveTimeout(10 seconds)
    context become identifying
  }

  def identifying: Receive = {
    case ActorIdentity(_, Some(sharedJournal)) =>
      SharedLeveldbJournal.setStore(sharedJournal, context.system)
      log.info("Succssfully set shared journal {}", sharedJournal)
      context.stop(self)
    case ActorIdentity(_, None) =>
      log.error("Can't identify shared journal!")
      context.stop(self)
    case ReceiveTimeout =>
      log.error("Timeout identifying shared journal!")
      context.stop(self)
  }

  def onSharedJournalMemberUp(member: Member): Unit = {
    val sharedJournal = context actorSelection (RootActorPath(member.address) / "user" / "shared-journal")
    sharedJournal ! Identify(None)
    becomeIdentifying()
  }
}
