package ck.grammar.visitors

import ck.ast.Type
import ckBaseVisitor
import ckParser
import java.lang.RuntimeException

class TypeVisitor(val env: List<Pair<String, Type>> = emptyList()) : ckBaseVisitor<Type>() {

    override fun visitCtorType(ctx: ckParser.CtorTypeContext?): Type {
        return Type.Op(ctx!!.TYPEID().text, ctx.types().accept(TypesVisitor(env)))
    }

    override fun visitSimpleType(ctx: ckParser.SimpleTypeContext?): Type {
        val found = env.find { p -> p.first == ctx!!.TYPEID().text }
        return when {
            found != null -> found.second
            ctx!!.TYPEID().text == "Int" -> Type.Op("Int", emptyList())
            ctx.TYPEID().text == "Unit" -> Type.Op("Unit", emptyList())
            else -> throw RuntimeException("unknown type ${ctx.TYPEID().text}")
        }
    }

    override fun visitFunType(ctx: ckParser.FunTypeContext?): Type =
        Type.Op(
            "Fun",
            (if (ctx!!.types() != null) TypesVisitor(env).visit(ctx.types())
            else emptyList())
                + this.visit(ctx.type())
        )
}