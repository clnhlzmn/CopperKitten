// Generated from C:/code/ck/compiler/ckc/grammar\ck.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ckParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ckVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ckParser#file}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFile(ckParser.FileContext ctx);
	/**
	 * Visit a parse tree produced by {@link ckParser#decls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecls(ckParser.DeclsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ckParser#decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl(ckParser.DeclContext ctx);
	/**
	 * Visit a parse tree produced by the {@code applyExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitApplyExpr(ckParser.ApplyExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunExpr(ckParser.FunExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code condExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondExpr(ckParser.CondExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code orExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpr(ckParser.OrExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sequenceExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSequenceExpr(ckParser.SequenceExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code breakExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakExpr(ckParser.BreakExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultExpr(ckParser.MultExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code letExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetExpr(ckParser.LetExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bitXorExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitXorExpr(ckParser.BitXorExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code shiftExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShiftExpr(ckParser.ShiftExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bitOrExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitOrExpr(ckParser.BitOrExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unaryExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpr(ckParser.UnaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code whileExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileExpr(ckParser.WhileExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddExpr(ckParser.AddExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code refExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRefExpr(ckParser.RefExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cfunExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCfunExpr(ckParser.CfunExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfExpr(ckParser.IfExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code naturalExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNaturalExpr(ckParser.NaturalExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code relExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelExpr(ckParser.RelExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bitAndExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitAndExpr(ckParser.BitAndExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignExpr(ckParser.AssignExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unitExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnitExpr(ckParser.UnitExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code equalityExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityExpr(ckParser.EqualityExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code andExpr}
	 * labeled alternative in {@link ckParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpr(ckParser.AndExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ckParser#args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgs(ckParser.ArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ckParser#sequence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSequence(ckParser.SequenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link ckParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(ckParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link ckParser#params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParams(ckParser.ParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ckParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(ckParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ckParser#simpleType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleType(ckParser.SimpleTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ckParser#funType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunType(ckParser.FunTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ckParser#types}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypes(ckParser.TypesContext ctx);
}