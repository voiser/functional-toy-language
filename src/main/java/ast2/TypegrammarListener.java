// Generated from grammar/Typegrammar.g4 by ANTLR 4.5.1
package ast2;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TypegrammarParser}.
 */
public interface TypegrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TypegrammarParser#ty}.
	 * @param ctx the parse tree
	 */
	void enterTy(TypegrammarParser.TyContext ctx);
	/**
	 * Exit a parse tree produced by {@link TypegrammarParser#ty}.
	 * @param ctx the parse tree
	 */
	void exitTy(TypegrammarParser.TyContext ctx);
	/**
	 * Enter a parse tree produced by {@link TypegrammarParser#ty2}.
	 * @param ctx the parse tree
	 */
	void enterTy2(TypegrammarParser.Ty2Context ctx);
	/**
	 * Exit a parse tree produced by {@link TypegrammarParser#ty2}.
	 * @param ctx the parse tree
	 */
	void exitTy2(TypegrammarParser.Ty2Context ctx);
	/**
	 * Enter a parse tree produced by {@link TypegrammarParser#simple}.
	 * @param ctx the parse tree
	 */
	void enterSimple(TypegrammarParser.SimpleContext ctx);
	/**
	 * Exit a parse tree produced by {@link TypegrammarParser#simple}.
	 * @param ctx the parse tree
	 */
	void exitSimple(TypegrammarParser.SimpleContext ctx);
	/**
	 * Enter a parse tree produced by {@link TypegrammarParser#fn}.
	 * @param ctx the parse tree
	 */
	void enterFn(TypegrammarParser.FnContext ctx);
	/**
	 * Exit a parse tree produced by {@link TypegrammarParser#fn}.
	 * @param ctx the parse tree
	 */
	void exitFn(TypegrammarParser.FnContext ctx);
	/**
	 * Enter a parse tree produced by {@link TypegrammarParser#left}.
	 * @param ctx the parse tree
	 */
	void enterLeft(TypegrammarParser.LeftContext ctx);
	/**
	 * Exit a parse tree produced by {@link TypegrammarParser#left}.
	 * @param ctx the parse tree
	 */
	void exitLeft(TypegrammarParser.LeftContext ctx);
}