package lectures.part1afp

object ForExpressionsRecap extends App {

  import lectures.common.Tanks._

  val cannonInches = for {
    tank <- tankList
  } yield tank.name -> tank.cannonMM/25.4

  cannonInches.foreach(x => println(s"Tank ${x._1} cannon in inches ${x._2}"))
}

