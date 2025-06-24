ThisBuild / version := "0.1.0-SNAPSHOT"

enablePlugins(ScalafmtPlugin)
scalafmtOnCompile := true

ThisBuild / scalaVersion := "2.13.12"

lazy val root = (project in file("."))
  .settings(
    name := "trip-tracker"
  )

libraryDependencies += "org.apache.kafka" % "kafka-clients" % "3.4.1"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "3.5.1",
  "org.apache.spark" %% "spark-sql" % "3.5.1",
  "org.apache.spark" %% "spark-sql-kafka-0-10" % "3.5.1"
)

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.16"
