sealed class EnrollmentStatus {
   data class Success(val courseCode: String) : EnrollmentStatus()
   data class Error(val message: String) : EnrollmentStatus()
   object Loading : EnrollmentStatus()
}

fun printStatus(status: EnrollmentStatus) {
    when (status) {
      is EnrollmentStatus.Success -> println("enrolled in course: ${status.courseCode}")
      is EnrollmentStatus.Error -> println("enrollment failed: ${status.message}")
      is EnrollmentStatus.Loading -> println("processing enrollment..")
    }
}

fun main() {
   printStatus(EnrollmentStatus.Success("CS-401"))
   printStatus(EnrollmentStatus.Error("Course is full"))
   printStatus(EnrollmentStatus.Loading)
}