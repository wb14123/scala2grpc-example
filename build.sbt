ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "scala2grpc-example"
  )



enablePlugins(Fs2Grpc)
enablePlugins(Scala2GrpcPlugin)

grpcGeneratorMainClass := "me.binwang.scala2grpc.example.GenerateGRPC"

libraryDependencies ++= Seq(
  "me.binwang.scala2grpc" %% "generator" % "1.0.0-SNAPSHOT",
)


