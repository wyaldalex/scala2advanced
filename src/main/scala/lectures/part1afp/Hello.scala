package part1afp

//to promote class parameter to field (it means it can be accessed from outside with className.field)
//just append the val or var to the class parameter
class Hello (name: String,val lastName: String) {

  val secureId: String = "x18371821"
  var insecureId: String = "s1321231"
  //The main constructor has to be called always at the end
  def this(numberX: Int) {
    this(numberX.toString, "RandomeLastName")
  }
}
