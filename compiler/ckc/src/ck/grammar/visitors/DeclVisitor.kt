package ck.grammar.visitors

import ck.ast.Type
import ck.ast.node.Expr
import ckBaseVisitor
import ckParser
import java.lang.RuntimeException

class DeclVisitor(val rest: Expr): ckBaseVisitor<Expr>() {
    override fun visitDecl(ctx: ckParser.DeclContext?): Expr {
        if (ctx!!.rec != null) TODO("not implemented")

        //add params to env
        val env = ArrayList<Pair<String, Type>>()
        if (ctx.typeParams() != null) {
            for (paramNode in ctx.typeParams().TYPEID()) {
                env.add(Pair(paramNode.text, Type.newVar()))
            }
        }

        //check for duplicate params
        if (env.distinctBy { p -> p.first }.size != env.size)
            throw RuntimeException("type parameters must be unique")

        //create type
        val type = Type.Op(ctx.TYPEID().text, env.map { p -> p.second })

        //sums not implemented
        if (ctx.sum().product().size != 1) TODO("not implemented")

        //parse context of product
        val productCtx = ctx.sum().product()[0]

        //create product data ctor
        val ctorArgTypes = if (productCtx.types() == null) ArrayList() else productCtx.types().accept(TypesVisitor(env))
        val ctorType = Type.Op("Fun", ctorArgTypes + type)

        //TODO: create product field accessor functions
        val accessors = ArrayList<Expr>()
        ctorArgTypes.forEachIndexed { index, type ->
            //accessors.add()
        }

        //TODO: fold accessors and ctor onto rest
        var ret = Expr.Let(
            Expr.Let.Binding(
                productCtx.ID().text,
                ctorType,
                Expr.Fun.ProductCtor(
                    ctorArgTypes.map { at -> Expr.Fun.Param(Type.newId(), at) },
                    ctorType
                )
            ),
            rest,
            rest.t
        )

        //return augmented expr
        return ret
    }
}