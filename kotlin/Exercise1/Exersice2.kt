data class Laptop(val brand: String, val ramGB: Int)

fun Int.toLehmanGigabytes(): String {
   return "$this GB (Lehman Standard)"
}

fun main() {
   val laptop1 = Laptop("Dell", 16)
   val laptop2 = Laptop("Apple", 32)

   println(laptop1)
   println("RAM: ${laptop1.ramGB.toLehmanGigabytes()}")

   println(laptop2)
   println("RAM: ${laptop2.ramGB.toLehmanGigabytes()}")
}