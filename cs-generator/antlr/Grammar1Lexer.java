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
		T__9=10, T__10=11, T__11=12, LINE_ENDER=13, IDENTIFIER=14, LINE_COMMENT=15, 
		STAR_COMMENT=16, STRING=17, TICK_STRING=18, DIGIT=19, PERIOD=20, COMMA=21, 
		CODE_SYMBOL=22, HWS=23;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "LINE_ENDER", "IDENTIFIER", "NOT_NL_CR", "LINE_COMMENT", 
			"STAR_COMMENT", "ESCAPED_CHAR", "NON_QUOTE_CHAR", "STRING_CHAR", "STRING", 
			"TICK_STRING", "IDENTIFIER_NON_DIGIT", "DIGIT", "PERIOD", "COMMA", "CODE_SYMBOL", 
			"HWS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'['", "']'", "'/'", "'::'", "'->'", "'{'", "'}'", 
			"'-'", "'+'", "'e'", null, null, null, null, null, null, null, "'.'", 
			"','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "LINE_ENDER", "IDENTIFIER", "LINE_COMMENT", "STAR_COMMENT", "STRING", 
			"TICK_STRING", "DIGIT", "PERIOD", "COMMA", "CODE_SYMBOL", "HWS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\31\u00a7\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\3\2\3\2\3\3\3\3\3\4\3\4"+
		"\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f"+
		"\3\f\3\r\3\r\3\16\6\16W\n\16\r\16\16\16X\3\17\3\17\3\17\7\17^\n\17\f\17"+
		"\16\17a\13\17\3\20\3\20\3\21\3\21\3\21\3\21\7\21i\n\21\f\21\16\21l\13"+
		"\21\3\21\3\21\3\22\3\22\3\22\3\22\7\22t\n\22\f\22\16\22w\13\22\3\22\3"+
		"\22\3\22\3\23\3\23\3\23\3\24\3\24\3\25\3\25\5\25\u0083\n\25\3\26\3\26"+
		"\7\26\u0087\n\26\f\26\16\26\u008a\13\26\3\26\3\26\3\27\3\27\7\27\u0090"+
		"\n\27\f\27\16\27\u0093\13\27\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3"+
		"\33\3\33\3\34\3\34\5\34\u00a1\n\34\3\35\6\35\u00a4\n\35\r\35\16\35\u00a5"+
		"\3u\2\36\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17"+
		"\35\20\37\2!\21#\22%\2\'\2)\2+\23-\24/\2\61\25\63\26\65\27\67\309\31\3"+
		"\2\t\4\2\f\f\17\17\3\2$$\3\2))\6\2&&C\\aac|\3\2\62;\13\2##\'(,-//\61\61"+
		"<A``~~\u0080\u0080\4\2\13\13\"\"\2\u00ab\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3"+
		"\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2"+
		"\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35"+
		"\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2\61\3\2\2\2\2\63"+
		"\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\3;\3\2\2\2\5=\3\2\2\2\7"+
		"?\3\2\2\2\tA\3\2\2\2\13C\3\2\2\2\rE\3\2\2\2\17H\3\2\2\2\21K\3\2\2\2\23"+
		"M\3\2\2\2\25O\3\2\2\2\27Q\3\2\2\2\31S\3\2\2\2\33V\3\2\2\2\35Z\3\2\2\2"+
		"\37b\3\2\2\2!d\3\2\2\2#o\3\2\2\2%{\3\2\2\2\'~\3\2\2\2)\u0082\3\2\2\2+"+
		"\u0084\3\2\2\2-\u008d\3\2\2\2/\u0096\3\2\2\2\61\u0098\3\2\2\2\63\u009a"+
		"\3\2\2\2\65\u009c\3\2\2\2\67\u00a0\3\2\2\29\u00a3\3\2\2\2;<\7*\2\2<\4"+
		"\3\2\2\2=>\7+\2\2>\6\3\2\2\2?@\7]\2\2@\b\3\2\2\2AB\7_\2\2B\n\3\2\2\2C"+
		"D\7\61\2\2D\f\3\2\2\2EF\7<\2\2FG\7<\2\2G\16\3\2\2\2HI\7/\2\2IJ\7@\2\2"+
		"J\20\3\2\2\2KL\7}\2\2L\22\3\2\2\2MN\7\177\2\2N\24\3\2\2\2OP\7/\2\2P\26"+
		"\3\2\2\2QR\7-\2\2R\30\3\2\2\2ST\7g\2\2T\32\3\2\2\2UW\t\2\2\2VU\3\2\2\2"+
		"WX\3\2\2\2XV\3\2\2\2XY\3\2\2\2Y\34\3\2\2\2Z_\5/\30\2[^\5/\30\2\\^\5\61"+
		"\31\2][\3\2\2\2]\\\3\2\2\2^a\3\2\2\2_]\3\2\2\2_`\3\2\2\2`\36\3\2\2\2a"+
		"_\3\2\2\2bc\n\2\2\2c \3\2\2\2de\7\61\2\2ef\7\61\2\2fj\3\2\2\2gi\5\37\20"+
		"\2hg\3\2\2\2il\3\2\2\2jh\3\2\2\2jk\3\2\2\2km\3\2\2\2lj\3\2\2\2mn\5\33"+
		"\16\2n\"\3\2\2\2op\7\61\2\2pq\7,\2\2qu\3\2\2\2rt\13\2\2\2sr\3\2\2\2tw"+
		"\3\2\2\2uv\3\2\2\2us\3\2\2\2vx\3\2\2\2wu\3\2\2\2xy\7,\2\2yz\7\61\2\2z"+
		"$\3\2\2\2{|\7^\2\2|}\13\2\2\2}&\3\2\2\2~\177\n\3\2\2\177(\3\2\2\2\u0080"+
		"\u0083\5%\23\2\u0081\u0083\5\'\24\2\u0082\u0080\3\2\2\2\u0082\u0081\3"+
		"\2\2\2\u0083*\3\2\2\2\u0084\u0088\7$\2\2\u0085\u0087\5)\25\2\u0086\u0085"+
		"\3\2\2\2\u0087\u008a\3\2\2\2\u0088\u0086\3\2\2\2\u0088\u0089\3\2\2\2\u0089"+
		"\u008b\3\2\2\2\u008a\u0088\3\2\2\2\u008b\u008c\7$\2\2\u008c,\3\2\2\2\u008d"+
		"\u0091\t\4\2\2\u008e\u0090\5)\25\2\u008f\u008e\3\2\2\2\u0090\u0093\3\2"+
		"\2\2\u0091\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0094\3\2\2\2\u0093"+
		"\u0091\3\2\2\2\u0094\u0095\t\4\2\2\u0095.\3\2\2\2\u0096\u0097\t\5\2\2"+
		"\u0097\60\3\2\2\2\u0098\u0099\t\6\2\2\u0099\62\3\2\2\2\u009a\u009b\7\60"+
		"\2\2\u009b\64\3\2\2\2\u009c\u009d\7.\2\2\u009d\66\3\2\2\2\u009e\u00a1"+
		"\t\7\2\2\u009f\u00a1\5\63\32\2\u00a0\u009e\3\2\2\2\u00a0\u009f\3\2\2\2"+
		"\u00a18\3\2\2\2\u00a2\u00a4\t\b\2\2\u00a3\u00a2\3\2\2\2\u00a4\u00a5\3"+
		"\2\2\2\u00a5\u00a3\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6:\3\2\2\2\r\2X]_j"+
		"u\u0082\u0088\u0091\u00a0\u00a5\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}