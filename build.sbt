name := "soql-brita"

organization := "com.socrata"

version := "1.2.0"

scalaVersion := "2.10.0"

crossScalaVersions := Seq("2.8.1", "2.8.2", "2.9.2", "2.10.0")

libraryDependencies <+= (scalaVersion) {
  case "2.8.1" | "2.8.2" => "org.scalatest" %% "scalatest" % "1.8" % "test"
  case "2.9.2" | "2.10.0" => "org.scalatest" %% "scalatest" % "1.9.1" % "test"
}
