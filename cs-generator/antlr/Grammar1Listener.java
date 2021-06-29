// Generated from Grammar1.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link Grammar1Parser}.
 */
public interface Grammar1Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link Grammar1Parser#state_defn}.
	 * @param ctx the parse tree
	 */
	void enterState_defn(Grammar1Parser.State_defnContext ctx);
	/**
	 * Exit a parse tree produced by {@link Grammar1Parser#state_defn}.
	 * @param ctx the parse tree
	 */
	void exitState_defn(Grammar1Parser.State_defnContext ctx);
	/**
	 * Enter a parse tree produced by {@link Grammar1Parser#state_name}.
	 * @param ctx the parse tree
	 */
	void enterState_name(Grammar1Parser.State_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link Grammar1Parser#state_name}.
	 * @param ctx the parse tree
	 */
	void exitState_name(Grammar1Parser.State_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link Grammar1Parser#behaviors}.
	 * @param ctx the parse tree
	 */
	void enterBehaviors(Grammar1Parser.BehaviorsContext ctx);
	/**
	 * Exit a parse tree produced by {@link Grammar1Parser#behaviors}.
	 * @param ctx the parse tree
	 */
	void exitBehaviors(Grammar1Parser.BehaviorsContext ctx);
	/**
	 * Enter a parse tree produced by {@link Grammar1Parser#behavior}.
	 * @param ctx the parse tree
	 */
	void enterBehavior(Grammar1Parser.BehaviorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Grammar1Parser#behavior}.
	 * @param ctx the parse tree
	 */
	void exitBehavior(Grammar1Parser.BehaviorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Grammar1Parser#trigger_id}.
	 * @param ctx the parse tree
	 */
	void enterTrigger_id(Grammar1Parser.Trigger_idContext ctx);
	/**
	 * Exit a parse tree produced by {@link Grammar1Parser#trigger_id}.
	 * @param ctx the parse tree
	 */
	void exitTrigger_id(Grammar1Parser.Trigger_idContext ctx);
	/**
	 * Enter a parse tree produced by {@link Grammar1Parser#triggers}.
	 * @param ctx the parse tree
	 */
	void enterTriggers(Grammar1Parser.TriggersContext ctx);
	/**
	 * Exit a parse tree produced by {@link Grammar1Parser#triggers}.
	 * @param ctx the parse tree
	 */
	void exitTriggers(Grammar1Parser.TriggersContext ctx);
	/**
	 * Enter a parse tree produced by {@link Grammar1Parser#order_number}.
	 * @param ctx the parse tree
	 */
	void enterOrder_number(Grammar1Parser.Order_numberContext ctx);
	/**
	 * Exit a parse tree produced by {@link Grammar1Parser#order_number}.
	 * @param ctx the parse tree
	 */
	void exitOrder_number(Grammar1Parser.Order_numberContext ctx);
	/**
	 * Enter a parse tree produced by {@link Grammar1Parser#order}.
	 * @param ctx the parse tree
	 */
	void enterOrder(Grammar1Parser.OrderContext ctx);
	/**
	 * Exit a parse tree produced by {@link Grammar1Parser#order}.
	 * @param ctx the parse tree
	 */
	void exitOrder(Grammar1Parser.OrderContext ctx);
	/**
	 * Enter a parse tree produced by {@link Grammar1Parser#guard}.
	 * @param ctx the parse tree
	 */
	void enterGuard(Grammar1Parser.GuardContext ctx);
	/**
	 * Exit a parse tree produced by {@link Grammar1Parser#guard}.
	 * @param ctx the parse tree
	 */
	void exitGuard(Grammar1Parser.GuardContext ctx);
	/**
	 * Enter a parse tree produced by {@link Grammar1Parser#action}.
	 * @param ctx the parse tree
	 */
	void enterAction(Grammar1Parser.ActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Grammar1Parser#action}.
	 * @param ctx the parse tree
	 */
	void exitAction(Grammar1Parser.ActionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Grammar1Parser#action_code}.
	 * @param ctx the parse tree
	 */
	void enterAction_code(Grammar1Parser.Action_codeContext ctx);
	/**
	 * Exit a parse tree produced by {@link Grammar1Parser#action_code}.
	 * @param ctx the parse tree
	 */
	void exitAction_code(Grammar1Parser.Action_codeContext ctx);
	/**
	 * Enter a parse tree produced by {@link Grammar1Parser#group_expression}.
	 * @param ctx the parse tree
	 */
	void enterGroup_expression(Grammar1Parser.Group_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Grammar1Parser#group_expression}.
	 * @param ctx the parse tree
	 */
	void exitGroup_expression(Grammar1Parser.Group_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Grammar1Parser#square_brace_expression}.
	 * @param ctx the parse tree
	 */
	void enterSquare_brace_expression(Grammar1Parser.Square_brace_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Grammar1Parser#square_brace_expression}.
	 * @param ctx the parse tree
	 */
	void exitSquare_brace_expression(Grammar1Parser.Square_brace_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Grammar1Parser#braced_expression}.
	 * @param ctx the parse tree
	 */
	void enterBraced_expression(Grammar1Parser.Braced_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Grammar1Parser#braced_expression}.
	 * @param ctx the parse tree
	 */
	void exitBraced_expression(Grammar1Parser.Braced_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Grammar1Parser#code_elements}.
	 * @param ctx the parse tree
	 */
	void enterCode_elements(Grammar1Parser.Code_elementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link Grammar1Parser#code_elements}.
	 * @param ctx the parse tree
	 */
	void exitCode_elements(Grammar1Parser.Code_elementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link Grammar1Parser#code_element}.
	 * @param ctx the parse tree
	 */
	void enterCode_element(Grammar1Parser.Code_elementContext ctx);
	/**
	 * Exit a parse tree produced by {@link Grammar1Parser#code_element}.
	 * @param ctx the parse tree
	 */
	void exitCode_element(Grammar1Parser.Code_elementContext ctx);
	/**
	 * Enter a parse tree produced by {@link Grammar1Parser#trigger_list}.
	 * @param ctx the parse tree
	 */
	void enterTrigger_list(Grammar1Parser.Trigger_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link Grammar1Parser#trigger_list}.
	 * @param ctx the parse tree
	 */
	void exitTrigger_list(Grammar1Parser.Trigger_listContext ctx);
}