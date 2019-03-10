

sealed class Definition private constructor (val local: Boolean) {
    class Let(val node: LetExpr, local: Boolean) : Definition(local)
    class Param(val node: FunExpr.Param, local: Boolean) : Definition(local)
}

//a visitor used to traverse the ast using enclosing
//scope to find the definition of a RefExpr
class FindDefinitionVisitor : BaseASTVisitor<Definition?>() {

    var isLocal = true

    var id: String? = null

    override fun visit(e: UnitExpr): Definition? {
        return null
    }

    override fun visit(e: SequenceExpr): Definition? {
        return null
    }

    override fun visit(e: NaturalExpr): Definition? {
        return null
    }

    //first visit should be to a RefExpr where we immediately
    //start visiting enclosing scopes
    override fun visit(e: RefExpr): Definition? {
        id = e.id
        return e.enclosingScope?.accept(this)
    }

    override fun visit(e: ApplyExpr): Definition? {
        return null
    }

    override fun visit(e: UnaryExpr): Definition? {
        return null
    }

    override fun visit(e: BinaryExpr): Definition? {
        return null
    }

    override fun visit(e: CondExpr): Definition? {
        return null
    }

    override fun visit(e: AssignExpr): Definition? {
        return null
    }

    override fun visit(e: FunExpr): Definition? {
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

    override fun visit(e: LetExpr): Definition? {
        //check the id of LetExpr
        if (e.id == id) {
            return Definition.Let(e, isLocal)
        }
        //otherwise look in enclosing scope
        return e.enclosingScope?.accept(this)
    }

    override fun visit(e: IfExpr): Definition? {
        return null
    }

    override fun visit(e: WhileExpr): Definition? {
        return null
    }

    override fun visit(e: BreakExpr): Definition? {
        return null
    }

}