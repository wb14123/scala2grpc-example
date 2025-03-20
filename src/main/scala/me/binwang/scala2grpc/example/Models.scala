package me.binwang.scala2grpc.example


/**
 * A simple structure. All fields are simple types.
 * @param boolean A boolean
 * @param str A string
 * @param numberInt A int number
 * @param numberDouble A double number
 * @param numberFloat A float number
 */
case class BaseTypeExample(
  boolean: Boolean,
  str: String,
  numberInt : Int,
  numberDouble: Double,
  numberFloat: Float,
)


/**
 * An example of using enum
 */
object EnumExample extends Enumeration {
  type EnumExample = Value
  val
  RED,
  BLUE,
  GREEN
  = Value
}


/**
 * A structure that has different collections as its fields
 * @param intList A list of int
 * @param nestedList A list of lists of int
 * @param opt An option type
 * @param nestedOpt An option of option
 * @param mixed A list of option
 */
case class CollectionExample(
  intList: Seq[Int],
  nestedList: Seq[Seq[Int]],
  opt: Option[Int],
  nestedOpt: Option[Option[Int]],
  mixed: Seq[Option[Int]],
  enum: EnumExample.EnumExample,
)

/**
 * A type that has both base structure and collection structure as fields
 * @param base Simple structure
 * @param collection Collection structure
 */
case class NestExample(
  base: BaseTypeExample,
  collection: CollectionExample,
)
