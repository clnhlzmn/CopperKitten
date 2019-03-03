

//gets the type of the expression it's visiting
//also checks the expression types for correctness
class GetTypeVisitor : ASTVisitor<Type> {

    //unit first has unit type
    override fun visit(e: UnitExpr): Type {
        return SimpleType("Unit")
    }

    //check the
    override fun visit(e: SequenceExpr): Type {
        //get the type of the first operand
        val type = e.first.accept(this)
        return when (type) {
            //if error then return error
            is ErrorType -> type
            //else first is ok, discard
            else -> {
                //get type of second operand
                val nextType = e.second?.accept(this)
                when (nextType) {
                    //if nextType is null then there was no second operand, return type of first
                    null -> type
                    //else return second
                    else -> nextType
                }
            }
        }
    }

    //natural first has type Int
    override fun visit(e: NaturalExpr): Type {
        return SimpleType("Int")
    }

    //ref has type of it's definition
    override fun visit(e: RefExpr): Type {
        val def = e.accept(FindDefinitionVisitor())
        return when (def) {
            is Param -> def.type
            is LetExpr -> def.value.accept(this)
            else -> ErrorType("unbound reference ${e.id}")
        }
    }

    //apply first has type of its target's return type
    override fun visit(e: ApplyExpr): Type {
        //get target type
        val targetType = e.target.accept(this)
        //get types of arguments
        val argTypes = e.args.map { a -> a.accept(this) }
        //check target
        return when (targetType) {
            //if target is error return that
            is ErrorType -> targetType
            //target is fun
            is FunType -> {
                when {
                    //num args doesn't match
                    targetType.paramTypes.size != argTypes.size ->
                        ErrorType("incorrect number of arguments in $e")
                    //argument type mismatch
                    targetType.paramTypes.zip(argTypes).any { p -> p.first != p.second } ->
                        ErrorType("argument type mismatch in $e")
                    //everything ok so return target return type
                    else -> targetType.returnType
                }
            }
            //target must be fun
            else -> ErrorType("${e.target} must be a function in $e")
        }
    }

    override fun visit(e: UnaryExpr): Type {
        val operandType = e.operand.accept(this)
        return when {
            operandType is ErrorType -> operandType
            operandType is SimpleType && operandType.id == "Int" -> operandType
            else -> ErrorType("expected Int in $e")
        }
    }

    override fun visit(e: BinaryExpr): Type {
        val lhsType = e.lhs.accept(this)
        val rhsType = e.rhs.accept(this)
        return when {
            lhsType is ErrorType -> lhsType
            rhsType is ErrorType -> rhsType
            lhsType is SimpleType && lhsType.id == "Int"
                && rhsType is SimpleType && rhsType.id == "Int" -> SimpleType("Int")
            else -> ErrorType("operands to $e must be Int")
        }
    }

    override fun visit(e: CondExpr): Type {
        val condType = e.cond.accept(this)
        val csqType = e.csq.accept(this)
        val altType = e.alt.accept(this)
        //cond type must be int
        return when {
            condType is ErrorType -> condType
            condType is SimpleType && condType.id == "Int" -> {
                when {
                    csqType is ErrorType -> csqType
                    altType is ErrorType -> altType
                    csqType == altType -> csqType
                    else -> ErrorType("${e.csq} and ${e.alt} must have same type in $e")
                }
            }
            else -> ErrorType("${e.cond} must be Int in $e")
        }
    }

    //assign has type of value, target must have same type
    //and be assignable (var, arrayRef, ...)
    override fun visit(e: AssignExpr): Type {
        val targetType = e.target.accept(this)
        val valueType = e.value.accept(this)
        return when {
            targetType is ErrorType -> targetType
            valueType is ErrorType -> valueType
            //TODO: check that target is assignable
            targetType == valueType -> valueType
            else -> ErrorType("type mismatch between ${e.target} and ${e.value} in $e")
        }
    }

    //this visitor shouldn't ever see a Param
    override fun visit(p: Param): Type {
        return ErrorType("shouldn't happen")
    }

    override fun visit(e: FunExpr): Type {
        //function expr has same type as its body
        //should also match declared type, note that declared type
        //isn't necessary at for a function expression as the body
        //will tell us the type
        val declType = e.type
        val bodyType = e.body.accept(this)
        return when {
            bodyType is ErrorType -> bodyType
            declType != bodyType -> ErrorType("type mismatch between $declType and $bodyType in $e")
            else -> bodyType
        }
    }

    override fun visit(e: LetExpr): Type {
        //let expr has type of its body, or if no body then unit
        val valueType = e.value.accept(this)
        val bodyType = e.body?.accept(this)
        return when {
            valueType is ErrorType -> valueType
            bodyType == null -> SimpleType("Unit")
            else -> bodyType
        }
    }


    override fun visit(e: IfExpr): Type {
        val condType = e.cond.accept(this)
    }


    override fun visit(e: WhileExpr): Type {

    }


    override fun visit(e: BreakExpr): Type {

    }


}

