// Generated from C:/code/ck/compiler/grammar\ck.g4 by ANTLR 4.7.2
package compiler;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ckParser}.
 */
public interface ckListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ckParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(ckParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link ckParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(ckParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link ckParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(ckParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ckParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(ckParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ckParser#statements}.
	 * @param ctx the parse tree
	 */
	void enterStatements(ckParser.StatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ckParser#statements}.
	 * @param ctx the parse tree
	 */
	void exitStatements(ckParser.StatementsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code applyExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterApplyExpr(ckParser.ApplyExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code applyExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitApplyExpr(ckParser.ApplyExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFunExpr(ckParser.FunExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFunExpr(ckParser.FunExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code condExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCondExpr(ckParser.CondExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code condExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCondExpr(ckParser.CondExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code orExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr(ckParser.OrExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code orExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr(ckParser.OrExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code seqExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSeqExpr(ckParser.SeqExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code seqExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSeqExpr(ckParser.SeqExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSubExpr(ckParser.SubExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSubExpr(ckParser.SubExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMultExpr(ckParser.MultExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMultExpr(ckParser.MultExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code letExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLetExpr(ckParser.LetExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code letExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLetExpr(ckParser.LetExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bitXorExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBitXorExpr(ckParser.BitXorExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bitXorExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBitXorExpr(ckParser.BitXorExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code shiftExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterShiftExpr(ckParser.ShiftExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code shiftExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitShiftExpr(ckParser.ShiftExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bitOrExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBitOrExpr(ckParser.BitOrExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bitOrExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBitOrExpr(ckParser.BitOrExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpr(ckParser.UnaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpr(ckParser.UnaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddExpr(ckParser.AddExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddExpr(ckParser.AddExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code refExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterRefExpr(ckParser.RefExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code refExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitRefExpr(ckParser.RefExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code naturalExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNaturalExpr(ckParser.NaturalExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code naturalExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNaturalExpr(ckParser.NaturalExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code relExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterRelExpr(ckParser.RelExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code relExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitRelExpr(ckParser.RelExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bitAndExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBitAndExpr(ckParser.BitAndExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bitAndExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBitAndExpr(ckParser.BitAndExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAssignExpr(ckParser.AssignExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAssignExpr(ckParser.AssignExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code equalityExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpr(ckParser.EqualityExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code equalityExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpr(ckParser.EqualityExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(ckParser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(ckParser.AndExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ckParser#exprs}.
	 * @param ctx the parse tree
	 */
	void enterExprs(ckParser.ExprsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ckParser#exprs}.
	 * @param ctx the parse tree
	 */
	void exitExprs(ckParser.ExprsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ckParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(ckParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link ckParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(ckParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link ckParser#params}.
	 * @param ctx the parse tree
	 */
	void enterParams(ckParser.ParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ckParser#params}.
	 * @param ctx the parse tree
	 */
	void exitParams(ckParser.ParamsContext ctx);
}