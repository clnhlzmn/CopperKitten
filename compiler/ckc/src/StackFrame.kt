import java.util.*
import kotlin.math.max

class StackFrame(
    val parent: StackFrame?,
    val context: ASTNode
) : ASTNode {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String {
        return "(StackFrame $context)"
    }

}