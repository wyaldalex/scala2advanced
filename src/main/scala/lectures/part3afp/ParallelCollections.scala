package lectures.part3afp

object ParallelCollections extends App {

  def measure[T](operation: => T) : Long = {
    val time = System.currentTimeMillis()
    operation
    System.currentTimeMillis() - time
  }

  val testList = (1 to 10000000).toList
  val serialTime = measure {
    testList.map(_ + 1)
  }

  val parallelTime = measure {
    testList.par.map(_ + 1)
  }

  println(s"Serial Time $serialTime")
  println(s"Parallel Time $parallelTime")
}
