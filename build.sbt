import com.typesafe.tools.mima.plugin.MimaPlugin.mimaDefaultSettings
import com.typesafe.tools.mima.plugin.MimaKeys.previousArtifact

name := "soql-brita"

previousArtifact <<= scalaBinaryVersion { sv => None /* Some("com.socrata" % ("soql-brita_" + sv) % "1.2.1") */ }

scalaVersion := "2.10.2"

crossScalaVersions := Seq("2.10.4", scalaVersion.value)

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "org.scalacheck" %% "scalacheck" % "1.12.4" % "test"
)
