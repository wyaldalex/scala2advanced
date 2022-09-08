package lectures.recap

object ScalaGeneralExercises extends App {

  val someList = List(1,5,6,7,8,2,4,5)
  val twoListTuple = someList.splitAt(someList.size/2)

  twoListTuple._2.foreach(println)
  twoListTuple._1.foreach(println)

}
