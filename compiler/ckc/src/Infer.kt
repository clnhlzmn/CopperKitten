import java.lang.RuntimeException
import kotlin.math.exp

//based on https://github.com/prakhar1989/type-inference (with corrections and extensions)
class Infer {

    //annotated expr
    sealed class AExpr(val t: Type) {

        object Unit: AExpr(Type.Unit) {
            override fun toString(): String = "()"
        }

        class Sequence(val first: AExpr, val second: AExpr, t: Type): AExpr(t) {
            override fun toString(): String = "{$first; $second}:: $t"
        }

        class Natural(val lit: Long): AExpr(Type.Int) {
            override fun toString(): String = "$lit"
        }

        class Unary(val operator: String, val operand: AExpr, t: Type): AExpr(t) {
            override fun toString(): String = "{$operator $operand}:: $t"
        }

        class Binary(val lhs: AExpr, val op: String, val rhs: AExpr, t: Type): AExpr(t) {
            override fun toString(): String = "{$lhs $op $rhs}:: $t"
        }

        class Ref(val id: String, t: Type): AExpr(t) {
            override fun toString(): String = "$id:: $t"
        }

        class Let(val id: String, val value: AExpr, val body: AExpr, t: Type): AExpr(t) {
            override fun toString(): String = "{let $id = $value; $body}:: $t"
        }

        class Fun(val params: List<String>, val body: AExpr, t: Type): AExpr(t) {
            override fun toString(): String = "{(${params.toString(", ")}): $body}:: $t"
        }

        class CFun(val id: String, t: Type): AExpr(t) {
            override fun toString(): String = "{cfun $id}:: $t"
        }

        class Apply(val fn: AExpr, val args: List<AExpr>, t: Type): AExpr(t) {
            override fun toString(): String = "{$fn}(${args.toString(", ")}):: $t"
        }

    }

