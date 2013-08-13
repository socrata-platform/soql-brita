import com.typesafe.tools.mima.plugin.MimaPlugin.mimaDefaultSettings
import com.typesafe.tools.mima.plugin.MimaKeys.previousArtifact

com.socrata.cloudbeessbt.SocrataCloudbeesSbt.socrataSettings()

name := "soql-brita"

version := "1.2.2-SNAPSHOT"

previousArtifact <<= scalaBinaryVersion { sv => None /* Some("com.socrata" % ("soql-brita_" + sv) % "1.2.1") */ }

scalaVersion := "2.10.2"

crossScalaVersions := Seq("2.8.1", "2.8.2", "2.9.2", "2.10.2")

libraryDependencies <++= (scalaVersion) {
  case "2.8.1" | "2.8.2" => Seq(
    "org.scalatest" %% "scalatest" % "1.8" % "test",
    "org.scalacheck" % "scalacheck_2.8.1" % "1.8" % "test"
  )
  case "2.9.2" | "2.10.2" => Seq(
    "org.scalatest" %% "scalatest" % "1.9.1" % "test",
    "org.scalacheck" %% "scalacheck" % "1.10.0" % "test"
  )
}
