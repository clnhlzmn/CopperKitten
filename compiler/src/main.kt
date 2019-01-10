

import org.antlr.v4.runtime.*

val stream = CharStreams.fromString("let foo = bar(42, 43); baz(); for (;1;) {} ; if(1) doThis() else doThat(); foo = ()->Unit{}")
val lexer = ckLexer(stream)
val tokens = CommonTokenStream(lexer)
val parser = ckParser(tokens)
val context = parser.file()

open class Expr

open class Statement

//Statements

data class BlockStatement(val statements:List<Statement>) : Statement() {
    override fun toString(): String =
        if (statements.isEmpty())
            "{}"
        else
            "{${statements.map { a -> a.toString() }.reduce { acc, s -> "$acc; $s" }}}"
}

data class LetStatement(val id:String, val value:Expr) : Statement() {
    override fun toString(): String =
        "let $id = $value"
}

data class ForStatement(
    val init:Statement?, val cond:Expr, val fin:Expr?, val statement:Statement) : Statement() {
    override fun toString(): String =
        "for (${init?.toString() ?: ""}; $cond; ${fin?.toString() ?: ""}) $statement"
}

data class IfStatement(val cond:Expr, val con:Statement, val alt:Statement?) : Statement() {
    override fun toString(): String =
        "if ($cond) $con${if (alt != null) " else $alt" else "" }"
}

data class ReturnStatement(val expr:Expr?) : Statement() {
    override fun toString(): String =
        "return $expr"
}

data class ExprStatement(val expr:Expr) : Statement() {
    override fun toString(): String =
        "$expr"
}

//Expressions

data class NaturalExpr(val value:Int) : Expr() {
    override fun toString():String =
        value.toString()
}

data class RefExpr(val id:String) : Expr() {
    override fun toString(): String =
        id
}

data class ApplyExpr(val target:Expr, val args:List<Expr>) : Expr() {
    override fun toString(): String =
        "$target(${if (args.isNotEmpty())
                       args.map { a -> a.toString() }.reduce { acc, s -> "$acc, $s" }
                   else
                       ""})"
}

data class UnaryExpr(val op:String, val expr:Expr) : Expr() {
    override fun toString(): String =
        "$op $expr"
}

data class BinaryExpr(val lhs:Expr, val op:String, val rhs:Expr) : Expr() {
    override fun toString(): String =
            "$lhs $op $rhs"
}

data class CondExpr(val cond:Expr, val con:Expr, val alt:Expr) : Expr() {
    override fun toString(): String =
            "$cond ? $con : $alt"
}

data class AssignExpr(val target:Expr, val value:Expr) : Expr() {
    override fun toString(): String =
            "$target = $value"
}

data class FormalParameter(val id:String, val type:String)

data class FunExpr(val parameters:List<FormalParameter>, val type:Type?, val body:Statement) : Expr() {
    override fun toString(): String =
            "() ->${if (type != null) " $type" else ""} $body"
}

data class LetExpr(val id:String, val value:Expr, val body:Expr) : Expr()

open class Type

data class SimpleType(val id:String) : Type() {
    override fun toString(): String = id
}

data class FunType(val argTypes:List<Type>, val returnType:Type) : Type() {
    override fun toString(): String =
        if (argTypes.isEmpty())
            "() -> $returnType"
        else
            "(${argTypes.map { a -> toString() }.reduce { acc, s -> "$acc, $s" }}) -> $returnType"
}

//Expression Visitors

class ExprsVisitor : ckBaseVisitor<List<Expr>>() {
    override fun visitExprs(ctx: ckParser.ExprsContext?): List<Expr> =
        ArrayList(ctx!!.expr().map { expr -> ExprVisitor().visit(expr) })
}

class ParamsVisitor : ckBaseVisitor<List<FormalParameter>>() {
    override fun visitParams(ctx: ckParser.ParamsContext?): List<FormalParameter> =
        ctx!!.param().map { p -> FormalParameter(p!!.ID().text, p.TYPEID().text) }
}

class TypesVisitor : ckBaseVisitor<List<Type>>() {
    override fun visitTypes(ctx: ckParser.TypesContext?): List<Type> =
        if (ctx!!.type().isNotEmpty())
            ctx.type().map { t -> TypeVisitor().visit(t) }
        else
            ArrayList()
}

class TypeVisitor : ckBaseVisitor<Type>() {

    override fun visitSimpleType(ctx: ckParser.SimpleTypeContext?): Type =
        SimpleType(ctx!!.TYPEID().text)

    override fun visitFunType(ctx: ckParser.FunTypeContext?): Type =
        FunType(
            argTypes = if (ctx!!.types() != null)
                           TypesVisitor().visit(ctx.types())
                       else
                           ArrayList(),
            returnType = TypeVisitor().visit(ctx.type())
        )
}

class ExprVisitor : ckBaseVisitor<Expr>() {
    override fun visitNaturalExpr(ctx: ckParser.NaturalExprContext?): Expr =
        NaturalExpr(Integer.valueOf(ctx!!.text))

    override fun visitSubExpr(ctx: ckParser.SubExprContext?): Expr =
        ExprVisitor().visit(ctx!!.expr())

    override fun visitRefExpr(ctx: ckParser.RefExprContext?): Expr =
        RefExpr(ctx!!.text)

    override fun visitApplyExpr(ctx: ckParser.ApplyExprContext?): Expr =
        ApplyExpr(
            target = ExprVisitor().visit(ctx!!.expr()),
            args = if (ctx.exprs() != null) ExprsVisitor().visit(ctx.exprs())
                   else ArrayList()
        )

