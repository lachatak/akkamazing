lazy val akkamazing = project in file(".")

name := "akkamazing"

Common.settings

libraryDependencies ++= Dependencies.akkamazing

initialCommands := """|import de.heikoseeberger.akkamazing._""".stripMargin

addCommandAlias("hs", "runMain de.heikoseeberger.akkamazing.HttpServiceApp -Dakka.remote.netty.tcp.port=2551 -Dakka.cluster.roles.0=http-service")
