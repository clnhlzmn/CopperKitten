
//based on https://github.com/prakhar1989/type-inference
class Infer {

    //annotated expr
    open class AExpr

    data class AUnitExpr(val t: Type): AExpr()

    data class ANatExpr(val lit: Long, val t: Type): AExpr()

    data class AApplyExpr(val target: AExpr, val args: List<AExpr>, val t: Type)

    data class AFunExpr(val params: List<String>, val body: AExpr, val t: Type)

    data class Constraint(val t1: Type, val t2: Type)

}

