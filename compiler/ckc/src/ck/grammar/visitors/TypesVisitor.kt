package ck.grammar.visitors

import ck.ast.Type
import ckBaseVisitor
import ckParser

class TypesVisitor(val env: List<Pair<String, Type>> = emptyList()) : ckBaseVisitor<List<Type>>() {
    override fun visitTypes(ctx: ckParser.TypesContext?): List<Type> =
        ctx!!.type().map { t -> TypeVisitor(env).visit(t) }
}