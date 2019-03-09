

interface ASTVisitor<T> {
    fun visit(e: UnitExpr): T
    fun visit(e: SequenceExpr): T
    fun visit(e: NaturalExpr): T
    fun visit(e: RefExpr): T
    fun visit(e: ApplyExpr): T
    fun visit(e: UnaryExpr): T
    fun visit(e: BinaryExpr): T
    fun visit(e: CondExpr): T
    fun visit(e: AssignExpr): T
    fun visit(e: FunExpr): T
    fun visit(e: LetExpr): T
    fun visit(e: IfExpr): T
    fun visit(e: WhileExpr): T
    fun visit(e: BreakExpr): T
}