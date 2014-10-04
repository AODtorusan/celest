import sbt.Keys._
import sbt._
import sbtassembly.Plugin._

object BuildSettings {
  import Dependencies._

  val buildSettings = Defaults.defaultSettings ++ Seq(
    organization := "be.angelcorp.omicronai",
    version := "1.0.0-SNAPSHOT",
    scalacOptions ++= Seq(),
    scalaVersion := "2.11.2",
    crossScalaVersions := Seq("2.11.0", "2.11.1", "2.11.2"),

    resolvers += Resolver.sonatypeRepo("snapshots"),
    resolvers += Resolver.sonatypeRepo("releases"),
    resolvers += Resolver.mavenLocal,
    resolvers += "Angelcorp Repository" at "http://repository.angelcorp.be",

    libraryDependencies ++= List(scalaLogging, logbackClassic, scalaTest),

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
    fork in run := true,
    javaOptions in run ++= List("-Xdebug", "-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005")
  )

}

object MyBuild extends Build {
  import BuildSettings._
  import Dependencies._

  lazy val root: Project = Project(
    "root",
    file("."),
    settings = buildSettings
  ) aggregate(celest_core, celest_orekit, celest_sofa, celest_console, celest_examples/*, celest_simulation*/)

  lazy val celest_core: Project = Project(
    "core",
    file("celest"),
    settings = buildSettings ++ forkedRun ++ Seq(
      unmanagedSourceDirectories in Compile += baseDirectory.value / "src/main/interfaces",
      // Add dependencies
      libraryDependencies ++= Seq( angelcorpMath, jgrapht, scalaGuice, scalaParser ),
      libraryDependencies ++= indexerAll,
      libraryDependencies ++= aetherAll
    ) /* ++ assemblySettings */
  )

  lazy val celest_orekit: Project = Project(
    "celest-orekit",
    file("celest-orekit"),
    settings = buildSettings ++ forkedRun ++ Seq(
      // Add dependencies
      libraryDependencies ++= Seq(orekit)
    )
  ) dependsOn celest_core

  lazy val celest_sofa: Project = Project(
    "celest-sofa",
    file("celest-sofa"),
    settings = buildSettings ++ forkedRun ++ Seq(
      libraryDependencies ++= Seq( sofa )
    )
  ) dependsOn celest_core

  lazy val celest_console: Project = Project(
    "console",
    file("console"),
    settings = buildSettings ++ forkedRun ++ Seq(
      libraryDependencies ++= Seq( )
    ) ++ assemblySettings
  ) dependsOn celest_core

  lazy val celest_examples: Project = Project(
    "examples",
    file("examples"),
    settings = buildSettings ++ forkedRun ++ Seq(
      libraryDependencies ++= Seq( miglayout, reflections, servletApi )
    )
  ) dependsOn celest_core

//  lazy val celest_simulation: Project = Project(
//    "simulation",
//    file("simulation"),
//    settings = buildSettings ++ forkedRun ++ Seq(
//      libraryDependencies ++= Seq( )
//    )
//  ) dependsOn celest_core

}