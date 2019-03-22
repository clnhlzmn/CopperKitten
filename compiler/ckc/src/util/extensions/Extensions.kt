package util.extensions

//create a string from list elements with a separator
fun <T> List<T>.toDelimitedString(delimiter: String): String =
    if (isEmpty()) ""
    else map { i -> i.toString() }.reduce { acc, s -> "$acc$delimiter$s" }

data class Error(val what: String)