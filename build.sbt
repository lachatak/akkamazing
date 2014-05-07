Common.settings

resolvers += "spray repo" at "http://repo.spray.io"

libraryDependencies ++= Dependencies.akkamazing

initialCommands := """|import name.heikoseeberger.akkamazing._""".stripMargin

addCommandAlias("hs", "runMain name.heikoseeberger.akkamazing.HttpServiceApp -Dakka.remote.netty.tcp.port=2551 -Dakka.cluster.roles.0=http-service")
