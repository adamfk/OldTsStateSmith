// Generated from Grammar1.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class Grammar1Lexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		LINE_ENDER=18, IDENTIFIER=19, LINE_COMMENT=20, STAR_COMMENT=21, STRING=22, 
		TICK_STRING=23, DIGIT=24, PERIOD=25, COMMA=26, CODE_SYMBOL=27, HWS=28;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"LINE_ENDER", "IDENTIFIER", "NOT_NL_CR", "LINE_COMMENT", "STAR_COMMENT", 
			"ESCAPED_CHAR", "NON_QUOTE_CHAR", "STRING_CHAR", "STRING", "TICK_STRING", 
			"IDENTIFIER_NON_DIGIT", "DIGIT", "PERIOD", "COMMA", "CODE_SYMBOL", "HWS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'$STATEMACHINE'", "':'", "'$NOTES'", "'$ORTHO'", "'#'", "'('", 
			"')'", "'['", "']'", "'/'", "'::'", "'->'", "'{'", "'}'", "'-'", "'+'", 
			"'e'", null, null, null, null, null, null, null, "'.'", "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, "LINE_ENDER", "IDENTIFIER", "LINE_COMMENT", 
			"STAR_COMMENT", "STRING", "TICK_STRING", "DIGIT", "PERIOD", "COMMA", 
			"CODE_SYMBOL", "HWS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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


	public Grammar1Lexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Grammar1.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\36\u00cf\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6"+
		"\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r"+
		"\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\6\23\u0081\n\23"+
		"\r\23\16\23\u0082\3\24\3\24\3\24\7\24\u0088\n\24\f\24\16\24\u008b\13\24"+
		"\3\25\3\25\3\26\3\26\3\26\3\26\7\26\u0093\n\26\f\26\16\26\u0096\13\26"+
		"\3\27\3\27\3\27\3\27\7\27\u009c\n\27\f\27\16\27\u009f\13\27\3\27\3\27"+
		"\3\27\3\30\3\30\3\30\3\31\3\31\3\32\3\32\5\32\u00ab\n\32\3\33\3\33\7\33"+
		"\u00af\n\33\f\33\16\33\u00b2\13\33\3\33\3\33\3\34\3\34\7\34\u00b8\n\34"+
		"\f\34\16\34\u00bb\13\34\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 "+
		"\3!\3!\5!\u00c9\n!\3\"\6\"\u00cc\n\"\r\"\16\"\u00cd\3\u009d\2#\3\3\5\4"+
		"\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22"+
		"#\23%\24\'\25)\2+\26-\27/\2\61\2\63\2\65\30\67\319\2;\32=\33?\34A\35C"+
		"\36\3\2\t\4\2\f\f\17\17\3\2$$\3\2))\6\2&&C\\aac|\3\2\62;\13\2##\'(,-/"+
		"/\61\61<A``~~\u0080\u0080\4\2\13\13\"\"\2\u00d3\2\3\3\2\2\2\2\5\3\2\2"+
		"\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\2;\3\2\2\2\2=\3"+
		"\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\3E\3\2\2\2\5S\3\2\2\2\7U\3\2\2"+
		"\2\t\\\3\2\2\2\13c\3\2\2\2\re\3\2\2\2\17g\3\2\2\2\21i\3\2\2\2\23k\3\2"+
		"\2\2\25m\3\2\2\2\27o\3\2\2\2\31r\3\2\2\2\33u\3\2\2\2\35w\3\2\2\2\37y\3"+
		"\2\2\2!{\3\2\2\2#}\3\2\2\2%\u0080\3\2\2\2\'\u0084\3\2\2\2)\u008c\3\2\2"+
		"\2+\u008e\3\2\2\2-\u0097\3\2\2\2/\u00a3\3\2\2\2\61\u00a6\3\2\2\2\63\u00aa"+
		"\3\2\2\2\65\u00ac\3\2\2\2\67\u00b5\3\2\2\29\u00be\3\2\2\2;\u00c0\3\2\2"+
		"\2=\u00c2\3\2\2\2?\u00c4\3\2\2\2A\u00c8\3\2\2\2C\u00cb\3\2\2\2EF\7&\2"+
		"\2FG\7U\2\2GH\7V\2\2HI\7C\2\2IJ\7V\2\2JK\7G\2\2KL\7O\2\2LM\7C\2\2MN\7"+
		"E\2\2NO\7J\2\2OP\7K\2\2PQ\7P\2\2QR\7G\2\2R\4\3\2\2\2ST\7<\2\2T\6\3\2\2"+
		"\2UV\7&\2\2VW\7P\2\2WX\7Q\2\2XY\7V\2\2YZ\7G\2\2Z[\7U\2\2[\b\3\2\2\2\\"+
		"]\7&\2\2]^\7Q\2\2^_\7T\2\2_`\7V\2\2`a\7J\2\2ab\7Q\2\2b\n\3\2\2\2cd\7%"+
		"\2\2d\f\3\2\2\2ef\7*\2\2f\16\3\2\2\2gh\7+\2\2h\20\3\2\2\2ij\7]\2\2j\22"+
		"\3\2\2\2kl\7_\2\2l\24\3\2\2\2mn\7\61\2\2n\26\3\2\2\2op\7<\2\2pq\7<\2\2"+
		"q\30\3\2\2\2rs\7/\2\2st\7@\2\2t\32\3\2\2\2uv\7}\2\2v\34\3\2\2\2wx\7\177"+
		"\2\2x\36\3\2\2\2yz\7/\2\2z \3\2\2\2{|\7-\2\2|\"\3\2\2\2}~\7g\2\2~$\3\2"+
		"\2\2\177\u0081\t\2\2\2\u0080\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0080"+
		"\3\2\2\2\u0082\u0083\3\2\2\2\u0083&\3\2\2\2\u0084\u0089\59\35\2\u0085"+
		"\u0088\59\35\2\u0086\u0088\5;\36\2\u0087\u0085\3\2\2\2\u0087\u0086\3\2"+
		"\2\2\u0088\u008b\3\2\2\2\u0089\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a"+
		"(\3\2\2\2\u008b\u0089\3\2\2\2\u008c\u008d\n\2\2\2\u008d*\3\2\2\2\u008e"+
		"\u008f\7\61\2\2\u008f\u0090\7\61\2\2\u0090\u0094\3\2\2\2\u0091\u0093\5"+
		")\25\2\u0092\u0091\3\2\2\2\u0093\u0096\3\2\2\2\u0094\u0092\3\2\2\2\u0094"+
		"\u0095\3\2\2\2\u0095,\3\2\2\2\u0096\u0094\3\2\2\2\u0097\u0098\7\61\2\2"+
		"\u0098\u0099\7,\2\2\u0099\u009d\3\2\2\2\u009a\u009c\13\2\2\2\u009b\u009a"+
		"\3\2\2\2\u009c\u009f\3\2\2\2\u009d\u009e\3\2\2\2\u009d\u009b\3\2\2\2\u009e"+
		"\u00a0\3\2\2\2\u009f\u009d\3\2\2\2\u00a0\u00a1\7,\2\2\u00a1\u00a2\7\61"+
		"\2\2\u00a2.\3\2\2\2\u00a3\u00a4\7^\2\2\u00a4\u00a5\13\2\2\2\u00a5\60\3"+
		"\2\2\2\u00a6\u00a7\n\3\2\2\u00a7\62\3\2\2\2\u00a8\u00ab\5/\30\2\u00a9"+
		"\u00ab\5\61\31\2\u00aa\u00a8\3\2\2\2\u00aa\u00a9\3\2\2\2\u00ab\64\3\2"+
		"\2\2\u00ac\u00b0\7$\2\2\u00ad\u00af\5\63\32\2\u00ae\u00ad\3\2\2\2\u00af"+
		"\u00b2\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b3\3\2"+
		"\2\2\u00b2\u00b0\3\2\2\2\u00b3\u00b4\7$\2\2\u00b4\66\3\2\2\2\u00b5\u00b9"+
		"\t\4\2\2\u00b6\u00b8\5\63\32\2\u00b7\u00b6\3\2\2\2\u00b8\u00bb\3\2\2\2"+
		"\u00b9\u00b7\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00bc\3\2\2\2\u00bb\u00b9"+
		"\3\2\2\2\u00bc\u00bd\t\4\2\2\u00bd8\3\2\2\2\u00be\u00bf\t\5\2\2\u00bf"+
		":\3\2\2\2\u00c0\u00c1\t\6\2\2\u00c1<\3\2\2\2\u00c2\u00c3\7\60\2\2\u00c3"+
		">\3\2\2\2\u00c4\u00c5\7.\2\2\u00c5@\3\2\2\2\u00c6\u00c9\t\7\2\2\u00c7"+
		"\u00c9\5=\37\2\u00c8\u00c6\3\2\2\2\u00c8\u00c7\3\2\2\2\u00c9B\3\2\2\2"+
		"\u00ca\u00cc\t\b\2\2\u00cb\u00ca\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00cb"+
		"\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ceD\3\2\2\2\r\2\u0082\u0087\u0089\u0094"+
		"\u009d\u00aa\u00b0\u00b9\u00c8\u00cd\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}