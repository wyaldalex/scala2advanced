package lectures.part2afp

import scala.io.Source.stdin

object PartialFunctions extends App {

  val someMatcherFunc = (x: Int) => x match {
    case 1 => 523
    case 2 => 623
    case 3 => 723
  }
  //Below is equivalent
  val somePartialFunc : PartialFunction[Int,Int] = {
    case 1 => 523
    case 2 => 623
    case 3 => 723
  }

  println(someMatcherFunc(2))
  println(somePartialFunc(2))
  //Partial Functions have specific utilities compared to total functions
  println(somePartialFunc.isDefinedAt(999))

  //lift
  val liftedFunc = somePartialFunc.lift //wraps with Option
  println(liftedFunc(999))
  println(liftedFunc(2))
  //orElseExtension
  val extendPartialFunc = somePartialFunc.orElse[Int,Int]{
    case 66 => 8888
  }
  println(extendPartialFunc(66))
  println(extendPartialFunc(2))

  //Partial funcs can be passed to our favorite HOFs
  val testList = List(1,2,3)
  val resultList1 =  testList.map(somePartialFunc)
  println(resultList1)
  //can also be injected
  val resultList2 =  testList.map{
    case 1 => 999
    case 2 => 1001
    case 3 => 1200
  }
  println(resultList2)
  //PF: can only have 1 parameter
}

object PartialFunctionsExercises extends App {
  //1) Ugly alternative to syntactic sugar using trait implementation overrides
  val uglyPartialFunc = new PartialFunction[Int,Int] {
    override def isDefinedAt(x: Int): Boolean = x == 1 || x == 2 || x == 3

    override def apply(v1: Int): Int = v1 match {
      case 1 => 523
      case 2 => 623
      case 3 => 723
    }
  }
  //all these debacle above is the same as
  val syntacticSugar : PartialFunction[Int,Int] = {
    case 1 => 523
    case 2 => 623
    case 3 => 723
  }
  println(uglyPartialFunc(2))
  println(syntacticSugar(2))

  //2) Create a chatbot using partial functions
  val megaChatbotR2x111 : PartialFunction[String,Unit] = {
    case "Hello" => println("Yo whatsup Doog")
    case "You got time" => println("Aint nobody got time for that!")
    case "Goodbye" => println("Sayonara")
    case _ => println("Huuuu??")
  }

  println("Starting Ultra Mega Chatbox")
  stdin.getLines().foreach(megaChatbotR2x111)

}
