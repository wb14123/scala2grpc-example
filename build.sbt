ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.16"

lazy val root = (project in file("."))
  .settings(
    name := "scala2grpc-example"
  )



enablePlugins(Fs2Grpc)
enablePlugins(Scala2GrpcPlugin)

grpcGeneratorMainClass := "me.binwang.scala2grpc.example.GenerateGRPC"

libraryDependencies ++= Seq(
  "me.binwang.scala2grpc" %% "generator" % "1.2.0-SNAPSHOT",

  // add logging backend
  "ch.qos.logback" % "logback-core" % "1.5.17",
  "ch.qos.logback" % "logback-classic" % "1.5.17",
)


