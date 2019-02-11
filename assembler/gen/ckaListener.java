// Generated from C:/code/CopperKitten/assembler/grammar\cka.g4 by ANTLR 4.7.2
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
	 * Enter a parse tree produced by {@link ckaParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterInstruction(ckaParser.InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ckaParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitInstruction(ckaParser.InstructionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ckaParser#frameLayout}.
	 * @param ctx the parse tree
	 */
	void enterFrameLayout(ckaParser.FrameLayoutContext ctx);
	/**
	 * Exit a parse tree produced by {@link ckaParser#frameLayout}.
	 * @param ctx the parse tree
	 */
	void exitFrameLayout(ckaParser.FrameLayoutContext ctx);
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
}