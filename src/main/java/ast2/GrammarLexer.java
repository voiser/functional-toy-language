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
		ID=18, CLASSID=19, INTEGER=20, FLOAT=21, STRING=22, BINOP=23, WS=24;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"ID", "CLASSID", "INTEGER", "FLOAT", "STRING", "BINOP", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'module'", "'import'", "'('", "')'", "'{'", "'}'", "','", "'->'", 
		"'def'", "'='", "'if'", "'then'", "'else'", "'::'", "'['", "']'", "':'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, "ID", "CLASSID", "INTEGER", "FLOAT", 
		"STRING", "BINOP", "WS"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\32\u009e\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4"+
		"\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13"+
		"\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3"+
		"\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\7\23m\n\23\f\23\16\23p\13"+
		"\23\3\24\3\24\7\24t\n\24\f\24\16\24w\13\24\3\25\6\25z\n\25\r\25\16\25"+
		"{\3\26\7\26\177\n\26\f\26\16\26\u0082\13\26\3\26\3\26\6\26\u0086\n\26"+
		"\r\26\16\26\u0087\3\27\3\27\7\27\u008c\n\27\f\27\16\27\u008f\13\27\3\27"+
		"\3\27\3\30\3\30\3\30\5\30\u0096\n\30\3\31\6\31\u0099\n\31\r\31\16\31\u009a"+
		"\3\31\3\31\2\2\32\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\3\2\n\3\2"+
		"c|\7\2//\62;C\\aac|\3\2C\\\4\2C\\c|\3\2\62;\3\2$$\5\2,-//\61\61\5\2\13"+
		"\f\17\17\"\"\u00a5\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2"+
		"!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3"+
		"\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\3\63\3\2\2\2\5:\3\2\2\2\7A\3\2\2\2\tC\3"+
		"\2\2\2\13E\3\2\2\2\rG\3\2\2\2\17I\3\2\2\2\21K\3\2\2\2\23N\3\2\2\2\25R"+
		"\3\2\2\2\27T\3\2\2\2\31W\3\2\2\2\33\\\3\2\2\2\35a\3\2\2\2\37d\3\2\2\2"+
		"!f\3\2\2\2#h\3\2\2\2%j\3\2\2\2\'q\3\2\2\2)y\3\2\2\2+\u0080\3\2\2\2-\u0089"+
		"\3\2\2\2/\u0095\3\2\2\2\61\u0098\3\2\2\2\63\64\7o\2\2\64\65\7q\2\2\65"+
		"\66\7f\2\2\66\67\7w\2\2\678\7n\2\289\7g\2\29\4\3\2\2\2:;\7k\2\2;<\7o\2"+
		"\2<=\7r\2\2=>\7q\2\2>?\7t\2\2?@\7v\2\2@\6\3\2\2\2AB\7*\2\2B\b\3\2\2\2"+
		"CD\7+\2\2D\n\3\2\2\2EF\7}\2\2F\f\3\2\2\2GH\7\177\2\2H\16\3\2\2\2IJ\7."+
		"\2\2J\20\3\2\2\2KL\7/\2\2LM\7@\2\2M\22\3\2\2\2NO\7f\2\2OP\7g\2\2PQ\7h"+
		"\2\2Q\24\3\2\2\2RS\7?\2\2S\26\3\2\2\2TU\7k\2\2UV\7h\2\2V\30\3\2\2\2WX"+
		"\7v\2\2XY\7j\2\2YZ\7g\2\2Z[\7p\2\2[\32\3\2\2\2\\]\7g\2\2]^\7n\2\2^_\7"+
		"u\2\2_`\7g\2\2`\34\3\2\2\2ab\7<\2\2bc\7<\2\2c\36\3\2\2\2de\7]\2\2e \3"+
		"\2\2\2fg\7_\2\2g\"\3\2\2\2hi\7<\2\2i$\3\2\2\2jn\t\2\2\2km\t\3\2\2lk\3"+
		"\2\2\2mp\3\2\2\2nl\3\2\2\2no\3\2\2\2o&\3\2\2\2pn\3\2\2\2qu\t\4\2\2rt\t"+
		"\5\2\2sr\3\2\2\2tw\3\2\2\2us\3\2\2\2uv\3\2\2\2v(\3\2\2\2wu\3\2\2\2xz\t"+
		"\6\2\2yx\3\2\2\2z{\3\2\2\2{y\3\2\2\2{|\3\2\2\2|*\3\2\2\2}\177\t\6\2\2"+
		"~}\3\2\2\2\177\u0082\3\2\2\2\u0080~\3\2\2\2\u0080\u0081\3\2\2\2\u0081"+
		"\u0083\3\2\2\2\u0082\u0080\3\2\2\2\u0083\u0085\7\60\2\2\u0084\u0086\t"+
		"\6\2\2\u0085\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0085\3\2\2\2\u0087"+
		"\u0088\3\2\2\2\u0088,\3\2\2\2\u0089\u008d\7$\2\2\u008a\u008c\n\7\2\2\u008b"+
		"\u008a\3\2\2\2\u008c\u008f\3\2\2\2\u008d\u008b\3\2\2\2\u008d\u008e\3\2"+
		"\2\2\u008e\u0090\3\2\2\2\u008f\u008d\3\2\2\2\u0090\u0091\7$\2\2\u0091"+
		".\3\2\2\2\u0092\u0096\t\b\2\2\u0093\u0094\7?\2\2\u0094\u0096\7?\2\2\u0095"+
		"\u0092\3\2\2\2\u0095\u0093\3\2\2\2\u0096\60\3\2\2\2\u0097\u0099\t\t\2"+
		"\2\u0098\u0097\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u0098\3\2\2\2\u009a\u009b"+
		"\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009d\b\31\2\2\u009d\62\3\2\2\2\13"+
		"\2nu{\u0080\u0087\u008d\u0095\u009a\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}