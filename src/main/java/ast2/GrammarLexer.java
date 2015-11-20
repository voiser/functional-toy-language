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
		CLASSID=18, INTEGER=19, FLOAT=20, STRING=21, WS=22;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "ID", "CLASSID", 
		"INTEGER", "FLOAT", "STRING", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'module'", "'import'", "'('", "')'", "'{'", "'}'", "','", "'->'", 
		"'def'", "'='", "'if'", "'then'", "'else'", "':'", "'['", "']'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, "ID", "CLASSID", "INTEGER", "FLOAT", "STRING", 
		"WS"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\30\u0092\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7"+
		"\3\7\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\r\3\r"+
		"\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3"+
		"\22\3\22\7\22f\n\22\f\22\16\22i\13\22\3\23\3\23\7\23m\n\23\f\23\16\23"+
		"p\13\23\3\24\6\24s\n\24\r\24\16\24t\3\25\7\25x\n\25\f\25\16\25{\13\25"+
		"\3\25\3\25\6\25\177\n\25\r\25\16\25\u0080\3\26\3\26\7\26\u0085\n\26\f"+
		"\26\16\26\u0088\13\26\3\26\3\26\3\27\6\27\u008d\n\27\r\27\16\27\u008e"+
		"\3\27\3\27\2\2\30\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30\3\2\t\3\2c|\7\2//\62"+
		";C\\aac|\3\2C\\\4\2C\\c|\3\2\62;\3\2$$\5\2\13\f\17\17\"\"\u0098\2\3\3"+
		"\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2"+
		"\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3"+
		"\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2"+
		"%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\3/\3\2\2\2\5\66"+
		"\3\2\2\2\7=\3\2\2\2\t?\3\2\2\2\13A\3\2\2\2\rC\3\2\2\2\17E\3\2\2\2\21G"+
		"\3\2\2\2\23J\3\2\2\2\25N\3\2\2\2\27P\3\2\2\2\31S\3\2\2\2\33X\3\2\2\2\35"+
		"]\3\2\2\2\37_\3\2\2\2!a\3\2\2\2#c\3\2\2\2%j\3\2\2\2\'r\3\2\2\2)y\3\2\2"+
		"\2+\u0082\3\2\2\2-\u008c\3\2\2\2/\60\7o\2\2\60\61\7q\2\2\61\62\7f\2\2"+
		"\62\63\7w\2\2\63\64\7n\2\2\64\65\7g\2\2\65\4\3\2\2\2\66\67\7k\2\2\678"+
		"\7o\2\289\7r\2\29:\7q\2\2:;\7t\2\2;<\7v\2\2<\6\3\2\2\2=>\7*\2\2>\b\3\2"+
		"\2\2?@\7+\2\2@\n\3\2\2\2AB\7}\2\2B\f\3\2\2\2CD\7\177\2\2D\16\3\2\2\2E"+
		"F\7.\2\2F\20\3\2\2\2GH\7/\2\2HI\7@\2\2I\22\3\2\2\2JK\7f\2\2KL\7g\2\2L"+
		"M\7h\2\2M\24\3\2\2\2NO\7?\2\2O\26\3\2\2\2PQ\7k\2\2QR\7h\2\2R\30\3\2\2"+
		"\2ST\7v\2\2TU\7j\2\2UV\7g\2\2VW\7p\2\2W\32\3\2\2\2XY\7g\2\2YZ\7n\2\2Z"+
		"[\7u\2\2[\\\7g\2\2\\\34\3\2\2\2]^\7<\2\2^\36\3\2\2\2_`\7]\2\2` \3\2\2"+
		"\2ab\7_\2\2b\"\3\2\2\2cg\t\2\2\2df\t\3\2\2ed\3\2\2\2fi\3\2\2\2ge\3\2\2"+
		"\2gh\3\2\2\2h$\3\2\2\2ig\3\2\2\2jn\t\4\2\2km\t\5\2\2lk\3\2\2\2mp\3\2\2"+
		"\2nl\3\2\2\2no\3\2\2\2o&\3\2\2\2pn\3\2\2\2qs\t\6\2\2rq\3\2\2\2st\3\2\2"+
		"\2tr\3\2\2\2tu\3\2\2\2u(\3\2\2\2vx\t\6\2\2wv\3\2\2\2x{\3\2\2\2yw\3\2\2"+
		"\2yz\3\2\2\2z|\3\2\2\2{y\3\2\2\2|~\7\60\2\2}\177\t\6\2\2~}\3\2\2\2\177"+
		"\u0080\3\2\2\2\u0080~\3\2\2\2\u0080\u0081\3\2\2\2\u0081*\3\2\2\2\u0082"+
		"\u0086\7$\2\2\u0083\u0085\n\7\2\2\u0084\u0083\3\2\2\2\u0085\u0088\3\2"+
		"\2\2\u0086\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0089\3\2\2\2\u0088"+
		"\u0086\3\2\2\2\u0089\u008a\7$\2\2\u008a,\3\2\2\2\u008b\u008d\t\b\2\2\u008c"+
		"\u008b\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u008c\3\2\2\2\u008e\u008f\3\2"+
		"\2\2\u008f\u0090\3\2\2\2\u0090\u0091\b\27\2\2\u0091.\3\2\2\2\n\2gnty\u0080"+
		"\u0086\u008e\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}