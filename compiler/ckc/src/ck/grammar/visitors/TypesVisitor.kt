package ck.grammar.visitors

import Type
import ckBaseVisitor
import ckParser

class TypesVisitor : ckBaseVisitor<List<Type>>() {
    override fun visitTypes(ctx: ckParser.TypesContext?): List<Type> =
        ctx!!.type().map { t -> TypeVisitor().visit(t) }
}