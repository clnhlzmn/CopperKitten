import java.util.*

data class StackFrame(
    val locals: ArrayDeque<Pair<String, ASTNode>>,
    val captures: ArrayDeque<Pair<String, ASTNode>>,
    val arguments: ArrayDeque<Pair<String, ASTNode>>
) : ASTNode {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

}

