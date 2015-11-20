// Generated from grammar/Typegrammar.g4 by ANTLR 4.5.1
package ast2;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TypegrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TypegrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TypegrammarParser#ty}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTy(TypegrammarParser.TyContext ctx);
	/**
	 * Visit a parse tree produced by {@link TypegrammarParser#ty2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTy2(TypegrammarParser.Ty2Context ctx);
	/**
	 * Visit a parse tree produced by {@link TypegrammarParser#simple}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple(TypegrammarParser.SimpleContext ctx);
	/**
	 * Visit a parse tree produced by {@link TypegrammarParser#fn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFn(TypegrammarParser.FnContext ctx);
	/**
	 * Visit a parse tree produced by {@link TypegrammarParser#left}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLeft(TypegrammarParser.LeftContext ctx);
}