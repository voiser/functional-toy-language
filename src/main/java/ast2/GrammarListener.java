// Generated from src/main/antlr/Grammar.g4 by ANTLR 4.5.1
package ast2;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GrammarParser}.
 */
public interface GrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GrammarParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(GrammarParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(GrammarParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#imp}.
	 * @param ctx the parse tree
	 */
	void enterImp(GrammarParser.ImpContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#imp}.
	 * @param ctx the parse tree
	 */
	void exitImp(GrammarParser.ImpContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(GrammarParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(GrammarParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(GrammarParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(GrammarParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(GrammarParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(GrammarParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#fn}.
	 * @param ctx the parse tree
	 */
	void enterFn(GrammarParser.FnContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#fn}.
	 * @param ctx the parse tree
	 */
	void exitFn(GrammarParser.FnContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#fnargpair}.
	 * @param ctx the parse tree
	 */
	void enterFnargpair(GrammarParser.FnargpairContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#fnargpair}.
	 * @param ctx the parse tree
	 */
	void exitFnargpair(GrammarParser.FnargpairContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#apply}.
	 * @param ctx the parse tree
	 */
	void enterApply(GrammarParser.ApplyContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#apply}.
	 * @param ctx the parse tree
	 */
	void exitApply(GrammarParser.ApplyContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#ref}.
	 * @param ctx the parse tree
	 */
	void enterRef(GrammarParser.RefContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#ref}.
	 * @param ctx the parse tree
	 */
	void exitRef(GrammarParser.RefContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCond(GrammarParser.CondContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCond(GrammarParser.CondContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#forward}.
	 * @param ctx the parse tree
	 */
	void enterForward(GrammarParser.ForwardContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#forward}.
	 * @param ctx the parse tree
	 */
	void exitForward(GrammarParser.ForwardContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#tydef}.
	 * @param ctx the parse tree
	 */
	void enterTydef(GrammarParser.TydefContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#tydef}.
	 * @param ctx the parse tree
	 */
	void exitTydef(GrammarParser.TydefContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#list}.
	 * @param ctx the parse tree
	 */
	void enterList(GrammarParser.ListContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#list}.
	 * @param ctx the parse tree
	 */
	void exitList(GrammarParser.ListContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#map}.
	 * @param ctx the parse tree
	 */
	void enterMap(GrammarParser.MapContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#map}.
	 * @param ctx the parse tree
	 */
	void exitMap(GrammarParser.MapContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#mappair}.
	 * @param ctx the parse tree
	 */
	void enterMappair(GrammarParser.MappairContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#mappair}.
	 * @param ctx the parse tree
	 */
	void exitMappair(GrammarParser.MappairContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#klass}.
	 * @param ctx the parse tree
	 */
	void enterKlass(GrammarParser.KlassContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#klass}.
	 * @param ctx the parse tree
	 */
	void exitKlass(GrammarParser.KlassContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#klassvar}.
	 * @param ctx the parse tree
	 */
	void enterKlassvar(GrammarParser.KlassvarContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#klassvar}.
	 * @param ctx the parse tree
	 */
	void exitKlassvar(GrammarParser.KlassvarContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#klassparent}.
	 * @param ctx the parse tree
	 */
	void enterKlassparent(GrammarParser.KlassparentContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#klassparent}.
	 * @param ctx the parse tree
	 */
	void exitKlassparent(GrammarParser.KlassparentContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#instantiation}.
	 * @param ctx the parse tree
	 */
	void enterInstantiation(GrammarParser.InstantiationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#instantiation}.
	 * @param ctx the parse tree
	 */
	void exitInstantiation(GrammarParser.InstantiationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#match}.
	 * @param ctx the parse tree
	 */
	void enterMatch(GrammarParser.MatchContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#match}.
	 * @param ctx the parse tree
	 */
	void exitMatch(GrammarParser.MatchContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#matchexp}.
	 * @param ctx the parse tree
	 */
	void enterMatchexp(GrammarParser.MatchexpContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#matchexp}.
	 * @param ctx the parse tree
	 */
	void exitMatchexp(GrammarParser.MatchexpContext ctx);
}