enablePlugins(ScalaJSPlugin)

name := "The Timer"

version := "0.1"

scalaVersion := "2.12.8"

// This is an application with a main method
scalaJSUseMainModuleInitializer := true

// To access DOM
libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.6"

// To use jQuery API
libraryDependencies += "org.querki" %%% "jquery-facade" % "1.2"

// To bundle up the external libraries
skip in packageJSDependencies := false
jsDependencies +=
  "org.webjars" % "jquery" % "2.2.1" / "jquery.js" minified "jquery.min.js"
