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
		RULE_fn = 5, RULE_fnargpair = 6, RULE_def = 7, RULE_apply = 8, RULE_objapply = 9, 
		RULE_objfield = 10, RULE_ref = 11, RULE_cond = 12, RULE_forward = 13, 
		RULE_tydef = 14, RULE_list = 15, RULE_map = 16, RULE_mappair = 17, RULE_klass = 18, 
		RULE_klassvar = 19, RULE_klassparent = 20, RULE_instantiation = 21;
	public static final String[] ruleNames = {
		"file", "imp", "block", "expression", "value", "fn", "fnargpair", "def", 
		"apply", "objapply", "objfield", "ref", "cond", "forward", "tydef", "list", 
		"map", "mappair", "klass", "klassvar", "klassparent", "instantiation"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'module'", "'import'", "'.'", "'as'", "'('", "')'", "'=='", "'*'", 
		"'/'", "'+'", "'-'", "'{'", "'}'", "','", "'=>'", "'='", "'if'", "'then'", 
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
			setState(44);
			match(T__0);
			setState(45);
			((FileContext)_localctx).name = match(ID);
			setState(49);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(46);
				imp();
				}
				}
				setState(51);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(52);
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
			setState(54);
			match(T__1);
			setState(55);
			match(ID);
			setState(58); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(56);
				match(T__2);
				setState(57);
				match(ID);
				}
				}
				setState(60); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__2 );
			setState(64);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(62);
				match(T__3);
				setState(63);
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
			setState(68); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(68);
				switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(66);
					expression(0);
					}
					break;
				case 2:
					{
					setState(67);
					forward();
					}
					break;
				}
				}
				setState(70); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__11) | (1L << T__16) | (1L << T__20) | (1L << T__23) | (1L << ID) | (1L << CLASSID) | (1L << INTEGER) | (1L << FLOAT) | (1L << STRING))) != 0) );
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
		public ExpressionContext exp;
		public Token binop;
		public ExpressionContext right;
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public FnContext fn() {
			return getRuleContext(FnContext.class,0);
		}
		public DefContext def() {
			return getRuleContext(DefContext.class,0);
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
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
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
			setState(89);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(73);
				value();
				}
				break;
			case 2:
				{
				setState(74);
				fn();
				}
				break;
			case 3:
				{
				setState(75);
				def();
				}
				break;
			case 4:
				{
				setState(76);
				apply();
				}
				break;
			case 5:
				{
				setState(77);
				objapply();
				}
				break;
			case 6:
				{
				setState(78);
				ref();
				}
				break;
			case 7:
				{
				setState(79);
				cond();
				}
				break;
			case 8:
				{
				setState(80);
				match(T__4);
				setState(81);
				((ExpressionContext)_localctx).exp = expression(0);
				setState(82);
				match(T__5);
				}
				break;
			case 9:
				{
				setState(84);
				list();
				}
				break;
			case 10:
				{
				setState(85);
				map();
				}
				break;
			case 11:
				{
				setState(86);
				klass();
				}
				break;
			case 12:
				{
				setState(87);
				instantiation();
				}
				break;
			case 13:
				{
				setState(88);
				objfield();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(102);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(100);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(91);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(92);
						((ExpressionContext)_localctx).binop = match(T__6);
						setState(93);
						((ExpressionContext)_localctx).right = expression(7);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(94);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(95);
						((ExpressionContext)_localctx).binop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__7 || _la==T__8) ) {
							((ExpressionContext)_localctx).binop = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(96);
						((ExpressionContext)_localctx).right = expression(6);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(97);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(98);
						((ExpressionContext)_localctx).binop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__9 || _la==T__10) ) {
							((ExpressionContext)_localctx).binop = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(99);
						((ExpressionContext)_localctx).right = expression(5);
						}
						break;
					}
					} 
				}
				setState(104);
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
			setState(105);
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
			setState(124);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(107);
				match(T__11);
				setState(108);
				block();
				setState(109);
				match(T__12);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(111);
				match(T__11);
				setState(112);
				fnargpair();
				setState(117);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__13) {
					{
					{
					setState(113);
					match(T__13);
					setState(114);
					fnargpair();
					}
					}
					setState(119);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(120);
				match(T__14);
				setState(121);
				block();
				setState(122);
				match(T__12);
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
			setState(126);
			match(ID);
			setState(128);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << ID) | (1L << CLASSID))) != 0)) {
				{
				setState(127);
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

	public static class DefContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(GrammarParser.ID, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public DefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefContext def() throws RecognitionException {
		DefContext _localctx = new DefContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_def);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			match(ID);
			setState(131);
			match(T__15);
			setState(132);
			expression(0);
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
		enterRule(_localctx, 16, RULE_apply);
		int _la;
		try {
			setState(149);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(134);
				match(ID);
				setState(135);
				match(T__4);
				setState(136);
				match(T__5);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(137);
				match(ID);
				setState(138);
				match(T__4);
				setState(139);
				expression(0);
				setState(144);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__13) {
					{
					{
					setState(140);
					match(T__13);
					setState(141);
					expression(0);
					}
					}
					setState(146);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(147);
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
		enterRule(_localctx, 18, RULE_objapply);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			ref();
			setState(152);
			match(T__2);
			setState(153);
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
		enterRule(_localctx, 20, RULE_objfield);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			ref();
			setState(156);
			match(T__2);
			setState(157);
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
		enterRule(_localctx, 22, RULE_ref);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
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
		enterRule(_localctx, 24, RULE_cond);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			match(T__16);
			setState(162);
			((CondContext)_localctx).condition = expression(0);
			setState(163);
			match(T__17);
			setState(164);
			((CondContext)_localctx).exptrue = expression(0);
			setState(165);
			match(T__18);
			setState(166);
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
		enterRule(_localctx, 26, RULE_forward);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			match(ID);
			setState(169);
			match(T__19);
			setState(170);
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
		int _startState = 28;
		enterRecursionRule(_localctx, 28, RULE_tydef, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			switch (_input.LA(1)) {
			case CLASSID:
				{
				setState(173);
				match(CLASSID);
				}
				break;
			case ID:
				{
				setState(174);
				match(ID);
				setState(179);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(175);
						match(T__9);
						setState(176);
						tydef(0);
						}
						} 
					}
					setState(181);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
				}
				}
				break;
			case T__4:
				{
				setState(182);
				match(T__4);
				setState(183);
				tydef(0);
				setState(184);
				match(T__5);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(208);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(206);
					switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
					case 1:
						{
						_localctx = new TydefContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_tydef);
						setState(188);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(189);
						match(T__22);
						setState(190);
						tydef(4);
						}
						break;
					case 2:
						{
						_localctx = new TydefContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_tydef);
						setState(191);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(192);
						match(T__13);
						setState(193);
						tydef(3);
						}
						break;
					case 3:
						{
						_localctx = new TydefContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_tydef);
						setState(194);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(195);
						match(T__20);
						setState(196);
						tydef(0);
						setState(201);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==T__13) {
							{
							{
							setState(197);
							match(T__13);
							setState(198);
							tydef(0);
							}
							}
							setState(203);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(204);
						match(T__21);
						}
						break;
					}
					} 
				}
				setState(210);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
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
		enterRule(_localctx, 30, RULE_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(211);
			match(T__20);
			setState(212);
			expression(0);
			setState(217);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__13) {
				{
				{
				setState(213);
				match(T__13);
				setState(214);
				expression(0);
				}
				}
				setState(219);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(220);
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
		enterRule(_localctx, 32, RULE_map);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(222);
			match(T__20);
			setState(223);
			mappair();
			setState(228);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__13) {
				{
				{
				setState(224);
				match(T__13);
				setState(225);
				mappair();
				}
				}
				setState(230);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(231);
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
		enterRule(_localctx, 34, RULE_mappair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			((MappairContext)_localctx).mapkey = expression(0);
			setState(234);
			match(T__19);
			setState(235);
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
		enterRule(_localctx, 36, RULE_klass);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(237);
			match(T__23);
			setState(238);
			match(CLASSID);
			setState(239);
			match(T__4);
			setState(248);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(240);
				klassvar();
				setState(245);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__13) {
					{
					{
					setState(241);
					match(T__13);
					setState(242);
					klassvar();
					}
					}
					setState(247);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(250);
			match(T__5);
			setState(260);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				setState(251);
				match(T__24);
				setState(252);
				klassparent(0);
				setState(257);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(253);
						match(T__13);
						setState(254);
						klassparent(0);
						}
						} 
					}
					setState(259);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
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
		enterRule(_localctx, 38, RULE_klassvar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
			match(ID);
			setState(263);
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
		int _startState = 40;
		enterRecursionRule(_localctx, 40, RULE_klassparent, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(268);
			switch (_input.LA(1)) {
			case CLASSID:
				{
				setState(266);
				match(CLASSID);
				}
				break;
			case ID:
				{
				setState(267);
				match(ID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(287);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(285);
					switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
					case 1:
						{
						_localctx = new KlassparentContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_klassparent);
						setState(270);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(271);
						match(T__13);
						setState(272);
						klassparent(2);
						}
						break;
					case 2:
						{
						_localctx = new KlassparentContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_klassparent);
						setState(273);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(274);
						match(T__20);
						setState(275);
						klassparent(0);
						setState(280);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==T__13) {
							{
							{
							setState(276);
							match(T__13);
							setState(277);
							klassparent(0);
							}
							}
							setState(282);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(283);
						match(T__21);
						}
						break;
					}
					} 
				}
				setState(289);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
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
		enterRule(_localctx, 42, RULE_instantiation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(290);
			match(CLASSID);
			setState(291);
			match(T__4);
			setState(300);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__11) | (1L << T__16) | (1L << T__20) | (1L << T__23) | (1L << ID) | (1L << CLASSID) | (1L << INTEGER) | (1L << FLOAT) | (1L << STRING))) != 0)) {
				{
				setState(292);
				expression(0);
				setState(297);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__13) {
					{
					{
					setState(293);
					match(T__13);
					setState(294);
					expression(0);
					}
					}
					setState(299);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(302);
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
		case 14:
			return tydef_sempred((TydefContext)_localctx, predIndex);
		case 20:
			return klassparent_sempred((KlassparentContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 6);
		case 1:
			return precpred(_ctx, 5);
		case 2:
			return precpred(_ctx, 4);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3!\u0133\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3\2\3\2\7\2\62"+
		"\n\2\f\2\16\2\65\13\2\3\2\3\2\3\3\3\3\3\3\3\3\6\3=\n\3\r\3\16\3>\3\3\3"+
		"\3\5\3C\n\3\3\4\3\4\6\4G\n\4\r\4\16\4H\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\\\n\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\7\5g\n\5\f\5\16\5j\13\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\7\7v\n\7\f\7\16\7y\13\7\3\7\3\7\3\7\3\7\5\7\177\n\7\3\b\3\b\5\b"+
		"\u0083\n\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u0091\n"+
		"\n\f\n\16\n\u0094\13\n\3\n\3\n\5\n\u0098\n\n\3\13\3\13\3\13\3\13\3\f\3"+
		"\f\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3"+
		"\17\3\20\3\20\3\20\3\20\3\20\7\20\u00b4\n\20\f\20\16\20\u00b7\13\20\3"+
		"\20\3\20\3\20\3\20\5\20\u00bd\n\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\7\20\u00ca\n\20\f\20\16\20\u00cd\13\20\3\20\3\20"+
		"\7\20\u00d1\n\20\f\20\16\20\u00d4\13\20\3\21\3\21\3\21\3\21\7\21\u00da"+
		"\n\21\f\21\16\21\u00dd\13\21\3\21\3\21\3\22\3\22\3\22\3\22\7\22\u00e5"+
		"\n\22\f\22\16\22\u00e8\13\22\3\22\3\22\3\23\3\23\3\23\3\23\3\24\3\24\3"+
		"\24\3\24\3\24\3\24\7\24\u00f6\n\24\f\24\16\24\u00f9\13\24\5\24\u00fb\n"+
		"\24\3\24\3\24\3\24\3\24\3\24\7\24\u0102\n\24\f\24\16\24\u0105\13\24\5"+
		"\24\u0107\n\24\3\25\3\25\3\25\3\26\3\26\3\26\5\26\u010f\n\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\7\26\u0119\n\26\f\26\16\26\u011c\13\26"+
		"\3\26\3\26\7\26\u0120\n\26\f\26\16\26\u0123\13\26\3\27\3\27\3\27\3\27"+
		"\3\27\7\27\u012a\n\27\f\27\16\27\u012d\13\27\5\27\u012f\n\27\3\27\3\27"+
		"\3\27\2\5\b\36*\30\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,\2\5"+
		"\3\2\n\13\3\2\f\r\3\2\36 \u0148\2.\3\2\2\2\48\3\2\2\2\6F\3\2\2\2\b[\3"+
		"\2\2\2\nk\3\2\2\2\f~\3\2\2\2\16\u0080\3\2\2\2\20\u0084\3\2\2\2\22\u0097"+
		"\3\2\2\2\24\u0099\3\2\2\2\26\u009d\3\2\2\2\30\u00a1\3\2\2\2\32\u00a3\3"+
		"\2\2\2\34\u00aa\3\2\2\2\36\u00bc\3\2\2\2 \u00d5\3\2\2\2\"\u00e0\3\2\2"+
		"\2$\u00eb\3\2\2\2&\u00ef\3\2\2\2(\u0108\3\2\2\2*\u010e\3\2\2\2,\u0124"+
		"\3\2\2\2./\7\3\2\2/\63\7\34\2\2\60\62\5\4\3\2\61\60\3\2\2\2\62\65\3\2"+
		"\2\2\63\61\3\2\2\2\63\64\3\2\2\2\64\66\3\2\2\2\65\63\3\2\2\2\66\67\5\6"+
		"\4\2\67\3\3\2\2\289\7\4\2\29<\7\34\2\2:;\7\5\2\2;=\7\34\2\2<:\3\2\2\2"+
		"=>\3\2\2\2><\3\2\2\2>?\3\2\2\2?B\3\2\2\2@A\7\6\2\2AC\7\34\2\2B@\3\2\2"+
		"\2BC\3\2\2\2C\5\3\2\2\2DG\5\b\5\2EG\5\34\17\2FD\3\2\2\2FE\3\2\2\2GH\3"+
		"\2\2\2HF\3\2\2\2HI\3\2\2\2I\7\3\2\2\2JK\b\5\1\2K\\\5\n\6\2L\\\5\f\7\2"+
		"M\\\5\20\t\2N\\\5\22\n\2O\\\5\24\13\2P\\\5\30\r\2Q\\\5\32\16\2RS\7\7\2"+
		"\2ST\5\b\5\2TU\7\b\2\2U\\\3\2\2\2V\\\5 \21\2W\\\5\"\22\2X\\\5&\24\2Y\\"+
		"\5,\27\2Z\\\5\26\f\2[J\3\2\2\2[L\3\2\2\2[M\3\2\2\2[N\3\2\2\2[O\3\2\2\2"+
		"[P\3\2\2\2[Q\3\2\2\2[R\3\2\2\2[V\3\2\2\2[W\3\2\2\2[X\3\2\2\2[Y\3\2\2\2"+
		"[Z\3\2\2\2\\h\3\2\2\2]^\f\b\2\2^_\7\t\2\2_g\5\b\5\t`a\f\7\2\2ab\t\2\2"+
		"\2bg\5\b\5\bcd\f\6\2\2de\t\3\2\2eg\5\b\5\7f]\3\2\2\2f`\3\2\2\2fc\3\2\2"+
		"\2gj\3\2\2\2hf\3\2\2\2hi\3\2\2\2i\t\3\2\2\2jh\3\2\2\2kl\t\4\2\2l\13\3"+
		"\2\2\2mn\7\16\2\2no\5\6\4\2op\7\17\2\2p\177\3\2\2\2qr\7\16\2\2rw\5\16"+
		"\b\2st\7\20\2\2tv\5\16\b\2us\3\2\2\2vy\3\2\2\2wu\3\2\2\2wx\3\2\2\2xz\3"+
		"\2\2\2yw\3\2\2\2z{\7\21\2\2{|\5\6\4\2|}\7\17\2\2}\177\3\2\2\2~m\3\2\2"+
		"\2~q\3\2\2\2\177\r\3\2\2\2\u0080\u0082\7\34\2\2\u0081\u0083\5\36\20\2"+
		"\u0082\u0081\3\2\2\2\u0082\u0083\3\2\2\2\u0083\17\3\2\2\2\u0084\u0085"+
		"\7\34\2\2\u0085\u0086\7\22\2\2\u0086\u0087\5\b\5\2\u0087\21\3\2\2\2\u0088"+
		"\u0089\7\34\2\2\u0089\u008a\7\7\2\2\u008a\u0098\7\b\2\2\u008b\u008c\7"+
		"\34\2\2\u008c\u008d\7\7\2\2\u008d\u0092\5\b\5\2\u008e\u008f\7\20\2\2\u008f"+
		"\u0091\5\b\5\2\u0090\u008e\3\2\2\2\u0091\u0094\3\2\2\2\u0092\u0090\3\2"+
		"\2\2\u0092\u0093\3\2\2\2\u0093\u0095\3\2\2\2\u0094\u0092\3\2\2\2\u0095"+
		"\u0096\7\b\2\2\u0096\u0098\3\2\2\2\u0097\u0088\3\2\2\2\u0097\u008b\3\2"+
		"\2\2\u0098\23\3\2\2\2\u0099\u009a\5\30\r\2\u009a\u009b\7\5\2\2\u009b\u009c"+
		"\5\22\n\2\u009c\25\3\2\2\2\u009d\u009e\5\30\r\2\u009e\u009f\7\5\2\2\u009f"+
		"\u00a0\7\34\2\2\u00a0\27\3\2\2\2\u00a1\u00a2\7\34\2\2\u00a2\31\3\2\2\2"+
		"\u00a3\u00a4\7\23\2\2\u00a4\u00a5\5\b\5\2\u00a5\u00a6\7\24\2\2\u00a6\u00a7"+
		"\5\b\5\2\u00a7\u00a8\7\25\2\2\u00a8\u00a9\5\b\5\2\u00a9\33\3\2\2\2\u00aa"+
		"\u00ab\7\34\2\2\u00ab\u00ac\7\26\2\2\u00ac\u00ad\5\36\20\2\u00ad\35\3"+
		"\2\2\2\u00ae\u00af\b\20\1\2\u00af\u00bd\7\35\2\2\u00b0\u00b5\7\34\2\2"+
		"\u00b1\u00b2\7\f\2\2\u00b2\u00b4\5\36\20\2\u00b3\u00b1\3\2\2\2\u00b4\u00b7"+
		"\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00bd\3\2\2\2\u00b7"+
		"\u00b5\3\2\2\2\u00b8\u00b9\7\7\2\2\u00b9\u00ba\5\36\20\2\u00ba\u00bb\7"+
		"\b\2\2\u00bb\u00bd\3\2\2\2\u00bc\u00ae\3\2\2\2\u00bc\u00b0\3\2\2\2\u00bc"+
		"\u00b8\3\2\2\2\u00bd\u00d2\3\2\2\2\u00be\u00bf\f\5\2\2\u00bf\u00c0\7\31"+
		"\2\2\u00c0\u00d1\5\36\20\6\u00c1\u00c2\f\4\2\2\u00c2\u00c3\7\20\2\2\u00c3"+
		"\u00d1\5\36\20\5\u00c4\u00c5\f\6\2\2\u00c5\u00c6\7\27\2\2\u00c6\u00cb"+
		"\5\36\20\2\u00c7\u00c8\7\20\2\2\u00c8\u00ca\5\36\20\2\u00c9\u00c7\3\2"+
		"\2\2\u00ca\u00cd\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc"+
		"\u00ce\3\2\2\2\u00cd\u00cb\3\2\2\2\u00ce\u00cf\7\30\2\2\u00cf\u00d1\3"+
		"\2\2\2\u00d0\u00be\3\2\2\2\u00d0\u00c1\3\2\2\2\u00d0\u00c4\3\2\2\2\u00d1"+
		"\u00d4\3\2\2\2\u00d2\u00d0\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\37\3\2\2"+
		"\2\u00d4\u00d2\3\2\2\2\u00d5\u00d6\7\27\2\2\u00d6\u00db\5\b\5\2\u00d7"+
		"\u00d8\7\20\2\2\u00d8\u00da\5\b\5\2\u00d9\u00d7\3\2\2\2\u00da\u00dd\3"+
		"\2\2\2\u00db\u00d9\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\u00de\3\2\2\2\u00dd"+
		"\u00db\3\2\2\2\u00de\u00df\7\30\2\2\u00df!\3\2\2\2\u00e0\u00e1\7\27\2"+
		"\2\u00e1\u00e6\5$\23\2\u00e2\u00e3\7\20\2\2\u00e3\u00e5\5$\23\2\u00e4"+
		"\u00e2\3\2\2\2\u00e5\u00e8\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e6\u00e7\3\2"+
		"\2\2\u00e7\u00e9\3\2\2\2\u00e8\u00e6\3\2\2\2\u00e9\u00ea\7\30\2\2\u00ea"+
		"#\3\2\2\2\u00eb\u00ec\5\b\5\2\u00ec\u00ed\7\26\2\2\u00ed\u00ee\5\b\5\2"+
		"\u00ee%\3\2\2\2\u00ef\u00f0\7\32\2\2\u00f0\u00f1\7\35\2\2\u00f1\u00fa"+
		"\7\7\2\2\u00f2\u00f7\5(\25\2\u00f3\u00f4\7\20\2\2\u00f4\u00f6\5(\25\2"+
		"\u00f5\u00f3\3\2\2\2\u00f6\u00f9\3\2\2\2\u00f7\u00f5\3\2\2\2\u00f7\u00f8"+
		"\3\2\2\2\u00f8\u00fb\3\2\2\2\u00f9\u00f7\3\2\2\2\u00fa\u00f2\3\2\2\2\u00fa"+
		"\u00fb\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc\u0106\7\b\2\2\u00fd\u00fe\7\33"+
		"\2\2\u00fe\u0103\5*\26\2\u00ff\u0100\7\20\2\2\u0100\u0102\5*\26\2\u0101"+
		"\u00ff\3\2\2\2\u0102\u0105\3\2\2\2\u0103\u0101\3\2\2\2\u0103\u0104\3\2"+
		"\2\2\u0104\u0107\3\2\2\2\u0105\u0103\3\2\2\2\u0106\u00fd\3\2\2\2\u0106"+
		"\u0107\3\2\2\2\u0107\'\3\2\2\2\u0108\u0109\7\34\2\2\u0109\u010a\5\36\20"+
		"\2\u010a)\3\2\2\2\u010b\u010c\b\26\1\2\u010c\u010f\7\35\2\2\u010d\u010f"+
		"\7\34\2\2\u010e\u010b\3\2\2\2\u010e\u010d\3\2\2\2\u010f\u0121\3\2\2\2"+
		"\u0110\u0111\f\3\2\2\u0111\u0112\7\20\2\2\u0112\u0120\5*\26\4\u0113\u0114"+
		"\f\4\2\2\u0114\u0115\7\27\2\2\u0115\u011a\5*\26\2\u0116\u0117\7\20\2\2"+
		"\u0117\u0119\5*\26\2\u0118\u0116\3\2\2\2\u0119\u011c\3\2\2\2\u011a\u0118"+
		"\3\2\2\2\u011a\u011b\3\2\2\2\u011b\u011d\3\2\2\2\u011c\u011a\3\2\2\2\u011d"+
		"\u011e\7\30\2\2\u011e\u0120\3\2\2\2\u011f\u0110\3\2\2\2\u011f\u0113\3"+
		"\2\2\2\u0120\u0123\3\2\2\2\u0121\u011f\3\2\2\2\u0121\u0122\3\2\2\2\u0122"+
		"+\3\2\2\2\u0123\u0121\3\2\2\2\u0124\u0125\7\35\2\2\u0125\u012e\7\7\2\2"+
		"\u0126\u012b\5\b\5\2\u0127\u0128\7\20\2\2\u0128\u012a\5\b\5\2\u0129\u0127"+
		"\3\2\2\2\u012a\u012d\3\2\2\2\u012b\u0129\3\2\2\2\u012b\u012c\3\2\2\2\u012c"+
		"\u012f\3\2\2\2\u012d\u012b\3\2\2\2\u012e\u0126\3\2\2\2\u012e\u012f\3\2"+
		"\2\2\u012f\u0130\3\2\2\2\u0130\u0131\7\b\2\2\u0131-\3\2\2\2 \63>BFH[f"+
		"hw~\u0082\u0092\u0097\u00b5\u00bc\u00cb\u00d0\u00d2\u00db\u00e6\u00f7"+
		"\u00fa\u0103\u0106\u010e\u011a\u011f\u0121\u012b\u012e";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}