package me.binwang.scala2grpc.example

import cats.effect.IO
import me.binwang.scala2grpc.GRPCGenerator
import org.typelevel.log4cats.LoggerFactory
import org.typelevel.log4cats.slf4j.Slf4jFactory

import scala.reflect.runtime.universe.{Type, typeOf}

object GenerateGRPC extends GRPCGenerator {
  override val protoJavaPackage: String = "me.binwang.scala2grpc.example.grpc"
  override val protoPackage: String = "example"

  override val modelClasses: Seq[Type] = Seq(
    typeOf[BaseTypeExample],
    typeOf[CollectionExample],
    typeOf[NestExample],
  )

  override val serviceClasses: Seq[Type] = Seq(
    typeOf[ExampleService],
  )

  override implicit def loggerFactory: LoggerFactory[IO] = Slf4jFactory.create[IO]
}
