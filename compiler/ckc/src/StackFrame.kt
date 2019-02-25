import java.util.*
import kotlin.math.max

//represents a stack frame that has a parent frame and a list of statements to execute in it's context
//it also has local variables, arguments, and captures
class StackFrame(val parent: StackFrame?) {

    //keep track of local variables
    private val locals = ArrayDeque<Pair<String, ASTNode>>()

    //maximum number of locals seen in this frame
    private var maxLocals = 0

    //arguments to this function
    private val arguments = ArrayDeque<Pair<String, ASTNode>>()

    //captures for this function
    private val captures = ArrayDeque<Pair<String, ASTNode>>()

    fun lookupType(id: String): Type? {
        TODO("not implemented")
    }

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

}

