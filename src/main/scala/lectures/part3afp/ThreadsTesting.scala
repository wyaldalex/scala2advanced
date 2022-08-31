package lectures.part3afp

import java.util.concurrent.Executors

object ThreadsTesting extends App {

  val aThread = new Thread(new Runnable {
    override def run(): Unit = {
      println("Starting to compute something")
      Thread.sleep(6000)
      println("Something")
    }
  })
  aThread.start()
  println("should be printed first")
  aThread.join() //blocks thread until computation completed
  println("Should be printed last")
}

object ThreadsTesting2 extends App {

  //Variable behaviour
  val aThread = new Thread(() => (1 to 10).foreach(x => {
    println(s"Computing A $x")
    Thread.sleep(30)
  }))

  val aThread2 = new Thread(() => (1 to 10).foreach(x => {
    println(s"Computing B $x")
    Thread.sleep(30)
  }))
  aThread.start()
  aThread2.start()

}

object ExecutorTesting extends App {

  val pool = Executors.newFixedThreadPool(10)
  val runnableList = List(new Runnable {
    override def run(): Unit = {
      println("Running long computation XA")
      Thread.sleep(3000)
      println("Finishing long computation XA")
    }
  }, new Runnable {
    override def run(): Unit = {
      println("Running long computation XB")
      Thread.sleep(3000)
      println("Finishing long computation XB")
    }
  },
    new Runnable {
      override def run(): Unit = {
        println("Running long computation XC")
        Thread.sleep(3000)
        println("Finishing long computation XC")
      }
    })
  pool.execute(() => {
    println("Doing long computation")
    Thread.sleep(2000)
    println("Finishing long computation")
  })

  //Should finish X computations very closely at around 3 seconds
  runnableList.foreach(x => pool.execute(x))
}
