// Generated from src/main/antlr/Grammar.g4 by ANTLR 4.5.1
package ast2;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, ID=30, CLASSID=31, INTEGER=32, 
		FLOAT=33, STRING=34, WS=35, COMMENT=36, COMMENT2=37;
	public static final int
		RULE_file = 0, RULE_imp = 1, RULE_block = 2, RULE_expression = 3, RULE_value = 4, 
		RULE_fn = 5, RULE_fnargpair = 6, RULE_apply = 7, RULE_anonapply = 8, RULE_ref = 9, 
		RULE_cond = 10, RULE_forward = 11, RULE_tydef = 12, RULE_list = 13, RULE_map = 14, 
		RULE_mappair = 15, RULE_klass = 16, RULE_klassvar = 17, RULE_klassparent = 18, 
		RULE_instantiation = 19, RULE_interf = 20, RULE_match = 21, RULE_matchexp = 22;
	public static final String[] ruleNames = {
		"file", "imp", "block", "expression", "value", "fn", "fnargpair", "apply", 
		"anonapply", "ref", "cond", "forward", "tydef", "list", "map", "mappair", 
		"klass", "klassvar", "klassparent", "instantiation", "interf", "match", 
		"matchexp"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'module'", "'import'", "'.'", "'('", "')'", "'=='", "'!='", "'*'", 
		"'/'", "'+'", "'-'", "'='", "','", "'false'", "'true'", "'{'", "'}'", 
		"'=>'", "'if'", "'then'", "'else'", "'native'", "':'", "'['", "']'", "'->'", 
		"'class'", "'is'", "'interface'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, "ID", "CLASSID", "INTEGER", "FLOAT", 
		"STRING", "WS", "COMMENT", "COMMENT2"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Grammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public GrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class FileContext extends ParserRuleContext {
		public Token name;
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode ID() { return getToken(GrammarParser.ID, 0); }
		public List<ImpContext> imp() {
			return getRuleContexts(ImpContext.class);
		}
		public ImpContext imp(int i) {
			return getRuleContext(ImpContext.class,i);
		}
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitFile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitFile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_file);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			match(T__0);
			setState(47);
			((FileContext)_localctx).name = match(ID);
			setState(51);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(48);
				imp();
				}
				}
				setState(53);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(54);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ImpContext extends ParserRuleContext {
		public Token what;
		public List<TerminalNode> ID() { return getTokens(GrammarParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(GrammarParser.ID, i);
		}
		public TerminalNode CLASSID() { return getToken(GrammarParser.CLASSID, 0); }
		public ImpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_imp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterImp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitImp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitImp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImpContext imp() throws RecognitionException {
		ImpContext _localctx = new ImpContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_imp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			match(T__1);
			setState(57);
			match(ID);
			setState(60);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(58);
				match(T__2);
				setState(59);
				match(ID);
				}
				break;
			}
			setState(62);
			match(T__2);
			setState(63);
			((ImpContext)_localctx).what = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==CLASSID) ) {
				((ImpContext)_localctx).what = (Token)_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ForwardContext> forward() {
			return getRuleContexts(ForwardContext.class);
		}
		public ForwardContext forward(int i) {
			return getRuleContext(ForwardContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(67);
				switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
				case 1:
					{
					setState(65);
					expression(0);
					}
					break;
				case 2:
					{
					setState(66);
					forward();
					}
					break;
				}
				}
				setState(69); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__10) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__18) | (1L << T__21) | (1L << T__23) | (1L << T__26) | (1L << T__28) | (1L << ID) | (1L << CLASSID) | (1L << INTEGER) | (1L << FLOAT) | (1L << STRING))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext objapply;
		public ExpressionContext objfield;
		public ExpressionContext left;
		public Token defsimple;
		public ExpressionContext defsimple2;
		public Token defn;
		public ExpressionContext body;
		public ExpressionContext exp;
		public Token binop;
		public ExpressionContext right;
		public TerminalNode ID() { return getToken(GrammarParser.ID, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<FnargpairContext> fnargpair() {
			return getRuleContexts(FnargpairContext.class);
		}
		public FnargpairContext fnargpair(int i) {
			return getRuleContext(FnargpairContext.class,i);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public ApplyContext apply() {
			return getRuleContext(ApplyContext.class,0);
		}
		public AnonapplyContext anonapply() {
			return getRuleContext(AnonapplyContext.class,0);
		}
		public FnContext fn() {
			return getRuleContext(FnContext.class,0);
		}
		public RefContext ref() {
			return getRuleContext(RefContext.class,0);
		}
		public CondContext cond() {
			return getRuleContext(CondContext.class,0);
		}
		public MatchContext match() {
			return getRuleContext(MatchContext.class,0);
		}
		public ListContext list() {
			return getRuleContext(ListContext.class,0);
		}
		public MapContext map() {
			return getRuleContext(MapContext.class,0);
		}
		public KlassContext klass() {
			return getRuleContext(KlassContext.class,0);
		}
		public InstantiationContext instantiation() {
			return getRuleContext(InstantiationContext.class,0);
		}
		public InterfContext interf() {
			return getRuleContext(InterfContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(72);
				((ExpressionContext)_localctx).defsimple = match(ID);
				setState(73);
				match(T__11);
				setState(74);
				((ExpressionContext)_localctx).defsimple2 = expression(3);
				}
				break;
			case 2:
				{
				setState(75);
				((ExpressionContext)_localctx).defn = match(ID);
				setState(76);
				match(T__3);
				setState(77);
				match(T__4);
				setState(78);
				match(T__11);
				setState(79);
				((ExpressionContext)_localctx).body = expression(2);
				}
				break;
			case 3:
				{
				setState(80);
				((ExpressionContext)_localctx).defn = match(ID);
				setState(81);
				match(T__3);
				setState(82);
				fnargpair();
				setState(87);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__12) {
					{
					{
					setState(83);
					match(T__12);
					setState(84);
					fnargpair();
					}
					}
					setState(89);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(90);
				match(T__4);
				setState(91);
				match(T__11);
				setState(92);
				((ExpressionContext)_localctx).body = expression(1);
				}
				break;
			case 4:
				{
				setState(94);
				value();
				}
				break;
			case 5:
				{
				setState(95);
				apply();
				}
				break;
			case 6:
				{
				setState(96);
				anonapply();
				}
				break;
			case 7:
				{
				setState(97);
				fn();
				}
				break;
			case 8:
				{
				setState(98);
				ref();
				}
				break;
			case 9:
				{
				setState(99);
				cond();
				}
				break;
			case 10:
				{
				setState(100);
				match();
				}
				break;
			case 11:
				{
				setState(101);
				match(T__3);
				setState(102);
				((ExpressionContext)_localctx).exp = expression(0);
				setState(103);
				match(T__4);
				}
				break;
			case 12:
				{
				setState(105);
				list();
				}
				break;
			case 13:
				{
				setState(106);
				map();
				}
				break;
			case 14:
				{
				setState(107);
				klass();
				}
				break;
			case 15:
				{
				setState(108);
				instantiation();
				}
				break;
			case 16:
				{
				setState(109);
				interf();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(129);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(127);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(112);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(113);
						((ExpressionContext)_localctx).binop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__5 || _la==T__6) ) {
							((ExpressionContext)_localctx).binop = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(114);
						((ExpressionContext)_localctx).right = expression(10);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(115);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(116);
						((ExpressionContext)_localctx).binop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__7 || _la==T__8) ) {
							((ExpressionContext)_localctx).binop = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(117);
						((ExpressionContext)_localctx).right = expression(9);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(118);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(119);
						((ExpressionContext)_localctx).binop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__9 || _la==T__10) ) {
							((ExpressionContext)_localctx).binop = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(120);
						((ExpressionContext)_localctx).right = expression(8);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.objapply = _prevctx;
						_localctx.objapply = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(121);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(122);
						match(T__2);
						setState(123);
						apply();
						}
						break;
					case 5:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.objfield = _prevctx;
						_localctx.objfield = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(124);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(125);
						match(T__2);
						setState(126);
						match(ID);
						}
						break;
					}
					} 
				}
				setState(131);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ValueContext extends ParserRuleContext {
		public Token m;
		public Token boolfalse;
		public Token booltrue;
		public TerminalNode INTEGER() { return getToken(GrammarParser.INTEGER, 0); }
		public TerminalNode FLOAT() { return getToken(GrammarParser.FLOAT, 0); }
		public TerminalNode STRING() { return getToken(GrammarParser.STRING, 0); }
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_value);
		int _la;
		try {
			setState(143);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(133);
				_la = _input.LA(1);
				if (_la==T__10) {
					{
					setState(132);
					((ValueContext)_localctx).m = match(T__10);
					}
				}

				setState(135);
				match(INTEGER);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(137);
				_la = _input.LA(1);
				if (_la==T__10) {
					{
					setState(136);
					((ValueContext)_localctx).m = match(T__10);
					}
				}

				setState(139);
				match(FLOAT);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(140);
				match(STRING);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(141);
				((ValueContext)_localctx).boolfalse = match(T__13);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(142);
				((ValueContext)_localctx).booltrue = match(T__14);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FnContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public List<FnargpairContext> fnargpair() {
			return getRuleContexts(FnargpairContext.class);
		}
		public FnargpairContext fnargpair(int i) {
			return getRuleContext(FnargpairContext.class,i);
		}
		public FnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fn; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterFn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitFn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitFn(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FnContext fn() throws RecognitionException {
		FnContext _localctx = new FnContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_fn);
		int _la;
		try {
			setState(162);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(145);
				match(T__15);
				setState(146);
				block();
				setState(147);
				match(T__16);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(149);
				match(T__15);
				setState(150);
				fnargpair();
				setState(155);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__12) {
					{
					{
					setState(151);
					match(T__12);
					setState(152);
					fnargpair();
					}
					}
					setState(157);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(158);
				match(T__17);
				setState(159);
				block();
				setState(160);
				match(T__16);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FnargpairContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(GrammarParser.ID, 0); }
		public TydefContext tydef() {
			return getRuleContext(TydefContext.class,0);
		}
		public FnargpairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fnargpair; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterFnargpair(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitFnargpair(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitFnargpair(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FnargpairContext fnargpair() throws RecognitionException {
		FnargpairContext _localctx = new FnargpairContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_fnargpair);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			match(ID);
			setState(166);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << ID) | (1L << CLASSID))) != 0)) {
				{
				setState(165);
				tydef(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ApplyContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(GrammarParser.ID, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ApplyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_apply; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterApply(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitApply(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitApply(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ApplyContext apply() throws RecognitionException {
		ApplyContext _localctx = new ApplyContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_apply);
		int _la;
		try {
			setState(183);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(168);
				match(ID);
				setState(169);
				match(T__3);
				setState(170);
				match(T__4);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(171);
				match(ID);
				setState(172);
				match(T__3);
				setState(173);
				expression(0);
				setState(178);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__12) {
					{
					{
					setState(174);
					match(T__12);
					setState(175);
					expression(0);
					}
					}
					setState(180);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(181);
				match(T__4);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnonapplyContext extends ParserRuleContext {
		public FnContext fn() {
			return getRuleContext(FnContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public AnonapplyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_anonapply; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterAnonapply(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitAnonapply(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitAnonapply(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnonapplyContext anonapply() throws RecognitionException {
		AnonapplyContext _localctx = new AnonapplyContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_anonapply);
		int _la;
		try {
			setState(201);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(185);
				fn();
				setState(186);
				match(T__3);
				setState(187);
				match(T__4);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(189);
				fn();
				setState(190);
				match(T__3);
				setState(191);
				expression(0);
				setState(196);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__12) {
					{
					{
					setState(192);
					match(T__12);
					setState(193);
					expression(0);
					}
					}
					setState(198);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(199);
				match(T__4);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RefContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(GrammarParser.ID, 0); }
		public RefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ref; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterRef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitRef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitRef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RefContext ref() throws RecognitionException {
		RefContext _localctx = new RefContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_ref);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(203);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CondContext extends ParserRuleContext {
		public ExpressionContext condition;
		public ExpressionContext exptrue;
		public ExpressionContext expfalse;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public CondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterCond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitCond(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitCond(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CondContext cond() throws RecognitionException {
		CondContext _localctx = new CondContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_cond);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(205);
			match(T__18);
			setState(206);
			((CondContext)_localctx).condition = expression(0);
			setState(207);
			match(T__19);
			setState(208);
			((CondContext)_localctx).exptrue = expression(0);
			setState(209);
			match(T__20);
			setState(210);
			((CondContext)_localctx).expfalse = expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForwardContext extends ParserRuleContext {
		public Token nat;
		public Token id;
		public Token natid;
		public TydefContext ty;
		public List<TerminalNode> ID() { return getTokens(GrammarParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(GrammarParser.ID, i);
		}
		public TydefContext tydef() {
			return getRuleContext(TydefContext.class,0);
		}
		public ForwardContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forward; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterForward(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitForward(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitForward(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForwardContext forward() throws RecognitionException {
		ForwardContext _localctx = new ForwardContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_forward);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(213);
			_la = _input.LA(1);
			if (_la==T__21) {
				{
				setState(212);
				((ForwardContext)_localctx).nat = match(T__21);
				}
			}

			setState(215);
			((ForwardContext)_localctx).id = match(ID);
			setState(219);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(216);
				match(T__3);
				setState(217);
				((ForwardContext)_localctx).natid = match(ID);
				setState(218);
				match(T__4);
				}
			}

			setState(221);
			match(T__22);
			setState(222);
			((ForwardContext)_localctx).ty = tydef(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TydefContext extends ParserRuleContext {
		public TerminalNode CLASSID() { return getToken(GrammarParser.CLASSID, 0); }
		public TerminalNode ID() { return getToken(GrammarParser.ID, 0); }
		public List<TydefContext> tydef() {
			return getRuleContexts(TydefContext.class);
		}
		public TydefContext tydef(int i) {
			return getRuleContext(TydefContext.class,i);
		}
		public TydefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tydef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterTydef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitTydef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitTydef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TydefContext tydef() throws RecognitionException {
		return tydef(0);
	}

	private TydefContext tydef(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TydefContext _localctx = new TydefContext(_ctx, _parentState);
		TydefContext _prevctx = _localctx;
		int _startState = 24;
		enterRecursionRule(_localctx, 24, RULE_tydef, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
			switch (_input.LA(1)) {
			case CLASSID:
				{
				setState(225);
				match(CLASSID);
				}
				break;
			case ID:
				{
				setState(226);
				match(ID);
				setState(231);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(227);
						match(T__9);
						setState(228);
						tydef(0);
						}
						} 
					}
					setState(233);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
				}
				}
				break;
			case T__3:
				{
				setState(234);
				match(T__3);
				setState(235);
				tydef(0);
				setState(236);
				match(T__4);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(264);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(262);
					switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
					case 1:
						{
						_localctx = new TydefContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_tydef);
						setState(240);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(245);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==T__12) {
							{
							{
							setState(241);
							match(T__12);
							setState(242);
							tydef(0);
							}
							}
							setState(247);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(248);
						match(T__25);
						setState(249);
						tydef(3);
						}
						break;
					case 2:
						{
						_localctx = new TydefContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_tydef);
						setState(250);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(251);
						match(T__23);
						setState(252);
						tydef(0);
						setState(257);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==T__12) {
							{
							{
							setState(253);
							match(T__12);
							setState(254);
							tydef(0);
							}
							}
							setState(259);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(260);
						match(T__24);
						}
						break;
					}
					} 
				}
				setState(266);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListContext list() throws RecognitionException {
		ListContext _localctx = new ListContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(267);
			match(T__23);
			setState(268);
			expression(0);
			setState(273);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__12) {
				{
				{
				setState(269);
				match(T__12);
				setState(270);
				expression(0);
				}
				}
				setState(275);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(276);
			match(T__24);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MapContext extends ParserRuleContext {
		public List<MappairContext> mappair() {
			return getRuleContexts(MappairContext.class);
		}
		public MappairContext mappair(int i) {
			return getRuleContext(MappairContext.class,i);
		}
		public MapContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_map; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterMap(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitMap(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitMap(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MapContext map() throws RecognitionException {
		MapContext _localctx = new MapContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_map);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(278);
			match(T__23);
			setState(279);
			mappair();
			setState(284);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__12) {
				{
				{
				setState(280);
				match(T__12);
				setState(281);
				mappair();
				}
				}
				setState(286);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(287);
			match(T__24);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MappairContext extends ParserRuleContext {
		public ExpressionContext mapkey;
		public ExpressionContext mapvalue;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public MappairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mappair; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterMappair(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitMappair(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitMappair(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MappairContext mappair() throws RecognitionException {
		MappairContext _localctx = new MappairContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_mappair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(289);
			((MappairContext)_localctx).mapkey = expression(0);
			setState(290);
			match(T__22);
			setState(291);
			((MappairContext)_localctx).mapvalue = expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KlassContext extends ParserRuleContext {
		public TerminalNode CLASSID() { return getToken(GrammarParser.CLASSID, 0); }
		public List<KlassvarContext> klassvar() {
			return getRuleContexts(KlassvarContext.class);
		}
		public KlassvarContext klassvar(int i) {
			return getRuleContext(KlassvarContext.class,i);
		}
		public List<KlassparentContext> klassparent() {
			return getRuleContexts(KlassparentContext.class);
		}
		public KlassparentContext klassparent(int i) {
			return getRuleContext(KlassparentContext.class,i);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public KlassContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_klass; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterKlass(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitKlass(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitKlass(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KlassContext klass() throws RecognitionException {
		KlassContext _localctx = new KlassContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_klass);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			match(T__26);
			setState(294);
			match(CLASSID);
			setState(295);
			match(T__3);
			setState(304);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(296);
				klassvar();
				setState(301);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__12) {
					{
					{
					setState(297);
					match(T__12);
					setState(298);
					klassvar();
					}
					}
					setState(303);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(306);
			match(T__4);
			setState(316);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				{
				setState(307);
				match(T__27);
				setState(308);
				klassparent(0);
				setState(313);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(309);
						match(T__12);
						setState(310);
						klassparent(0);
						}
						} 
					}
					setState(315);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
				}
				}
				break;
			}
			setState(322);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				{
				setState(318);
				match(T__15);
				setState(319);
				block();
				setState(320);
				match(T__16);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KlassvarContext extends ParserRuleContext {
		public TydefContext ty;
		public TerminalNode ID() { return getToken(GrammarParser.ID, 0); }
		public TydefContext tydef() {
			return getRuleContext(TydefContext.class,0);
		}
		public KlassvarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_klassvar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterKlassvar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitKlassvar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitKlassvar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KlassvarContext klassvar() throws RecognitionException {
		KlassvarContext _localctx = new KlassvarContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_klassvar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(324);
			match(ID);
			setState(326);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << ID) | (1L << CLASSID))) != 0)) {
				{
				setState(325);
				((KlassvarContext)_localctx).ty = tydef(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KlassparentContext extends ParserRuleContext {
		public TerminalNode CLASSID() { return getToken(GrammarParser.CLASSID, 0); }
		public TerminalNode ID() { return getToken(GrammarParser.ID, 0); }
		public List<KlassparentContext> klassparent() {
			return getRuleContexts(KlassparentContext.class);
		}
		public KlassparentContext klassparent(int i) {
			return getRuleContext(KlassparentContext.class,i);
		}
		public KlassparentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_klassparent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterKlassparent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitKlassparent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitKlassparent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KlassparentContext klassparent() throws RecognitionException {
		return klassparent(0);
	}

	private KlassparentContext klassparent(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		KlassparentContext _localctx = new KlassparentContext(_ctx, _parentState);
		KlassparentContext _prevctx = _localctx;
		int _startState = 36;
		enterRecursionRule(_localctx, 36, RULE_klassparent, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(331);
			switch (_input.LA(1)) {
			case CLASSID:
				{
				setState(329);
				match(CLASSID);
				}
				break;
			case ID:
				{
				setState(330);
				match(ID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(347);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new KlassparentContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_klassparent);
					setState(333);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(334);
					match(T__23);
					setState(335);
					klassparent(0);
					setState(340);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__12) {
						{
						{
						setState(336);
						match(T__12);
						setState(337);
						klassparent(0);
						}
						}
						setState(342);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(343);
					match(T__24);
					}
					} 
				}
				setState(349);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class InstantiationContext extends ParserRuleContext {
		public TerminalNode CLASSID() { return getToken(GrammarParser.CLASSID, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public InstantiationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instantiation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterInstantiation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitInstantiation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitInstantiation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstantiationContext instantiation() throws RecognitionException {
		InstantiationContext _localctx = new InstantiationContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_instantiation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(350);
			match(CLASSID);
			setState(351);
			match(T__3);
			setState(360);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__10) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__18) | (1L << T__23) | (1L << T__26) | (1L << T__28) | (1L << ID) | (1L << CLASSID) | (1L << INTEGER) | (1L << FLOAT) | (1L << STRING))) != 0)) {
				{
				setState(352);
				expression(0);
				setState(357);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__12) {
					{
					{
					setState(353);
					match(T__12);
					setState(354);
					expression(0);
					}
					}
					setState(359);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(362);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InterfContext extends ParserRuleContext {
		public TerminalNode CLASSID() { return getToken(GrammarParser.CLASSID, 0); }
		public List<TydefContext> tydef() {
			return getRuleContexts(TydefContext.class);
		}
		public TydefContext tydef(int i) {
			return getRuleContext(TydefContext.class,i);
		}
		public List<KlassparentContext> klassparent() {
			return getRuleContexts(KlassparentContext.class);
		}
		public KlassparentContext klassparent(int i) {
			return getRuleContext(KlassparentContext.class,i);
		}
		public List<ForwardContext> forward() {
			return getRuleContexts(ForwardContext.class);
		}
		public ForwardContext forward(int i) {
			return getRuleContext(ForwardContext.class,i);
		}
		public InterfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interf; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterInterf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitInterf(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitInterf(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InterfContext interf() throws RecognitionException {
		InterfContext _localctx = new InterfContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_interf);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(364);
			match(T__28);
			setState(365);
			match(CLASSID);
			setState(377);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				{
				setState(366);
				match(T__23);
				setState(367);
				tydef(0);
				setState(372);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__12) {
					{
					{
					setState(368);
					match(T__12);
					setState(369);
					tydef(0);
					}
					}
					setState(374);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(375);
				match(T__24);
				}
				break;
			}
			setState(388);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
			case 1:
				{
				setState(379);
				match(T__27);
				setState(380);
				klassparent(0);
				setState(385);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(381);
						match(T__12);
						setState(382);
						klassparent(0);
						}
						} 
					}
					setState(387);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
				}
				}
				break;
			}
			setState(398);
			switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
			case 1:
				{
				setState(390);
				match(T__15);
				setState(394);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__21 || _la==ID) {
					{
					{
					setState(391);
					forward();
					}
					}
					setState(396);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(397);
				match(T__16);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MatchContext extends ParserRuleContext {
		public ExpressionContext source;
		public ExpressionContext exptrue;
		public ExpressionContext expfalse;
		public MatchexpContext matchexp() {
			return getRuleContext(MatchexpContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public MatchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_match; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterMatch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitMatch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitMatch(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchContext match() throws RecognitionException {
		MatchContext _localctx = new MatchContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_match);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(400);
			match(T__18);
			setState(401);
			((MatchContext)_localctx).source = expression(0);
			setState(402);
			match(T__27);
			setState(403);
			matchexp();
			setState(404);
			match(T__19);
			setState(405);
			((MatchContext)_localctx).exptrue = expression(0);
			setState(406);
			match(T__20);
			setState(407);
			((MatchContext)_localctx).expfalse = expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MatchexpContext extends ParserRuleContext {
		public Token v;
		public TerminalNode CLASSID() { return getToken(GrammarParser.CLASSID, 0); }
		public List<MatchexpContext> matchexp() {
			return getRuleContexts(MatchexpContext.class);
		}
		public MatchexpContext matchexp(int i) {
			return getRuleContext(MatchexpContext.class,i);
		}
		public TerminalNode ID() { return getToken(GrammarParser.ID, 0); }
		public MatchexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matchexp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterMatchexp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitMatchexp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitMatchexp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchexpContext matchexp() throws RecognitionException {
		MatchexpContext _localctx = new MatchexpContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_matchexp);
		int _la;
		try {
			setState(426);
			switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(410);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(409);
					((MatchexpContext)_localctx).v = match(ID);
					}
				}

				setState(412);
				match(CLASSID);
				setState(413);
				match(T__3);
				setState(422);
				_la = _input.LA(1);
				if (_la==ID || _la==CLASSID) {
					{
					setState(414);
					matchexp();
					setState(419);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__12) {
						{
						{
						setState(415);
						match(T__12);
						setState(416);
						matchexp();
						}
						}
						setState(421);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(424);
				match(T__4);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(425);
				match(ID);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 3:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		case 12:
			return tydef_sempred((TydefContext)_localctx, predIndex);
		case 18:
			return klassparent_sempred((KlassparentContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 9);
		case 1:
			return precpred(_ctx, 8);
		case 2:
			return precpred(_ctx, 7);
		case 3:
			return precpred(_ctx, 11);
		case 4:
			return precpred(_ctx, 10);
		}
		return true;
	}
	private boolean tydef_sempred(TydefContext _localctx, int predIndex) {
		switch (predIndex) {
		case 5:
			return precpred(_ctx, 2);
		case 6:
			return precpred(_ctx, 3);
		}
		return true;
	}
	private boolean klassparent_sempred(KlassparentContext _localctx, int predIndex) {
		switch (predIndex) {
		case 7:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\'\u01af\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2\3\2\3"+
		"\2\7\2\64\n\2\f\2\16\2\67\13\2\3\2\3\2\3\3\3\3\3\3\3\3\5\3?\n\3\3\3\3"+
		"\3\3\3\3\4\3\4\6\4F\n\4\r\4\16\4G\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\7\5X\n\5\f\5\16\5[\13\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5q\n\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\u0082\n\5\f"+
		"\5\16\5\u0085\13\5\3\6\5\6\u0088\n\6\3\6\3\6\5\6\u008c\n\6\3\6\3\6\3\6"+
		"\3\6\5\6\u0092\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7\u009c\n\7\f\7\16"+
		"\7\u009f\13\7\3\7\3\7\3\7\3\7\5\7\u00a5\n\7\3\b\3\b\5\b\u00a9\n\b\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u00b3\n\t\f\t\16\t\u00b6\13\t\3\t\3\t"+
		"\5\t\u00ba\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u00c5\n\n\f\n\16"+
		"\n\u00c8\13\n\3\n\3\n\5\n\u00cc\n\n\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\r\5\r\u00d8\n\r\3\r\3\r\3\r\3\r\5\r\u00de\n\r\3\r\3\r\3\r\3\16"+
		"\3\16\3\16\3\16\3\16\7\16\u00e8\n\16\f\16\16\16\u00eb\13\16\3\16\3\16"+
		"\3\16\3\16\5\16\u00f1\n\16\3\16\3\16\3\16\7\16\u00f6\n\16\f\16\16\16\u00f9"+
		"\13\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u0102\n\16\f\16\16\16\u0105"+
		"\13\16\3\16\3\16\7\16\u0109\n\16\f\16\16\16\u010c\13\16\3\17\3\17\3\17"+
		"\3\17\7\17\u0112\n\17\f\17\16\17\u0115\13\17\3\17\3\17\3\20\3\20\3\20"+
		"\3\20\7\20\u011d\n\20\f\20\16\20\u0120\13\20\3\20\3\20\3\21\3\21\3\21"+
		"\3\21\3\22\3\22\3\22\3\22\3\22\3\22\7\22\u012e\n\22\f\22\16\22\u0131\13"+
		"\22\5\22\u0133\n\22\3\22\3\22\3\22\3\22\3\22\7\22\u013a\n\22\f\22\16\22"+
		"\u013d\13\22\5\22\u013f\n\22\3\22\3\22\3\22\3\22\5\22\u0145\n\22\3\23"+
		"\3\23\5\23\u0149\n\23\3\24\3\24\3\24\5\24\u014e\n\24\3\24\3\24\3\24\3"+
		"\24\3\24\7\24\u0155\n\24\f\24\16\24\u0158\13\24\3\24\3\24\7\24\u015c\n"+
		"\24\f\24\16\24\u015f\13\24\3\25\3\25\3\25\3\25\3\25\7\25\u0166\n\25\f"+
		"\25\16\25\u0169\13\25\5\25\u016b\n\25\3\25\3\25\3\26\3\26\3\26\3\26\3"+
		"\26\3\26\7\26\u0175\n\26\f\26\16\26\u0178\13\26\3\26\3\26\5\26\u017c\n"+
		"\26\3\26\3\26\3\26\3\26\7\26\u0182\n\26\f\26\16\26\u0185\13\26\5\26\u0187"+
		"\n\26\3\26\3\26\7\26\u018b\n\26\f\26\16\26\u018e\13\26\3\26\5\26\u0191"+
		"\n\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\5\30\u019d\n\30"+
		"\3\30\3\30\3\30\3\30\3\30\7\30\u01a4\n\30\f\30\16\30\u01a7\13\30\5\30"+
		"\u01a9\n\30\3\30\3\30\5\30\u01ad\n\30\3\30\2\5\b\32&\31\2\4\6\b\n\f\16"+
		"\20\22\24\26\30\32\34\36 \"$&(*,.\2\6\3\2 !\3\2\b\t\3\2\n\13\3\2\f\r\u01dd"+
		"\2\60\3\2\2\2\4:\3\2\2\2\6E\3\2\2\2\bp\3\2\2\2\n\u0091\3\2\2\2\f\u00a4"+
		"\3\2\2\2\16\u00a6\3\2\2\2\20\u00b9\3\2\2\2\22\u00cb\3\2\2\2\24\u00cd\3"+
		"\2\2\2\26\u00cf\3\2\2\2\30\u00d7\3\2\2\2\32\u00f0\3\2\2\2\34\u010d\3\2"+
		"\2\2\36\u0118\3\2\2\2 \u0123\3\2\2\2\"\u0127\3\2\2\2$\u0146\3\2\2\2&\u014d"+
		"\3\2\2\2(\u0160\3\2\2\2*\u016e\3\2\2\2,\u0192\3\2\2\2.\u01ac\3\2\2\2\60"+
		"\61\7\3\2\2\61\65\7 \2\2\62\64\5\4\3\2\63\62\3\2\2\2\64\67\3\2\2\2\65"+
		"\63\3\2\2\2\65\66\3\2\2\2\668\3\2\2\2\67\65\3\2\2\289\5\6\4\29\3\3\2\2"+
		"\2:;\7\4\2\2;>\7 \2\2<=\7\5\2\2=?\7 \2\2><\3\2\2\2>?\3\2\2\2?@\3\2\2\2"+
		"@A\7\5\2\2AB\t\2\2\2B\5\3\2\2\2CF\5\b\5\2DF\5\30\r\2EC\3\2\2\2ED\3\2\2"+
		"\2FG\3\2\2\2GE\3\2\2\2GH\3\2\2\2H\7\3\2\2\2IJ\b\5\1\2JK\7 \2\2KL\7\16"+
		"\2\2Lq\5\b\5\5MN\7 \2\2NO\7\6\2\2OP\7\7\2\2PQ\7\16\2\2Qq\5\b\5\4RS\7 "+
		"\2\2ST\7\6\2\2TY\5\16\b\2UV\7\17\2\2VX\5\16\b\2WU\3\2\2\2X[\3\2\2\2YW"+
		"\3\2\2\2YZ\3\2\2\2Z\\\3\2\2\2[Y\3\2\2\2\\]\7\7\2\2]^\7\16\2\2^_\5\b\5"+
		"\3_q\3\2\2\2`q\5\n\6\2aq\5\20\t\2bq\5\22\n\2cq\5\f\7\2dq\5\24\13\2eq\5"+
		"\26\f\2fq\5,\27\2gh\7\6\2\2hi\5\b\5\2ij\7\7\2\2jq\3\2\2\2kq\5\34\17\2"+
		"lq\5\36\20\2mq\5\"\22\2nq\5(\25\2oq\5*\26\2pI\3\2\2\2pM\3\2\2\2pR\3\2"+
		"\2\2p`\3\2\2\2pa\3\2\2\2pb\3\2\2\2pc\3\2\2\2pd\3\2\2\2pe\3\2\2\2pf\3\2"+
		"\2\2pg\3\2\2\2pk\3\2\2\2pl\3\2\2\2pm\3\2\2\2pn\3\2\2\2po\3\2\2\2q\u0083"+
		"\3\2\2\2rs\f\13\2\2st\t\3\2\2t\u0082\5\b\5\fuv\f\n\2\2vw\t\4\2\2w\u0082"+
		"\5\b\5\13xy\f\t\2\2yz\t\5\2\2z\u0082\5\b\5\n{|\f\r\2\2|}\7\5\2\2}\u0082"+
		"\5\20\t\2~\177\f\f\2\2\177\u0080\7\5\2\2\u0080\u0082\7 \2\2\u0081r\3\2"+
		"\2\2\u0081u\3\2\2\2\u0081x\3\2\2\2\u0081{\3\2\2\2\u0081~\3\2\2\2\u0082"+
		"\u0085\3\2\2\2\u0083\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084\t\3\2\2\2"+
		"\u0085\u0083\3\2\2\2\u0086\u0088\7\r\2\2\u0087\u0086\3\2\2\2\u0087\u0088"+
		"\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u0092\7\"\2\2\u008a\u008c\7\r\2\2\u008b"+
		"\u008a\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u0092\7#"+
		"\2\2\u008e\u0092\7$\2\2\u008f\u0092\7\20\2\2\u0090\u0092\7\21\2\2\u0091"+
		"\u0087\3\2\2\2\u0091\u008b\3\2\2\2\u0091\u008e\3\2\2\2\u0091\u008f\3\2"+
		"\2\2\u0091\u0090\3\2\2\2\u0092\13\3\2\2\2\u0093\u0094\7\22\2\2\u0094\u0095"+
		"\5\6\4\2\u0095\u0096\7\23\2\2\u0096\u00a5\3\2\2\2\u0097\u0098\7\22\2\2"+
		"\u0098\u009d\5\16\b\2\u0099\u009a\7\17\2\2\u009a\u009c\5\16\b\2\u009b"+
		"\u0099\3\2\2\2\u009c\u009f\3\2\2\2\u009d\u009b\3\2\2\2\u009d\u009e\3\2"+
		"\2\2\u009e\u00a0\3\2\2\2\u009f\u009d\3\2\2\2\u00a0\u00a1\7\24\2\2\u00a1"+
		"\u00a2\5\6\4\2\u00a2\u00a3\7\23\2\2\u00a3\u00a5\3\2\2\2\u00a4\u0093\3"+
		"\2\2\2\u00a4\u0097\3\2\2\2\u00a5\r\3\2\2\2\u00a6\u00a8\7 \2\2\u00a7\u00a9"+
		"\5\32\16\2\u00a8\u00a7\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\17\3\2\2\2\u00aa"+
		"\u00ab\7 \2\2\u00ab\u00ac\7\6\2\2\u00ac\u00ba\7\7\2\2\u00ad\u00ae\7 \2"+
		"\2\u00ae\u00af\7\6\2\2\u00af\u00b4\5\b\5\2\u00b0\u00b1\7\17\2\2\u00b1"+
		"\u00b3\5\b\5\2\u00b2\u00b0\3\2\2\2\u00b3\u00b6\3\2\2\2\u00b4\u00b2\3\2"+
		"\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b7\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b7"+
		"\u00b8\7\7\2\2\u00b8\u00ba\3\2\2\2\u00b9\u00aa\3\2\2\2\u00b9\u00ad\3\2"+
		"\2\2\u00ba\21\3\2\2\2\u00bb\u00bc\5\f\7\2\u00bc\u00bd\7\6\2\2\u00bd\u00be"+
		"\7\7\2\2\u00be\u00cc\3\2\2\2\u00bf\u00c0\5\f\7\2\u00c0\u00c1\7\6\2\2\u00c1"+
		"\u00c6\5\b\5\2\u00c2\u00c3\7\17\2\2\u00c3\u00c5\5\b\5\2\u00c4\u00c2\3"+
		"\2\2\2\u00c5\u00c8\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7"+
		"\u00c9\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c9\u00ca\7\7\2\2\u00ca\u00cc\3\2"+
		"\2\2\u00cb\u00bb\3\2\2\2\u00cb\u00bf\3\2\2\2\u00cc\23\3\2\2\2\u00cd\u00ce"+
		"\7 \2\2\u00ce\25\3\2\2\2\u00cf\u00d0\7\25\2\2\u00d0\u00d1\5\b\5\2\u00d1"+
		"\u00d2\7\26\2\2\u00d2\u00d3\5\b\5\2\u00d3\u00d4\7\27\2\2\u00d4\u00d5\5"+
		"\b\5\2\u00d5\27\3\2\2\2\u00d6\u00d8\7\30\2\2\u00d7\u00d6\3\2\2\2\u00d7"+
		"\u00d8\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00dd\7 \2\2\u00da\u00db\7\6"+
		"\2\2\u00db\u00dc\7 \2\2\u00dc\u00de\7\7\2\2\u00dd\u00da\3\2\2\2\u00dd"+
		"\u00de\3\2\2\2\u00de\u00df\3\2\2\2\u00df\u00e0\7\31\2\2\u00e0\u00e1\5"+
		"\32\16\2\u00e1\31\3\2\2\2\u00e2\u00e3\b\16\1\2\u00e3\u00f1\7!\2\2\u00e4"+
		"\u00e9\7 \2\2\u00e5\u00e6\7\f\2\2\u00e6\u00e8\5\32\16\2\u00e7\u00e5\3"+
		"\2\2\2\u00e8\u00eb\3\2\2\2\u00e9\u00e7\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea"+
		"\u00f1\3\2\2\2\u00eb\u00e9\3\2\2\2\u00ec\u00ed\7\6\2\2\u00ed\u00ee\5\32"+
		"\16\2\u00ee\u00ef\7\7\2\2\u00ef\u00f1\3\2\2\2\u00f0\u00e2\3\2\2\2\u00f0"+
		"\u00e4\3\2\2\2\u00f0\u00ec\3\2\2\2\u00f1\u010a\3\2\2\2\u00f2\u00f7\f\4"+
		"\2\2\u00f3\u00f4\7\17\2\2\u00f4\u00f6\5\32\16\2\u00f5\u00f3\3\2\2\2\u00f6"+
		"\u00f9\3\2\2\2\u00f7\u00f5\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00fa\3\2"+
		"\2\2\u00f9\u00f7\3\2\2\2\u00fa\u00fb\7\34\2\2\u00fb\u0109\5\32\16\5\u00fc"+
		"\u00fd\f\5\2\2\u00fd\u00fe\7\32\2\2\u00fe\u0103\5\32\16\2\u00ff\u0100"+
		"\7\17\2\2\u0100\u0102\5\32\16\2\u0101\u00ff\3\2\2\2\u0102\u0105\3\2\2"+
		"\2\u0103\u0101\3\2\2\2\u0103\u0104\3\2\2\2\u0104\u0106\3\2\2\2\u0105\u0103"+
		"\3\2\2\2\u0106\u0107\7\33\2\2\u0107\u0109\3\2\2\2\u0108\u00f2\3\2\2\2"+
		"\u0108\u00fc\3\2\2\2\u0109\u010c\3\2\2\2\u010a\u0108\3\2\2\2\u010a\u010b"+
		"\3\2\2\2\u010b\33\3\2\2\2\u010c\u010a\3\2\2\2\u010d\u010e\7\32\2\2\u010e"+
		"\u0113\5\b\5\2\u010f\u0110\7\17\2\2\u0110\u0112\5\b\5\2\u0111\u010f\3"+
		"\2\2\2\u0112\u0115\3\2\2\2\u0113\u0111\3\2\2\2\u0113\u0114\3\2\2\2\u0114"+
		"\u0116\3\2\2\2\u0115\u0113\3\2\2\2\u0116\u0117\7\33\2\2\u0117\35\3\2\2"+
		"\2\u0118\u0119\7\32\2\2\u0119\u011e\5 \21\2\u011a\u011b\7\17\2\2\u011b"+
		"\u011d\5 \21\2\u011c\u011a\3\2\2\2\u011d\u0120\3\2\2\2\u011e\u011c\3\2"+
		"\2\2\u011e\u011f\3\2\2\2\u011f\u0121\3\2\2\2\u0120\u011e\3\2\2\2\u0121"+
		"\u0122\7\33\2\2\u0122\37\3\2\2\2\u0123\u0124\5\b\5\2\u0124\u0125\7\31"+
		"\2\2\u0125\u0126\5\b\5\2\u0126!\3\2\2\2\u0127\u0128\7\35\2\2\u0128\u0129"+
		"\7!\2\2\u0129\u0132\7\6\2\2\u012a\u012f\5$\23\2\u012b\u012c\7\17\2\2\u012c"+
		"\u012e\5$\23\2\u012d\u012b\3\2\2\2\u012e\u0131\3\2\2\2\u012f\u012d\3\2"+
		"\2\2\u012f\u0130\3\2\2\2\u0130\u0133\3\2\2\2\u0131\u012f\3\2\2\2\u0132"+
		"\u012a\3\2\2\2\u0132\u0133\3\2\2\2\u0133\u0134\3\2\2\2\u0134\u013e\7\7"+
		"\2\2\u0135\u0136\7\36\2\2\u0136\u013b\5&\24\2\u0137\u0138\7\17\2\2\u0138"+
		"\u013a\5&\24\2\u0139\u0137\3\2\2\2\u013a\u013d\3\2\2\2\u013b\u0139\3\2"+
		"\2\2\u013b\u013c\3\2\2\2\u013c\u013f\3\2\2\2\u013d\u013b\3\2\2\2\u013e"+
		"\u0135\3\2\2\2\u013e\u013f\3\2\2\2\u013f\u0144\3\2\2\2\u0140\u0141\7\22"+
		"\2\2\u0141\u0142\5\6\4\2\u0142\u0143\7\23\2\2\u0143\u0145\3\2\2\2\u0144"+
		"\u0140\3\2\2\2\u0144\u0145\3\2\2\2\u0145#\3\2\2\2\u0146\u0148\7 \2\2\u0147"+
		"\u0149\5\32\16\2\u0148\u0147\3\2\2\2\u0148\u0149\3\2\2\2\u0149%\3\2\2"+
		"\2\u014a\u014b\b\24\1\2\u014b\u014e\7!\2\2\u014c\u014e\7 \2\2\u014d\u014a"+
		"\3\2\2\2\u014d\u014c\3\2\2\2\u014e\u015d\3\2\2\2\u014f\u0150\f\3\2\2\u0150"+
		"\u0151\7\32\2\2\u0151\u0156\5&\24\2\u0152\u0153\7\17\2\2\u0153\u0155\5"+
		"&\24\2\u0154\u0152\3\2\2\2\u0155\u0158\3\2\2\2\u0156\u0154\3\2\2\2\u0156"+
		"\u0157\3\2\2\2\u0157\u0159\3\2\2\2\u0158\u0156\3\2\2\2\u0159\u015a\7\33"+
		"\2\2\u015a\u015c\3\2\2\2\u015b\u014f\3\2\2\2\u015c\u015f\3\2\2\2\u015d"+
		"\u015b\3\2\2\2\u015d\u015e\3\2\2\2\u015e\'\3\2\2\2\u015f\u015d\3\2\2\2"+
		"\u0160\u0161\7!\2\2\u0161\u016a\7\6\2\2\u0162\u0167\5\b\5\2\u0163\u0164"+
		"\7\17\2\2\u0164\u0166\5\b\5\2\u0165\u0163\3\2\2\2\u0166\u0169\3\2\2\2"+
		"\u0167\u0165\3\2\2\2\u0167\u0168\3\2\2\2\u0168\u016b\3\2\2\2\u0169\u0167"+
		"\3\2\2\2\u016a\u0162\3\2\2\2\u016a\u016b\3\2\2\2\u016b\u016c\3\2\2\2\u016c"+
		"\u016d\7\7\2\2\u016d)\3\2\2\2\u016e\u016f\7\37\2\2\u016f\u017b\7!\2\2"+
		"\u0170\u0171\7\32\2\2\u0171\u0176\5\32\16\2\u0172\u0173\7\17\2\2\u0173"+
		"\u0175\5\32\16\2\u0174\u0172\3\2\2\2\u0175\u0178\3\2\2\2\u0176\u0174\3"+
		"\2\2\2\u0176\u0177\3\2\2\2\u0177\u0179\3\2\2\2\u0178\u0176\3\2\2\2\u0179"+
		"\u017a\7\33\2\2\u017a\u017c\3\2\2\2\u017b\u0170\3\2\2\2\u017b\u017c\3"+
		"\2\2\2\u017c\u0186\3\2\2\2\u017d\u017e\7\36\2\2\u017e\u0183\5&\24\2\u017f"+
		"\u0180\7\17\2\2\u0180\u0182\5&\24\2\u0181\u017f\3\2\2\2\u0182\u0185\3"+
		"\2\2\2\u0183\u0181\3\2\2\2\u0183\u0184\3\2\2\2\u0184\u0187\3\2\2\2\u0185"+
		"\u0183\3\2\2\2\u0186\u017d\3\2\2\2\u0186\u0187\3\2\2\2\u0187\u0190\3\2"+
		"\2\2\u0188\u018c\7\22\2\2\u0189\u018b\5\30\r\2\u018a\u0189\3\2\2\2\u018b"+
		"\u018e\3\2\2\2\u018c\u018a\3\2\2\2\u018c\u018d\3\2\2\2\u018d\u018f\3\2"+
		"\2\2\u018e\u018c\3\2\2\2\u018f\u0191\7\23\2\2\u0190\u0188\3\2\2\2\u0190"+
		"\u0191\3\2\2\2\u0191+\3\2\2\2\u0192\u0193\7\25\2\2\u0193\u0194\5\b\5\2"+
		"\u0194\u0195\7\36\2\2\u0195\u0196\5.\30\2\u0196\u0197\7\26\2\2\u0197\u0198"+
		"\5\b\5\2\u0198\u0199\7\27\2\2\u0199\u019a\5\b\5\2\u019a-\3\2\2\2\u019b"+
		"\u019d\7 \2\2\u019c\u019b\3\2\2\2\u019c\u019d\3\2\2\2\u019d\u019e\3\2"+
		"\2\2\u019e\u019f\7!\2\2\u019f\u01a8\7\6\2\2\u01a0\u01a5\5.\30\2\u01a1"+
		"\u01a2\7\17\2\2\u01a2\u01a4\5.\30\2\u01a3\u01a1\3\2\2\2\u01a4\u01a7\3"+
		"\2\2\2\u01a5\u01a3\3\2\2\2\u01a5\u01a6\3\2\2\2\u01a6\u01a9\3\2\2\2\u01a7"+
		"\u01a5\3\2\2\2\u01a8\u01a0\3\2\2\2\u01a8\u01a9\3\2\2\2\u01a9\u01aa\3\2"+
		"\2\2\u01aa\u01ad\7\7\2\2\u01ab\u01ad\7 \2\2\u01ac\u019c\3\2\2\2\u01ac"+
		"\u01ab\3\2\2\2\u01ad/\3\2\2\2\63\65>EGYp\u0081\u0083\u0087\u008b\u0091"+
		"\u009d\u00a4\u00a8\u00b4\u00b9\u00c6\u00cb\u00d7\u00dd\u00e9\u00f0\u00f7"+
		"\u0103\u0108\u010a\u0113\u011e\u012f\u0132\u013b\u013e\u0144\u0148\u014d"+
		"\u0156\u015d\u0167\u016a\u0176\u017b\u0183\u0186\u018c\u0190\u019c\u01a5"+
		"\u01a8\u01ac";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}