import org.antlr.v4.runtime.*

val stream = CharStreams.fromString(
    "{" +
        "let foo = 42;" +
        "let bar = ():Int foo" +
    "}"
)
val lexer = ckLexer(stream)
val tokens = CommonTokenStream(lexer)
val parser = ckParser(tokens)
val context = parser.file()

fun main() {
    val res = context.accept(FileVisitor())
    res.accept(ScopeBuildingVisitor())
    val type = res.accept(GetTypeVisitor())
    res.accept(ComputeCapturesVisitor())
    println(res)
    println(type)
}

