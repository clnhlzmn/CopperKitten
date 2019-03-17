import java.lang.RuntimeException

//adapted from "Basic Polymorphic Type Checking" by Luca Cardelli
class Analyze {

    data class Env(val id: String, val type: Type, val tail: Env?)

    data class CopyEnv(val old: Type, val new: Type, val tail: CopyEnv?)

    data class NonGenericTypes(val type: Type, val tail: NonGenericTypes?)

    companion object {

        fun extend(id: String, type: Type, env: Env?): Env =
            Env(id, type, env)

        fun extendCopyEnv(old: Type, new: Type, env: CopyEnv?): CopyEnv =
            CopyEnv(old, new, env)

        fun freshVar(type: Type, scan: CopyEnv?, env: Array<CopyEnv?>): Type {
            if (scan == null) {
                val newTypeVar = Type.newVar()
                env[0] = extendCopyEnv(type, newTypeVar, env[0])
                return newTypeVar
            } else if (type == scan.old) {
                return scan.new
            } else {
                return freshVar(type, scan.tail, env)
            }
        }

        fun fresh(type: Type, list: NonGenericTypes?, env: Array<CopyEnv?>): Type {
            val pType = prune(type)
            return when (pType) {
                is Type.Var ->
                    if (isGeneric(type, list)) {
                        freshVar(pType, env[0], env)
                    } else {
                        pType
                    }
                is Type.Fun -> {
                    Type.Fun(
                        pType.paramTypes.map { p -> fresh(p, list, env) },
                        fresh(pType.returnType, list, env)
                    )
                }
                else -> pType
            }
        }

        fun freshType(type: Type, list: NonGenericTypes?): Type {
            val env: Array<CopyEnv?> = Array(1) {null}
            return fresh(type, list, env)
        }

        fun retrieve(id: String, env: Env?, list: NonGenericTypes?): Type {
            return if (env == null) {
                Type.Error("unbound var $id")
            } else if (env.id == id) {
                freshType(env.type, list)
            } else {
                retrieve(id, env.tail, list)
            }
        }

        //Eliminate redundant instantiated variables at the top of "typeExp";
        //The result of Prune is always a non-instantiated type variable or a
        //type operator
        fun prune(t: Type): Type =
            when (t) {
                is Type.Var -> {
                    if (t.instance == null)
                        t
                    else {
                        t.instance = prune(t.instance!!)
                        t.instance!!
                    }
                }
                else -> t
            }

        fun occursInType(tVar: Type, tExp: Type): Boolean {
            val ptExp = prune(tExp)
            return when (ptExp) {
                is Type.Error -> false
                is Type.Var -> ptExp == tVar
                Type.Int -> ptExp == tVar
                Type.Unit -> ptExp == tVar
                is Type.Fun -> ptExp.paramTypes.any { pt -> occursInType(tVar, pt) }
                    || occursInType(tVar, ptExp.returnType)
            }
        }

        //takes two types and a type environment and returns a potentially
        //new environment and the unification of the two types
        fun unifyType(t1: Type, t2: Type) {
            val pt1 = prune(t1)
            val pt2 = prune(t2)
            return when (pt1) {
                is Type.Var ->
                    if (occursInType(pt1, pt2) && pt1 != pt2)
                        //un-unifiable circularity
                        throw RuntimeException("un-unifiable circularity between $pt1 and $pt2")
                    else
                        //pt1 is var and does not occur in pt2, extend env with pt1.id = pt2
                        pt1.instance = pt2
                Type.Int ->
                    when (pt2) {
                        is Type.Var -> unifyType(pt2, pt1)
                        is Type.Int -> {}
                        else -> {}
                    }
                Type.Unit ->
                    when (pt2) {
                        is Type.Var -> unifyType(pt2, pt1)
                        is Type.Unit -> {}
                        else -> throw RuntimeException("type mismatch between $pt1 and $pt2")
                    }
                is Type.Fun ->
                    when (pt2) {
                        is Type.Var -> unifyType(pt2, pt1)
                        is Type.Fun -> {
                            if (pt1.paramTypes.size != pt2.paramTypes.size)
                                throw RuntimeException("type mismatch between $pt1 and $pt2")
                            else {
                                pt1.paramTypes.zip(pt2.paramTypes)
                                    .forEach { pair -> unifyType(pair.first, pair.second) }
                                unifyType(pt1.returnType, pt2.returnType)
                            }
                        }
                        else -> throw RuntimeException("type mismatch between $pt1 and $pt2")
                    }
                else -> {}
            }
        }

        fun isGeneric(type: Type, list: NonGenericTypes?): Boolean =
            when {
                list == null -> true
                type == list.type -> false
                else -> isGeneric(type, list.tail)
            }

        fun analyze(e: Expr, env: Env?, list: NonGenericTypes?): Type =
            when (e) {
                is Expr.Error -> TODO()
                Expr.Unit -> Type.Unit
                is Expr.Sequence -> {
                    analyze(e.first, env, list)
                    analyze(e.second, env, list)
                }
                is Expr.Natural -> Type.Int
                is Expr.Ref -> {
                    retrieve(e.id, env, list)
                }
                is Expr.Apply -> {
                    val fnType: Type = analyze(e.fn, env, list)
                    val argTypes: List<Type> = e.args.map { a -> analyze(a, env, list) }
                    val retType: Type = Type.newVar()
                    unifyType(fnType, Type.Fun(argTypes, retType))
                    retType
                }
                is Expr.Unary -> TODO()
                is Expr.Binary -> TODO()
                is Expr.Cond -> TODO()
                is Expr.Assign -> TODO()
                is Expr.Fun -> {
                    //create new types for params
                    val paramTypes: List<Type> = e.params.map { Type.newVar() }
                    //extend env with mapping from param names to types
                    val bodyEnv: Env? =
                        e.params.zip(paramTypes).foldRight(env){ pair, acc -> extend(pair.first.id, pair.second, acc) }
                    //add param types to non generics
                    val bodyList: NonGenericTypes? = paramTypes.foldRight(list) { type, acc -> NonGenericTypes(type, acc) }
                    //analyze body
                    val bodyType: Type = analyze(e.body, bodyEnv, bodyList)
                    //return fun type
                    Type.Fun(paramTypes, bodyType)
                }
                is Expr.CFun -> TODO()
                is Expr.Let -> {
                    //extend body with binding, value is evaluated in current env
                    val bodyEnv = extend(e.id, analyze(e.value, env, list), env)
                    //then analyze body with new env
                    analyze(e.body, bodyEnv, list)
                }
                is Expr.If -> TODO()
                is Expr.While -> TODO()
                is Expr.Break -> TODO()
            }

    }

}