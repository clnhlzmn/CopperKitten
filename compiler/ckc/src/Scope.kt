import kotlin.math.max

class Scope {

    //keep track of local variables
    private val locals = ArrayList<Pair<String, ASTNode>>()

    //maximum number of locals seen in this frame
    private var maxLocals = 0

    //arguments to this function
    private val arguments = ArrayList<Pair<String, ASTNode>>()

    //captures for this function
    private val captures = ArrayList<Pair<String, ASTNode>>()

    fun lookupLocal(id: String): Int =
        locals.indexOfLast { p -> p.first == id }

    fun lookupArgument(id: String): Int =
        arguments.indexOfFirst { p -> p.first == id }

    fun lookupCapture(id: String): Int =
        captures.indexOfFirst { p -> p.first == id }

    fun pushLocal(id: String, node: ASTNode) {
        locals.add(Pair(id, node))
        maxLocals = max(maxLocals, locals.size)
    }

    fun popLocal() =
        locals.removeAt(locals.size - 1)

    fun pushArgument(id: String, node: ASTNode) =
        arguments.add(Pair(id, node))

    fun popArgument() =
        arguments.removeAt(arguments.size - 1)

    fun pushCapture(id: String, node: ASTNode) =
        captures.add(Pair(id, node))

    fun popCapture() =
        captures.removeAt(captures.size - 1)

    fun localsSize(): Int =
        locals.size

    fun localsMaxSize(): Int =
        maxLocals

    fun argumentsSize(): Int =
        arguments.size

    fun capturesSize(): Int =
        captures.size

}

