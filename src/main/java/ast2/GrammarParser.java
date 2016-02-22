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
		T__24=25, T__25=26, T__26=27, ID=28, CLASSID=29, INTEGER=30, FLOAT=31, 
		STRING=32, WS=33;
	public static final int
		RULE_file = 0, RULE_imp = 1, RULE_block = 2, RULE_expression = 3, RULE_value = 4, 
		RULE_fn = 5, RULE_fnargpair = 6, RULE_apply = 7, RULE_ref = 8, RULE_cond = 9, 
		RULE_forward = 10, RULE_tydef = 11, RULE_list = 12, RULE_map = 13, RULE_mappair = 14, 
		RULE_klass = 15, RULE_klassvar = 16, RULE_klassparent = 17, RULE_instantiation = 18, 
		RULE_match = 19, RULE_matchexp = 20;
	public static final String[] ruleNames = {
		"file", "imp", "block", "expression", "value", "fn", "fnargpair", "apply", 
		"ref", "cond", "forward", "tydef", "list", "map", "mappair", "klass", 
		"klassvar", "klassparent", "instantiation", "match", "matchexp"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'module'", "'import'", "'.'", "'('", "')'", "'=='", "'!='", "'*'", 
		"'/'", "'+'", "'-'", "'='", "','", "'false'", "'true'", "'{'", "'}'", 
		"'=>'", "'if'", "'then'", "'else'", "':'", "'['", "']'", "'->'", "'class'", 
		"'is'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, "ID", "CLASSID", "INTEGER", "FLOAT", "STRING", 
		"WS"
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
			setState(52);
			match(T__1);
			setState(53);
			match(ID);
			setState(56);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(54);
				match(T__2);
				setState(55);
				match(ID);
				}
				break;
			}
			setState(58);
			match(T__2);
			setState(59);
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
			setState(63); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(63);
				switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
				case 1:
					{
					setState(61);
					expression(0);
					}
					break;
				case 2:
					{
					setState(62);
					forward();
					}
					break;
				}
				}
				setState(65); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__10) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__18) | (1L << T__22) | (1L << T__25) | (1L << ID) | (1L << CLASSID) | (1L << INTEGER) | (1L << FLOAT) | (1L << STRING))) != 0) );
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
		public FnContext fn() {
			return getRuleContext(FnContext.class,0);
		}
		public ApplyContext apply() {
			return getRuleContext(ApplyContext.class,0);
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
			setState(104);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(68);
				((ExpressionContext)_localctx).defsimple = match(ID);
				setState(69);
				match(T__11);
				setState(70);
				((ExpressionContext)_localctx).defsimple2 = expression(3);
				}
				break;
			case 2:
				{
				setState(71);
				((ExpressionContext)_localctx).defn = match(ID);
				setState(72);
				match(T__3);
				setState(73);
				match(T__4);
				setState(74);
				match(T__11);
				setState(75);
				((ExpressionContext)_localctx).body = expression(2);
				}
				break;
			case 3:
				{
				setState(76);
				((ExpressionContext)_localctx).defn = match(ID);
				setState(77);
				match(T__3);
				setState(78);
				fnargpair();
				setState(83);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__12) {
					{
					{
					setState(79);
					match(T__12);
					setState(80);
					fnargpair();
					}
					}
					setState(85);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(86);
				match(T__4);
				setState(87);
				match(T__11);
				setState(88);
				((ExpressionContext)_localctx).body = expression(1);
				}
				break;
			case 4:
				{
				setState(90);
				value();
				}
				break;
			case 5:
				{
				setState(91);
				fn();
				}
				break;
			case 6:
				{
				setState(92);
				apply();
				}
				break;
			case 7:
				{
				setState(93);
				ref();
				}
				break;
			case 8:
				{
				setState(94);
				cond();
				}
				break;
			case 9:
				{
				setState(95);
				match();
				}
				break;
			case 10:
				{
				setState(96);
				match(T__3);
				setState(97);
				((ExpressionContext)_localctx).exp = expression(0);
				setState(98);
				match(T__4);
				}
				break;
			case 11:
				{
				setState(100);
				list();
				}
				break;
			case 12:
				{
				setState(101);
				map();
				}
				break;
			case 13:
				{
				setState(102);
				klass();
				}
				break;
			case 14:
				{
				setState(103);
				instantiation();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(123);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(121);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(106);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(107);
						((ExpressionContext)_localctx).binop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__5 || _la==T__6) ) {
							((ExpressionContext)_localctx).binop = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(108);
						((ExpressionContext)_localctx).right = expression(9);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(109);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(110);
						((ExpressionContext)_localctx).binop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__7 || _la==T__8) ) {
							((ExpressionContext)_localctx).binop = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(111);
						((ExpressionContext)_localctx).right = expression(8);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(112);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(113);
						((ExpressionContext)_localctx).binop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__9 || _la==T__10) ) {
							((ExpressionContext)_localctx).binop = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(114);
						((ExpressionContext)_localctx).right = expression(7);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.objapply = _prevctx;
						_localctx.objapply = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(115);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(116);
						match(T__2);
						setState(117);
						apply();
						}
						break;
					case 5:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.objfield = _prevctx;
						_localctx.objfield = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(118);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(119);
						match(T__2);
						setState(120);
						match(ID);
						}
						break;
					}
					} 
				}
				setState(125);
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
			setState(137);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(127);
				_la = _input.LA(1);
				if (_la==T__10) {
					{
					setState(126);
					((ValueContext)_localctx).m = match(T__10);
					}
				}

				setState(129);
				match(INTEGER);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(131);
				_la = _input.LA(1);
				if (_la==T__10) {
					{
					setState(130);
					((ValueContext)_localctx).m = match(T__10);
					}
				}

				setState(133);
				match(FLOAT);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(134);
				match(STRING);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(135);
				((ValueContext)_localctx).boolfalse = match(T__13);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(136);
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
			setState(156);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(139);
				match(T__15);
				setState(140);
				block();
				setState(141);
				match(T__16);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(143);
				match(T__15);
				setState(144);
				fnargpair();
				setState(149);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__12) {
					{
					{
					setState(145);
					match(T__12);
					setState(146);
					fnargpair();
					}
					}
					setState(151);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(152);
				match(T__17);
				setState(153);
				block();
				setState(154);
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
			setState(158);
			match(ID);
			setState(160);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << ID) | (1L << CLASSID))) != 0)) {
				{
				setState(159);
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
			setState(177);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(162);
				match(ID);
				setState(163);
				match(T__3);
				setState(164);
				match(T__4);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(165);
				match(ID);
				setState(166);
				match(T__3);
				setState(167);
				expression(0);
				setState(172);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__12) {
					{
					{
					setState(168);
					match(T__12);
					setState(169);
					expression(0);
					}
					}
					setState(174);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(175);
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
		enterRule(_localctx, 16, RULE_ref);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
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
		enterRule(_localctx, 18, RULE_cond);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			match(T__18);
			setState(182);
			((CondContext)_localctx).condition = expression(0);
			setState(183);
			match(T__19);
			setState(184);
			((CondContext)_localctx).exptrue = expression(0);
			setState(185);
			match(T__20);
			setState(186);
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
		enterRule(_localctx, 20, RULE_forward);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			match(ID);
			setState(189);
			match(T__21);
			setState(190);
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
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_tydef, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			switch (_input.LA(1)) {
			case CLASSID:
				{
				setState(193);
				match(CLASSID);
				}
				break;
			case ID:
				{
				setState(194);
				match(ID);
				setState(199);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(195);
						match(T__9);
						setState(196);
						tydef(0);
						}
						} 
					}
					setState(201);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
				}
				}
				break;
			case T__3:
				{
				setState(202);
				match(T__3);
				setState(203);
				tydef(0);
				setState(204);
				match(T__4);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(231);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(229);
					switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
					case 1:
						{
						_localctx = new TydefContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_tydef);
						setState(208);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(209);
						match(T__24);
						setState(210);
						tydef(4);
						}
						break;
					case 2:
						{
						_localctx = new TydefContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_tydef);
						setState(211);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(212);
						match(T__12);
						setState(213);
						tydef(0);
						setState(214);
						match(T__24);
						setState(215);
						tydef(3);
						}
						break;
					case 3:
						{
						_localctx = new TydefContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_tydef);
						setState(217);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(218);
						match(T__22);
						setState(219);
						tydef(0);
						setState(224);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==T__12) {
							{
							{
							setState(220);
							match(T__12);
							setState(221);
							tydef(0);
							}
							}
							setState(226);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(227);
						match(T__23);
						}
						break;
					}
					} 
				}
				setState(233);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
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
		enterRule(_localctx, 24, RULE_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(234);
			match(T__22);
			setState(235);
			expression(0);
			setState(240);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__12) {
				{
				{
				setState(236);
				match(T__12);
				setState(237);
				expression(0);
				}
				}
				setState(242);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(243);
			match(T__23);
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
		enterRule(_localctx, 26, RULE_map);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245);
			match(T__22);
			setState(246);
			mappair();
			setState(251);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__12) {
				{
				{
				setState(247);
				match(T__12);
				setState(248);
				mappair();
				}
				}
				setState(253);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(254);
			match(T__23);
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
		enterRule(_localctx, 28, RULE_mappair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256);
			((MappairContext)_localctx).mapkey = expression(0);
			setState(257);
			match(T__21);
			setState(258);
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
		enterRule(_localctx, 30, RULE_klass);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(260);
			match(T__25);
			setState(261);
			match(CLASSID);
			setState(262);
			match(T__3);
			setState(271);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(263);
				klassvar();
				setState(268);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__12) {
					{
					{
					setState(264);
					match(T__12);
					setState(265);
					klassvar();
					}
					}
					setState(270);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(273);
			match(T__4);
			setState(283);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				{
				setState(274);
				match(T__26);
				setState(275);
				klassparent(0);
				setState(280);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(276);
						match(T__12);
						setState(277);
						klassparent(0);
						}
						} 
					}
					setState(282);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
				}
				}
				break;
			}
			setState(289);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				{
				setState(285);
				match(T__15);
				setState(286);
				block();
				setState(287);
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
		enterRule(_localctx, 32, RULE_klassvar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(291);
			match(ID);
			setState(293);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << ID) | (1L << CLASSID))) != 0)) {
				{
				setState(292);
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
		int _startState = 34;
		enterRecursionRule(_localctx, 34, RULE_klassparent, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(298);
			switch (_input.LA(1)) {
			case CLASSID:
				{
				setState(296);
				match(CLASSID);
				}
				break;
			case ID:
				{
				setState(297);
				match(ID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(314);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new KlassparentContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_klassparent);
					setState(300);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(301);
					match(T__22);
					setState(302);
					klassparent(0);
					setState(307);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__12) {
						{
						{
						setState(303);
						match(T__12);
						setState(304);
						klassparent(0);
						}
						}
						setState(309);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(310);
					match(T__23);
					}
					} 
				}
				setState(316);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
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
		enterRule(_localctx, 36, RULE_instantiation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(317);
			match(CLASSID);
			setState(318);
			match(T__3);
			setState(327);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__10) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__18) | (1L << T__22) | (1L << T__25) | (1L << ID) | (1L << CLASSID) | (1L << INTEGER) | (1L << FLOAT) | (1L << STRING))) != 0)) {
				{
				setState(319);
				expression(0);
				setState(324);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__12) {
					{
					{
					setState(320);
					match(T__12);
					setState(321);
					expression(0);
					}
					}
					setState(326);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(329);
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
		enterRule(_localctx, 38, RULE_match);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(331);
			match(T__18);
			setState(332);
			((MatchContext)_localctx).source = expression(0);
			setState(333);
			match(T__26);
			setState(334);
			matchexp();
			setState(335);
			match(T__19);
			setState(336);
			((MatchContext)_localctx).exptrue = expression(0);
			setState(337);
			match(T__20);
			setState(338);
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
		enterRule(_localctx, 40, RULE_matchexp);
		int _la;
		try {
			setState(357);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(341);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(340);
					((MatchexpContext)_localctx).v = match(ID);
					}
				}

				setState(343);
				match(CLASSID);
				setState(344);
				match(T__3);
				setState(353);
				_la = _input.LA(1);
				if (_la==ID || _la==CLASSID) {
					{
					setState(345);
					matchexp();
					setState(350);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__12) {
						{
						{
						setState(346);
						match(T__12);
						setState(347);
						matchexp();
						}
						}
						setState(352);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(355);
				match(T__4);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(356);
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
		case 11:
			return tydef_sempred((TydefContext)_localctx, predIndex);
		case 17:
			return klassparent_sempred((KlassparentContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 8);
		case 1:
			return precpred(_ctx, 7);
		case 2:
			return precpred(_ctx, 6);
		case 3:
			return precpred(_ctx, 10);
		case 4:
			return precpred(_ctx, 9);
		}
		return true;
	}
	private boolean tydef_sempred(TydefContext _localctx, int predIndex) {
		switch (predIndex) {
		case 5:
			return precpred(_ctx, 3);
		case 6:
			return precpred(_ctx, 2);
		case 7:
			return precpred(_ctx, 4);
		}
		return true;
	}
	private boolean klassparent_sempred(KlassparentContext _localctx, int predIndex) {
		switch (predIndex) {
		case 8:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3#\u016a\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\2\7\2\60\n\2\f\2\16"+
		"\2\63\13\2\3\2\3\2\3\3\3\3\3\3\3\3\5\3;\n\3\3\3\3\3\3\3\3\4\3\4\6\4B\n"+
		"\4\r\4\16\4C\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7"+
		"\5T\n\5\f\5\16\5W\13\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\5\5k\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\7\5|\n\5\f\5\16\5\177\13\5\3\6\5\6\u0082\n\6"+
		"\3\6\3\6\5\6\u0086\n\6\3\6\3\6\3\6\3\6\5\6\u008c\n\6\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\7\7\u0096\n\7\f\7\16\7\u0099\13\7\3\7\3\7\3\7\3\7\5\7\u009f"+
		"\n\7\3\b\3\b\5\b\u00a3\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u00ad\n"+
		"\t\f\t\16\t\u00b0\13\t\3\t\3\t\5\t\u00b4\n\t\3\n\3\n\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\7\r\u00c8\n\r\f"+
		"\r\16\r\u00cb\13\r\3\r\3\r\3\r\3\r\5\r\u00d1\n\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u00e1\n\r\f\r\16\r\u00e4\13\r\3"+
		"\r\3\r\7\r\u00e8\n\r\f\r\16\r\u00eb\13\r\3\16\3\16\3\16\3\16\7\16\u00f1"+
		"\n\16\f\16\16\16\u00f4\13\16\3\16\3\16\3\17\3\17\3\17\3\17\7\17\u00fc"+
		"\n\17\f\17\16\17\u00ff\13\17\3\17\3\17\3\20\3\20\3\20\3\20\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\7\21\u010d\n\21\f\21\16\21\u0110\13\21\5\21\u0112\n"+
		"\21\3\21\3\21\3\21\3\21\3\21\7\21\u0119\n\21\f\21\16\21\u011c\13\21\5"+
		"\21\u011e\n\21\3\21\3\21\3\21\3\21\5\21\u0124\n\21\3\22\3\22\5\22\u0128"+
		"\n\22\3\23\3\23\3\23\5\23\u012d\n\23\3\23\3\23\3\23\3\23\3\23\7\23\u0134"+
		"\n\23\f\23\16\23\u0137\13\23\3\23\3\23\7\23\u013b\n\23\f\23\16\23\u013e"+
		"\13\23\3\24\3\24\3\24\3\24\3\24\7\24\u0145\n\24\f\24\16\24\u0148\13\24"+
		"\5\24\u014a\n\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\26\5\26\u0158\n\26\3\26\3\26\3\26\3\26\3\26\7\26\u015f\n\26\f\26\16"+
		"\26\u0162\13\26\5\26\u0164\n\26\3\26\3\26\5\26\u0168\n\26\3\26\2\5\b\30"+
		"$\27\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*\2\6\3\2\36\37\3\2\b"+
		"\t\3\2\n\13\3\2\f\r\u018e\2,\3\2\2\2\4\66\3\2\2\2\6A\3\2\2\2\bj\3\2\2"+
		"\2\n\u008b\3\2\2\2\f\u009e\3\2\2\2\16\u00a0\3\2\2\2\20\u00b3\3\2\2\2\22"+
		"\u00b5\3\2\2\2\24\u00b7\3\2\2\2\26\u00be\3\2\2\2\30\u00d0\3\2\2\2\32\u00ec"+
		"\3\2\2\2\34\u00f7\3\2\2\2\36\u0102\3\2\2\2 \u0106\3\2\2\2\"\u0125\3\2"+
		"\2\2$\u012c\3\2\2\2&\u013f\3\2\2\2(\u014d\3\2\2\2*\u0167\3\2\2\2,-\7\3"+
		"\2\2-\61\7\36\2\2.\60\5\4\3\2/.\3\2\2\2\60\63\3\2\2\2\61/\3\2\2\2\61\62"+
		"\3\2\2\2\62\64\3\2\2\2\63\61\3\2\2\2\64\65\5\6\4\2\65\3\3\2\2\2\66\67"+
		"\7\4\2\2\67:\7\36\2\289\7\5\2\29;\7\36\2\2:8\3\2\2\2:;\3\2\2\2;<\3\2\2"+
		"\2<=\7\5\2\2=>\t\2\2\2>\5\3\2\2\2?B\5\b\5\2@B\5\26\f\2A?\3\2\2\2A@\3\2"+
		"\2\2BC\3\2\2\2CA\3\2\2\2CD\3\2\2\2D\7\3\2\2\2EF\b\5\1\2FG\7\36\2\2GH\7"+
		"\16\2\2Hk\5\b\5\5IJ\7\36\2\2JK\7\6\2\2KL\7\7\2\2LM\7\16\2\2Mk\5\b\5\4"+
		"NO\7\36\2\2OP\7\6\2\2PU\5\16\b\2QR\7\17\2\2RT\5\16\b\2SQ\3\2\2\2TW\3\2"+
		"\2\2US\3\2\2\2UV\3\2\2\2VX\3\2\2\2WU\3\2\2\2XY\7\7\2\2YZ\7\16\2\2Z[\5"+
		"\b\5\3[k\3\2\2\2\\k\5\n\6\2]k\5\f\7\2^k\5\20\t\2_k\5\22\n\2`k\5\24\13"+
		"\2ak\5(\25\2bc\7\6\2\2cd\5\b\5\2de\7\7\2\2ek\3\2\2\2fk\5\32\16\2gk\5\34"+
		"\17\2hk\5 \21\2ik\5&\24\2jE\3\2\2\2jI\3\2\2\2jN\3\2\2\2j\\\3\2\2\2j]\3"+
		"\2\2\2j^\3\2\2\2j_\3\2\2\2j`\3\2\2\2ja\3\2\2\2jb\3\2\2\2jf\3\2\2\2jg\3"+
		"\2\2\2jh\3\2\2\2ji\3\2\2\2k}\3\2\2\2lm\f\n\2\2mn\t\3\2\2n|\5\b\5\13op"+
		"\f\t\2\2pq\t\4\2\2q|\5\b\5\nrs\f\b\2\2st\t\5\2\2t|\5\b\5\tuv\f\f\2\2v"+
		"w\7\5\2\2w|\5\20\t\2xy\f\13\2\2yz\7\5\2\2z|\7\36\2\2{l\3\2\2\2{o\3\2\2"+
		"\2{r\3\2\2\2{u\3\2\2\2{x\3\2\2\2|\177\3\2\2\2}{\3\2\2\2}~\3\2\2\2~\t\3"+
		"\2\2\2\177}\3\2\2\2\u0080\u0082\7\r\2\2\u0081\u0080\3\2\2\2\u0081\u0082"+
		"\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u008c\7 \2\2\u0084\u0086\7\r\2\2\u0085"+
		"\u0084\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u008c\7!"+
		"\2\2\u0088\u008c\7\"\2\2\u0089\u008c\7\20\2\2\u008a\u008c\7\21\2\2\u008b"+
		"\u0081\3\2\2\2\u008b\u0085\3\2\2\2\u008b\u0088\3\2\2\2\u008b\u0089\3\2"+
		"\2\2\u008b\u008a\3\2\2\2\u008c\13\3\2\2\2\u008d\u008e\7\22\2\2\u008e\u008f"+
		"\5\6\4\2\u008f\u0090\7\23\2\2\u0090\u009f\3\2\2\2\u0091\u0092\7\22\2\2"+
		"\u0092\u0097\5\16\b\2\u0093\u0094\7\17\2\2\u0094\u0096\5\16\b\2\u0095"+
		"\u0093\3\2\2\2\u0096\u0099\3\2\2\2\u0097\u0095\3\2\2\2\u0097\u0098\3\2"+
		"\2\2\u0098\u009a\3\2\2\2\u0099\u0097\3\2\2\2\u009a\u009b\7\24\2\2\u009b"+
		"\u009c\5\6\4\2\u009c\u009d\7\23\2\2\u009d\u009f\3\2\2\2\u009e\u008d\3"+
		"\2\2\2\u009e\u0091\3\2\2\2\u009f\r\3\2\2\2\u00a0\u00a2\7\36\2\2\u00a1"+
		"\u00a3\5\30\r\2\u00a2\u00a1\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\17\3\2\2"+
		"\2\u00a4\u00a5\7\36\2\2\u00a5\u00a6\7\6\2\2\u00a6\u00b4\7\7\2\2\u00a7"+
		"\u00a8\7\36\2\2\u00a8\u00a9\7\6\2\2\u00a9\u00ae\5\b\5\2\u00aa\u00ab\7"+
		"\17\2\2\u00ab\u00ad\5\b\5\2\u00ac\u00aa\3\2\2\2\u00ad\u00b0\3\2\2\2\u00ae"+
		"\u00ac\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00b1\3\2\2\2\u00b0\u00ae\3\2"+
		"\2\2\u00b1\u00b2\7\7\2\2\u00b2\u00b4\3\2\2\2\u00b3\u00a4\3\2\2\2\u00b3"+
		"\u00a7\3\2\2\2\u00b4\21\3\2\2\2\u00b5\u00b6\7\36\2\2\u00b6\23\3\2\2\2"+
		"\u00b7\u00b8\7\25\2\2\u00b8\u00b9\5\b\5\2\u00b9\u00ba\7\26\2\2\u00ba\u00bb"+
		"\5\b\5\2\u00bb\u00bc\7\27\2\2\u00bc\u00bd\5\b\5\2\u00bd\25\3\2\2\2\u00be"+
		"\u00bf\7\36\2\2\u00bf\u00c0\7\30\2\2\u00c0\u00c1\5\30\r\2\u00c1\27\3\2"+
		"\2\2\u00c2\u00c3\b\r\1\2\u00c3\u00d1\7\37\2\2\u00c4\u00c9\7\36\2\2\u00c5"+
		"\u00c6\7\f\2\2\u00c6\u00c8\5\30\r\2\u00c7\u00c5\3\2\2\2\u00c8\u00cb\3"+
		"\2\2\2\u00c9\u00c7\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00d1\3\2\2\2\u00cb"+
		"\u00c9\3\2\2\2\u00cc\u00cd\7\6\2\2\u00cd\u00ce\5\30\r\2\u00ce\u00cf\7"+
		"\7\2\2\u00cf\u00d1\3\2\2\2\u00d0\u00c2\3\2\2\2\u00d0\u00c4\3\2\2\2\u00d0"+
		"\u00cc\3\2\2\2\u00d1\u00e9\3\2\2\2\u00d2\u00d3\f\5\2\2\u00d3\u00d4\7\33"+
		"\2\2\u00d4\u00e8\5\30\r\6\u00d5\u00d6\f\4\2\2\u00d6\u00d7\7\17\2\2\u00d7"+
		"\u00d8\5\30\r\2\u00d8\u00d9\7\33\2\2\u00d9\u00da\5\30\r\5\u00da\u00e8"+
		"\3\2\2\2\u00db\u00dc\f\6\2\2\u00dc\u00dd\7\31\2\2\u00dd\u00e2\5\30\r\2"+
		"\u00de\u00df\7\17\2\2\u00df\u00e1\5\30\r\2\u00e0\u00de\3\2\2\2\u00e1\u00e4"+
		"\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3\u00e5\3\2\2\2\u00e4"+
		"\u00e2\3\2\2\2\u00e5\u00e6\7\32\2\2\u00e6\u00e8\3\2\2\2\u00e7\u00d2\3"+
		"\2\2\2\u00e7\u00d5\3\2\2\2\u00e7\u00db\3\2\2\2\u00e8\u00eb\3\2\2\2\u00e9"+
		"\u00e7\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\31\3\2\2\2\u00eb\u00e9\3\2\2"+
		"\2\u00ec\u00ed\7\31\2\2\u00ed\u00f2\5\b\5\2\u00ee\u00ef\7\17\2\2\u00ef"+
		"\u00f1\5\b\5\2\u00f0\u00ee\3\2\2\2\u00f1\u00f4\3\2\2\2\u00f2\u00f0\3\2"+
		"\2\2\u00f2\u00f3\3\2\2\2\u00f3\u00f5\3\2\2\2\u00f4\u00f2\3\2\2\2\u00f5"+
		"\u00f6\7\32\2\2\u00f6\33\3\2\2\2\u00f7\u00f8\7\31\2\2\u00f8\u00fd\5\36"+
		"\20\2\u00f9\u00fa\7\17\2\2\u00fa\u00fc\5\36\20\2\u00fb\u00f9\3\2\2\2\u00fc"+
		"\u00ff\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe\u0100\3\2"+
		"\2\2\u00ff\u00fd\3\2\2\2\u0100\u0101\7\32\2\2\u0101\35\3\2\2\2\u0102\u0103"+
		"\5\b\5\2\u0103\u0104\7\30\2\2\u0104\u0105\5\b\5\2\u0105\37\3\2\2\2\u0106"+
		"\u0107\7\34\2\2\u0107\u0108\7\37\2\2\u0108\u0111\7\6\2\2\u0109\u010e\5"+
		"\"\22\2\u010a\u010b\7\17\2\2\u010b\u010d\5\"\22\2\u010c\u010a\3\2\2\2"+
		"\u010d\u0110\3\2\2\2\u010e\u010c\3\2\2\2\u010e\u010f\3\2\2\2\u010f\u0112"+
		"\3\2\2\2\u0110\u010e\3\2\2\2\u0111\u0109\3\2\2\2\u0111\u0112\3\2\2\2\u0112"+
		"\u0113\3\2\2\2\u0113\u011d\7\7\2\2\u0114\u0115\7\35\2\2\u0115\u011a\5"+
		"$\23\2\u0116\u0117\7\17\2\2\u0117\u0119\5$\23\2\u0118\u0116\3\2\2\2\u0119"+
		"\u011c\3\2\2\2\u011a\u0118\3\2\2\2\u011a\u011b\3\2\2\2\u011b\u011e\3\2"+
		"\2\2\u011c\u011a\3\2\2\2\u011d\u0114\3\2\2\2\u011d\u011e\3\2\2\2\u011e"+
		"\u0123\3\2\2\2\u011f\u0120\7\22\2\2\u0120\u0121\5\6\4\2\u0121\u0122\7"+
		"\23\2\2\u0122\u0124\3\2\2\2\u0123\u011f\3\2\2\2\u0123\u0124\3\2\2\2\u0124"+
		"!\3\2\2\2\u0125\u0127\7\36\2\2\u0126\u0128\5\30\r\2\u0127\u0126\3\2\2"+
		"\2\u0127\u0128\3\2\2\2\u0128#\3\2\2\2\u0129\u012a\b\23\1\2\u012a\u012d"+
		"\7\37\2\2\u012b\u012d\7\36\2\2\u012c\u0129\3\2\2\2\u012c\u012b\3\2\2\2"+
		"\u012d\u013c\3\2\2\2\u012e\u012f\f\3\2\2\u012f\u0130\7\31\2\2\u0130\u0135"+
		"\5$\23\2\u0131\u0132\7\17\2\2\u0132\u0134\5$\23\2\u0133\u0131\3\2\2\2"+
		"\u0134\u0137\3\2\2\2\u0135\u0133\3\2\2\2\u0135\u0136\3\2\2\2\u0136\u0138"+
		"\3\2\2\2\u0137\u0135\3\2\2\2\u0138\u0139\7\32\2\2\u0139\u013b\3\2\2\2"+
		"\u013a\u012e\3\2\2\2\u013b\u013e\3\2\2\2\u013c\u013a\3\2\2\2\u013c\u013d"+
		"\3\2\2\2\u013d%\3\2\2\2\u013e\u013c\3\2\2\2\u013f\u0140\7\37\2\2\u0140"+
		"\u0149\7\6\2\2\u0141\u0146\5\b\5\2\u0142\u0143\7\17\2\2\u0143\u0145\5"+
		"\b\5\2\u0144\u0142\3\2\2\2\u0145\u0148\3\2\2\2\u0146\u0144\3\2\2\2\u0146"+
		"\u0147\3\2\2\2\u0147\u014a\3\2\2\2\u0148\u0146\3\2\2\2\u0149\u0141\3\2"+
		"\2\2\u0149\u014a\3\2\2\2\u014a\u014b\3\2\2\2\u014b\u014c\7\7\2\2\u014c"+
		"\'\3\2\2\2\u014d\u014e\7\25\2\2\u014e\u014f\5\b\5\2\u014f\u0150\7\35\2"+
		"\2\u0150\u0151\5*\26\2\u0151\u0152\7\26\2\2\u0152\u0153\5\b\5\2\u0153"+
		"\u0154\7\27\2\2\u0154\u0155\5\b\5\2\u0155)\3\2\2\2\u0156\u0158\7\36\2"+
		"\2\u0157\u0156\3\2\2\2\u0157\u0158\3\2\2\2\u0158\u0159\3\2\2\2\u0159\u015a"+
		"\7\37\2\2\u015a\u0163\7\6\2\2\u015b\u0160\5*\26\2\u015c\u015d\7\17\2\2"+
		"\u015d\u015f\5*\26\2\u015e\u015c\3\2\2\2\u015f\u0162\3\2\2\2\u0160\u015e"+
		"\3\2\2\2\u0160\u0161\3\2\2\2\u0161\u0164\3\2\2\2\u0162\u0160\3\2\2\2\u0163"+
		"\u015b\3\2\2\2\u0163\u0164\3\2\2\2\u0164\u0165\3\2\2\2\u0165\u0168\7\7"+
		"\2\2\u0166\u0168\7\36\2\2\u0167\u0157\3\2\2\2\u0167\u0166\3\2\2\2\u0168"+
		"+\3\2\2\2(\61:ACUj{}\u0081\u0085\u008b\u0097\u009e\u00a2\u00ae\u00b3\u00c9"+
		"\u00d0\u00e2\u00e7\u00e9\u00f2\u00fd\u010e\u0111\u011a\u011d\u0123\u0127"+
		"\u012c\u0135\u013c\u0146\u0149\u0157\u0160\u0163\u0167";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}