import java.util.*
import kotlin.math.max

class StackFrame(val statements: List<Statement>) : ASTNode {

    //keep track of local variables
    private val locals: Deque<Pair<String, ASTNode>> = ArrayDeque()

    //maximum number of locals seen in this frame
    private var maxLocals = 0

    //arguments to this function
    private val arguments: Deque<Pair<String, ASTNode>> = ArrayDeque()

    //captures for this function
    private val captures: Deque<Pair<String, ASTNode>> = ArrayDeque()

    fun lookupLocal(id: String): Int =
        locals.indexOfLast { p -> p.first == id }

    fun lookupArgument(id: String): Int =
        arguments.indexOfFirst { p -> p.first == id }

    fun lookupCapture(id: String): Int =
        captures.indexOfFirst { p -> p.first == id }

    fun pushLocal(id: String, node: ASTNode) {
        locals.addLast(Pair(id, node))
        maxLocals = max(maxLocals, locals.size)
    }

    fun popLocal() =
        locals.removeLast()

    fun pushArgument(id: String, node: ASTNode) =
        arguments.addLast(Pair(id, node))

    fun popArgument() =
        arguments.removeLast()

    fun pushCapture(id: String, node: ASTNode) =
        captures.addLast(Pair(id, node))

    fun popCapture() =
        captures.removeLast()

    fun localsSize(): Int =
        locals.size

    fun localsMaxSize(): Int =
        maxLocals

    fun argumentsSize(): Int =
        arguments.size

    fun capturesSize(): Int =
        captures.size

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

}

