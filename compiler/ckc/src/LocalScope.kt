
class LocalScope(
    val id: String,
    val value: ASTNode,
    val child: ASTNode
) : ASTNode {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)


}


