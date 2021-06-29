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
                MY_EVENT [some_guard()] / my_action();
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

        class KeyPrinter : Grammar1BaseListener
        {
            public override void EnterEveryRule([NotNull] ParserRuleContext context)
            {
                Console.WriteLine("EnterEveryRule: " + context);
            }


            public override void ExitEveryRule([NotNull] ParserRuleContext context)
            {
                Console.WriteLine("ExitEveryRule: " + context);
            }


            public override void EnterState_name([NotNull] Grammar1Parser.State_nameContext context)
            {
                Console.WriteLine("EnterState_name: " + context);
            }

            public override void ExitState_name([NotNull] Grammar1Parser.State_nameContext context)
            {
                Console.WriteLine("ExitState_name: " + context);
            }
        }
    }
}
