// Generated from C:/code/ck/assembler/grammar\cka.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ckaParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ckaVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ckaParser#file}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFile(ckaParser.FileContext ctx);
	/**
	 * Visit a parse tree produced by {@link ckaParser#instructions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstructions(ckaParser.InstructionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ckaParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruction(ckaParser.InstructionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ckaParser#frameLayout}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFrameLayout(ckaParser.FrameLayoutContext ctx);
	/**
	 * Visit a parse tree produced by the {@code refArrayLayout}
	 * labeled alternative in {@link ckaParser#allocLayout}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRefArrayLayout(ckaParser.RefArrayLayoutContext ctx);
	/**
	 * Visit a parse tree produced by the {@code customLayout}
	 * labeled alternative in {@link ckaParser#allocLayout}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCustomLayout(ckaParser.CustomLayoutContext ctx);
}