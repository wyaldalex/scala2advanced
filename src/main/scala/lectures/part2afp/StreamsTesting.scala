package lectures.part2afp

object StreamsTesting extends App {

  val stream = Stream(111,4,5,6,9)
  println(stream)
  stream.take(4).foreach(x => println(x))

  val biggerStream : Stream[Int] = (1 to 100).toStream
  println(biggerStream.take(40).reduce(_ + _))
  println(biggerStream.take(40).foldLeft(1000000)(_ + _))

}


object StreamsTesting2 extends App {

  val massiveStream : Stream[Int] = (1 to 1000000000).toStream
  println(massiveStream.take(5).reduce(_ + _))
  /*
  However, be careful with methods that aren’t transformers.
  Calls to the following strict methods are evaluated immediately
  and can easily cause java.lang.OutOfMemoryError errors:
  stream.max
  stream.size
  stream.sum
  * */



}
