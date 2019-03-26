// Generated from C:/code/CopperKitten/compiler/cka/grammar\cka.g4 by ANTLR 4.7.2
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
	 * Visit a parse tree produced by the {@code simpleInst}
	 * labeled alternative in {@link ckaParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleInst(ckaParser.SimpleInstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code literalIntInst}
	 * labeled alternative in {@link ckaParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralIntInst(ckaParser.LiteralIntInstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code literalLabelInst}
	 * labeled alternative in {@link ckaParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralLabelInst(ckaParser.LiteralLabelInstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code literalStringInst}
	 * labeled alternative in {@link ckaParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralStringInst(ckaParser.LiteralStringInstContext ctx);
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
	 * Visit a parse tree produced by the {@code ncallInst}
	 * labeled alternative in {@link ckaParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNcallInst(ckaParser.NcallInstContext ctx);
	/**
	 * Visit a parse tree produced by {@link ckaParser#literalLabelMnemonic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralLabelMnemonic(ckaParser.LiteralLabelMnemonicContext ctx);
	/**
	 * Visit a parse tree produced by {@link ckaParser#literalIntMnemonic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralIntMnemonic(ckaParser.LiteralIntMnemonicContext ctx);
	/**
	 * Visit a parse tree produced by {@link ckaParser#literalStringMnemonic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralStringMnemonic(ckaParser.LiteralStringMnemonicContext ctx);
	/**
	 * Visit a parse tree produced by {@link ckaParser#simpleInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleInstruction(ckaParser.SimpleInstructionContext ctx);
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
	 * Visit a parse tree produced by the {@code emptyCustomLayout}
	 * labeled alternative in {@link ckaParser#allocLayout}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyCustomLayout(ckaParser.EmptyCustomLayoutContext ctx);
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