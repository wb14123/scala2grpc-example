package me.binwang.scala2grpc.example


case class BaseTypeExample(
  boolean: Boolean,
  str: String,
  numberInt : Int,
  numberDouble: Double,
  numberFloat: Float,
)

case class CollectionExample(
  intList: Seq[Int],
  nestedList: Seq[Seq[Int]],
  opt: Option[Int],
  nestedOpt: Option[Option[Int]],
  mixed: Seq[Option[Int]],
)

case class NestExample(
  base: BaseTypeExample,
  collection: CollectionExample,
)
