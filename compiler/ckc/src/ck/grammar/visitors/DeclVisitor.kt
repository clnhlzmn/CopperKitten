package ck.grammar.visitors

import ck.ast.Type
import ck.ast.node.Expr
import ckBaseVisitor
import ckParser
import java.lang.RuntimeException

class DeclVisitor(private val rest: Expr): ckBaseVisitor<Expr>() {

    //visit a top level decl
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
        //TODO: check that type name is unique in context, maybe not here
        val type = Type.Op(ctx.TYPEID().text, env.map { p -> p.second })

        //sums not implemented
        if (ctx.sum().product().size != 1) TODO("not implemented")

        //parse context of product
        val productCtx = ctx.sum().product()[0]

        //create product ctor
        val ctorArgTypes = if (productCtx.types() == null) ArrayList() else productCtx.types().accept(TypesVisitor(env))
        val ctorType = Type.Op("Fun", ctorArgTypes + type)

        //a list of the new bindings that this decl creates
        val newBindings = ArrayList<Expr.Let.Binding>()

        //add the ctor
        newBindings.add(
            Expr.Let.Binding(
                productCtx.ID().text,
                ctorType,
                Expr.Fun.ProductCtor(
                    ctorArgTypes.map { at -> Expr.Fun.Param(Type.newId(), at) },
                    ctorType
                )
            )
        )

        //add the accessors
        ctorArgTypes.forEachIndexed { index, argType ->
            val accessorType = Type.Op("Fun", listOf(type, argType))
            newBindings.add(
                Expr.Let.Binding(
                    "_${ctx.TYPEID().text}_$index",
                    accessorType,
                    Expr.Fun.ProductAccessor(
                        index,
                        accessorType
                    )
                )
            )
        }

        //return augmented expr
        return newBindings.foldRight(rest) { binding, acc -> Expr.Let(binding, acc, acc.t) }
    }
}