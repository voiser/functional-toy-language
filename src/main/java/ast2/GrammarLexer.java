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
		T__17=18, ID=19, CLASSID=20, INTEGER=21, FLOAT=22, STRING=23, BINOP=24, 
		WS=25;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "ID", "CLASSID", "INTEGER", "FLOAT", "STRING", "BINOP", "WS"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\33\u00a0\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3"+
		"\13\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3"+
		"\20\3\20\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\7\24o\n\24"+
		"\f\24\16\24r\13\24\3\25\3\25\7\25v\n\25\f\25\16\25y\13\25\3\26\6\26|\n"+
		"\26\r\26\16\26}\3\27\7\27\u0081\n\27\f\27\16\27\u0084\13\27\3\27\3\27"+
		"\6\27\u0088\n\27\r\27\16\27\u0089\3\30\3\30\7\30\u008e\n\30\f\30\16\30"+
		"\u0091\13\30\3\30\3\30\3\31\3\31\3\31\5\31\u0098\n\31\3\32\6\32\u009b"+
		"\n\32\r\32\16\32\u009c\3\32\3\32\2\2\33\3\3\5\4\7\5\t\6\13\7\r\b\17\t"+
		"\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27"+
		"-\30/\31\61\32\63\33\3\2\n\3\2c|\b\2))//\62;C\\aac|\3\2C\\\4\2C\\c|\3"+
		"\2\62;\3\2$$\5\2,-//\61\61\5\2\13\f\17\17\"\"\u00a7\2\3\3\2\2\2\2\5\3"+
		"\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2"+
		"\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3"+
		"\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'"+
		"\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63"+
		"\3\2\2\2\3\65\3\2\2\2\5<\3\2\2\2\7C\3\2\2\2\tE\3\2\2\2\13H\3\2\2\2\rJ"+
		"\3\2\2\2\17L\3\2\2\2\21N\3\2\2\2\23P\3\2\2\2\25R\3\2\2\2\27T\3\2\2\2\31"+
		"W\3\2\2\2\33Y\3\2\2\2\35\\\3\2\2\2\37a\3\2\2\2!f\3\2\2\2#h\3\2\2\2%j\3"+
		"\2\2\2\'l\3\2\2\2)s\3\2\2\2+{\3\2\2\2-\u0082\3\2\2\2/\u008b\3\2\2\2\61"+
		"\u0097\3\2\2\2\63\u009a\3\2\2\2\65\66\7o\2\2\66\67\7q\2\2\678\7f\2\28"+
		"9\7w\2\29:\7n\2\2:;\7g\2\2;\4\3\2\2\2<=\7k\2\2=>\7o\2\2>?\7r\2\2?@\7q"+
		"\2\2@A\7t\2\2AB\7v\2\2B\6\3\2\2\2CD\7\60\2\2D\b\3\2\2\2EF\7c\2\2FG\7u"+
		"\2\2G\n\3\2\2\2HI\7*\2\2I\f\3\2\2\2JK\7+\2\2K\16\3\2\2\2LM\7-\2\2M\20"+
		"\3\2\2\2NO\7}\2\2O\22\3\2\2\2PQ\7\177\2\2Q\24\3\2\2\2RS\7.\2\2S\26\3\2"+
		"\2\2TU\7/\2\2UV\7@\2\2V\30\3\2\2\2WX\7?\2\2X\32\3\2\2\2YZ\7k\2\2Z[\7h"+
		"\2\2[\34\3\2\2\2\\]\7v\2\2]^\7j\2\2^_\7g\2\2_`\7p\2\2`\36\3\2\2\2ab\7"+
		"g\2\2bc\7n\2\2cd\7u\2\2de\7g\2\2e \3\2\2\2fg\7<\2\2g\"\3\2\2\2hi\7]\2"+
		"\2i$\3\2\2\2jk\7_\2\2k&\3\2\2\2lp\t\2\2\2mo\t\3\2\2nm\3\2\2\2or\3\2\2"+
		"\2pn\3\2\2\2pq\3\2\2\2q(\3\2\2\2rp\3\2\2\2sw\t\4\2\2tv\t\5\2\2ut\3\2\2"+
		"\2vy\3\2\2\2wu\3\2\2\2wx\3\2\2\2x*\3\2\2\2yw\3\2\2\2z|\t\6\2\2{z\3\2\2"+
		"\2|}\3\2\2\2}{\3\2\2\2}~\3\2\2\2~,\3\2\2\2\177\u0081\t\6\2\2\u0080\177"+
		"\3\2\2\2\u0081\u0084\3\2\2\2\u0082\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083"+
		"\u0085\3\2\2\2\u0084\u0082\3\2\2\2\u0085\u0087\7\60\2\2\u0086\u0088\t"+
		"\6\2\2\u0087\u0086\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u0087\3\2\2\2\u0089"+
		"\u008a\3\2\2\2\u008a.\3\2\2\2\u008b\u008f\7$\2\2\u008c\u008e\n\7\2\2\u008d"+
		"\u008c\3\2\2\2\u008e\u0091\3\2\2\2\u008f\u008d\3\2\2\2\u008f\u0090\3\2"+
		"\2\2\u0090\u0092\3\2\2\2\u0091\u008f\3\2\2\2\u0092\u0093\7$\2\2\u0093"+
		"\60\3\2\2\2\u0094\u0098\t\b\2\2\u0095\u0096\7?\2\2\u0096\u0098\7?\2\2"+
		"\u0097\u0094\3\2\2\2\u0097\u0095\3\2\2\2\u0098\62\3\2\2\2\u0099\u009b"+
		"\t\t\2\2\u009a\u0099\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009a\3\2\2\2\u009c"+
		"\u009d\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u009f\b\32\2\2\u009f\64\3\2\2"+
		"\2\13\2pw}\u0082\u0089\u008f\u0097\u009c\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}