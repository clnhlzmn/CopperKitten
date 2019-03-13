import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const
import java.lang.RuntimeException

//based on https://github.com/prakhar1989/type-inference
class Infer {

    //annotated expr
    open class AExpr(val t: Type)

    class AUnitExpr(t: Type): AExpr(t)

    class ANaturalExpr(val lit: Long, t: Type): AExpr(t)

    class AUnaryExpr(val operator: String, val operand: AExpr, t: Type): AExpr(t)

    class ABinaryExpr(val lhs: AExpr, val op: String, val rhs: AExpr, t: Type): AExpr(t)

    class ARefExpr(val id: String, t: Type): AExpr(t)

    class AFunExpr(val params: List<String>, val body: AExpr, t: Type): AExpr(t)

    class ACFunExpr(val id: String, t: FunType): AExpr(t)

    class AApplyExpr(val target: AExpr, val args: List<AExpr>, t: Type): AExpr(t)

    companion object {

        var i = 0

        fun newType(): UnknownType =
            UnknownType("T${i++}")

        //using the "env" built into Exprs using GetDef
        fun annotateExpr(e: Expr): AExpr {
            return when (e) {
                is UnitExpr -> AUnitExpr(UnitType)
                is NaturalExpr -> ANaturalExpr(e.value, IntType)
                is UnaryExpr -> AUnaryExpr(e.operator, annotateExpr(e.operand), IntType)
                is BinaryExpr -> ABinaryExpr(annotateExpr(e.lhs), e.operator, annotateExpr(e.rhs), IntType)
                is RefExpr -> {
                    val def = e.accept(GetDefinitionVisitor())
                    when (def) {
                        null -> throw RuntimeException("$e not defined")
                        is Definition.Param -> {
                            if (def.node.typeInfo == null)
                                def.node.typeInfo = newType()
                            ARefExpr(e.id, def.node.typeInfo!!)
                        }
                        is Definition.Let -> {
                            if (def.node.typeInfo == null)
                                def.node.typeInfo = newType()
                            ARefExpr(e.id, def.node.typeInfo!!)
                        }
                    }
                }
                is ApplyExpr -> AApplyExpr(
                    annotateExpr(e.target),
                    e.args.map { a -> annotateExpr(a) },
                    newType()
                )
                is FunExpr -> {
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
                    AFunExpr(e.params.map{p->p.id}, abody, FunType(paramTypes, newType()))
                }
                is CFunExpr -> ACFunExpr(e.id, e.sig)
                else -> TODO("not implemented")
            }
        }

        //a strict equality on t1, t2
        data class Constraint(val t1: Type, val t2: Type)

        fun collectArgs(lae: List<AExpr>): Sequence<Constraint> {
            return lae.map { a -> collect(a) }.fold(emptySequence()){ acc, seq -> acc + seq }
        }

        //takes an annotated expr and returns a list of constraints
        fun collect(ae: AExpr): Sequence<Constraint> {
            return when (ae) {
                is AUnitExpr -> emptySequence()
                is ANaturalExpr -> emptySequence()
                //constraints of operand plus operand t must be int type and ae t must be int type
                is AUnaryExpr ->
                    collect(ae.operand) + sequenceOf(Constraint(ae.operand.t, IntType), Constraint(ae.t, IntType))
                //constraints of lhs, rhs plus lhs, rhs t must be int type and ae t must be int type
                is ABinaryExpr ->
                    collect(ae.lhs) +
                    collect(ae.rhs) +
                    sequenceOf(Constraint(ae.lhs.t, IntType), Constraint(ae.rhs.t, IntType), Constraint(ae.t, IntType))
                //ref expr gives nothing
                is ARefExpr -> emptySequence()
                //fun expr must have fun type, then add constraints from body, and that ae t is ae.t.returnType
                is AFunExpr -> {
                    when (ae.t) {
                        is FunType -> collect(ae.body) + sequenceOf(Constraint(ae.t, ae.t.returnType))
                        else -> throw RuntimeException("not a function")
                    }
                }
                is ACFunExpr -> {
                    when (ae.t) {
                        is FunType -> sequenceOf(Constraint(ae.t, ae.t.returnType))
                        else -> throw RuntimeException("not a function")
                    }
                }
                is AApplyExpr -> {
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
                else -> throw RuntimeException("not a function")
            }
        }
    }

}

