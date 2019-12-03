name := "soql-brita"
organization := "com.socrata"

mimaPreviousArtifacts := Set(/*"com.socrata" %% "soql-brita" % "1.4.1"*/)

scalaVersion := "2.13.1"

crossScalaVersions := Seq("2.10.4", "2.11.7", "2.12.10", scalaVersion.value)

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.8" % "test",
  "org.scalacheck" %% "scalacheck" % "1.14.0" % "test"
)

scalacOptions ++= Seq("-deprecation")
