// Generated from C:/code/CopperKitten/compiler/cka/grammar\cka.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ckaParser}.
 */
public interface ckaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ckaParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(ckaParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link ckaParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(ckaParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link ckaParser#instructions}.
	 * @param ctx the parse tree
	 */
	void enterInstructions(ckaParser.InstructionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ckaParser#instructions}.
	 * @param ctx the parse tree
	 */
	void exitInstructions(ckaParser.InstructionsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code labelInst}
	 * labeled alternative in {@link ckaParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterLabelInst(ckaParser.LabelInstContext ctx);
	/**
	 * Exit a parse tree produced by the {@code labelInst}
	 * labeled alternative in {@link ckaParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitLabelInst(ckaParser.LabelInstContext ctx);
	/**
	 * Enter a parse tree produced by the {@code simpleInst}
	 * labeled alternative in {@link ckaParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterSimpleInst(ckaParser.SimpleInstContext ctx);
	/**
	 * Exit a parse tree produced by the {@code simpleInst}
	 * labeled alternative in {@link ckaParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitSimpleInst(ckaParser.SimpleInstContext ctx);
	/**
	 * Enter a parse tree produced by the {@code literalIntInst}
	 * labeled alternative in {@link ckaParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterLiteralIntInst(ckaParser.LiteralIntInstContext ctx);
	/**
	 * Exit a parse tree produced by the {@code literalIntInst}
	 * labeled alternative in {@link ckaParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitLiteralIntInst(ckaParser.LiteralIntInstContext ctx);
	/**
	 * Enter a parse tree produced by the {@code literalLabelInst}
	 * labeled alternative in {@link ckaParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterLiteralLabelInst(ckaParser.LiteralLabelInstContext ctx);
	/**
	 * Exit a parse tree produced by the {@code literalLabelInst}
	 * labeled alternative in {@link ckaParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitLiteralLabelInst(ckaParser.LiteralLabelInstContext ctx);
	/**
	 * Enter a parse tree produced by the {@code layoutInst}
	 * labeled alternative in {@link ckaParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterLayoutInst(ckaParser.LayoutInstContext ctx);
	/**
	 * Exit a parse tree produced by the {@code layoutInst}
	 * labeled alternative in {@link ckaParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitLayoutInst(ckaParser.LayoutInstContext ctx);
	/**
	 * Enter a parse tree produced by the {@code allocInst}
	 * labeled alternative in {@link ckaParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterAllocInst(ckaParser.AllocInstContext ctx);
	/**
	 * Exit a parse tree produced by the {@code allocInst}
	 * labeled alternative in {@link ckaParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitAllocInst(ckaParser.AllocInstContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ncallInst}
	 * labeled alternative in {@link ckaParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterNcallInst(ckaParser.NcallInstContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ncallInst}
	 * labeled alternative in {@link ckaParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitNcallInst(ckaParser.NcallInstContext ctx);
	/**
	 * Enter a parse tree produced by {@link ckaParser#literalLabelMnemonic}.
	 * @param ctx the parse tree
	 */
	void enterLiteralLabelMnemonic(ckaParser.LiteralLabelMnemonicContext ctx);
	/**
	 * Exit a parse tree produced by {@link ckaParser#literalLabelMnemonic}.
	 * @param ctx the parse tree
	 */
	void exitLiteralLabelMnemonic(ckaParser.LiteralLabelMnemonicContext ctx);
	/**
	 * Enter a parse tree produced by {@link ckaParser#literalIntMnemonic}.
	 * @param ctx the parse tree
	 */
	void enterLiteralIntMnemonic(ckaParser.LiteralIntMnemonicContext ctx);
	/**
	 * Exit a parse tree produced by {@link ckaParser#literalIntMnemonic}.
	 * @param ctx the parse tree
	 */
	void exitLiteralIntMnemonic(ckaParser.LiteralIntMnemonicContext ctx);
	/**
	 * Enter a parse tree produced by {@link ckaParser#simpleInstruction}.
	 * @param ctx the parse tree
	 */
	void enterSimpleInstruction(ckaParser.SimpleInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ckaParser#simpleInstruction}.
	 * @param ctx the parse tree
	 */
	void exitSimpleInstruction(ckaParser.SimpleInstructionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code emptyFrameLayout}
	 * labeled alternative in {@link ckaParser#frameLayout}.
	 * @param ctx the parse tree
	 */
	void enterEmptyFrameLayout(ckaParser.EmptyFrameLayoutContext ctx);
	/**
	 * Exit a parse tree produced by the {@code emptyFrameLayout}
	 * labeled alternative in {@link ckaParser#frameLayout}.
	 * @param ctx the parse tree
	 */
	void exitEmptyFrameLayout(ckaParser.EmptyFrameLayoutContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nonEmptyFrameLayout}
	 * labeled alternative in {@link ckaParser#frameLayout}.
	 * @param ctx the parse tree
	 */
	void enterNonEmptyFrameLayout(ckaParser.NonEmptyFrameLayoutContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nonEmptyFrameLayout}
	 * labeled alternative in {@link ckaParser#frameLayout}.
	 * @param ctx the parse tree
	 */
	void exitNonEmptyFrameLayout(ckaParser.NonEmptyFrameLayoutContext ctx);
	/**
	 * Enter a parse tree produced by the {@code refArrayLayout}
	 * labeled alternative in {@link ckaParser#allocLayout}.
	 * @param ctx the parse tree
	 */
	void enterRefArrayLayout(ckaParser.RefArrayLayoutContext ctx);
	/**
	 * Exit a parse tree produced by the {@code refArrayLayout}
	 * labeled alternative in {@link ckaParser#allocLayout}.
	 * @param ctx the parse tree
	 */
	void exitRefArrayLayout(ckaParser.RefArrayLayoutContext ctx);
	/**
	 * Enter a parse tree produced by the {@code emptyCustomLayout}
	 * labeled alternative in {@link ckaParser#allocLayout}.
	 * @param ctx the parse tree
	 */
	void enterEmptyCustomLayout(ckaParser.EmptyCustomLayoutContext ctx);
	/**
	 * Exit a parse tree produced by the {@code emptyCustomLayout}
	 * labeled alternative in {@link ckaParser#allocLayout}.
	 * @param ctx the parse tree
	 */
	void exitEmptyCustomLayout(ckaParser.EmptyCustomLayoutContext ctx);
	/**
	 * Enter a parse tree produced by the {@code customLayout}
	 * labeled alternative in {@link ckaParser#allocLayout}.
	 * @param ctx the parse tree
	 */
	void enterCustomLayout(ckaParser.CustomLayoutContext ctx);
	/**
	 * Exit a parse tree produced by the {@code customLayout}
	 * labeled alternative in {@link ckaParser#allocLayout}.
	 * @param ctx the parse tree
	 */
	void exitCustomLayout(ckaParser.CustomLayoutContext ctx);
	/**
	 * Enter a parse tree produced by {@link ckaParser#integer}.
	 * @param ctx the parse tree
	 */
	void enterInteger(ckaParser.IntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link ckaParser#integer}.
	 * @param ctx the parse tree
	 */
	void exitInteger(ckaParser.IntegerContext ctx);
}