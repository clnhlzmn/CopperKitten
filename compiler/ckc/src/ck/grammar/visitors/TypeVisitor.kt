package ck.grammar.visitors

import ck.ast.Type
import ckBaseVisitor
import ckParser

class TypeVisitor : ckBaseVisitor<Type>() {

    override fun visitSimpleType(ctx: ckParser.SimpleTypeContext?): Type =
        when (ctx!!.TYPEID().text) {
            "Int" -> Type.Op("Int", emptyList())
            "Unit" -> Type.Op("Unit", emptyList())
            else -> Type.Error("unknown type ${ctx.TYPEID().text}") //SimpleType(ctx.TYPEID().text)
        }

    override fun visitFunType(ctx: ckParser.FunTypeContext?): Type =
        Type.Op(
            "Fun",
            (if (ctx!!.types() != null) TypesVisitor().visit(ctx.types())
            else emptyList())
                + TypeVisitor().visit(ctx.type())
        )
}