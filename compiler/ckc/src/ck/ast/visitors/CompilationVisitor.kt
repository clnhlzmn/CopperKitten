package ck.ast.visitors

import ck.ast.node.CkFile
import ck.ast.node.Expr
import ck.compiler.StackFrame
import util.extensions.toDelimitedString

class CompilationVisitor(val debug: Boolean = false) : BaseASTVisitor<List<String>>() {

    //to keep track of locals and temps on the stack
    //(to be able to generate layout [] instructions)
    //whenever the stack is changed (because we emit an
    //instruction like push, pop, dup, add, etc) we need
    //to adjust the corresponding frame element. similarly
    //when we emit an enter or leave instruction we need
    //to create a new, or restore the last, ck.compiler.StackFrame object
    var frame: StackFrame = StackFrame()

    companion object {
        var count: Int = 0
        private fun nextLabel(): String {
            return "Label_${count++}"
        }
    }

    private fun compileExprInNewFrame(expr: Expr): List<String> {
        //empty program
        val ret = ArrayList<String>()
        //enter frame
        ret.add("enter")
        //create a new compile time frame
        val lastFrame = frame
        frame = StackFrame()
        //compile top level
        ret.addAll(expr.accept(this))
        //save return value
        ret.add("store")
        frame.pop()
        //leave top level frame
        ret.add("leave")
        //restore old frame
        frame = lastFrame
        return ret
    }

    private fun compileFunctionBody(body: Expr): List<String> {
        val ret = ArrayList(compileExprInNewFrame(body))
        ret.add("return")
        return ret
    }

    override fun visit(f: CkFile): List<String> {
        val ret = ArrayList<String>()
        if (debug) ret.add("debugpush \"$f\"")
        ret.addAll(compileExprInNewFrame(f.expr))
        if (debug) ret.add("debugpop")
        ret.add("halt")
        return ret
    }

    override fun visit(e: Expr.Tuple): List<String> {
        val ret = ArrayList<String>()
        if (debug) ret.add("debugpush \"$e\"")
        if (e.exprs.isEmpty()) {
            //NULL pointer
            frame.push("<()>", true)
            ret.add("push 0")
        } else {
            ret.add("push ${e.exprs.size}")
            frame.push("${e.exprs.size}", false)

            ret.add("layout ${frame.getLayoutString()}")

            ret.add("alloc [${e.exprs.mapIndexed { index, _ ->  index.toString() }.toDelimitedString(", ")}]")
            frame.pop()
            frame.push("[${e.exprs.size}]", true)

            e.exprs.forEachIndexed { index, expr ->
                ret.addAll(expr.accept(this))
                ret.add("rstore $index")
                frame.pop()
            }
        }
        if (debug) ret.add("debugpop")
        return ret
    }

    override fun visit(e: Expr.Sequence): List<String> {
        val ret = ArrayList<String>()
        if (debug) ret.add("debugpush \"$e\"")
        ret.addAll(e.first.accept(this))
        //[...|first]
        ret.add("pop")
        frame.pop()
        //[...]
        ret.addAll(e.second.accept(this))
        //[...|second]
        if (debug) ret.add("debugpop")
        return ret
    }

    override fun visit(e: Expr.Natural): List<String> {
        val ret = ArrayList<String>()
        if (debug) ret.add("debugpush \"$e\"")
        //alloc storage for result value
        ret.add("push 1")
        frame.push("<1>", false)
        ret.add("layout ${frame.getLayoutString()}")
        ret.add("alloc []")
        frame.pop()
        frame.push("Int($e)", true)
        //[...|[]]
        ret.add("push ${e.value}")
        //[...|[]|value]
        ret.add("rstore 0")
        //[...|[value]]
        if (debug) if (debug) ret.add("debugpop")
        return ret
    }

