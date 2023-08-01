ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.0"

lazy val root = (project in file("."))
  .settings(
    name             := "zio-hands-on",
    idePackagePrefix := Some("jp.webcrew.hands.on.zio"),
    libraryDependencies ++= Seq("dev.zio" %% "zio" % "2.0.13"),
    // DB関係
    libraryDependencies ++= Seq(
      "mysql"        % "mysql-connector-java" % "8.0.17",
      "io.getquill" %% "quill-jdbc-zio"       % "4.6.0.1"
    )
  )

ThisBuild / assemblyMergeStrategy := {
  case PathList(ps @ _*) if ps.last == "module-info.class" =>
    MergeStrategy.discard
  case PathList("io", "getquill", xs @ _*) => MergeStrategy.first
  case x =>
    val oldStrategy = (ThisBuild / assemblyMergeStrategy).value
    oldStrategy(x)
}
