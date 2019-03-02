

//a visitor used to traverse the ast using enclosing
//scope to find the definition of a RefExpr
class FindDefinitionVisitor : ASTVisitor<ASTNode?> {

    var id: String? = null

    override fun visit(e: UnitExpr): ASTNode? {
        return null
    }

    override fun visit(e: SequenceExpr): ASTNode? {
        return null
    }

    override fun visit(e: NaturalExpr): ASTNode? {
        return null
    }

    //first visit should be to a RefExpr where we immediately
    //start visiting enclosing scopes
    override fun visit(e: RefExpr): ASTNode? {
        id = e.id
        return e.enclosingScope?.accept(this)
    }

    override fun visit(e: ApplyExpr): ASTNode? {
        return null
    }

    override fun visit(e: UnaryExpr): ASTNode? {
        return null
    }

    override fun visit(e: BinaryExpr): ASTNode? {
        return null
    }

    override fun visit(e: CondExpr): ASTNode? {
        return null
    }

    override fun visit(e: AssignExpr): ASTNode? {
        return null
    }

    override fun visit(p: Param): ASTNode? {
        return null
    }

    override fun visit(e: FunExpr): ASTNode? {
        //look at function parameters for id
        for (param in e.params) {
            if (param.id == id) {
                return param
            }
        }
        //otherwise look in enclosing scope
        return e.enclosingScope?.accept(this)
    }

    override fun visit(e: LetExpr): ASTNode? {
        //check the id of LetExpr
        if (e.id == id) {
            return e.value
        }
        //otherwise look in enclosing scope
        return e.enclosingScope?.accept(this)
    }

    override fun visit(e: IfExpr): ASTNode? {
        return null
    }

    override fun visit(e: WhileExpr): ASTNode? {
        return null
    }

    override fun visit(e: BreakExpr): ASTNode? {
        return null
    }

}