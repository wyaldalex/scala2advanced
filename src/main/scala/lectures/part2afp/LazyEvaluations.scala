package lectures.part2afp

object LazyEvaluations extends App {

  //CALL BY NAME EXAMPLE
  def byNameMethod(n: => Int) : Int = {
    //CALL BY NAME - convert to lazy val to execute all at the same time
    //waiting time reduced from 3 seconds to 1 second
    lazy val t = n
    t + t + t + 1
  }
  def retrieveSlowData = {
    println("waiting")
    Thread.sleep(1000)
    42
  }
  println(byNameMethod(retrieveSlowData))


  //Lazy withFilter
  def lessThan30(n: Int) : Boolean = {
    println("Analyzing less than 30")
    n < 30
  }
  def greaterThan20(n: Int) : Boolean = {
    println("Analyzing greater than 20")
    n > 20
  }
  val someNumbers = List(47,12,23,29,55,27,11)
  val step1 = someNumbers.filter(lessThan30)
  val step2 = step1.filter(greaterThan20)
  println(step2)


  val step1_lazy = someNumbers.withFilter(lessThan30)
  val step2_lazy = step1_lazy.withFilter(greaterThan20)
  step2_lazy.foreach(println)



}
