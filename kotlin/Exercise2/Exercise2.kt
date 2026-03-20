data class WebResponse(
    val statusCode: Int,
    val statusMessage: String,
    val body: String?
)

fun describeStatus(code: Int): String {
    return when (code) {
        in 200..299 -> "Success: The request was fulfilled."
        in 400..499 -> "Client Error: Check your URL or parameters."
        in 500..599 -> "Server Error: The Lehman Server is having trouble."
        else        -> "Unknown status code."
    }
}

fun main() {
    val responses = listOf(
        WebResponse(201, "Created", """{"id": 42}"""),
        WebResponse(404, "Not Found",             null),
        WebResponse(503, "Service Unavailable",   null)
    )

    for (response in responses) {
        println("${response.statusCode} ${response.statusMessage}")
        println("  → ${describeStatus(response.statusCode)}")
        println()
    }
}