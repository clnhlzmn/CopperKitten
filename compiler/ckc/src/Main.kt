import org.antlr.v4.runtime.*

val stream = CharStreams.fromString(
    "42 ? 42 : -42"   //ok
    //"{let foo = 42; ():Int {{():Int bar}()}}" //unbound reference bar
)
val lexer = ckLexer(stream)
val tokens = CommonTokenStream(lexer)
val parser = ckParser(tokens)
val context = parser.file()

fun main() {
    //get AST
    val res = context.accept(FileVisitor())
    //link enclosingScope fields
    res.accept(ScopeBuildingVisitor())
    //get program type
    val type = res.accept(GetTypeVisitor())
    //print type
    println(type)
    if (type !is ErrorType) {
        //compute function captures
        res.accept(ComputeCapturesVisitor())
        val code = compileTopLevel(res)
        //print AST
        println(res)
        //print code
        println(code)
    }
}

