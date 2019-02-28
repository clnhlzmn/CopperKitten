
interface ASTNode {
    fun<T> accept(visitor: ASTVisitor<T>): T
}

open class BaseASTNode : ASTNode {

    override fun <T> accept(visitor: ASTVisitor<T>): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

