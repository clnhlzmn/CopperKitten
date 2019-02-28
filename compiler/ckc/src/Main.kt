import org.antlr.v4.runtime.*

val stream = CharStreams.fromString(
    "{" +
        "let foo = bar(42, 43); " +
        "baz(); " +
        "while (1) { 1 + 2 / 3 % 4 - 1 } ; " +
        "if(1) doThis() else doThat() ; " +
        "foo = (a:Int):Unit ()" +
    "}"
)
val lexer = ckLexer(stream)
val tokens = CommonTokenStream(lexer)
val parser = ckParser(tokens)
val context = parser.file()

fun main() {
    val res = context.accept(FileVisitor())
    println(res)
}

