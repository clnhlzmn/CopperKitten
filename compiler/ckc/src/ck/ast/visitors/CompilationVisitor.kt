package ck.ast.visitors

import ck.ast.node.CkFile
import ck.ast.node.Expr
import ck.compiler.StackFrame
import ck.ast.Type
import util.extensions.toDelimitedString

class CompilationVisitor : BaseASTVisitor<List<String>>() {

    //to keep track of locals and temps on the stack
    //(to be able to generate layout [] instructions)
    //whenever the stack is changed (because we emit an
    //instruction like push, pop, dup, add, etc) we need
    //to adjust the corresponding frame element. similarly
    //when we emit an enter or leave instruction we need
    //to create a new, or restore the last, ck.compiler.StackFrame object
    val frame: StackFrame = StackFrame()

    companion object {
        var count: Int = 0
        private fun nextLabel(): String {
            return "Label_${count++}"
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

    }

    override fun visit(f: CkFile): List<String> {
        val program = ArrayList(compileTopLevelExpr(f.expr))
        program.add("halt")
        return program
    }

    override fun visit(e: Expr.Unit): List<String> {
        //NULL pointer
        frame.push("*", true)
        return listOf("push 0")
    }

    override fun visit(e: Expr.Sequence): List<String> {
        val ret = ArrayList(e.first.accept(this))
        //[...|first]
        ret.add("pop")
        frame.pop()
        //[...]
        ret.addAll(e.second.accept(this))
        //[...|second]
        return ret
    }

    override fun visit(e: Expr.Natural): List<String> {
        val ret = ArrayList<String>()
        //alloc storage for result value
        ret.add("push 1")
        frame.push("*", false)
        ret.add("layout ${frame.getLayoutString()}")
        ret.add("alloc []")
        frame.pop()
        frame.push("*", true)
        //[...|[]]
        ret.add("push ${e.value}")
        //[...|[]|value]
        ret.add("rstore 0")
        //[...|[value]]
        return ret
    }

    override fun visit(e: Expr.Ref): List<String> {
        val def = e.accept(GetDefinitionVisitor())
        when (def) {
            is Definition -> {
                if (def.local) {
                    when (def) {
                        is Definition.Param -> {
                            val enclosingFun = e.accept(GetEnclosingFunction())!!
                            val paramIndex = enclosingFun.params.indexOfFirst { p -> p.id == e.id }
                            frame.push("*", true)
                            return listOf("aload $paramIndex //${e.id}")
                        }
                        is Definition.Let -> {
                            val localIndex = frame.lookup(e.id)
                            if (localIndex != null) {
                                frame.push("*", true)
                                return listOf("lload $localIndex //${e.id}")
                            } else {
                                TODO("error")
                            }
                        }
                    }
                } else {
                    val enclosingFun = e.accept(GetEnclosingFunction())!!
                    val captureIndex = enclosingFun.captures.indexOfFirst { c -> c.id == e.id }
                    frame.push("*", true)
                    return listOf("cload $captureIndex //${e.id}")
                }
            }
            else -> TODO("error")
        }
    }

    override fun visit(e: Expr.Apply): List<String> {
        val ret = ArrayList<String>()

        //compile arguments in reverse order
        e.args.reversed().forEach { a ->
            ret.addAll(a.accept(this))
        }
        //[...|argn-1|...|arg0]

        //then compile function
        ret.addAll(e.fn.accept(this))
        //[...|argn-1|...|arg0|fun]

        //duplicate function (puts another ref on stack)
        ret.add("dup")
        frame.push("*", true)
        //[...|argn-1|...|arg0|fun|fun]

        //get code address (replaces dup'd function with non-ref function address)
        ret.add("rload 0")
        frame.pop()
        frame.push("*", false)
        //[...|argn-1|...|arg0|fun|addr]

        //layout instruction
        ret.add("layout ${frame.getLayoutString()}")

        //call (return removes function address)
        ret.add("call")
        frame.pop()
        //[...|argn-1|...|arg0|fun]

        //remove original function
        ret.add("pop")
        frame.pop()
        //[...|argn-1|...|arg0]

        //remove args in the same way
        repeat(e.args.size) {
            ret.add("pop")
            frame.pop()
        }
        //[...]

        //load return value (function body uses store on ret val)
        ret.add("load")
        frame.push("*", true)
        //[...|retVal]

        return ret
    }

    override fun visit(e: Expr.Unary): List<String> {
        val ret = ArrayList<String>()

        //alloc storage for result value
        ret.add("layout ${frame.getLayoutString()}")
        ret.add("push 1")
        ret.add("alloc []")
        frame.push("*", true)
        //[...|[]]

        //compile operand
        ret.addAll(e.operand.accept(this))
        ret.add("rload 0")
        frame.pop()
        frame.push("*", false)
        //[...|[]|operand]

        when (e.operator) {
            "-" -> ret.add("neg")
            "!" -> ret.add("not")
            "~" -> ret.add("bitnot")
            else -> TODO("not implemented")
        }
        //[...|[]|op(operand)]

        //store result
        ret.add("rstore 0")
        frame.pop()
        //[...|[op(operand)]]

        return ret
    }

    override fun visit(e: Expr.Binary): List<String> {
        val ret = ArrayList<String>()

        //alloc storage for result value
        ret.add("layout ${frame.getLayoutString()}")
        ret.add("push 1")
        ret.add("alloc []")
        frame.push("*", true)
        //[...|[]]

        //do operation
        if (e.operator != "&&" && e.operator != "||") {
            //compile lhs
            ret.addAll(e.lhs.accept(this))
            ret.add("rload 0")
            frame.pop()
            frame.push("*", false)
            //[...|[]|lhs]

            //compile rhs
            ret.addAll(e.rhs.accept(this))
            ret.add("rload 0")
            frame.pop()
            frame.push("*", false)
            //[...|[]|lhs|rhs]

            //do op
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
                else -> TODO("not implemented")
            }
            frame.pop()
            //[...|[]|lhs op rhs]
        } else {
            when (e.operator) {
                "&&" -> {
                    val endLabel = nextLabel()
                    //compile lhs
                    ret.addAll(e.lhs.accept(this))
                    ret.add("rload 0")
                    frame.pop()
                    frame.push("*", false)
                    //[...|[]|lhs]

                    ret.add("dup")
                    ret.add("jumpz $endLabel")
                    //[...|[]|lhs]

                    ret.add("pop")
                    frame.pop()
                    //[...|[]]

                    ret.addAll(e.rhs.accept(this))
                    ret.add("rload 0")
                    frame.pop()
                    frame.push("*", false)
                    //[...|[]|rhs]

                    ret.add("$endLabel:")
                    //[...|[]|lhs && rhs]
                }
                "||" -> {
                    val endLabel = nextLabel()
                    //compile lhs
                    ret.addAll(e.lhs.accept(this))
                    ret.add("rload 0")
                    frame.pop()
                    frame.push("*", false)
                    //[...|[]|lhs]

                    ret.add("dup")
                    ret.add("jumpnz $endLabel")
                    //[...|[]|lhs]

                    ret.add("pop")
                    frame.pop()
                    //[...|[]]

                    ret.addAll(e.rhs.accept(this))
                    ret.add("rload 0")
                    frame.pop()
                    frame.push("*", false)
                    //[...|[]|rhs]

                    ret.add("$endLabel:")
                    //[...|[]|lhs || rhs]
                }
                else -> TODO("not implemented")
            }
        }
        //[...|[]|lhs op rhs]

        //store result
        ret.add("rstore 0")
        frame.pop()
        //[...|[lhs op rhs]]
        return ret
    }

