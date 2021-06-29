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
		LINE_ENDER=10, IDENTIFIER=11, DIGIT=12, LINE_COMMENT=13, ML_COMMENT=14, 
		CHAR_LITERAL=15, STRING=16, CODE_SYMBOLS=17, HWS=18;
	public static final int
		RULE_state_defn = 0, RULE_state_name = 1, RULE_behaviors = 2, RULE_behavior = 3, 
		RULE_triggers = 4, RULE_order = 5, RULE_guard = 6, RULE_action = 7, RULE_braced_action = 8, 
		RULE_naked_action = 9, RULE_group_expression = 10, RULE_square_brace_expression = 11, 
		RULE_braced_expression = 12, RULE_code_elements = 13, RULE_code_element = 14, 
		RULE_trigger_list = 15;
	private static String[] makeRuleNames() {
		return new String[] {
			"state_defn", "state_name", "behaviors", "behavior", "triggers", "order", 
			"guard", "action", "braced_action", "naked_action", "group_expression", 
			"square_brace_expression", "braced_expression", "code_elements", "code_element", 
			"trigger_list"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'.'", "'['", "']'", "'/'", "'{'", "'}'", "'('", "')'", "','"
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
			setState(32);
			state_name();
			setState(34);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LINE_ENDER) {
				{
				setState(33);
				behaviors();
				}
			}

			setState(36);
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
		public TerminalNode IDENTIFIER() { return getToken(Grammar1Parser.IDENTIFIER, 0); }
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
			setState(38);
			match(IDENTIFIER);
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
			setState(40);
			match(LINE_ENDER);
			setState(41);
			behavior();
			setState(46);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LINE_ENDER) {
				{
				{
				setState(42);
				match(LINE_ENDER);
				setState(43);
				behavior();
				}
				}
				setState(48);
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
		public OrderContext order() {
			return getRuleContext(OrderContext.class,0);
		}
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
		enterRule(_localctx, 6, RULE_behavior);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DIGIT) {
				{
				setState(49);
				order();
				}
			}

			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6 || _la==IDENTIFIER) {
				{
				setState(52);
				triggers();
				}
			}

			setState(56);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(55);
				guard();
				}
			}

			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(58);
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

	public static class TriggersContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(Grammar1Parser.IDENTIFIER, 0); }
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
		enterRule(_localctx, 8, RULE_triggers);
		try {
			setState(63);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(61);
				match(IDENTIFIER);
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 2);
				{
				setState(62);
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

	public static class OrderContext extends ParserRuleContext {
		public List<TerminalNode> DIGIT() { return getTokens(Grammar1Parser.DIGIT); }
		public TerminalNode DIGIT(int i) {
			return getToken(Grammar1Parser.DIGIT, i);
		}
		public OrderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_order; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterOrder(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitOrder(this);
		}
	}

	public final OrderContext order() throws RecognitionException {
		OrderContext _localctx = new OrderContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_order);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(65);
				match(DIGIT);
				}
				}
				setState(68); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DIGIT );
			setState(70);
			match(T__0);
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
		public List<Code_elementsContext> code_elements() {
			return getRuleContexts(Code_elementsContext.class);
		}
		public Code_elementsContext code_elements(int i) {
			return getRuleContext(Code_elementsContext.class,i);
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
		enterRule(_localctx, 12, RULE_guard);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(T__1);
			setState(76);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__4) | (1L << T__6) | (1L << IDENTIFIER) | (1L << LINE_COMMENT) | (1L << ML_COMMENT) | (1L << CHAR_LITERAL) | (1L << STRING) | (1L << CODE_SYMBOLS))) != 0)) {
				{
				{
				setState(73);
				code_elements();
				}
				}
				setState(78);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(79);
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
		enterRule(_localctx, 14, RULE_action);
		try {
			setState(83);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(81);
				braced_action();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(82);
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
		enterRule(_localctx, 16, RULE_braced_action);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			match(T__3);
			setState(86);
			match(T__4);
			setState(90);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__4) | (1L << T__6) | (1L << IDENTIFIER) | (1L << LINE_COMMENT) | (1L << ML_COMMENT) | (1L << CHAR_LITERAL) | (1L << STRING) | (1L << CODE_SYMBOLS))) != 0)) {
				{
				{
				setState(87);
				code_elements();
				}
				}
				setState(92);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(93);
			match(T__5);
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
		enterRule(_localctx, 18, RULE_naked_action);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			match(T__3);
			setState(99);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__4) | (1L << T__6) | (1L << IDENTIFIER) | (1L << LINE_COMMENT) | (1L << ML_COMMENT) | (1L << CHAR_LITERAL) | (1L << STRING) | (1L << CODE_SYMBOLS))) != 0)) {
				{
				{
				setState(96);
				code_elements();
				}
				}
				setState(101);
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
		enterRule(_localctx, 20, RULE_group_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			match(T__6);
			setState(106);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__4) | (1L << T__6) | (1L << IDENTIFIER) | (1L << LINE_COMMENT) | (1L << ML_COMMENT) | (1L << CHAR_LITERAL) | (1L << STRING) | (1L << CODE_SYMBOLS))) != 0)) {
				{
				{
				setState(103);
				code_elements();
				}
				}
				setState(108);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(109);
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
		enterRule(_localctx, 22, RULE_square_brace_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			match(T__1);
			setState(115);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__4) | (1L << T__6) | (1L << IDENTIFIER) | (1L << LINE_COMMENT) | (1L << ML_COMMENT) | (1L << CHAR_LITERAL) | (1L << STRING) | (1L << CODE_SYMBOLS))) != 0)) {
				{
				{
				setState(112);
				code_elements();
				}
				}
				setState(117);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(118);
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
		enterRule(_localctx, 24, RULE_braced_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			match(T__4);
			setState(124);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__4) | (1L << T__6) | (1L << IDENTIFIER) | (1L << LINE_COMMENT) | (1L << ML_COMMENT) | (1L << CHAR_LITERAL) | (1L << STRING) | (1L << CODE_SYMBOLS))) != 0)) {
				{
				{
				setState(121);
				code_elements();
				}
				}
				setState(126);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(127);
			match(T__5);
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
		enterRule(_localctx, 26, RULE_code_elements);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(130); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(129);
					code_element();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(132); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
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
		public TerminalNode IDENTIFIER() { return getToken(Grammar1Parser.IDENTIFIER, 0); }
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
		enterRule(_localctx, 28, RULE_code_element);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LINE_COMMENT:
				{
				setState(134);
				match(LINE_COMMENT);
				}
				break;
			case ML_COMMENT:
				{
				setState(135);
				match(ML_COMMENT);
				}
				break;
			case CHAR_LITERAL:
				{
				setState(136);
				match(CHAR_LITERAL);
				}
				break;
			case STRING:
				{
				setState(137);
				match(STRING);
				}
				break;
			case IDENTIFIER:
				{
				setState(138);
				match(IDENTIFIER);
				}
				break;
			case CODE_SYMBOLS:
				{
				setState(139);
				match(CODE_SYMBOLS);
				}
				break;
			case T__6:
				{
				setState(140);
				group_expression();
				}
				break;
			case T__1:
				{
				setState(141);
				square_brace_expression();
				}
				break;
			case T__4:
				{
				setState(142);
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

	public static class Trigger_listContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(Grammar1Parser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(Grammar1Parser.IDENTIFIER, i);
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
		enterRule(_localctx, 30, RULE_trigger_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			match(T__6);
			setState(154);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				{
				setState(146);
				match(IDENTIFIER);
				}
				break;
			case T__7:
			case T__8:
				{
				setState(151);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__8) {
					{
					{
					setState(147);
					match(T__8);
					setState(148);
					match(IDENTIFIER);
					}
					}
					setState(153);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(156);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\24\u00a1\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2"+
		"\5\2%\n\2\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\7\4/\n\4\f\4\16\4\62\13\4\3"+
		"\5\5\5\65\n\5\3\5\5\58\n\5\3\5\5\5;\n\5\3\5\5\5>\n\5\3\6\3\6\5\6B\n\6"+
		"\3\7\6\7E\n\7\r\7\16\7F\3\7\3\7\3\b\3\b\7\bM\n\b\f\b\16\bP\13\b\3\b\3"+
		"\b\3\t\3\t\5\tV\n\t\3\n\3\n\3\n\7\n[\n\n\f\n\16\n^\13\n\3\n\3\n\3\13\3"+
		"\13\7\13d\n\13\f\13\16\13g\13\13\3\f\3\f\7\fk\n\f\f\f\16\fn\13\f\3\f\3"+
		"\f\3\r\3\r\7\rt\n\r\f\r\16\rw\13\r\3\r\3\r\3\16\3\16\7\16}\n\16\f\16\16"+
		"\16\u0080\13\16\3\16\3\16\3\17\6\17\u0085\n\17\r\17\16\17\u0086\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u0092\n\20\3\21\3\21\3\21"+
		"\3\21\7\21\u0098\n\21\f\21\16\21\u009b\13\21\5\21\u009d\n\21\3\21\3\21"+
		"\3\21\2\2\22\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \2\2\2\u00aa\2\"\3"+
		"\2\2\2\4(\3\2\2\2\6*\3\2\2\2\b\64\3\2\2\2\nA\3\2\2\2\fD\3\2\2\2\16J\3"+
		"\2\2\2\20U\3\2\2\2\22W\3\2\2\2\24a\3\2\2\2\26h\3\2\2\2\30q\3\2\2\2\32"+
		"z\3\2\2\2\34\u0084\3\2\2\2\36\u0091\3\2\2\2 \u0093\3\2\2\2\"$\5\4\3\2"+
		"#%\5\6\4\2$#\3\2\2\2$%\3\2\2\2%&\3\2\2\2&\'\7\2\2\3\'\3\3\2\2\2()\7\r"+
		"\2\2)\5\3\2\2\2*+\7\f\2\2+\60\5\b\5\2,-\7\f\2\2-/\5\b\5\2.,\3\2\2\2/\62"+
		"\3\2\2\2\60.\3\2\2\2\60\61\3\2\2\2\61\7\3\2\2\2\62\60\3\2\2\2\63\65\5"+
		"\f\7\2\64\63\3\2\2\2\64\65\3\2\2\2\65\67\3\2\2\2\668\5\n\6\2\67\66\3\2"+
		"\2\2\678\3\2\2\28:\3\2\2\29;\5\16\b\2:9\3\2\2\2:;\3\2\2\2;=\3\2\2\2<>"+
		"\5\20\t\2=<\3\2\2\2=>\3\2\2\2>\t\3\2\2\2?B\7\r\2\2@B\5 \21\2A?\3\2\2\2"+
		"A@\3\2\2\2B\13\3\2\2\2CE\7\16\2\2DC\3\2\2\2EF\3\2\2\2FD\3\2\2\2FG\3\2"+
		"\2\2GH\3\2\2\2HI\7\3\2\2I\r\3\2\2\2JN\7\4\2\2KM\5\34\17\2LK\3\2\2\2MP"+
		"\3\2\2\2NL\3\2\2\2NO\3\2\2\2OQ\3\2\2\2PN\3\2\2\2QR\7\5\2\2R\17\3\2\2\2"+
		"SV\5\22\n\2TV\5\24\13\2US\3\2\2\2UT\3\2\2\2V\21\3\2\2\2WX\7\6\2\2X\\\7"+
		"\7\2\2Y[\5\34\17\2ZY\3\2\2\2[^\3\2\2\2\\Z\3\2\2\2\\]\3\2\2\2]_\3\2\2\2"+
		"^\\\3\2\2\2_`\7\b\2\2`\23\3\2\2\2ae\7\6\2\2bd\5\34\17\2cb\3\2\2\2dg\3"+
		"\2\2\2ec\3\2\2\2ef\3\2\2\2f\25\3\2\2\2ge\3\2\2\2hl\7\t\2\2ik\5\34\17\2"+
		"ji\3\2\2\2kn\3\2\2\2lj\3\2\2\2lm\3\2\2\2mo\3\2\2\2nl\3\2\2\2op\7\n\2\2"+
		"p\27\3\2\2\2qu\7\4\2\2rt\5\34\17\2sr\3\2\2\2tw\3\2\2\2us\3\2\2\2uv\3\2"+
		"\2\2vx\3\2\2\2wu\3\2\2\2xy\7\5\2\2y\31\3\2\2\2z~\7\7\2\2{}\5\34\17\2|"+
		"{\3\2\2\2}\u0080\3\2\2\2~|\3\2\2\2~\177\3\2\2\2\177\u0081\3\2\2\2\u0080"+
		"~\3\2\2\2\u0081\u0082\7\b\2\2\u0082\33\3\2\2\2\u0083\u0085\5\36\20\2\u0084"+
		"\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0084\3\2\2\2\u0086\u0087\3\2"+
		"\2\2\u0087\35\3\2\2\2\u0088\u0092\7\17\2\2\u0089\u0092\7\20\2\2\u008a"+
		"\u0092\7\21\2\2\u008b\u0092\7\22\2\2\u008c\u0092\7\r\2\2\u008d\u0092\7"+
		"\23\2\2\u008e\u0092\5\26\f\2\u008f\u0092\5\30\r\2\u0090\u0092\5\32\16"+
		"\2\u0091\u0088\3\2\2\2\u0091\u0089\3\2\2\2\u0091\u008a\3\2\2\2\u0091\u008b"+
		"\3\2\2\2\u0091\u008c\3\2\2\2\u0091\u008d\3\2\2\2\u0091\u008e\3\2\2\2\u0091"+
		"\u008f\3\2\2\2\u0091\u0090\3\2\2\2\u0092\37\3\2\2\2\u0093\u009c\7\t\2"+
		"\2\u0094\u009d\7\r\2\2\u0095\u0096\7\13\2\2\u0096\u0098\7\r\2\2\u0097"+
		"\u0095\3\2\2\2\u0098\u009b\3\2\2\2\u0099\u0097\3\2\2\2\u0099\u009a\3\2"+
		"\2\2\u009a\u009d\3\2\2\2\u009b\u0099\3\2\2\2\u009c\u0094\3\2\2\2\u009c"+
		"\u0099\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u009f\7\n\2\2\u009f!\3\2\2\2"+
		"\25$\60\64\67:=AFNU\\elu~\u0086\u0091\u0099\u009c";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}