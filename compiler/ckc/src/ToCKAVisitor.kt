

fun compileTopLevel(expr: Expr) {
    val functions = ArrayList<String>()
    val code = ArrayList<String>()

    code.add("enter")
    code.add("layout [0]")

    expr.accept(ToCKAVisitor(functions, code))

    code.add("leave")
}

class ToCKAVisitor(val functions: MutableList<String>, val code: MutableList<String>) : ASTVisitor<Unit> {

    override fun visit(e: UnitExpr) {
        code.add("push 0")
    }

    override fun visit(e: SequenceExpr) {
        e.first.accept(this)
        e.second?.accept(this)
    }

    override fun visit(e: NaturalExpr) {
        code.add("push ${e.value}")
    }

    override fun visit(e: RefExpr) {
        //TODO: lookup ref and access according to local, arg, or nonlocal
    }

    override fun visit(e: ApplyExpr) {
        //evaluate arguments
        e.args.forEach { a -> a.accept(this) }
        //then evaluate function
        e.target.accept(this)
        //duplicate function
        code.add("dup")
        //get code address
        code.add("rload 0")
        //call
        code.add("call")
    }

    override fun visit(e: UnaryExpr) {
        //evaluate operand
        e.operand.accept(this)
        when (e.operator) {
            "-" ->
                //TODO: implement neg
                code.add("neg")
            "!" ->
                //TODO: implement not
                code.add("not")
            "~" ->
                //TODO: implement bitnot
                code.add("bitnot")
            else -> TODO("not implemented")
        }
    }

    override fun visit(e: BinaryExpr) {
        e.lhs.accept(this)
        e.rhs.accept(this)
        when (e.operator) {
            "*" -> code.add("mul")
            "/" -> code.add("div")
            "%" -> code.add("mod")
            "+" -> code.add("add")
            "-" -> code.add("sub")
            "<<" -> code.add("shl")
            ">>" -> code.add("shr")
            "<" -> code.add("lt") //TODO: implement this
            "<=" -> code.add("lte") //TODO: implement this
            ">" -> code.add("gt") //TODO: implement this
            ">=" -> code.add("gte") //TODO: implement this
            "<>" -> code.add("cmp")
            "==" -> code.add("equal") //TODO: implement this
            "!=" -> code.add("nequal") //TODO: implement this
            "&" -> code.add("bitand") //TODO: implement this
            "^" -> code.add("bitxor") //TODO: implement this
            "|" -> code.add("bitor") //TODO: implement this
            "&&" -> code.add("and") //TODO: implement this (short circuiting)
            "||" -> code.add("or") //TODO: implement this (short circuiting)
            else -> code.add("error") //TODO
        }
    }

    override fun visit(e: CondExpr) {
        //evaluate condition
        e.cond.accept(this)
    }

    override fun visit(e: AssignExpr) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(p: Param) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: FunExpr) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: LetExpr) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: IfExpr) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: WhileExpr) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: BreakExpr) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
