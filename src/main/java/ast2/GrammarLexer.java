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
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, IMPORT=17, 
		ID=18, CLASSID=19, INTEGER=20, FLOAT=21, STRING=22, RESTRICTION=23, BINOP=24, 
		WS=25;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "IMPORT", 
		"ID", "CLASSID", "INTEGER", "FLOAT", "STRING", "RESTRICTION", "BINOP", 
		"WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'module'", "'import'", "'as'", "'('", "')'", "'{'", "'}'", "','", 
		"'->'", "'='", "'if'", "'then'", "'else'", "':'", "'['", "']'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, "IMPORT", "ID", "CLASSID", "INTEGER", "FLOAT", 
		"STRING", "RESTRICTION", "BINOP", "WS"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\33\u00af\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\13"+
		"\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3"+
		"\17\3\20\3\20\3\21\3\21\3\22\6\22j\n\22\r\22\16\22k\3\22\3\22\3\22\6\22"+
		"q\n\22\r\22\16\22r\3\23\3\23\7\23w\n\23\f\23\16\23z\13\23\3\24\3\24\7"+
		"\24~\n\24\f\24\16\24\u0081\13\24\3\25\6\25\u0084\n\25\r\25\16\25\u0085"+
		"\3\26\7\26\u0089\n\26\f\26\16\26\u008c\13\26\3\26\3\26\6\26\u0090\n\26"+
		"\r\26\16\26\u0091\3\27\3\27\7\27\u0096\n\27\f\27\16\27\u0099\13\27\3\27"+
		"\3\27\3\30\3\30\7\30\u009f\n\30\f\30\16\30\u00a2\13\30\3\31\3\31\3\31"+
		"\5\31\u00a7\n\31\3\32\6\32\u00aa\n\32\r\32\16\32\u00ab\3\32\3\32\2\2\33"+
		"\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20"+
		"\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\3\2\r\5\2\60\60^^"+
		"c|\3\2c|\7\2//\62;C\\aac|\b\2))//\62;C\\aac|\3\2C\\\4\2C\\c|\3\2\62;\3"+
		"\2$$\t\2--//\62;C\\^^aac|\5\2,-//\61\61\5\2\13\f\17\17\"\"\u00b9\2\3\3"+
		"\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2"+
		"\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3"+
		"\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2"+
		"%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61"+
		"\3\2\2\2\2\63\3\2\2\2\3\65\3\2\2\2\5<\3\2\2\2\7C\3\2\2\2\tF\3\2\2\2\13"+
		"H\3\2\2\2\rJ\3\2\2\2\17L\3\2\2\2\21N\3\2\2\2\23P\3\2\2\2\25S\3\2\2\2\27"+
		"U\3\2\2\2\31X\3\2\2\2\33]\3\2\2\2\35b\3\2\2\2\37d\3\2\2\2!f\3\2\2\2#i"+
		"\3\2\2\2%t\3\2\2\2\'{\3\2\2\2)\u0083\3\2\2\2+\u008a\3\2\2\2-\u0093\3\2"+
		"\2\2/\u009c\3\2\2\2\61\u00a6\3\2\2\2\63\u00a9\3\2\2\2\65\66\7o\2\2\66"+
		"\67\7q\2\2\678\7f\2\289\7w\2\29:\7n\2\2:;\7g\2\2;\4\3\2\2\2<=\7k\2\2="+
		">\7o\2\2>?\7r\2\2?@\7q\2\2@A\7t\2\2AB\7v\2\2B\6\3\2\2\2CD\7c\2\2DE\7u"+
		"\2\2E\b\3\2\2\2FG\7*\2\2G\n\3\2\2\2HI\7+\2\2I\f\3\2\2\2JK\7}\2\2K\16\3"+
		"\2\2\2LM\7\177\2\2M\20\3\2\2\2NO\7.\2\2O\22\3\2\2\2PQ\7/\2\2QR\7@\2\2"+
		"R\24\3\2\2\2ST\7?\2\2T\26\3\2\2\2UV\7k\2\2VW\7h\2\2W\30\3\2\2\2XY\7v\2"+
		"\2YZ\7j\2\2Z[\7g\2\2[\\\7p\2\2\\\32\3\2\2\2]^\7g\2\2^_\7n\2\2_`\7u\2\2"+
		"`a\7g\2\2a\34\3\2\2\2bc\7<\2\2c\36\3\2\2\2de\7]\2\2e \3\2\2\2fg\7_\2\2"+
		"g\"\3\2\2\2hj\t\2\2\2ih\3\2\2\2jk\3\2\2\2ki\3\2\2\2kl\3\2\2\2lm\3\2\2"+
		"\2mn\7\60\2\2np\t\3\2\2oq\t\4\2\2po\3\2\2\2qr\3\2\2\2rp\3\2\2\2rs\3\2"+
		"\2\2s$\3\2\2\2tx\t\3\2\2uw\t\5\2\2vu\3\2\2\2wz\3\2\2\2xv\3\2\2\2xy\3\2"+
		"\2\2y&\3\2\2\2zx\3\2\2\2{\177\t\6\2\2|~\t\7\2\2}|\3\2\2\2~\u0081\3\2\2"+
		"\2\177}\3\2\2\2\177\u0080\3\2\2\2\u0080(\3\2\2\2\u0081\177\3\2\2\2\u0082"+
		"\u0084\t\b\2\2\u0083\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0083\3\2"+
		"\2\2\u0085\u0086\3\2\2\2\u0086*\3\2\2\2\u0087\u0089\t\b\2\2\u0088\u0087"+
		"\3\2\2\2\u0089\u008c\3\2\2\2\u008a\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b"+
		"\u008d\3\2\2\2\u008c\u008a\3\2\2\2\u008d\u008f\7\60\2\2\u008e\u0090\t"+
		"\b\2\2\u008f\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u008f\3\2\2\2\u0091"+
		"\u0092\3\2\2\2\u0092,\3\2\2\2\u0093\u0097\7$\2\2\u0094\u0096\n\t\2\2\u0095"+
		"\u0094\3\2\2\2\u0096\u0099\3\2\2\2\u0097\u0095\3\2\2\2\u0097\u0098\3\2"+
		"\2\2\u0098\u009a\3\2\2\2\u0099\u0097\3\2\2\2\u009a\u009b\7$\2\2\u009b"+
		".\3\2\2\2\u009c\u00a0\t\3\2\2\u009d\u009f\t\n\2\2\u009e\u009d\3\2\2\2"+
		"\u009f\u00a2\3\2\2\2\u00a0\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\60"+
		"\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a3\u00a7\t\13\2\2\u00a4\u00a5\7?\2\2\u00a5"+
		"\u00a7\7?\2\2\u00a6\u00a3\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a7\62\3\2\2\2"+
		"\u00a8\u00aa\t\f\2\2\u00a9\u00a8\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00a9"+
		"\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00ae\b\32\2\2"+
		"\u00ae\64\3\2\2\2\16\2krx\177\u0085\u008a\u0091\u0097\u00a0\u00a6\u00ab"+
		"\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}