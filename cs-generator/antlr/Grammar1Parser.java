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
		RULE_trigger_id = 4, RULE_triggers = 5, RULE_order_number = 6, RULE_order = 7, 
		RULE_guard = 8, RULE_action = 9, RULE_action_code = 10, RULE_group_expression = 11, 
		RULE_square_brace_expression = 12, RULE_braced_expression = 13, RULE_code_elements = 14, 
		RULE_code_element = 15, RULE_trigger_list = 16;
	private static String[] makeRuleNames() {
		return new String[] {
			"state_defn", "state_name", "behaviors", "behavior", "trigger_id", "triggers", 
			"order_number", "order", "guard", "action", "action_code", "group_expression", 
			"square_brace_expression", "braced_expression", "code_elements", "code_element", 
			"trigger_list"
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
			setState(34);
			state_name();
			setState(36);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LINE_ENDER) {
				{
				setState(35);
				behaviors();
				}
			}

			setState(38);
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
			setState(40);
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
			setState(42);
			match(LINE_ENDER);
			setState(43);
			behavior();
			setState(48);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LINE_ENDER) {
				{
				{
				setState(44);
				match(LINE_ENDER);
				setState(45);
				behavior();
				}
				}
				setState(50);
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
			setState(52);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DIGIT) {
				{
				setState(51);
				order();
				}
			}

			setState(55);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4 || _la==IDENTIFIER) {
				{
				setState(54);
				triggers();
				}
			}

			setState(58);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(57);
				guard();
				}
			}

			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(60);
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

	public static class Trigger_idContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(Grammar1Parser.IDENTIFIER, 0); }
		public Trigger_idContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_trigger_id; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterTrigger_id(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitTrigger_id(this);
		}
	}

	public final Trigger_idContext trigger_id() throws RecognitionException {
		Trigger_idContext _localctx = new Trigger_idContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_trigger_id);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
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

	public static class TriggersContext extends ParserRuleContext {
		public Trigger_idContext trigger_id() {
			return getRuleContext(Trigger_idContext.class,0);
		}
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
		enterRule(_localctx, 10, RULE_triggers);
		try {
			setState(67);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(65);
				trigger_id();
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 2);
				{
				setState(66);
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

	public static class Order_numberContext extends ParserRuleContext {
		public List<TerminalNode> DIGIT() { return getTokens(Grammar1Parser.DIGIT); }
		public TerminalNode DIGIT(int i) {
			return getToken(Grammar1Parser.DIGIT, i);
		}
		public Order_numberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_order_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterOrder_number(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitOrder_number(this);
		}
	}

	public final Order_numberContext order_number() throws RecognitionException {
		Order_numberContext _localctx = new Order_numberContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_order_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(69);
				match(DIGIT);
				}
				}
				setState(72); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DIGIT );
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
		public Order_numberContext order_number() {
			return getRuleContext(Order_numberContext.class,0);
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
		enterRule(_localctx, 14, RULE_order);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			order_number();
			setState(75);
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
		enterRule(_localctx, 16, RULE_guard);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			match(T__1);
			setState(81);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__4) | (1L << T__6) | (1L << IDENTIFIER) | (1L << LINE_COMMENT) | (1L << ML_COMMENT) | (1L << CHAR_LITERAL) | (1L << STRING) | (1L << CODE_SYMBOLS))) != 0)) {
				{
				{
				setState(78);
				code_elements();
				}
				}
				setState(83);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(84);
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
		public Action_codeContext action_code() {
			return getRuleContext(Action_codeContext.class,0);
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
		enterRule(_localctx, 18, RULE_action);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			match(T__3);
			setState(87);
			action_code();
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

	public static class Action_codeContext extends ParserRuleContext {
		public List<Code_elementsContext> code_elements() {
			return getRuleContexts(Code_elementsContext.class);
		}
		public Code_elementsContext code_elements(int i) {
			return getRuleContext(Code_elementsContext.class,i);
		}
		public Action_codeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_action_code; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterAction_code(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitAction_code(this);
		}
	}

	public final Action_codeContext action_code() throws RecognitionException {
		Action_codeContext _localctx = new Action_codeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_action_code);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__4) | (1L << T__6) | (1L << IDENTIFIER) | (1L << LINE_COMMENT) | (1L << ML_COMMENT) | (1L << CHAR_LITERAL) | (1L << STRING) | (1L << CODE_SYMBOLS))) != 0)) {
				{
				{
				setState(89);
				code_elements();
				}
				}
				setState(94);
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
		enterRule(_localctx, 22, RULE_group_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			match(T__4);
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
			setState(102);
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
		enterRule(_localctx, 24, RULE_square_brace_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(T__1);
			setState(108);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__4) | (1L << T__6) | (1L << IDENTIFIER) | (1L << LINE_COMMENT) | (1L << ML_COMMENT) | (1L << CHAR_LITERAL) | (1L << STRING) | (1L << CODE_SYMBOLS))) != 0)) {
				{
				{
				setState(105);
				code_elements();
				}
				}
				setState(110);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(111);
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
		enterRule(_localctx, 26, RULE_braced_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			match(T__6);
			setState(117);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__4) | (1L << T__6) | (1L << IDENTIFIER) | (1L << LINE_COMMENT) | (1L << ML_COMMENT) | (1L << CHAR_LITERAL) | (1L << STRING) | (1L << CODE_SYMBOLS))) != 0)) {
				{
				{
				setState(114);
				code_elements();
				}
				}
				setState(119);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(120);
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
		enterRule(_localctx, 28, RULE_code_elements);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(123); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(122);
					code_element();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(125); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
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
		enterRule(_localctx, 30, RULE_code_element);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LINE_COMMENT:
				{
				setState(127);
				match(LINE_COMMENT);
				}
				break;
			case ML_COMMENT:
				{
				setState(128);
				match(ML_COMMENT);
				}
				break;
			case CHAR_LITERAL:
				{
				setState(129);
				match(CHAR_LITERAL);
				}
				break;
			case STRING:
				{
				setState(130);
				match(STRING);
				}
				break;
			case IDENTIFIER:
				{
				setState(131);
				match(IDENTIFIER);
				}
				break;
			case CODE_SYMBOLS:
				{
				setState(132);
				match(CODE_SYMBOLS);
				}
				break;
			case T__4:
				{
				setState(133);
				group_expression();
				}
				break;
			case T__1:
				{
				setState(134);
				square_brace_expression();
				}
				break;
			case T__6:
				{
				setState(135);
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
		enterRule(_localctx, 32, RULE_trigger_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			match(T__4);
			setState(147);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				{
				setState(139);
				match(IDENTIFIER);
				}
				break;
			case T__5:
			case T__8:
				{
				setState(144);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__8) {
					{
					{
					setState(140);
					match(T__8);
					setState(141);
					match(IDENTIFIER);
					}
					}
					setState(146);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(149);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\24\u009a\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\5\2\'\n\2\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\7\4\61\n\4\f\4\16\4"+
		"\64\13\4\3\5\5\5\67\n\5\3\5\5\5:\n\5\3\5\5\5=\n\5\3\5\5\5@\n\5\3\6\3\6"+
		"\3\7\3\7\5\7F\n\7\3\b\6\bI\n\b\r\b\16\bJ\3\t\3\t\3\t\3\n\3\n\7\nR\n\n"+
		"\f\n\16\nU\13\n\3\n\3\n\3\13\3\13\3\13\3\f\7\f]\n\f\f\f\16\f`\13\f\3\r"+
		"\3\r\7\rd\n\r\f\r\16\rg\13\r\3\r\3\r\3\16\3\16\7\16m\n\16\f\16\16\16p"+
		"\13\16\3\16\3\16\3\17\3\17\7\17v\n\17\f\17\16\17y\13\17\3\17\3\17\3\20"+
		"\6\20~\n\20\r\20\16\20\177\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\5\21\u008b\n\21\3\22\3\22\3\22\3\22\7\22\u0091\n\22\f\22\16\22\u0094"+
		"\13\22\5\22\u0096\n\22\3\22\3\22\3\22\2\2\23\2\4\6\b\n\f\16\20\22\24\26"+
		"\30\32\34\36 \"\2\2\2\u00a0\2$\3\2\2\2\4*\3\2\2\2\6,\3\2\2\2\b\66\3\2"+
		"\2\2\nA\3\2\2\2\fE\3\2\2\2\16H\3\2\2\2\20L\3\2\2\2\22O\3\2\2\2\24X\3\2"+
		"\2\2\26^\3\2\2\2\30a\3\2\2\2\32j\3\2\2\2\34s\3\2\2\2\36}\3\2\2\2 \u008a"+
		"\3\2\2\2\"\u008c\3\2\2\2$&\5\4\3\2%\'\5\6\4\2&%\3\2\2\2&\'\3\2\2\2\'("+
		"\3\2\2\2()\7\2\2\3)\3\3\2\2\2*+\7\r\2\2+\5\3\2\2\2,-\7\f\2\2-\62\5\b\5"+
		"\2./\7\f\2\2/\61\5\b\5\2\60.\3\2\2\2\61\64\3\2\2\2\62\60\3\2\2\2\62\63"+
		"\3\2\2\2\63\7\3\2\2\2\64\62\3\2\2\2\65\67\5\20\t\2\66\65\3\2\2\2\66\67"+
		"\3\2\2\2\679\3\2\2\28:\5\f\7\298\3\2\2\29:\3\2\2\2:<\3\2\2\2;=\5\22\n"+
		"\2<;\3\2\2\2<=\3\2\2\2=?\3\2\2\2>@\5\24\13\2?>\3\2\2\2?@\3\2\2\2@\t\3"+
		"\2\2\2AB\7\r\2\2B\13\3\2\2\2CF\5\n\6\2DF\5\"\22\2EC\3\2\2\2ED\3\2\2\2"+
		"F\r\3\2\2\2GI\7\16\2\2HG\3\2\2\2IJ\3\2\2\2JH\3\2\2\2JK\3\2\2\2K\17\3\2"+
		"\2\2LM\5\16\b\2MN\7\3\2\2N\21\3\2\2\2OS\7\4\2\2PR\5\36\20\2QP\3\2\2\2"+
		"RU\3\2\2\2SQ\3\2\2\2ST\3\2\2\2TV\3\2\2\2US\3\2\2\2VW\7\5\2\2W\23\3\2\2"+
		"\2XY\7\6\2\2YZ\5\26\f\2Z\25\3\2\2\2[]\5\36\20\2\\[\3\2\2\2]`\3\2\2\2^"+
		"\\\3\2\2\2^_\3\2\2\2_\27\3\2\2\2`^\3\2\2\2ae\7\7\2\2bd\5\36\20\2cb\3\2"+
		"\2\2dg\3\2\2\2ec\3\2\2\2ef\3\2\2\2fh\3\2\2\2ge\3\2\2\2hi\7\b\2\2i\31\3"+
		"\2\2\2jn\7\4\2\2km\5\36\20\2lk\3\2\2\2mp\3\2\2\2nl\3\2\2\2no\3\2\2\2o"+
		"q\3\2\2\2pn\3\2\2\2qr\7\5\2\2r\33\3\2\2\2sw\7\t\2\2tv\5\36\20\2ut\3\2"+
		"\2\2vy\3\2\2\2wu\3\2\2\2wx\3\2\2\2xz\3\2\2\2yw\3\2\2\2z{\7\n\2\2{\35\3"+
		"\2\2\2|~\5 \21\2}|\3\2\2\2~\177\3\2\2\2\177}\3\2\2\2\177\u0080\3\2\2\2"+
		"\u0080\37\3\2\2\2\u0081\u008b\7\17\2\2\u0082\u008b\7\20\2\2\u0083\u008b"+
		"\7\21\2\2\u0084\u008b\7\22\2\2\u0085\u008b\7\r\2\2\u0086\u008b\7\23\2"+
		"\2\u0087\u008b\5\30\r\2\u0088\u008b\5\32\16\2\u0089\u008b\5\34\17\2\u008a"+
		"\u0081\3\2\2\2\u008a\u0082\3\2\2\2\u008a\u0083\3\2\2\2\u008a\u0084\3\2"+
		"\2\2\u008a\u0085\3\2\2\2\u008a\u0086\3\2\2\2\u008a\u0087\3\2\2\2\u008a"+
		"\u0088\3\2\2\2\u008a\u0089\3\2\2\2\u008b!\3\2\2\2\u008c\u0095\7\7\2\2"+
		"\u008d\u0096\7\r\2\2\u008e\u008f\7\13\2\2\u008f\u0091\7\r\2\2\u0090\u008e"+
		"\3\2\2\2\u0091\u0094\3\2\2\2\u0092\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093"+
		"\u0096\3\2\2\2\u0094\u0092\3\2\2\2\u0095\u008d\3\2\2\2\u0095\u0092\3\2"+
		"\2\2\u0096\u0097\3\2\2\2\u0097\u0098\7\b\2\2\u0098#\3\2\2\2\23&\62\66"+
		"9<?EJS^enw\177\u008a\u0092\u0095";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}