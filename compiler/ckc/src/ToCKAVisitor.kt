

fun compileTopLevel(expr: Expr): String {
    val code = ArrayList<String>()

    expr.accept(ToCKAVisitor(code))

    return code.fold("") { acc, s -> "$acc\n$s" }
}

class ToCKAVisitor(val code: MutableList<String>) : ASTVisitor<Unit> {

    var frame = StackFrame()

    companion object {
        var count:Int = 0
    }

    private fun nextLabel():String {
        return "Label_${count++}"
    }

    override fun visit(e: UnitExpr) {
        code.add("push 0")
        frame.pushTemp(false)
    }

    override fun visit(e: SequenceExpr) {
        e.first.accept(this)
        e.second?.accept(this)
    }

    override fun visit(e: NaturalExpr) {
        code.add("push ${e.value}")
        frame.pushTemp(false)
    }

    override fun visit(e: RefExpr) {
        //TODO: lookup ref and access according to local, arg, or nonlocal
    }

    override fun visit(e: ApplyExpr) {

        //evaluate arguments
        e.args.reversed().forEach{ a ->
            a.accept(this)
        }
        //then evaluate function
        e.target.accept(this)
        //duplicate function
        code.add("dup")
        //get code address
        code.add("rload 0")

        //call (replaces code address with return value)
        code.add("call")
        val retType = e.accept(GetTypeVisitor())
        when (retType) {
            is FunType -> frame.pushTemp(true)
            else -> frame.pushTemp(false)
        }

        //swap function with return value
        code.add("swap")
        //remove function
        code.add("pop")
        frame.popTemp()
        //remove args in the same way
        e.args.forEach { _ ->
            code.add("swap");
            code.add("pop")
            frame.popTemp()
        }
    }

    override fun visit(e: UnaryExpr) {
        //evaluate operand
        e.operand.accept(this)
        when (e.operator) {
            "-" -> code.add("neg")
            "!" -> code.add("not")
            "~" -> code.add("bitnot")
            else -> TODO("not implemented")
        }
        frame.pushTemp(false)
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
            "<" -> code.add("lt")
            "<=" -> code.add("lte")
            ">" -> code.add("gt")
            ">=" -> code.add("gte")
            "<>" -> code.add("cmp")
            "==" -> code.add("equal")
            "!=" -> code.add("nequal")
            "&" -> code.add("bitand")
            "^" -> code.add("bitxor")
            "|" -> code.add("bitor")
            "&&" -> TODO("not implemented")
            "||" -> TODO("not implemented")
            else -> TODO("not implemented")
        }
        frame.popTemp()
    }

    override fun visit(e: CondExpr) {

        //<cond>
        //jz Alt
        //<csq>
        //j End:
        //Alt:
        //<alt>
        //End:

        val altLabel = nextLabel()
        val endLabel = nextLabel()

        e.cond.accept(this)
        code.add("jumpz $altLabel")
        e.csq.accept(this)
        code.add("jump $endLabel")
        code.add("$altLabel:")
        e.alt.accept(this)
        code.add("$endLabel:")
    }

    override fun visit(e: AssignExpr) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(p: Param) {
        TODO("not implemented")
    }

    override fun visit(e: FunExpr) {
        val bodyLabel = nextLabel()
        //alloc array for function (need to know the layout of the stack at this point)
        //store bodyLabel in fun[0]
        //compile capture references
        //and store in fun[1] to fun[1 + captures.size - 1]
        code.add("$bodyLabel:")
        e.body.accept(this)
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

