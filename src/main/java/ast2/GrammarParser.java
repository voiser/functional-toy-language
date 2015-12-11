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
		T__17=18, ID=19, CLASSID=20, INTEGER=21, FLOAT=22, STRING=23, BINOP=24, 
		WS=25;
	public static final int
		RULE_file = 0, RULE_imp = 1, RULE_block = 2, RULE_expression = 3, RULE_value = 4, 
		RULE_fn = 5, RULE_fnargpair = 6, RULE_def = 7, RULE_apply = 8, RULE_objapply = 9, 
		RULE_ref = 10, RULE_cond = 11, RULE_forward = 12, RULE_tydef = 13, RULE_binary = 14, 
		RULE_binexp = 15, RULE_list = 16, RULE_map = 17, RULE_mappair = 18;
	public static final String[] ruleNames = {
		"file", "imp", "block", "expression", "value", "fn", "fnargpair", "def", 
		"apply", "objapply", "ref", "cond", "forward", "tydef", "binary", "binexp", 
		"list", "map", "mappair"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'module'", "'import'", "'.'", "'as'", "'('", "')'", "'+'", "'{'", 
		"'}'", "','", "'->'", "'='", "'if'", "'then'", "'else'", "':'", "'['", 
		"']'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, "ID", "CLASSID", "INTEGER", 
		"FLOAT", "STRING", "BINOP", "WS"
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
			setState(38);
			match(T__0);
			setState(39);
			((FileContext)_localctx).name = match(ID);
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(40);
				imp();
				}
				}
				setState(45);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(46);
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
			setState(48);
			match(T__1);
			setState(49);
			match(ID);
			setState(52); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(50);
				match(T__2);
				setState(51);
				match(ID);
				}
				}
				setState(54); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__2 );
			setState(58);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(56);
				match(T__3);
				setState(57);
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
			setState(62); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(62);
				switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(60);
					expression();
					}
					break;
				case 2:
					{
					setState(61);
					forward();
					}
					break;
				}
				}
				setState(64); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__7) | (1L << T__12) | (1L << T__16) | (1L << ID) | (1L << INTEGER) | (1L << FLOAT) | (1L << STRING))) != 0) );
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
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ListContext list() {
			return getRuleContext(ListContext.class,0);
		}
		public MapContext map() {
			return getRuleContext(MapContext.class,0);
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
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_expression);
		try {
			setState(79);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(66);
				value(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(67);
				fn();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(68);
				def();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(69);
				apply();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(70);
				objapply();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(71);
				ref();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(72);
				cond();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(73);
				match(T__4);
				setState(74);
				expression();
				setState(75);
				match(T__5);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(77);
				list();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(78);
				map();
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

	public static class ValueContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(GrammarParser.INTEGER, 0); }
		public TerminalNode FLOAT() { return getToken(GrammarParser.FLOAT, 0); }
		public TerminalNode STRING() { return getToken(GrammarParser.STRING, 0); }
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
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
		return value(0);
	}

	private ValueContext value(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ValueContext _localctx = new ValueContext(_ctx, _parentState);
		ValueContext _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_value, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			switch (_input.LA(1)) {
			case INTEGER:
				{
				setState(82);
				match(INTEGER);
				}
				break;
			case FLOAT:
				{
				setState(83);
				match(FLOAT);
				}
				break;
			case STRING:
				{
				setState(84);
				match(STRING);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(92);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ValueContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_value);
					setState(87);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					{
					setState(88);
					match(T__6);
					setState(89);
					value(0);
					}
					}
					} 
				}
				setState(94);
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
			setState(112);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(95);
				match(T__7);
				setState(96);
				block();
				setState(97);
				match(T__8);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(99);
				match(T__7);
				setState(100);
				fnargpair();
				setState(105);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__9) {
					{
					{
					setState(101);
					match(T__9);
					setState(102);
					fnargpair();
					}
					}
					setState(107);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(108);
				match(T__10);
				setState(109);
				block();
				setState(110);
				match(T__8);
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
		public TerminalNode CLASSID() { return getToken(GrammarParser.CLASSID, 0); }
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
			setState(114);
			match(ID);
			setState(116);
			_la = _input.LA(1);
			if (_la==CLASSID) {
				{
				setState(115);
				match(CLASSID);
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
			setState(118);
			match(ID);
			setState(119);
			match(T__11);
			setState(120);
			expression();
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
			setState(137);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(122);
				match(ID);
				setState(123);
				match(T__4);
				setState(124);
				match(T__5);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(125);
				match(ID);
				setState(126);
				match(T__4);
				setState(127);
				expression();
				setState(132);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__9) {
					{
					{
					setState(128);
					match(T__9);
					setState(129);
					expression();
					}
					}
					setState(134);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(135);
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
			setState(139);
			ref();
			setState(140);
			match(T__2);
			setState(141);
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
			setState(143);
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
			setState(145);
			match(T__12);
			setState(146);
			((CondContext)_localctx).condition = expression();
			setState(147);
			match(T__13);
			setState(148);
			((CondContext)_localctx).exptrue = expression();
			setState(149);
			match(T__14);
			setState(150);
			((CondContext)_localctx).expfalse = expression();
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
			setState(152);
			match(ID);
			setState(153);
			match(T__15);
			setState(154);
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
			setState(170);
			switch (_input.LA(1)) {
			case CLASSID:
				{
				setState(157);
				match(CLASSID);
				}
				break;
			case ID:
				{
				setState(158);
				match(ID);
				setState(163);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(159);
						match(T__6);
						setState(160);
						tydef(0);
						}
						} 
					}
					setState(165);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
				}
				}
				break;
			case T__4:
				{
				setState(166);
				match(T__4);
				setState(167);
				tydef(0);
				setState(168);
				match(T__5);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(192);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(190);
					switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
					case 1:
						{
						_localctx = new TydefContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_tydef);
						setState(172);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(173);
						match(T__10);
						setState(174);
						tydef(4);
						}
						break;
					case 2:
						{
						_localctx = new TydefContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_tydef);
						setState(175);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(176);
						match(T__9);
						setState(177);
						tydef(3);
						}
						break;
					case 3:
						{
						_localctx = new TydefContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_tydef);
						setState(178);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(179);
						match(T__16);
						setState(180);
						tydef(0);
						setState(185);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==T__9) {
							{
							{
							setState(181);
							match(T__9);
							setState(182);
							tydef(0);
							}
							}
							setState(187);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(188);
						match(T__17);
						}
						break;
					}
					} 
				}
				setState(194);
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

	public static class BinaryContext extends ParserRuleContext {
		public BinaryContext bleft;
		public BinexpContext xleft;
		public Token op;
		public BinexpContext right;
		public List<BinexpContext> binexp() {
			return getRuleContexts(BinexpContext.class);
		}
		public BinexpContext binexp(int i) {
			return getRuleContext(BinexpContext.class,i);
		}
		public TerminalNode BINOP() { return getToken(GrammarParser.BINOP, 0); }
		public BinaryContext binary() {
			return getRuleContext(BinaryContext.class,0);
		}
		public BinaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterBinary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitBinary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitBinary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinaryContext binary() throws RecognitionException {
		return binary(0);
	}

	private BinaryContext binary(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		BinaryContext _localctx = new BinaryContext(_ctx, _parentState);
		BinaryContext _prevctx = _localctx;
		int _startState = 28;
		enterRecursionRule(_localctx, 28, RULE_binary, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(196);
			((BinaryContext)_localctx).xleft = binexp();
			setState(197);
			((BinaryContext)_localctx).op = match(BINOP);
			setState(198);
			((BinaryContext)_localctx).right = binexp();
			}
			_ctx.stop = _input.LT(-1);
			setState(205);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new BinaryContext(_parentctx, _parentState);
					_localctx.bleft = _prevctx;
					_localctx.bleft = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_binary);
					setState(200);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(201);
					((BinaryContext)_localctx).op = match(BINOP);
					setState(202);
					((BinaryContext)_localctx).right = binexp();
					}
					} 
				}
				setState(207);
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

	public static class BinexpContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public ApplyContext apply() {
			return getRuleContext(ApplyContext.class,0);
		}
		public RefContext ref() {
			return getRuleContext(RefContext.class,0);
		}
		public BinaryContext binary() {
			return getRuleContext(BinaryContext.class,0);
		}
		public BinexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binexp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterBinexp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitBinexp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitBinexp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinexpContext binexp() throws RecognitionException {
		BinexpContext _localctx = new BinexpContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_binexp);
		try {
			setState(215);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(208);
				value(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(209);
				apply();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(210);
				ref();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(211);
				match(T__4);
				setState(212);
				binary(0);
				setState(213);
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
		enterRule(_localctx, 32, RULE_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(217);
			match(T__16);
			setState(218);
			expression();
			setState(223);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(219);
				match(T__9);
				setState(220);
				expression();
				}
				}
				setState(225);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(226);
			match(T__17);
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
		enterRule(_localctx, 34, RULE_map);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(228);
			match(T__16);
			setState(229);
			mappair();
			setState(234);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(230);
				match(T__9);
				setState(231);
				mappair();
				}
				}
				setState(236);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(237);
			match(T__17);
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
		enterRule(_localctx, 36, RULE_mappair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(239);
			((MappairContext)_localctx).mapkey = expression();
			setState(240);
			match(T__15);
			setState(241);
			((MappairContext)_localctx).mapvalue = expression();
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
		case 4:
			return value_sempred((ValueContext)_localctx, predIndex);
		case 13:
			return tydef_sempred((TydefContext)_localctx, predIndex);
		case 14:
			return binary_sempred((BinaryContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean value_sempred(ValueContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean tydef_sempred(TydefContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 3);
		case 2:
			return precpred(_ctx, 2);
		case 3:
			return precpred(_ctx, 4);
		}
		return true;
	}
	private boolean binary_sempred(BinaryContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\33\u00f6\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\3\2\3\2\3\2\7\2,\n\2\f\2\16\2/\13\2\3\2\3\2\3\3\3"+
		"\3\3\3\3\3\6\3\67\n\3\r\3\16\38\3\3\3\3\5\3=\n\3\3\4\3\4\6\4A\n\4\r\4"+
		"\16\4B\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5R\n\5\3"+
		"\6\3\6\3\6\3\6\5\6X\n\6\3\6\3\6\3\6\7\6]\n\6\f\6\16\6`\13\6\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\7\7j\n\7\f\7\16\7m\13\7\3\7\3\7\3\7\3\7\5\7s\n"+
		"\7\3\b\3\b\5\bw\n\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\7"+
		"\n\u0085\n\n\f\n\16\n\u0088\13\n\3\n\3\n\5\n\u008c\n\n\3\13\3\13\3\13"+
		"\3\13\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17"+
		"\3\17\3\17\3\17\7\17\u00a4\n\17\f\17\16\17\u00a7\13\17\3\17\3\17\3\17"+
		"\3\17\5\17\u00ad\n\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\7\17\u00ba\n\17\f\17\16\17\u00bd\13\17\3\17\3\17\7\17\u00c1\n\17"+
		"\f\17\16\17\u00c4\13\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\7\20\u00ce"+
		"\n\20\f\20\16\20\u00d1\13\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u00da"+
		"\n\21\3\22\3\22\3\22\3\22\7\22\u00e0\n\22\f\22\16\22\u00e3\13\22\3\22"+
		"\3\22\3\23\3\23\3\23\3\23\7\23\u00eb\n\23\f\23\16\23\u00ee\13\23\3\23"+
		"\3\23\3\24\3\24\3\24\3\24\3\24\2\5\n\34\36\25\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\32\34\36 \"$&\2\2\u0105\2(\3\2\2\2\4\62\3\2\2\2\6@\3\2\2\2\bQ\3"+
		"\2\2\2\nW\3\2\2\2\fr\3\2\2\2\16t\3\2\2\2\20x\3\2\2\2\22\u008b\3\2\2\2"+
		"\24\u008d\3\2\2\2\26\u0091\3\2\2\2\30\u0093\3\2\2\2\32\u009a\3\2\2\2\34"+
		"\u00ac\3\2\2\2\36\u00c5\3\2\2\2 \u00d9\3\2\2\2\"\u00db\3\2\2\2$\u00e6"+
		"\3\2\2\2&\u00f1\3\2\2\2()\7\3\2\2)-\7\25\2\2*,\5\4\3\2+*\3\2\2\2,/\3\2"+
		"\2\2-+\3\2\2\2-.\3\2\2\2.\60\3\2\2\2/-\3\2\2\2\60\61\5\6\4\2\61\3\3\2"+
		"\2\2\62\63\7\4\2\2\63\66\7\25\2\2\64\65\7\5\2\2\65\67\7\25\2\2\66\64\3"+
		"\2\2\2\678\3\2\2\28\66\3\2\2\289\3\2\2\29<\3\2\2\2:;\7\6\2\2;=\7\25\2"+
		"\2<:\3\2\2\2<=\3\2\2\2=\5\3\2\2\2>A\5\b\5\2?A\5\32\16\2@>\3\2\2\2@?\3"+
		"\2\2\2AB\3\2\2\2B@\3\2\2\2BC\3\2\2\2C\7\3\2\2\2DR\5\n\6\2ER\5\f\7\2FR"+
		"\5\20\t\2GR\5\22\n\2HR\5\24\13\2IR\5\26\f\2JR\5\30\r\2KL\7\7\2\2LM\5\b"+
		"\5\2MN\7\b\2\2NR\3\2\2\2OR\5\"\22\2PR\5$\23\2QD\3\2\2\2QE\3\2\2\2QF\3"+
		"\2\2\2QG\3\2\2\2QH\3\2\2\2QI\3\2\2\2QJ\3\2\2\2QK\3\2\2\2QO\3\2\2\2QP\3"+
		"\2\2\2R\t\3\2\2\2ST\b\6\1\2TX\7\27\2\2UX\7\30\2\2VX\7\31\2\2WS\3\2\2\2"+
		"WU\3\2\2\2WV\3\2\2\2X^\3\2\2\2YZ\f\3\2\2Z[\7\t\2\2[]\5\n\6\2\\Y\3\2\2"+
		"\2]`\3\2\2\2^\\\3\2\2\2^_\3\2\2\2_\13\3\2\2\2`^\3\2\2\2ab\7\n\2\2bc\5"+
		"\6\4\2cd\7\13\2\2ds\3\2\2\2ef\7\n\2\2fk\5\16\b\2gh\7\f\2\2hj\5\16\b\2"+
		"ig\3\2\2\2jm\3\2\2\2ki\3\2\2\2kl\3\2\2\2ln\3\2\2\2mk\3\2\2\2no\7\r\2\2"+
		"op\5\6\4\2pq\7\13\2\2qs\3\2\2\2ra\3\2\2\2re\3\2\2\2s\r\3\2\2\2tv\7\25"+
		"\2\2uw\7\26\2\2vu\3\2\2\2vw\3\2\2\2w\17\3\2\2\2xy\7\25\2\2yz\7\16\2\2"+
		"z{\5\b\5\2{\21\3\2\2\2|}\7\25\2\2}~\7\7\2\2~\u008c\7\b\2\2\177\u0080\7"+
		"\25\2\2\u0080\u0081\7\7\2\2\u0081\u0086\5\b\5\2\u0082\u0083\7\f\2\2\u0083"+
		"\u0085\5\b\5\2\u0084\u0082\3\2\2\2\u0085\u0088\3\2\2\2\u0086\u0084\3\2"+
		"\2\2\u0086\u0087\3\2\2\2\u0087\u0089\3\2\2\2\u0088\u0086\3\2\2\2\u0089"+
		"\u008a\7\b\2\2\u008a\u008c\3\2\2\2\u008b|\3\2\2\2\u008b\177\3\2\2\2\u008c"+
		"\23\3\2\2\2\u008d\u008e\5\26\f\2\u008e\u008f\7\5\2\2\u008f\u0090\5\22"+
		"\n\2\u0090\25\3\2\2\2\u0091\u0092\7\25\2\2\u0092\27\3\2\2\2\u0093\u0094"+
		"\7\17\2\2\u0094\u0095\5\b\5\2\u0095\u0096\7\20\2\2\u0096\u0097\5\b\5\2"+
		"\u0097\u0098\7\21\2\2\u0098\u0099\5\b\5\2\u0099\31\3\2\2\2\u009a\u009b"+
		"\7\25\2\2\u009b\u009c\7\22\2\2\u009c\u009d\5\34\17\2\u009d\33\3\2\2\2"+
		"\u009e\u009f\b\17\1\2\u009f\u00ad\7\26\2\2\u00a0\u00a5\7\25\2\2\u00a1"+
		"\u00a2\7\t\2\2\u00a2\u00a4\5\34\17\2\u00a3\u00a1\3\2\2\2\u00a4\u00a7\3"+
		"\2\2\2\u00a5\u00a3\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00ad\3\2\2\2\u00a7"+
		"\u00a5\3\2\2\2\u00a8\u00a9\7\7\2\2\u00a9\u00aa\5\34\17\2\u00aa\u00ab\7"+
		"\b\2\2\u00ab\u00ad\3\2\2\2\u00ac\u009e\3\2\2\2\u00ac\u00a0\3\2\2\2\u00ac"+
		"\u00a8\3\2\2\2\u00ad\u00c2\3\2\2\2\u00ae\u00af\f\5\2\2\u00af\u00b0\7\r"+
		"\2\2\u00b0\u00c1\5\34\17\6\u00b1\u00b2\f\4\2\2\u00b2\u00b3\7\f\2\2\u00b3"+
		"\u00c1\5\34\17\5\u00b4\u00b5\f\6\2\2\u00b5\u00b6\7\23\2\2\u00b6\u00bb"+
		"\5\34\17\2\u00b7\u00b8\7\f\2\2\u00b8\u00ba\5\34\17\2\u00b9\u00b7\3\2\2"+
		"\2\u00ba\u00bd\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00be"+
		"\3\2\2\2\u00bd\u00bb\3\2\2\2\u00be\u00bf\7\24\2\2\u00bf\u00c1\3\2\2\2"+
		"\u00c0\u00ae\3\2\2\2\u00c0\u00b1\3\2\2\2\u00c0\u00b4\3\2\2\2\u00c1\u00c4"+
		"\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3\35\3\2\2\2\u00c4"+
		"\u00c2\3\2\2\2\u00c5\u00c6\b\20\1\2\u00c6\u00c7\5 \21\2\u00c7\u00c8\7"+
		"\32\2\2\u00c8\u00c9\5 \21\2\u00c9\u00cf\3\2\2\2\u00ca\u00cb\f\3\2\2\u00cb"+
		"\u00cc\7\32\2\2\u00cc\u00ce\5 \21\2\u00cd\u00ca\3\2\2\2\u00ce\u00d1\3"+
		"\2\2\2\u00cf\u00cd\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\37\3\2\2\2\u00d1"+
		"\u00cf\3\2\2\2\u00d2\u00da\5\n\6\2\u00d3\u00da\5\22\n\2\u00d4\u00da\5"+
		"\26\f\2\u00d5\u00d6\7\7\2\2\u00d6\u00d7\5\36\20\2\u00d7\u00d8\7\b\2\2"+
		"\u00d8\u00da\3\2\2\2\u00d9\u00d2\3\2\2\2\u00d9\u00d3\3\2\2\2\u00d9\u00d4"+
		"\3\2\2\2\u00d9\u00d5\3\2\2\2\u00da!\3\2\2\2\u00db\u00dc\7\23\2\2\u00dc"+
		"\u00e1\5\b\5\2\u00dd\u00de\7\f\2\2\u00de\u00e0\5\b\5\2\u00df\u00dd\3\2"+
		"\2\2\u00e0\u00e3\3\2\2\2\u00e1\u00df\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2"+
		"\u00e4\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e4\u00e5\7\24\2\2\u00e5#\3\2\2\2"+
		"\u00e6\u00e7\7\23\2\2\u00e7\u00ec\5&\24\2\u00e8\u00e9\7\f\2\2\u00e9\u00eb"+
		"\5&\24\2\u00ea\u00e8\3\2\2\2\u00eb\u00ee\3\2\2\2\u00ec\u00ea\3\2\2\2\u00ec"+
		"\u00ed\3\2\2\2\u00ed\u00ef\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ef\u00f0\7\24"+
		"\2\2\u00f0%\3\2\2\2\u00f1\u00f2\5\b\5\2\u00f2\u00f3\7\22\2\2\u00f3\u00f4"+
		"\5\b\5\2\u00f4\'\3\2\2\2\30-8<@BQW^krv\u0086\u008b\u00a5\u00ac\u00bb\u00c0"+
		"\u00c2\u00cf\u00d9\u00e1\u00ec";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}