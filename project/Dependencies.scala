import sbt._

object Version {
  val akka                 = "2.3.3"
  val akkaPersistenceMongo = "0.7.2-SNAPSHOT"
  val logback              = "1.1.2"
  val scala                = "2.11.1"
  val scalaTest            = "2.1.7"
  val spray                = "1.3.1-20140423"
  val sprayJson            = "1.2.6"
}

object Library {
  val akkaActor            = "com.typesafe.akka"  %% "akka-actor"                    % Version.akka
  val akkaCluster          = "com.typesafe.akka"  %% "akka-cluster"                  % Version.akka
  val akkaContrib          = "com.typesafe.akka"  %% "akka-contrib"                  % Version.akka
  val akkaPersistenceMongo = "com.github.ddevore" %% "akka-persistence-mongo-casbah" % Version.akkaPersistenceMongo
  val akkaPersistence      = "com.typesafe.akka"  %% "akka-persistence-experimental" % Version.akka
  val akkaSlf4j            = "com.typesafe.akka"  %% "akka-slf4j"                    % Version.akka
  val akkaTestkit          = "com.typesafe.akka"  %% "akka-testkit"                  % Version.akka
  val logbackClassic       = "ch.qos.logback"     %  "logback-classic"               % Version.logback
  val scalaTest            = "org.scalatest"      %% "scalatest"                     % Version.scalaTest
  val sprayCan             = "io.spray"           %% "spray-can"                     % Version.spray
  val sprayJson            = "io.spray"           %% "spray-json"                    % Version.sprayJson
  val sprayRouting         = "io.spray"           %% "spray-routing"                 % Version.spray
}

object Dependencies {

  import Library._

  val akkamazing = List(
    akkaActor,
    akkaCluster,
    akkaContrib,
    akkaPersistence,
    akkaPersistenceMongo,
    akkaSlf4j,
    logbackClassic,
    sprayCan,
    sprayJson,
    sprayRouting,
    akkaTestkit % "test",
    scalaTest % "test"
  )
}
