//
//
////gets the type of the expression it's visiting
////also checks the expression types for correctness
//class GetTypeVisitor : BaseASTVisitor<Type>() {
//
//    //unit first has unit type
//    override fun visit(e: Expr.Unit): Type {
//        return Type.Unit
//    }
//
//    //check first and then check next
//    override fun visit(e: Expr.Sequence): Type {
//        //get the type of the first operand
//        val type = e.first.accept(this)
//        return when (type) {
//            //if error then return error
//            is Type.Error -> type
//            //else first is ok, discard
//            else -> e.second.accept(this)
//        }
//    }
//
//    //natural first has type Int
//    override fun visit(e: Expr.Natural): Type {
//        return Type.Int
//    }
//
//    //ref has type of it's definition
//    override fun visit(e: Expr.Ref): Type {
//        val def = e.accept(GetDefinitionVisitor())
//        return when(def) {
//            is Definition -> {
//                return when (def) {
//                    is Definition.Let -> def.node.value.accept(this)
//                    is Definition.Param -> def.node.type
//                }
//            }
//            null -> Type.Error("unbound reference ${e.id}")
//        }
//    }
//
//    //apply first has type of its fn's return type
//    override fun visit(e: Expr.Apply): Type {
//        //get fn type
//        val targetType = e.fn.accept(this)
//        //get types of arguments
//        val argTypes = e.args.map { a -> a.accept(this) }
//        //check fn
//        return when (targetType) {
//            //if fn is error return that
//            is Type.Error -> targetType
//            //fn is fun
//            is Type.Fun -> {
//                when {
//                    //num args doesn't match
//                    targetType.paramTypes.size != argTypes.size ->
//                        Type.Error("incorrect number of arguments in $e")
//                    //argument type mismatch
//                    targetType.paramTypes.zip(argTypes).any { p -> p.first != p.second } ->
//                        Type.Error("argument type mismatch in $e")
//                    //everything ok so return fn return type
//                    else -> targetType.returnType
//                }
//            }
//            //fn must be fun
//            else -> Type.Error("${e.fn} must be a function in $e")
//        }
//    }
//
//    override fun visit(e: Expr.Unary): Type {
//        val operandType = e.operand.accept(this)
//        return when (operandType) {
//            is Type.Error -> operandType
//            is Type.Int -> operandType
//            else -> Type.Error("expected Int in $e")
//        }
//    }
//
//    override fun visit(e: Expr.Binary): Type {
//        val lhsType = e.lhs.accept(this)
//        val rhsType = e.rhs.accept(this)
//        return when {
//            lhsType is Type.Error -> lhsType
//            rhsType is Type.Error -> rhsType
//            lhsType is Type.Int && rhsType is Type.Int -> Type.Int
//            else -> Type.Error("operands to $e must be Int")
//        }
//    }
//
//    override fun visit(e: Expr.Cond): Type {
//        val condType = e.cond.accept(this)
//        val csqType = e.csq.accept(this)
//        val altType = e.alt.accept(this)
//        //cond type must be int
//        return when (condType) {
//            is Type.Error -> condType
//            is Type.Int -> {
//                when {
//                    csqType is Type.Error -> csqType
//                    altType is Type.Error -> altType
//                    csqType == altType -> csqType
//                    else -> Type.Error("${e.csq} and ${e.alt} must have same type in $e")
//                }
//            }
//            else -> Type.Error("${e.cond} must be Int in $e")
//        }
//    }
//
//    //assign has type of value, fn must have same type
//    //and be assignable (var, arrayRef, ...)
//    override fun visit(e: Expr.Assign): Type {
//        val targetType = e.target.accept(this)
//        val valueType = e.value.accept(this)
//        return when {
//            targetType is Type.Error -> targetType
//            valueType is Type.Error -> valueType
//            //TODO: check that fn is assignable
//            targetType == valueType -> valueType
//            else -> Type.Error("type mismatch between ${e.target} and ${e.value} in $e")
//        }
//    }
//
//    override fun visit(e: Expr.Fun): Type {
//        //function expr has function type
//        val declType = e.type
//        val bodyType = e.body.accept(this)
//        return when {
//            bodyType is Type.Error -> bodyType
//            declType != bodyType -> Type.Error("type mismatch between ${e.type} and $bodyType in $e")
//            e.params.distinctBy { p -> p.id }.count() != e.params.size -> Type.Error("parameters must be distinct in $e")
//            else -> Type.Fun(
//                e.params.map { p -> p.type },
//                e.type
//            )
//        }
//    }
//
//    override fun visit(e: Expr.CFun): Type =
//        e.sig
//
//    override fun visit(e: Expr.Let): Type {
//        //let expr has type of its body, or if no body then unit
//        val valueType = e.value.accept(this)
//        val bodyType = e.body.accept(this)
//        return when (valueType) {
//            is Type.Error -> valueType
//            else -> bodyType
//        }
//    }
//
//
//    override fun visit(e: Expr.If): Type {
//        val condType = e.cond.accept(this)
//        val csqType = e.csq.accept(this)
//        val altType = e.alt?.accept(this)
//        return when {
//            //if cond is error return that
//            condType is Type.Error -> condType
//            //cond must be Int
//            condType !is Type.Int -> Type.Error("${e.cond} must have type Int in $e")
//            //if csq is error return that
//            csqType is Type.Error -> csqType
//            //if no alt then csq must be Unit
//            altType == null && csqType !is Type.Unit -> Type.Error("${e.csq} must have type Unit when no alternate in $e")
//            //no alt and csq is unit
//            altType == null && csqType is Type.Unit -> Type.Unit
//            //if alt is error return that
//            altType is Type.Error -> altType
//            //csq and alt must match
//            csqType != altType -> Type.Error("type mismatch between $csqType and $altType in $e")
//            //otherwise it's csq type
//            else -> csqType
//        }
//    }
//
//    //For now loops return ()
//    override fun visit(e: Expr.While): Type {
//        val condType = e.cond.accept(this)
//        val bodyType = e.body.accept(this)
//        return when {
//            condType is Type.Error -> condType
//            bodyType is Type.Error -> bodyType
//            //cond must be Int
//            condType !is Type.Int -> Type.Error("${e.cond} must have type Int in $e")
//            //otherwise it's Unit
//            else -> Type.Unit
//        }
//    }
//
//
//    override fun visit(e: Expr.Break): Type {
//        val valueType = e.value?.accept(this)
//        return when (valueType) {
//            null -> Type.Unit
//            else -> Type.Error("break not accepting value at this time")
//        }
//    }
//
//
//}
//
