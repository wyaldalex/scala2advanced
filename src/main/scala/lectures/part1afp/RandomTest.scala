package part1afp

object RandomTest extends App {


  val myNumbers = List(1,3,4,5,6,1)
  println(s"el promedio es = ${myNumbers.sum/myNumbers.size}")

  val phrase : String = "a la gran blablaba"

  if(phrase.contains("mierda")){
    println("ERROR Usar malas palabras es malo")
  }
}
