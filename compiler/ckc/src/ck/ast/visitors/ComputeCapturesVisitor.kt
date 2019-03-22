package ck.ast.visitors

import ck.ast.node.Expr

class ComputeCapturesVisitor : BaseASTVisitor<Unit>() {

    var currentFun: Expr.Fun? = null

    override fun visit(e: Expr.Unit) {
        //nothing
    }

    override fun visit(e: Expr.Sequence) {
        e.first.accept(this)
        e.second.accept(this)
    }

    override fun visit(e: Expr.Natural) {
        //nothing
    }

    override fun visit(e: Expr.Ref) {
        val def = e.accept(GetDefinitionVisitor())
        //create capture expr
        val capture = Expr.Ref(e.id, e.t)
        capture.enclosingScope = currentFun?.enclosingScope
        when {
            //if def is non isLocal then add capture to currentFun
            def is Definition && !def.local -> {
                //currentFun can't be null here, right?
                //as long as GetTypeVisitor returned non ErrorType on the program
                currentFun!!.captures.add(capture)
            }
        }
    }

    override fun visit(e: Expr.Apply) {
        e.args.forEach { a -> a.accept(this) }
        e.fn.accept(this)
    }

    override fun visit(e: Expr.Unary) {
        e.operand.accept(this)
    }

    override fun visit(e: Expr.Binary) {
        e.lhs.accept(this)
        e.rhs.accept(this)
    }

    override fun visit(e: Expr.Cond) {
        e.cond.accept(this)
        e.csq.accept(this)
        e.alt.accept(this)
    }

    override fun visit(e: Expr.Assign) {
        e.target.accept(this)
        e.value.accept(this)
    }

    override fun visit(e: Expr.Fun) {
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

    override fun visit(e: Expr.CFun) {
        //nothing
    }

    override fun visit(e: Expr.Let) {
        e.value.accept(this)
        e.body.accept(this)
    }

    override fun visit(e: Expr.If) {
        e.cond.accept(this)
        e.csq.accept(this)
        e.alt?.accept(this)
    }

    override fun visit(e: Expr.While) {
        e.cond.accept(this)
        e.body.accept(this)
    }

    override fun visit(e: Expr.Break) {
        e.value?.accept(this)
    }

}

