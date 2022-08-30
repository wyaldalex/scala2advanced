package part1afp

import scala.math.sqrt

object AnonAndHOFuncs extends App {

  val someAnonFunc = (x : Double) => {
    sqrt(x) + 100 / 7.5
  }

  val someListOfNumbers = List(1,4,5,1,21,312,3,13,123)
  someListOfNumbers.map(x => someAnonFunc(x)).foreach(println)



}
