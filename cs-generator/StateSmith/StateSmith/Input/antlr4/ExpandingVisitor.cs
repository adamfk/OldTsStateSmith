using Antlr4.Runtime.Tree;
using StateSmith.Input.Expansions;
using System;
using System.Collections.Generic;
using System.Diagnostics.CodeAnalysis;
using System.Text;

namespace StateSmith.Input.antlr4
{
    public class ExpandingVisitor : Grammar1BaseVisitor<int>
    {
        /// <summary>
        /// It is more efficient to just use a StringBuilder field rather than
        /// returning a string from the visit method and appending them to larger strings.
        /// </summary>
        private const int UNUSED = 0;

        public StringBuilder stringBuilder = new StringBuilder();

        public Expander expander;

        public ExpandingVisitor(Expander expander)
        {
            this.expander = expander;
        }

        public override int VisitTerminal(ITerminalNode node)
        {
            if (node.Symbol != null)
            {
                Append(node.Symbol.Text);
            }
            return UNUSED;
        }

        public override int VisitExpandable_identifier([NotNull] Grammar1Parser.Expandable_identifierContext context)
        {
            Append(context.ohs()?.GetText() ?? "");
            string identifier = context.IDENTIFIER().GetText();
            identifier = expander.TryExpandVariableExpansion(identifier);
            Append(identifier);

            return UNUSED;
        }

        public override int VisitExpandable_function_call([NotNull] Grammar1Parser.Expandable_function_callContext context)
        {
            return base.VisitExpandable_function_call(context);
        }

        private void Append(string str)
        {
            stringBuilder.Append(str);
        }

        public static string ParseAndExpandCode(Expander expander, string code)
        {
            var parser = new LabelParser();
            var visitor = new ExpandingVisitor(expander);
            parser.ParseAndVisitAnyCode(visitor, code);
            if (parser.HasError())
            {
                //todolow improve error handling messages
                throw parser.GetErrors()[0].exception;
            }
            return visitor.stringBuilder.ToString();
        }
    }
}
