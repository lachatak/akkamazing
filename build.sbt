lazy val akkamazing = project in file(".")

name := "akkamazing"

Common.settings

libraryDependencies ++= Dependencies.akkamazing

initialCommands := """|import de.heikoseeberger.akkamazing._""".stripMargin

addCommandAlias("hs", "runMain de.heikoseeberger.akkamazing.HttpServiceApp -Dakka.remote.netty.tcp.port=2551 -Dakka.cluster.roles.0=http-service")

addCommandAlias("us1", "runMain de.heikoseeberger.akkamazing.UserServiceApp -Dakka.remote.netty.tcp.port=2552 -Dakka.cluster.roles.0=user-service")

addCommandAlias("us2", "runMain de.heikoseeberger.akkamazing.UserServiceApp -Dakka.remote.netty.tcp.port=2553 -Dakka.cluster.roles.0=user-service")
