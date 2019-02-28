

class ScopeVisitor : ASTVisitor<Unit> {

    var localScope = ArrayList<Pair<String, ASTNode>>()
    var nonLocalScope = ArrayList<String>()

    override fun visit(e: UnitExpr) {
        //nothing
    }

    override fun visit(e: SequenceExpr) {
        e.expr.accept(this)
        e.next?.accept(this)
    }

    override fun visit(e: NaturalExpr) {
        //nothing
    }

    override fun visit(e: RefExpr) {
        val found = localScope.findLast { p -> p.first == e.id }
        if (found == null) {
            nonLocalScope.add(e.id)
        }
    }

    override fun visit(e: ApplyExpr) {
        e.target.accept(this)
        e.args.forEach{ a -> a.accept(this) }
    }

    override fun visit(e: UnaryExpr) {
        e.expr.accept(this)
    }

    override fun visit(e: BinaryExpr) {
        e.lhs.accept(this)
        e.rhs.accept(this)
    }

    override fun visit(e: CondExpr) {
        e.cond.accept(this)
        e.csq.accept(this)
        e.alt.accept(this)
    }

    override fun visit(e: AssignExpr) {
        e.target.accept(this)
        e.value.accept(this)
    }

    override fun visit(e: FunExpr) {
        //TODO: what to do here?
        //TODO: save current scope
        //TODO: make new scope
        //TODO: visit body
        //TODO: save that function's scope somewhere
        //TODO: treat non locals from the function body as refExprs here
    }

    override fun visit(e: LetExpr) {
        //TODO: is this right?
        localScope.add(Pair(e.id, e.value))
        e.body?.accept(this)
    }

    override fun visit(e: IfExpr) {
        e.cond.accept(this)
        e.csq.accept(this)
        e.alt?.accept(this)
    }

    override fun visit(e: WhileExpr) {
        e.cond.accept(this)
        e.body.accept(this)
    }

    override fun visit(e: BreakExpr) {
        e.value?.accept(this)
    }

}