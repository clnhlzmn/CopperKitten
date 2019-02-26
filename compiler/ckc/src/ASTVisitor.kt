

interface ASTVisitor<T> {

    fun visit(s: BlockStatement): T
    fun visit(s: LetStatement): T
    fun visit(s: ForStatement): T
    fun visit(s: IfStatement): T
    fun visit(s: ReturnStatement): T
    fun visit(s: ExprStatement): T

    fun visit(e: NaturalExpr): T
    fun visit(e: RefExpr): T
    fun visit(e: ApplyExpr): T
    fun visit(e: UnaryExpr): T
    fun visit(e: BinaryExpr): T
    fun visit(e: CondExpr): T
    fun visit(e: AssignExpr): T
    fun visit(e: FunExpr): T
    fun visit(e: LetExpr): T
}