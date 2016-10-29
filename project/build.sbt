resolvers ++= Seq(
  "socrata releases" at "http://repo.socrata.com/artifactory/libs-release"
)

addSbtPlugin("com.socrata" % "socrata-cloudbees-sbt" % "1.4.1")
