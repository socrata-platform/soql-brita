import com.typesafe.tools.mima.plugin.MimaPlugin.mimaDefaultSettings
import com.typesafe.tools.mima.plugin.MimaKeys.previousArtifact

mimaDefaultSettings

name := "soql-brita"

organization := "com.socrata"

version := "1.2.1-SNAPSHOT"

previousArtifact <<= scalaBinaryVersion { sv => Some("com.socrata" % ("soql-brita_" + sv) % "1.2.0") }

scalaVersion := "2.10.0"

crossScalaVersions := Seq("2.8.1", "2.8.2", "2.9.2", "2.10.0")

libraryDependencies <++= (scalaVersion) {
  case "2.8.1" | "2.8.2" => Seq(
    "org.scalatest" %% "scalatest" % "1.8" % "test",
    "org.scalacheck" % "scalacheck_2.8.1" % "1.8" % "test"
  )
  case "2.9.2" | "2.10.0" => Seq(
    "org.scalatest" %% "scalatest" % "1.9.1" % "test",
    "org.scalacheck" %% "scalacheck" % "1.10.0" % "test"
  )
}
