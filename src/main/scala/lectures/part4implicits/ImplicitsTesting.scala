package lectures.part4implicits

import scala.util.Random

object ImplicitsTesting extends App {

  val stringTuple = "a" -> "ADAS"

  case class Person(name: String) {
    val greet = s"Hey my name is $name"
  }

  implicit def stringToPerson (str: String) : Person = {
    Person(str)
  }
//Notice that if a second implicit also matches, the compiler wont be able to determine which to use
  /*
  implicit def stringToPerson2(str: String): Person = {
    Person(str)
  }*/
  /* Magically Scala looks in all implicits methods, classes value etc
  that may help compile this thing
   */
  println("Lasldasda".greet)

  //implicit parameters
  implicit val someRandomInteger4000123120102 : Int = 12912312

  //Implicit value has to go at least into the second position
  def random2( val2: Int)(implicit val1: Int) = {
    println(s"Using val1 $val1")
    println(s"Using val1 $val2")
    Random.nextInt(val1) + Random.nextInt(val2)
  }

  println(random2(4))

}
