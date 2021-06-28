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
		WORD=10, LINE_ENDER=11, TRIGGER_SIMPLE=12, ORDER=13, LINE_COMMENT=14, 
		ML_COMMENT=15, CODE_IDENTIFIER=16, ESCAPED_CHAR=17, CHAR_LITERAL=18, STRING_CHAR=19, 
		CODE_SYMBOLS=20, WS=21;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"WORD", "LINE_ENDER", "TRIGGER_SIMPLE", "ORDER", "LINE_COMMENT", "ML_COMMENT", 
			"CODE_IDENTIFIER", "ESCAPED_CHAR", "CHAR_LITERAL", "STRING_CHAR", "CODE_SYMBOLS", 
			"WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "','", "')'", "'['", "']'", "'/'", "'{'", "'}'", "'\"'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, "WORD", "LINE_ENDER", 
			"TRIGGER_SIMPLE", "ORDER", "LINE_COMMENT", "ML_COMMENT", "CODE_IDENTIFIER", 
			"ESCAPED_CHAR", "CHAR_LITERAL", "STRING_CHAR", "CODE_SYMBOLS", "WS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\27\u008b\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\3\3\3\3\4\3\4"+
		"\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\6\13A\n\13\r\13"+
		"\16\13B\3\f\6\fF\n\f\r\f\16\fG\3\f\5\fK\n\f\3\r\6\rN\n\r\r\r\16\rO\3\16"+
		"\6\16S\n\16\r\16\16\16T\3\16\3\16\3\17\3\17\3\17\3\17\7\17]\n\17\f\17"+
		"\16\17`\13\17\3\17\3\17\3\20\3\20\3\20\3\20\7\20h\n\20\f\20\16\20k\13"+
		"\20\3\20\3\20\3\20\3\21\6\21q\n\21\r\21\16\21r\3\22\3\22\3\22\3\23\3\23"+
		"\3\23\5\23{\n\23\3\23\3\23\3\24\3\24\5\24\u0081\n\24\3\25\3\25\3\26\6"+
		"\26\u0086\n\26\r\26\16\26\u0087\3\26\3\26\3i\2\27\3\3\5\4\7\5\t\6\13\7"+
		"\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25"+
		")\26+\27\3\2\r\3\2c|\4\2\f\f\17\17\5\2C\\aac|\3\2\62;\5\2\f\f\17\17``"+
		"\7\2&&\62;C\\aac|\3\2))\4\2))``\4\2$$``\t\2##\'(,\61<A``~~\u0080\u0080"+
		"\5\2\13\f\17\17\"\"\2\u0095\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3"+
		"\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2"+
		"\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37"+
		"\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3"+
		"\2\2\2\3-\3\2\2\2\5/\3\2\2\2\7\61\3\2\2\2\t\63\3\2\2\2\13\65\3\2\2\2\r"+
		"\67\3\2\2\2\179\3\2\2\2\21;\3\2\2\2\23=\3\2\2\2\25@\3\2\2\2\27J\3\2\2"+
		"\2\31M\3\2\2\2\33R\3\2\2\2\35X\3\2\2\2\37c\3\2\2\2!p\3\2\2\2#t\3\2\2\2"+
		"%w\3\2\2\2\'\u0080\3\2\2\2)\u0082\3\2\2\2+\u0085\3\2\2\2-.\7*\2\2.\4\3"+
		"\2\2\2/\60\7.\2\2\60\6\3\2\2\2\61\62\7+\2\2\62\b\3\2\2\2\63\64\7]\2\2"+
		"\64\n\3\2\2\2\65\66\7_\2\2\66\f\3\2\2\2\678\7\61\2\28\16\3\2\2\29:\7}"+
		"\2\2:\20\3\2\2\2;<\7\177\2\2<\22\3\2\2\2=>\7$\2\2>\24\3\2\2\2?A\t\2\2"+
		"\2@?\3\2\2\2AB\3\2\2\2B@\3\2\2\2BC\3\2\2\2C\26\3\2\2\2DF\t\3\2\2ED\3\2"+
		"\2\2FG\3\2\2\2GE\3\2\2\2GH\3\2\2\2HK\3\2\2\2IK\7\2\2\3JE\3\2\2\2JI\3\2"+
		"\2\2K\30\3\2\2\2LN\t\4\2\2ML\3\2\2\2NO\3\2\2\2OM\3\2\2\2OP\3\2\2\2P\32"+
		"\3\2\2\2QS\t\5\2\2RQ\3\2\2\2ST\3\2\2\2TR\3\2\2\2TU\3\2\2\2UV\3\2\2\2V"+
		"W\7\60\2\2W\34\3\2\2\2XY\7\61\2\2YZ\7\61\2\2Z^\3\2\2\2[]\t\6\2\2\\[\3"+
		"\2\2\2]`\3\2\2\2^\\\3\2\2\2^_\3\2\2\2_a\3\2\2\2`^\3\2\2\2ab\5\27\f\2b"+
		"\36\3\2\2\2cd\7\61\2\2de\7,\2\2ei\3\2\2\2fh\13\2\2\2gf\3\2\2\2hk\3\2\2"+
		"\2ij\3\2\2\2ig\3\2\2\2jl\3\2\2\2ki\3\2\2\2lm\7,\2\2mn\7\61\2\2n \3\2\2"+
		"\2oq\t\7\2\2po\3\2\2\2qr\3\2\2\2rp\3\2\2\2rs\3\2\2\2s\"\3\2\2\2tu\7^\2"+
		"\2uv\13\2\2\2v$\3\2\2\2wz\t\b\2\2x{\5#\22\2y{\t\t\2\2zx\3\2\2\2zy\3\2"+
		"\2\2{|\3\2\2\2|}\t\b\2\2}&\3\2\2\2~\u0081\5#\22\2\177\u0081\t\n\2\2\u0080"+
		"~\3\2\2\2\u0080\177\3\2\2\2\u0081(\3\2\2\2\u0082\u0083\t\13\2\2\u0083"+
		"*\3\2\2\2\u0084\u0086\t\f\2\2\u0085\u0084\3\2\2\2\u0086\u0087\3\2\2\2"+
		"\u0087\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008a"+
		"\b\26\2\2\u008a,\3\2\2\2\17\2BGJOT^iprz\u0080\u0087\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}