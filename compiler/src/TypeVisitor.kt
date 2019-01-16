

class TypeVisitor : ckBaseVisitor<Type>() {

    override fun visitSimpleType(ctx: ckParser.SimpleTypeContext?): Type =
            SimpleType(ctx!!.TYPEID().text)

    override fun visitFunType(ctx: ckParser.FunTypeContext?): Type =
            FunType(
                    argTypes =
                    if (ctx!!.types() != null)
                        TypesVisitor().visit(ctx.types())
                    else
                        null,
                    returnType = TypeVisitor().visit(ctx.type())
            )
}

class TypesVisitor : ckBaseVisitor<Types>() {
    override fun visitTypes(ctx: ckParser.TypesContext?): Types =
        Types(
            type = TypeVisitor().visit(ctx!!.type()),
            types =
            if (ctx.types() != null)
                TypesVisitor().visit(ctx.types())
            else
                null
        )
}

