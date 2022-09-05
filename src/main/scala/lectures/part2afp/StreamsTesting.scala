package lectures.part2afp

object StreamsTesting extends App {

  val stream = Stream(111,4,5,6,9)
  println(stream)
  stream.take(4).foreach(x => println(x))

  val biggerStream : Stream[Int] = (1 to 100).toStream
  println(biggerStream.take(40).reduce(_ + _))
  println(biggerStream.take(40).foldLeft(1000000)(_ + _))

}
