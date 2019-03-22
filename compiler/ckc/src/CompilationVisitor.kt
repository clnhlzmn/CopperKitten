import ck.ast.BaseASTVisitor

fun compileCkFile(ckFile: CkFile): List<String> {
    val program = ArrayList<String>()
    program.addAll(compileTopLevelExpr(ckFile.expr))
    program.add("halt")
    return program
}

fun compileTopLevelExpr(expr: Expr): List<String> {
    //empty program
    val program = ArrayList<String>()
    //enter frame
    program.add("enter")
    //compilation visitor
    val compilationVisitor = CompilationVisitor()
    //compile top level
    program.addAll(expr.accept(compilationVisitor))
    //save return value
    program.add("store")
    //leave top level frame
    program.add("leave")
    return program
}

fun compileFunctionBody(body: Expr): List<String> {
    val ret = ArrayList(compileTopLevelExpr(body))
    ret.add("return")
    return ret
}

class CompilationVisitor : BaseASTVisitor<List<String>>() {

    //to keep track of locals and temps on the stack
    //(to be able to generate layout [] instructions)
    //whenever the stack is changed (because we emit an
    //instruction like push, pop, dup, add, etc) we need
    //to adjust the corresponding frame element. similarly
    //when we emit an enter or leave instruction we need
    //to create a new, or restore the last, StackFrame object
    val frame: StackFrame = StackFrame()

    companion object {
        var count: Int = 0
    }

    private fun nextLabel(): String {
        return "Label_${count++}"
    }

    override fun visit(e: Expr.Unit): List<String> {
        frame.push("*", false)
        return listOf("push 0")
    }

    override fun visit(e: Expr.Sequence): List<String> {
        val ret = ArrayList(e.first.accept(this))
        ret.add("pop")
        frame.pop()
        ret.addAll(e.second.accept(this))
        return ret
    }

    override fun visit(e: Expr.Natural): List<String> {
        frame.push("*", false)
        return listOf("push ${e.value}")
    }

    override fun visit(e: Expr.Ref): List<String> {
        val def = e.accept(GetDefinitionVisitor())
        //TODO: compile ref for correct instance
        val isRef = Type.simplify(e.t).isRefType()
        when (def) {
            is Definition -> {
                if (def.local) {
                    when (def) {
                        is Definition.Param -> {
                            val enclosingFun = e.accept(GetEnclosingFunction())!!
                            val paramIndex = enclosingFun.params.indexOfFirst { p -> p.id == e.id }
                            frame.push("*", isRef)
                            return listOf("aload $paramIndex //${e.id}")
                        }
                        is Definition.Let -> {
                            val localIndex = frame.lookup(e.id)
                            if (localIndex != null) {
                                frame.push("*", isRef)
                                return listOf("lload $localIndex //${e.id}")
                            } else {
                                TODO("error")
                            }
                        }
                    }
                } else {
                    val enclosingFun = e.accept(GetEnclosingFunction())!!
                    val captureIndex = enclosingFun.captures.indexOfFirst { c -> c.id == e.id }
                    frame.push("*", isRef)
                    return listOf("cload $captureIndex //${e.id}")
                }
            }
            else -> TODO("error")
        }
    }

    override fun visit(e: Expr.Apply): List<String> {
        val ret = ArrayList<String>()
        //evaluate arguments
        e.args.reversed().forEach { a ->
            ret.addAll(a.accept(this))
        }
        //then evaluate function
        ret.addAll(e.fn.accept(this))
        //duplicate function (puts another ref on stack)
        ret.add("dup")
        //get code address (replaces dup'd function with non-ref function address)
        ret.add("rload 0")
        frame.push("*", false)
        //layout instruction
        ret.add("layout [${frame.getLayout().toString(", ")}]")
        //call (removes function address)
        ret.add("call")
        frame.pop()
        //remove original function
        ret.add("pop")
        frame.pop()
        //remove args in the same way
        e.args.forEach { _ ->
            ret.add("pop")
            frame.pop()
        }
        //load return value
        ret.add("load")
        //push temp (return value)
        frame.push("*", Type.simplify(e.t).isRefType())
        return ret
    }

    override fun visit(e: Expr.Unary): List<String> {
        val ret = ArrayList<String>()
        //evaluate operand
        ret.addAll(e.operand.accept(this))
        when (e.operator) {
            "-" -> ret.add("neg")
            "!" -> ret.add("not")
            "~" -> ret.add("bitnot")
            else -> TODO("not implemented")
        }
        return ret
    }

