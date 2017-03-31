name := "banking-service"

organization := "io.namelos"

version := "0.0.1"

scalaVersion := "2.11.5"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.1" % "test" withSources() withJavadoc(),
  "org.scalacheck" %% "scalacheck" % "1.12.1" % "test" withSources() withJavadoc(),
  "com.github.nscala-time" %% "nscala-time" % "2.16.0" withSources() withJavadoc()
)

initialCommands := "import io.namelos.bankingservice._"

