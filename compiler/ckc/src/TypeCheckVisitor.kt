import java.util.*

data class Error(val what: String)

class TypeCheckVisitor : ASTVisitor<List<Error>> {

    private val noError = ArrayList<Error>()

    private val locals = ArrayDeque<Pair<String, ASTNode>>()

    override fun visit(s: BlockStatement): List<Error> {
        for (statement in s.statements) {
            val err = statement.accept(this)
            if (err.isNotEmpty())
                return err
        }
        return noError
    }

    override fun visit(s: LetStatement): List<Error> {
        locals.add(Pair(s.id, s))
        return noError
    }

    override fun visit(s: ForStatement): List<Error> {
        if (s.init != null) {
            val ret = s.init.accept(this)
            if (ret.isNotEmpty())
                return ret
        }
        //TODO: rest
        return noError
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

