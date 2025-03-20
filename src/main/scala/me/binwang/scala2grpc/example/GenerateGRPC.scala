package me.binwang.scala2grpc.example

import cats.effect.IO
import io.grpc.{Metadata, Status, StatusRuntimeException}
import me.binwang.scala2grpc.example.EnumExample.EnumExample
import me.binwang.scala2grpc.{ChainedGrpcHook, ErrorWrapperHook, GRPCGenerator, GrpcHook, RequestLoggerHook}
import org.typelevel.log4cats.LoggerFactory
import org.typelevel.log4cats.slf4j.Slf4jFactory

import scala.reflect.runtime.universe.{Type, typeOf}

object GenerateGRPC extends GRPCGenerator {
  override val protoJavaPackage: String = "me.binwang.scala2grpc.example.grpc"
  override val protoPackage: String = "example"

  override val modelClasses: Seq[Type] = Seq(
    typeOf[BaseTypeExample],
    typeOf[EnumExample],
    typeOf[CollectionExample],
    typeOf[NestExample],
  )

  override val serviceClasses: Seq[Type] = Seq(
    typeOf[ExampleService],
  )

  override implicit def loggerFactory: LoggerFactory[IO] = Slf4jFactory.create[IO]

  private val customErrorMapper = new ErrorWrapperHook() {
    override def mapError(err: Throwable, metadata: Metadata): StatusRuntimeException = {
      err match {
        case _: AuthError => new StatusRuntimeException(Status.UNAUTHENTICATED.withCause(err), metadata)
        case _ => super.mapError(err, metadata)
      }
    }
  }
  override implicit def grpcHook: GrpcHook = new ChainedGrpcHook(Seq(
    new RequestLoggerHook(),
    customErrorMapper,
  ))

}
