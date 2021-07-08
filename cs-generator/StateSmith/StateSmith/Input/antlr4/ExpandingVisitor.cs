using Antlr4.Runtime.Misc;
using Antlr4.Runtime.Tree;
using StateSmith.Input.Expansions;
using System;
using System.Collections.Generic;
using System.Text;
using System.Linq;

namespace StateSmith.Input.antlr4
{
    public class ExpandingVisitor : Grammar1BaseVisitor<string>
    {
        public Expander expander;

        public ExpandingVisitor(Expander expander)
        {
            this.expander = expander;
        }

        public override string VisitTerminal(ITerminalNode node)
        {
            if (node.Symbol != null)
            {
                return node.Symbol.Text;
            }
            return "";
        }

        public override string VisitExpandable_identifier([NotNull] Grammar1Parser.Expandable_identifierContext context)
        {
            string result = context.ohs()?.GetText() ?? "";
            string identifier = context.IDENTIFIER().GetText();
            identifier = expander.TryExpandVariableExpansion(identifier);
            result += identifier;

            return result;
        }

        public override string VisitExpandable_function_call([NotNull] Grammar1Parser.Expandable_function_callContext context)
        {
            var result = context.ohs()?.Accept(this) ?? "";

            //manually get identifier so that we don't visit `VisitExpandable_identifier()` and potentially get a variable expansion.
            var identifier = context.expandable_identifier().GetText();
            if (expander.HasMethodName(identifier))
            {
                result = ExpandFunctionCall(context, result, identifier);
            }
            else
            {
                result += identifier;
                result += context.braced_function_args().Accept(this);
            }

            return result;
        }

        private string ExpandFunctionCall(Grammar1Parser.Expandable_function_callContext context, string result, string identifier)
        {
            //We can't just visit the `function_args` rule because it includes commas and additional white space.
            //We need to manually visit each `function_arg_code` rule
            var functionArgContexts = context.braced_function_args().function_args()?.function_arg();

            var stringArgs = new string[functionArgContexts?.Length ?? 0];

            for (int i = 0; i < stringArgs.Length; i++)
            {
                var argContext = functionArgContexts[i];
                stringArgs[i] = argContext.function_arg_code().Accept(this);
            }

            var expandedCode = expander.TryExpandMethodExpansion(identifier, stringArgs);
            result += expandedCode;
            return result;
        }

        protected override string AggregateResult(string aggregate, string nextResult)
        {
            return aggregate + nextResult;
        }

        public static string ParseAndExpandCode(Expander expander, string code)
        {
            var parser = new LabelParser();
            var visitor = new ExpandingVisitor(expander);
            var result = parser.ParseAndVisitAnyCode(visitor, code);
            if (parser.HasError())
            {
                //todolow improve error handling messages
                throw parser.GetErrors()[0].exception;
            }
            return result;
        }
    }
}
