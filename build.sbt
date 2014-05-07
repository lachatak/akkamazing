Common.settings

resolvers += "spray repo" at "http://repo.spray.io"

libraryDependencies ++= Dependencies.akkamazing

initialCommands := """|import name.heikoseeberger.akkamazing._""".stripMargin
