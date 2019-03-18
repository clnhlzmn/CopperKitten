import java.lang.RuntimeException

//adapted from "Basic Polymorphic Type Checking" by Luca Cardelli
class Analyze {

    data class Env(val id: String, val type: Type, val tail: Env?)

    data class CopyEnv(val old: Type, val new: Type, val tail: CopyEnv?)

    data class NonGenericTypes(val type: Type, val tail: NonGenericTypes?)

    companion object {

        /**returns a new environment extended with [type] bound to [id]*/
        fun extend(id: String, type: Type, env: Env?): Env =
            Env(id, type, env)

        /**returns a new [CopyEnv] extended with [old] and [new]*/
        fun extendCopyEnv(old: Type, new: Type, env: CopyEnv?): CopyEnv =
            CopyEnv(old, new, env)

        /**creates a new [Type.Var], TODO: how does this use [scan] and [env]*/
        fun freshVar(type: Type, scan: CopyEnv?, env: Array<CopyEnv?>): Type {
            if (scan == null) {
                /**if [scan] is empty then create a new var and extend original [env]*/
                val newTypeVar = Type.newVar()
                env[0] = extendCopyEnv(type, newTypeVar, env[0])
                return newTypeVar
            } else if (type == scan.old) {
                /**return what is found in [scan] and don't change [env]*/
                return scan.new
            } else {
                /**continue searching [scan]*/
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

        /**Make a copy of [type]; the generic variables are copied,
         * while the non-generic variables are shared*/
        fun freshType(type: Type, list: NonGenericTypes?): Type {
            val env: Array<CopyEnv?> = Array(1) {null}
            return fresh(type, list, env)
        }

        /**Search for an identifier in an environment and return a "fresh" copy of
        the associated [Type] (using [freshType]). The identifier must be
        bound in the environment*/
        fun retrieve(id: String, env: Env?, list: NonGenericTypes?): Type {
            return if (env == null) {
                /**[env] is empty: [id] is unbound*/
                Type.Error("unbound var $id")
            } else if (env.id == id) {
                /**create a new [Type] that matches [env.type]
                 * vars are copied if they're generic and shared if not*/
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

        /**takes two types [t1] and [t2] and unifies them or throws an error*/
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

        /**Whether [type] is generic w.r.t. [list] of non-generic type variables*/
        fun isGeneric(type: Type, list: NonGenericTypes?): Boolean =
            when {
                list == null -> true
                type == list.type -> false
                else -> isGeneric(type, list.tail)
            }

        /**returns the type of [e] in [env] given [list] of [NonGenericTypes]*/
        fun analyze(e: Expr, env: Env?, list: NonGenericTypes?): Type =
            when (e) {
                is Expr.Error -> e.t
                Expr.Unit -> Type.Unit
                is Expr.Natural -> Type.Int
                is Expr.Sequence -> {
                    analyze(e.first, env, list)
                    e.t = analyze(e.second, env, list)
                    e.t
                }
                is Expr.Ref -> {
                    e.t = retrieve(e.id, env, list)
                    e.t
                }
                is Expr.Apply -> {
                    val fnType: Type = analyze(e.fn, env, list)
                    val argTypes: List<Type> = e.args.map { a -> analyze(a, env, list) }
                    val retType: Type = Type.newVar()
                    unifyType(fnType, Type.Fun(argTypes, retType))
                    e.t = retType
                    e.t
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
                    e.t = Type.Fun(paramTypes, bodyType)
                    e.t
                }
                is Expr.CFun -> TODO()
                is Expr.Let -> {
                    //extend body with binding, value is evaluated in current env
                    val bodyEnv = extend(e.id, analyze(e.value, env, list), env)
                    //then analyze body with new env
                    e.t = analyze(e.body, bodyEnv, list)
                    e.t
                }
                is Expr.If -> TODO()
                is Expr.While -> TODO()
                is Expr.Break -> TODO()
            }

    }

}