

class Environment(parent: Environment?) : ASTNode {

    val locals = ArrayList<Pair<String, ASTNode>>()

    val statements = ArrayList<Statement>()

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

}

