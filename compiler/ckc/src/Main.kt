import org.antlr.v4.runtime.*

val stream = CharStreams.fromString(
    "(a:Int, a:String): Int 42"
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

