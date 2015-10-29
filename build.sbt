lazy val root = (project in file(".")).
  settings(
    name := "asm2",
    version := "0.1",
    scalaVersion := "2.11.7"
  )

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"