    override fun visit(e: Expr.Ref): List<String> {
        val ret = ArrayList<String>()
        if (debug) ret.add("debugpush \"$e\"")
        val def = e.accept(GetDefinitionVisitor())
        when (def) {
            is Definition -> {
                if (def.local) {
                    when (def) {
                        is Definition.Param -> {
                            val enclosingFun = e.accept(GetEnclosingFunction())!!
                            val paramIndex = enclosingFun.params.indexOfFirst { p -> p.id == e.id }
                            frame.push("<$e>", true)
                            ret.add("aload $paramIndex")
                        }
                        is Definition.Let -> {
                            val localIndex = frame.lookup(e.id)
                            if (localIndex != null) {
                                frame.push("<$e>", true)
                                ret.add("lload $localIndex")
                            } else {
                                TODO("error")
                            }
                        }
                        is Definition.LetRec -> {
                            val localIndex = frame.lookup(e.id)
                            if (localIndex != null) {
                                frame.push("<$e>", true)
                                ret.add("lload $localIndex")
                            } else {
                                TODO("error")
                            }
                        }
                    }
                } else {
                    val enclosingFun = e.accept(GetEnclosingFunction())!!
                    val captureIndex = enclosingFun.captures.indexOfFirst { c -> c.id == e.id }
                    frame.push("<$e>", true)
                    ret.add("cload $captureIndex")
                }
            }
            else -> TODO("error")
        }
        if (debug) ret.add("debugpop")
        return ret
    }

    override fun visit(e: Expr.Apply): List<String> {
        val ret = ArrayList<String>()
        if (debug) ret.add("debugpush \"$e\"")

        //tail call optimization if number of args of caller and callee are equal
        if (e.isTailCall && e.accept(GetEnclosingFunction())!!.params.size == e.args.size) {

            //compile arguments in reverse order
            e.args.reversed().forEach { a ->
                ret.addAll(a.accept(this))
            }
            //[...|argn-1|...|arg0]

            //then compile function
            ret.addAll(e.fn.accept(this))
            //[...|argn-1|...|arg0|fun]

            //save a copy of fun for later
            ret.add("dup")
            ret.add("store")

            //then store fun in fun location on stack
            ret.add("astore -1")
            frame.pop()
            //[...|argn-1|...|arg0]

            //then store args
            e.args.forEachIndexed { index, _ ->
                ret.add("astore $index")
                frame.pop()
                //[...|argn-1|...]
            }
            //[...]

            //don't have to keep exact compile time stack info here
            //just add "fake" ret value at enc

            //then clear frame
            ret.add("clear")

            //then leave frame
            ret.add("leave")

            //then load fun
            ret.add("load")

            //get addr
            ret.add("rload 0")

            //then jump
            ret.add("goto")

            //then push "fake" return value?
            frame.push("<$e>", true)

        } else {

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
            frame.push("<${e.fn}>", true)
            //[...|argn-1|...|arg0|fun|fun]

            //get code address (replaces dup'd function with non-ref function address)
            ret.add("rload 0")
            frame.pop()
            frame.push("&${e.fn}", false)
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
            frame.push("<$e>", true)
            //[...|retVal]

        }

        if (debug) ret.add("debugpop")
        return ret
    }

    override fun visit(e: Expr.Unary): List<String> {
        val ret = ArrayList<String>()
        if (debug) ret.add("debugpush \"$e\"")

        //alloc storage for result value
        ret.add("layout ${frame.getLayoutString()}")
        ret.add("push 1")
        ret.add("alloc []")
        frame.push("<Int($e)>", true)
        //[...|[]]

        //compile operand
        ret.addAll(e.operand.accept(this))
        ret.add("rload 0")
        frame.pop()
        frame.push("<${e.operand}>", false)
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

        if (debug) ret.add("debugpop")
        return ret
    }

