

class TypeCheckVisitor : ASTVisitor<List<CKCError>> {

    override fun visit(e: UnitExpr): List<CKCError> {
        return ArrayList()
    }

    override fun visit(e: SequenceExpr): List<CKCError> {
        val ret = e.first.accept(this)
        return if (ret.isEmpty())
            //no error
            return if (e.second != null)
                //have second? return errors from that
                e.second.accept(this)
            else
                //no second return empty list
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
        //TODO: also visit args with this
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
                else -> ret.add(CKCError("target of apply first must be a function"))
            }
        }
        return ret
    }

    override fun visit(e: UnaryExpr): List<CKCError> {
        val ret = ArrayList<CKCError>()
        //get type of first
        val exprType = e.operand.accept(GetTypeVisitor())
        //check first also
        ret.addAll(e.operand.accept(this))
        when {
            exprType is SimpleType && exprType.id == "Int" -> run {}
            exprType is ErrorType -> ret.add(CKCError(exprType.what)) //error
            else -> ret.add(CKCError("type error"))
        }
        return ret
    }

    override fun visit(e: BinaryExpr): List<CKCError> {
        val ret = ArrayList<CKCError>()

        return ret
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

