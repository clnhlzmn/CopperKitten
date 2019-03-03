import org.antlr.v4.runtime.*

val stream = CharStreams.fromString(
    ""
)
val lexer = ckLexer(stream)
val tokens = CommonTokenStream(lexer)
val parser = ckParser(tokens)
val context = parser.file()

fun main() {
    val res = context.accept(FileVisitor())
    res.accept(ScopeBuildingVisitor())
    val type = res.accept(GetTypeVisitor())
    println(res)
    println(type)
}

