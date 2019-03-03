
interface ASTNode {
    fun<T> accept(visitor: ASTVisitor<T>): T
}

