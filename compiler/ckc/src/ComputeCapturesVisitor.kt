

class ComputeCapturesVisitor : ASTVisitor<Unit> {

    var currentFun: FunExpr? = null

    override fun visit(e: UnitExpr) {
        //nothing
    }

    override fun visit(e: SequenceExpr) {
        e.first.accept(this)
        e.second?.accept(this)
    }

    override fun visit(e: NaturalExpr) {
        //nothing
    }

    override fun visit(e: RefExpr) {
        val def = e.accept(FindDefinitionVisitor())
        when (def) {
            is NonLocalDef -> currentFun?.captures?.add(def.node)
        }
    }

    override fun visit(e: ApplyExpr) {
        e.args.forEach { a -> a.accept(this) }
        e.target.accept(this)
    }

    override fun visit(e: UnaryExpr) {
        e.operand.accept(this)
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

    override fun visit(p: Param) {
        TODO("not implemented")
    }

    override fun visit(e: FunExpr) {
        val lastFun = currentFun
        currentFun = e
        e.body.accept(this)
        currentFun = lastFun
    }

    override fun visit(e: LetExpr) {
        e.value.accept(this)
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

