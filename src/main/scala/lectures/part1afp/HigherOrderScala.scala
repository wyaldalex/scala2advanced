package part1afp

import scala.annotation.tailrec
import scala.math.sqrt

object AnonAndHOFuncs extends App {

  val someAnonFunc = (x : Double) => {
    sqrt(x) + 100 / 7.5
  }

  val someListOfNumbers = List(1,4,5,1,21,312,3,13,123)
  someListOfNumbers.map(x => someAnonFunc(x)).foreach(println)


  def factorianN(n: Int) : Int = {
    def go(n: Int, acc: Int) : Int = {
      if (n <= 1) acc
      else go(n-1, acc*n)
    }
    go(n, 1)

  }

  def formatResult(operation:String , n: Int, f: Int => Int ): String ={
    val msg = "The formatted %s output of %d is %d"
    msg.format(operation,n,f(n))
  }
  println( formatResult("factorial",5, factorianN))

}

object PartialFuncsTesting extends App {

  def partial1[A,B,C](a: A,f: (A,B) => C) : B => C =
    (b: B) => f(a,b)

  def curry[A,B,C](f: (A,B) => C) : A => (B => C) =
    (a: A) => f(a, _)
}


object HigherOrderVariableOrdering extends App {

  case class Tank(id: Int,
                  name: String,
                  cannonMM: Double,
                  weight: Double,
                  armorThickness: Double )

  val listOfTanks = List(
    Tank(100,"T34", 75,40,200),
    Tank(1,"T14-Armata", 125,50,300),
    Tank(2,"M1A2-Abrams", 125,70,350),
    Tank(3,"Leopard-A2", 125,65,340),
    Tank(128,"Stuart", 20,15,40),
    Tank(4,"T-90", 125,60,310),
    Tank(5,"Challenger-2", 120,65,340),
  )

  //A higher order function that will take a list of tanks and sort them out based on a local implicit
  def multipleTankOrdering(tanks: List[Tank], orderingfn: (Tank,Tank) => Boolean ) : List[Tank] = {
    implicit val localImplicitOrdering: Ordering[Tank] = Ordering.fromLessThan((tanka,tankb) => {
      orderingfn(tanka,tankb)
    })
    tanks.sorted
  }

  println("Ordering By Cannon MM")
  multipleTankOrdering(listOfTanks, (tankA: Tank, tankB: Tank) => {tankA.cannonMM > tankB.cannonMM})
    .foreach(x => print(s"$x ,"))
  println("-----------------------------------------------")

  println("Ordering By Cannon MM")
  multipleTankOrdering(listOfTanks, (tankA: Tank, tankB: Tank) => {
    tankA.weight < tankB.weight
  })
    .foreach(x => print(s"$x ,"))
  println("-----------------------------------------------")


  println("A generic function to test if a list is sorted base on an ordering fn")
  def validateIfSorted[A](array: Array[A], orderingfn: (A,A) => Boolean) : Boolean = {

    @tailrec
    def go(counter: Int): Boolean = {
      //three possible scenarios
      //trivial/base case , no more to iterate, all items are ordered
      println(s"$counter + ${array(counter)}" )
      if(counter >= array.length - 1) true
      //some item does not comply with the ordering fn provided
      else if (!orderingfn(array(counter), array(counter+1))) false
      //else contiue the recursive call to the next pair
      else go(counter + 1)
    }
    go(0)
  }

  val sortedByCannonMMAscending = multipleTankOrdering(listOfTanks, (tankA: Tank, tankB: Tank) => {
    tankA.cannonMM < tankB.cannonMM
  }).toArray

   println("Is default list ordered by ascending cannon millimeters? " + validateIfSorted(listOfTanks.toArray,(tankA: Tank, tankB: Tank) => {
    tankA.cannonMM <= tankB.cannonMM
  }))

  println("Is sortedByCannonMMAscending list ordered by ascending cannon millimeters? " + validateIfSorted(sortedByCannonMMAscending, (tankA: Tank, tankB: Tank) => {
    tankA.cannonMM <= tankB.cannonMM
  }))






}