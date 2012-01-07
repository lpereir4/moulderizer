
resolvers += Classpaths.typesafeResolver

resolvers ++= Seq(
  ("Web plugin repo" at "http://siasia.github.com/maven2"),
  ("sonatype.repo" at "https://oss.sonatype.org/content/groups/public"),
  ("sbt-idea-repo" at "http://mpeltonen.github.com/maven/")
)

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "0.11.0")

addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse" % "1.5.0")

libraryDependencies <++= sbtVersion { sbtv => List(
  ("com.github.siasia" %% "xsbt-web-plugin" % (sbtv + "-0.2.10"))
)}

