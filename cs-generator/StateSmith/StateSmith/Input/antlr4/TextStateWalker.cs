using Antlr4.Runtime;
using Antlr4.Runtime.Tree;
using System.Collections.Generic;
using System.Diagnostics.CodeAnalysis;

namespace StateSmith.Input.antlr4
{
    public class TextState
    {
        public string stateName;
        public List<TextBehavior> behaviors = new List<TextBehavior>();
        public IParseTree tree;
    }

    public class TextBehavior
    {
        public List<string> triggers = new List<string>();
        public string order;
        public string guardCode;
        public string actionCode;
    }

    public class TextStateWalker : Grammar1BaseListener
    {
        public TextState textState = new TextState();
        TextBehavior currentBehavior;
        public List<TextBehavior> behaviors = new List<TextBehavior>();


        public override void EnterState_name([NotNull] Grammar1Parser.State_nameContext context)
        {
            textState.stateName = context.IDENTIFIER().GetText();
        }



        public override void EnterBehavior([NotNull] Grammar1Parser.BehaviorContext context)
        {
            // Skip empty behaviors which may occur because the grammar is allowed to be fully optional: `behavior: order? triggers? guard? action? ;`
            if (context.ChildCount == 0)
            {
                return;
            }

            currentBehavior = new TextBehavior();
            currentBehavior.order = context.order()?.number()?.GetText();
            currentBehavior.guardCode = context.guard()?.guard_code()?.GetText().Trim();
            currentBehavior.actionCode = GetActionCodeText(context.action()?.action_code());
            behaviors.Add(currentBehavior);
        }

        private string GetActionCodeText(Grammar1Parser.Action_codeContext action_codeContext)
        {
            if (action_codeContext == null || action_codeContext.ChildCount == 0)
            {
                return null;
            }

            var code = TryGetBracedActionCode(action_codeContext) ?? action_codeContext.GetText();

            return code;
        }


        private static string TryGetBracedActionCode(Grammar1Parser.Action_codeContext action_codeContext)
        {
            var any_code = action_codeContext.braced_expression()?.any_code();

            if (any_code == null)
            {
                return null;
            }

            var visitor = new DeIndentExpandVisitor();

            foreach (var item in any_code.code_element())
            {
                visitor.Visit(item);
            }

            return visitor.stringBuilder.ToString().Trim();
        }

        public override void EnterTrigger_id([NotNull] Grammar1Parser.Trigger_idContext context)
        {
            currentBehavior.triggers.Add(context.expandable_identifier().GetText());
        }

        public override void ExitBehavior([NotNull] Grammar1Parser.BehaviorContext context)
        {
            currentBehavior = null;
        }
    }
}
