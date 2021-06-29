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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, LINE_ENDER=9, 
		WS=10, IDENTIFIER=11, DIGIT=12, TRIGGER_LIST=13, LINE_COMMENT=14, ML_COMMENT=15, 
		CHAR_LITERAL=16, STRING=17, CODE_SYMBOLS=18;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "LINE_ENDER", 
			"WS", "IDENTIFIER", "IDENTIFIER_NON_DIGIT", "DIGIT", "TRIGGER_LIST", 
			"NOT_NL_CR", "LINE_COMMENT", "ML_COMMENT", "ESCAPED_CHAR", "CHAR_LITERAL", 
			"NON_QUOTE_CHAR", "STRING_CHAR", "STRING", "CODE_SYMBOLS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'.'", "'['", "']'", "'/'", "'{'", "'}'", "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, "LINE_ENDER", "WS", 
			"IDENTIFIER", "DIGIT", "TRIGGER_LIST", "LINE_COMMENT", "ML_COMMENT", 
			"CHAR_LITERAL", "STRING", "CODE_SYMBOLS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\24\u009a\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2"+
		"\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\6\nC"+
		"\n\n\r\n\16\nD\3\13\6\13H\n\13\r\13\16\13I\3\13\3\13\3\f\3\f\3\f\7\fQ"+
		"\n\f\f\f\16\fT\13\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\17\7\17^\n\17\f"+
		"\17\16\17a\13\17\5\17c\n\17\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\21\7"+
		"\21m\n\21\f\21\16\21p\13\21\3\21\3\21\3\22\3\22\3\22\3\22\7\22x\n\22\f"+
		"\22\16\22{\13\22\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24\5\24\u0086"+
		"\n\24\3\24\3\24\3\25\3\25\3\26\3\26\5\26\u008e\n\26\3\27\3\27\7\27\u0092"+
		"\n\27\f\27\16\27\u0095\13\27\3\27\3\27\3\30\3\30\3y\2\31\3\3\5\4\7\5\t"+
		"\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\2\33\16\35\17\37\2!\20#\21%\2"+
		"\'\22)\2+\2-\23/\24\3\2\t\4\2\f\f\17\17\5\2\13\f\17\17\"\"\6\2&&C\\aa"+
		"c|\3\2\62;\3\2))\3\2$$\t\2##\'(,\61<A``~~\u0080\u0080\2\u009f\2\3\3\2"+
		"\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17"+
		"\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2\'\3\2\2\2\2-\3\2\2\2\2/\3\2"+
		"\2\2\3\61\3\2\2\2\5\63\3\2\2\2\7\65\3\2\2\2\t\67\3\2\2\2\139\3\2\2\2\r"+
		";\3\2\2\2\17=\3\2\2\2\21?\3\2\2\2\23B\3\2\2\2\25G\3\2\2\2\27M\3\2\2\2"+
		"\31U\3\2\2\2\33W\3\2\2\2\35Y\3\2\2\2\37f\3\2\2\2!h\3\2\2\2#s\3\2\2\2%"+
		"\177\3\2\2\2\'\u0082\3\2\2\2)\u0089\3\2\2\2+\u008d\3\2\2\2-\u008f\3\2"+
		"\2\2/\u0098\3\2\2\2\61\62\7\60\2\2\62\4\3\2\2\2\63\64\7]\2\2\64\6\3\2"+
		"\2\2\65\66\7_\2\2\66\b\3\2\2\2\678\7\61\2\28\n\3\2\2\29:\7}\2\2:\f\3\2"+
		"\2\2;<\7\177\2\2<\16\3\2\2\2=>\7*\2\2>\20\3\2\2\2?@\7+\2\2@\22\3\2\2\2"+
		"AC\t\2\2\2BA\3\2\2\2CD\3\2\2\2DB\3\2\2\2DE\3\2\2\2E\24\3\2\2\2FH\t\3\2"+
		"\2GF\3\2\2\2HI\3\2\2\2IG\3\2\2\2IJ\3\2\2\2JK\3\2\2\2KL\b\13\2\2L\26\3"+
		"\2\2\2MR\5\31\r\2NQ\5\31\r\2OQ\5\33\16\2PN\3\2\2\2PO\3\2\2\2QT\3\2\2\2"+
		"RP\3\2\2\2RS\3\2\2\2S\30\3\2\2\2TR\3\2\2\2UV\t\4\2\2V\32\3\2\2\2WX\t\5"+
		"\2\2X\34\3\2\2\2Yb\7*\2\2Zc\5\27\f\2[\\\7.\2\2\\^\5\27\f\2][\3\2\2\2^"+
		"a\3\2\2\2_]\3\2\2\2_`\3\2\2\2`c\3\2\2\2a_\3\2\2\2bZ\3\2\2\2b_\3\2\2\2"+
		"cd\3\2\2\2de\7+\2\2e\36\3\2\2\2fg\n\2\2\2g \3\2\2\2hi\7\61\2\2ij\7\61"+
		"\2\2jn\3\2\2\2km\5\37\20\2lk\3\2\2\2mp\3\2\2\2nl\3\2\2\2no\3\2\2\2oq\3"+
		"\2\2\2pn\3\2\2\2qr\5\23\n\2r\"\3\2\2\2st\7\61\2\2tu\7,\2\2uy\3\2\2\2v"+
		"x\13\2\2\2wv\3\2\2\2x{\3\2\2\2yz\3\2\2\2yw\3\2\2\2z|\3\2\2\2{y\3\2\2\2"+
		"|}\7,\2\2}~\7\61\2\2~$\3\2\2\2\177\u0080\7^\2\2\u0080\u0081\13\2\2\2\u0081"+
		"&\3\2\2\2\u0082\u0085\t\6\2\2\u0083\u0086\5%\23\2\u0084\u0086\n\6\2\2"+
		"\u0085\u0083\3\2\2\2\u0085\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0088"+
		"\t\6\2\2\u0088(\3\2\2\2\u0089\u008a\n\7\2\2\u008a*\3\2\2\2\u008b\u008e"+
		"\5%\23\2\u008c\u008e\5)\25\2\u008d\u008b\3\2\2\2\u008d\u008c\3\2\2\2\u008e"+
		",\3\2\2\2\u008f\u0093\7$\2\2\u0090\u0092\5+\26\2\u0091\u0090\3\2\2\2\u0092"+
		"\u0095\3\2\2\2\u0093\u0091\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0096\3\2"+
		"\2\2\u0095\u0093\3\2\2\2\u0096\u0097\7$\2\2\u0097.\3\2\2\2\u0098\u0099"+
		"\t\b\2\2\u0099\60\3\2\2\2\16\2DIPR_bny\u0085\u008d\u0093\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}