

interface Scope : ASTNode {
    //returns the integer index of the variable identified by id
    //within the stack frame, or -1 if the variable doesn't exist
    fun lookup(id: String): Int
}

class FunctionScope() : Scope {

    override fun lookup(id: String): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

}

