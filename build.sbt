ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.6"

lazy val root = (project in file("."))
  .settings(
    name := "trip-tracker"
  )

libraryDependencies += "org.apache.kafka" % "kafka-clients" % "4.0.0"