    override fun visitUnaryExpr(ctx: ckParser.UnaryExprContext?): Expr =
        UnaryExpr(
            op = ctx!!.op.text,
            expr = ExprVisitor().visit(ctx.expr())
        )

    override fun visitMultExpr(ctx: ckParser.MultExprContext?): Expr =
        BinaryExpr(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            op = ctx.op.text,
            rhs = ExprVisitor().visit(ctx.rhs)
        )

    override fun visitAddExpr(ctx: ckParser.AddExprContext?): Expr =
        BinaryExpr(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            op = ctx.op.text,
            rhs = ExprVisitor().visit(ctx.rhs)
        )

    override fun visitShiftExpr(ctx: ckParser.ShiftExprContext?): Expr =
        BinaryExpr(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            op = ctx.op.text,
            rhs = ExprVisitor().visit(ctx.rhs)
        )

    override fun visitRelExpr(ctx: ckParser.RelExprContext?): Expr =
        BinaryExpr(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            op = ctx.op.text,
            rhs = ExprVisitor().visit(ctx.rhs)
        )

    override fun visitEqualityExpr(ctx: ckParser.EqualityExprContext?): Expr =
        BinaryExpr(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            op = ctx.op.text,
            rhs = ExprVisitor().visit(ctx.rhs)
        )

    override fun visitBitAndExpr(ctx: ckParser.BitAndExprContext?): Expr =
        BinaryExpr(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            op = "&",
            rhs = ExprVisitor().visit(ctx.rhs)
        )

    override fun visitBitXorExpr(ctx: ckParser.BitXorExprContext?): Expr =
        BinaryExpr(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            op = "^",
            rhs = ExprVisitor().visit(ctx.rhs)
        )

    override fun visitBitOrExpr(ctx: ckParser.BitOrExprContext?): Expr =
        BinaryExpr(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            op = "|",
            rhs = ExprVisitor().visit(ctx.rhs)
        )

    override fun visitAndExpr(ctx: ckParser.AndExprContext?): Expr =
        BinaryExpr(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            op = "&&",
            rhs = ExprVisitor().visit(ctx.rhs)
        )

    override fun visitOrExpr(ctx: ckParser.OrExprContext?): Expr =
        BinaryExpr(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            op = "||",
            rhs = ExprVisitor().visit(ctx.rhs)
        )

    override fun visitCondExpr(ctx: ckParser.CondExprContext?): Expr =
        CondExpr(
            cond = ExprVisitor().visit(ctx!!.cond),
            con = ExprVisitor().visit(ctx.con),
            alt = ExprVisitor().visit(ctx.alt)
        )

    override fun visitFunExpr(ctx: ckParser.FunExprContext?): Expr =
        FunExpr(
            parameters = if (ctx!!.params() != null) ArrayList(ParamsVisitor().visit(ctx.params()))
                         else ArrayList(),
            type = if (ctx.type() != null) TypeVisitor().visit(ctx.type())
                   else null,
            body = StatementVisitor().visit(ctx.statement())
        )

    override fun visitLetExpr(ctx: ckParser.LetExprContext?): Expr =
        LetExpr(
            id = ctx!!.ID().text,
            value = ExprVisitor().visit(ctx.value),
            body = ExprVisitor().visit(ctx.body)
        )
}

//Statement Visitors

class StatementVisitor : ckBaseVisitor<Statement>() {
    override fun visitBlockStatement(ctx: ckParser.BlockStatementContext?): Statement =
        if (ctx!!.statements() != null)
            BlockStatement(StatementsVisitor().visit(ctx.statements()))
        else
            BlockStatement(ArrayList())

    override fun visitIfStatement(ctx: ckParser.IfStatementContext?): Statement =
        IfStatement(
            cond = ExprVisitor().visit(ctx!!.expr()),
            con = StatementVisitor().visit(ctx.con),
            alt = if (ctx.alt == null) null else StatementVisitor().visit(ctx.alt)
        )

    override fun visitExprStatement(ctx: ckParser.ExprStatementContext?): Statement =
        ExprStatement(ExprVisitor().visit(ctx!!.expr()))

    override fun visitLetStatement(ctx: ckParser.LetStatementContext?): Statement =
        LetStatement(id = ctx!!.ID().toString(), value = ExprVisitor().visit(ctx.expr()))

    override fun visitReturnStatement(ctx: ckParser.ReturnStatementContext?): Statement =
        ReturnStatement(ExprVisitor().visit(ctx!!.expr()))

    override fun visitForStatement(ctx: ckParser.ForStatementContext?): Statement =
        ForStatement(
            init = if (ctx!!.init == null) null else StatementVisitor().visit(ctx.init),
            cond = ExprVisitor().visit(ctx.cond),
            fin = if (ctx.fin == null) null else ExprVisitor().visit(ctx.fin),
            statement = StatementVisitor().visit(ctx.block)
        )
}

class StatementsVisitor : ckBaseVisitor<List<Statement>>() {
    override fun visitStatements(ctx: ckParser.StatementsContext?): List<Statement> {
        val ret = ArrayList<Statement>()
        ret.add(StatementVisitor().visit(ctx!!.statement()));
        if (ctx.statements() != null) {
            ret.addAll(StatementsVisitor().visit(ctx.statements()))
        }
        return ret
    }
}

//ck File visitor

class FileVisitor : ckBaseVisitor<BlockStatement>() {
    override fun visitFile(ctx: ckParser.FileContext?): BlockStatement {
        if (ctx!!.statements() != null) {
            return BlockStatement(StatementsVisitor().visit(ctx.statements()))
        }
        return BlockStatement(ArrayList());
    }
}

fun main() {
    val res = FileVisitor().visit(context)
    println(res)
}

