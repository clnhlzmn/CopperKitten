

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

    override fun visitIntInst(ctx: ckaParser.IntInstContext?) {
        pc.instructions.add(PushInstruction(ctx!!.integer().text.toInt()))
    }

    override fun visitSimpleInst(ctx: ckaParser.SimpleInstContext?) {
        pc.instructions.add(SimpleInstruction(ctx!!.ID().text))
    }

    override fun visitJumpInst(ctx: ckaParser.JumpInstContext?) {
        pc.instructions.add(JumpInstruction(ctx!!.ID().text, ctx.LABEL().text))
    }

    override fun visitPushInst(ctx: ckaParser.PushInstContext?) {
        pc.instructions.add(PushInstruction(ctx!!.integer().text.toInt()))
    }

    override fun visitLayoutInst(ctx: ckaParser.LayoutInstContext?) {
        //TODO create frame layout visitor and use it to construct the frame layout for
        pc.instructions.add(LayoutInstruction(/*TODO:parse frame layout*/))
    }

    //TODO: visit alloc inst

}