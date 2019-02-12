import org.antlr.v4.runtime.*

val stream = CharStreams.fromString(
    "Main:\n" +
    "in\n" +
    "10\n" +
    "enter\n" +
    "leave\n" +
    "layout [0, 2]\n" +
    "alloc [10, 0, 1, 2]\n" +
    "alloc [10, *]\n" +
    "jump Main\n"
)
val lexer = ckaLexer(stream)
val tokens = CommonTokenStream(lexer)
val parser = ckaParser(tokens)
val context = parser.file()

fun main() {
    val res = FileVisitor().visit(context)
    println(res)
}