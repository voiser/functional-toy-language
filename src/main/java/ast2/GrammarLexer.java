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
		T__17=18, IMPORT=19, ID=20, CLASSID=21, INTEGER=22, FLOAT=23, STRING=24, 
		RESTRICTION=25, BINOP=26, WS=27;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "IMPORT", "ID", "CLASSID", "INTEGER", "FLOAT", "STRING", "RESTRICTION", 
		"BINOP", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'module'", "'import'", "'as'", "'('", "')'", "'{'", "'}'", "','", 
		"'->'", "'def'", "'='", "'if'", "'then'", "'else'", "'::'", "'['", "']'", 
		"':'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, "IMPORT", "ID", "CLASSID", "INTEGER", 
		"FLOAT", "STRING", "RESTRICTION", "BINOP", "WS"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\35\u00ba\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t"+
		"\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16"+
		"\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\22\3\22"+
		"\3\23\3\23\3\24\6\24u\n\24\r\24\16\24v\3\24\3\24\3\24\6\24|\n\24\r\24"+
		"\16\24}\3\25\3\25\7\25\u0082\n\25\f\25\16\25\u0085\13\25\3\26\3\26\7\26"+
		"\u0089\n\26\f\26\16\26\u008c\13\26\3\27\6\27\u008f\n\27\r\27\16\27\u0090"+
		"\3\30\7\30\u0094\n\30\f\30\16\30\u0097\13\30\3\30\3\30\6\30\u009b\n\30"+
		"\r\30\16\30\u009c\3\31\3\31\7\31\u00a1\n\31\f\31\16\31\u00a4\13\31\3\31"+
		"\3\31\3\32\3\32\7\32\u00aa\n\32\f\32\16\32\u00ad\13\32\3\33\3\33\3\33"+
		"\5\33\u00b2\n\33\3\34\6\34\u00b5\n\34\r\34\16\34\u00b6\3\34\3\34\2\2\35"+
		"\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20"+
		"\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\35\3\2\f"+
		"\5\2\60\60^^c|\3\2c|\7\2//\62;C\\aac|\3\2C\\\4\2C\\c|\3\2\62;\3\2$$\t"+
		"\2--//\62;C\\^^aac|\5\2,-//\61\61\5\2\13\f\17\17\"\"\u00c4\2\3\3\2\2\2"+
		"\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2"+
		"\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2"+
		"\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2"+
		"\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2"+
		"\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\39\3\2\2\2\5@\3\2\2\2\7G\3\2"+
		"\2\2\tJ\3\2\2\2\13L\3\2\2\2\rN\3\2\2\2\17P\3\2\2\2\21R\3\2\2\2\23T\3\2"+
		"\2\2\25W\3\2\2\2\27[\3\2\2\2\31]\3\2\2\2\33`\3\2\2\2\35e\3\2\2\2\37j\3"+
		"\2\2\2!m\3\2\2\2#o\3\2\2\2%q\3\2\2\2\'t\3\2\2\2)\177\3\2\2\2+\u0086\3"+
		"\2\2\2-\u008e\3\2\2\2/\u0095\3\2\2\2\61\u009e\3\2\2\2\63\u00a7\3\2\2\2"+
		"\65\u00b1\3\2\2\2\67\u00b4\3\2\2\29:\7o\2\2:;\7q\2\2;<\7f\2\2<=\7w\2\2"+
		"=>\7n\2\2>?\7g\2\2?\4\3\2\2\2@A\7k\2\2AB\7o\2\2BC\7r\2\2CD\7q\2\2DE\7"+
		"t\2\2EF\7v\2\2F\6\3\2\2\2GH\7c\2\2HI\7u\2\2I\b\3\2\2\2JK\7*\2\2K\n\3\2"+
		"\2\2LM\7+\2\2M\f\3\2\2\2NO\7}\2\2O\16\3\2\2\2PQ\7\177\2\2Q\20\3\2\2\2"+
		"RS\7.\2\2S\22\3\2\2\2TU\7/\2\2UV\7@\2\2V\24\3\2\2\2WX\7f\2\2XY\7g\2\2"+
		"YZ\7h\2\2Z\26\3\2\2\2[\\\7?\2\2\\\30\3\2\2\2]^\7k\2\2^_\7h\2\2_\32\3\2"+
		"\2\2`a\7v\2\2ab\7j\2\2bc\7g\2\2cd\7p\2\2d\34\3\2\2\2ef\7g\2\2fg\7n\2\2"+
		"gh\7u\2\2hi\7g\2\2i\36\3\2\2\2jk\7<\2\2kl\7<\2\2l \3\2\2\2mn\7]\2\2n\""+
		"\3\2\2\2op\7_\2\2p$\3\2\2\2qr\7<\2\2r&\3\2\2\2su\t\2\2\2ts\3\2\2\2uv\3"+
		"\2\2\2vt\3\2\2\2vw\3\2\2\2wx\3\2\2\2xy\7\60\2\2y{\t\3\2\2z|\t\4\2\2{z"+
		"\3\2\2\2|}\3\2\2\2}{\3\2\2\2}~\3\2\2\2~(\3\2\2\2\177\u0083\t\3\2\2\u0080"+
		"\u0082\t\4\2\2\u0081\u0080\3\2\2\2\u0082\u0085\3\2\2\2\u0083\u0081\3\2"+
		"\2\2\u0083\u0084\3\2\2\2\u0084*\3\2\2\2\u0085\u0083\3\2\2\2\u0086\u008a"+
		"\t\5\2\2\u0087\u0089\t\6\2\2\u0088\u0087\3\2\2\2\u0089\u008c\3\2\2\2\u008a"+
		"\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b,\3\2\2\2\u008c\u008a\3\2\2\2"+
		"\u008d\u008f\t\7\2\2\u008e\u008d\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u008e"+
		"\3\2\2\2\u0090\u0091\3\2\2\2\u0091.\3\2\2\2\u0092\u0094\t\7\2\2\u0093"+
		"\u0092\3\2\2\2\u0094\u0097\3\2\2\2\u0095\u0093\3\2\2\2\u0095\u0096\3\2"+
		"\2\2\u0096\u0098\3\2\2\2\u0097\u0095\3\2\2\2\u0098\u009a\7\60\2\2\u0099"+
		"\u009b\t\7\2\2\u009a\u0099\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009a\3\2"+
		"\2\2\u009c\u009d\3\2\2\2\u009d\60\3\2\2\2\u009e\u00a2\7$\2\2\u009f\u00a1"+
		"\n\b\2\2\u00a0\u009f\3\2\2\2\u00a1\u00a4\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a2"+
		"\u00a3\3\2\2\2\u00a3\u00a5\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a5\u00a6\7$"+
		"\2\2\u00a6\62\3\2\2\2\u00a7\u00ab\t\3\2\2\u00a8\u00aa\t\t\2\2\u00a9\u00a8"+
		"\3\2\2\2\u00aa\u00ad\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac"+
		"\64\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ae\u00b2\t\n\2\2\u00af\u00b0\7?\2\2"+
		"\u00b0\u00b2\7?\2\2\u00b1\u00ae\3\2\2\2\u00b1\u00af\3\2\2\2\u00b2\66\3"+
		"\2\2\2\u00b3\u00b5\t\13\2\2\u00b4\u00b3\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6"+
		"\u00b4\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00b9\b\34"+
		"\2\2\u00b98\3\2\2\2\16\2v}\u0083\u008a\u0090\u0095\u009c\u00a2\u00ab\u00b1"+
		"\u00b6\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}