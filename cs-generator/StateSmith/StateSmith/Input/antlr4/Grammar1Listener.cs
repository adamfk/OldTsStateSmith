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
	/// Enter a parse tree produced by <see cref="Grammar1Parser.state_name"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterState_name([NotNull] Grammar1Parser.State_nameContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.state_name"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitState_name([NotNull] Grammar1Parser.State_nameContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.behaviors"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterBehaviors([NotNull] Grammar1Parser.BehaviorsContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.behaviors"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitBehaviors([NotNull] Grammar1Parser.BehaviorsContext context);
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
	/// Enter a parse tree produced by <see cref="Grammar1Parser.braced_action"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterBraced_action([NotNull] Grammar1Parser.Braced_actionContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.braced_action"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitBraced_action([NotNull] Grammar1Parser.Braced_actionContext context);
	/// <summary>
	/// Enter a parse tree produced by <see cref="Grammar1Parser.naked_action"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterNaked_action([NotNull] Grammar1Parser.Naked_actionContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.naked_action"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitNaked_action([NotNull] Grammar1Parser.Naked_actionContext context);
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
	/// Enter a parse tree produced by <see cref="Grammar1Parser.code_elements"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void EnterCode_elements([NotNull] Grammar1Parser.Code_elementsContext context);
	/// <summary>
	/// Exit a parse tree produced by <see cref="Grammar1Parser.code_elements"/>.
	/// </summary>
	/// <param name="context">The parse tree.</param>
	void ExitCode_elements([NotNull] Grammar1Parser.Code_elementsContext context);
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
}