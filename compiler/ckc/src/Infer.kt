import com.sun.xml.internal.bind.v2.model.annotation.RuntimeInlineAnnotationReader
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const
import java.lang.RuntimeException

//based on https://github.com/prakhar1989/type-inference
class Infer {

    //annotated expr
    sealed class AExpr(val t: Type) {

        class Unit(t: Type) : AExpr(t)

        class Natural(val lit: Long, t: Type) : AExpr(t)

        class Unary(val operator: String, val operand: AExpr, t: Type) : AExpr(t)

        class Binary(val lhs: AExpr, val op: String, val rhs: AExpr, t: Type) : AExpr(t)

        class Ref(val id: String, t: Type) : AExpr(t)

        class Fun(val params: List<String>, val body: AExpr, t: Type) : AExpr(t)

        class CFun(val id: String, t: Type) : AExpr(t)

        class Apply(val target: AExpr, val args: List<AExpr>, t: Type) : AExpr(t)

    }

    companion object {

        var i = 0

        fun newType(): UnknownType =
            UnknownType("T${i++}")

        //using the "env" built into Exprs using GetDef
        fun annotateExpr(e: Expr): AExpr =
            when (e) {
                is Expr.Unit -> AExpr.Unit(UnitType)
                is Expr.Natural -> AExpr.Natural(e.value, IntType)
                is Expr.Unary -> AExpr.Unary(e.operator, annotateExpr(e.operand), IntType)
                is Expr.Binary -> AExpr.Binary(annotateExpr(e.lhs), e.operator, annotateExpr(e.rhs), IntType)
                is Expr.Ref -> {
                    val def = e.accept(GetDefinitionVisitor())
                    when (def) {
                        null -> throw RuntimeException("$e not defined")
                        is Definition.Param -> {
                            if (def.node.typeInfo == null)
                                def.node.typeInfo = newType()
                            AExpr.Ref(e.id, def.node.typeInfo!!)
                        }
                        is Definition.Let -> {
                            if (def.node.typeInfo == null)
                                def.node.typeInfo = newType()
                            AExpr.Ref(e.id, def.node.typeInfo!!)
                        }
                    }
                }
                is Expr.Apply -> AExpr.Apply(
                    annotateExpr(e.target),
                    e.args.map { a -> annotateExpr(a) },
                    newType()
                )
                is Expr.Fun -> {
                    val abody = annotateExpr(e.body)
                    val paramTypes = ArrayList<Type>()
                    e.params.forEach { p ->
                        //if p has no type info then generate it?
                        //I think the reference source is just failing in this case
                        //if p doesn't have typeInfo then that means it's not referenced in e.body
                        //in that case this newType info will not be used?
                        if (p.typeInfo == null) {
                            p.typeInfo = newType()
                        }
                        //otherwise (p was referenced in e.body) use existing
                        paramTypes.add(p.typeInfo!!)
                    }
                    AExpr.Fun(e.params.map{p->p.id}, abody, FunType(paramTypes, newType()))
                }
                is Expr.CFun -> AExpr.CFun(e.id, e.sig)
                else -> TODO("not implemented")
            }

        //a strict equality on t1, t2
        data class Constraint(val t1: Type, val t2: Type)

        fun collectArgs(lae: List<AExpr>): Sequence<Constraint> =
            lae.map { a -> collect(a) }.fold(emptySequence()){ acc, seq -> acc + seq }

        //takes an annotated expr and returns a list of constraints
        fun collect(ae: AExpr): Sequence<Constraint> =
            when (ae) {
                is AExpr.Unit -> emptySequence()
                is AExpr.Natural -> emptySequence()
                //constraints of operand plus operand t must be int type and ae t must be int type
                is AExpr.Unary ->
                    collect(ae.operand) + sequenceOf(Constraint(ae.operand.t, IntType), Constraint(ae.t, IntType))
                //constraints of lhs, rhs plus lhs, rhs t must be int type and ae t must be int type
                is AExpr.Binary ->
                    collect(ae.lhs) +
                    collect(ae.rhs) +
                    sequenceOf(Constraint(ae.lhs.t, IntType), Constraint(ae.rhs.t, IntType), Constraint(ae.t, IntType))
                //ref expr gives nothing
                is AExpr.Ref -> emptySequence()
                //fun expr must have fun type, then add constraints from body, and that ae t is ae.t.returnType
                is AExpr.Fun -> {
                    when (ae.t) {
                        is FunType -> collect(ae.body) + sequenceOf(Constraint(ae.t, ae.t.returnType))
                        else -> throw RuntimeException("not a function")
                    }
                }
                is AExpr.CFun -> {
                    when (ae.t) {
                        is FunType -> sequenceOf(Constraint(ae.t, ae.t.returnType))
                        else -> throw RuntimeException("not a function")
                    }
                }
                is AExpr.Apply -> {
                    when (ae.target.t) {
                        is FunType -> {
                            if (ae.target.t.paramTypes.size != ae.args.size)
                                throw RuntimeException("incorrect number of arguments")
                            //target constraints plus arg constraints plus ae.t is ae target return type
                            //plus ae target param types match ae arg types
                            collect(ae.target) + collectArgs(ae.args) +
                                sequenceOf(Constraint(ae.t, ae.target.t.returnType)) +
                                ae.target.t.paramTypes.zip(ae.args).map { p -> Constraint(p.first, p.second.t) }
                        }
                        is UnknownType -> collect(ae.target) + collectArgs(ae.args) +
                            sequenceOf(Constraint(ae.target.t, FunType(ae.args.map { a -> a.t }, ae.t)))
                        else -> throw RuntimeException("incorrect function application")
                    }
                }
            }

        fun substitute(u: Type, x: String, t: Type): Type =
            when (t) {
                is UnitType -> t
                is IntType -> t
                is UnknownType -> if (t.id == x) u else t
                is FunType -> FunType(t.paramTypes.map { p -> substitute(u, x, p) }, substitute(u, x, t.returnType))
                is ErrorType -> throw RuntimeException(t.toString())
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
            if (first == null) {
                return emptySequence()
            } else {
                val t2 = unify(constraints.drop(1))
                val t1 = unifyOne(apply(t2, first.t1), apply(t2, first.t2))
                return t1 + t2
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
                t1 is UnitType && t2 is UnitType -> emptySequence()
                t1 is IntType && t2 is IntType -> emptySequence()
                t1 is UnknownType -> sequenceOf(Substitution(t1.id, t2))
                t2 is UnknownType -> sequenceOf(Substitution(t2.id, t1))
                //both fun types then unify argument types and return type
                t1 is FunType && t2 is FunType ->
                    unify(
                        t1.paramTypes.zip(t2.paramTypes).map { p -> Constraint(p.first, p.second) }.asSequence() +
                            sequenceOf(Constraint(t1.returnType, t2.returnType))
                    )
                else -> throw RuntimeException("mismatched types")
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
                is AExpr.Unit -> AExpr.Unit(apply(subs, ae.t))
                is AExpr.Natural -> AExpr.Natural(ae.lit, apply(subs, ae.t))
                is AExpr.Unary -> AExpr.Unary(ae.operator, applyExpr(subs, ae.operand), apply(subs, ae.t))
                is AExpr.Binary -> AExpr.Binary(applyExpr(subs, ae.lhs), ae.op, applyExpr(subs, ae.rhs), apply(subs, ae.t))
                is AExpr.Ref -> AExpr.Ref(ae.id, apply(subs, ae.t))
                is AExpr.Fun -> AExpr.Fun(ae.params, applyExpr(subs, ae.body), apply(subs, ae.t))
                is AExpr.CFun -> AExpr.CFun(ae.id, apply(subs, ae.t))
                is AExpr.Apply -> AExpr.Apply(applyExpr(subs, ae.target), ae.args.map { a -> applyExpr(subs, a) }, apply(subs, ae.t))
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
            val constraints = collect(ae)
            val subs = unify(constraints)
            return applyExpr(subs, ae)
        }
            

    }

}

