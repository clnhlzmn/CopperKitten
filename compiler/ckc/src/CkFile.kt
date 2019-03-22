import ck.ast.ASTNode
import ck.ast.ASTVisitor
import ck.ast.BaseASTNode

class CkFile(val defs: List<ASTNode>, val expr: Expr) : BaseASTNode() {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

}

