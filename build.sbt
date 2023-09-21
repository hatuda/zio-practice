ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.0"

lazy val root = (project in file("."))
  .settings(
    name                             := "zio-hands-on",
    idePackagePrefix                 := Some("jp.webcrew.hands.on.zio"),
    libraryDependencies += "dev.zio" %% "zio-http" % "3.0.0-RC2"
  )
