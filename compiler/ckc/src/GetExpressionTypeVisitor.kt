
class GetExpressionTypeVisitor : ASTVisitor<Type> {

    //unit expr has unit type
    override fun visit(e: UnitExpr): Type {
        return SimpleType("Unit")
    }

    //sequence expr has type of "next" expr
    override fun visit(e: SequenceExpr): Type {
        return if (e.next == null)
            e.expr.accept(this)
        else
            e.next.accept(this)
    }

    //natural expr has type Int
    override fun visit(e: NaturalExpr): Type {
        return SimpleType("Int")
    }

    //ref has type of it's definition
    override fun visit(e: RefExpr): Type {
        val def = e.accept(FindDefinitionVisitor(e.id))
        return when (def) {
            is Param -> def.type
            is LetExpr -> def.value.accept(this)
            else -> throw CKCError("unbound reference")
        }
    }

    //apply expr has type of its target's return type
    override fun visit(e: ApplyExpr): Type {
        //e.target must be a function type
        //return it's return type
        val funType = e.target.accept(this)
        return when (funType) {
            is FunType -> funType.returnType
            else -> throw CKCError("type of target of apply expression must be a function")
        }
    }

    //unary expr has type int (for now)
    override fun visit(e: UnaryExpr): Type {
        //unary expr must be int for now
        return SimpleType("Int")
    }

    //binary expr has type int (for now)
    override fun visit(e: BinaryExpr): Type {
        //binary expr must be int for now
        return SimpleType("Int")
    }

    //conditional expr has type of alt or csq (must be the same, but not checked here)
    override fun visit(e: CondExpr): Type {
        //not attempting to check things here
        //e must have same type for csq and alt
        //return type of csq
        return e.csq.accept(this)
    }

    //assign expr has type of value
    override fun visit(e: AssignExpr): Type {
        return e.value.accept(this)
    }

    //this visitor shouldn't ever see a Param
    override fun visit(p: Param): Type {
        throw CKCError("shouldn't happen")
    }

    //fun expr has function type
    override fun visit(e: FunExpr): Type {
        return FunType(
            e.params.map { p -> p.type },
            e.type
        )
    }

    //let expr has type of body or Unit if no body
    override fun visit(e: LetExpr): Type {
        return if (e.body == null)
            SimpleType("Unit")
        else
            e.body.accept(this)
    }

    //if an IfExpr has no alt then it's return type must be Unit
    //otherwise csq and alt must be the same type so return type of csq
    override fun visit(e: IfExpr): Type {
        return if (e.alt == null)
            SimpleType("Unit")
        else
            //e must have same type for csq and alt
            //return type of csq
            e.csq.accept(this)
    }

    //while expr has type of body
    override fun visit(e: WhileExpr): Type {
        return e.body.accept(this)
    }

    //break expr has unit type or type of value if present
    override fun visit(e: BreakExpr): Type {
        return if (e.value == null)
            SimpleType("Unit")
        else
            e.value.accept(this)
    }


}

