lazy val akkamazing = project in file(".")

name := "akkamazing"

Common.settings

libraryDependencies ++= Dependencies.akkamazing

initialCommands := """|import de.heikoseeberger.akkamazing._""".stripMargin
