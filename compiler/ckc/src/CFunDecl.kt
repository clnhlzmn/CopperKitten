

class CFunDecl : BaseASTNode() {
    override fun <T> accept(visitor: ASTVisitor<T>): T {
        return visitor.visit(this)
    }
}

