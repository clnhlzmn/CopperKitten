

fun compileTopLevel(expr: Expr): String {

    //TODO: wrap top level expr in {():Unit expr}()
    val program = expr.accept(ToCKAVisitor())

    return program.fold("") { acc, s -> "$acc\n$s" }
}

class ToCKAVisitor() : ASTVisitor<List<String>> {

    //to keep track of locals and temps on the stack
    //(to be able to generate layout [] instructions)
    //whenever the stack is changed (because we emit an
    //instruction like push, pop, dup, add, etc) we need
    //to adjust the corresponding frame element. similarly
    //when we emit an enter or leave instruction we need
    //to create a new, or restore the last, StackFrame object
    var frame:StackFrame? = null

    companion object {
        var count:Int = 0
    }

    private fun nextLabel():String {
        return "Label_${count++}"
    }

    override fun visit(e: UnitExpr): List<String> {
        frame!!.pushTemp(false)
        return listOf("push 0")
    }

    override fun visit(e: SequenceExpr): List<String> {
        val ret = ArrayList(e.first.accept(this))
        if (e.second != null) {
            ret.addAll(e.second.accept(this))
        }
        return ret
    }

    override fun visit(e: NaturalExpr): List<String> {
        frame!!.pushTemp(false)
        return listOf("push ${e.value}")
    }

    override fun visit(e: RefExpr): List<String> {
        val def = e.accept(FindDefinitionVisitor())
        when (def) {
            is NonLocalDef -> {
                val enclosingFun = e.accept(GetEnclosingFunction())!!
                val captureIndex = enclosingFun.captures.indexOfFirst{c -> c.id == e.id }
                return listOf("cload $captureIndex")
            }
            is LocalDef -> {
                when (def.node) {
                    is Param -> {
                        val enclosingFun = e.accept(GetEnclosingFunction())!!
                        val paramIndex = enclosingFun.params.indexOfFirst { p -> p.id == e.id }
                        return listOf("aload $paramIndex")
                    }
                    is LetExpr -> {
                        val localIndex = frame!!.lookupLocal(e.id)
                        if (localIndex != null) {
                            return listOf("lload $localIndex")
                        } else {
                            TODO("error")
                        }
                    }
                    else -> TODO("error")
                }
            }
            else -> TODO("error")
        }
    }

    override fun visit(e: ApplyExpr): List<String> {

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
            is FunType -> frame!!.pushTemp(true)
            else -> frame!!.pushTemp(false)
        }

        //swap function with return value
        code.add("swap")
        //remove function
        code.add("pop")
        frame!!.popTemp()
        //remove args in the same way
        e.args.forEach { _ ->
            code.add("swap");
            code.add("pop")
            frame!!.popTemp()
        }
    }

    override fun visit(e: UnaryExpr): List<String> {
        //evaluate operand
        e.operand.accept(this)
        when (e.operator) {
            "-" -> code.add("neg")
            "!" -> code.add("not")
            "~" -> code.add("bitnot")
            else -> TODO("not implemented")
        }
    }

    override fun visit(e: BinaryExpr): List<String> {
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
        //all the above instructions (except && ||) remove a temp from the stack
        frame!!.popTemp()
    }

    override fun visit(e: CondExpr): List<String> {

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

    override fun visit(e: AssignExpr): List<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(p: Param): List<String> {
        TODO("not implemented")
    }

    override fun visit(e: FunExpr): List<String> {
        val bodyLabel = nextLabel()
        //alloc array for function (need to know the layout of the stack at this point)
        //store bodyLabel in fun[0]
        //compile capture references
        //and store in fun[1] to fun[1 + captures.size - 1]
        code.add("$bodyLabel:")
        //TODO: add 'enter' instruction here and create a new StackFrame
        e.body.accept(this)
        //TODO: add 'leave' and 'return' here and restore old StackFrame
    }

    override fun visit(e: LetExpr): List<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: IfExpr): List<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: WhileExpr): List<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: BreakExpr): List<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

