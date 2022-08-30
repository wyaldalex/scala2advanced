package lectures.part2afp

object FunctionCurrying extends App {

  //EXERCISE
  val simpleAddFunction = (x: Int,y: Int) => x + y
  def simpleAddMethod(x: Int,y: Int) = x + y
  def curriedAddMethod (x:Int) (y: Int) = x + y

  val add7 = curriedAddMethod(7) _
  println(add7(3))

  val add7_2 =(x: Int) => simpleAddFunction(7,x)
  println(add7_2(3))

  val add7_3 = (x: Int) => simpleAddMethod(7, x)
  println(add7_3(3))

  //Undescores are powerful
  def concatenator(a: String, b: String, c: String) = a + b + c
  val reducedConcatenator = concatenator("Some intro ", _: String, " Some outro ")
  val reducedConcatenator2 = concatenator("Some intro ", _: String, _: String)
  println(reducedConcatenator("asdas"))
  println(reducedConcatenator2("asdas"," iiqioqo "))

}

object FunctionCurryingExercise extends App {
  def baseFormater (formatPattern: String) (number: Double) : String = formatPattern.format(number)
  val someNumbers = List(Math.PI,Math.E,9.66,9.81)

  val simpleFormat = baseFormater("%4.2f") _
  val complexFormat = baseFormater("%8.6f") _
  val precisionFormat = baseFormater("%14.12f") _

  someNumbers.foreach(x => println(simpleFormat(x)))
  someNumbers.foreach(x => println(complexFormat(x)))
  someNumbers.foreach(x => println(precisionFormat(x)))

}
