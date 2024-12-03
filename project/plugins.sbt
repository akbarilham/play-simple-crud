// addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.8.20")
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.9.0-RC2")
addSbtPlugin("org.foundweekends.giter8" % "sbt-giter8-scaffold" % "0.16.2")
addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.6.3")
ThisBuild / libraryDependencySchemes += "org.scala-lang.modules" %% "scala-xml" % VersionScheme.Always
ThisBuild / evictionErrorLevel := Level.Info
