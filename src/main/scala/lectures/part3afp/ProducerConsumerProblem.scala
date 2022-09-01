package lectures.part3afp


class SimpleContainer {
  private var value: Int = 0

  def isEmpty: Boolean = value == 0

  def set(newValue: Int) = this.value = newValue

  def get(): Int = {
    val result = this.value
    this.value = 0
    result
  }
}

object NaiveProducerConsumerProblem extends App {
  /*
  producer - consumer problem
  producer -> [x] -> consumer
   */
  def naiveProducerConsumer(): Unit ={
    val container = new SimpleContainer

    val consumer = new Thread(() => {
      println("[consumer] waiting...")
      while(container.isEmpty) {
        println("[consumer] waiting for producer to set value")
      }
      println(s"I have consumer ${container.get()}")
    })

    val producer = new Thread(() => {
      Thread.sleep(150)
      val value = 42
      println("[producer] Producing value")
      container.set(value)
      println(s"[producer] Processing completed of value $value")
    })
    consumer.start()
    producer.start()
  }
  naiveProducerConsumer()
}

object SyncProducerConsumerProblem extends App {
  /*
  producer - consumer problem
  producer -> [x] -> consumer
   */
  def syncProducerConsumer(): Unit ={
    val container = new SimpleContainer

    val consumer = new Thread(() => {
      println("[consumer] waiting...")
      container.synchronized{
        container.wait()
      }
      println(s"I have consumed ${container.get()}")
    })

    val producer = new Thread(() => {
      Thread.sleep(2000)
      val value = 42
      container.synchronized {
        println("[producer] Producing value")
        container.set(value)
        container.notify()
      }
      println(s"[producer] Processing completed of value $value")
    })
    consumer.start()
    producer.start()
  }
  syncProducerConsumer()
}
