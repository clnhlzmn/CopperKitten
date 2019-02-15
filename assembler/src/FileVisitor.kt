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

    override fun visitPushIntInst(ctx: ckaParser.PushIntInstContext?) {
        pc.instructions.add(PushIntInstruction(ctx!!.integer().text.toInt()))
    }

    override fun visitPushLabelInst(ctx: ckaParser.PushLabelInstContext?) {
        pc.instructions.add(LiteralLabelInstruction("push", ctx!!.LABEL().text))
    }

    override fun visitSimpleInst(ctx: ckaParser.SimpleInstContext?) {
        pc.instructions.add(SimpleInstruction(ctx!!.simpleInstruction().text))
    }

    override fun visitJumpInst(ctx: ckaParser.JumpInstContext?) {
        pc.instructions.add(LiteralLabelInstruction(ctx!!.jumpMnemonic().text, ctx.LABEL().text))
    }

    override fun visitLayoutInst(ctx: ckaParser.LayoutInstContext?) {
        pc.instructions.add(LayoutInstruction(FrameLayoutVisitor().visit(ctx!!.frameLayout())))
    }

    override fun visitAllocInst(ctx: ckaParser.AllocInstContext?) {
        pc.instructions.add(AllocInstruction(AllocLayoutVisitor().visit(ctx!!.allocLayout())))
    }

}

class FrameLayoutVisitor : ckaBaseVisitor<List<Int>>() {
    override fun visitEmptyFrameLayout(ctx: ckaParser.EmptyFrameLayoutContext?): List<Int> {
        return ArrayList()
    }

    override fun visitNonEmptyFrameLayout(ctx: ckaParser.NonEmptyFrameLayoutContext?): List<Int> {
        return ctx!!.NATURAL().map { node -> node.text.toInt() }
    }
}

class AllocLayoutVisitor : ckaBaseVisitor<AllocLayout>() {
    override fun visitRefArrayLayout(ctx: ckaParser.RefArrayLayoutContext?): AllocLayout {
        return RefArrayLayout(ctx!!.NATURAL().text.toInt())
    }

    override fun visitCustomLayout(ctx: ckaParser.CustomLayoutContext?): AllocLayout {
        return CustomLayout(
            ctx!!.NATURAL().first().text.toInt(),
            ctx.NATURAL().subList(1, ctx.NATURAL().size).map { node -> node.text.toInt() }
        )
    }
}