package ck.ast.visitors

import ck.ast.node.CkFile
import ck.ast.node.Expr

sealed class Definition(val local: Boolean) {
    class Let(val node: Expr.Let, local: Boolean) : Definition(local)
    class LetRec(val node: Expr.Let.Rec, local: Boolean): Definition(local)
    class Param(val node: Expr.Fun.Param, local: Boolean) : Definition(local)
}

//a visitor used to traverse the ast using enclosing
//scope to find the definition of a ck.ast.node.Expr.Ref
class GetDefinitionVisitor : BaseASTVisitor<Definition?>() {

    override fun visit(e: Expr.Fun.ProductCtor): Definition? {
        return null
    }

    var isLocal = true

    var id: String? = null

    override fun visit(f: CkFile): Definition? {
        return null
    }

    override fun visit(e: Expr.Tuple): Definition? {
        return null
    }

    override fun visit(e: Expr.Sequence): Definition? {
        return null
    }

    override fun visit(e: Expr.Natural): Definition? {
        return null
    }

    //first visit should be to a ck.ast.node.Expr.Ref where we immediately
    //start visiting enclosing scopes
    override fun visit(e: Expr.Ref): Definition? {
        id = e.id
        return e.enclosingScope?.accept(this)
    }

    override fun visit(e: Expr.Apply): Definition? {
        return null
    }

    override fun visit(e: Expr.Unary): Definition? {
        return null
    }

    override fun visit(e: Expr.Binary): Definition? {
        return null
    }

    override fun visit(e: Expr.Cond): Definition? {
        return null
    }

    override fun visit(e: Expr.Assign): Definition? {
        return null
    }

    override fun visit(e: Expr.Fun): Definition? {
        //look at function parameters for id
        for (param in e.params) {
            if (param.id == id) {
                return Definition.Param(param, isLocal)
            }
        }
        isLocal = false
        //otherwise look in enclosing scope
        return e.enclosingScope?.accept(this)
    }

    override fun visit(e: Expr.Let): Definition? {
        //check the id of ck.ast.node.Expr.Let
        if (e.binding.id == id) {
            return Definition.Let(e, isLocal)
        }
        //otherwise look in enclosing scope
        return e.enclosingScope?.accept(this)
    }

    override fun visit(e: Expr.Let.Rec): Definition? {
        for (binding in e.bindings) {
            if (binding.id == id) {
                return Definition.LetRec(e, isLocal)
            }
        }
        return e.enclosingScope?.accept(this)
    }

    override fun visit(e: Expr.CFun): Definition? {
        return null
    }

    override fun visit(e: Expr.If): Definition? {
        return null
    }

}