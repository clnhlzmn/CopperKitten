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

    override fun visitLiteralLabelInst(ctx: ckaParser.LiteralLabelInstContext?) {
        pc.instructions.add(LiteralLabelInstruction(ctx!!.literalLabelMnemonic().text, ctx.LABEL().text))
    }

    override fun visitLayoutInst(ctx: ckaParser.LayoutInstContext?) {
        pc.instructions.add(LayoutInstruction("layout", FrameLayoutVisitor().visit(ctx!!.frameLayout())))
    }

    override fun visitAllocInst(ctx: ckaParser.AllocInstContext?) {
        pc.instructions.add(LayoutInstruction("alloc", AllocLayoutVisitor().visit(ctx!!.allocLayout())))
    }

}

open class FrameLayoutVisitor : ckaBaseVisitor<LayoutFunction>() {
    override fun visitEmptyFrameLayout(ctx: ckaParser.EmptyFrameLayoutContext?): LayoutFunction {
        return CustomLayoutFunction(ArrayList())
    }

    override fun visitNonEmptyFrameLayout(ctx: ckaParser.NonEmptyFrameLayoutContext?): LayoutFunction {
        return CustomLayoutFunction(ctx!!.NATURAL().map { node -> node.text.toInt() })
    }
}

class AllocLayoutVisitor : FrameLayoutVisitor() {
    override fun visitRefArrayLayout(ctx: ckaParser.RefArrayLayoutContext?): LayoutFunction {
        return RefArrayLayoutFunction
    }

    override fun visitEmptyCustomLayout(ctx: ckaParser.EmptyCustomLayoutContext?): LayoutFunction {
        return CustomLayoutFunction(ArrayList())
    }

    override fun visitCustomLayout(ctx: ckaParser.CustomLayoutContext?): LayoutFunction {
        return CustomLayoutFunction(ctx!!.NATURAL().map { n -> n.text.toInt() })
    }
}