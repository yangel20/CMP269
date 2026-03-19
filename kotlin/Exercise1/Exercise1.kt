fun main() {
   var studentName: String = "John"
   var middleName: String? = null

   var middleNameDisplay = middleName ?: "no Middle Name"

   println("Welcome, $studentName $middleNameDisplay!")
}