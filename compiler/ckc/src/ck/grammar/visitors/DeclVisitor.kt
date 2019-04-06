package ck.grammar.visitors

import ck.ast.Type
import ck.ast.node.Expr
import ckBaseVisitor
import ckParser
import java.lang.RuntimeException

class DeclVisitor(private val rest: Expr): ckBaseVisitor<Expr>() {

    //visit a top level decl
    override fun visitDecl(ctx: ckParser.DeclContext?): Expr {

        //add params to env
        val env = ArrayList<Pair<String, Type>>()
        if (ctx!!.typeParams() != null) {
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

        //add type to env (for recursive types)
        env.add(Pair(ctx.TYPEID().text, type))

        //a list of the new bindings that this decl creates
        val newBindings = ArrayList<Expr.Let.Binding>()

        //for each product in the sum
        ctx.sum().product().forEachIndexed { index, productContext ->

            //create constructor argument types
            val constructorArgTypes =
                if (productContext.types() == null)
                    ArrayList()
                else
                    productContext.types().accept(TypesVisitor(env))

            //create constructor function type
            val constructorType = Type.Op("Fun", constructorArgTypes + type)

            //add the constructor fun
            newBindings.add(
                Expr.Let.Binding(
                    productContext.ID().text,
                    constructorType,
                    Expr.Fun.DataConstructor(
                        constructorArgTypes.map { at -> Expr.Fun.Param(Type.newId(), at) },
                        index,
                        constructorType
                    )
                )
            )

            //create predicate type
            val predicateType = Type.Op("Fun", listOf(type, Type.Op("Int")))

            //add predicate function
            newBindings.add(
                Expr.Let.Binding(
                    "_${ctx.TYPEID().text}_is_${productContext.ID().text}",
                    predicateType,
                    Expr.Fun.DataPredicate(index, predicateType)
                )
            )

            //for each argument to this constructor add an accessor fun
            constructorArgTypes.forEachIndexed { accessorIndex, accessorRetType ->
                //create accessor fun type
                val accessorType = Type.Op("Fun", listOf(type, accessorRetType))
                //add accessor
                newBindings.add(
                    Expr.Let.Binding(
                        "_${ctx.TYPEID().text}_${productContext.ID().text}_$accessorIndex",
                        accessorType,
                        Expr.Fun.DataAccessor(accessorIndex, accessorType)
                    )
                )
            }
        }

        //return augmented expr
        return newBindings.foldRight(rest) { binding, acc -> Expr.Let(binding, acc, acc.t) }
    }
}