import eu.diversit.sbt.plugin.WebDavPlugin._
import com.typesafe.tools.mima.plugin.MimaPlugin.mimaDefaultSettings
import com.typesafe.tools.mima.plugin.MimaKeys.previousArtifact

mimaDefaultSettings

seq(WebDav.scopedSettings : _*)

name := "soql-brita"

organization := "com.socrata"

version := "1.2.2-SNAPSHOT"

previousArtifact <<= scalaBinaryVersion { sv => Some("com.socrata" % ("soql-brita_" + sv) % "1.2.1") }

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

credentials ++= List(new File("/private/socrata-oss/maven-credentials")).flatMap { f =>
  if(f.exists) Some(Credentials(f)) else None
}

publishTo <<= version { v =>
  val cloudbees = "https://repository-socrata-oss.forge.cloudbees.com/"
  if(v.endsWith("SNAPSHOT")) {
    Some("snapshots" at cloudbees + "snapshot")
  } else {
    Some("releases" at cloudbees + "release")
  }
}
