package lectures.common

object Tanks {

  case class Tank(id: Int,
                  name: String,
                  cannonMM: Double,
                  weight: Double,
                  armorThickness: Double)

  val tankList = List(
    Tank(100, "T34", 75, 40, 200),
    Tank(1, "T14-Armata", 125, 50, 300),
    Tank(2, "M1A2-Abrams", 125, 70, 350),
    Tank(3, "Leopard-A2", 125, 65, 340),
    Tank(128, "Stuart", 20, 15, 40),
    Tank(4, "T-90", 125, 60, 310),
    Tank(5, "Challenger-2", 120, 65, 340),
  )
}
