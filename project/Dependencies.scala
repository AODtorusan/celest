
import sbt._

object Dependencies {

  val commonsMath =     "org.apache.commons"          %  "commons-math3"            % "3.3"
  val orekit =          "org.orekit"                  %  "orekit"                   % "6.1"
  val sofa =            "be.angelcorp"                %  "sofa"                     % "1.1-SNAPSHOT"

  val breeze =          "org.scalanlp"                %% "breeze"                   % "0.11.2"
  val breezeNatives =   "org.scalanlp"                %% "breeze-natives"           % breeze.revision
  val breezeViz =       "org.scalanlp"                %% "breeze-viz"               % breeze.revision
  val config =          "com.typesafe"                %  "config"                   % "1.2.1"
  val guava =           "com.google.guava"            %  "guava"                    % "17.0"
  val jgrapht =         "net.sf.jgrapht"              %  "jgrapht"                  % "0.8.3"
  val jsr305 =          "com.google.code.findbugs"    %  "jsr305"                   % "2.0.3" // Required for guava
  val logbackClassic =  "ch.qos.logback"              %  "logback-classic"          % "1.0.13"
  val miglayout =       "com.miglayout"               %  "miglayout-swing"          % "5.0"
  val reflections =     "org.reflections"             %  "reflections"              % "0.9.9-RC1" exclude( "com.google.guava", "guava" )
  val scalaGlsl =       "be.angelcorp.scala-glsl"     %% "core"                     % "1.0.0-SNAPSHOT"
  val scalaGuice =      "net.codingwell"              %% "scala-guice"              % "4.0.0-beta4"
  val scalaLogging =    "com.typesafe.scala-logging"  %% "scala-logging-slf4j"      % "2.1.2"
  val scalaParser =     "org.scala-lang.modules"      %% "scala-parser-combinators" % "1.0.2"
  val scalaTest =       "org.scalatest"               %% "scalatest"                % "2.2.1"       % "test"
  val servletApi =      "javax.servlet"               %  "servlet-api"              % "2.5" // Required for reflections

  val indexer =         "org.apache.maven.indexer"    %  "indexer-core"             % "6.0-SNAPSHOT"
  val wagonHttp =       "org.apache.maven.wagon"      %  "wagon-http-lightweight"   % "2.3"
  val indexerAll = Seq( indexer, wagonHttp )

  val aetherApi =       "org.eclipse.aether"          %  "aether-api"               % "1.0.0.v20140518"
  val aetherImpl =      "org.eclipse.aether"          %  "aether-impl"              % aetherApi.revision
  val aetherBasic =     "org.eclipse.aether"          %  "aether-connector-basic"   % aetherApi.revision
  val aetherFile =      "org.eclipse.aether"          %  "aether-transport-file"    % aetherApi.revision
  val aetherHttp =      "org.eclipse.aether"          %  "aether-transport-http"    % aetherApi.revision
  val aetherWagon =     "org.eclipse.aether"          %  "aether-transport-wagon"   % aetherApi.revision
  val apacheAether =    "org.apache.maven"            %  "maven-aether-provider"    % "3.1.0"
  val apacheWagonSsh =  "org.apache.maven.wagon"      %  "wagon-ssh"                % "1.0"
  val aetherAll = Seq( aetherApi, aetherImpl, aetherBasic, aetherFile, aetherHttp, aetherWagon, apacheAether, apacheWagonSsh )

}
