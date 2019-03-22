package ck.ast

import CkFile
import Expr

interface ASTVisitor<T> {

    fun visit(f: CkFile): T

    fun visit(e: Expr.Unit): T
    fun visit(e: Expr.Sequence): T
    fun visit(e: Expr.Natural): T
    fun visit(e: Expr.Ref): T
    fun visit(e: Expr.Apply): T
    fun visit(e: Expr.Unary): T
    fun visit(e: Expr.Binary): T
    fun visit(e: Expr.Cond): T
    fun visit(e: Expr.Assign): T
    fun visit(e: Expr.Fun): T
    fun visit(e: Expr.CFun): T
    fun visit(e: Expr.Let): T
    fun visit(e: Expr.If): T
    fun visit(e: Expr.While): T
    fun visit(e: Expr.Break): T
}

open class BaseASTVisitor<T> : ASTVisitor<T> {

    override fun visit(f: CkFile): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: Expr.Unit): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: Expr.Sequence): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: Expr.Natural): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: Expr.Ref): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: Expr.Apply): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: Expr.Unary): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: Expr.Binary): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: Expr.Cond): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: Expr.Assign): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: Expr.Fun): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: Expr.CFun): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: Expr.Let): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: Expr.If): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: Expr.While): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: Expr.Break): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}