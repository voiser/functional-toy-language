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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, ID=8, VAR=9, WS=10;
	public static final int
		RULE_ty = 0, RULE_ty2 = 1, RULE_simple = 2, RULE_generic = 3, RULE_var = 4, 
		RULE_restriction = 5, RULE_fn = 6, RULE_left = 7;
	public static final String[] ruleNames = {
		"ty", "ty2", "simple", "generic", "var", "restriction", "fn", "left"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'['", "','", "']'", "'+'", "'->'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, "ID", "VAR", "WS"
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
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
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
			setState(24);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(16);
				simple();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(17);
				generic();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(18);
				var();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(19);
				fn();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(20);
				match(T__0);
				setState(21);
				fn();
				setState(22);
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
			setState(33);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(26);
				simple();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(27);
				generic();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(28);
				var();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(29);
				match(T__0);
				setState(30);
				fn();
				setState(31);
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
			setState(35);
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
			setState(37);
			match(ID);
			setState(38);
			match(T__2);
			setState(39);
			ty2();
			setState(44);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(40);
				match(T__3);
				setState(41);
				ty2();
				}
				}
				setState(46);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(47);
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
		public List<RestrictionContext> restriction() {
			return getRuleContexts(RestrictionContext.class);
		}
		public RestrictionContext restriction(int i) {
			return getRuleContext(RestrictionContext.class,i);
		}
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49);
			match(VAR);
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(50);
				restriction();
				}
				}
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class RestrictionContext extends ParserRuleContext {
		public SimpleContext simple() {
			return getRuleContext(SimpleContext.class,0);
		}
		public GenericContext generic() {
			return getRuleContext(GenericContext.class,0);
		}
		public RestrictionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_restriction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypegrammarListener ) ((TypegrammarListener)listener).enterRestriction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypegrammarListener ) ((TypegrammarListener)listener).exitRestriction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypegrammarVisitor ) return ((TypegrammarVisitor<? extends T>)visitor).visitRestriction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RestrictionContext restriction() throws RecognitionException {
		RestrictionContext _localctx = new RestrictionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_restriction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			match(T__5);
			setState(59);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(57);
				simple();
				}
				break;
			case 2:
				{
				setState(58);
				generic();
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
		enterRule(_localctx, 12, RULE_fn);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			left();
			setState(66);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(62);
				match(T__3);
				setState(63);
				left();
				}
				}
				setState(68);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(69);
			match(T__6);
			setState(70);
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
		enterRule(_localctx, 14, RULE_left);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\fM\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\5\2\33\n\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3$\n\3\3\4\3\4\3"+
		"\5\3\5\3\5\3\5\3\5\7\5-\n\5\f\5\16\5\60\13\5\3\5\3\5\3\6\3\6\7\6\66\n"+
		"\6\f\6\16\69\13\6\3\7\3\7\3\7\5\7>\n\7\3\b\3\b\3\b\7\bC\n\b\f\b\16\bF"+
		"\13\b\3\b\3\b\3\b\3\t\3\t\3\t\2\2\n\2\4\6\b\n\f\16\20\2\2O\2\32\3\2\2"+
		"\2\4#\3\2\2\2\6%\3\2\2\2\b\'\3\2\2\2\n\63\3\2\2\2\f:\3\2\2\2\16?\3\2\2"+
		"\2\20J\3\2\2\2\22\33\5\6\4\2\23\33\5\b\5\2\24\33\5\n\6\2\25\33\5\16\b"+
		"\2\26\27\7\3\2\2\27\30\5\16\b\2\30\31\7\4\2\2\31\33\3\2\2\2\32\22\3\2"+
		"\2\2\32\23\3\2\2\2\32\24\3\2\2\2\32\25\3\2\2\2\32\26\3\2\2\2\33\3\3\2"+
		"\2\2\34$\5\6\4\2\35$\5\b\5\2\36$\5\n\6\2\37 \7\3\2\2 !\5\16\b\2!\"\7\4"+
		"\2\2\"$\3\2\2\2#\34\3\2\2\2#\35\3\2\2\2#\36\3\2\2\2#\37\3\2\2\2$\5\3\2"+
		"\2\2%&\7\n\2\2&\7\3\2\2\2\'(\7\n\2\2()\7\5\2\2).\5\4\3\2*+\7\6\2\2+-\5"+
		"\4\3\2,*\3\2\2\2-\60\3\2\2\2.,\3\2\2\2./\3\2\2\2/\61\3\2\2\2\60.\3\2\2"+
		"\2\61\62\7\7\2\2\62\t\3\2\2\2\63\67\7\13\2\2\64\66\5\f\7\2\65\64\3\2\2"+
		"\2\669\3\2\2\2\67\65\3\2\2\2\678\3\2\2\28\13\3\2\2\29\67\3\2\2\2:=\7\b"+
		"\2\2;>\5\6\4\2<>\5\b\5\2=;\3\2\2\2=<\3\2\2\2>\r\3\2\2\2?D\5\20\t\2@A\7"+
		"\6\2\2AC\5\20\t\2B@\3\2\2\2CF\3\2\2\2DB\3\2\2\2DE\3\2\2\2EG\3\2\2\2FD"+
		"\3\2\2\2GH\7\t\2\2HI\5\4\3\2I\17\3\2\2\2JK\5\4\3\2K\21\3\2\2\2\b\32#."+
		"\67=D";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}