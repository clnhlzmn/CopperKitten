

interface ASTVisitor<T> {

    fun visit(f: CkFile): T

    fun visit(c: CFunDecl): T

    fun visit(e: UnitExpr): T
    fun visit(e: SequenceExpr): T
    fun visit(e: NaturalExpr): T
    fun visit(e: RefExpr): T
    fun visit(e: ApplyExpr): T
    fun visit(e: UnaryExpr): T
    fun visit(e: BinaryExpr): T
    fun visit(e: CondExpr): T
    fun visit(e: AssignExpr): T
    fun visit(e: FunExpr): T
    fun visit(e: LetExpr): T
    fun visit(e: IfExpr): T
    fun visit(e: WhileExpr): T
    fun visit(e: BreakExpr): T
}

open class BaseASTVisitor<T> : ASTVisitor<T> {

    override fun visit(f: CkFile): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(c: CFunDecl): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: UnitExpr): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: SequenceExpr): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: NaturalExpr): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: RefExpr): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: ApplyExpr): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: UnaryExpr): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: BinaryExpr): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: CondExpr): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: AssignExpr): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: FunExpr): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: LetExpr): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: IfExpr): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: WhileExpr): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: BreakExpr): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}