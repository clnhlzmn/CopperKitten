

package cph.ck.compiler

import ckBaseVisitor
import ckLexer
import ckParser
import org.antlr.v4.runtime.*

val stream = CharStreams.fromString("42")
val lexer = ckLexer(stream)
val tokens = CommonTokenStream(lexer)
val parser = ckParser(tokens)
val context = parser.file()

class Object {}

data class NaturalExpr(val value:Int)

class visitNaturalExpr : ckBaseVisitor<NaturalExpr>() {
    override fun visitNaturalExpr(ctx: ckParser.NaturalExprContext?): NaturalExpr =
        NaturalExpr(Integer.valueOf(ctx!!.text))
}

class visitor : ckBaseVisitor<Object>() {
    override fun visitExprStatement(ctx: ckParser.ExprStatementContext?): Object =
            Object()
}

fun main() {
    val res = visitNaturalExpr().visit(context)
    println(res)
}