    override fun visit(e: Expr.Binary): List<String> {
        val ret = ArrayList<String>()
        if (debug) ret.add("debugpush \"$e\"")

        //alloc storage for result value
        ret.add("layout ${frame.getLayoutString()}")
        ret.add("push 1")
        ret.add("alloc []")
        frame.push("<Int($e)>", true)
        //[...|[]]

        //do operation
        if (e.operator != "&&" && e.operator != "||") {
            //compile lhs
            ret.addAll(e.lhs.accept(this))
            ret.add("rload 0")
            frame.pop()
            frame.push("<${e.lhs}>", false)
            //[...|[]|lhs]

            //compile rhs
            ret.addAll(e.rhs.accept(this))
            ret.add("rload 0")
            frame.pop()
            frame.push("<${e.rhs}>", false)
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
                "==" -> ret.add("eq")
                "!=" -> ret.add("neq")
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
                    frame.push("<${e.lhs}>", false)
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
                    frame.push("<${e.rhs}>", false)
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
                    frame.push("<${e.lhs}>", false)
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
                    frame.push("<${e.rhs}>", false)
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

        if (debug) ret.add("debugpop")
        return ret
    }

    override fun visit(e: Expr.Cond): List<String> {
        val ret = ArrayList<String>()
        if (debug) ret.add("debugpush \"$e\"")
        val altLabel = nextLabel()
        val endLabel = nextLabel()

        //compile cond
        ret.addAll(e.cond.accept(this))
        ret.add("rload 0")
        frame.pop()
        frame.push("<${e.cond}>", false)
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

        if (debug) ret.add("debugpop")
        return ret
    }

    override fun visit(e: Expr.Assign): List<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun compileFunctionAllocation(e: Expr.Fun): List<String> {
        val ret = ArrayList<String>()
        ret.add("push ${e.captures.size + 1}")
        frame.push("<${e.captures.size + 1}>", false)
        //[...|size]

        //set current layout
        ret.add("layout ${frame.getLayoutString()}")

        //alloc function array
        //capture layout is indices of captures + 1 because function address is first element
        val captureLayout = (1..e.captures.size).map { ci -> ci.toString() }.toDelimitedString(", ")
        ret.add("alloc [$captureLayout]")
        frame.pop()
        frame.push("Fun($e)", true)
        //[...|fun]
        return ret
    }

    private fun compileFunctionWithoutAlloc(e: Expr.Fun): List<String> {
        val ret = ArrayList<String>()
        val bodyLabel = nextLabel()
        val contLabel = nextLabel()

        //store function address in fun[0]
        ret.add("push $bodyLabel")
        ret.add("rstore 0")
        //[...|[addr|...]]

        e.captures.forEachIndexed { i, c ->
            //compile capture reference
            ret.addAll(c.accept(this))
            ret.add("rstore ${i + 1}")
            frame.pop()
            ////[...|[addr|capt0|...|capti]]
        }

        //leave function object on stack here

        //jump over the function body
        ret.add("jump $contLabel")

        //code between bodyLabel and contLabel doesn't change stack here
        //here goes the body
        ret.add("$bodyLabel: //$e")
        //compile body (including 'enter', 'store' ret val, 'leave', and 'return')
        ret.addAll(compileFunctionBody(e.body))

        //continue program from above
        ret.add("$contLabel:")
        return ret
    }

    override fun visit(e: Expr.Fun): List<String> {
        val ret = ArrayList<String>()
        if (debug) ret.add("debugpush \"$e\"")

        ret.addAll(compileFunctionAllocation(e))

        ret.addAll(compileFunctionWithoutAlloc(e))

        if (debug) ret.add("debugpop")
        return ret
    }

    override fun visit(e: Expr.Fun.DataConstructor): List<String> {
        val ret = ArrayList<String>()
        if (debug) ret.add("debugpush \"$e\"")
        val bodyLabel = nextLabel()
        val contLabel = nextLabel()

        //push size of 1 for function address
        ret.add("push 1")
        frame.push("<1>", false)
        //set current layout
        ret.add("layout ${frame.getLayoutString()}")
        //alloc function array
        ret.add("alloc []")
        frame.pop()
        frame.push("<Fun($e)>", true)
        //[...|fun]

        //store function address in fun[0]
        ret.add("push $bodyLabel")
        ret.add("rstore 0")
        //[...|[addr]]

        //leave function object on stack here

        //jump over the function body
        ret.add("jump $contLabel")

        //here goes the body
        ret.add("$bodyLabel:")

        //create new frame
        ret.add("enter")
        val lastFrame = frame
        frame = StackFrame()

        //alloc object (+1 for instance number)
        ret.add("push ${e.params.size + 1}")
        frame.push("<${e.params.size + 1}>", false)
        ret.add("layout ${frame.getLayoutString()}")
        //everything but first element is ref
        ret.add("alloc [${(1..e.params.size).map { i -> i.toString() }.toDelimitedString(", ")}]")
        frame.pop()
        frame.push("<[*]>", true)

        //store index
        ret.add("push ${e.index}")
        ret.add("rstore 0")

        //store args
        e.params.forEachIndexed { index, _ ->
            ret.add("aload $index")
            ret.add("rstore ${index + 1}")
        }

        //store object for return
        ret.add("store")
        frame.pop()

        //leave frame
        ret.add("leave")
        frame = lastFrame

        //return
        ret.add("return")

        //continue program from above
        ret.add("$contLabel:")
        if (debug) ret.add("debugpop")
        return ret
    }

    override fun visit(e: Expr.Fun.DataPredicate): List<String> {
        val ret = ArrayList<String>()
        if (debug) ret.add("debugpush \"$e\"")
        val bodyLabel = nextLabel()
        val contLabel = nextLabel()

        //push size of 1 for function address
        ret.add("push 1")
        frame.push("<1>", false)
        //set current layout
        ret.add("layout ${frame.getLayoutString()}")
        //alloc function array
        ret.add("alloc []")
        frame.pop()
        frame.push("<Fun($e)>", true)
        //[...|fun]

        //store function address in fun[0]
        ret.add("push $bodyLabel")
        ret.add("rstore 0")
        //[...|[addr]]

        //leave function object on stack here

        //jump over the function body
        ret.add("jump $contLabel")

        //here goes the body
        ret.add("$bodyLabel:")

        //create new frame
        ret.add("enter")
        val lastFrame = frame
        frame = StackFrame()

        //create allocation for result
        ret.add("push 1")
        frame.push("<1>", false)
        ret.add("layout ${frame.getLayoutString()}")
        ret.add("alloc []")

        //load arg
        ret.add("aload 0")

        //load instance index
        ret.add("rload 0")

        //push index to check against
        ret.add("push ${e.index}")

        //do comparison
        ret.add("eq")

        //store in alloc
        ret.add("rstore 0")

        //store object for return
        ret.add("store")
        frame.pop()

        //leave frame
        ret.add("leave")
        frame = lastFrame

        //return
        ret.add("return")

        //continue program from above
        ret.add("$contLabel:")
        if (debug) ret.add("debugpop")
        return ret
    }

    override fun visit(e: Expr.Fun.DataAccessor): List<String> {
        val ret = ArrayList<String>()
        if (debug) ret.add("debugpush \"$e\"")
        val bodyLabel = nextLabel()
        val contLabel = nextLabel()

        //push size of 1 for function address
        ret.add("push 1")
        frame.push("<1>", false)
        //set current layout
        ret.add("layout ${frame.getLayoutString()}")
        //alloc function array
        ret.add("alloc []")
        frame.pop()
        frame.push("<Fun($e)>", true)
        //[...|fun]

        //store function address in fun[0]
        ret.add("push $bodyLabel")
        ret.add("rstore 0")
        //[...|[addr]]

        //leave function object on stack here

        //jump over the function body
        ret.add("jump $contLabel")

        //here goes the body
        ret.add("$bodyLabel:")

        //create new frame
        ret.add("enter")
        val lastFrame = frame
        frame = StackFrame()

        //load arg
        ret.add("aload 0")
        frame.push("<a0>", true)

        //get field at specified index
        ret.add("rload ${e.index + 1}")

        //store object for return
        ret.add("store")
        frame.pop()

        //leave frame
        ret.add("leave")
        frame = lastFrame

        //return
        ret.add("return")

        //continue program from above
        ret.add("$contLabel:")
        if (debug) ret.add("debugpop")
        return ret
    }

    override fun visit(e: Expr.CFun): List<String> {
        val ret = ArrayList<String>()
        if (debug) ret.add("debugpush \"$e\"")
        val bodyLabel = nextLabel()
        val contLabel = nextLabel()

        //push size of 1 for function address
        ret.add("push 1")
        frame.push("<1>", false)
        //set current layout
        ret.add("layout ${frame.getLayoutString()}")
        //alloc function array
        ret.add("alloc []")
        frame.pop()
        frame.push("<Fun($e)>", true)
        //[...|fun]

        //store function address in fun[0]
        ret.add("push $bodyLabel")
        ret.add("rstore 0")
        //[...|[addr]]

        //leave function object on stack here

        //jump over the function body
        ret.add("jump $contLabel")

        //here goes the body
        ret.add("$bodyLabel:")

        //create new frame
        ret.add("enter")
        val lastFrame = frame
        frame = StackFrame()

        //call native fun
        ret.add("ncall ${e.id}")
        frame.push("<${e.id}()>", true)

        //store single return value from cfun
        ret.add("store")
        frame.pop()

        //leave frame
        ret.add("leave")
        frame = lastFrame

        //return
        ret.add("return")

        //continue program from above
        ret.add("$contLabel:")
        if (debug) ret.add("debugpop")
        return ret
    }

    override fun visit(e: Expr.Let): List<String> {
        val ret = ArrayList<String>()
        if (debug) ret.add("debugpush \"$e\"")
        //[...]

        //add space for local
        ret.add("push 0")
        //get local index (adjusted for instance #) in which to store this value
        val localIndex = frame.push(e.binding.id, true)
        //[...|0]

        //compile the value expr
        ret.addAll(e.binding.value.accept(this))
        //[...|0|val]

        //store value in local
        ret.add("lstore $localIndex //${e.binding.id}")
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
        //[...|bodyVal]

        if (debug) ret.add("debugpop")
        return ret
    }

    override fun visit(e: Expr.Let.Rec): List<String> {
        val ret = ArrayList<String>()
        if (debug) ret.add("debugpush \"$e\"")
        //[...]

        val localIndices = ArrayList<Int>()

        //add space for locals
        e.bindings.forEach { binding ->
            ret.add("push 0")
            //get local index (adjusted for instance #) in which to store this value
            localIndices.add(frame.push(binding.id, true))
            //[...|0]
        }
        //[...|0|...|0]

        //compile the values (only function alloc if value is function)
        //this creates an array of appropriate size to hold the function
        //address+captures and stores it in its spot on the stack
        e.bindings.forEachIndexed { index, binding ->
            if (binding.value is Expr.Fun) {
                //compile the function alloc
                ret.addAll(compileFunctionAllocation(binding.value))
            } else {
                ret.addAll(binding.value.accept(this))
                //[...|0|...|0|val]
            }
            //store value in local
            ret.add("lstore ${localIndices[index]} //${binding.id}")
            frame.pop()
            //[...|val0|...|0]
        }
        //[...|val0|...|valn]

        //compile function bodies if value is function
        //this loads the uninitialized function from the stack
        //and compiles the rest of it (code address, captures)
        //and stores those things in the function object
        //the value on the stack is a pointer to the same object
        //that is pointed to by the local at localIndices[i] so it too
        //is updated with code address and captures
        e.bindings.forEachIndexed { index, binding ->
            if (binding.value is Expr.Fun) {
                //load fun alloc from local
                ret.add("lload ${localIndices[index]} //${binding.id}")
                frame.push("<${binding.value}>", true)
                //[...|fun]
                //compile rest of fun
                ret.addAll(compileFunctionWithoutAlloc(binding.value))
                //[...|fun]
                //fun alloc is already stored in local so can just remove here
                ret.add("pop")
                frame.pop()
            }
        }

        //compile body
        ret.addAll(e.body.accept(this))
        //[...|val0|...|valn|bodyVal]

        //store body value
        ret.add("store")
        frame.pop()
        //[...|val0|...|valn]

        //remove locals now we're done with it
        e.bindings.forEach {
            ret.add("pop")
            frame.pop()
            //[...|val0|...|valn-1]
        }
        //[...]

        //load body value
        ret.add("load")
        frame.push("<${e.body}>", true)
        //[...|bodyVal]

        if (debug) ret.add("debugpop")
        return ret
    }

    override fun visit(e: Expr.If): List<String> {
        val ret = ArrayList<String>()
        if (debug) ret.add("debugpush \"$e\"")
        val altLabel = nextLabel()
        val endLabel = nextLabel()
        //[...]

        //compile cond
        ret.addAll(e.cond.accept(this))
        ret.add("rload 0")
        frame.pop()
        frame.push("<${e.cond}>", false)
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
            frame.push("<()>", true)
        } else {
            ret.addAll(e.alt.accept(this))
        }
        //[...|alt]

        ret.add("$endLabel:")
        //[...|if (cond) csq else alt]

        if (debug) ret.add("debugpop")
        return ret
    }

}

