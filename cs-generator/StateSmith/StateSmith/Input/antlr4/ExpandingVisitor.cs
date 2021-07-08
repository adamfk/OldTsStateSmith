using Antlr4.Runtime.Misc;
using Antlr4.Runtime.Tree;
using StateSmith.Input.Expansions;
using System;
using System.Collections.Generic;
using System.Text;
using System.Linq;

namespace StateSmith.Input.antlr4
{
    public class FunctionArg
    {
        public string leading_ws;
        public string argument;
    }

    public class FunctionArgs
    {
        public List<FunctionArg> list = new List<FunctionArg>();
    }

    public class ExpandingVisitor : Grammar1BaseVisitor<string>
    {
        public Expander expander;

        private Stack<FunctionArgs> functionArgsStack = new Stack<FunctionArgs>();

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
            string result = (context.ohs()?.GetText() ?? "");
            string identifier = context.IDENTIFIER().GetText();
            identifier = expander.TryExpandVariableExpansion(identifier);
            result += (identifier);

            return result;
        }

        public override string VisitMember_function_call([NotNull] Grammar1Parser.Member_function_callContext context)
        {
            functionArgsStack.Push(new FunctionArgs());
            var result = base.VisitMember_function_call(context);
            functionArgsStack.Pop(); //unused here

            return result;
        }

        public override string VisitFunction_arg([NotNull] Grammar1Parser.Function_argContext context)
        {
            var arg = new FunctionArg
            {
                leading_ws = context.optional_any_space()?.GetText() ?? "",
                argument = ""
            };

            foreach (var code_element in context.code_element())
            {
                arg.argument += code_element.Accept(this);
            }

            functionArgsStack.Peek().list.Add(arg);

            return arg.leading_ws + arg.argument;
        }

        public override string VisitExpandable_function_call([NotNull] Grammar1Parser.Expandable_function_callContext context)
        {
            var result = context.ohs()?.Accept(this) ?? "";

            functionArgsStack.Push(new FunctionArgs());

            var identifier = context.expandable_identifier().GetText();
            if (expander.HasMethodName(identifier) == false)
            {
                result += identifier;
                result += context.braced_function_args().Accept(this);
                functionArgsStack.Pop();
            }
            else
            {
                context.braced_function_args().Accept(this);

                var args = functionArgsStack.Pop();

                var stringArgs = new string[args.list.Count];

                for (int i = 0; i < stringArgs.Length; i++)
                {
                    stringArgs[i] = args.list[i].argument;
                }

                var expandedCode = expander.TryExpandMethodExpansion(identifier, stringArgs);
                result += expandedCode;
            }

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
