package me.binwang.scala2grpc.example

import cats.effect.{ExitCode, IO, IOApp}
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder
import fs2.grpc.syntax.all.fs2GrpcSyntaxServerBuilder

object ExampleGrpcServer extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {
    val services = Seq(
      new ExampleService(),
    )

    val serverBuilder = NettyServerBuilder.forPort(9999)

    GenerateGRPC.addServicesToServerBuilder(serverBuilder, services).flatMap { sb =>
      fs2GrpcSyntaxServerBuilder(sb)
        .resource[IO]
        .evalMap(server => IO(server.start()))
    }.useForever

  }
}
