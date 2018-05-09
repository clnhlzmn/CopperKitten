// Generated from C:/code/CopperKitten/CKAssembler/src\ckasm.g4 by ANTLR 4.7
package com.cph;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ckasmParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ckasmVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ckasmParser#file}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFile(ckasmParser.FileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code label}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabel(ckasmParser.LabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code add}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd(ckasmParser.AddContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addInteger}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddInteger(ckasmParser.AddIntegerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sub}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSub(ckasmParser.SubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subInteger}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubInteger(ckasmParser.SubIntegerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mul}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMul(ckasmParser.MulContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mulInteger}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulInteger(ckasmParser.MulIntegerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code div}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDiv(ckasmParser.DivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code divInteger}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivInteger(ckasmParser.DivIntegerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mod}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMod(ckasmParser.ModContext ctx);
	/**
	 * Visit a parse tree produced by the {@code modInteger}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModInteger(ckasmParser.ModIntegerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cmp}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmp(ckasmParser.CmpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cmpInteger}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmpInteger(ckasmParser.CmpIntegerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code call}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall(ckasmParser.CallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callLabel}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallLabel(ckasmParser.CallLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code return}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn(ckasmParser.ReturnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code jump}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJump(ckasmParser.JumpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code jumpz}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJumpz(ckasmParser.JumpzContext ctx);
	/**
	 * Visit a parse tree produced by the {@code jumpo}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJumpo(ckasmParser.JumpoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code jumpoz}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJumpoz(ckasmParser.JumpozContext ctx);
	/**
	 * Visit a parse tree produced by the {@code jumpLabel}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJumpLabel(ckasmParser.JumpLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code jumpzLabel}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJumpzLabel(ckasmParser.JumpzLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code push}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPush(ckasmParser.PushContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pushw}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPushw(ckasmParser.PushwContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dup}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDup(ckasmParser.DupContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pop}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPop(ckasmParser.PopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code swap}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwap(ckasmParser.SwapContext ctx);
	/**
	 * Visit a parse tree produced by the {@code halt}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHalt(ckasmParser.HaltContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pushref}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPushref(ckasmParser.PushrefContext ctx);
	/**
	 * Visit a parse tree produced by the {@code popref}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPopref(ckasmParser.PoprefContext ctx);
	/**
	 * Visit a parse tree produced by the {@code enter}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnter(ckasmParser.EnterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code leave}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLeave(ckasmParser.LeaveContext ctx);
	/**
	 * Visit a parse tree produced by the {@code in}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIn(ckasmParser.InContext ctx);
	/**
	 * Visit a parse tree produced by the {@code out}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOut(ckasmParser.OutContext ctx);
	/**
	 * Visit a parse tree produced by the {@code outInteger}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOutInteger(ckasmParser.OutIntegerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alloc}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlloc(ckasmParser.AllocContext ctx);
	/**
	 * Visit a parse tree produced by the {@code allocInteger}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAllocInteger(ckasmParser.AllocIntegerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code allocIntInt}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAllocIntInt(ckasmParser.AllocIntIntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code refget}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRefget(ckasmParser.RefgetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code refgetInteger}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRefgetInteger(ckasmParser.RefgetIntegerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code refset}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRefset(ckasmParser.RefsetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code refsetInteger}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRefsetInteger(ckasmParser.RefsetIntegerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code refsetIntInt}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRefsetIntInt(ckasmParser.RefsetIntIntContext ctx);
}