import sbt.Keys._
import sbt._
import sbtassembly.Plugin._

object Build extends sbt.Build {
  import BuildSettings._
  import Dependencies._

  lazy val root: Project = Project(
    "root",
    file("."),
    settings = buildSettings
  ) aggregate(celest_core, celest_orekit, celest_sofa, celest_console, celest_examples/*, celest_simulation*/)

  lazy val celest_core: Project = Project(
    "celest-core",
    file("core"),
    settings = buildSettings ++ forkedRun ++ Seq(
      unmanagedSourceDirectories in Compile += baseDirectory.value / "src/main/interfaces",
      // Add dependencies
      libraryDependencies ++= Seq( breeze, commonsMath, jgrapht, scalaGuice, scalaParser ),
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
    "celest-console",
    file("console"),
    settings = buildSettings ++ forkedRun ++ Seq(
      libraryDependencies += "org.scala-lang" % "scala-compiler" % scalaVersion.value
    ) ++ assemblySettings
  ) dependsOn celest_core

  lazy val celest_examples: Project = Project(
    "celest-examples",
    file("examples"),
    settings = buildSettings ++ forkedRun ++ Seq(
      libraryDependencies ++= Seq( breezeViz, miglayout, reflections, servletApi ),
      libraryDependencies += "org.scala-lang" % "scala-compiler" % scalaVersion.value
    )
  ) dependsOn(celest_core, celest_simulation)

  lazy val celest_simulation: Project = Project(
    "celest-simulation",
    file("simulation"),
    settings = buildSettings ++ forkedRun ++ Seq(
      libraryDependencies ++= Seq( )
    )
  ) dependsOn celest_core

}
