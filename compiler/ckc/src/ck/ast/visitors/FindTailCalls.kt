package ck.ast.visitors

import ck.ast.node.CkFile
import ck.ast.node.Expr

class FindTailCalls: BaseASTVisitor<Unit>() {

    var tailPosition = false

    override fun visit(e: Expr.LetRec) {
        val tp = tailPosition
        tailPosition = false
        e.bindings.forEach { b -> b.second.accept(this) }
        tailPosition = tp
        e.body.accept(this)
    }

    override fun visit(f: CkFile) {
        tailPosition = false
        f.expr.accept(this)
    }

    override fun visit(e: Expr.Unit) {
        //nothing
    }

    override fun visit(e: Expr.Sequence) {
        val tp = tailPosition
        tailPosition = false
        e.first.accept(this)
        tailPosition = tp
        e.second.accept(this)
    }

    override fun visit(e: Expr.Natural) {
        //nothing
    }

    override fun visit(e: Expr.Ref) {
        //nothing
    }

    override fun visit(e: Expr.Apply) {
        e.isTailCall = tailPosition
        tailPosition = false
        e.args.forEach { a -> a.accept(this) }
        e.fn.accept(this)
        tailPosition = e.isTailCall
    }

    override fun visit(e: Expr.Unary) {
        val tp = tailPosition
        tailPosition = false
        e.operand.accept(this)
        tailPosition = tp
    }

    override fun visit(e: Expr.Binary) {
        val tp = tailPosition
        tailPosition = false
        e.lhs.accept(this)
        e.rhs.accept(this)
        tailPosition = tp
    }

    override fun visit(e: Expr.Cond) {
        val tp = tailPosition
        tailPosition = false
        e.cond.accept(this)
        tailPosition = tp
        e.csq.accept(this)
        e.alt.accept(this)
    }

    override fun visit(e: Expr.Assign) {
        TODO("not implemented")
    }

    override fun visit(e: Expr.Fun) {
        val tp = tailPosition
        tailPosition = true
        e.body.accept(this)
        tailPosition = tp
    }

    override fun visit(e: Expr.CFun) {
        //nothing
    }

    override fun visit(e: Expr.Let) {
        val tp = tailPosition
        tailPosition = false
        e.value.accept(this)
        tailPosition = tp
        e.body.accept(this)
    }

    override fun visit(e: Expr.If) {
        val tp = tailPosition
        tailPosition = false
        e.cond.accept(this)
        tailPosition = tp
        e.csq.accept(this)
        e.alt?.accept(this)
    }

    override fun visit(e: Expr.While) {
        TODO("not implemented")
    }

    override fun visit(e: Expr.Break) {
        TODO("not implemented")
    }

}