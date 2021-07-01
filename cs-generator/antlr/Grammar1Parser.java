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
		T__9=10, T__10=11, T__11=12, LINE_ENDER=13, IDENTIFIER=14, LINE_COMMENT=15, 
		STAR_COMMENT=16, STRING=17, TICK_STRING=18, DIGIT=19, PERIOD=20, COMMA=21, 
		CODE_SYMBOL=22, HWS=23;
	public static final int
		RULE_optional_any_space = 0, RULE_ohs = 1, RULE_state_defn = 2, RULE_state_name = 3, 
		RULE_nl_behaviors = 4, RULE_nl_behavior = 5, RULE_behavior = 6, RULE_order = 7, 
		RULE_triggers = 8, RULE_trigger_id = 9, RULE_trigger_list = 10, RULE_guard = 11, 
		RULE_guard_code = 12, RULE_action = 13, RULE_action_code = 14, RULE_naked_action_code = 15, 
		RULE_member_access_operator = 16, RULE_member_access = 17, RULE_expandable_identifier = 18, 
		RULE_group_expression = 19, RULE_square_brace_expression = 20, RULE_braced_expression = 21, 
		RULE_line_comment = 22, RULE_star_comment = 23, RULE_function_args = 24, 
		RULE_function_arg = 25, RULE_braced_function_args = 26, RULE_expandable_function_call = 27, 
		RULE_member_function_call = 28, RULE_any_code = 29, RULE_code_element = 30, 
		RULE_naked_action_code_elements = 31, RULE_code_line_element = 32, RULE_code_line = 33, 
		RULE_number = 34, RULE_string = 35;
	private static String[] makeRuleNames() {
		return new String[] {
			"optional_any_space", "ohs", "state_defn", "state_name", "nl_behaviors", 
			"nl_behavior", "behavior", "order", "triggers", "trigger_id", "trigger_list", 
			"guard", "guard_code", "action", "action_code", "naked_action_code", 
			"member_access_operator", "member_access", "expandable_identifier", "group_expression", 
			"square_brace_expression", "braced_expression", "line_comment", "star_comment", 
			"function_args", "function_arg", "braced_function_args", "expandable_function_call", 
			"member_function_call", "any_code", "code_element", "naked_action_code_elements", 
			"code_line_element", "code_line", "number", "string"
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

	public static class Optional_any_spaceContext extends ParserRuleContext {
		public List<TerminalNode> HWS() { return getTokens(Grammar1Parser.HWS); }
		public TerminalNode HWS(int i) {
			return getToken(Grammar1Parser.HWS, i);
		}
		public List<TerminalNode> LINE_ENDER() { return getTokens(Grammar1Parser.LINE_ENDER); }
		public TerminalNode LINE_ENDER(int i) {
			return getToken(Grammar1Parser.LINE_ENDER, i);
		}
		public Optional_any_spaceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_optional_any_space; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterOptional_any_space(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitOptional_any_space(this);
		}
	}

	public final Optional_any_spaceContext optional_any_space() throws RecognitionException {
		Optional_any_spaceContext _localctx = new Optional_any_spaceContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_optional_any_space);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(72);
					_la = _input.LA(1);
					if ( !(_la==LINE_ENDER || _la==HWS) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					} 
				}
				setState(77);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
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

	public static class OhsContext extends ParserRuleContext {
		public TerminalNode HWS() { return getToken(Grammar1Parser.HWS, 0); }
		public OhsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ohs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterOhs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitOhs(this);
		}
	}

	public final OhsContext ohs() throws RecognitionException {
		OhsContext _localctx = new OhsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_ohs);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(78);
				match(HWS);
				}
				break;
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

	public static class State_defnContext extends ParserRuleContext {
		public List<Optional_any_spaceContext> optional_any_space() {
			return getRuleContexts(Optional_any_spaceContext.class);
		}
		public Optional_any_spaceContext optional_any_space(int i) {
			return getRuleContext(Optional_any_spaceContext.class,i);
		}
		public State_nameContext state_name() {
			return getRuleContext(State_nameContext.class,0);
		}
		public OhsContext ohs() {
			return getRuleContext(OhsContext.class,0);
		}
		public TerminalNode EOF() { return getToken(Grammar1Parser.EOF, 0); }
		public Nl_behaviorsContext nl_behaviors() {
			return getRuleContext(Nl_behaviorsContext.class,0);
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
		enterRule(_localctx, 4, RULE_state_defn);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			optional_any_space();
			setState(82);
			state_name();
			setState(83);
			ohs();
			setState(86);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(84);
				nl_behaviors();
				}
				break;
			case 2:
				{
				setState(85);
				optional_any_space();
				}
				break;
			}
			setState(88);
			optional_any_space();
			setState(89);
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
		enterRule(_localctx, 6, RULE_state_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
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

	public static class Nl_behaviorsContext extends ParserRuleContext {
		public List<Nl_behaviorContext> nl_behavior() {
			return getRuleContexts(Nl_behaviorContext.class);
		}
		public Nl_behaviorContext nl_behavior(int i) {
			return getRuleContext(Nl_behaviorContext.class,i);
		}
		public Nl_behaviorsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nl_behaviors; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterNl_behaviors(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitNl_behaviors(this);
		}
	}

	public final Nl_behaviorsContext nl_behaviors() throws RecognitionException {
		Nl_behaviorsContext _localctx = new Nl_behaviorsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_nl_behaviors);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(94); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(93);
					nl_behavior();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(96); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
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

	public static class Nl_behaviorContext extends ParserRuleContext {
		public TerminalNode LINE_ENDER() { return getToken(Grammar1Parser.LINE_ENDER, 0); }
		public Optional_any_spaceContext optional_any_space() {
			return getRuleContext(Optional_any_spaceContext.class,0);
		}
		public BehaviorContext behavior() {
			return getRuleContext(BehaviorContext.class,0);
		}
		public Nl_behaviorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nl_behavior; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterNl_behavior(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitNl_behavior(this);
		}
	}

	public final Nl_behaviorContext nl_behavior() throws RecognitionException {
		Nl_behaviorContext _localctx = new Nl_behaviorContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_nl_behavior);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			match(LINE_ENDER);
			setState(99);
			optional_any_space();
			setState(100);
			behavior();
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
		public TriggersContext triggers() {
			return getRuleContext(TriggersContext.class,0);
		}
		public GuardContext guard() {
			return getRuleContext(GuardContext.class,0);
		}
		public OrderContext order() {
			return getRuleContext(OrderContext.class,0);
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
		enterRule(_localctx, 12, RULE_behavior);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(102);
				order();
				}
				break;
			}
			setState(110);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(105);
				triggers();
				setState(106);
				guard();
				}
				break;
			case 2:
				{
				setState(108);
				triggers();
				}
				break;
			case 3:
				{
				setState(109);
				guard();
				}
				break;
			}
			setState(113);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(112);
				action();
				}
				break;
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

	public static class OrderContext extends ParserRuleContext {
		public List<OhsContext> ohs() {
			return getRuleContexts(OhsContext.class);
		}
		public OhsContext ohs(int i) {
			return getRuleContext(OhsContext.class,i);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public TerminalNode PERIOD() { return getToken(Grammar1Parser.PERIOD, 0); }
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
			setState(115);
			ohs();
			setState(116);
			number();
			setState(117);
			ohs();
			setState(118);
			match(PERIOD);
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
		enterRule(_localctx, 16, RULE_triggers);
		try {
			setState(122);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(120);
				trigger_id();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(121);
				trigger_list();
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

	public static class Trigger_idContext extends ParserRuleContext {
		public OhsContext ohs() {
			return getRuleContext(OhsContext.class,0);
		}
		public Expandable_identifierContext expandable_identifier() {
			return getRuleContext(Expandable_identifierContext.class,0);
		}
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
		enterRule(_localctx, 18, RULE_trigger_id);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			ohs();
			setState(125);
			expandable_identifier();
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
		public List<OhsContext> ohs() {
			return getRuleContexts(OhsContext.class);
		}
		public OhsContext ohs(int i) {
			return getRuleContext(OhsContext.class,i);
		}
		public List<Optional_any_spaceContext> optional_any_space() {
			return getRuleContexts(Optional_any_spaceContext.class);
		}
		public Optional_any_spaceContext optional_any_space(int i) {
			return getRuleContext(Optional_any_spaceContext.class,i);
		}
		public List<Trigger_idContext> trigger_id() {
			return getRuleContexts(Trigger_idContext.class);
		}
		public Trigger_idContext trigger_id(int i) {
			return getRuleContext(Trigger_idContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(Grammar1Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(Grammar1Parser.COMMA, i);
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
		enterRule(_localctx, 20, RULE_trigger_list);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			ohs();
			setState(128);
			match(T__0);
			setState(129);
			optional_any_space();
			setState(130);
			trigger_id();
			setState(138);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(131);
					optional_any_space();
					setState(132);
					match(COMMA);
					setState(133);
					optional_any_space();
					setState(134);
					trigger_id();
					}
					} 
				}
				setState(140);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			setState(141);
			ohs();
			setState(142);
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

	public static class GuardContext extends ParserRuleContext {
		public OhsContext ohs() {
			return getRuleContext(OhsContext.class,0);
		}
		public Guard_codeContext guard_code() {
			return getRuleContext(Guard_codeContext.class,0);
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
		enterRule(_localctx, 22, RULE_guard);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			ohs();
			setState(145);
			match(T__2);
			setState(146);
			guard_code();
			setState(147);
			match(T__3);
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

	public static class Guard_codeContext extends ParserRuleContext {
		public OhsContext ohs() {
			return getRuleContext(OhsContext.class,0);
		}
		public Any_codeContext any_code() {
			return getRuleContext(Any_codeContext.class,0);
		}
		public Guard_codeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_guard_code; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterGuard_code(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitGuard_code(this);
		}
	}

	public final Guard_codeContext guard_code() throws RecognitionException {
		Guard_codeContext _localctx = new Guard_codeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_guard_code);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			ohs();
			setState(150);
			any_code();
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
		public List<OhsContext> ohs() {
			return getRuleContexts(OhsContext.class);
		}
		public OhsContext ohs(int i) {
			return getRuleContext(OhsContext.class,i);
		}
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
		enterRule(_localctx, 26, RULE_action);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			ohs();
			setState(153);
			match(T__4);
			setState(154);
			ohs();
			setState(155);
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
		public Naked_action_codeContext naked_action_code() {
			return getRuleContext(Naked_action_codeContext.class,0);
		}
		public Braced_expressionContext braced_expression() {
			return getRuleContext(Braced_expressionContext.class,0);
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
		enterRule(_localctx, 28, RULE_action_code);
		try {
			setState(159);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case T__5:
			case T__6:
			case T__9:
			case T__10:
			case IDENTIFIER:
			case STAR_COMMENT:
			case STRING:
			case TICK_STRING:
			case DIGIT:
			case PERIOD:
			case CODE_SYMBOL:
			case HWS:
				enterOuterAlt(_localctx, 1);
				{
				setState(157);
				naked_action_code();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 2);
				{
				setState(158);
				braced_expression();
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

	public static class Naked_action_codeContext extends ParserRuleContext {
		public List<Naked_action_code_elementsContext> naked_action_code_elements() {
			return getRuleContexts(Naked_action_code_elementsContext.class);
		}
		public Naked_action_code_elementsContext naked_action_code_elements(int i) {
			return getRuleContext(Naked_action_code_elementsContext.class,i);
		}
		public Naked_action_codeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_naked_action_code; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterNaked_action_code(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitNaked_action_code(this);
		}
	}

	public final Naked_action_codeContext naked_action_code() throws RecognitionException {
		Naked_action_codeContext _localctx = new Naked_action_codeContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_naked_action_code);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(162); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(161);
					naked_action_code_elements();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(164); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
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

	public static class Member_access_operatorContext extends ParserRuleContext {
		public TerminalNode PERIOD() { return getToken(Grammar1Parser.PERIOD, 0); }
		public Member_access_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_member_access_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterMember_access_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitMember_access_operator(this);
		}
	}

	public final Member_access_operatorContext member_access_operator() throws RecognitionException {
		Member_access_operatorContext _localctx = new Member_access_operatorContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_member_access_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << PERIOD))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class Member_accessContext extends ParserRuleContext {
		public List<OhsContext> ohs() {
			return getRuleContexts(OhsContext.class);
		}
		public OhsContext ohs(int i) {
			return getRuleContext(OhsContext.class,i);
		}
		public Member_access_operatorContext member_access_operator() {
			return getRuleContext(Member_access_operatorContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(Grammar1Parser.IDENTIFIER, 0); }
		public Member_function_callContext member_function_call() {
			return getRuleContext(Member_function_callContext.class,0);
		}
		public Member_accessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_member_access; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterMember_access(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitMember_access(this);
		}
	}

	public final Member_accessContext member_access() throws RecognitionException {
		Member_accessContext _localctx = new Member_accessContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_member_access);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			ohs();
			setState(169);
			member_access_operator();
			setState(170);
			ohs();
			setState(173);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(171);
				match(IDENTIFIER);
				}
				break;
			case 2:
				{
				setState(172);
				member_function_call();
				}
				break;
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

	public static class Expandable_identifierContext extends ParserRuleContext {
		public OhsContext ohs() {
			return getRuleContext(OhsContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(Grammar1Parser.IDENTIFIER, 0); }
		public Expandable_identifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expandable_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterExpandable_identifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitExpandable_identifier(this);
		}
	}

	public final Expandable_identifierContext expandable_identifier() throws RecognitionException {
		Expandable_identifierContext _localctx = new Expandable_identifierContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_expandable_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			ohs();
			setState(176);
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

	public static class Group_expressionContext extends ParserRuleContext {
		public OhsContext ohs() {
			return getRuleContext(OhsContext.class,0);
		}
		public Any_codeContext any_code() {
			return getRuleContext(Any_codeContext.class,0);
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
		enterRule(_localctx, 38, RULE_group_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			ohs();
			setState(179);
			match(T__0);
			setState(180);
			any_code();
			setState(181);
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

	public static class Square_brace_expressionContext extends ParserRuleContext {
		public Any_codeContext any_code() {
			return getRuleContext(Any_codeContext.class,0);
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
		enterRule(_localctx, 40, RULE_square_brace_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			match(T__2);
			setState(184);
			any_code();
			setState(185);
			match(T__3);
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
		public Any_codeContext any_code() {
			return getRuleContext(Any_codeContext.class,0);
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
		enterRule(_localctx, 42, RULE_braced_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			match(T__7);
			setState(188);
			any_code();
			setState(189);
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

	public static class Line_commentContext extends ParserRuleContext {
		public TerminalNode LINE_COMMENT() { return getToken(Grammar1Parser.LINE_COMMENT, 0); }
		public Line_commentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_line_comment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterLine_comment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitLine_comment(this);
		}
	}

	public final Line_commentContext line_comment() throws RecognitionException {
		Line_commentContext _localctx = new Line_commentContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_line_comment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			match(LINE_COMMENT);
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

	public static class Star_commentContext extends ParserRuleContext {
		public TerminalNode STAR_COMMENT() { return getToken(Grammar1Parser.STAR_COMMENT, 0); }
		public Star_commentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_star_comment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterStar_comment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitStar_comment(this);
		}
	}

	public final Star_commentContext star_comment() throws RecognitionException {
		Star_commentContext _localctx = new Star_commentContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_star_comment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			match(STAR_COMMENT);
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

	public static class Function_argsContext extends ParserRuleContext {
		public List<Function_argContext> function_arg() {
			return getRuleContexts(Function_argContext.class);
		}
		public Function_argContext function_arg(int i) {
			return getRuleContext(Function_argContext.class,i);
		}
		public List<Optional_any_spaceContext> optional_any_space() {
			return getRuleContexts(Optional_any_spaceContext.class);
		}
		public Optional_any_spaceContext optional_any_space(int i) {
			return getRuleContext(Optional_any_spaceContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(Grammar1Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(Grammar1Parser.COMMA, i);
		}
		public Function_argsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterFunction_args(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitFunction_args(this);
		}
	}

	public final Function_argsContext function_args() throws RecognitionException {
		Function_argsContext _localctx = new Function_argsContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_function_args);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(195);
			function_arg();
			setState(202);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(196);
					optional_any_space();
					setState(197);
					match(COMMA);
					setState(198);
					function_arg();
					}
					} 
				}
				setState(204);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
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

	public static class Function_argContext extends ParserRuleContext {
		public Optional_any_spaceContext optional_any_space() {
			return getRuleContext(Optional_any_spaceContext.class,0);
		}
		public List<Code_elementContext> code_element() {
			return getRuleContexts(Code_elementContext.class);
		}
		public Code_elementContext code_element(int i) {
			return getRuleContext(Code_elementContext.class,i);
		}
		public Function_argContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_arg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterFunction_arg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitFunction_arg(this);
		}
	}

	public final Function_argContext function_arg() throws RecognitionException {
		Function_argContext _localctx = new Function_argContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_function_arg);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(205);
			optional_any_space();
			setState(207); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(206);
					code_element();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(209); 
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

	public static class Braced_function_argsContext extends ParserRuleContext {
		public List<Optional_any_spaceContext> optional_any_space() {
			return getRuleContexts(Optional_any_spaceContext.class);
		}
		public Optional_any_spaceContext optional_any_space(int i) {
			return getRuleContext(Optional_any_spaceContext.class,i);
		}
		public Function_argsContext function_args() {
			return getRuleContext(Function_argsContext.class,0);
		}
		public Braced_function_argsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_braced_function_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterBraced_function_args(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitBraced_function_args(this);
		}
	}

	public final Braced_function_argsContext braced_function_args() throws RecognitionException {
		Braced_function_argsContext _localctx = new Braced_function_argsContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_braced_function_args);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(211);
			match(T__0);
			setState(212);
			optional_any_space();
			setState(214);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				setState(213);
				function_args();
				}
				break;
			}
			setState(216);
			optional_any_space();
			setState(217);
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

	public static class Expandable_function_callContext extends ParserRuleContext {
		public OhsContext ohs() {
			return getRuleContext(OhsContext.class,0);
		}
		public Expandable_identifierContext expandable_identifier() {
			return getRuleContext(Expandable_identifierContext.class,0);
		}
		public Braced_function_argsContext braced_function_args() {
			return getRuleContext(Braced_function_argsContext.class,0);
		}
		public Expandable_function_callContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expandable_function_call; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterExpandable_function_call(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitExpandable_function_call(this);
		}
	}

	public final Expandable_function_callContext expandable_function_call() throws RecognitionException {
		Expandable_function_callContext _localctx = new Expandable_function_callContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_expandable_function_call);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			ohs();
			setState(220);
			expandable_identifier();
			setState(221);
			braced_function_args();
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

	public static class Member_function_callContext extends ParserRuleContext {
		public OhsContext ohs() {
			return getRuleContext(OhsContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(Grammar1Parser.IDENTIFIER, 0); }
		public Braced_function_argsContext braced_function_args() {
			return getRuleContext(Braced_function_argsContext.class,0);
		}
		public Member_function_callContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_member_function_call; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterMember_function_call(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitMember_function_call(this);
		}
	}

	public final Member_function_callContext member_function_call() throws RecognitionException {
		Member_function_callContext _localctx = new Member_function_callContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_member_function_call);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(223);
			ohs();
			setState(224);
			match(IDENTIFIER);
			setState(225);
			braced_function_args();
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

	public static class Any_codeContext extends ParserRuleContext {
		public List<Code_elementContext> code_element() {
			return getRuleContexts(Code_elementContext.class);
		}
		public Code_elementContext code_element(int i) {
			return getRuleContext(Code_elementContext.class,i);
		}
		public Any_codeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_any_code; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterAny_code(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitAny_code(this);
		}
	}

	public final Any_codeContext any_code() throws RecognitionException {
		Any_codeContext _localctx = new Any_codeContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_any_code);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__2) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << LINE_ENDER) | (1L << IDENTIFIER) | (1L << LINE_COMMENT) | (1L << STAR_COMMENT) | (1L << STRING) | (1L << TICK_STRING) | (1L << DIGIT) | (1L << PERIOD) | (1L << CODE_SYMBOL) | (1L << HWS))) != 0)) {
				{
				{
				setState(227);
				code_element();
				}
				}
				setState(232);
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

	public static class Code_elementContext extends ParserRuleContext {
		public Code_line_elementContext code_line_element() {
			return getRuleContext(Code_line_elementContext.class,0);
		}
		public TerminalNode LINE_ENDER() { return getToken(Grammar1Parser.LINE_ENDER, 0); }
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
		enterRule(_localctx, 60, RULE_code_element);
		try {
			setState(235);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case T__2:
			case T__5:
			case T__6:
			case T__7:
			case T__9:
			case T__10:
			case IDENTIFIER:
			case LINE_COMMENT:
			case STAR_COMMENT:
			case STRING:
			case TICK_STRING:
			case DIGIT:
			case PERIOD:
			case CODE_SYMBOL:
			case HWS:
				enterOuterAlt(_localctx, 1);
				{
				setState(233);
				code_line_element();
				}
				break;
			case LINE_ENDER:
				enterOuterAlt(_localctx, 2);
				{
				setState(234);
				match(LINE_ENDER);
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

	public static class Naked_action_code_elementsContext extends ParserRuleContext {
		public Star_commentContext star_comment() {
			return getRuleContext(Star_commentContext.class,0);
		}
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public Expandable_function_callContext expandable_function_call() {
			return getRuleContext(Expandable_function_callContext.class,0);
		}
		public Member_accessContext member_access() {
			return getRuleContext(Member_accessContext.class,0);
		}
		public Expandable_identifierContext expandable_identifier() {
			return getRuleContext(Expandable_identifierContext.class,0);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public TerminalNode CODE_SYMBOL() { return getToken(Grammar1Parser.CODE_SYMBOL, 0); }
		public Group_expressionContext group_expression() {
			return getRuleContext(Group_expressionContext.class,0);
		}
		public TerminalNode HWS() { return getToken(Grammar1Parser.HWS, 0); }
		public Naked_action_code_elementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_naked_action_code_elements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterNaked_action_code_elements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitNaked_action_code_elements(this);
		}
	}

	public final Naked_action_code_elementsContext naked_action_code_elements() throws RecognitionException {
		Naked_action_code_elementsContext _localctx = new Naked_action_code_elementsContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_naked_action_code_elements);
		try {
			setState(246);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(237);
				star_comment();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(238);
				string();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(239);
				expandable_function_call();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(240);
				member_access();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(241);
				expandable_identifier();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(242);
				number();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(243);
				match(CODE_SYMBOL);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(244);
				group_expression();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(245);
				match(HWS);
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

	public static class Code_line_elementContext extends ParserRuleContext {
		public Line_commentContext line_comment() {
			return getRuleContext(Line_commentContext.class,0);
		}
		public Star_commentContext star_comment() {
			return getRuleContext(Star_commentContext.class,0);
		}
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public Expandable_function_callContext expandable_function_call() {
			return getRuleContext(Expandable_function_callContext.class,0);
		}
		public Member_accessContext member_access() {
			return getRuleContext(Member_accessContext.class,0);
		}
		public Expandable_identifierContext expandable_identifier() {
			return getRuleContext(Expandable_identifierContext.class,0);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public TerminalNode CODE_SYMBOL() { return getToken(Grammar1Parser.CODE_SYMBOL, 0); }
		public Group_expressionContext group_expression() {
			return getRuleContext(Group_expressionContext.class,0);
		}
		public Square_brace_expressionContext square_brace_expression() {
			return getRuleContext(Square_brace_expressionContext.class,0);
		}
		public Braced_expressionContext braced_expression() {
			return getRuleContext(Braced_expressionContext.class,0);
		}
		public TerminalNode HWS() { return getToken(Grammar1Parser.HWS, 0); }
		public Code_line_elementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_code_line_element; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterCode_line_element(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitCode_line_element(this);
		}
	}

	public final Code_line_elementContext code_line_element() throws RecognitionException {
		Code_line_elementContext _localctx = new Code_line_elementContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_code_line_element);
		try {
			setState(260);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(248);
				line_comment();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(249);
				star_comment();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(250);
				string();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(251);
				expandable_function_call();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(252);
				member_access();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(253);
				expandable_identifier();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(254);
				number();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(255);
				match(CODE_SYMBOL);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(256);
				group_expression();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(257);
				square_brace_expression();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(258);
				braced_expression();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(259);
				match(HWS);
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

	public static class Code_lineContext extends ParserRuleContext {
		public OhsContext ohs() {
			return getRuleContext(OhsContext.class,0);
		}
		public Code_lineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_code_line; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterCode_line(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitCode_line(this);
		}
	}

	public final Code_lineContext code_line() throws RecognitionException {
		Code_lineContext _localctx = new Code_lineContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_code_line);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
			ohs();
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

	public static class NumberContext extends ParserRuleContext {
		public List<TerminalNode> DIGIT() { return getTokens(Grammar1Parser.DIGIT); }
		public TerminalNode DIGIT(int i) {
			return getToken(Grammar1Parser.DIGIT, i);
		}
		public TerminalNode PERIOD() { return getToken(Grammar1Parser.PERIOD, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Grammar1Listener ) ((Grammar1Listener)listener).exitNumber(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_number);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(265);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__9 || _la==T__10) {
				{
				setState(264);
				_la = _input.LA(1);
				if ( !(_la==T__9 || _la==T__10) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(268); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(267);
					match(DIGIT);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(270); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(278);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				{
				setState(272);
				match(PERIOD);
				setState(274); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(273);
						match(DIGIT);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(276); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			}
			setState(286);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__11) {
				{
				setState(280);
				match(T__11);
				setState(282); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(281);
						match(DIGIT);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(284); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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
		public TerminalNode STRING() { return getToken(Grammar1Parser.STRING, 0); }
		public TerminalNode TICK_STRING() { return getToken(Grammar1Parser.TICK_STRING, 0); }
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
		enterRule(_localctx, 70, RULE_string);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(288);
			_la = _input.LA(1);
			if ( !(_la==STRING || _la==TICK_STRING) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\31\u0125\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\3\2\7\2L\n\2\f\2\16\2O\13\2\3\3\5\3R\n\3"+
		"\3\4\3\4\3\4\3\4\3\4\5\4Y\n\4\3\4\3\4\3\4\3\5\3\5\3\6\6\6a\n\6\r\6\16"+
		"\6b\3\7\3\7\3\7\3\7\3\b\5\bj\n\b\3\b\3\b\3\b\3\b\3\b\5\bq\n\b\3\b\5\b"+
		"t\n\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\5\n}\n\n\3\13\3\13\3\13\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u008b\n\f\f\f\16\f\u008e\13\f\3\f\3\f\3\f"+
		"\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20"+
		"\5\20\u00a2\n\20\3\21\6\21\u00a5\n\21\r\21\16\21\u00a6\3\22\3\22\3\23"+
		"\3\23\3\23\3\23\3\23\5\23\u00b0\n\23\3\24\3\24\3\24\3\25\3\25\3\25\3\25"+
		"\3\25\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\30\3\30\3\31\3\31\3\32"+
		"\3\32\3\32\3\32\3\32\7\32\u00cb\n\32\f\32\16\32\u00ce\13\32\3\33\3\33"+
		"\6\33\u00d2\n\33\r\33\16\33\u00d3\3\34\3\34\3\34\5\34\u00d9\n\34\3\34"+
		"\3\34\3\34\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\37\7\37\u00e7\n\37"+
		"\f\37\16\37\u00ea\13\37\3 \3 \5 \u00ee\n \3!\3!\3!\3!\3!\3!\3!\3!\3!\5"+
		"!\u00f9\n!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\5\"\u0107\n"+
		"\"\3#\3#\3$\5$\u010c\n$\3$\6$\u010f\n$\r$\16$\u0110\3$\3$\6$\u0115\n$"+
		"\r$\16$\u0116\5$\u0119\n$\3$\3$\6$\u011d\n$\r$\16$\u011e\5$\u0121\n$\3"+
		"%\3%\3%\2\2&\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64"+
		"\668:<>@BDFH\2\6\4\2\17\17\31\31\4\2\b\t\26\26\3\2\f\r\3\2\23\24\2\u012b"+
		"\2M\3\2\2\2\4Q\3\2\2\2\6S\3\2\2\2\b]\3\2\2\2\n`\3\2\2\2\fd\3\2\2\2\16"+
		"i\3\2\2\2\20u\3\2\2\2\22|\3\2\2\2\24~\3\2\2\2\26\u0081\3\2\2\2\30\u0092"+
		"\3\2\2\2\32\u0097\3\2\2\2\34\u009a\3\2\2\2\36\u00a1\3\2\2\2 \u00a4\3\2"+
		"\2\2\"\u00a8\3\2\2\2$\u00aa\3\2\2\2&\u00b1\3\2\2\2(\u00b4\3\2\2\2*\u00b9"+
		"\3\2\2\2,\u00bd\3\2\2\2.\u00c1\3\2\2\2\60\u00c3\3\2\2\2\62\u00c5\3\2\2"+
		"\2\64\u00cf\3\2\2\2\66\u00d5\3\2\2\28\u00dd\3\2\2\2:\u00e1\3\2\2\2<\u00e8"+
		"\3\2\2\2>\u00ed\3\2\2\2@\u00f8\3\2\2\2B\u0106\3\2\2\2D\u0108\3\2\2\2F"+
		"\u010b\3\2\2\2H\u0122\3\2\2\2JL\t\2\2\2KJ\3\2\2\2LO\3\2\2\2MK\3\2\2\2"+
		"MN\3\2\2\2N\3\3\2\2\2OM\3\2\2\2PR\7\31\2\2QP\3\2\2\2QR\3\2\2\2R\5\3\2"+
		"\2\2ST\5\2\2\2TU\5\b\5\2UX\5\4\3\2VY\5\n\6\2WY\5\2\2\2XV\3\2\2\2XW\3\2"+
		"\2\2YZ\3\2\2\2Z[\5\2\2\2[\\\7\2\2\3\\\7\3\2\2\2]^\7\20\2\2^\t\3\2\2\2"+
		"_a\5\f\7\2`_\3\2\2\2ab\3\2\2\2b`\3\2\2\2bc\3\2\2\2c\13\3\2\2\2de\7\17"+
		"\2\2ef\5\2\2\2fg\5\16\b\2g\r\3\2\2\2hj\5\20\t\2ih\3\2\2\2ij\3\2\2\2jp"+
		"\3\2\2\2kl\5\22\n\2lm\5\30\r\2mq\3\2\2\2nq\5\22\n\2oq\5\30\r\2pk\3\2\2"+
		"\2pn\3\2\2\2po\3\2\2\2qs\3\2\2\2rt\5\34\17\2sr\3\2\2\2st\3\2\2\2t\17\3"+
		"\2\2\2uv\5\4\3\2vw\5F$\2wx\5\4\3\2xy\7\26\2\2y\21\3\2\2\2z}\5\24\13\2"+
		"{}\5\26\f\2|z\3\2\2\2|{\3\2\2\2}\23\3\2\2\2~\177\5\4\3\2\177\u0080\5&"+
		"\24\2\u0080\25\3\2\2\2\u0081\u0082\5\4\3\2\u0082\u0083\7\3\2\2\u0083\u0084"+
		"\5\2\2\2\u0084\u008c\5\24\13\2\u0085\u0086\5\2\2\2\u0086\u0087\7\27\2"+
		"\2\u0087\u0088\5\2\2\2\u0088\u0089\5\24\13\2\u0089\u008b\3\2\2\2\u008a"+
		"\u0085\3\2\2\2\u008b\u008e\3\2\2\2\u008c\u008a\3\2\2\2\u008c\u008d\3\2"+
		"\2\2\u008d\u008f\3\2\2\2\u008e\u008c\3\2\2\2\u008f\u0090\5\4\3\2\u0090"+
		"\u0091\7\4\2\2\u0091\27\3\2\2\2\u0092\u0093\5\4\3\2\u0093\u0094\7\5\2"+
		"\2\u0094\u0095\5\32\16\2\u0095\u0096\7\6\2\2\u0096\31\3\2\2\2\u0097\u0098"+
		"\5\4\3\2\u0098\u0099\5<\37\2\u0099\33\3\2\2\2\u009a\u009b\5\4\3\2\u009b"+
		"\u009c\7\7\2\2\u009c\u009d\5\4\3\2\u009d\u009e\5\36\20\2\u009e\35\3\2"+
		"\2\2\u009f\u00a2\5 \21\2\u00a0\u00a2\5,\27\2\u00a1\u009f\3\2\2\2\u00a1"+
		"\u00a0\3\2\2\2\u00a2\37\3\2\2\2\u00a3\u00a5\5@!\2\u00a4\u00a3\3\2\2\2"+
		"\u00a5\u00a6\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7!\3"+
		"\2\2\2\u00a8\u00a9\t\3\2\2\u00a9#\3\2\2\2\u00aa\u00ab\5\4\3\2\u00ab\u00ac"+
		"\5\"\22\2\u00ac\u00af\5\4\3\2\u00ad\u00b0\7\20\2\2\u00ae\u00b0\5:\36\2"+
		"\u00af\u00ad\3\2\2\2\u00af\u00ae\3\2\2\2\u00b0%\3\2\2\2\u00b1\u00b2\5"+
		"\4\3\2\u00b2\u00b3\7\20\2\2\u00b3\'\3\2\2\2\u00b4\u00b5\5\4\3\2\u00b5"+
		"\u00b6\7\3\2\2\u00b6\u00b7\5<\37\2\u00b7\u00b8\7\4\2\2\u00b8)\3\2\2\2"+
		"\u00b9\u00ba\7\5\2\2\u00ba\u00bb\5<\37\2\u00bb\u00bc\7\6\2\2\u00bc+\3"+
		"\2\2\2\u00bd\u00be\7\n\2\2\u00be\u00bf\5<\37\2\u00bf\u00c0\7\13\2\2\u00c0"+
		"-\3\2\2\2\u00c1\u00c2\7\21\2\2\u00c2/\3\2\2\2\u00c3\u00c4\7\22\2\2\u00c4"+
		"\61\3\2\2\2\u00c5\u00cc\5\64\33\2\u00c6\u00c7\5\2\2\2\u00c7\u00c8\7\27"+
		"\2\2\u00c8\u00c9\5\64\33\2\u00c9\u00cb\3\2\2\2\u00ca\u00c6\3\2\2\2\u00cb"+
		"\u00ce\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\63\3\2\2"+
		"\2\u00ce\u00cc\3\2\2\2\u00cf\u00d1\5\2\2\2\u00d0\u00d2\5> \2\u00d1\u00d0"+
		"\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4"+
		"\65\3\2\2\2\u00d5\u00d6\7\3\2\2\u00d6\u00d8\5\2\2\2\u00d7\u00d9\5\62\32"+
		"\2\u00d8\u00d7\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00db"+
		"\5\2\2\2\u00db\u00dc\7\4\2\2\u00dc\67\3\2\2\2\u00dd\u00de\5\4\3\2\u00de"+
		"\u00df\5&\24\2\u00df\u00e0\5\66\34\2\u00e09\3\2\2\2\u00e1\u00e2\5\4\3"+
		"\2\u00e2\u00e3\7\20\2\2\u00e3\u00e4\5\66\34\2\u00e4;\3\2\2\2\u00e5\u00e7"+
		"\5> \2\u00e6\u00e5\3\2\2\2\u00e7\u00ea\3\2\2\2\u00e8\u00e6\3\2\2\2\u00e8"+
		"\u00e9\3\2\2\2\u00e9=\3\2\2\2\u00ea\u00e8\3\2\2\2\u00eb\u00ee\5B\"\2\u00ec"+
		"\u00ee\7\17\2\2\u00ed\u00eb\3\2\2\2\u00ed\u00ec\3\2\2\2\u00ee?\3\2\2\2"+
		"\u00ef\u00f9\5\60\31\2\u00f0\u00f9\5H%\2\u00f1\u00f9\58\35\2\u00f2\u00f9"+
		"\5$\23\2\u00f3\u00f9\5&\24\2\u00f4\u00f9\5F$\2\u00f5\u00f9\7\30\2\2\u00f6"+
		"\u00f9\5(\25\2\u00f7\u00f9\7\31\2\2\u00f8\u00ef\3\2\2\2\u00f8\u00f0\3"+
		"\2\2\2\u00f8\u00f1\3\2\2\2\u00f8\u00f2\3\2\2\2\u00f8\u00f3\3\2\2\2\u00f8"+
		"\u00f4\3\2\2\2\u00f8\u00f5\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f8\u00f7\3\2"+
		"\2\2\u00f9A\3\2\2\2\u00fa\u0107\5.\30\2\u00fb\u0107\5\60\31\2\u00fc\u0107"+
		"\5H%\2\u00fd\u0107\58\35\2\u00fe\u0107\5$\23\2\u00ff\u0107\5&\24\2\u0100"+
		"\u0107\5F$\2\u0101\u0107\7\30\2\2\u0102\u0107\5(\25\2\u0103\u0107\5*\26"+
		"\2\u0104\u0107\5,\27\2\u0105\u0107\7\31\2\2\u0106\u00fa\3\2\2\2\u0106"+
		"\u00fb\3\2\2\2\u0106\u00fc\3\2\2\2\u0106\u00fd\3\2\2\2\u0106\u00fe\3\2"+
		"\2\2\u0106\u00ff\3\2\2\2\u0106\u0100\3\2\2\2\u0106\u0101\3\2\2\2\u0106"+
		"\u0102\3\2\2\2\u0106\u0103\3\2\2\2\u0106\u0104\3\2\2\2\u0106\u0105\3\2"+
		"\2\2\u0107C\3\2\2\2\u0108\u0109\5\4\3\2\u0109E\3\2\2\2\u010a\u010c\t\4"+
		"\2\2\u010b\u010a\3\2\2\2\u010b\u010c\3\2\2\2\u010c\u010e\3\2\2\2\u010d"+
		"\u010f\7\25\2\2\u010e\u010d\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u010e\3"+
		"\2\2\2\u0110\u0111\3\2\2\2\u0111\u0118\3\2\2\2\u0112\u0114\7\26\2\2\u0113"+
		"\u0115\7\25\2\2\u0114\u0113\3\2\2\2\u0115\u0116\3\2\2\2\u0116\u0114\3"+
		"\2\2\2\u0116\u0117\3\2\2\2\u0117\u0119\3\2\2\2\u0118\u0112\3\2\2\2\u0118"+
		"\u0119\3\2\2\2\u0119\u0120\3\2\2\2\u011a\u011c\7\16\2\2\u011b\u011d\7"+
		"\25\2\2\u011c\u011b\3\2\2\2\u011d\u011e\3\2\2\2\u011e\u011c\3\2\2\2\u011e"+
		"\u011f\3\2\2\2\u011f\u0121\3\2\2\2\u0120\u011a\3\2\2\2\u0120\u0121\3\2"+
		"\2\2\u0121G\3\2\2\2\u0122\u0123\t\5\2\2\u0123I\3\2\2\2\33MQXbips|\u008c"+
		"\u00a1\u00a6\u00af\u00cc\u00d3\u00d8\u00e8\u00ed\u00f8\u0106\u010b\u0110"+
		"\u0116\u0118\u011e\u0120";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}