import org.antlr.v4.runtime.*

val stream = CharStreams.fromString(
    "let foo = bar(42, 43); " +
        "baz(); " +
        "for (;1;) {} ; " +
        "if(1) doThis() else doThat() ; " +
        "foo = (a:Int):Unit{}"
)
val lexer = ckLexer(stream)
val tokens = CommonTokenStream(lexer)
val parser = ckParser(tokens)
val context = parser.file()

fun main() {
    val res = FileVisitor().visit(context)
    println(res)
}

