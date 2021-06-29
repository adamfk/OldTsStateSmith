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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, Trigger_list=8, 
		Triggers=9, ORDER=10, LINE_COMMENT=11, ML_COMMENT=12, CODE_IDENTIFIER=13, 
		CHAR_LITERAL=14, STRING=15, CODE_SYMBOLS=16, LINE_ENDER=17, WS=18, WORD=19;
	public static final int
		RULE_state_defn = 0, RULE_state_name = 1, RULE_behaviors = 2, RULE_behavior = 3, 
		RULE_guard = 4, RULE_action = 5, RULE_braced_action = 6, RULE_naked_action = 7, 
		RULE_group_expression = 8, RULE_square_brace_expression = 9, RULE_braced_expression = 10, 
		RULE_code_elements = 11, RULE_code_element = 12;
	private static String[] makeRuleNames() {
		return new String[] {
			"state_defn", "state_name", "behaviors", "behavior", "guard", "action", 
			"braced_action", "naked_action", "group_expression", "square_brace_expression", 
			"braced_expression", "code_elements", "code_element"
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

	public static class State_defnContext extends ParserRuleContext {
		public State_nameContext state_name() {
			return getRuleContext(State_nameContext.class,0);
		}
		public TerminalNode EOF() { return getToken(Grammar1Parser.EOF, 0); }
		public BehaviorsContext behaviors() {
			return getRuleContext(BehaviorsContext.class,0);
		}
		public State_defnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_state_defn; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterState_defn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitState_defn(this);
		}
	}

	public final State_defnContext state_defn() throws RecognitionException {
		State_defnContext _localctx = new State_defnContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_state_defn);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			state_name();
			setState(28);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LINE_ENDER) {
				{
				setState(27);
				behaviors();
				}
			}

			setState(30);
			match(EOF);
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

	public static class State_nameContext extends ParserRuleContext {
		public TerminalNode WORD() { return getToken(Grammar1Parser.WORD, 0); }
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
		enterRule(_localctx, 2, RULE_state_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			match(WORD);
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

	public static class BehaviorsContext extends ParserRuleContext {
		public List<TerminalNode> LINE_ENDER() { return getTokens(Grammar1Parser.LINE_ENDER); }
		public TerminalNode LINE_ENDER(int i) {
			return getToken(Grammar1Parser.LINE_ENDER, i);
		}
		public List<BehaviorContext> behavior() {
			return getRuleContexts(BehaviorContext.class);
		}
		public BehaviorContext behavior(int i) {
			return getRuleContext(BehaviorContext.class,i);
		}
		public BehaviorsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_behaviors; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterBehaviors(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitBehaviors(this);
		}
	}

	public final BehaviorsContext behaviors() throws RecognitionException {
		BehaviorsContext _localctx = new BehaviorsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_behaviors);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			match(LINE_ENDER);
			setState(35);
			behavior();
			setState(40);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LINE_ENDER) {
				{
				{
				setState(36);
				match(LINE_ENDER);
				setState(37);
				behavior();
				}
				}
				setState(42);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class BehaviorContext extends ParserRuleContext {
		public TerminalNode LINE_ENDER() { return getToken(Grammar1Parser.LINE_ENDER, 0); }
		public TerminalNode ORDER() { return getToken(Grammar1Parser.ORDER, 0); }
		public TerminalNode Triggers() { return getToken(Grammar1Parser.Triggers, 0); }
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
		enterRule(_localctx, 6, RULE_behavior);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ORDER) {
				{
				setState(43);
				match(ORDER);
				}
			}

			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Triggers) {
				{
				setState(46);
				match(Triggers);
				}
			}

			setState(50);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(49);
				guard();
				}
			}

			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(52);
				action();
				}
			}

			setState(55);
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

	public static class GuardContext extends ParserRuleContext {
		public Code_elementsContext code_elements() {
			return getRuleContext(Code_elementsContext.class,0);
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
		enterRule(_localctx, 8, RULE_guard);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			match(T__0);
			setState(58);
			code_elements();
			setState(59);
			match(T__1);
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
		enterRule(_localctx, 10, RULE_action);
		try {
			setState(63);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(61);
				braced_action();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(62);
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
		public List<Code_elementsContext> code_elements() {
			return getRuleContexts(Code_elementsContext.class);
		}
		public Code_elementsContext code_elements(int i) {
			return getRuleContext(Code_elementsContext.class,i);
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
		enterRule(_localctx, 12, RULE_braced_action);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			match(T__2);
			setState(66);
			match(T__3);
			setState(70);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__3) | (1L << T__5) | (1L << LINE_COMMENT) | (1L << ML_COMMENT) | (1L << CODE_IDENTIFIER) | (1L << CHAR_LITERAL) | (1L << STRING) | (1L << CODE_SYMBOLS))) != 0)) {
				{
				{
				setState(67);
				code_elements();
				}
				}
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(73);
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

	public static class Naked_actionContext extends ParserRuleContext {
		public List<Code_elementsContext> code_elements() {
			return getRuleContexts(Code_elementsContext.class);
		}
		public Code_elementsContext code_elements(int i) {
			return getRuleContext(Code_elementsContext.class,i);
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
		enterRule(_localctx, 14, RULE_naked_action);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(T__2);
			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__3) | (1L << T__5) | (1L << LINE_COMMENT) | (1L << ML_COMMENT) | (1L << CODE_IDENTIFIER) | (1L << CHAR_LITERAL) | (1L << STRING) | (1L << CODE_SYMBOLS))) != 0)) {
				{
				{
				setState(76);
				code_elements();
				}
				}
				setState(81);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class Group_expressionContext extends ParserRuleContext {
		public List<Code_elementsContext> code_elements() {
			return getRuleContexts(Code_elementsContext.class);
		}
		public Code_elementsContext code_elements(int i) {
			return getRuleContext(Code_elementsContext.class,i);
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
		enterRule(_localctx, 16, RULE_group_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(T__5);
			setState(86);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__3) | (1L << T__5) | (1L << LINE_COMMENT) | (1L << ML_COMMENT) | (1L << CODE_IDENTIFIER) | (1L << CHAR_LITERAL) | (1L << STRING) | (1L << CODE_SYMBOLS))) != 0)) {
				{
				{
				setState(83);
				code_elements();
				}
				}
				setState(88);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(89);
			match(T__6);
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
		public List<Code_elementsContext> code_elements() {
			return getRuleContexts(Code_elementsContext.class);
		}
		public Code_elementsContext code_elements(int i) {
			return getRuleContext(Code_elementsContext.class,i);
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
		enterRule(_localctx, 18, RULE_square_brace_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			match(T__0);
			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__3) | (1L << T__5) | (1L << LINE_COMMENT) | (1L << ML_COMMENT) | (1L << CODE_IDENTIFIER) | (1L << CHAR_LITERAL) | (1L << STRING) | (1L << CODE_SYMBOLS))) != 0)) {
				{
				{
				setState(92);
				code_elements();
				}
				}
				setState(97);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(98);
			match(T__1);
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
		public List<Code_elementsContext> code_elements() {
			return getRuleContexts(Code_elementsContext.class);
		}
		public Code_elementsContext code_elements(int i) {
			return getRuleContext(Code_elementsContext.class,i);
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
		enterRule(_localctx, 20, RULE_braced_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			match(T__3);
			setState(104);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__3) | (1L << T__5) | (1L << LINE_COMMENT) | (1L << ML_COMMENT) | (1L << CODE_IDENTIFIER) | (1L << CHAR_LITERAL) | (1L << STRING) | (1L << CODE_SYMBOLS))) != 0)) {
				{
				{
				setState(101);
				code_elements();
				}
				}
				setState(106);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(107);
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

	public static class Code_elementsContext extends ParserRuleContext {
		public List<Code_elementContext> code_element() {
			return getRuleContexts(Code_elementContext.class);
		}
		public Code_elementContext code_element(int i) {
			return getRuleContext(Code_elementContext.class,i);
		}
		public Code_elementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_code_elements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterCode_elements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitCode_elements(this);
		}
	}

	public final Code_elementsContext code_elements() throws RecognitionException {
		Code_elementsContext _localctx = new Code_elementsContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_code_elements);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(110); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(109);
					code_element();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(112); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class Code_elementContext extends ParserRuleContext {
		public TerminalNode LINE_COMMENT() { return getToken(Grammar1Parser.LINE_COMMENT, 0); }
		public TerminalNode ML_COMMENT() { return getToken(Grammar1Parser.ML_COMMENT, 0); }
		public TerminalNode CHAR_LITERAL() { return getToken(Grammar1Parser.CHAR_LITERAL, 0); }
		public TerminalNode STRING() { return getToken(Grammar1Parser.STRING, 0); }
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
		public Code_elementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_code_element; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterCode_element(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitCode_element(this);
		}
	}

	public final Code_elementContext code_element() throws RecognitionException {
		Code_elementContext _localctx = new Code_elementContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_code_element);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LINE_COMMENT:
				{
				setState(114);
				match(LINE_COMMENT);
				}
				break;
			case ML_COMMENT:
				{
				setState(115);
				match(ML_COMMENT);
				}
				break;
			case CHAR_LITERAL:
				{
				setState(116);
				match(CHAR_LITERAL);
				}
				break;
			case STRING:
				{
				setState(117);
				match(STRING);
				}
				break;
			case CODE_IDENTIFIER:
				{
				setState(118);
				match(CODE_IDENTIFIER);
				}
				break;
			case CODE_SYMBOLS:
				{
				setState(119);
				match(CODE_SYMBOLS);
				}
				break;
			case T__5:
				{
				setState(120);
				group_expression();
				}
				break;
			case T__0:
				{
				setState(121);
				square_brace_expression();
				}
				break;
			case T__3:
				{
				setState(122);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\25\u0080\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\5\2\37\n\2\3\2\3\2\3\3\3\3\3\4"+
		"\3\4\3\4\3\4\7\4)\n\4\f\4\16\4,\13\4\3\5\5\5/\n\5\3\5\5\5\62\n\5\3\5\5"+
		"\5\65\n\5\3\5\5\58\n\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\5\7B\n\7\3\b\3"+
		"\b\3\b\7\bG\n\b\f\b\16\bJ\13\b\3\b\3\b\3\t\3\t\7\tP\n\t\f\t\16\tS\13\t"+
		"\3\n\3\n\7\nW\n\n\f\n\16\nZ\13\n\3\n\3\n\3\13\3\13\7\13`\n\13\f\13\16"+
		"\13c\13\13\3\13\3\13\3\f\3\f\7\fi\n\f\f\f\16\fl\13\f\3\f\3\f\3\r\6\rq"+
		"\n\r\r\r\16\rr\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16~\n\16"+
		"\3\16\2\2\17\2\4\6\b\n\f\16\20\22\24\26\30\32\2\2\2\u0087\2\34\3\2\2\2"+
		"\4\"\3\2\2\2\6$\3\2\2\2\b.\3\2\2\2\n;\3\2\2\2\fA\3\2\2\2\16C\3\2\2\2\20"+
		"M\3\2\2\2\22T\3\2\2\2\24]\3\2\2\2\26f\3\2\2\2\30p\3\2\2\2\32}\3\2\2\2"+
		"\34\36\5\4\3\2\35\37\5\6\4\2\36\35\3\2\2\2\36\37\3\2\2\2\37 \3\2\2\2 "+
		"!\7\2\2\3!\3\3\2\2\2\"#\7\25\2\2#\5\3\2\2\2$%\7\23\2\2%*\5\b\5\2&\'\7"+
		"\23\2\2\')\5\b\5\2(&\3\2\2\2),\3\2\2\2*(\3\2\2\2*+\3\2\2\2+\7\3\2\2\2"+
		",*\3\2\2\2-/\7\f\2\2.-\3\2\2\2./\3\2\2\2/\61\3\2\2\2\60\62\7\13\2\2\61"+
		"\60\3\2\2\2\61\62\3\2\2\2\62\64\3\2\2\2\63\65\5\n\6\2\64\63\3\2\2\2\64"+
		"\65\3\2\2\2\65\67\3\2\2\2\668\5\f\7\2\67\66\3\2\2\2\678\3\2\2\289\3\2"+
		"\2\29:\7\23\2\2:\t\3\2\2\2;<\7\3\2\2<=\5\30\r\2=>\7\4\2\2>\13\3\2\2\2"+
		"?B\5\16\b\2@B\5\20\t\2A?\3\2\2\2A@\3\2\2\2B\r\3\2\2\2CD\7\5\2\2DH\7\6"+
		"\2\2EG\5\30\r\2FE\3\2\2\2GJ\3\2\2\2HF\3\2\2\2HI\3\2\2\2IK\3\2\2\2JH\3"+
		"\2\2\2KL\7\7\2\2L\17\3\2\2\2MQ\7\5\2\2NP\5\30\r\2ON\3\2\2\2PS\3\2\2\2"+
		"QO\3\2\2\2QR\3\2\2\2R\21\3\2\2\2SQ\3\2\2\2TX\7\b\2\2UW\5\30\r\2VU\3\2"+
		"\2\2WZ\3\2\2\2XV\3\2\2\2XY\3\2\2\2Y[\3\2\2\2ZX\3\2\2\2[\\\7\t\2\2\\\23"+
		"\3\2\2\2]a\7\3\2\2^`\5\30\r\2_^\3\2\2\2`c\3\2\2\2a_\3\2\2\2ab\3\2\2\2"+
		"bd\3\2\2\2ca\3\2\2\2de\7\4\2\2e\25\3\2\2\2fj\7\6\2\2gi\5\30\r\2hg\3\2"+
		"\2\2il\3\2\2\2jh\3\2\2\2jk\3\2\2\2km\3\2\2\2lj\3\2\2\2mn\7\7\2\2n\27\3"+
		"\2\2\2oq\5\32\16\2po\3\2\2\2qr\3\2\2\2rp\3\2\2\2rs\3\2\2\2s\31\3\2\2\2"+
		"t~\7\r\2\2u~\7\16\2\2v~\7\20\2\2w~\7\21\2\2x~\7\17\2\2y~\7\22\2\2z~\5"+
		"\22\n\2{~\5\24\13\2|~\5\26\f\2}t\3\2\2\2}u\3\2\2\2}v\3\2\2\2}w\3\2\2\2"+
		"}x\3\2\2\2}y\3\2\2\2}z\3\2\2\2}{\3\2\2\2}|\3\2\2\2~\33\3\2\2\2\20\36*"+
		".\61\64\67AHQXajr}";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}