// Generated from /Users/colinholzman/Documents/CopperKitten/assembler/grammar/cka.g4 by ANTLR 4.7
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
	 * Visit a parse tree produced by the {@code labelInst}
	 * labeled alternative in {@link ckaParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabelInst(ckaParser.LabelInstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intInst}
	 * labeled alternative in {@link ckaParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntInst(ckaParser.IntInstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simpleInst}
	 * labeled alternative in {@link ckaParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleInst(ckaParser.SimpleInstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code jumpInst}
	 * labeled alternative in {@link ckaParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJumpInst(ckaParser.JumpInstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pushInst}
	 * labeled alternative in {@link ckaParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPushInst(ckaParser.PushInstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code layoutInst}
	 * labeled alternative in {@link ckaParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLayoutInst(ckaParser.LayoutInstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code allocInst}
	 * labeled alternative in {@link ckaParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAllocInst(ckaParser.AllocInstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code emptyFrameLayout}
	 * labeled alternative in {@link ckaParser#frameLayout}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyFrameLayout(ckaParser.EmptyFrameLayoutContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nonEmptyFrameLayout}
	 * labeled alternative in {@link ckaParser#frameLayout}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNonEmptyFrameLayout(ckaParser.NonEmptyFrameLayoutContext ctx);
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
	/**
	 * Visit a parse tree produced by {@link ckaParser#integer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteger(ckaParser.IntegerContext ctx);
}