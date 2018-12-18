resolvers ++= Seq(
  "socrata releases" at "https://repo.socrata.com/artifactory/ivy-libs-release-local/"
)

addSbtPlugin("com.socrata" % "socrata-sbt-plugins" % "1.6.6")
