//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     ANTLR Version: 4.9.2
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

// Generated from Grammar1.g4 by ANTLR 4.9.2

// Unreachable code detected
#pragma warning disable 0162
// The variable '...' is assigned but its value is never used
#pragma warning disable 0219
// Missing XML comment for publicly visible type or member '...'
#pragma warning disable 1591
// Ambiguous reference in cref attribute
#pragma warning disable 419

using Antlr4.Runtime.Misc;
using IParseTreeListener = Antlr4.Runtime.Tree.IParseTreeListener;
using IToken = Antlr4.Runtime.IToken;

/// <summary>
/// This interface defines a complete listener for a parse tree produced by
/// <see cref="Grammar1Parser"/>.
/// </summary>
[System.CodeDom.Compiler.GeneratedCode("ANTLR", "4.9.2")]
[System.CLSCompliant(false)]
public interface IGrammar1Listener : IParseTreeListener {
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.optional_any_space"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterOptional_any_space([NotNull] Grammar1Parser.Optional_any_spaceContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.optional_any_space"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitOptional_any_space([NotNull] Grammar1Parser.Optional_any_spaceContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.ohs"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterOhs([NotNull] Grammar1Parser.OhsContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.ohs"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitOhs([NotNull] Grammar1Parser.OhsContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.some_ws"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterSome_ws([NotNull] Grammar1Parser.Some_wsContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.some_ws"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitSome_ws([NotNull] Grammar1Parser.Some_wsContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.node"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterNode([NotNull] Grammar1Parser.NodeContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.node"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitNode([NotNull] Grammar1Parser.NodeContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.statemachine_defn"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterStatemachine_defn([NotNull] Grammar1Parser.Statemachine_defnContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.statemachine_defn"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitStatemachine_defn([NotNull] Grammar1Parser.Statemachine_defnContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.notes_text"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterNotes_text([NotNull] Grammar1Parser.Notes_textContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.notes_text"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitNotes_text([NotNull] Grammar1Parser.Notes_textContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.notes_node"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterNotes_node([NotNull] Grammar1Parser.Notes_nodeContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.notes_node"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitNotes_node([NotNull] Grammar1Parser.Notes_nodeContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.state_behaviors"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterState_behaviors([NotNull] Grammar1Parser.State_behaviorsContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.state_behaviors"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitState_behaviors([NotNull] Grammar1Parser.State_behaviorsContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.ortho_defn"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterOrtho_defn([NotNull] Grammar1Parser.Ortho_defnContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.ortho_defn"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitOrtho_defn([NotNull] Grammar1Parser.Ortho_defnContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.state_defn"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterState_defn([NotNull] Grammar1Parser.State_defnContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.state_defn"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitState_defn([NotNull] Grammar1Parser.State_defnContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.global_id"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterGlobal_id([NotNull] Grammar1Parser.Global_idContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.global_id"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitGlobal_id([NotNull] Grammar1Parser.Global_idContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.state_id"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterState_id([NotNull] Grammar1Parser.State_idContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.state_id"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitState_id([NotNull] Grammar1Parser.State_idContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.ortho_order"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterOrtho_order([NotNull] Grammar1Parser.Ortho_orderContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.ortho_order"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitOrtho_order([NotNull] Grammar1Parser.Ortho_orderContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.edge"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterEdge([NotNull] Grammar1Parser.EdgeContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.edge"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitEdge([NotNull] Grammar1Parser.EdgeContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.edge_behaviors"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterEdge_behaviors([NotNull] Grammar1Parser.Edge_behaviorsContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.edge_behaviors"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitEdge_behaviors([NotNull] Grammar1Parser.Edge_behaviorsContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.nl_behaviors"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterNl_behaviors([NotNull] Grammar1Parser.Nl_behaviorsContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.nl_behaviors"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitNl_behaviors([NotNull] Grammar1Parser.Nl_behaviorsContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.nl_behavior"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterNl_behavior([NotNull] Grammar1Parser.Nl_behaviorContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.nl_behavior"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitNl_behavior([NotNull] Grammar1Parser.Nl_behaviorContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.behavior"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterBehavior([NotNull] Grammar1Parser.BehaviorContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.behavior"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitBehavior([NotNull] Grammar1Parser.BehaviorContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.order"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterOrder([NotNull] Grammar1Parser.OrderContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.order"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitOrder([NotNull] Grammar1Parser.OrderContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.triggers"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterTriggers([NotNull] Grammar1Parser.TriggersContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.triggers"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitTriggers([NotNull] Grammar1Parser.TriggersContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.trigger_id"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterTrigger_id([NotNull] Grammar1Parser.Trigger_idContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.trigger_id"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitTrigger_id([NotNull] Grammar1Parser.Trigger_idContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.trigger_list"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterTrigger_list([NotNull] Grammar1Parser.Trigger_listContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.trigger_list"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitTrigger_list([NotNull] Grammar1Parser.Trigger_listContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.guard"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterGuard([NotNull] Grammar1Parser.GuardContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.guard"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitGuard([NotNull] Grammar1Parser.GuardContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.guard_code"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterGuard_code([NotNull] Grammar1Parser.Guard_codeContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.guard_code"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitGuard_code([NotNull] Grammar1Parser.Guard_codeContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.action"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterAction([NotNull] Grammar1Parser.ActionContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.action"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitAction([NotNull] Grammar1Parser.ActionContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.action_code"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterAction_code([NotNull] Grammar1Parser.Action_codeContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.action_code"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitAction_code([NotNull] Grammar1Parser.Action_codeContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.naked_action_code"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterNaked_action_code([NotNull] Grammar1Parser.Naked_action_codeContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.naked_action_code"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitNaked_action_code([NotNull] Grammar1Parser.Naked_action_codeContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.member_access_operator"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterMember_access_operator([NotNull] Grammar1Parser.Member_access_operatorContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.member_access_operator"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitMember_access_operator([NotNull] Grammar1Parser.Member_access_operatorContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.member_access"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterMember_access([NotNull] Grammar1Parser.Member_accessContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.member_access"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitMember_access([NotNull] Grammar1Parser.Member_accessContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.expandable_identifier"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterExpandable_identifier([NotNull] Grammar1Parser.Expandable_identifierContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.expandable_identifier"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitExpandable_identifier([NotNull] Grammar1Parser.Expandable_identifierContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.group_expression"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterGroup_expression([NotNull] Grammar1Parser.Group_expressionContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.group_expression"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitGroup_expression([NotNull] Grammar1Parser.Group_expressionContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.square_brace_expression"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterSquare_brace_expression([NotNull] Grammar1Parser.Square_brace_expressionContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.square_brace_expression"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitSquare_brace_expression([NotNull] Grammar1Parser.Square_brace_expressionContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.braced_expression"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterBraced_expression([NotNull] Grammar1Parser.Braced_expressionContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.braced_expression"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitBraced_expression([NotNull] Grammar1Parser.Braced_expressionContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.line_comment"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterLine_comment([NotNull] Grammar1Parser.Line_commentContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.line_comment"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitLine_comment([NotNull] Grammar1Parser.Line_commentContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.star_comment"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterStar_comment([NotNull] Grammar1Parser.Star_commentContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.star_comment"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitStar_comment([NotNull] Grammar1Parser.Star_commentContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.function_args"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterFunction_args([NotNull] Grammar1Parser.Function_argsContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.function_args"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitFunction_args([NotNull] Grammar1Parser.Function_argsContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.function_arg"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterFunction_arg([NotNull] Grammar1Parser.Function_argContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.function_arg"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitFunction_arg([NotNull] Grammar1Parser.Function_argContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.leading_optional_any_space"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterLeading_optional_any_space([NotNull] Grammar1Parser.Leading_optional_any_spaceContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.leading_optional_any_space"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitLeading_optional_any_space([NotNull] Grammar1Parser.Leading_optional_any_spaceContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.trailing_optional_any_space"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterTrailing_optional_any_space([NotNull] Grammar1Parser.Trailing_optional_any_spaceContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.trailing_optional_any_space"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitTrailing_optional_any_space([NotNull] Grammar1Parser.Trailing_optional_any_spaceContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.braced_function_args"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterBraced_function_args([NotNull] Grammar1Parser.Braced_function_argsContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.braced_function_args"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitBraced_function_args([NotNull] Grammar1Parser.Braced_function_argsContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.expandable_function_call"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterExpandable_function_call([NotNull] Grammar1Parser.Expandable_function_callContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.expandable_function_call"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitExpandable_function_call([NotNull] Grammar1Parser.Expandable_function_callContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.member_function_call"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterMember_function_call([NotNull] Grammar1Parser.Member_function_callContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.member_function_call"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitMember_function_call([NotNull] Grammar1Parser.Member_function_callContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.any_code"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterAny_code([NotNull] Grammar1Parser.Any_codeContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.any_code"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitAny_code([NotNull] Grammar1Parser.Any_codeContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.code_element"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterCode_element([NotNull] Grammar1Parser.Code_elementContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.code_element"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitCode_element([NotNull] Grammar1Parser.Code_elementContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.naked_action_code_elements"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterNaked_action_code_elements([NotNull] Grammar1Parser.Naked_action_code_elementsContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.naked_action_code_elements"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitNaked_action_code_elements([NotNull] Grammar1Parser.Naked_action_code_elementsContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.code_line_element"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterCode_line_element([NotNull] Grammar1Parser.Code_line_elementContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.code_line_element"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitCode_line_element([NotNull] Grammar1Parser.Code_line_elementContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.code_line"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterCode_line([NotNull] Grammar1Parser.Code_lineContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.code_line"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitCode_line([NotNull] Grammar1Parser.Code_lineContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.line_end_with_hs"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterLine_end_with_hs([NotNull] Grammar1Parser.Line_end_with_hsContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.line_end_with_hs"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitLine_end_with_hs([NotNull] Grammar1Parser.Line_end_with_hsContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.number"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterNumber([NotNull] Grammar1Parser.NumberContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.number"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitNumber([NotNull] Grammar1Parser.NumberContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.string"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterString([NotNull] Grammar1Parser.StringContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.string"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitString([NotNull] Grammar1Parser.StringContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.code_symbol"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterCode_symbol([NotNull] Grammar1Parser.Code_symbolContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.code_symbol"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitCode_symbol([NotNull] Grammar1Parser.Code_symbolContext context);
}
