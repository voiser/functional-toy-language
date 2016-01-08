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
		T__24=25, ID=26, CLASSID=27, INTEGER=28, FLOAT=29, STRING=30, WS=31;
	public static final int
		RULE_file = 0, RULE_imp = 1, RULE_block = 2, RULE_expression = 3, RULE_value = 4, 
		RULE_fn = 5, RULE_fnargpair = 6, RULE_apply = 7, RULE_objapply = 8, RULE_objfield = 9, 
		RULE_ref = 10, RULE_cond = 11, RULE_forward = 12, RULE_tydef = 13, RULE_list = 14, 
		RULE_map = 15, RULE_mappair = 16, RULE_klass = 17, RULE_klassvar = 18, 
		RULE_klassparent = 19, RULE_instantiation = 20;
	public static final String[] ruleNames = {
		"file", "imp", "block", "expression", "value", "fn", "fnargpair", "apply", 
		"objapply", "objfield", "ref", "cond", "forward", "tydef", "list", "map", 
		"mappair", "klass", "klassvar", "klassparent", "instantiation"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'module'", "'import'", "'.'", "'as'", "'('", "')'", "'=='", "'*'", 
		"'/'", "'+'", "'-'", "'='", "','", "'{'", "'}'", "'=>'", "'if'", "'then'", 
		"'else'", "':'", "'['", "']'", "'->'", "'class'", "'is'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, "ID", "CLASSID", "INTEGER", "FLOAT", "STRING", "WS"
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
			setState(42);
			match(T__0);
			setState(43);
			((FileContext)_localctx).name = match(ID);
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(44);
				imp();
				}
				}
				setState(49);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(50);
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
		public Token alias;
		public List<TerminalNode> ID() { return getTokens(GrammarParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(GrammarParser.ID, i);
		}
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
			setState(52);
			match(T__1);
			setState(53);
			match(ID);
			setState(56); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(54);
				match(T__2);
				setState(55);
				match(ID);
				}
				}
				setState(58); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__2 );
			setState(62);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(60);
				match(T__3);
				setState(61);
				((ImpContext)_localctx).alias = match(ID);
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
			setState(66); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(66);
				switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(64);
					expression(0);
					}
					break;
				case 2:
					{
					setState(65);
					forward();
					}
					break;
				}
				}
				setState(68); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__13) | (1L << T__16) | (1L << T__20) | (1L << T__23) | (1L << ID) | (1L << CLASSID) | (1L << INTEGER) | (1L << FLOAT) | (1L << STRING))) != 0) );
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
		public FnContext fn() {
			return getRuleContext(FnContext.class,0);
		}
		public ApplyContext apply() {
			return getRuleContext(ApplyContext.class,0);
		}
		public ObjapplyContext objapply() {
			return getRuleContext(ObjapplyContext.class,0);
		}
		public RefContext ref() {
			return getRuleContext(RefContext.class,0);
		}
		public CondContext cond() {
			return getRuleContext(CondContext.class,0);
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
		public ObjfieldContext objfield() {
			return getRuleContext(ObjfieldContext.class,0);
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
			setState(108);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(71);
				((ExpressionContext)_localctx).defsimple = match(ID);
				setState(72);
				match(T__11);
				setState(73);
				((ExpressionContext)_localctx).defsimple2 = expression(3);
				}
				break;
			case 2:
				{
				setState(74);
				((ExpressionContext)_localctx).defn = match(ID);
				setState(75);
				match(T__4);
				setState(76);
				match(T__5);
				setState(77);
				match(T__11);
				setState(78);
				((ExpressionContext)_localctx).body = expression(2);
				}
				break;
			case 3:
				{
				setState(79);
				((ExpressionContext)_localctx).defn = match(ID);
				setState(80);
				match(T__4);
				setState(81);
				fnargpair();
				setState(86);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__12) {
					{
					{
					setState(82);
					match(T__12);
					setState(83);
					fnargpair();
					}
					}
					setState(88);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(89);
				match(T__5);
				setState(90);
				match(T__11);
				setState(91);
				((ExpressionContext)_localctx).body = expression(1);
				}
				break;
			case 4:
				{
				setState(93);
				value();
				}
				break;
			case 5:
				{
				setState(94);
				fn();
				}
				break;
			case 6:
				{
				setState(95);
				apply();
				}
				break;
			case 7:
				{
				setState(96);
				objapply();
				}
				break;
			case 8:
				{
				setState(97);
				ref();
				}
				break;
			case 9:
				{
				setState(98);
				cond();
				}
				break;
			case 10:
				{
				setState(99);
				match(T__4);
				setState(100);
				((ExpressionContext)_localctx).exp = expression(0);
				setState(101);
				match(T__5);
				}
				break;
			case 11:
				{
				setState(103);
				list();
				}
				break;
			case 12:
				{
				setState(104);
				map();
				}
				break;
			case 13:
				{
				setState(105);
				klass();
				}
				break;
			case 14:
				{
				setState(106);
				instantiation();
				}
				break;
			case 15:
				{
				setState(107);
				objfield();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(121);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(119);
					switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(110);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(111);
						((ExpressionContext)_localctx).binop = match(T__6);
						setState(112);
						((ExpressionContext)_localctx).right = expression(10);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(113);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(114);
						((ExpressionContext)_localctx).binop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__7 || _la==T__8) ) {
							((ExpressionContext)_localctx).binop = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(115);
						((ExpressionContext)_localctx).right = expression(9);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(116);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(117);
						((ExpressionContext)_localctx).binop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__9 || _la==T__10) ) {
							((ExpressionContext)_localctx).binop = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(118);
						((ExpressionContext)_localctx).right = expression(8);
						}
						break;
					}
					} 
				}
				setState(123);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
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
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INTEGER) | (1L << FLOAT) | (1L << STRING))) != 0)) ) {
			_errHandler.recoverInline(this);
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
			setState(143);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(126);
				match(T__13);
				setState(127);
				block();
				setState(128);
				match(T__14);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(130);
				match(T__13);
				setState(131);
				fnargpair();
				setState(136);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__12) {
					{
					{
					setState(132);
					match(T__12);
					setState(133);
					fnargpair();
					}
					}
					setState(138);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(139);
				match(T__15);
				setState(140);
				block();
				setState(141);
				match(T__14);
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
			setState(145);
			match(ID);
			setState(147);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << ID) | (1L << CLASSID))) != 0)) {
				{
				setState(146);
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
			setState(164);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(149);
				match(ID);
				setState(150);
				match(T__4);
				setState(151);
				match(T__5);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(152);
				match(ID);
				setState(153);
				match(T__4);
				setState(154);
				expression(0);
				setState(159);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__12) {
					{
					{
					setState(155);
					match(T__12);
					setState(156);
					expression(0);
					}
					}
					setState(161);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(162);
				match(T__5);
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

	public static class ObjapplyContext extends ParserRuleContext {
		public RefContext ref() {
			return getRuleContext(RefContext.class,0);
		}
		public ApplyContext apply() {
			return getRuleContext(ApplyContext.class,0);
		}
		public ObjapplyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objapply; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterObjapply(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitObjapply(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitObjapply(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjapplyContext objapply() throws RecognitionException {
		ObjapplyContext _localctx = new ObjapplyContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_objapply);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			ref();
			setState(167);
			match(T__2);
			setState(168);
			apply();
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

	public static class ObjfieldContext extends ParserRuleContext {
		public RefContext ref() {
			return getRuleContext(RefContext.class,0);
		}
		public TerminalNode ID() { return getToken(GrammarParser.ID, 0); }
		public ObjfieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objfield; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterObjfield(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitObjfield(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitObjfield(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjfieldContext objfield() throws RecognitionException {
		ObjfieldContext _localctx = new ObjfieldContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_objfield);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			ref();
			setState(171);
			match(T__2);
			setState(172);
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
		enterRule(_localctx, 20, RULE_ref);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
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
		enterRule(_localctx, 22, RULE_cond);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
			match(T__16);
			setState(177);
			((CondContext)_localctx).condition = expression(0);
			setState(178);
			match(T__17);
			setState(179);
			((CondContext)_localctx).exptrue = expression(0);
			setState(180);
			match(T__18);
			setState(181);
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
		public TydefContext ty;
		public TerminalNode ID() { return getToken(GrammarParser.ID, 0); }
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
		enterRule(_localctx, 24, RULE_forward);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			match(ID);
			setState(184);
			match(T__19);
			setState(185);
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
		int _startState = 26;
		enterRecursionRule(_localctx, 26, RULE_tydef, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(201);
			switch (_input.LA(1)) {
			case CLASSID:
				{
				setState(188);
				match(CLASSID);
				}
				break;
			case ID:
				{
				setState(189);
				match(ID);
				setState(194);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(190);
						match(T__9);
						setState(191);
						tydef(0);
						}
						} 
					}
					setState(196);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
				}
				}
				break;
			case T__4:
				{
				setState(197);
				match(T__4);
				setState(198);
				tydef(0);
				setState(199);
				match(T__5);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(223);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(221);
					switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
					case 1:
						{
						_localctx = new TydefContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_tydef);
						setState(203);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(204);
						match(T__22);
						setState(205);
						tydef(4);
						}
						break;
					case 2:
						{
						_localctx = new TydefContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_tydef);
						setState(206);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(207);
						match(T__12);
						setState(208);
						tydef(3);
						}
						break;
					case 3:
						{
						_localctx = new TydefContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_tydef);
						setState(209);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(210);
						match(T__20);
						setState(211);
						tydef(0);
						setState(216);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==T__12) {
							{
							{
							setState(212);
							match(T__12);
							setState(213);
							tydef(0);
							}
							}
							setState(218);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(219);
						match(T__21);
						}
						break;
					}
					} 
				}
				setState(225);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
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
		enterRule(_localctx, 28, RULE_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(226);
			match(T__20);
			setState(227);
			expression(0);
			setState(232);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__12) {
				{
				{
				setState(228);
				match(T__12);
				setState(229);
				expression(0);
				}
				}
				setState(234);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(235);
			match(T__21);
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
		enterRule(_localctx, 30, RULE_map);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(237);
			match(T__20);
			setState(238);
			mappair();
			setState(243);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__12) {
				{
				{
				setState(239);
				match(T__12);
				setState(240);
				mappair();
				}
				}
				setState(245);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(246);
			match(T__21);
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
		enterRule(_localctx, 32, RULE_mappair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(248);
			((MappairContext)_localctx).mapkey = expression(0);
			setState(249);
			match(T__19);
			setState(250);
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
		enterRule(_localctx, 34, RULE_klass);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(252);
			match(T__23);
			setState(253);
			match(CLASSID);
			setState(254);
			match(T__4);
			setState(263);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(255);
				klassvar();
				setState(260);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__12) {
					{
					{
					setState(256);
					match(T__12);
					setState(257);
					klassvar();
					}
					}
					setState(262);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(265);
			match(T__5);
			setState(275);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				{
				setState(266);
				match(T__24);
				setState(267);
				klassparent(0);
				setState(272);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(268);
						match(T__12);
						setState(269);
						klassparent(0);
						}
						} 
					}
					setState(274);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
				}
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
		enterRule(_localctx, 36, RULE_klassvar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
			match(ID);
			setState(278);
			((KlassvarContext)_localctx).ty = tydef(0);
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
		int _startState = 38;
		enterRecursionRule(_localctx, 38, RULE_klassparent, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(283);
			switch (_input.LA(1)) {
			case CLASSID:
				{
				setState(281);
				match(CLASSID);
				}
				break;
			case ID:
				{
				setState(282);
				match(ID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(302);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(300);
					switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
					case 1:
						{
						_localctx = new KlassparentContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_klassparent);
						setState(285);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(286);
						match(T__12);
						setState(287);
						klassparent(2);
						}
						break;
					case 2:
						{
						_localctx = new KlassparentContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_klassparent);
						setState(288);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(289);
						match(T__20);
						setState(290);
						klassparent(0);
						setState(295);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==T__12) {
							{
							{
							setState(291);
							match(T__12);
							setState(292);
							klassparent(0);
							}
							}
							setState(297);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(298);
						match(T__21);
						}
						break;
					}
					} 
				}
				setState(304);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
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
		enterRule(_localctx, 40, RULE_instantiation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(305);
			match(CLASSID);
			setState(306);
			match(T__4);
			setState(315);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__13) | (1L << T__16) | (1L << T__20) | (1L << T__23) | (1L << ID) | (1L << CLASSID) | (1L << INTEGER) | (1L << FLOAT) | (1L << STRING))) != 0)) {
				{
				setState(307);
				expression(0);
				setState(312);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__12) {
					{
					{
					setState(308);
					match(T__12);
					setState(309);
					expression(0);
					}
					}
					setState(314);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(317);
			match(T__5);
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
		case 13:
			return tydef_sempred((TydefContext)_localctx, predIndex);
		case 19:
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
		}
		return true;
	}
	private boolean tydef_sempred(TydefContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return precpred(_ctx, 3);
		case 4:
			return precpred(_ctx, 2);
		case 5:
			return precpred(_ctx, 4);
		}
		return true;
	}
	private boolean klassparent_sempred(KlassparentContext _localctx, int predIndex) {
		switch (predIndex) {
		case 6:
			return precpred(_ctx, 1);
		case 7:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3!\u0142\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\2\7\2\60\n\2\f\2\16"+
		"\2\63\13\2\3\2\3\2\3\3\3\3\3\3\3\3\6\3;\n\3\r\3\16\3<\3\3\3\3\5\3A\n\3"+
		"\3\4\3\4\6\4E\n\4\r\4\16\4F\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\7\5W\n\5\f\5\16\5Z\13\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5o\n\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\7\5z\n\5\f\5\16\5}\13\5\3\6\3\6\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\7\7\u0089\n\7\f\7\16\7\u008c\13\7\3\7\3\7\3\7\3\7\5\7\u0092"+
		"\n\7\3\b\3\b\5\b\u0096\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u00a0\n"+
		"\t\f\t\16\t\u00a3\13\t\3\t\3\t\5\t\u00a7\n\t\3\n\3\n\3\n\3\n\3\13\3\13"+
		"\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17"+
		"\3\17\3\17\3\17\3\17\7\17\u00c3\n\17\f\17\16\17\u00c6\13\17\3\17\3\17"+
		"\3\17\3\17\5\17\u00cc\n\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\7\17\u00d9\n\17\f\17\16\17\u00dc\13\17\3\17\3\17\7\17\u00e0"+
		"\n\17\f\17\16\17\u00e3\13\17\3\20\3\20\3\20\3\20\7\20\u00e9\n\20\f\20"+
		"\16\20\u00ec\13\20\3\20\3\20\3\21\3\21\3\21\3\21\7\21\u00f4\n\21\f\21"+
		"\16\21\u00f7\13\21\3\21\3\21\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3"+
		"\23\3\23\7\23\u0105\n\23\f\23\16\23\u0108\13\23\5\23\u010a\n\23\3\23\3"+
		"\23\3\23\3\23\3\23\7\23\u0111\n\23\f\23\16\23\u0114\13\23\5\23\u0116\n"+
		"\23\3\24\3\24\3\24\3\25\3\25\3\25\5\25\u011e\n\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\7\25\u0128\n\25\f\25\16\25\u012b\13\25\3\25\3\25"+
		"\7\25\u012f\n\25\f\25\16\25\u0132\13\25\3\26\3\26\3\26\3\26\3\26\7\26"+
		"\u0139\n\26\f\26\16\26\u013c\13\26\5\26\u013e\n\26\3\26\3\26\3\26\2\5"+
		"\b\34(\27\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*\2\5\3\2\n\13\3"+
		"\2\f\r\3\2\36 \u015b\2,\3\2\2\2\4\66\3\2\2\2\6D\3\2\2\2\bn\3\2\2\2\n~"+
		"\3\2\2\2\f\u0091\3\2\2\2\16\u0093\3\2\2\2\20\u00a6\3\2\2\2\22\u00a8\3"+
		"\2\2\2\24\u00ac\3\2\2\2\26\u00b0\3\2\2\2\30\u00b2\3\2\2\2\32\u00b9\3\2"+
		"\2\2\34\u00cb\3\2\2\2\36\u00e4\3\2\2\2 \u00ef\3\2\2\2\"\u00fa\3\2\2\2"+
		"$\u00fe\3\2\2\2&\u0117\3\2\2\2(\u011d\3\2\2\2*\u0133\3\2\2\2,-\7\3\2\2"+
		"-\61\7\34\2\2.\60\5\4\3\2/.\3\2\2\2\60\63\3\2\2\2\61/\3\2\2\2\61\62\3"+
		"\2\2\2\62\64\3\2\2\2\63\61\3\2\2\2\64\65\5\6\4\2\65\3\3\2\2\2\66\67\7"+
		"\4\2\2\67:\7\34\2\289\7\5\2\29;\7\34\2\2:8\3\2\2\2;<\3\2\2\2<:\3\2\2\2"+
		"<=\3\2\2\2=@\3\2\2\2>?\7\6\2\2?A\7\34\2\2@>\3\2\2\2@A\3\2\2\2A\5\3\2\2"+
		"\2BE\5\b\5\2CE\5\32\16\2DB\3\2\2\2DC\3\2\2\2EF\3\2\2\2FD\3\2\2\2FG\3\2"+
		"\2\2G\7\3\2\2\2HI\b\5\1\2IJ\7\34\2\2JK\7\16\2\2Ko\5\b\5\5LM\7\34\2\2M"+
		"N\7\7\2\2NO\7\b\2\2OP\7\16\2\2Po\5\b\5\4QR\7\34\2\2RS\7\7\2\2SX\5\16\b"+
		"\2TU\7\17\2\2UW\5\16\b\2VT\3\2\2\2WZ\3\2\2\2XV\3\2\2\2XY\3\2\2\2Y[\3\2"+
		"\2\2ZX\3\2\2\2[\\\7\b\2\2\\]\7\16\2\2]^\5\b\5\3^o\3\2\2\2_o\5\n\6\2`o"+
		"\5\f\7\2ao\5\20\t\2bo\5\22\n\2co\5\26\f\2do\5\30\r\2ef\7\7\2\2fg\5\b\5"+
		"\2gh\7\b\2\2ho\3\2\2\2io\5\36\20\2jo\5 \21\2ko\5$\23\2lo\5*\26\2mo\5\24"+
		"\13\2nH\3\2\2\2nL\3\2\2\2nQ\3\2\2\2n_\3\2\2\2n`\3\2\2\2na\3\2\2\2nb\3"+
		"\2\2\2nc\3\2\2\2nd\3\2\2\2ne\3\2\2\2ni\3\2\2\2nj\3\2\2\2nk\3\2\2\2nl\3"+
		"\2\2\2nm\3\2\2\2o{\3\2\2\2pq\f\13\2\2qr\7\t\2\2rz\5\b\5\fst\f\n\2\2tu"+
		"\t\2\2\2uz\5\b\5\13vw\f\t\2\2wx\t\3\2\2xz\5\b\5\nyp\3\2\2\2ys\3\2\2\2"+
		"yv\3\2\2\2z}\3\2\2\2{y\3\2\2\2{|\3\2\2\2|\t\3\2\2\2}{\3\2\2\2~\177\t\4"+
		"\2\2\177\13\3\2\2\2\u0080\u0081\7\20\2\2\u0081\u0082\5\6\4\2\u0082\u0083"+
		"\7\21\2\2\u0083\u0092\3\2\2\2\u0084\u0085\7\20\2\2\u0085\u008a\5\16\b"+
		"\2\u0086\u0087\7\17\2\2\u0087\u0089\5\16\b\2\u0088\u0086\3\2\2\2\u0089"+
		"\u008c\3\2\2\2\u008a\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008d\3\2"+
		"\2\2\u008c\u008a\3\2\2\2\u008d\u008e\7\22\2\2\u008e\u008f\5\6\4\2\u008f"+
		"\u0090\7\21\2\2\u0090\u0092\3\2\2\2\u0091\u0080\3\2\2\2\u0091\u0084\3"+
		"\2\2\2\u0092\r\3\2\2\2\u0093\u0095\7\34\2\2\u0094\u0096\5\34\17\2\u0095"+
		"\u0094\3\2\2\2\u0095\u0096\3\2\2\2\u0096\17\3\2\2\2\u0097\u0098\7\34\2"+
		"\2\u0098\u0099\7\7\2\2\u0099\u00a7\7\b\2\2\u009a\u009b\7\34\2\2\u009b"+
		"\u009c\7\7\2\2\u009c\u00a1\5\b\5\2\u009d\u009e\7\17\2\2\u009e\u00a0\5"+
		"\b\5\2\u009f\u009d\3\2\2\2\u00a0\u00a3\3\2\2\2\u00a1\u009f\3\2\2\2\u00a1"+
		"\u00a2\3\2\2\2\u00a2\u00a4\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a4\u00a5\7\b"+
		"\2\2\u00a5\u00a7\3\2\2\2\u00a6\u0097\3\2\2\2\u00a6\u009a\3\2\2\2\u00a7"+
		"\21\3\2\2\2\u00a8\u00a9\5\26\f\2\u00a9\u00aa\7\5\2\2\u00aa\u00ab\5\20"+
		"\t\2\u00ab\23\3\2\2\2\u00ac\u00ad\5\26\f\2\u00ad\u00ae\7\5\2\2\u00ae\u00af"+
		"\7\34\2\2\u00af\25\3\2\2\2\u00b0\u00b1\7\34\2\2\u00b1\27\3\2\2\2\u00b2"+
		"\u00b3\7\23\2\2\u00b3\u00b4\5\b\5\2\u00b4\u00b5\7\24\2\2\u00b5\u00b6\5"+
		"\b\5\2\u00b6\u00b7\7\25\2\2\u00b7\u00b8\5\b\5\2\u00b8\31\3\2\2\2\u00b9"+
		"\u00ba\7\34\2\2\u00ba\u00bb\7\26\2\2\u00bb\u00bc\5\34\17\2\u00bc\33\3"+
		"\2\2\2\u00bd\u00be\b\17\1\2\u00be\u00cc\7\35\2\2\u00bf\u00c4\7\34\2\2"+
		"\u00c0\u00c1\7\f\2\2\u00c1\u00c3\5\34\17\2\u00c2\u00c0\3\2\2\2\u00c3\u00c6"+
		"\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00cc\3\2\2\2\u00c6"+
		"\u00c4\3\2\2\2\u00c7\u00c8\7\7\2\2\u00c8\u00c9\5\34\17\2\u00c9\u00ca\7"+
		"\b\2\2\u00ca\u00cc\3\2\2\2\u00cb\u00bd\3\2\2\2\u00cb\u00bf\3\2\2\2\u00cb"+
		"\u00c7\3\2\2\2\u00cc\u00e1\3\2\2\2\u00cd\u00ce\f\5\2\2\u00ce\u00cf\7\31"+
		"\2\2\u00cf\u00e0\5\34\17\6\u00d0\u00d1\f\4\2\2\u00d1\u00d2\7\17\2\2\u00d2"+
		"\u00e0\5\34\17\5\u00d3\u00d4\f\6\2\2\u00d4\u00d5\7\27\2\2\u00d5\u00da"+
		"\5\34\17\2\u00d6\u00d7\7\17\2\2\u00d7\u00d9\5\34\17\2\u00d8\u00d6\3\2"+
		"\2\2\u00d9\u00dc\3\2\2\2\u00da\u00d8\3\2\2\2\u00da\u00db\3\2\2\2\u00db"+
		"\u00dd\3\2\2\2\u00dc\u00da\3\2\2\2\u00dd\u00de\7\30\2\2\u00de\u00e0\3"+
		"\2\2\2\u00df\u00cd\3\2\2\2\u00df\u00d0\3\2\2\2\u00df\u00d3\3\2\2\2\u00e0"+
		"\u00e3\3\2\2\2\u00e1\u00df\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\35\3\2\2"+
		"\2\u00e3\u00e1\3\2\2\2\u00e4\u00e5\7\27\2\2\u00e5\u00ea\5\b\5\2\u00e6"+
		"\u00e7\7\17\2\2\u00e7\u00e9\5\b\5\2\u00e8\u00e6\3\2\2\2\u00e9\u00ec\3"+
		"\2\2\2\u00ea\u00e8\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb\u00ed\3\2\2\2\u00ec"+
		"\u00ea\3\2\2\2\u00ed\u00ee\7\30\2\2\u00ee\37\3\2\2\2\u00ef\u00f0\7\27"+
		"\2\2\u00f0\u00f5\5\"\22\2\u00f1\u00f2\7\17\2\2\u00f2\u00f4\5\"\22\2\u00f3"+
		"\u00f1\3\2\2\2\u00f4\u00f7\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f5\u00f6\3\2"+
		"\2\2\u00f6\u00f8\3\2\2\2\u00f7\u00f5\3\2\2\2\u00f8\u00f9\7\30\2\2\u00f9"+
		"!\3\2\2\2\u00fa\u00fb\5\b\5\2\u00fb\u00fc\7\26\2\2\u00fc\u00fd\5\b\5\2"+
		"\u00fd#\3\2\2\2\u00fe\u00ff\7\32\2\2\u00ff\u0100\7\35\2\2\u0100\u0109"+
		"\7\7\2\2\u0101\u0106\5&\24\2\u0102\u0103\7\17\2\2\u0103\u0105\5&\24\2"+
		"\u0104\u0102\3\2\2\2\u0105\u0108\3\2\2\2\u0106\u0104\3\2\2\2\u0106\u0107"+
		"\3\2\2\2\u0107\u010a\3\2\2\2\u0108\u0106\3\2\2\2\u0109\u0101\3\2\2\2\u0109"+
		"\u010a\3\2\2\2\u010a\u010b\3\2\2\2\u010b\u0115\7\b\2\2\u010c\u010d\7\33"+
		"\2\2\u010d\u0112\5(\25\2\u010e\u010f\7\17\2\2\u010f\u0111\5(\25\2\u0110"+
		"\u010e\3\2\2\2\u0111\u0114\3\2\2\2\u0112\u0110\3\2\2\2\u0112\u0113\3\2"+
		"\2\2\u0113\u0116\3\2\2\2\u0114\u0112\3\2\2\2\u0115\u010c\3\2\2\2\u0115"+
		"\u0116\3\2\2\2\u0116%\3\2\2\2\u0117\u0118\7\34\2\2\u0118\u0119\5\34\17"+
		"\2\u0119\'\3\2\2\2\u011a\u011b\b\25\1\2\u011b\u011e\7\35\2\2\u011c\u011e"+
		"\7\34\2\2\u011d\u011a\3\2\2\2\u011d\u011c\3\2\2\2\u011e\u0130\3\2\2\2"+
		"\u011f\u0120\f\3\2\2\u0120\u0121\7\17\2\2\u0121\u012f\5(\25\4\u0122\u0123"+
		"\f\4\2\2\u0123\u0124\7\27\2\2\u0124\u0129\5(\25\2\u0125\u0126\7\17\2\2"+
		"\u0126\u0128\5(\25\2\u0127\u0125\3\2\2\2\u0128\u012b\3\2\2\2\u0129\u0127"+
		"\3\2\2\2\u0129\u012a\3\2\2\2\u012a\u012c\3\2\2\2\u012b\u0129\3\2\2\2\u012c"+
		"\u012d\7\30\2\2\u012d\u012f\3\2\2\2\u012e\u011f\3\2\2\2\u012e\u0122\3"+
		"\2\2\2\u012f\u0132\3\2\2\2\u0130\u012e\3\2\2\2\u0130\u0131\3\2\2\2\u0131"+
		")\3\2\2\2\u0132\u0130\3\2\2\2\u0133\u0134\7\35\2\2\u0134\u013d\7\7\2\2"+
		"\u0135\u013a\5\b\5\2\u0136\u0137\7\17\2\2\u0137\u0139\5\b\5\2\u0138\u0136"+
		"\3\2\2\2\u0139\u013c\3\2\2\2\u013a\u0138\3\2\2\2\u013a\u013b\3\2\2\2\u013b"+
		"\u013e\3\2\2\2\u013c\u013a\3\2\2\2\u013d\u0135\3\2\2\2\u013d\u013e\3\2"+
		"\2\2\u013e\u013f\3\2\2\2\u013f\u0140\7\b\2\2\u0140+\3\2\2\2!\61<@DFXn"+
		"y{\u008a\u0091\u0095\u00a1\u00a6\u00c4\u00cb\u00da\u00df\u00e1\u00ea\u00f5"+
		"\u0106\u0109\u0112\u0115\u011d\u0129\u012e\u0130\u013a\u013d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}