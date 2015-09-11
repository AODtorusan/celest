import sbt.Keys._
import sbt._

object BuildSettings {
  import Dependencies._

  val buildSettings = Defaults.defaultSettings ++ Seq(
    organization := "be.angelcorp.celest",
    version := "1.0.0-SNAPSHOT",
    scalacOptions ++= Seq(),
    scalaVersion := "2.11.7",

    resolvers += Resolver.sonatypeRepo("snapshots"),
    resolvers += Resolver.sonatypeRepo("releases"),
    resolvers += Resolver.mavenLocal,
    resolvers += "Angelcorp Repository" at "http://repository.angelcorp.be",

    libraryDependencies ++= List(scalaLogging, logbackClassic, scalaTest),
    testOptions in Test += Tests.Argument("-oF"), // show full stack traces for scalatest
    testOptions in Test <+= (target in Test) map {
      t => Tests.Argument(TestFrameworks.ScalaTest, "junitxml(directory=\"%s\")" format (t / "test-reports"))
    },

    publishMavenStyle := true,
    publishArtifact in Test := false,
    publishTo := {
      val nexus = "http://jetty.angelcorp.be/nexus/"
      if (isSnapshot.value)
        Some("snapshots" at nexus + "content/repositories/snapshots")
      else
        Some("releases"  at nexus + "content/repositories/releases")
    }
  )
  val forkedRun = Seq(
    // Do not run in the same JVM (native libraries can only be loaded once in sbt, running again requires a restart of sbt)
    //javaOptions ++= List("-Xdebug", "-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"),
    fork := true
  )

}
