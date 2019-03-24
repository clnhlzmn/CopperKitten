package ck.grammar.visitors

import ck.ast.Type
import ckBaseVisitor
import ckParser
import java.lang.RuntimeException

class TypeVisitor : ckBaseVisitor<Type>() {

    override fun visitSimpleType(ctx: ckParser.SimpleTypeContext?): Type =
        when (ctx!!.TYPEID().text) {
            "Int" -> Type.Op("Int", emptyList())
            "Unit" -> Type.Op("Unit", emptyList())
            else -> throw RuntimeException("unknown type ${ctx.TYPEID().text}")
        }

    override fun visitFunType(ctx: ckParser.FunTypeContext?): Type =
        Type.Op(
            "Fun",
            (if (ctx!!.types() != null) TypesVisitor().visit(ctx.types())
            else emptyList())
                + TypeVisitor().visit(ctx.type())
        )
}