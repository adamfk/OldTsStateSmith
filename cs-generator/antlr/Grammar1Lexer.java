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
		LINE_ENDER=10, IDENTIFIER=11, DIGIT=12, LINE_COMMENT=13, ML_COMMENT=14, 
		CHAR_LITERAL=15, STRING=16, CODE_SYMBOLS=17, HWS=18;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"FOLLOWING_WS", "LINE_ENDER", "IDENTIFIER", "IDENTIFIER_NON_DIGIT", "DIGIT", 
			"NOT_NL_CR", "LINE_COMMENT", "ML_COMMENT", "ESCAPED_CHAR", "CHAR_LITERAL", 
			"NON_QUOTE_CHAR", "STRING_CHAR", "STRING", "CODE_SYMBOLS", "HWS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'.'", "'['", "']'", "'/'", "'('", "')'", "'{'", "'}'", "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, "LINE_ENDER", 
			"IDENTIFIER", "DIGIT", "LINE_COMMENT", "ML_COMMENT", "CHAR_LITERAL", 
			"STRING", "CODE_SYMBOLS", "HWS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\24\u0099\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t"+
		"\3\n\3\n\3\13\3\13\3\f\6\fI\n\f\r\f\16\fJ\3\f\7\fN\n\f\f\f\16\fQ\13\f"+
		"\3\r\3\r\3\r\7\rV\n\r\f\r\16\rY\13\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21"+
		"\3\21\3\21\3\21\7\21e\n\21\f\21\16\21h\13\21\3\21\3\21\3\22\3\22\3\22"+
		"\3\22\7\22p\n\22\f\22\16\22s\13\22\3\22\3\22\3\22\3\23\3\23\3\23\3\24"+
		"\3\24\3\24\5\24~\n\24\3\24\3\24\3\25\3\25\3\26\3\26\5\26\u0086\n\26\3"+
		"\27\3\27\7\27\u008a\n\27\f\27\16\27\u008d\13\27\3\27\3\27\3\30\3\30\3"+
		"\31\6\31\u0094\n\31\r\31\16\31\u0095\3\31\3\31\3q\2\32\3\3\5\4\7\5\t\6"+
		"\13\7\r\b\17\t\21\n\23\13\25\2\27\f\31\r\33\2\35\16\37\2!\17#\20%\2\'"+
		"\21)\2+\2-\22/\23\61\24\3\2\n\5\2\13\f\17\17\"\"\4\2\f\f\17\17\6\2&&C"+
		"\\aac|\3\2\62;\3\2))\3\2$$\t\2##\'(,\61<A``~~\u0080\u0080\4\2\13\13\""+
		"\"\2\u009c\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2"+
		"\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\27\3\2\2\2\2\31"+
		"\3\2\2\2\2\35\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2\'\3\2\2\2\2-\3\2\2\2\2/"+
		"\3\2\2\2\2\61\3\2\2\2\3\63\3\2\2\2\5\65\3\2\2\2\7\67\3\2\2\2\t9\3\2\2"+
		"\2\13;\3\2\2\2\r=\3\2\2\2\17?\3\2\2\2\21A\3\2\2\2\23C\3\2\2\2\25E\3\2"+
		"\2\2\27H\3\2\2\2\31R\3\2\2\2\33Z\3\2\2\2\35\\\3\2\2\2\37^\3\2\2\2!`\3"+
		"\2\2\2#k\3\2\2\2%w\3\2\2\2\'z\3\2\2\2)\u0081\3\2\2\2+\u0085\3\2\2\2-\u0087"+
		"\3\2\2\2/\u0090\3\2\2\2\61\u0093\3\2\2\2\63\64\7\60\2\2\64\4\3\2\2\2\65"+
		"\66\7]\2\2\66\6\3\2\2\2\678\7_\2\28\b\3\2\2\29:\7\61\2\2:\n\3\2\2\2;<"+
		"\7*\2\2<\f\3\2\2\2=>\7+\2\2>\16\3\2\2\2?@\7}\2\2@\20\3\2\2\2AB\7\177\2"+
		"\2B\22\3\2\2\2CD\7.\2\2D\24\3\2\2\2EF\t\2\2\2F\26\3\2\2\2GI\t\3\2\2HG"+
		"\3\2\2\2IJ\3\2\2\2JH\3\2\2\2JK\3\2\2\2KO\3\2\2\2LN\5\25\13\2ML\3\2\2\2"+
		"NQ\3\2\2\2OM\3\2\2\2OP\3\2\2\2P\30\3\2\2\2QO\3\2\2\2RW\5\33\16\2SV\5\33"+
		"\16\2TV\5\35\17\2US\3\2\2\2UT\3\2\2\2VY\3\2\2\2WU\3\2\2\2WX\3\2\2\2X\32"+
		"\3\2\2\2YW\3\2\2\2Z[\t\4\2\2[\34\3\2\2\2\\]\t\5\2\2]\36\3\2\2\2^_\n\3"+
		"\2\2_ \3\2\2\2`a\7\61\2\2ab\7\61\2\2bf\3\2\2\2ce\5\37\20\2dc\3\2\2\2e"+
		"h\3\2\2\2fd\3\2\2\2fg\3\2\2\2gi\3\2\2\2hf\3\2\2\2ij\5\27\f\2j\"\3\2\2"+
		"\2kl\7\61\2\2lm\7,\2\2mq\3\2\2\2np\13\2\2\2on\3\2\2\2ps\3\2\2\2qr\3\2"+
		"\2\2qo\3\2\2\2rt\3\2\2\2sq\3\2\2\2tu\7,\2\2uv\7\61\2\2v$\3\2\2\2wx\7^"+
		"\2\2xy\13\2\2\2y&\3\2\2\2z}\t\6\2\2{~\5%\23\2|~\n\6\2\2}{\3\2\2\2}|\3"+
		"\2\2\2~\177\3\2\2\2\177\u0080\t\6\2\2\u0080(\3\2\2\2\u0081\u0082\n\7\2"+
		"\2\u0082*\3\2\2\2\u0083\u0086\5%\23\2\u0084\u0086\5)\25\2\u0085\u0083"+
		"\3\2\2\2\u0085\u0084\3\2\2\2\u0086,\3\2\2\2\u0087\u008b\7$\2\2\u0088\u008a"+
		"\5+\26\2\u0089\u0088\3\2\2\2\u008a\u008d\3\2\2\2\u008b\u0089\3\2\2\2\u008b"+
		"\u008c\3\2\2\2\u008c\u008e\3\2\2\2\u008d\u008b\3\2\2\2\u008e\u008f\7$"+
		"\2\2\u008f.\3\2\2\2\u0090\u0091\t\b\2\2\u0091\60\3\2\2\2\u0092\u0094\t"+
		"\t\2\2\u0093\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0093\3\2\2\2\u0095"+
		"\u0096\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0098\b\31\2\2\u0098\62\3\2\2"+
		"\2\r\2JOUWfq}\u0085\u008b\u0095\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}