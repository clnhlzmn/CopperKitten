

//gets the type of the expression it's visiting
//also checks the expression types for correctness
class GetTypeVisitor : BaseASTVisitor<Type>() {

    //unit first has unit type
    override fun visit(e: UnitExpr): Type {
        return UnitType
    }

    //check first and then check next
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
        return IntType
    }

    //ref has type of it's definition
    override fun visit(e: RefExpr): Type {
        val def = e.accept(GetDefinitionVisitor())
        return when(def) {
            is Definition -> {
                return when (def) {
                    is Definition.Let -> def.node.value.accept(this)
                    is Definition.Param -> def.node.type
                }
            }
            null -> ErrorType("unbound reference ${e.id}")
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
        return when (operandType) {
            is ErrorType -> operandType
            is IntType -> operandType
            else -> ErrorType("expected Int in $e")
        }
    }

    override fun visit(e: BinaryExpr): Type {
        val lhsType = e.lhs.accept(this)
        val rhsType = e.rhs.accept(this)
        return when {
            lhsType is ErrorType -> lhsType
            rhsType is ErrorType -> rhsType
            lhsType is IntType && rhsType is IntType -> IntType
            else -> ErrorType("operands to $e must be Int")
        }
    }

    override fun visit(e: CondExpr): Type {
        val condType = e.cond.accept(this)
        val csqType = e.csq.accept(this)
        val altType = e.alt.accept(this)
        //cond type must be int
        return when (condType) {
            is ErrorType -> condType
            is IntType -> {
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

    override fun visit(e: FunExpr): Type {
        //function expr has function type
        val declType = e.type
        val bodyType = e.body.accept(this)
        return when {
            bodyType is ErrorType -> bodyType
            declType != bodyType -> ErrorType("type mismatch between ${e.type} and $bodyType in $e")
            e.params.distinctBy { p -> p.id }.count() != e.params.size -> ErrorType("parameters must be distinct in $e")
            else -> FunType(
                e.params.map { p -> p.type },
                e.type
            )
        }
    }

    override fun visit(e: CFunExpr): Type =
        e.sig

    override fun visit(e: LetExpr): Type {
        //let expr has type of its body, or if no body then unit
        val valueType = e.value.accept(this)
        val bodyType = e.body?.accept(this)
        return when {
            valueType is ErrorType -> valueType
            bodyType == null -> UnitType
            else -> bodyType
        }
    }


    override fun visit(e: IfExpr): Type {
        val condType = e.cond.accept(this)
        val csqType = e.csq.accept(this)
        val altType = e.alt?.accept(this)
        return when {
            //if cond is error return that
            condType is ErrorType -> condType
            //cond must be Int
            condType !is IntType -> ErrorType("${e.cond} must have type Int in $e")
            //if csq is error return that
            csqType is ErrorType -> csqType
            //if no alt then csq must be Unit
            altType == null && csqType !is UnitType -> ErrorType("${e.csq} must have type Unit when no alternate in $e")
            //no alt and csq is unit
            altType == null && csqType is UnitType -> UnitType
            //if alt is error return that
            altType is ErrorType -> altType
            //csq and alt must match
            csqType != altType -> ErrorType("type mismatch between $csqType and $altType in $e")
            //otherwise it's csq type
            else -> csqType
        }
    }

    //For now loops return ()
    override fun visit(e: WhileExpr): Type {
        val condType = e.cond.accept(this)
        val bodyType = e.body.accept(this)
        return when {
            condType is ErrorType -> condType
            bodyType is ErrorType -> bodyType
            //cond must be Int
            condType !is IntType -> ErrorType("${e.cond} must have type Int in $e")
            //otherwise it's Unit
            else -> UnitType
        }
    }


    override fun visit(e: BreakExpr): Type {
        val valueType = e.value?.accept(this)
        return when (valueType) {
            null -> UnitType
            else -> ErrorType("break not accepting value at this time")
        }
    }


}

