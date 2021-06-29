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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, Trigger_list=8, 
		Triggers=9, ORDER=10, LINE_COMMENT=11, ML_COMMENT=12, CODE_IDENTIFIER=13, 
		CHAR_LITERAL=14, STRING=15, CODE_SYMBOLS=16, LINE_ENDER=17, WS=18, WORD=19;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "TRIGGER_SIMPLE", 
			"Trigger_list", "Triggers", "ORDER", "NOT_NL_CR", "LINE_COMMENT", "ML_COMMENT", 
			"CODE_IDENTIFIER", "ESCAPED_CHAR", "CHAR_LITERAL", "NON_QUOTE_CHAR", 
			"STRING_CHAR", "STRING", "CODE_SYMBOLS", "LINE_ENDER", "WS", "WORD"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'['", "']'", "'/'", "'{'", "'}'", "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, "Trigger_list", "Triggers", 
			"ORDER", "LINE_COMMENT", "ML_COMMENT", "CODE_IDENTIFIER", "CHAR_LITERAL", 
			"STRING", "CODE_SYMBOLS", "LINE_ENDER", "WS", "WORD"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\25\u00a8\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\6\t"+
		"C\n\t\r\t\16\tD\3\n\3\n\3\n\3\n\7\nK\n\n\f\n\16\nN\13\n\5\nP\n\n\3\n\3"+
		"\n\3\13\3\13\5\13V\n\13\3\f\6\fY\n\f\r\f\16\fZ\3\f\3\f\3\r\3\r\3\16\3"+
		"\16\3\16\3\16\7\16e\n\16\f\16\16\16h\13\16\3\16\3\16\3\17\3\17\3\17\3"+
		"\17\7\17p\n\17\f\17\16\17s\13\17\3\17\3\17\3\17\3\20\6\20y\n\20\r\20\16"+
		"\20z\3\21\3\21\3\21\3\22\3\22\3\22\5\22\u0083\n\22\3\22\3\22\3\23\3\23"+
		"\3\24\3\24\5\24\u008b\n\24\3\25\3\25\7\25\u008f\n\25\f\25\16\25\u0092"+
		"\13\25\3\25\3\25\3\26\3\26\3\27\6\27\u0099\n\27\r\27\16\27\u009a\3\30"+
		"\6\30\u009e\n\30\r\30\16\30\u009f\3\30\3\30\3\31\6\31\u00a5\n\31\r\31"+
		"\16\31\u00a6\3q\2\32\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\2\23\n\25\13\27"+
		"\f\31\2\33\r\35\16\37\17!\2#\20%\2\'\2)\21+\22-\23/\24\61\25\3\2\n\6\2"+
		"\62;C\\aac|\3\2\62;\4\2\f\f\17\17\7\2&&\62;C\\aac|\3\2))\3\2$$\t\2##\'"+
		"(,\61<A``~~\u0080\u0080\5\2\13\f\17\17\"\"\2\u00b0\2\3\3\2\2\2\2\5\3\2"+
		"\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\23"+
		"\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\2#\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2"+
		"\2\3\63\3\2\2\2\5\65\3\2\2\2\7\67\3\2\2\2\t9\3\2\2\2\13;\3\2\2\2\r=\3"+
		"\2\2\2\17?\3\2\2\2\21B\3\2\2\2\23F\3\2\2\2\25U\3\2\2\2\27X\3\2\2\2\31"+
		"^\3\2\2\2\33`\3\2\2\2\35k\3\2\2\2\37x\3\2\2\2!|\3\2\2\2#\177\3\2\2\2%"+
		"\u0086\3\2\2\2\'\u008a\3\2\2\2)\u008c\3\2\2\2+\u0095\3\2\2\2-\u0098\3"+
		"\2\2\2/\u009d\3\2\2\2\61\u00a4\3\2\2\2\63\64\7]\2\2\64\4\3\2\2\2\65\66"+
		"\7_\2\2\66\6\3\2\2\2\678\7\61\2\28\b\3\2\2\29:\7}\2\2:\n\3\2\2\2;<\7\177"+
		"\2\2<\f\3\2\2\2=>\7*\2\2>\16\3\2\2\2?@\7+\2\2@\20\3\2\2\2AC\t\2\2\2BA"+
		"\3\2\2\2CD\3\2\2\2DB\3\2\2\2DE\3\2\2\2E\22\3\2\2\2FO\7*\2\2GP\5\21\t\2"+
		"HI\7.\2\2IK\5\21\t\2JH\3\2\2\2KN\3\2\2\2LJ\3\2\2\2LM\3\2\2\2MP\3\2\2\2"+
		"NL\3\2\2\2OG\3\2\2\2OL\3\2\2\2PQ\3\2\2\2QR\7+\2\2R\24\3\2\2\2SV\5\21\t"+
		"\2TV\5\23\n\2US\3\2\2\2UT\3\2\2\2V\26\3\2\2\2WY\t\3\2\2XW\3\2\2\2YZ\3"+
		"\2\2\2ZX\3\2\2\2Z[\3\2\2\2[\\\3\2\2\2\\]\7\60\2\2]\30\3\2\2\2^_\n\4\2"+
		"\2_\32\3\2\2\2`a\7\61\2\2ab\7\61\2\2bf\3\2\2\2ce\5\31\r\2dc\3\2\2\2eh"+
		"\3\2\2\2fd\3\2\2\2fg\3\2\2\2gi\3\2\2\2hf\3\2\2\2ij\5-\27\2j\34\3\2\2\2"+
		"kl\7\61\2\2lm\7,\2\2mq\3\2\2\2np\13\2\2\2on\3\2\2\2ps\3\2\2\2qr\3\2\2"+
		"\2qo\3\2\2\2rt\3\2\2\2sq\3\2\2\2tu\7,\2\2uv\7\61\2\2v\36\3\2\2\2wy\t\5"+
		"\2\2xw\3\2\2\2yz\3\2\2\2zx\3\2\2\2z{\3\2\2\2{ \3\2\2\2|}\7^\2\2}~\13\2"+
		"\2\2~\"\3\2\2\2\177\u0082\t\6\2\2\u0080\u0083\5!\21\2\u0081\u0083\n\6"+
		"\2\2\u0082\u0080\3\2\2\2\u0082\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084"+
		"\u0085\t\6\2\2\u0085$\3\2\2\2\u0086\u0087\n\7\2\2\u0087&\3\2\2\2\u0088"+
		"\u008b\5!\21\2\u0089\u008b\5%\23\2\u008a\u0088\3\2\2\2\u008a\u0089\3\2"+
		"\2\2\u008b(\3\2\2\2\u008c\u0090\7$\2\2\u008d\u008f\5\'\24\2\u008e\u008d"+
		"\3\2\2\2\u008f\u0092\3\2\2\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091"+
		"\u0093\3\2\2\2\u0092\u0090\3\2\2\2\u0093\u0094\7$\2\2\u0094*\3\2\2\2\u0095"+
		"\u0096\t\b\2\2\u0096,\3\2\2\2\u0097\u0099\t\4\2\2\u0098\u0097\3\2\2\2"+
		"\u0099\u009a\3\2\2\2\u009a\u0098\3\2\2\2\u009a\u009b\3\2\2\2\u009b.\3"+
		"\2\2\2\u009c\u009e\t\t\2\2\u009d\u009c\3\2\2\2\u009e\u009f\3\2\2\2\u009f"+
		"\u009d\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a2\b\30"+
		"\2\2\u00a2\60\3\2\2\2\u00a3\u00a5\t\2\2\2\u00a4\u00a3\3\2\2\2\u00a5\u00a6"+
		"\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\62\3\2\2\2\22\2"+
		"DLOUZfqxz\u0082\u008a\u0090\u009a\u009f\u00a6\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}