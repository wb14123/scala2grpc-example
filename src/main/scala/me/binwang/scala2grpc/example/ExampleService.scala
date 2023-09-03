package me.binwang.scala2grpc.example

import cats.effect.IO

class ExampleService {

  def echo(req: BaseTypeExample): IO[BaseTypeExample] = IO.pure(req)

  def combineExamples(base: BaseTypeExample, collection: CollectionExample): IO[NestExample] = {
    IO.pure(NestExample(base, collection))
  }

}
