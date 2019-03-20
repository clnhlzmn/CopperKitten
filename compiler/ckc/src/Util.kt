//create a string from list elements with a separator
fun <T> List<T>.toString(sep: String): String =
    if (isEmpty()) ""
    else map { i -> i.toString() }.reduce { acc, s -> "$acc$sep$s" }

data class Error(val what: String)