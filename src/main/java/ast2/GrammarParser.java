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
		RULE_fn = 5, RULE_fnargpair = 6, RULE_apply = 7, RULE_ref = 8, RULE_cond = 9, 
		RULE_forward = 10, RULE_tydef = 11, RULE_list = 12, RULE_map = 13, RULE_mappair = 14, 
		RULE_klass = 15, RULE_klassvar = 16, RULE_klassparent = 17, RULE_instantiation = 18, 
		RULE_interf = 19, RULE_match = 20, RULE_matchexp = 21;
	public static final String[] ruleNames = {
		"file", "imp", "block", "expression", "value", "fn", "fnargpair", "apply", 
		"ref", "cond", "forward", "tydef", "list", "map", "mappair", "klass", 
		"klassvar", "klassparent", "instantiation", "interf", "match", "matchexp"
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
			setState(54);
			match(T__1);
			setState(55);
			match(ID);
			setState(58);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(56);
				match(T__2);
				setState(57);
				match(ID);
				}
				break;
			}
			setState(60);
			match(T__2);
			setState(61);
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
			setState(65); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(65);
				switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
				case 1:
					{
					setState(63);
					expression(0);
					}
					break;
				case 2:
					{
					setState(64);
					forward();
					}
					break;
				}
				}
				setState(67); 
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
			setState(107);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(70);
				((ExpressionContext)_localctx).defsimple = match(ID);
				setState(71);
				match(T__11);
				setState(72);
				((ExpressionContext)_localctx).defsimple2 = expression(3);
				}
				break;
			case 2:
				{
				setState(73);
				((ExpressionContext)_localctx).defn = match(ID);
				setState(74);
				match(T__3);
				setState(75);
				match(T__4);
				setState(76);
				match(T__11);
				setState(77);
				((ExpressionContext)_localctx).body = expression(2);
				}
				break;
			case 3:
				{
				setState(78);
				((ExpressionContext)_localctx).defn = match(ID);
				setState(79);
				match(T__3);
				setState(80);
				fnargpair();
				setState(85);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__12) {
					{
					{
					setState(81);
					match(T__12);
					setState(82);
					fnargpair();
					}
					}
					setState(87);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(88);
				match(T__4);
				setState(89);
				match(T__11);
				setState(90);
				((ExpressionContext)_localctx).body = expression(1);
				}
				break;
			case 4:
				{
				setState(92);
				value();
				}
				break;
			case 5:
				{
				setState(93);
				fn();
				}
				break;
			case 6:
				{
				setState(94);
				apply();
				}
				break;
			case 7:
				{
				setState(95);
				ref();
				}
				break;
			case 8:
				{
				setState(96);
				cond();
				}
				break;
			case 9:
				{
				setState(97);
				match();
				}
				break;
			case 10:
				{
				setState(98);
				match(T__3);
				setState(99);
				((ExpressionContext)_localctx).exp = expression(0);
				setState(100);
				match(T__4);
				}
				break;
			case 11:
				{
				setState(102);
				list();
				}
				break;
			case 12:
				{
				setState(103);
				map();
				}
				break;
			case 13:
				{
				setState(104);
				klass();
				}
				break;
			case 14:
				{
				setState(105);
				instantiation();
				}
				break;
			case 15:
				{
				setState(106);
				interf();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(126);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(124);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(109);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(110);
						((ExpressionContext)_localctx).binop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__5 || _la==T__6) ) {
							((ExpressionContext)_localctx).binop = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(111);
						((ExpressionContext)_localctx).right = expression(10);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(112);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(113);
						((ExpressionContext)_localctx).binop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__7 || _la==T__8) ) {
							((ExpressionContext)_localctx).binop = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(114);
						((ExpressionContext)_localctx).right = expression(9);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(115);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(116);
						((ExpressionContext)_localctx).binop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__9 || _la==T__10) ) {
							((ExpressionContext)_localctx).binop = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(117);
						((ExpressionContext)_localctx).right = expression(8);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.objapply = _prevctx;
						_localctx.objapply = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(118);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(119);
						match(T__2);
						setState(120);
						apply();
						}
						break;
					case 5:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.objfield = _prevctx;
						_localctx.objfield = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(121);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(122);
						match(T__2);
						setState(123);
						match(ID);
						}
						break;
					}
					} 
				}
				setState(128);
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
			setState(140);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(130);
				_la = _input.LA(1);
				if (_la==T__10) {
					{
					setState(129);
					((ValueContext)_localctx).m = match(T__10);
					}
				}

				setState(132);
				match(INTEGER);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(134);
				_la = _input.LA(1);
				if (_la==T__10) {
					{
					setState(133);
					((ValueContext)_localctx).m = match(T__10);
					}
				}

				setState(136);
				match(FLOAT);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(137);
				match(STRING);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(138);
				((ValueContext)_localctx).boolfalse = match(T__13);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(139);
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
			setState(159);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(142);
				match(T__15);
				setState(143);
				block();
				setState(144);
				match(T__16);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(146);
				match(T__15);
				setState(147);
				fnargpair();
				setState(152);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__12) {
					{
					{
					setState(148);
					match(T__12);
					setState(149);
					fnargpair();
					}
					}
					setState(154);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(155);
				match(T__17);
				setState(156);
				block();
				setState(157);
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
			setState(161);
			match(ID);
			setState(163);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << ID) | (1L << CLASSID))) != 0)) {
				{
				setState(162);
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
			setState(180);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(165);
				match(ID);
				setState(166);
				match(T__3);
				setState(167);
				match(T__4);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(168);
				match(ID);
				setState(169);
				match(T__3);
				setState(170);
				expression(0);
				setState(175);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__12) {
					{
					{
					setState(171);
					match(T__12);
					setState(172);
					expression(0);
					}
					}
					setState(177);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(178);
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
			setState(182);
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
			setState(184);
			match(T__18);
			setState(185);
			((CondContext)_localctx).condition = expression(0);
			setState(186);
			match(T__19);
			setState(187);
			((CondContext)_localctx).exptrue = expression(0);
			setState(188);
			match(T__20);
			setState(189);
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
		enterRule(_localctx, 20, RULE_forward);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192);
			_la = _input.LA(1);
			if (_la==T__21) {
				{
				setState(191);
				((ForwardContext)_localctx).nat = match(T__21);
				}
			}

			setState(194);
			((ForwardContext)_localctx).id = match(ID);
			setState(198);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(195);
				match(T__3);
				setState(196);
				((ForwardContext)_localctx).natid = match(ID);
				setState(197);
				match(T__4);
				}
			}

			setState(200);
			match(T__22);
			setState(201);
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
			setState(217);
			switch (_input.LA(1)) {
			case CLASSID:
				{
				setState(204);
				match(CLASSID);
				}
				break;
			case ID:
				{
				setState(205);
				match(ID);
				setState(210);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(206);
						match(T__9);
						setState(207);
						tydef(0);
						}
						} 
					}
					setState(212);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
				}
				}
				break;
			case T__3:
				{
				setState(213);
				match(T__3);
				setState(214);
				tydef(0);
				setState(215);
				match(T__4);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(243);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(241);
					switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
					case 1:
						{
						_localctx = new TydefContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_tydef);
						setState(219);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
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
						match(T__25);
						setState(228);
						tydef(3);
						}
						break;
					case 2:
						{
						_localctx = new TydefContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_tydef);
						setState(229);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(230);
						match(T__23);
						setState(231);
						tydef(0);
						setState(236);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==T__12) {
							{
							{
							setState(232);
							match(T__12);
							setState(233);
							tydef(0);
							}
							}
							setState(238);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(239);
						match(T__24);
						}
						break;
					}
					} 
				}
				setState(245);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
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
			setState(246);
			match(T__23);
			setState(247);
			expression(0);
			setState(252);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__12) {
				{
				{
				setState(248);
				match(T__12);
				setState(249);
				expression(0);
				}
				}
				setState(254);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(255);
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
		enterRule(_localctx, 26, RULE_map);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(257);
			match(T__23);
			setState(258);
			mappair();
			setState(263);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__12) {
				{
				{
				setState(259);
				match(T__12);
				setState(260);
				mappair();
				}
				}
				setState(265);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(266);
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
		enterRule(_localctx, 28, RULE_mappair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(268);
			((MappairContext)_localctx).mapkey = expression(0);
			setState(269);
			match(T__22);
			setState(270);
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
			setState(272);
			match(T__26);
			setState(273);
			match(CLASSID);
			setState(274);
			match(T__3);
			setState(283);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(275);
				klassvar();
				setState(280);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__12) {
					{
					{
					setState(276);
					match(T__12);
					setState(277);
					klassvar();
					}
					}
					setState(282);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(285);
			match(T__4);
			setState(295);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				{
				setState(286);
				match(T__27);
				setState(287);
				klassparent(0);
				setState(292);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(288);
						match(T__12);
						setState(289);
						klassparent(0);
						}
						} 
					}
					setState(294);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
				}
				}
				break;
			}
			setState(301);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				{
				setState(297);
				match(T__15);
				setState(298);
				block();
				setState(299);
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
			setState(303);
			match(ID);
			setState(305);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << ID) | (1L << CLASSID))) != 0)) {
				{
				setState(304);
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
			setState(310);
			switch (_input.LA(1)) {
			case CLASSID:
				{
				setState(308);
				match(CLASSID);
				}
				break;
			case ID:
				{
				setState(309);
				match(ID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(326);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new KlassparentContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_klassparent);
					setState(312);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(313);
					match(T__23);
					setState(314);
					klassparent(0);
					setState(319);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__12) {
						{
						{
						setState(315);
						match(T__12);
						setState(316);
						klassparent(0);
						}
						}
						setState(321);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(322);
					match(T__24);
					}
					} 
				}
				setState(328);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
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
			setState(329);
			match(CLASSID);
			setState(330);
			match(T__3);
			setState(339);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__10) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__18) | (1L << T__23) | (1L << T__26) | (1L << T__28) | (1L << ID) | (1L << CLASSID) | (1L << INTEGER) | (1L << FLOAT) | (1L << STRING))) != 0)) {
				{
				setState(331);
				expression(0);
				setState(336);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__12) {
					{
					{
					setState(332);
					match(T__12);
					setState(333);
					expression(0);
					}
					}
					setState(338);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(341);
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
		enterRule(_localctx, 38, RULE_interf);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(343);
			match(T__28);
			setState(344);
			match(CLASSID);
			setState(356);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				{
				setState(345);
				match(T__23);
				setState(346);
				tydef(0);
				setState(351);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__12) {
					{
					{
					setState(347);
					match(T__12);
					setState(348);
					tydef(0);
					}
					}
					setState(353);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(354);
				match(T__24);
				}
				break;
			}
			setState(367);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				{
				setState(358);
				match(T__27);
				setState(359);
				klassparent(0);
				setState(364);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(360);
						match(T__12);
						setState(361);
						klassparent(0);
						}
						} 
					}
					setState(366);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
				}
				}
				break;
			}
			setState(377);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
			case 1:
				{
				setState(369);
				match(T__15);
				setState(373);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__21 || _la==ID) {
					{
					{
					setState(370);
					forward();
					}
					}
					setState(375);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(376);
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
		enterRule(_localctx, 40, RULE_match);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(379);
			match(T__18);
			setState(380);
			((MatchContext)_localctx).source = expression(0);
			setState(381);
			match(T__27);
			setState(382);
			matchexp();
			setState(383);
			match(T__19);
			setState(384);
			((MatchContext)_localctx).exptrue = expression(0);
			setState(385);
			match(T__20);
			setState(386);
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
		enterRule(_localctx, 42, RULE_matchexp);
		int _la;
		try {
			setState(405);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(389);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(388);
					((MatchexpContext)_localctx).v = match(ID);
					}
				}

				setState(391);
				match(CLASSID);
				setState(392);
				match(T__3);
				setState(401);
				_la = _input.LA(1);
				if (_la==ID || _la==CLASSID) {
					{
					setState(393);
					matchexp();
					setState(398);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__12) {
						{
						{
						setState(394);
						match(T__12);
						setState(395);
						matchexp();
						}
						}
						setState(400);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(403);
				match(T__4);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(404);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\'\u019a\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3\2\3\2\7\2\62"+
		"\n\2\f\2\16\2\65\13\2\3\2\3\2\3\3\3\3\3\3\3\3\5\3=\n\3\3\3\3\3\3\3\3\4"+
		"\3\4\6\4D\n\4\r\4\16\4E\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\7\5V\n\5\f\5\16\5Y\13\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5n\n\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\177\n\5\f\5\16\5\u0082\13"+
		"\5\3\6\5\6\u0085\n\6\3\6\3\6\5\6\u0089\n\6\3\6\3\6\3\6\3\6\5\6\u008f\n"+
		"\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7\u0099\n\7\f\7\16\7\u009c\13\7\3"+
		"\7\3\7\3\7\3\7\5\7\u00a2\n\7\3\b\3\b\5\b\u00a6\n\b\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\7\t\u00b0\n\t\f\t\16\t\u00b3\13\t\3\t\3\t\5\t\u00b7\n\t\3"+
		"\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\5\f\u00c3\n\f\3\f\3\f\3"+
		"\f\3\f\5\f\u00c9\n\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\7\r\u00d3\n\r\f\r"+
		"\16\r\u00d6\13\r\3\r\3\r\3\r\3\r\5\r\u00dc\n\r\3\r\3\r\3\r\7\r\u00e1\n"+
		"\r\f\r\16\r\u00e4\13\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u00ed\n\r\f\r\16"+
		"\r\u00f0\13\r\3\r\3\r\7\r\u00f4\n\r\f\r\16\r\u00f7\13\r\3\16\3\16\3\16"+
		"\3\16\7\16\u00fd\n\16\f\16\16\16\u0100\13\16\3\16\3\16\3\17\3\17\3\17"+
		"\3\17\7\17\u0108\n\17\f\17\16\17\u010b\13\17\3\17\3\17\3\20\3\20\3\20"+
		"\3\20\3\21\3\21\3\21\3\21\3\21\3\21\7\21\u0119\n\21\f\21\16\21\u011c\13"+
		"\21\5\21\u011e\n\21\3\21\3\21\3\21\3\21\3\21\7\21\u0125\n\21\f\21\16\21"+
		"\u0128\13\21\5\21\u012a\n\21\3\21\3\21\3\21\3\21\5\21\u0130\n\21\3\22"+
		"\3\22\5\22\u0134\n\22\3\23\3\23\3\23\5\23\u0139\n\23\3\23\3\23\3\23\3"+
		"\23\3\23\7\23\u0140\n\23\f\23\16\23\u0143\13\23\3\23\3\23\7\23\u0147\n"+
		"\23\f\23\16\23\u014a\13\23\3\24\3\24\3\24\3\24\3\24\7\24\u0151\n\24\f"+
		"\24\16\24\u0154\13\24\5\24\u0156\n\24\3\24\3\24\3\25\3\25\3\25\3\25\3"+
		"\25\3\25\7\25\u0160\n\25\f\25\16\25\u0163\13\25\3\25\3\25\5\25\u0167\n"+
		"\25\3\25\3\25\3\25\3\25\7\25\u016d\n\25\f\25\16\25\u0170\13\25\5\25\u0172"+
		"\n\25\3\25\3\25\7\25\u0176\n\25\f\25\16\25\u0179\13\25\3\25\5\25\u017c"+
		"\n\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\5\27\u0188\n\27"+
		"\3\27\3\27\3\27\3\27\3\27\7\27\u018f\n\27\f\27\16\27\u0192\13\27\5\27"+
		"\u0194\n\27\3\27\3\27\5\27\u0198\n\27\3\27\2\5\b\30$\30\2\4\6\b\n\f\16"+
		"\20\22\24\26\30\32\34\36 \"$&(*,\2\6\3\2 !\3\2\b\t\3\2\n\13\3\2\f\r\u01c6"+
		"\2.\3\2\2\2\48\3\2\2\2\6C\3\2\2\2\bm\3\2\2\2\n\u008e\3\2\2\2\f\u00a1\3"+
		"\2\2\2\16\u00a3\3\2\2\2\20\u00b6\3\2\2\2\22\u00b8\3\2\2\2\24\u00ba\3\2"+
		"\2\2\26\u00c2\3\2\2\2\30\u00db\3\2\2\2\32\u00f8\3\2\2\2\34\u0103\3\2\2"+
		"\2\36\u010e\3\2\2\2 \u0112\3\2\2\2\"\u0131\3\2\2\2$\u0138\3\2\2\2&\u014b"+
		"\3\2\2\2(\u0159\3\2\2\2*\u017d\3\2\2\2,\u0197\3\2\2\2./\7\3\2\2/\63\7"+
		" \2\2\60\62\5\4\3\2\61\60\3\2\2\2\62\65\3\2\2\2\63\61\3\2\2\2\63\64\3"+
		"\2\2\2\64\66\3\2\2\2\65\63\3\2\2\2\66\67\5\6\4\2\67\3\3\2\2\289\7\4\2"+
		"\29<\7 \2\2:;\7\5\2\2;=\7 \2\2<:\3\2\2\2<=\3\2\2\2=>\3\2\2\2>?\7\5\2\2"+
		"?@\t\2\2\2@\5\3\2\2\2AD\5\b\5\2BD\5\26\f\2CA\3\2\2\2CB\3\2\2\2DE\3\2\2"+
		"\2EC\3\2\2\2EF\3\2\2\2F\7\3\2\2\2GH\b\5\1\2HI\7 \2\2IJ\7\16\2\2Jn\5\b"+
		"\5\5KL\7 \2\2LM\7\6\2\2MN\7\7\2\2NO\7\16\2\2On\5\b\5\4PQ\7 \2\2QR\7\6"+
		"\2\2RW\5\16\b\2ST\7\17\2\2TV\5\16\b\2US\3\2\2\2VY\3\2\2\2WU\3\2\2\2WX"+
		"\3\2\2\2XZ\3\2\2\2YW\3\2\2\2Z[\7\7\2\2[\\\7\16\2\2\\]\5\b\5\3]n\3\2\2"+
		"\2^n\5\n\6\2_n\5\f\7\2`n\5\20\t\2an\5\22\n\2bn\5\24\13\2cn\5*\26\2de\7"+
		"\6\2\2ef\5\b\5\2fg\7\7\2\2gn\3\2\2\2hn\5\32\16\2in\5\34\17\2jn\5 \21\2"+
		"kn\5&\24\2ln\5(\25\2mG\3\2\2\2mK\3\2\2\2mP\3\2\2\2m^\3\2\2\2m_\3\2\2\2"+
		"m`\3\2\2\2ma\3\2\2\2mb\3\2\2\2mc\3\2\2\2md\3\2\2\2mh\3\2\2\2mi\3\2\2\2"+
		"mj\3\2\2\2mk\3\2\2\2ml\3\2\2\2n\u0080\3\2\2\2op\f\13\2\2pq\t\3\2\2q\177"+
		"\5\b\5\frs\f\n\2\2st\t\4\2\2t\177\5\b\5\13uv\f\t\2\2vw\t\5\2\2w\177\5"+
		"\b\5\nxy\f\r\2\2yz\7\5\2\2z\177\5\20\t\2{|\f\f\2\2|}\7\5\2\2}\177\7 \2"+
		"\2~o\3\2\2\2~r\3\2\2\2~u\3\2\2\2~x\3\2\2\2~{\3\2\2\2\177\u0082\3\2\2\2"+
		"\u0080~\3\2\2\2\u0080\u0081\3\2\2\2\u0081\t\3\2\2\2\u0082\u0080\3\2\2"+
		"\2\u0083\u0085\7\r\2\2\u0084\u0083\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0086"+
		"\3\2\2\2\u0086\u008f\7\"\2\2\u0087\u0089\7\r\2\2\u0088\u0087\3\2\2\2\u0088"+
		"\u0089\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008f\7#\2\2\u008b\u008f\7$\2"+
		"\2\u008c\u008f\7\20\2\2\u008d\u008f\7\21\2\2\u008e\u0084\3\2\2\2\u008e"+
		"\u0088\3\2\2\2\u008e\u008b\3\2\2\2\u008e\u008c\3\2\2\2\u008e\u008d\3\2"+
		"\2\2\u008f\13\3\2\2\2\u0090\u0091\7\22\2\2\u0091\u0092\5\6\4\2\u0092\u0093"+
		"\7\23\2\2\u0093\u00a2\3\2\2\2\u0094\u0095\7\22\2\2\u0095\u009a\5\16\b"+
		"\2\u0096\u0097\7\17\2\2\u0097\u0099\5\16\b\2\u0098\u0096\3\2\2\2\u0099"+
		"\u009c\3\2\2\2\u009a\u0098\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u009d\3\2"+
		"\2\2\u009c\u009a\3\2\2\2\u009d\u009e\7\24\2\2\u009e\u009f\5\6\4\2\u009f"+
		"\u00a0\7\23\2\2\u00a0\u00a2\3\2\2\2\u00a1\u0090\3\2\2\2\u00a1\u0094\3"+
		"\2\2\2\u00a2\r\3\2\2\2\u00a3\u00a5\7 \2\2\u00a4\u00a6\5\30\r\2\u00a5\u00a4"+
		"\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\17\3\2\2\2\u00a7\u00a8\7 \2\2\u00a8"+
		"\u00a9\7\6\2\2\u00a9\u00b7\7\7\2\2\u00aa\u00ab\7 \2\2\u00ab\u00ac\7\6"+
		"\2\2\u00ac\u00b1\5\b\5\2\u00ad\u00ae\7\17\2\2\u00ae\u00b0\5\b\5\2\u00af"+
		"\u00ad\3\2\2\2\u00b0\u00b3\3\2\2\2\u00b1\u00af\3\2\2\2\u00b1\u00b2\3\2"+
		"\2\2\u00b2\u00b4\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b4\u00b5\7\7\2\2\u00b5"+
		"\u00b7\3\2\2\2\u00b6\u00a7\3\2\2\2\u00b6\u00aa\3\2\2\2\u00b7\21\3\2\2"+
		"\2\u00b8\u00b9\7 \2\2\u00b9\23\3\2\2\2\u00ba\u00bb\7\25\2\2\u00bb\u00bc"+
		"\5\b\5\2\u00bc\u00bd\7\26\2\2\u00bd\u00be\5\b\5\2\u00be\u00bf\7\27\2\2"+
		"\u00bf\u00c0\5\b\5\2\u00c0\25\3\2\2\2\u00c1\u00c3\7\30\2\2\u00c2\u00c1"+
		"\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c8\7 \2\2\u00c5"+
		"\u00c6\7\6\2\2\u00c6\u00c7\7 \2\2\u00c7\u00c9\7\7\2\2\u00c8\u00c5\3\2"+
		"\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00cb\7\31\2\2\u00cb"+
		"\u00cc\5\30\r\2\u00cc\27\3\2\2\2\u00cd\u00ce\b\r\1\2\u00ce\u00dc\7!\2"+
		"\2\u00cf\u00d4\7 \2\2\u00d0\u00d1\7\f\2\2\u00d1\u00d3\5\30\r\2\u00d2\u00d0"+
		"\3\2\2\2\u00d3\u00d6\3\2\2\2\u00d4\u00d2\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5"+
		"\u00dc\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d7\u00d8\7\6\2\2\u00d8\u00d9\5\30"+
		"\r\2\u00d9\u00da\7\7\2\2\u00da\u00dc\3\2\2\2\u00db\u00cd\3\2\2\2\u00db"+
		"\u00cf\3\2\2\2\u00db\u00d7\3\2\2\2\u00dc\u00f5\3\2\2\2\u00dd\u00e2\f\4"+
		"\2\2\u00de\u00df\7\17\2\2\u00df\u00e1\5\30\r\2\u00e0\u00de\3\2\2\2\u00e1"+
		"\u00e4\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3\u00e5\3\2"+
		"\2\2\u00e4\u00e2\3\2\2\2\u00e5\u00e6\7\34\2\2\u00e6\u00f4\5\30\r\5\u00e7"+
		"\u00e8\f\5\2\2\u00e8\u00e9\7\32\2\2\u00e9\u00ee\5\30\r\2\u00ea\u00eb\7"+
		"\17\2\2\u00eb\u00ed\5\30\r\2\u00ec\u00ea\3\2\2\2\u00ed\u00f0\3\2\2\2\u00ee"+
		"\u00ec\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef\u00f1\3\2\2\2\u00f0\u00ee\3\2"+
		"\2\2\u00f1\u00f2\7\33\2\2\u00f2\u00f4\3\2\2\2\u00f3\u00dd\3\2\2\2\u00f3"+
		"\u00e7\3\2\2\2\u00f4\u00f7\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f5\u00f6\3\2"+
		"\2\2\u00f6\31\3\2\2\2\u00f7\u00f5\3\2\2\2\u00f8\u00f9\7\32\2\2\u00f9\u00fe"+
		"\5\b\5\2\u00fa\u00fb\7\17\2\2\u00fb\u00fd\5\b\5\2\u00fc\u00fa\3\2\2\2"+
		"\u00fd\u0100\3\2\2\2\u00fe\u00fc\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\u0101"+
		"\3\2\2\2\u0100\u00fe\3\2\2\2\u0101\u0102\7\33\2\2\u0102\33\3\2\2\2\u0103"+
		"\u0104\7\32\2\2\u0104\u0109\5\36\20\2\u0105\u0106\7\17\2\2\u0106\u0108"+
		"\5\36\20\2\u0107\u0105\3\2\2\2\u0108\u010b\3\2\2\2\u0109\u0107\3\2\2\2"+
		"\u0109\u010a\3\2\2\2\u010a\u010c\3\2\2\2\u010b\u0109\3\2\2\2\u010c\u010d"+
		"\7\33\2\2\u010d\35\3\2\2\2\u010e\u010f\5\b\5\2\u010f\u0110\7\31\2\2\u0110"+
		"\u0111\5\b\5\2\u0111\37\3\2\2\2\u0112\u0113\7\35\2\2\u0113\u0114\7!\2"+
		"\2\u0114\u011d\7\6\2\2\u0115\u011a\5\"\22\2\u0116\u0117\7\17\2\2\u0117"+
		"\u0119\5\"\22\2\u0118\u0116\3\2\2\2\u0119\u011c\3\2\2\2\u011a\u0118\3"+
		"\2\2\2\u011a\u011b\3\2\2\2\u011b\u011e\3\2\2\2\u011c\u011a\3\2\2\2\u011d"+
		"\u0115\3\2\2\2\u011d\u011e\3\2\2\2\u011e\u011f\3\2\2\2\u011f\u0129\7\7"+
		"\2\2\u0120\u0121\7\36\2\2\u0121\u0126\5$\23\2\u0122\u0123\7\17\2\2\u0123"+
		"\u0125\5$\23\2\u0124\u0122\3\2\2\2\u0125\u0128\3\2\2\2\u0126\u0124\3\2"+
		"\2\2\u0126\u0127\3\2\2\2\u0127\u012a\3\2\2\2\u0128\u0126\3\2\2\2\u0129"+
		"\u0120\3\2\2\2\u0129\u012a\3\2\2\2\u012a\u012f\3\2\2\2\u012b\u012c\7\22"+
		"\2\2\u012c\u012d\5\6\4\2\u012d\u012e\7\23\2\2\u012e\u0130\3\2\2\2\u012f"+
		"\u012b\3\2\2\2\u012f\u0130\3\2\2\2\u0130!\3\2\2\2\u0131\u0133\7 \2\2\u0132"+
		"\u0134\5\30\r\2\u0133\u0132\3\2\2\2\u0133\u0134\3\2\2\2\u0134#\3\2\2\2"+
		"\u0135\u0136\b\23\1\2\u0136\u0139\7!\2\2\u0137\u0139\7 \2\2\u0138\u0135"+
		"\3\2\2\2\u0138\u0137\3\2\2\2\u0139\u0148\3\2\2\2\u013a\u013b\f\3\2\2\u013b"+
		"\u013c\7\32\2\2\u013c\u0141\5$\23\2\u013d\u013e\7\17\2\2\u013e\u0140\5"+
		"$\23\2\u013f\u013d\3\2\2\2\u0140\u0143\3\2\2\2\u0141\u013f\3\2\2\2\u0141"+
		"\u0142\3\2\2\2\u0142\u0144\3\2\2\2\u0143\u0141\3\2\2\2\u0144\u0145\7\33"+
		"\2\2\u0145\u0147\3\2\2\2\u0146\u013a\3\2\2\2\u0147\u014a\3\2\2\2\u0148"+
		"\u0146\3\2\2\2\u0148\u0149\3\2\2\2\u0149%\3\2\2\2\u014a\u0148\3\2\2\2"+
		"\u014b\u014c\7!\2\2\u014c\u0155\7\6\2\2\u014d\u0152\5\b\5\2\u014e\u014f"+
		"\7\17\2\2\u014f\u0151\5\b\5\2\u0150\u014e\3\2\2\2\u0151\u0154\3\2\2\2"+
		"\u0152\u0150\3\2\2\2\u0152\u0153\3\2\2\2\u0153\u0156\3\2\2\2\u0154\u0152"+
		"\3\2\2\2\u0155\u014d\3\2\2\2\u0155\u0156\3\2\2\2\u0156\u0157\3\2\2\2\u0157"+
		"\u0158\7\7\2\2\u0158\'\3\2\2\2\u0159\u015a\7\37\2\2\u015a\u0166\7!\2\2"+
		"\u015b\u015c\7\32\2\2\u015c\u0161\5\30\r\2\u015d\u015e\7\17\2\2\u015e"+
		"\u0160\5\30\r\2\u015f\u015d\3\2\2\2\u0160\u0163\3\2\2\2\u0161\u015f\3"+
		"\2\2\2\u0161\u0162\3\2\2\2\u0162\u0164\3\2\2\2\u0163\u0161\3\2\2\2\u0164"+
		"\u0165\7\33\2\2\u0165\u0167\3\2\2\2\u0166\u015b\3\2\2\2\u0166\u0167\3"+
		"\2\2\2\u0167\u0171\3\2\2\2\u0168\u0169\7\36\2\2\u0169\u016e\5$\23\2\u016a"+
		"\u016b\7\17\2\2\u016b\u016d\5$\23\2\u016c\u016a\3\2\2\2\u016d\u0170\3"+
		"\2\2\2\u016e\u016c\3\2\2\2\u016e\u016f\3\2\2\2\u016f\u0172\3\2\2\2\u0170"+
		"\u016e\3\2\2\2\u0171\u0168\3\2\2\2\u0171\u0172\3\2\2\2\u0172\u017b\3\2"+
		"\2\2\u0173\u0177\7\22\2\2\u0174\u0176\5\26\f\2\u0175\u0174\3\2\2\2\u0176"+
		"\u0179\3\2\2\2\u0177\u0175\3\2\2\2\u0177\u0178\3\2\2\2\u0178\u017a\3\2"+
		"\2\2\u0179\u0177\3\2\2\2\u017a\u017c\7\23\2\2\u017b\u0173\3\2\2\2\u017b"+
		"\u017c\3\2\2\2\u017c)\3\2\2\2\u017d\u017e\7\25\2\2\u017e\u017f\5\b\5\2"+
		"\u017f\u0180\7\36\2\2\u0180\u0181\5,\27\2\u0181\u0182\7\26\2\2\u0182\u0183"+
		"\5\b\5\2\u0183\u0184\7\27\2\2\u0184\u0185\5\b\5\2\u0185+\3\2\2\2\u0186"+
		"\u0188\7 \2\2\u0187\u0186\3\2\2\2\u0187\u0188\3\2\2\2\u0188\u0189\3\2"+
		"\2\2\u0189\u018a\7!\2\2\u018a\u0193\7\6\2\2\u018b\u0190\5,\27\2\u018c"+
		"\u018d\7\17\2\2\u018d\u018f\5,\27\2\u018e\u018c\3\2\2\2\u018f\u0192\3"+
		"\2\2\2\u0190\u018e\3\2\2\2\u0190\u0191\3\2\2\2\u0191\u0194\3\2\2\2\u0192"+
		"\u0190\3\2\2\2\u0193\u018b\3\2\2\2\u0193\u0194\3\2\2\2\u0194\u0195\3\2"+
		"\2\2\u0195\u0198\7\7\2\2\u0196\u0198\7 \2\2\u0197\u0187\3\2\2\2\u0197"+
		"\u0196\3\2\2\2\u0198-\3\2\2\2\61\63<CEWm~\u0080\u0084\u0088\u008e\u009a"+
		"\u00a1\u00a5\u00b1\u00b6\u00c2\u00c8\u00d4\u00db\u00e2\u00ee\u00f3\u00f5"+
		"\u00fe\u0109\u011a\u011d\u0126\u0129\u012f\u0133\u0138\u0141\u0148\u0152"+
		"\u0155\u0161\u0166\u016e\u0171\u0177\u017b\u0187\u0190\u0193\u0197";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}