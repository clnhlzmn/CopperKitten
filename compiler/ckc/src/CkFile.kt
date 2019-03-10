
class CkFile : BaseASTNode() {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    //list of top level definitions
    val defs = ArrayList<ASTNode>()

    //expr in the file
    val expr: Expr? = null

}

