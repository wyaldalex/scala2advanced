package lectures.recap

object tailRecursion extends App {

  def fib(n: Int): Int = {
    @annotation.tailrec
    def loop(n: Int, prev: Int, cur: Int): Int =
      if (n <= 0) prev
      else loop(n - 1, cur, prev + cur)
    loop(n, 0, 1)
  }
  println(fib(5))

}

object TailRecursionPlusHOFns extends App {
  def isSorted[A](as: Array[A], ordering: (A, A) => Boolean): Boolean = {
    @annotation.tailrec
    def go(n: Int): Boolean =
      if (n >= as.length - 1) true
      else if (!ordering(as(n), as(n + 1))) false
      else go(n + 1)

    go(0)
  }

  print(isSorted(Array(1, 3, 5, 7), (x: Int, y: Int) => x < y))

  println(isSorted(Array(7, 5, 1, 3), (x: Int, y: Int) => x > y))

  println(isSorted(
    Array("Scala", "Exercises"),
    (x: String, y: String) => x.length < y.length))


}
