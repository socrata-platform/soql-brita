import com.typesafe.tools.mima.plugin.MimaPlugin.mimaDefaultSettings
import com.typesafe.tools.mima.plugin.MimaKeys.previousArtifact
import com.socrata.sbtplugins.StylePlugin.StyleKeys

name := "soql-brita"
organization := "com.socrata"

previousArtifact <<= scalaBinaryVersion { sv => None /* Some("com.socrata" % ("soql-brita_" + sv) % "1.2.1") */ }

scalaVersion := "2.11.7"

crossScalaVersions := Seq("2.10.4", scalaVersion.value)

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "org.scalacheck" %% "scalacheck" % "1.12.4" % "test"
)

// I cannot silence the monster, but I can pull its fangs
StyleKeys.styleFailOnError in Compile := false
StyleKeys.styleFailOnError in Test := false
