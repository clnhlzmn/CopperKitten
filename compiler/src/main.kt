

package cph.ck.compiler

import ckBaseListener
import ckBaseVisitor
import ckLexer
import ckParser
import org.antlr.v4.runtime.*
import org.antlr.v4.runtime.tree.ParseTreeVisitor
import org.antlr.v4.runtime.tree.ParseTreeWalker

val stream = CharStreams.fromString("let foo = 42 ; let bar = \\():Int 42")
val lexer = ckLexer(stream)
val tokens = CommonTokenStream(lexer)
val parser = ckParser(tokens)
val context = parser.file()

class ckObject {}

class visitor : ckBaseVisitor<ckObject>() {
    override fun visitExprStatement(ctx: ckParser.ExprStatementContext?): ckObject {
        return ckObject()
    }
}

fun main() {
    println("Hello, World!")
    val res = visitor().visit(context)
    println(res)
}

