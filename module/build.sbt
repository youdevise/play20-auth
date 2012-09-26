name := "play20.auth"

organization := "jp.t2v"

version := "0.4.2-YD1"

resolvers ++= Seq(
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies ++= Seq(
  "play" %% "play" % "2.0.1"
)

credentials += Credentials(new File("/etc/sbt/credentials"))

publishTo := sys.env.get("LOCAL_MAVEN_REPO").map { dir =>
  Resolver.file("maven-repo", file(dir))(Patterns(true, Resolver.mavenStyleBasePattern))
}

publishTo <<= version { (v: String) =>
      val nexus = "http://repo.youdevise.com:8081/nexus/content/repositories/"
      if (v.trim.endsWith("SNAPSHOT"))
        Some("snapshots" at nexus + "snapshots")
      else
        Some("releases" at nexus + "yd-release-candidates")
    }

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { x => false }
