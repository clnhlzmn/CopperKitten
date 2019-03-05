import org.antlr.v4.runtime.*

val stream = CharStreams.fromString(
    "{let foo = 42; ():Int {{():Int foo}()}}"   //ok
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
    if (type !is ErrorType) {
        //compute function captures
        res.accept(ComputeCapturesVisitor())
    }
    //print AST
    println(res)
    //print type
    println(type)
}

