class FileVisitor : ckaBaseVisitor<ParseContext>() {

    override fun visitFile(ctx: ckaParser.FileContext?): ParseContext {
        return InstructionsVisitor().visit(ctx!!.instructions())
    }

}

class InstructionsVisitor : ckaBaseVisitor<ParseContext>() {
    override fun visitInstructions(ctx: ckaParser.InstructionsContext?): ParseContext {
        val pc = ParseContext()
        for (inst in ctx!!.instruction()) {
            InstructionVisitor(pc).visit(inst)
        }
        return pc
    }
}

class InstructionVisitor(val pc: ParseContext) : ckaBaseVisitor<Unit>() {

    override fun visitLabelInst(ctx: ckaParser.LabelInstContext?) {
        pc.labels[ctx!!.LABEL().text] = pc.instructions.count()
    }

    override fun visitSimpleInst(ctx: ckaParser.SimpleInstContext?) {
        pc.instructions.add(SimpleInstruction(ctx!!.simpleInstruction().text))
    }

    override fun visitLiteralIntInst(ctx: ckaParser.LiteralIntInstContext?) {
        pc.instructions.add(LiteralIntInstruction(ctx!!.literalIntMnemonic().text, ctx.integer().text.toLong()))
    }

    override fun visitLiteralStringInst(ctx: ckaParser.LiteralStringInstContext?) {
        pc.instructions.add(LiteralStringInstruction(ctx!!.literalStringMnemonic().text, ctx.STRING().text.trim{c -> c == '"'}))
    }

    override fun visitLiteralLabelInst(ctx: ckaParser.LiteralLabelInstContext?) {
        pc.instructions.add(LiteralLabelInstruction(ctx!!.literalLabelMnemonic().text, ctx.LABEL().text))
    }

    override fun visitLayoutInst(ctx: ckaParser.LayoutInstContext?) {
        pc.instructions.add(LayoutInstruction("layout", FrameLayoutVisitor().visit(ctx!!.frameLayout())))
    }

    override fun visitAllocInst(ctx: ckaParser.AllocInstContext?) {
        pc.instructions.add(LayoutInstruction("alloc", AllocLayoutVisitor().visit(ctx!!.allocLayout())))
    }

    override fun visitNcallInst(ctx: ckaParser.NcallInstContext?) {
        pc.instructions.add(NCallInstruction(ctx!!.ID().text))
    }

}

open class FrameLayoutVisitor : ckaBaseVisitor<Function>() {
    override fun visitEmptyFrameLayout(ctx: ckaParser.EmptyFrameLayoutContext?): Function {
        return CustomLayoutFunction(ArrayList())
    }

    override fun visitNonEmptyFrameLayout(ctx: ckaParser.NonEmptyFrameLayoutContext?): Function {
        return CustomLayoutFunction(ctx!!.NATURAL().map { node -> node.text.toInt() })
    }
}

class AllocLayoutVisitor : FrameLayoutVisitor() {
    override fun visitRefArrayLayout(ctx: ckaParser.RefArrayLayoutContext?): Function {
        return RefArrayLayoutFunction
    }

    override fun visitEmptyCustomLayout(ctx: ckaParser.EmptyCustomLayoutContext?): Function {
        return CustomLayoutFunction(ArrayList())
    }

    override fun visitCustomLayout(ctx: ckaParser.CustomLayoutContext?): Function {
        return CustomLayoutFunction(ctx!!.NATURAL().map { n -> n.text.toInt() })
    }
}