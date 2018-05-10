// Generated from /Users/colinholzman/Documents/CopperKitten/CKAssembler/src/ckasm.g4 by ANTLR 4.7
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
	 * Visit a parse tree produced by the {@code mnemonicInstruction}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMnemonicInstruction(ckasmParser.MnemonicInstructionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intArgInstruction}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntArgInstruction(ckasmParser.IntArgInstructionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code labelArgInstruction}
	 * labeled alternative in {@link ckasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabelArgInstruction(ckasmParser.LabelArgInstructionContext ctx);
}