organization := "mobi.pereira"

name := "moulderizer"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.9.1"

scalacOptions ++= "-unchecked"::"-deprecation"::Nil

libraryDependencies ++= Seq(
  "net.debasishg" %% "redisclient" % "2.4.0",
  "net.databinder" %% "unfiltered-filter" % "0.5.2",
  "net.databinder" %% "unfiltered-spec" % "0.5.2",
  "net.databinder" %% "dispatch-http" % "0.8.6",
  "javax.servlet" % "servlet-api" % "2.5" % "provided",
  "org.eclipse.jetty" % "jetty-webapp" % "8.0.4.v20111024" % "container",
  "org.clapper" %% "avsl" % "0.3.6",
  "org.scala-tools.testing" %% "specs" % "1.6.9",
  "moulder" %% "moulder-s" % "1.0"
)

seq(webSettings :_*)

