

fun compileFunctionBody(expr: Expr): List<String> {
    //empty program
    var program = ArrayList<String>()
    //compilation visitor
    val compilationVisitor = CompilationVisitor()
    //compile top level
    program.addAll(expr.accept(compilationVisitor))
    //get max locals from the top level frame
    val maxLocals = compilationVisitor.frame.maxLocals()
    //create preamble to program
    val preamble = arrayListOf("enter")
    //create space for top level locals
    preamble.addAll(List(maxLocals) {"push 0"})
    //append program to preamble
    preamble.addAll(program)
    //program is preamble
    program = preamble
    //remove locals
    program.addAll(List(maxLocals) {"pop"})
    //leave top level frame
    program.add("leave")
    return program
}

class CompilationVisitor() : ASTVisitor<List<String>> {

    //to keep track of locals and temps on the stack
    //(to be able to generate layout [] instructions)
    //whenever the stack is changed (because we emit an
    //instruction like push, pop, dup, add, etc) we need
    //to adjust the corresponding frame element. similarly
    //when we emit an enter or leave instruction we need
    //to create a new, or restore the last, StackFrame object
    var frame:StackFrame = StackFrame()

    companion object {
        var count:Int = 0
    }

    private fun nextLabel():String {
        return "Label_${count++}"
    }

    override fun visit(e: UnitExpr): List<String> {
        frame.pushTemp(false)
        return listOf("push 0")
    }

    override fun visit(e: SequenceExpr): List<String> {
        val ret = ArrayList(e.first.accept(this))
        ret.add("pop")
        if (e.second != null) {
            ret.addAll(e.second.accept(this))
        }
        return ret
    }

    override fun visit(e: NaturalExpr): List<String> {
        frame.pushTemp(false)
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
                        val localIndex = frame.lookupLocal(e.id)
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
        val ret = ArrayList<String>()
        //evaluate arguments
        e.args.reversed().forEach{ a ->
            ret.addAll(a.accept(this))
            frame.pushTemp(a.accept(GetTypeVisitor()).isRefType())
        }
        //then evaluate function
        ret.addAll(e.target.accept(this))
        frame.pushTemp(true)
        //duplicate function
        ret.add("dup")
        //get code address
        ret.add("rload 0")
        frame.pushTemp(false)
        ret.add("layout [${frame.getLayout().toString(", ")}]")
        //call (replaces code address with return value)
        ret.add("call")
        val retType = e.accept(GetTypeVisitor())
        when (retType) {
            is FunType -> frame.pushTemp(true)
            else -> frame.pushTemp(false)
        }
        //swap function with return value
        ret.add("swap")
        //remove function
        ret.add("pop")
        frame.popTemp()
        //remove args in the same way
        e.args.forEach { _ ->
            ret.add("swap");
            ret.add("pop")
            frame.popTemp()
        }
        return ret
    }

    override fun visit(e: UnaryExpr): List<String> {
        val ret = ArrayList<String>()
        //evaluate operand
        e.operand.accept(this)
        when (e.operator) {
            "-" -> ret.add("neg")
            "!" -> ret.add("not")
            "~" -> ret.add("bitnot")
            else -> TODO("not implemented")
        }
        return ret
    }

    override fun visit(e: BinaryExpr): List<String> {
        val ret = ArrayList<String>()
        e.lhs.accept(this)
        e.rhs.accept(this)
        when (e.operator) {
            "*" -> ret.add("mul")
            "/" -> ret.add("div")
            "%" -> ret.add("mod")
            "+" -> ret.add("add")
            "-" -> ret.add("sub")
            "<<" -> ret.add("shl")
            ">>" -> ret.add("shr")
            "<" -> ret.add("lt")
            "<=" -> ret.add("lte")
            ">" -> ret.add("gt")
            ">=" -> ret.add("gte")
            "<>" -> ret.add("cmp")
            "==" -> ret.add("equal")
            "!=" -> ret.add("nequal")
            "&" -> ret.add("bitand")
            "^" -> ret.add("bitxor")
            "|" -> ret.add("bitor")
            "&&" -> TODO("not implemented")
            "||" -> TODO("not implemented")
            else -> TODO("not implemented")
        }
        //all the above instructions (except && ||) remove a temp from the stack
        frame.popTemp()
        return ret
    }

    override fun visit(e: CondExpr): List<String> {
        val ret = ArrayList<String>()
        val altLabel = nextLabel()
        val endLabel = nextLabel()
        e.cond.accept(this)
        ret.add("jumpz $altLabel")
        ret.addAll(e.csq.accept(this))
        ret.add("jump $endLabel")
        ret.add("$altLabel:")
        ret.addAll(e.alt.accept(this))
        ret.add("$endLabel:")
        return ret
    }

    override fun visit(e: AssignExpr): List<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(p: Param): List<String> {
        TODO("not implemented")
    }

    override fun visit(e: FunExpr): List<String> {
        val ret = ArrayList<String>()
        val bodyLabel = nextLabel()
        val contLabel = nextLabel()
        //set current layout
        ret.add("layout [${frame.getLayout().map { i -> i.toString() }.toString(", ")}]")
        //push size of captures + 1 for function address
        ret.add("push ${e.captures.size + 1}")
        //capture layout is indices of captures where isRefType() is true + 1 because function address is first element
        val captureLayout = (1..e.captures.size).filter { i -> e.captures[i - 1].accept(GetTypeVisitor()).isRefType() }
        //alloc function array
        ret.add("alloc [${captureLayout.map { ci -> ci.toString() }.toString(", ")}]")
        frame.pushTemp(true)
        //duplicate function array
        ret.add("dup")
        frame.pushTemp(true)
        //store function address in fun[0]
        ret.add("push $bodyLabel")
        ret.add("rstore 0")
        frame.popTemp()
        for (i in (0..e.captures.size - 1)) {
            //duplicate function array
            ret.add("dup")
            frame.pushTemp(true)
            //compile capture reference
            e.captures[i].accept(this)
            ret.add("rstore 0")
            frame.popTemp()
        }
        ret.add("jump $contLabel")
        ret.add("$bodyLabel:")
        ret.addAll(compileFunctionBody(e.body))
        ret.add("$contLabel:")
        return ret
    }

    override fun visit(e: LetExpr): List<String> {
        //return var
        val ret = ArrayList<String>()
        //get local index in which to store this value
        val localIndex = frame.pushLocal(e.id, e.value.accept(GetTypeVisitor()).isRefType())
        //compile the value expr
        ret.addAll(e.value.accept(this))
        //store value in local
        ret.add("lstore $localIndex")
        frame.popTemp()
        //compile body if present
        if (e.body != null) {
            ret.addAll(e.body.accept(this))
        }
        //remove local now we're done with it
        frame.popLocal()
        return ret
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

