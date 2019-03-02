

class TypeCheckVisitor : ASTVisitor<List<CKCError>> {

    override fun visit(e: UnitExpr): List<CKCError> {
        return ArrayList()
    }

    override fun visit(e: SequenceExpr): List<CKCError> {
        val ret = e.expr.accept(this)
        return if (ret.isEmpty())
            //no error
            return if (e.next != null)
                //have next? return errors from that
                e.next.accept(this)
            else
                //no next return empty list
                ret
        else
            //return errors
            //TODO: add info
            ret
    }

    override fun visit(e: NaturalExpr): List<CKCError> {
        return ArrayList()
    }

    override fun visit(e: RefExpr): List<CKCError> {
        //make sure e.id is in scope
        val ret = ArrayList<CKCError>()
        val def = e.accept(FindDefinitionVisitor())
        if (def == null)
            ret.add(CKCError("unbound variable ${e.id}"))
        return ret
    }

    override fun visit(e: ApplyExpr): List<CKCError> {
        val ret = ArrayList<CKCError>()
        //get types of arguments
        val argTypes = e.args.map { a -> a.accept(GetTypeVisitor()) }
        if (argTypes.any{ at -> at is ErrorType }) {
            //arg contains error
            argTypes.forEach{ at ->
                if (at is ErrorType)
                    ret.add(CKCError(at.what))
            }
        } else {
            //no errors in args
            val targetType = e.target.accept(GetTypeVisitor())
            when (targetType) {
                is ErrorType -> ret.add(CKCError(targetType.what))
                is FunType -> {
                    when {
                        targetType.paramTypes.size != argTypes.size -> {
                            //not correct number of args
                            ret.add(CKCError("incorrect number of arguments"))
                        }
                        targetType.paramTypes.zip(argTypes).any { p -> p.first != p.second } -> {
                            //an argument doesn't match function parameter type
                            ret.add(CKCError("argument type mismatch"))
                        }
                        else -> {
                            //everything is fine
                        }
                    }
                }
                else -> ret.add(CKCError("target of apply expr must be a function"))
            }
        }
        return ret
    }

    override fun visit(e: UnaryExpr): List<CKCError> {
        val ret = ArrayList<CKCError>()
        val exprType = e.expr.accept(GetTypeVisitor())
        when {
            exprType is SimpleType && exprType.id == "Int" -> ret //OK
            exprType is ErrorType -> ret.add(CKCError(exprType.what)) //error
            else -> ret.add(CKCError("type error"))
        }
        return ret
    }

    override fun visit(e: BinaryExpr): List<CKCError> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: CondExpr): List<CKCError> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: AssignExpr): List<CKCError> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(p: Param): List<CKCError> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: FunExpr): List<CKCError> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: LetExpr): List<CKCError> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: IfExpr): List<CKCError> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: WhileExpr): List<CKCError> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: BreakExpr): List<CKCError> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

