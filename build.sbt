ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.0"

lazy val root = (project in file("."))
  .settings(
    name                             := "zio-hands-on",
    idePackagePrefix                 := Some("jp.webcrew.hands.on.zio"),
    libraryDependencies += "dev.zio" %% "zio-http" % "3.0.0-RC2",
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio-test"          % "2.0.17" % Test,
      "dev.zio" %% "zio-test-sbt"      % "2.0.17" % Test,
      "dev.zio" %% "zio-test-magnolia" % "2.0.17" % Test
    ),
    testFrameworks +=
      new TestFramework("zio.test.sbt.ZTestFramework")
  )
