package ck.ast.visitors

import ck.ast.node.CkFile
import ck.ast.node.Decl
import ck.ast.node.Expr

interface ASTVisitor<T> {

    fun visit(f: CkFile): T

    fun visit(m: Decl.Module): T
    fun visit(m: Decl.Type): T

    fun visit(e: Expr.Tuple): T
    fun visit(e: Expr.Sequence): T
    fun visit(e: Expr.Natural): T
    fun visit(e: Expr.Ref): T
    fun visit(e: Expr.Apply): T
    fun visit(e: Expr.Unary): T
    fun visit(e: Expr.Binary): T
    fun visit(e: Expr.Cond): T
    fun visit(e: Expr.Assign): T
    fun visit(e: Expr.Fun): T
    fun visit(e: Expr.Fun.DataConstructor): T
    fun visit(e: Expr.Fun.DataPredicate): T
    fun visit(e: Expr.Fun.DataAccessor): T
    fun visit(e: Expr.CFun): T
    fun visit(e: Expr.Let): T
    fun visit(e: Expr.Let.Rec): T
    fun visit(e: Expr.If): T
}

open class BaseASTVisitor<T> : ASTVisitor<T> {
    override fun visit(m: Decl.Type): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(m: Decl.Module): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: Expr.Fun.DataPredicate): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: Expr.Fun.DataAccessor): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: Expr.Fun.DataConstructor): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: Expr.Let.Rec): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(f: CkFile): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: Expr.Tuple): T {
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

}