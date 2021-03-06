import bintray.Plugin._
import sbt._
import sbt.Keys._
import sbtrelease.ReleasePlugin.ReleaseKeys._
import sbtrelease.ReleasePlugin._

object ApplicationBuild extends Build {
  val scala11Version = "2.11.7"

  val main = Project("total-map", file(".")).settings(
    useGlobalVersion := false,
    organization := "com.boldradius",
    scalaVersion := scala11Version,
    libraryDependencies += "com.storm-enroute" %% "scalameter" % "0.6",
    licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html")),
    testFrameworks += new TestFramework("org.scalameter.ScalaMeterFramework"),
    parallelExecution in Test := false,
    crossScalaVersions := Seq("2.10.4", scala11Version),
    publishMavenStyle := true,
    bintray.Keys.bintrayOrganization in bintray.Keys.bintray := Some("boldradiussolutions")
  ).settings(bintraySettings ++ releaseSettings: _*)

  val basic = Project("basic", file("examples/basic")).settings(
    scalaVersion := scala11Version,
    //libraryDependencies += "com.boldradius" %% "total-map" % "0.1.10",
    resolvers += Resolver.bintrayRepo("boldradiussolutions", "maven"),
    bintray.Keys.bintrayOrganization in bintray.Keys.bintray := Some("boldradiussolutions")
  ).dependsOn(main)
}
