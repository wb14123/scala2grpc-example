package me.binwang.scala2grpc.example

import cats.effect.IO

/**
 * An example service
 */
class ExampleService {

  /**
   * Echo the request.
   * Response the same thing as sent in request.
   * @param req The request
   * @return The same as `req`
   */
  def echo(req: BaseTypeExample): IO[BaseTypeExample] = IO.pure(req)

  /**
   * Echo the request, but repeat the response as a stream
   * @param req The request
   * @param repeat How many times to repeat the request
   * @return A stream with `req` repeated a few times
   */
  def echoStream(req: BaseTypeExample, repeat: Int): fs2.Stream[IO, BaseTypeExample] = {
    fs2.Stream.emit(req).repeatN(repeat)
  }

  /**
   * Raise an error
   * @param req Doesn't matter
   * @return Always raise an error
   */
  def errorIO(req: BaseTypeExample): IO[BaseTypeExample] = IO.raiseError(new Exception("Test exception"))

  /**
   * Raise an error for a stream API
   * @param req Doesn't matter
   * @param repeat Always raise an error.
   * @return
   */
  def errorStream(req: BaseTypeExample, repeat: Int): fs2.Stream[IO, BaseTypeExample] =
    fs2.Stream.emit(req).repeatN(repeat) ++ fs2.Stream.raiseError[IO](new Exception("Test exception"))

  /**
   * An API endpoint to test if the framework maps the internal error to specific GRPC error code and message
   * @param req Doesn't matter
   * @return Always raise an auth error
   */
  def mappedError(req: BaseTypeExample): IO[BaseTypeExample] = IO.raiseError(new AuthError("Test auth error"))

  /**
   * A complex API that has nested structure as request
   * @param base A simple structure
   * @param collection A more complex structure that has different collections as fields
   * @return Combine `base` and `collection`
   */
  def combineExamples(base: BaseTypeExample, collection: CollectionExample): IO[NestExample] = {
    IO.pure(NestExample(base, collection))
  }

}