    override fun visit(e: Expr.Cond): List<String> {
        val ret = ArrayList<String>()
        val altLabel = nextLabel()
        val endLabel = nextLabel()

        //compile cond
        ret.addAll(e.cond.accept(this))
        ret.add("rload 0")
        frame.pop()
        frame.push("*", false)
        //[...|cond]

        //if cond is zero goto alt
        ret.add("jumpz $altLabel")
        frame.pop()

        //compile csq
        ret.addAll(e.csq.accept(this))
        //[...|csq]

        //if cond was non zero then skip alt
        ret.add("jump $endLabel")

        //compile alt
        ret.add("$altLabel:")

        //remove csq from compile frame before compiling alt
        frame.pop()

        //compile alt
        ret.addAll(e.alt.accept(this))
        //[...|alt]

        ret.add("$endLabel:")
        //[...|cond?csq:alt]

        return ret
    }

    override fun visit(e: Expr.Assign): List<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: Expr.Fun): List<String> {
        val ret = ArrayList<String>()
        val bodyLabel = nextLabel()
        val contLabel = nextLabel()

        //push size of captures + 1 for function address
        ret.add("push ${e.captures.size + 1}")
        frame.push("*", false)
        //[...|size]

        //set current layout
        ret.add("layout ${frame.getLayoutString()}")

        //alloc function array
        //capture layout is indices of captures + 1 because function address is first element
        val captureLayout = (1..e.captures.size).map { ci -> ci.toString() }.toDelimitedString(", ")
        ret.add("alloc [$captureLayout]")
        frame.pop()
        frame.push("*", true)
        //[...|fun]

        //store function address in fun[0]
        ret.add("push $bodyLabel")
        ret.add("rstore 0")
        //[...|[addr|...]]

        e.captures.forEachIndexed { i, c ->
            //compile capture reference
            ret.addAll(c.accept(this))
            ret.add("rstore ${i + 1}")
            ////[...|[addr|capt0|...|capti]]
        }

        //jump over the function body
        ret.add("jump $contLabel")

        //code between bodyLabel and contLabel doesn't change stack here
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

        //push size of 1 for function address
        ret.add("push 1")
        frame.push("*", false)
        //set current layout
        ret.add("layout ${frame.getLayoutString()}")
        //alloc function array
        ret.add("alloc []")
        frame.pop()
        frame.push("*", true)
        //[...|fun]

        //store function address in fun[0]
        ret.add("push $bodyLabel")
        ret.add("rstore 0")
        //[...|[addr]]

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
        val ret = ArrayList<String>()
        //[...]

        //add space for local
        ret.add("push 0")
        //get local index (adjusted for instance #) in which to store this value
        val localIndex = frame.push(e.id, true)
        //[...|0]

        //compile the value expr
        ret.addAll(e.value.accept(this))
        //[...|0|val]

        //store value in local
        ret.add("lstore $localIndex //${e.id}")
        frame.pop()
        //[...|val]

        //compile body
        ret.addAll(e.body.accept(this))
        //[...|val|bodyVal]

        //swap body val
        ret.add("swap")
        //[...|bodyVal|val]

        //remove local now we're done with it
        ret.add("pop")
        frame.pop()
        //[...]

        return ret
    }

    override fun visit(e: Expr.If): List<String> {
        val ret = ArrayList<String>()
        val altLabel = nextLabel()
        val endLabel = nextLabel()
        //[...]

        //compile cond
        ret.addAll(e.cond.accept(this))
        ret.add("rload 0")
        frame.pop()
        frame.push("*", false)
        //[...|cond]

        //if cond zero the goto alt
        ret.add("jumpz $altLabel")
        frame.pop()
        //[...]

        //compile csq
        ret.addAll(e.csq.accept(this))
        //[...|csq]

        //if cond not zero then skip alt
        ret.add("jump $endLabel")
        //doesn't change stack

        //compile alt
        ret.add("$altLabel:")
        //remove csq from compile frame before compiling alt
        frame.pop()
        if (e.alt == null) {
            ret.add("push 0")
            frame.push("*", true)
        } else {
            ret.addAll(e.alt.accept(this))
        }
        //[...|alt]

        ret.add("$endLabel:")
        //[...|if (cond) csq else alt]

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
        ret.add("rload 0")
        frame.pop()
        frame.push("*", false)
        ret.add("jumpnz $beginLabel")
        frame.pop()
        //value of type Unit (NULL pointer)
        ret.add("push 0")
        frame.push("*", true)
        return ret
    }

    override fun visit(e: Expr.Break): List<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

