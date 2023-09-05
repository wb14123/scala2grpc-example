package me.binwang.scala2grpc.example

import cats.effect.IO

class ExampleService {

  def echo(req: BaseTypeExample): IO[BaseTypeExample] = IO.pure(req)

  def echoStream(req: BaseTypeExample, repeat: Int): fs2.Stream[IO, BaseTypeExample] = {
    fs2.Stream.emit(req).repeatN(repeat)
  }

  def errorIO(req: BaseTypeExample): IO[BaseTypeExample] = IO.raiseError(new Exception("Test exception"))

  def errorStream(req: BaseTypeExample, repeat: Int): fs2.Stream[IO, BaseTypeExample] =
    fs2.Stream.emit(req).repeatN(repeat) ++ fs2.Stream.raiseError[IO](new Exception("Test exception"))

  def mappedError(req: BaseTypeExample): IO[BaseTypeExample] = IO.raiseError(new AuthError("Test auth error"))

  def combineExamples(base: BaseTypeExample, collection: CollectionExample): IO[NestExample] = {
    IO.pure(NestExample(base, collection))
  }

}
