package lectures.part4implicits

import scala.util.Random

object ImplicitsTesting extends App {

  val stringTuple = "a" -> "ADAS"

  case class Person(name: String, age: Int = 99) {
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
  println("Lasldasda".age)

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

object OrderingImplicits extends App {

  implicit val inverseOrderingNumbers: Ordering[Int] = Ordering.fromLessThan(_ > _)
  //implicit val normalOrderingNumbers: Ordering[Int] = Ordering.fromLessThan(_ < _) //Having two implicits for the same causes compiler issue
  val testList = List(12,23,3,6,7,5,1,3,46,9,100)
  println(testList.sorted)



  case class User(id: Int, name: String, email: String)
  implicit val userOdering : Ordering[User] = Ordering.fromLessThan((a,b) => {
    a.name < b.name
  })

  val userList = List(
    User(1,"Xavier","asdas"),
    User(2,"Logan","asdas"),
    User(3,"Esther","asdas"),
    User(4,"Peter","asdas"),
    User(5,"Abraham","asdas"),
  )
  println(userList.sorted)

}


object ImplicitOrderingExercise2 extends App {

  case class Tank(id: Int, name: String, cannonMM: Double, armorThickness: Double)
  case class AttackHelicopter(id: Int, name: String, sealing: Double, payload: Double)

  val genericTankList = (1 to 10).toList.map(x => {
    Tank(x,s"Tank-$x",(Random.nextDouble()*100)+30,(Random.nextDouble()*100)+10)
  })

  val genericHelicopterList = (1 to 10).toList.map(x => {
    Tank(x, s"Tank-$x", (Random.nextDouble() * 100) + 1000, (Random.nextDouble() * 100) + 500)
  })

  println(genericTankList)
  println(genericHelicopterList)

  //create a generic sorting that could work with either tank or helicopter
  implicit val tankOrdering : Ordering[Tank] = Ordering.fromLessThan((tankA, tankB) => {
    tankA.cannonMM > tankB.cannonMM
  })
  implicit val helicopterOrdering: Ordering[AttackHelicopter] = Ordering.fromLessThan((heliA, heliB) => {
    heliA.payload > heliA.payload
  })

  println(genericTankList.sorted)
  println(genericHelicopterList.sorted)


}
