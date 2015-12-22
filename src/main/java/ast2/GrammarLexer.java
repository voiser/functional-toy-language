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
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, ID=26, CLASSID=27, INTEGER=28, FLOAT=29, STRING=30, WS=31;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
		"ID", "CLASSID", "INTEGER", "FLOAT", "STRING", "WS"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2!\u00bc\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3"+
		"\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3"+
		"\r\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\22\3\22\3\22\3\23\3"+
		"\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3"+
		"\27\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\33\3"+
		"\33\7\33\u0090\n\33\f\33\16\33\u0093\13\33\3\34\3\34\7\34\u0097\n\34\f"+
		"\34\16\34\u009a\13\34\3\35\6\35\u009d\n\35\r\35\16\35\u009e\3\36\7\36"+
		"\u00a2\n\36\f\36\16\36\u00a5\13\36\3\36\3\36\6\36\u00a9\n\36\r\36\16\36"+
		"\u00aa\3\37\3\37\7\37\u00af\n\37\f\37\16\37\u00b2\13\37\3\37\3\37\3 \6"+
		" \u00b7\n \r \16 \u00b8\3 \3 \2\2!\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n"+
		"\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30"+
		"/\31\61\32\63\33\65\34\67\359\36;\37= ?!\3\2\t\3\2c|\b\2))//\62;C\\aa"+
		"c|\3\2C\\\4\2C\\c|\3\2\62;\3\2$$\5\2\13\f\17\17\"\"\u00c2\2\3\3\2\2\2"+
		"\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2"+
		"\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2"+
		"\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2"+
		"\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2"+
		"\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2"+
		"\2\2\2?\3\2\2\2\3A\3\2\2\2\5H\3\2\2\2\7O\3\2\2\2\tQ\3\2\2\2\13T\3\2\2"+
		"\2\rV\3\2\2\2\17X\3\2\2\2\21[\3\2\2\2\23]\3\2\2\2\25_\3\2\2\2\27a\3\2"+
		"\2\2\31c\3\2\2\2\33e\3\2\2\2\35g\3\2\2\2\37i\3\2\2\2!l\3\2\2\2#n\3\2\2"+
		"\2%q\3\2\2\2\'v\3\2\2\2){\3\2\2\2+}\3\2\2\2-\177\3\2\2\2/\u0081\3\2\2"+
		"\2\61\u0084\3\2\2\2\63\u008a\3\2\2\2\65\u008d\3\2\2\2\67\u0094\3\2\2\2"+
		"9\u009c\3\2\2\2;\u00a3\3\2\2\2=\u00ac\3\2\2\2?\u00b6\3\2\2\2AB\7o\2\2"+
		"BC\7q\2\2CD\7f\2\2DE\7w\2\2EF\7n\2\2FG\7g\2\2G\4\3\2\2\2HI\7k\2\2IJ\7"+
		"o\2\2JK\7r\2\2KL\7q\2\2LM\7t\2\2MN\7v\2\2N\6\3\2\2\2OP\7\60\2\2P\b\3\2"+
		"\2\2QR\7c\2\2RS\7u\2\2S\n\3\2\2\2TU\7*\2\2U\f\3\2\2\2VW\7+\2\2W\16\3\2"+
		"\2\2XY\7?\2\2YZ\7?\2\2Z\20\3\2\2\2[\\\7,\2\2\\\22\3\2\2\2]^\7\61\2\2^"+
		"\24\3\2\2\2_`\7-\2\2`\26\3\2\2\2ab\7/\2\2b\30\3\2\2\2cd\7}\2\2d\32\3\2"+
		"\2\2ef\7\177\2\2f\34\3\2\2\2gh\7.\2\2h\36\3\2\2\2ij\7?\2\2jk\7@\2\2k "+
		"\3\2\2\2lm\7?\2\2m\"\3\2\2\2no\7k\2\2op\7h\2\2p$\3\2\2\2qr\7v\2\2rs\7"+
		"j\2\2st\7g\2\2tu\7p\2\2u&\3\2\2\2vw\7g\2\2wx\7n\2\2xy\7u\2\2yz\7g\2\2"+
		"z(\3\2\2\2{|\7<\2\2|*\3\2\2\2}~\7]\2\2~,\3\2\2\2\177\u0080\7_\2\2\u0080"+
		".\3\2\2\2\u0081\u0082\7/\2\2\u0082\u0083\7@\2\2\u0083\60\3\2\2\2\u0084"+
		"\u0085\7e\2\2\u0085\u0086\7n\2\2\u0086\u0087\7c\2\2\u0087\u0088\7u\2\2"+
		"\u0088\u0089\7u\2\2\u0089\62\3\2\2\2\u008a\u008b\7k\2\2\u008b\u008c\7"+
		"u\2\2\u008c\64\3\2\2\2\u008d\u0091\t\2\2\2\u008e\u0090\t\3\2\2\u008f\u008e"+
		"\3\2\2\2\u0090\u0093\3\2\2\2\u0091\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092"+
		"\66\3\2\2\2\u0093\u0091\3\2\2\2\u0094\u0098\t\4\2\2\u0095\u0097\t\5\2"+
		"\2\u0096\u0095\3\2\2\2\u0097\u009a\3\2\2\2\u0098\u0096\3\2\2\2\u0098\u0099"+
		"\3\2\2\2\u00998\3\2\2\2\u009a\u0098\3\2\2\2\u009b\u009d\t\6\2\2\u009c"+
		"\u009b\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u009c\3\2\2\2\u009e\u009f\3\2"+
		"\2\2\u009f:\3\2\2\2\u00a0\u00a2\t\6\2\2\u00a1\u00a0\3\2\2\2\u00a2\u00a5"+
		"\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a6\3\2\2\2\u00a5"+
		"\u00a3\3\2\2\2\u00a6\u00a8\7\60\2\2\u00a7\u00a9\t\6\2\2\u00a8\u00a7\3"+
		"\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00a8\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab"+
		"<\3\2\2\2\u00ac\u00b0\7$\2\2\u00ad\u00af\n\7\2\2\u00ae\u00ad\3\2\2\2\u00af"+
		"\u00b2\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b3\3\2"+
		"\2\2\u00b2\u00b0\3\2\2\2\u00b3\u00b4\7$\2\2\u00b4>\3\2\2\2\u00b5\u00b7"+
		"\t\b\2\2\u00b6\u00b5\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b8"+
		"\u00b9\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00bb\b \2\2\u00bb@\3\2\2\2\n"+
		"\2\u0091\u0098\u009e\u00a3\u00aa\u00b0\u00b8\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}