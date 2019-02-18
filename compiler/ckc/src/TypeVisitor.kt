class TypeVisitor : ckBaseVisitor<Type>() {

    override fun visitSimpleType(ctx: ckParser.SimpleTypeContext?): Type =
        SimpleType(ctx!!.TYPEID().text)

    override fun visitFunType(ctx: ckParser.FunTypeContext?): Type =
        FunType(
            argTypes =
            if (ctx!!.types() != null)
                TypesVisitor().visit(ctx.types())
            else
                ArrayList(),
            returnType = TypeVisitor().visit(ctx.type())
        )
}

class TypesVisitor : ckBaseVisitor<List<Type>>() {
    override fun visitTypes(ctx: ckParser.TypesContext?): List<Type> =
        ctx!!.type().map { t -> TypeVisitor().visit(t) }
}

