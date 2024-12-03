name := """telkomsigma-rest-api"""
organization := "com.scc.bumnsd"

version := "2.1-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.12"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.scc.pertamina.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.scc.pertamina.binders._"
libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.23"
// libraryDependencies += "mysql" % "mysql-connector-j" % "8.0.33"
libraryDependencies += "com.typesafe.play" %% "play-slick" % "5.1.0"
libraryDependencies += "com.typesafe.play" %% "play-slick-evolutions" % "5.1.0"
libraryDependencies ++= Seq("com.graphql-java" % "graphql-java" % "21.1")
libraryDependencySchemes += "org.scala-lang.modules" %% "scala-xml" % VersionScheme.Always
