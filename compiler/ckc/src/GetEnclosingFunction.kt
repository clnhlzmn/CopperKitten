

class GetEnclosingFunction : ASTVisitor<FunExpr?> {

    override fun visit(e: UnitExpr): FunExpr? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: SequenceExpr): FunExpr? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: NaturalExpr): FunExpr? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: RefExpr): FunExpr? {
        return e.enclosingScope?.accept(this)
    }

    override fun visit(e: ApplyExpr): FunExpr? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: UnaryExpr): FunExpr? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: BinaryExpr): FunExpr? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: CondExpr): FunExpr? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: AssignExpr): FunExpr? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: FunExpr): FunExpr? {
        return e
    }

    override fun visit(e: LetExpr): FunExpr? {
        return e.enclosingScope?.accept(this)
    }

    override fun visit(e: IfExpr): FunExpr? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: WhileExpr): FunExpr? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: BreakExpr): FunExpr? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}