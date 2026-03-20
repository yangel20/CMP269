fun routeRequest(path: String, user: String?): String {
    return when (path) {
        "/home"   -> "Welcome to the Lehman Homepage, ${user ?: "Guest"}!"
        "/grades" -> if (user == null) "Error: Unauthorized access to grades."
                     else "Loading grades for $user..."
        else      -> "404: Path $path not found."
    }
}

fun main() {
    // /home — logged in
    println(routeRequest("/home", "User"))
    // /home — not logged in
    println(routeRequest("/home", null))

    // /grades — logged in
    println(routeRequest("/grades", "user"))
    // /grades — not logged in
    println(routeRequest("/grades", null))

    // unknown path
    println(routeRequest("/settings", "User"))
}
