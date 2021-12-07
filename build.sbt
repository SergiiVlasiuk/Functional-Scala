name := "FunctionalProgrammingCoursera"

version := "1.0"

scalaVersion := "2.11.12"
//scalaVersion := "3.0.1"

scalacOptions ++= Seq("-deprecation", "-feature")

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
//  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "junit" % "junit" % "4.12" % "test",
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.2"
//  "org.scala-lang.modules" %% "scala-parser-combinators" % "2.1.0"
)