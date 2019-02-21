

interface ASTVisitor<T> {
    fun visit(s: BlockStatement): T
    fun visit(s: LetStatement): T
    fun visit(s: ForStatement): T
    fun visit(s: IfStatement): T
    fun visit(s: ReturnStatement): T
    fun visit(s: ExprStatement): T
}