    companion object {

        fun exprType(e: Expr): Type {
            return when (e) {
                is Expr.Unit -> Type.Unit
                is Expr.Natural -> Type.Int
                is Expr.Fun -> {
                    val bodyType = e.type ?: exprType(e.body)
                    Type.Fun(
                        e.params.map { p -> if (p.type == null) Type.newUnknown() else p.type },
                        bodyType
                    )
                }
                is Expr.CFun -> e.sig
                is Expr.Sequence -> exprType(e.second)
                is Expr.Let ->
                    exprType(e.body)
                is Expr.Binary -> Type.Int
                is Expr.Unary -> Type.Int
                is Expr.While -> Type.Unit
                is Expr.Break -> Type.Unit
                is Expr.Assign -> exprType(e.value)
                is Expr.If -> exprType(e.csq)
                is Expr.Cond -> exprType(e.csq)
                is Expr.Apply -> Type.newUnknown()
                is Expr.Ref -> {
                    val def = e.accept(GetDefinitionVisitor())
                    when (def) {
                        null -> throw RuntimeException("$e not defined")
                        is Definition.Param -> {
                            if (def.node.typeInfo == null)
                                def.node.typeInfo = Type.newUnknown()
                            def.node.typeInfo!!
                        }
                        is Definition.Let -> {
                            if (def.node.typeInfo == null) {
                                def.node.typeInfo = exprType(def.node.value)
                            }
                            def.node.typeInfo!!
                        }
                    }
                }
            }
        }

        fun functionReturnType(e: Expr): Type {
            return when (e) {
                is Expr.Fun -> exprType(e.body)
                is Expr.Ref -> {
                    val def = e.accept(GetDefinitionVisitor())
                    when (def) {
                        null -> throw RuntimeException("$e not defined")
                        is Definition.Param -> {
                            if (def.node.typeInfo == null)
                                def.node.typeInfo = Type.newUnknown()
                            def.node.typeInfo!!
                        }
                        is Definition.Let -> {
                            if (def.node.typeInfo == null) {
                                def.node.typeInfo = exprType(def.node.value)
                            }
                            def.node.typeInfo!!
                        }
                    }
                }
                else -> Type.newUnknown()
            }
        }

        //using the "env" built into Exprs using GetDef
        fun annotateExpr(e: Expr): AExpr =
            when (e) {
                is Expr.Unit -> AExpr.Unit
                is Expr.Sequence -> AExpr.Sequence(annotateExpr(e.first), annotateExpr(e.second), Type.newUnknown())
                is Expr.Natural -> AExpr.Natural(e.value)
                is Expr.Unary -> AExpr.Unary(e.operator, annotateExpr(e.operand), Type.newUnknown())
                is Expr.Binary -> AExpr.Binary(annotateExpr(e.lhs), e.operator, annotateExpr(e.rhs), Type.newUnknown())
                is Expr.Ref -> {
                    val def = e.accept(GetDefinitionVisitor())
                    when (def) {
                        null -> throw RuntimeException("$e not defined")
                        is Definition.Param -> {
                            if (def.node.typeInfo == null)
                                def.node.typeInfo = Type.newUnknown()
                            AExpr.Ref(e.id, def.node.typeInfo!!)
                        }
                        is Definition.Let -> {
                            if (def.node.typeInfo == null) {
                                def.node.typeInfo = exprType(def.node.value)
                            }
                            AExpr.Ref(e.id, def.node.typeInfo!!)
                        }
                    }
                }
                is Expr.Apply ->
                    AExpr.Apply(
                        annotateExpr(e.fn),
                        e.args.map { a -> annotateExpr(a) },
                        Type.newUnknown()
                    )
                is Expr.Let -> {
                    val aBody = annotateExpr(e.body)
                    val aValue = annotateExpr(e.value)
                    AExpr.Let(e.id, aValue, aBody, aBody.t)
                }
                is Expr.Fun -> {
                    val aBody = annotateExpr(e.body)
                    val paramTypes = ArrayList<Type>()
                    e.params.forEach { p ->
                        if (p.typeInfo == null) {
                            p.typeInfo = Type.newUnknown()
                        }
                        //otherwise (p was referenced in e.body) use existing
                        paramTypes.add(p.typeInfo!!)
                    }
                    AExpr.Fun(e.params.map { p -> p.id }, aBody, Type.Fun(paramTypes, aBody.t))
                }
                is Expr.CFun -> AExpr.CFun(e.id, e.sig)
                else -> TODO("not implemented")
            }

        //a strict equality on t1, t2
        data class Constraint(val t1: Type, val t2: Type) {
            override fun toString(): String =
                "($t1; $t2)"
        }

        fun collectArgs(lae: List<AExpr>): Sequence<Constraint> =
            lae.map { a -> collect(a) }.foldRight(emptySequence()){ acc, seq -> acc + seq }

        //takes an annotated expr and returns a list of constraints
        fun collect(ae: AExpr): Sequence<Constraint> =
            when (ae) {
                is AExpr.Unit -> emptySequence()
                is AExpr.Natural -> emptySequence()
                //collect first and second, and second.t must equal ae.t
                is AExpr.Sequence -> collect(ae.first) + collect(ae.second) + sequenceOf(Constraint(ae.t, ae.second.t))
                //constraints of operand plus operand t must be int type and ae t must be int type
                is AExpr.Unary ->
                    collect(ae.operand) + sequenceOf(Constraint(ae.operand.t, Type.Int), Constraint(ae.t, Type.Int))
                //constraints of lhs, rhs plus lhs, rhs t must be int type and ae t must be int type
                is AExpr.Binary ->
                    collect(ae.lhs) +
                    collect(ae.rhs) +
                    sequenceOf(Constraint(ae.lhs.t, Type.Int), Constraint(ae.rhs.t, Type.Int), Constraint(ae.t, Type.Int))
                //ref expr gives nothing
                is AExpr.Ref ->
                    emptySequence()
                //fun expr must have fun type, then add constraints from body, and that ae t is ae.body.t
                is AExpr.Fun -> {
                    when (ae.t) {
                        is Type.Fun -> collect(ae.body) +
                            sequenceOf(Constraint(ae.t, Type.Fun(ae.t.paramTypes, ae.body.t)))
                        else -> throw RuntimeException("not possible")
                    }
                }
                is AExpr.Let -> {
                    collect(ae.value) +
                    collect(ae.body) +
                    sequenceOf(Constraint(ae.t, ae.body.t))
                }
                //cfun doesn't require constraints because the type must be declared
                is AExpr.CFun -> emptySequence()
                is AExpr.Apply -> {
                    when (ae.fn.t) {
                        is Type.Fun -> {
                            if (ae.fn.t.paramTypes.size != ae.args.size)
                                throw RuntimeException("incorrect number of arguments")
                            //fn constraints plus arg constraints plus ae.t is ae fn return type
                            //plus ae fn param types match ae arg types
                            collect(ae.fn) + collectArgs(ae.args) +
                                sequenceOf(Constraint(ae.t, ae.fn.t.returnType)) +
                                ae.fn.t.paramTypes.zip(ae.args).map { p -> Constraint(p.first, p.second.t) }
                        }
                        is Type.Unknown ->
                            collect(ae.fn) + collectArgs(ae.args) +
                            sequenceOf(Constraint(ae.fn.t, Type.Fun(ae.args.map { a -> a.t }, ae.t)))
                        else -> throw RuntimeException("incorrect function application")
                    }
                }
            }

        fun substitute(u: Type, x: String, t: Type): Type =
            when (t) {
                is Type.Unit -> t
                is Type.Int -> t
                is Type.Unknown -> if (t.id == x) u else t
                is Type.Fun -> Type.Fun(t.paramTypes.map { p -> substitute(u, x, p) }, substitute(u, x, t.returnType))
                is Type.Error -> throw RuntimeException(t.toString())
            }

        data class Substitution(val x: String, val u: Type)

        fun apply(subs: Sequence<Substitution>, t: Type): Type =
            subs.toList().foldRight(t){ sub, t -> substitute(sub.u, sub.x, t) }

//        let rec unify (constraints: (primitiveType * primitiveType) list) : substitutions =
//          match constraints with
//              | [] -> []
//              | (x, y) :: xs ->
//                  (* generate substitutions of the rest of the list *)
//                  let t2 = unify xs in
//                  (* resolve the LHS and RHS of the constraints from the previous substitutions *)
//                  let t1 = unify_one (apply t2 x) (apply t2 y) in
//                  t1 @ t2

        fun unify(constraints: Sequence<Constraint>): Sequence<Substitution> {
            val first = constraints.firstOrNull()
            return if (first == null) {
                emptySequence()
            } else {
                val t2 = unify(constraints.drop(1))
                val t1 = unifyOne(apply(t2, first.t1), apply(t2, first.t2))
                t1 + t2
            }
        }

//        and unify_one (t1: primitiveType) (t2: primitiveType) : substitutions =
//          match t1, t2 with
//          | TNum, TNum | TBool, TBool -> []
//          | T(x), z | z, T(x) -> [(x, z)]
//
//          (* This case is particularly useful when you are calling a function that returns a function *)
//          | TFun(a, b), TFun(x, y) -> unify [(a, x); (b, y)]
//          | _ -> raise (failwith "mismatched types")

        fun unifyOne(t1: Type, t2: Type): Sequence<Substitution> {
            return when {
                t1 is Type.Unit && t2 is Type.Unit -> emptySequence()
                t1 is Type.Int && t2 is Type.Int -> emptySequence()
                t1 is Type.Unknown -> sequenceOf(Substitution(t1.id, t2))
                t2 is Type.Unknown -> sequenceOf(Substitution(t2.id, t1))
                //both fun types then unify argument types and return type
                t1 is Type.Fun && t2 is Type.Fun ->
                    unify(
                        t1.paramTypes.zip(t2.paramTypes).map { p -> Constraint(p.first, p.second) }.asSequence() +
                            sequenceOf(Constraint(t1.returnType, t2.returnType))
                    )
                else -> throw RuntimeException("mismatched types $t1 and $t2")
            }
        }

//        let rec apply_expr (subs: substitutions) (ae: aexpr): aexpr =
//          match ae with
//              | ABoolLit(b, t) -> ABoolLit(b, apply subs t)
//              | ANumLit(n, t) -> ANumLit(n, apply subs t)
//              | AVal(s, t) -> AVal(s, apply subs t)
//              | ABinop(e1, op, e2, t) -> ABinop(apply_expr subs e1, op, apply_expr subs e2, apply subs t)
//              | AFun(id, e, t) -> AFun(id, apply_expr subs e, apply subs t)
//              | AApp(fn, arg, t) -> AApp(apply_expr subs fn, apply_expr subs arg, apply subs t)
//        ;;

        fun applyExpr(subs: Sequence<Substitution>, ae: AExpr): AExpr =
            when (ae) {
                is AExpr.Unit -> AExpr.Unit
                is AExpr.Natural -> AExpr.Natural(ae.lit)
                is AExpr.Sequence -> AExpr.Sequence(applyExpr(subs, ae.first), applyExpr(subs, ae.second), apply(subs, ae.t))
                is AExpr.Unary -> AExpr.Unary(ae.operator, applyExpr(subs, ae.operand), apply(subs, ae.t))
                is AExpr.Binary -> AExpr.Binary(applyExpr(subs, ae.lhs), ae.op, applyExpr(subs, ae.rhs), apply(subs, ae.t))
                is AExpr.Ref -> AExpr.Ref(ae.id, apply(subs, ae.t))
                is AExpr.Let -> AExpr.Let(ae.id, applyExpr(subs, ae.value), applyExpr(subs, ae.body), apply(subs, ae.t))
                is AExpr.Fun -> AExpr.Fun(ae.params, applyExpr(subs, ae.body), apply(subs, ae.t))
                is AExpr.CFun -> AExpr.CFun(ae.id, apply(subs, ae.t))
                is AExpr.Apply -> AExpr.Apply(applyExpr(subs, ae.fn), ae.args.map { a -> applyExpr(subs, a) }, apply(subs, ae.t))
            }

//        let infer (env: environment) (e: expr) : aexpr =
//          let annotated_expr = annotate_expr e env in
//          let constraints = collect_expr annotated_expr in
//          let subs = unify constraints in
//          (* reset the type counter after completing inference *)
//          type_variable := (Char.code 'a');
//          apply_expr subs annotated_expr
//        ;;

        //env included in e
        fun infer(e: Expr): AExpr {
            val ae = annotateExpr(e)
            println(ae)
            val constraints = collect(ae)
            println(constraints.toList().toString(" "))
            val subs = unify(constraints)
            return applyExpr(subs, ae)
        }
            

    }

}

