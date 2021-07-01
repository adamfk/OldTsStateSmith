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

        public TextState Parse(string stateLabel)
        {
            //https://github.com/antlr/antlr4/blob/master/doc/csharp-target.md

            ICharStream stream = CharStreams.fromString(stateLabel);
            ITokenSource lexer = new Grammar1Lexer(stream);
            ITokenStream tokens = new CommonTokenStream(lexer);
            Grammar1Parser parser = new Grammar1Parser(tokens);
            parser.BuildParseTree = true;
            IParseTree tree = parser.state_defn();
            TextStateWalker walker = new TextStateWalker();
            ParseTreeWalker.Default.Walk(walker, tree);
            walker.textState.tree = tree;
            return walker.textState;
        }


        [Fact]
        public void StateNameOnly()
        {
            String input = @"
                SOME_SM_STATE_NAME
            ";
            var textState = Parse(input);
            textState.stateName.Should().Be("SOME_SM_STATE_NAME");
            textState.behaviors.Count.Should().Be(0);
        }

        [Fact]
        public void Test1()
        {
            //Console.SetOut(new ConsoleCaptureConverter(output));

            String input = @"
                SOME_SM_STATE_NAME
                11. MY_EVENT [some_guard( ""my }str with spaces"" ) && blah] / my_action();
            ";
            var textState = Parse(input);
            textState.stateName.Should().Be("SOME_SM_STATE_NAME");
            textState.behaviors.Count.Should().Be(1);
            textState.behaviors[0].order.Should().Be("11");
            textState.behaviors[0].triggers.Should().BeEquivalentTo(new string[] { "MY_EVENT" });
            textState.behaviors[0].guardCode.Should().Be(@"some_guard(""my }str with spaces"")&&blah");
            textState.behaviors[0].actionCode.Should().Be(@"my_action();");
        }


        [Fact]
        public void MultipleBehaviors()
        {
            String input = @"
                a_lowercase_state_name
                [ true ] / { }
                event / { action_code(123); }
            ";
            var textState = Parse(input);
            textState.stateName.Should().Be("a_lowercase_state_name");
            textState.behaviors.Count.Should().Be(2);
            textState.behaviors[0].order.Should().Be(null);
            textState.behaviors[0].triggers.Count.Should().Be(0);
            textState.behaviors[0].guardCode.Should().Be(@"true");
            textState.behaviors[0].actionCode.Should().Be(@"{}");

            textState.behaviors[1].order.Should().Be(null);
            textState.behaviors[1].triggers.Should().BeEquivalentTo(new string[] { "event" });
            textState.behaviors[1].guardCode.Should().Be(null);
            textState.behaviors[1].actionCode.Should().Be(@"{action_code(123);}");
        }

        [Fact]
        public void MultilineAction()
        {
            String input = @"
                $ORTHO_STATE
                event / { 
                  action_code(123);
                }
            ";
            var textState = Parse(input);
            textState.stateName.Should().Be("$ORTHO_STATE");
            textState.behaviors.Count.Should().Be(2);
            textState.behaviors[0].order.Should().Be(null);
            textState.behaviors[0].triggers.Count.Should().Be(0);
            textState.behaviors[0].guardCode.Should().Be(@"true");
            textState.behaviors[0].actionCode.Should().Be(@"{}");

            textState.behaviors[1].order.Should().Be(null);
            textState.behaviors[1].triggers.Should().BeEquivalentTo(new string[] { "event" });
            textState.behaviors[1].guardCode.Should().Be(null);
            textState.behaviors[1].actionCode.Should().Be(@"{action_code(123);}");
        }

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

        class TextStateWalker : Grammar1BaseListener
        {
            public TextState textState = new TextState();
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
                currentBehavior.order = context.order()?.order_number()?.GetText();
                currentBehavior.guardCode = context.guard()?.guard_code()?.GetText();
                currentBehavior.actionCode = context.action()?.action_code()?.GetText();
                textState.behaviors.Add(currentBehavior);
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
