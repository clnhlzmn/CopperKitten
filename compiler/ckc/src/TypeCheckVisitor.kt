import java.util.*

data class Error(val what: String)

class TypeCheckVisitor : ASTVisitor<List<Error>> {

    var frame = StackFrame(null)

    override fun visit(s: BlockStatement): List<Error> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(s: LetStatement): List<Error> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(s: ForStatement): List<Error> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(s: IfStatement): List<Error> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(s: ReturnStatement): List<Error> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(s: ExprStatement): List<Error> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: Expr): List<Error> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

