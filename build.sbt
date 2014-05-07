Common.settings

resolvers += "spray repo" at "http://repo.spray.io"

libraryDependencies ++= Dependencies.akkamazing

initialCommands := """|import name.heikoseeberger.akkamazing._""".stripMargin

addCommandAlias("hs", "runMain name.heikoseeberger.akkamazing.HttpServiceApp -Dakka.remote.netty.tcp.port=2551 -Dakka.cluster.roles.0=http-service")

addCommandAlias("us", "runMain name.heikoseeberger.akkamazing.UserServiceApp -Dakka.remote.netty.tcp.port=2552 -Dakka.cluster.roles.0=user-service")

addCommandAlias("us2", "runMain name.heikoseeberger.akkamazing.UserServiceApp -Dakka.remote.netty.tcp.port=2553 -Dakka.cluster.roles.0=user-service")

addCommandAlias("sj", "runMain name.heikoseeberger.akkamazing.SharedJournalApp -Dakka.remote.netty.tcp.port=2554 -Dakka.cluster.roles.0=shared-journal")
