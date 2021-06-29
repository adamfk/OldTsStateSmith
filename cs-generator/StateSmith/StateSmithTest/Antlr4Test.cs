using System;
using System.Collections.Generic;
using System.Text;
using Xunit;
using FluentAssertions;
using Antlr4.Runtime;
using Antlr4.Runtime.Tree;
using Antlr4.Runtime.Misc;
using Xunit.Abstractions;

namespace StateSmithTest
{
    public class Antlr4Test
    {
        ITestOutputHelper output;

        public Antlr4Test(ITestOutputHelper output)
        {
            this.output = output;
        }

        [Fact]
        public void Test1()
        {
            Console.SetOut(new ConsoleCaptureConverter(output));

            //https://github.com/antlr/antlr4/blob/master/doc/csharp-target.md
            String input = @"
                SOME_SM_STATE_NAME
                1. MY_EVENT [some_guard(""my str with spaces"")] / my_action();
            ";
            ICharStream stream = CharStreams.fromString(input);
            ITokenSource lexer = new Grammar1Lexer(stream);
            ITokenStream tokens = new CommonTokenStream(lexer);
            Grammar1Parser parser = new Grammar1Parser(tokens);
            parser.BuildParseTree = true;
            IParseTree tree = parser.state_defn();
            KeyPrinter printer = new KeyPrinter();
            ParseTreeWalker.Default.Walk(printer, tree);
        }

        public class TextBehavior
        {
            public List<string> triggers = new List<string>();
            public string order = "";
            public string guardCode = "";
            public string actionCode = "";
        }

        class KeyPrinter : Grammar1BaseListener
        {

            public string stateName;
            public List<TextBehavior> behaviors = new List<TextBehavior>();
            TextBehavior currentBehavior;

            public override void EnterEveryRule([NotNull] ParserRuleContext context)
            {
                //Console.WriteLine("EnterEveryRule: " + context);
            }


            public override void ExitEveryRule([NotNull] ParserRuleContext context)
            {
                //Console.WriteLine("ExitEveryRule: " + context);
            }


            public override void EnterState_name([NotNull] Grammar1Parser.State_nameContext context)
            {
                stateName = context.IDENTIFIER().GetText();
            }

            public override void EnterBehavior([NotNull] Grammar1Parser.BehaviorContext context)
            {
                // Skip empty behaviors which may occur because the grammar is allowed to be fully optional: `behavior: order? triggers? guard? action? ;`
                if (context.ChildCount == 0)
                {
                    return;
                }

                currentBehavior = new TextBehavior();
                currentBehavior.order = context.order()?.order_number()?.GetText();
                currentBehavior.guardCode = context.guard()?.GetText();
                currentBehavior.actionCode = context.action()?.action_code()?.GetText();
                behaviors.Add(currentBehavior);
            }

            public override void EnterTrigger_id([NotNull] Grammar1Parser.Trigger_idContext context)
            {
                currentBehavior.triggers.Add(context.IDENTIFIER().GetText());
            }

            public override void ExitBehavior([NotNull] Grammar1Parser.BehaviorContext context)
            {
                currentBehavior = null;
            }
        }
    }
}
