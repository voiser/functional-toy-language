// Generated from src/main/antlr/Typegrammar.g4 by ANTLR 4.5.1
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
public class TypegrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, ID=7, VAR=8, WS=9;
	public static final int
		RULE_ty = 0, RULE_ty2 = 1, RULE_simple = 2, RULE_generic = 3, RULE_var = 4, 
		RULE_fn = 5, RULE_left = 6;
	public static final String[] ruleNames = {
		"ty", "ty2", "simple", "generic", "var", "fn", "left"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'['", "','", "']'", "'->'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, "ID", "VAR", "WS"
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
	public String getGrammarFileName() { return "Typegrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TypegrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class TyContext extends ParserRuleContext {
		public SimpleContext simple() {
			return getRuleContext(SimpleContext.class,0);
		}
		public GenericContext generic() {
			return getRuleContext(GenericContext.class,0);
		}
		public FnContext fn() {
			return getRuleContext(FnContext.class,0);
		}
		public TyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ty; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypegrammarListener ) ((TypegrammarListener)listener).enterTy(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypegrammarListener ) ((TypegrammarListener)listener).exitTy(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypegrammarVisitor ) return ((TypegrammarVisitor<? extends T>)visitor).visitTy(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TyContext ty() throws RecognitionException {
		TyContext _localctx = new TyContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_ty);
		try {
			setState(17);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(14);
				simple();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(15);
				generic();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(16);
				fn();
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

	public static class Ty2Context extends ParserRuleContext {
		public SimpleContext simple() {
			return getRuleContext(SimpleContext.class,0);
		}
		public GenericContext generic() {
			return getRuleContext(GenericContext.class,0);
		}
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public FnContext fn() {
			return getRuleContext(FnContext.class,0);
		}
		public Ty2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ty2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypegrammarListener ) ((TypegrammarListener)listener).enterTy2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypegrammarListener ) ((TypegrammarListener)listener).exitTy2(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypegrammarVisitor ) return ((TypegrammarVisitor<? extends T>)visitor).visitTy2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Ty2Context ty2() throws RecognitionException {
		Ty2Context _localctx = new Ty2Context(_ctx, getState());
		enterRule(_localctx, 2, RULE_ty2);
		try {
			setState(26);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(19);
				simple();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(20);
				generic();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(21);
				var();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(22);
				match(T__0);
				setState(23);
				fn();
				setState(24);
				match(T__1);
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

	public static class SimpleContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(TypegrammarParser.ID, 0); }
		public SimpleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simple; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypegrammarListener ) ((TypegrammarListener)listener).enterSimple(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypegrammarListener ) ((TypegrammarListener)listener).exitSimple(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypegrammarVisitor ) return ((TypegrammarVisitor<? extends T>)visitor).visitSimple(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SimpleContext simple() throws RecognitionException {
		SimpleContext _localctx = new SimpleContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_simple);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
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

	public static class GenericContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(TypegrammarParser.ID, 0); }
		public List<Ty2Context> ty2() {
			return getRuleContexts(Ty2Context.class);
		}
		public Ty2Context ty2(int i) {
			return getRuleContext(Ty2Context.class,i);
		}
		public GenericContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_generic; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypegrammarListener ) ((TypegrammarListener)listener).enterGeneric(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypegrammarListener ) ((TypegrammarListener)listener).exitGeneric(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypegrammarVisitor ) return ((TypegrammarVisitor<? extends T>)visitor).visitGeneric(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GenericContext generic() throws RecognitionException {
		GenericContext _localctx = new GenericContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_generic);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			match(ID);
			setState(31);
			match(T__2);
			setState(32);
			ty2();
			setState(37);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(33);
				match(T__3);
				setState(34);
				ty2();
				}
				}
				setState(39);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(40);
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

	public static class VarContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(TypegrammarParser.VAR, 0); }
		public VarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypegrammarListener ) ((TypegrammarListener)listener).enterVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypegrammarListener ) ((TypegrammarListener)listener).exitVar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypegrammarVisitor ) return ((TypegrammarVisitor<? extends T>)visitor).visitVar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarContext var() throws RecognitionException {
		VarContext _localctx = new VarContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_var);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			match(VAR);
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
		public Ty2Context right;
		public List<LeftContext> left() {
			return getRuleContexts(LeftContext.class);
		}
		public LeftContext left(int i) {
			return getRuleContext(LeftContext.class,i);
		}
		public Ty2Context ty2() {
			return getRuleContext(Ty2Context.class,0);
		}
		public FnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fn; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypegrammarListener ) ((TypegrammarListener)listener).enterFn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypegrammarListener ) ((TypegrammarListener)listener).exitFn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypegrammarVisitor ) return ((TypegrammarVisitor<? extends T>)visitor).visitFn(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FnContext fn() throws RecognitionException {
		FnContext _localctx = new FnContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_fn);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			left();
			setState(49);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(45);
				match(T__3);
				setState(46);
				left();
				}
				}
				setState(51);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(52);
			match(T__5);
			setState(53);
			((FnContext)_localctx).right = ty2();
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

	public static class LeftContext extends ParserRuleContext {
		public Ty2Context ty2() {
			return getRuleContext(Ty2Context.class,0);
		}
		public LeftContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_left; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypegrammarListener ) ((TypegrammarListener)listener).enterLeft(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypegrammarListener ) ((TypegrammarListener)listener).exitLeft(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypegrammarVisitor ) return ((TypegrammarVisitor<? extends T>)visitor).visitLeft(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LeftContext left() throws RecognitionException {
		LeftContext _localctx = new LeftContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_left);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			ty2();
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\13<\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3\2\3\2\5\2\24\n\2\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\5\3\35\n\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\7\5&\n"+
		"\5\f\5\16\5)\13\5\3\5\3\5\3\6\3\6\3\7\3\7\3\7\7\7\62\n\7\f\7\16\7\65\13"+
		"\7\3\7\3\7\3\7\3\b\3\b\3\b\2\2\t\2\4\6\b\n\f\16\2\2;\2\23\3\2\2\2\4\34"+
		"\3\2\2\2\6\36\3\2\2\2\b \3\2\2\2\n,\3\2\2\2\f.\3\2\2\2\169\3\2\2\2\20"+
		"\24\5\6\4\2\21\24\5\b\5\2\22\24\5\f\7\2\23\20\3\2\2\2\23\21\3\2\2\2\23"+
		"\22\3\2\2\2\24\3\3\2\2\2\25\35\5\6\4\2\26\35\5\b\5\2\27\35\5\n\6\2\30"+
		"\31\7\3\2\2\31\32\5\f\7\2\32\33\7\4\2\2\33\35\3\2\2\2\34\25\3\2\2\2\34"+
		"\26\3\2\2\2\34\27\3\2\2\2\34\30\3\2\2\2\35\5\3\2\2\2\36\37\7\t\2\2\37"+
		"\7\3\2\2\2 !\7\t\2\2!\"\7\5\2\2\"\'\5\4\3\2#$\7\6\2\2$&\5\4\3\2%#\3\2"+
		"\2\2&)\3\2\2\2\'%\3\2\2\2\'(\3\2\2\2(*\3\2\2\2)\'\3\2\2\2*+\7\7\2\2+\t"+
		"\3\2\2\2,-\7\n\2\2-\13\3\2\2\2.\63\5\16\b\2/\60\7\6\2\2\60\62\5\16\b\2"+
		"\61/\3\2\2\2\62\65\3\2\2\2\63\61\3\2\2\2\63\64\3\2\2\2\64\66\3\2\2\2\65"+
		"\63\3\2\2\2\66\67\7\b\2\2\678\5\4\3\28\r\3\2\2\29:\5\4\3\2:\17\3\2\2\2"+
		"\6\23\34\'\63";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}