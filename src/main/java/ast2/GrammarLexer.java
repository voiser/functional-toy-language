// Generated from src/main/antlr/Grammar.g4 by ANTLR 4.5.1
package ast2;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, ID=17, 
		CLASSID=18, INTEGER=19, FLOAT=20, STRING=21, BINOP=22, WS=23;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "ID", "CLASSID", 
		"INTEGER", "FLOAT", "STRING", "BINOP", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'module'", "'import'", "'('", "')'", "'{'", "'}'", "','", "'->'", 
		"'def'", "'='", "'if'", "'then'", "'else'", "':'", "'['", "']'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, "ID", "CLASSID", "INTEGER", "FLOAT", "STRING", 
		"BINOP", "WS"
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


	public GrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Grammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\31\u0099\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3"+
		"\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3"+
		"\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3"+
		"\21\3\21\3\22\3\22\7\22h\n\22\f\22\16\22k\13\22\3\23\3\23\7\23o\n\23\f"+
		"\23\16\23r\13\23\3\24\6\24u\n\24\r\24\16\24v\3\25\7\25z\n\25\f\25\16\25"+
		"}\13\25\3\25\3\25\6\25\u0081\n\25\r\25\16\25\u0082\3\26\3\26\7\26\u0087"+
		"\n\26\f\26\16\26\u008a\13\26\3\26\3\26\3\27\3\27\3\27\5\27\u0091\n\27"+
		"\3\30\6\30\u0094\n\30\r\30\16\30\u0095\3\30\3\30\2\2\31\3\3\5\4\7\5\t"+
		"\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\'\25)\26+\27-\30/\31\3\2\n\3\2c|\7\2//\62;C\\aac|\3\2C\\\4\2C\\c"+
		"|\3\2\62;\3\2$$\5\2,-//\61\61\5\2\13\f\17\17\"\"\u00a0\2\3\3\2\2\2\2\5"+
		"\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2"+
		"\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33"+
		"\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2"+
		"\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\3\61\3\2\2\2\5"+
		"8\3\2\2\2\7?\3\2\2\2\tA\3\2\2\2\13C\3\2\2\2\rE\3\2\2\2\17G\3\2\2\2\21"+
		"I\3\2\2\2\23L\3\2\2\2\25P\3\2\2\2\27R\3\2\2\2\31U\3\2\2\2\33Z\3\2\2\2"+
		"\35_\3\2\2\2\37a\3\2\2\2!c\3\2\2\2#e\3\2\2\2%l\3\2\2\2\'t\3\2\2\2){\3"+
		"\2\2\2+\u0084\3\2\2\2-\u0090\3\2\2\2/\u0093\3\2\2\2\61\62\7o\2\2\62\63"+
		"\7q\2\2\63\64\7f\2\2\64\65\7w\2\2\65\66\7n\2\2\66\67\7g\2\2\67\4\3\2\2"+
		"\289\7k\2\29:\7o\2\2:;\7r\2\2;<\7q\2\2<=\7t\2\2=>\7v\2\2>\6\3\2\2\2?@"+
		"\7*\2\2@\b\3\2\2\2AB\7+\2\2B\n\3\2\2\2CD\7}\2\2D\f\3\2\2\2EF\7\177\2\2"+
		"F\16\3\2\2\2GH\7.\2\2H\20\3\2\2\2IJ\7/\2\2JK\7@\2\2K\22\3\2\2\2LM\7f\2"+
		"\2MN\7g\2\2NO\7h\2\2O\24\3\2\2\2PQ\7?\2\2Q\26\3\2\2\2RS\7k\2\2ST\7h\2"+
		"\2T\30\3\2\2\2UV\7v\2\2VW\7j\2\2WX\7g\2\2XY\7p\2\2Y\32\3\2\2\2Z[\7g\2"+
		"\2[\\\7n\2\2\\]\7u\2\2]^\7g\2\2^\34\3\2\2\2_`\7<\2\2`\36\3\2\2\2ab\7]"+
		"\2\2b \3\2\2\2cd\7_\2\2d\"\3\2\2\2ei\t\2\2\2fh\t\3\2\2gf\3\2\2\2hk\3\2"+
		"\2\2ig\3\2\2\2ij\3\2\2\2j$\3\2\2\2ki\3\2\2\2lp\t\4\2\2mo\t\5\2\2nm\3\2"+
		"\2\2or\3\2\2\2pn\3\2\2\2pq\3\2\2\2q&\3\2\2\2rp\3\2\2\2su\t\6\2\2ts\3\2"+
		"\2\2uv\3\2\2\2vt\3\2\2\2vw\3\2\2\2w(\3\2\2\2xz\t\6\2\2yx\3\2\2\2z}\3\2"+
		"\2\2{y\3\2\2\2{|\3\2\2\2|~\3\2\2\2}{\3\2\2\2~\u0080\7\60\2\2\177\u0081"+
		"\t\6\2\2\u0080\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0080\3\2\2\2\u0082"+
		"\u0083\3\2\2\2\u0083*\3\2\2\2\u0084\u0088\7$\2\2\u0085\u0087\n\7\2\2\u0086"+
		"\u0085\3\2\2\2\u0087\u008a\3\2\2\2\u0088\u0086\3\2\2\2\u0088\u0089\3\2"+
		"\2\2\u0089\u008b\3\2\2\2\u008a\u0088\3\2\2\2\u008b\u008c\7$\2\2\u008c"+
		",\3\2\2\2\u008d\u0091\t\b\2\2\u008e\u008f\7?\2\2\u008f\u0091\7?\2\2\u0090"+
		"\u008d\3\2\2\2\u0090\u008e\3\2\2\2\u0091.\3\2\2\2\u0092\u0094\t\t\2\2"+
		"\u0093\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0093\3\2\2\2\u0095\u0096"+
		"\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0098\b\30\2\2\u0098\60\3\2\2\2\13"+
		"\2ipv{\u0082\u0088\u0090\u0095\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}