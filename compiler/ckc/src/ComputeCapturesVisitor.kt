

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
        //create capture expr
        val capture = RefExpr(e.id)
        capture.enclosingScope = currentFun?.enclosingScope
        when (def) {
            //if def is non local then add capture to currentFun
            is NonLocalDef -> {
                val cf = currentFun
                when (cf) {
                    null -> TODO("impossible, as long as GetTypeVisitor returned non ErrorType on the program")
                    else -> cf.captures.add(e)
                }
            }
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
        //save current fun
        val lastFun = currentFun
        //set current fun to e
        currentFun = e
        //compute captures for e
        e.body.accept(this)
        //reset current fun
        currentFun = lastFun
        //do a pass on any captures from e, to add to current fun if necessary
        e.captures.forEach { c -> c.accept(this) }
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

