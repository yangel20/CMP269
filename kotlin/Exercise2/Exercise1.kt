data class WebResponse(
    val statusCode: Int,
    val statusMessage: String,
    val body: String?
)

fun main() {
    val success = WebResponse(
        statusCode = 200,
        statusMessage = "OK",
        body = """{"message": "fetched successfully"}"""
    )
    val notFound = WebResponse(
        statusCode = 404,
        statusMessage = "Not Found",
        body = null
    )
    println(success)
    println(notFound)
}