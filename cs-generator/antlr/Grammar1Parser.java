// Generated from Grammar1.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class Grammar1Parser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		WORD=10, LINE_ENDER=11, TRIGGER_SIMPLE=12, ORDER=13, LINE_COMMENT=14, 
		ML_COMMENT=15, CODE_IDENTIFIER=16, ESCAPED_CHAR=17, CHAR_LITERAL=18, STRING_CHAR=19, 
		CODE_SYMBOLS=20, WS=21;
	public static final int
		RULE_state_name = 0, RULE_trigger_list = 1, RULE_triggers = 2, RULE_guard = 3, 
		RULE_action = 4, RULE_braced_action = 5, RULE_naked_action = 6, RULE_behavior = 7, 
		RULE_string = 8, RULE_group_expression = 9, RULE_square_brace_expression = 10, 
		RULE_braced_expression = 11, RULE_code_expression = 12;
	private static String[] makeRuleNames() {
		return new String[] {
			"state_name", "trigger_list", "triggers", "guard", "action", "braced_action", 
			"naked_action", "behavior", "string", "group_expression", "square_brace_expression", 
			"braced_expression", "code_expression"
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

	@Override
	public String getGrammarFileName() { return "Grammar1.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public Grammar1Parser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class State_nameContext extends ParserRuleContext {
		public TerminalNode WORD() { return getToken(Grammar1Parser.WORD, 0); }
		public TerminalNode LINE_ENDER() { return getToken(Grammar1Parser.LINE_ENDER, 0); }
		public State_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_state_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterState_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitState_name(this);
		}
	}

	public final State_nameContext state_name() throws RecognitionException {
		State_nameContext _localctx = new State_nameContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_state_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			match(WORD);
			setState(27);
			match(LINE_ENDER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Trigger_listContext extends ParserRuleContext {
		public List<TerminalNode> TRIGGER_SIMPLE() { return getTokens(Grammar1Parser.TRIGGER_SIMPLE); }
		public TerminalNode TRIGGER_SIMPLE(int i) {
			return getToken(Grammar1Parser.TRIGGER_SIMPLE, i);
		}
		public Trigger_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_trigger_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterTrigger_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitTrigger_list(this);
		}
	}

	public final Trigger_listContext trigger_list() throws RecognitionException {
		Trigger_listContext _localctx = new Trigger_listContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_trigger_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
			match(T__0);
			setState(38);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TRIGGER_SIMPLE:
				{
				setState(30);
				match(TRIGGER_SIMPLE);
				}
				break;
			case T__1:
			case T__2:
				{
				setState(35);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(31);
					match(T__1);
					setState(32);
					match(TRIGGER_SIMPLE);
					}
					}
					setState(37);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(40);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TriggersContext extends ParserRuleContext {
		public TerminalNode TRIGGER_SIMPLE() { return getToken(Grammar1Parser.TRIGGER_SIMPLE, 0); }
		public Trigger_listContext trigger_list() {
			return getRuleContext(Trigger_listContext.class,0);
		}
		public TriggersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_triggers; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterTriggers(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitTriggers(this);
		}
	}

	public final TriggersContext triggers() throws RecognitionException {
		TriggersContext _localctx = new TriggersContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_triggers);
		try {
			setState(44);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TRIGGER_SIMPLE:
				enterOuterAlt(_localctx, 1);
				{
				setState(42);
				match(TRIGGER_SIMPLE);
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 2);
				{
				setState(43);
				trigger_list();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GuardContext extends ParserRuleContext {
		public Code_expressionContext code_expression() {
			return getRuleContext(Code_expressionContext.class,0);
		}
		public GuardContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_guard; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterGuard(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitGuard(this);
		}
	}

	public final GuardContext guard() throws RecognitionException {
		GuardContext _localctx = new GuardContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_guard);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			match(T__3);
			setState(47);
			code_expression();
			setState(48);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ActionContext extends ParserRuleContext {
		public Braced_actionContext braced_action() {
			return getRuleContext(Braced_actionContext.class,0);
		}
		public Naked_actionContext naked_action() {
			return getRuleContext(Naked_actionContext.class,0);
		}
		public ActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_action; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitAction(this);
		}
	}

	public final ActionContext action() throws RecognitionException {
		ActionContext _localctx = new ActionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_action);
		try {
			setState(52);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(50);
				braced_action();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(51);
				naked_action();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Braced_actionContext extends ParserRuleContext {
		public Code_expressionContext code_expression() {
			return getRuleContext(Code_expressionContext.class,0);
		}
		public Braced_actionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_braced_action; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterBraced_action(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitBraced_action(this);
		}
	}

	public final Braced_actionContext braced_action() throws RecognitionException {
		Braced_actionContext _localctx = new Braced_actionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_braced_action);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			match(T__5);
			setState(55);
			match(T__6);
			setState(56);
			code_expression();
			setState(57);
			match(T__7);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Naked_actionContext extends ParserRuleContext {
		public Code_expressionContext code_expression() {
			return getRuleContext(Code_expressionContext.class,0);
		}
		public Naked_actionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_naked_action; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterNaked_action(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitNaked_action(this);
		}
	}

	public final Naked_actionContext naked_action() throws RecognitionException {
		Naked_actionContext _localctx = new Naked_actionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_naked_action);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			match(T__5);
			setState(60);
			code_expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BehaviorContext extends ParserRuleContext {
		public TerminalNode ORDER() { return getToken(Grammar1Parser.ORDER, 0); }
		public TriggersContext triggers() {
			return getRuleContext(TriggersContext.class,0);
		}
		public GuardContext guard() {
			return getRuleContext(GuardContext.class,0);
		}
		public ActionContext action() {
			return getRuleContext(ActionContext.class,0);
		}
		public BehaviorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_behavior; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterBehavior(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitBehavior(this);
		}
	}

	public final BehaviorContext behavior() throws RecognitionException {
		BehaviorContext _localctx = new BehaviorContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_behavior);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ORDER) {
				{
				setState(62);
				match(ORDER);
				}
			}

			setState(66);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0 || _la==TRIGGER_SIMPLE) {
				{
				setState(65);
				triggers();
				}
			}

			setState(69);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(68);
				guard();
				}
			}

			setState(72);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(71);
				action();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringContext extends ParserRuleContext {
		public List<TerminalNode> STRING_CHAR() { return getTokens(Grammar1Parser.STRING_CHAR); }
		public TerminalNode STRING_CHAR(int i) {
			return getToken(Grammar1Parser.STRING_CHAR, i);
		}
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitString(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_string);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			match(T__8);
			setState(78);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==STRING_CHAR) {
				{
				{
				setState(75);
				match(STRING_CHAR);
				}
				}
				setState(80);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(81);
			match(T__8);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Group_expressionContext extends ParserRuleContext {
		public List<Code_expressionContext> code_expression() {
			return getRuleContexts(Code_expressionContext.class);
		}
		public Code_expressionContext code_expression(int i) {
			return getRuleContext(Code_expressionContext.class,i);
		}
		public Group_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_group_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterGroup_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitGroup_expression(this);
		}
	}

	public final Group_expressionContext group_expression() throws RecognitionException {
		Group_expressionContext _localctx = new Group_expressionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_group_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			match(T__0);
			setState(87);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__3) | (1L << T__6) | (1L << T__8) | (1L << LINE_COMMENT) | (1L << ML_COMMENT) | (1L << CODE_IDENTIFIER) | (1L << CHAR_LITERAL) | (1L << CODE_SYMBOLS))) != 0)) {
				{
				{
				setState(84);
				code_expression();
				}
				}
				setState(89);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(90);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Square_brace_expressionContext extends ParserRuleContext {
		public List<Code_expressionContext> code_expression() {
			return getRuleContexts(Code_expressionContext.class);
		}
		public Code_expressionContext code_expression(int i) {
			return getRuleContext(Code_expressionContext.class,i);
		}
		public Square_brace_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_square_brace_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterSquare_brace_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitSquare_brace_expression(this);
		}
	}

	public final Square_brace_expressionContext square_brace_expression() throws RecognitionException {
		Square_brace_expressionContext _localctx = new Square_brace_expressionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_square_brace_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			match(T__3);
			setState(96);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__3) | (1L << T__6) | (1L << T__8) | (1L << LINE_COMMENT) | (1L << ML_COMMENT) | (1L << CODE_IDENTIFIER) | (1L << CHAR_LITERAL) | (1L << CODE_SYMBOLS))) != 0)) {
				{
				{
				setState(93);
				code_expression();
				}
				}
				setState(98);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(99);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Braced_expressionContext extends ParserRuleContext {
		public List<Code_expressionContext> code_expression() {
			return getRuleContexts(Code_expressionContext.class);
		}
		public Code_expressionContext code_expression(int i) {
			return getRuleContext(Code_expressionContext.class,i);
		}
		public Braced_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_braced_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterBraced_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitBraced_expression(this);
		}
	}

	public final Braced_expressionContext braced_expression() throws RecognitionException {
		Braced_expressionContext _localctx = new Braced_expressionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_braced_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
			match(T__6);
			setState(105);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__3) | (1L << T__6) | (1L << T__8) | (1L << LINE_COMMENT) | (1L << ML_COMMENT) | (1L << CODE_IDENTIFIER) | (1L << CHAR_LITERAL) | (1L << CODE_SYMBOLS))) != 0)) {
				{
				{
				setState(102);
				code_expression();
				}
				}
				setState(107);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(108);
			match(T__7);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Code_expressionContext extends ParserRuleContext {
		public TerminalNode LINE_COMMENT() { return getToken(Grammar1Parser.LINE_COMMENT, 0); }
		public TerminalNode ML_COMMENT() { return getToken(Grammar1Parser.ML_COMMENT, 0); }
		public TerminalNode CHAR_LITERAL() { return getToken(Grammar1Parser.CHAR_LITERAL, 0); }
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public TerminalNode CODE_IDENTIFIER() { return getToken(Grammar1Parser.CODE_IDENTIFIER, 0); }
		public TerminalNode CODE_SYMBOLS() { return getToken(Grammar1Parser.CODE_SYMBOLS, 0); }
		public Group_expressionContext group_expression() {
			return getRuleContext(Group_expressionContext.class,0);
		}
		public Square_brace_expressionContext square_brace_expression() {
			return getRuleContext(Square_brace_expressionContext.class,0);
		}
		public Braced_expressionContext braced_expression() {
			return getRuleContext(Braced_expressionContext.class,0);
		}
		public Code_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_code_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterCode_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitCode_expression(this);
		}
	}

	public final Code_expressionContext code_expression() throws RecognitionException {
		Code_expressionContext _localctx = new Code_expressionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_code_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LINE_COMMENT:
				{
				setState(110);
				match(LINE_COMMENT);
				}
				break;
			case ML_COMMENT:
				{
				setState(111);
				match(ML_COMMENT);
				}
				break;
			case CHAR_LITERAL:
				{
				setState(112);
				match(CHAR_LITERAL);
				}
				break;
			case T__8:
				{
				setState(113);
				string();
				}
				break;
			case CODE_IDENTIFIER:
				{
				setState(114);
				match(CODE_IDENTIFIER);
				}
				break;
			case CODE_SYMBOLS:
				{
				setState(115);
				match(CODE_SYMBOLS);
				}
				break;
			case T__0:
				{
				setState(116);
				group_expression();
				}
				break;
			case T__3:
				{
				setState(117);
				square_brace_expression();
				}
				break;
			case T__6:
				{
				setState(118);
				braced_expression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\27|\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\3\3\3\3\3\3\3\7\3$\n\3\f\3\16\3"+
		"\'\13\3\5\3)\n\3\3\3\3\3\3\4\3\4\5\4/\n\4\3\5\3\5\3\5\3\5\3\6\3\6\5\6"+
		"\67\n\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\5\tB\n\t\3\t\5\tE\n\t\3\t"+
		"\5\tH\n\t\3\t\5\tK\n\t\3\n\3\n\7\nO\n\n\f\n\16\nR\13\n\3\n\3\n\3\13\3"+
		"\13\7\13X\n\13\f\13\16\13[\13\13\3\13\3\13\3\f\3\f\7\fa\n\f\f\f\16\fd"+
		"\13\f\3\f\3\f\3\r\3\r\7\rj\n\r\f\r\16\rm\13\r\3\r\3\r\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\5\16z\n\16\3\16\2\2\17\2\4\6\b\n\f\16\20"+
		"\22\24\26\30\32\2\2\2\u0082\2\34\3\2\2\2\4\37\3\2\2\2\6.\3\2\2\2\b\60"+
		"\3\2\2\2\n\66\3\2\2\2\f8\3\2\2\2\16=\3\2\2\2\20A\3\2\2\2\22L\3\2\2\2\24"+
		"U\3\2\2\2\26^\3\2\2\2\30g\3\2\2\2\32y\3\2\2\2\34\35\7\f\2\2\35\36\7\r"+
		"\2\2\36\3\3\2\2\2\37(\7\3\2\2 )\7\16\2\2!\"\7\4\2\2\"$\7\16\2\2#!\3\2"+
		"\2\2$\'\3\2\2\2%#\3\2\2\2%&\3\2\2\2&)\3\2\2\2\'%\3\2\2\2( \3\2\2\2(%\3"+
		"\2\2\2)*\3\2\2\2*+\7\5\2\2+\5\3\2\2\2,/\7\16\2\2-/\5\4\3\2.,\3\2\2\2."+
		"-\3\2\2\2/\7\3\2\2\2\60\61\7\6\2\2\61\62\5\32\16\2\62\63\7\7\2\2\63\t"+
		"\3\2\2\2\64\67\5\f\7\2\65\67\5\16\b\2\66\64\3\2\2\2\66\65\3\2\2\2\67\13"+
		"\3\2\2\289\7\b\2\29:\7\t\2\2:;\5\32\16\2;<\7\n\2\2<\r\3\2\2\2=>\7\b\2"+
		"\2>?\5\32\16\2?\17\3\2\2\2@B\7\17\2\2A@\3\2\2\2AB\3\2\2\2BD\3\2\2\2CE"+
		"\5\6\4\2DC\3\2\2\2DE\3\2\2\2EG\3\2\2\2FH\5\b\5\2GF\3\2\2\2GH\3\2\2\2H"+
		"J\3\2\2\2IK\5\n\6\2JI\3\2\2\2JK\3\2\2\2K\21\3\2\2\2LP\7\13\2\2MO\7\25"+
		"\2\2NM\3\2\2\2OR\3\2\2\2PN\3\2\2\2PQ\3\2\2\2QS\3\2\2\2RP\3\2\2\2ST\7\13"+
		"\2\2T\23\3\2\2\2UY\7\3\2\2VX\5\32\16\2WV\3\2\2\2X[\3\2\2\2YW\3\2\2\2Y"+
		"Z\3\2\2\2Z\\\3\2\2\2[Y\3\2\2\2\\]\7\5\2\2]\25\3\2\2\2^b\7\6\2\2_a\5\32"+
		"\16\2`_\3\2\2\2ad\3\2\2\2b`\3\2\2\2bc\3\2\2\2ce\3\2\2\2db\3\2\2\2ef\7"+
		"\7\2\2f\27\3\2\2\2gk\7\t\2\2hj\5\32\16\2ih\3\2\2\2jm\3\2\2\2ki\3\2\2\2"+
		"kl\3\2\2\2ln\3\2\2\2mk\3\2\2\2no\7\n\2\2o\31\3\2\2\2pz\7\20\2\2qz\7\21"+
		"\2\2rz\7\24\2\2sz\5\22\n\2tz\7\22\2\2uz\7\26\2\2vz\5\24\13\2wz\5\26\f"+
		"\2xz\5\30\r\2yp\3\2\2\2yq\3\2\2\2yr\3\2\2\2ys\3\2\2\2yt\3\2\2\2yu\3\2"+
		"\2\2yv\3\2\2\2yw\3\2\2\2yx\3\2\2\2z\33\3\2\2\2\17%(.\66ADGJPYbky";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}