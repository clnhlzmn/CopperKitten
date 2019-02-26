

class LetStatementVisitor(val properties: ASTProperties) : ASTVisitor<Unit> {

    var locals = HashMap<String, ASTNode>()

    override fun visit(s: BlockStatement) {
        for (statement in s.statements) {
            statement.accept(this)
        }
    }

    override fun visit(s: LetStatement) {
        locals.put(s.id, s.value)
    }

    override fun visit(s: ForStatement) {
        s.init?.accept(this)
        s.statement.accept(this)
    }

    override fun visit(s: IfStatement) {
        s.con.accept(this)
        s.alt?.accept(this)
    }

    override fun visit(s: ReturnStatement) {
        //nothing here
    }

    override fun visit(s: ExprStatement) {
        //nothing here
    }

    override fun visit(e: NaturalExpr) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: RefExpr) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: ApplyExpr) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: UnaryExpr) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: BinaryExpr) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: CondExpr) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: AssignExpr) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: FunExpr) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: LetExpr) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toString(): String {
        return locals.map { kv -> "${kv.key}: ${kv.value}, " }.fold(""){ acc, s -> acc+s }
    }

}

