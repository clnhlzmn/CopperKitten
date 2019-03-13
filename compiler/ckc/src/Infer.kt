import java.lang.RuntimeException

//based on https://github.com/prakhar1989/type-inference
class Infer {

    //annotated expr
    open class AExpr

    data class AUnitExpr(val t: Type): AExpr()

    data class ANaturalExpr(val lit: Long, val t: Type): AExpr()

    data class ARefExpr(val id: String, val t: Type): AExpr()

    data class AApplyExpr(val target: AExpr, val args: List<AExpr>, val t: Type): AExpr()

    data class AFunExpr(val params: List<String>, val body: AExpr, val t: Type): AExpr()

    data class Constraint(val t1: Type, val t2: Type)

    companion object {

        var i = 0

        fun newType(): UnknownType =
            UnknownType("T${i++}")

        //using the "env" built into Exprs using GetDef
        fun annotateExpr(e: Expr): AExpr {
            return when (e) {
                is NaturalExpr -> ANaturalExpr(e.value, IntType)
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
                    val ae = annotateExpr(e.body)
                    val paramTypes = ArrayList<Type>()
                    e.params.forEach { p ->
                        //if p has no type info then generate it
                        if (p.typeInfo == null) {
                            p.typeInfo = newType()
                        }
                        //otherwise use existing
                        paramTypes.add(p.typeInfo!!)
                    }
                    AFunExpr(e.params.map{p->p.id}, ae, FunType(paramTypes, newType()))
                }
                else -> TODO("not implemented")
            }
        }
    }

}

