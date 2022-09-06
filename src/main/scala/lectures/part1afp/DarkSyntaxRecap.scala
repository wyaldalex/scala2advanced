package lectures.part1afp

import java.awt.Composite
import scala.util.Try

object DarkSyntaxRecapSingleParamMethods extends App {

  val someSingleParamMethod = (x: Double) => x*100

  //param can be provided as the result of an expresion
  val result1 =  someSingleParamMethod {
    println("Doing complex calculation")
    100
  }
  println(result1)

  //Another example is the Try Monda
  val result2 = Try {
    throw new RuntimeException("EXPLOSION")
    9090
  }
  println(result2)

  //stuff like map can also be fed like that
  List(1,2,3).map( x => {
    x*100
  }).foreach(println(_))
  //or
  List(1, 2, 3).map {x =>
    x * 100
  }.foreach(println(_))

}

object DarkSyntaxRecapSingleUnimplementedMethods extends App {

  trait SomeTrait {
    def returnX(x: Int) : Int
  }

  //as there is only one unimpemented member, the compiler can infer that you are providing
  //the implementation
  val someImplementation : SomeTrait = (x: Int) => x * 900
  println(someImplementation.returnX(900))

  /*
  Also works with abstract classes that have only 1 member not implemented
   */
  abstract class SomeAbstractClass {
    def returnSomeString : String = {
      "ajdakjdlaks"
    }
    def returnSomeDouble(x: Double) : Double
  }

  val implementation2 : SomeAbstractClass = (x: Double) => x*1000
  println(implementation2.returnSomeString)
  println(implementation2.returnSomeDouble(1000))

}

object AlienStuf extends App {
  /*
  ::
  #::
  are alien
   */

  val someList = 2 :: List(1,3,4)
  println(someList)
  //in scala objects decided their method order
  //stuff ending in : means that it will operate as [OBJECT].[methodName]([value])
  println(11 :: 40 :: 2 :: List(1,4,5))

  class MyStream[T] {
    def -->:(value: T) : MyStream[T] = this
  }

  val myStream = 1 -->: 2 -->: 100 -->: new MyStream[Int]
  println(myStream)
}

object MultiWordMethods extends App {

  class Roboto5000(name: String) {
    def `I have to execute order` (order: Int) : String = {
      s"Executing order number $order"
    }
  }
  val robot1 = new Roboto5000("COMETTA")
  println(robot1 `I have to execute order` 66)

}

object InfixTypes extends App {
  class Composite[A,B]
  val someComposite : Int Composite String = ???

  class -->[A,B]
  val towards: Int --> String = ???

}


