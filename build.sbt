lazy val root = (project in file(".")).
  settings(
    name := "asm2",
    version := "0.1",
    scalaVersion := "2.11.7"
  )

// libraryDependencies += "junit" % "junit" % "4.12" % Test
libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"

libraryDependencies += "org.antlr" % "antlr4" % "4.5.1"
libraryDependencies += "org.ow2.asm" % "asm" % "5.0.4"