    override fun visit(e: Expr.Binary): List<String> {
        val ret = ArrayList<String>()
        if (e.operator != "&&" && e.operator != "||") {
            ret.addAll(e.lhs.accept(this))
            ret.addAll(e.rhs.accept(this))
        }
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
            "&&" -> {
                val endLabel = nextLabel()
                ret.addAll(e.lhs.accept(this))
                ret.add("dup")
                ret.add("jumpz $endLabel")
                ret.add("pop")
                ret.addAll(e.rhs.accept(this))
                ret.add("$endLabel:")
            }
            "||" -> {
                val endLabel = nextLabel()
                ret.addAll(e.lhs.accept(this))
                ret.add("dup")
                ret.add("jumpnz $endLabel")
                ret.add("pop")
                ret.addAll(e.rhs.accept(this))
                ret.add("$endLabel:")
            }
            else -> TODO("not implemented")
        }
        //all the above instructions (except && ||) remove a temp from the stack
        frame.pop()
        return ret
    }

    override fun visit(e: Expr.Cond): List<String> {
        val ret = ArrayList<String>()
        val altLabel = nextLabel()
        val endLabel = nextLabel()
        ret.addAll(e.cond.accept(this))
        ret.add("jumpz $altLabel")
        ret.addAll(e.csq.accept(this))
        ret.add("jump $endLabel")
        ret.add("$altLabel:")
        ret.addAll(e.alt.accept(this))
        ret.add("$endLabel:")
        return ret
    }

    override fun visit(e: Expr.Assign): List<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: Expr.Fun): List<String> {
        val ret = ArrayList<String>()
        val bodyLabel = nextLabel()
        val contLabel = nextLabel()
        //set current layout
        ret.add("layout [${frame.getLayout().map { i -> i.toString() }.toString(", ")}]")
        //push size of captures + 1 for function address
        ret.add("push ${e.captures.size + 1}")
        //capture layout is indices of captures where isRefType() is true + 1 because function address is first element
        val captureLayout = (1..e.captures.size).filter { i -> Type.simplify(e.captures[i - 1].t).isRefType() }
        //alloc function array
        ret.add("alloc [${captureLayout.map { ci -> ci.toString() }.toString(", ")}]")
        frame.push("*", true)
        //duplicate function array
        ret.add("dup")
        frame.push("*", true)
        //store function address in fun[0]
        ret.add("push $bodyLabel")
        ret.add("rstore 0")
        frame.pop()
        for (i in (0 until e.captures.size)) {
            //duplicate function array
            ret.add("dup")
            frame.push("*", true)
            //compile capture reference
            ret.addAll(e.captures[i].accept(this))
            ret.add("rstore ${i + 1}")
            frame.pop()
        }
        //jump over the function body
        ret.add("jump $contLabel")
        //here goes the body
        ret.add("$bodyLabel:")
        //compile body (including 'enter', 'store' ret val, 'leave', and 'return')
        ret.addAll(compileFunctionBody(e.body))
        //continue program from above
        ret.add("$contLabel:")
        return ret
    }

    override fun visit(e: Expr.CFun): List<String> {
        val ret = ArrayList<String>()
        val bodyLabel = nextLabel()
        val contLabel = nextLabel()
        //set current layout
        ret.add("layout [${frame.getLayout().map { i -> i.toString() }.toString(", ")}]")
        //push size of 1 for function address
        ret.add("push 1")
        //alloc function array
        ret.add("alloc []")
        frame.push("*", true)
        //duplicate function array
        ret.add("dup")
        frame.push("*", true)
        //store function address in fun[0]
        ret.add("push $bodyLabel")
        ret.add("rstore 0")
        frame.pop()
        //jump over the function body
        ret.add("jump $contLabel")
        //here goes the body
        ret.add("$bodyLabel:")
        //compile body (including 'enter', 'store' ret val, 'leave', and 'return')
        ret.add("enter")
        ret.add("ncall ${e.id}")
        //store single return value from cfun
        ret.add("store")
        ret.add("leave")
        //put 'return' here
        ret.add("return")
        //continue program from above
        ret.add("$contLabel:")
        return ret
    }

    override fun visit(e: Expr.Let): List<String> {
        //return var
        val ret = ArrayList<String>()
        e.instances.forEachIndexed { index, instance ->
            //TODO: this doesn't work because enclosingScope fields are not linked correctly
            //TODO: have to create a completely new expr with correct instances
            //get subs
            val subs = Type.getSubstitutions(Type.simplify(e.value.t), instance)
            //apply to value
            val value = Expr.apply(subs, e.value)
            //get local index (adjusted for instance #) in which to store this value
            val localIndex = frame.push(e.id + "<$index>", value.t.isRefType())
            //compile the value expr
            ret.add("//$value")
            ret.addAll(value.accept(this))
            //store value in local
            ret.add("lstore $localIndex //${e.id}<$index>")
            frame.pop()
        }
        //compile body if present
        ret.add("//${e.body}")
        ret.addAll(e.body.accept(this))
        //remove local now we're done with it
        frame.pop()
        return ret
    }

    override fun visit(e: Expr.If): List<String> {
        val ret = ArrayList<String>()
        val altLabel = nextLabel()
        val endLabel = nextLabel()
        ret.addAll(e.cond.accept(this))
        ret.add("jumpz $altLabel")
        ret.addAll(e.csq.accept(this))
        ret.add("jump $endLabel")
        ret.add("$altLabel:")
        if (e.alt == null) {
            ret.add("push 0")
            frame.push("*", false)
        } else {
            ret.addAll(e.alt.accept(this))
        }
        ret.add("$endLabel:")
        return ret
    }

    override fun visit(e: Expr.While): List<String> {
        val ret = ArrayList<String>()
        val beginLabel = nextLabel()
        val condLabel = nextLabel()
        ret.add("jump $condLabel")
        ret.add("$beginLabel:")
        ret.addAll(e.body.accept(this))
        //discard result
        ret.add("pop")
        frame.pop()
        ret.add("$condLabel:")
        ret.addAll(e.cond.accept(this))
        ret.add("jumpnz $beginLabel")
        frame.pop()
        //value of type Unit
        ret.add("push 0")
        frame.push("*", false)
        return ret
    }

    override fun visit(e: Expr.Break): List<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

