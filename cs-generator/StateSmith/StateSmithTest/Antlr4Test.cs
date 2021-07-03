using System;
using System.Collections.Generic;
using System.Text;
using Xunit;
using FluentAssertions;
using Antlr4.Runtime;
using Antlr4.Runtime.Tree;
using Antlr4.Runtime.Misc;
using Xunit.Abstractions;
using StateSmith.Input.antlr4;

//todolow look into this: https://www.antlr.org/api/Java/org/antlr/v4/runtime/TokenStreamRewriter.html

namespace StateSmithTest
{
    public class Antlr4Test
    {
        ITestOutputHelper output;
        StateParser parser = new StateParser();

        public Antlr4Test(ITestOutputHelper output)
        {
            this.output = output;
        }

        [Fact]
        public void StateNameOnly()
        {
            string input = @"
                SOME_SM_STATE_NAME
            ";
            var textState = parser.ParseStateLabel(input);
            textState.stateName.Should().Be("SOME_SM_STATE_NAME");
            textState.behaviors.Count.Should().Be(0);
        }

        [Fact]
        public void Test1()
        {
            //Console.SetOut(new ConsoleCaptureConverter(output));

            string input = @"
                SOME_SM_STATE_NAME
                11. MY_EVENT [some_guard( ""my }str with spaces"" ) && blah] / my_action();
            ";
            var textState = parser.ParseStateLabel(input);
            textState.stateName.Should().Be("SOME_SM_STATE_NAME");
            textState.behaviors.Count.Should().Be(1);
            textState.behaviors[0].order.Should().Be("11");
            textState.behaviors[0].triggers.Should().BeEquivalentTo(new string[] { "MY_EVENT" });
            textState.behaviors[0].guardCode.Should().Be(@"some_guard( ""my }str with spaces"" ) && blah");
            textState.behaviors[0].actionCode.Should().Be("my_action();");
        }


        [Fact]
        public void MultipleBehaviors()
        {
            string input = @"
                a_lowercase_state_name
                [ true ] / { }
                event / { action_code(123); }
            ";
            var textState = parser.ParseStateLabel(input);
            textState.stateName.Should().Be("a_lowercase_state_name");
            textState.behaviors.Count.Should().Be(2);
            textState.behaviors[0].order.Should().Be(null);
            textState.behaviors[0].triggers.Count.Should().Be(0);
            textState.behaviors[0].guardCode.Should().Be(@"true");
            textState.behaviors[0].actionCode.Trim().Should().Be(@"");

            textState.behaviors[1].order.Should().Be(null);
            textState.behaviors[1].triggers.Should().BeEquivalentTo(new string[] { "event" });
            textState.behaviors[1].guardCode.Should().Be(null);
            textState.behaviors[1].actionCode.Trim().Should().Be(@"action_code(123);");
        }

        [Fact]
        public void MultilineAction()
        {
            string input = @"
                $ORTHO_STATE
                event / { 
                  action_code(123);
                }
            ";
            var textState = parser.ParseStateLabel(input);
            textState.stateName.Should().Be("$ORTHO_STATE");
            textState.behaviors.Count.Should().Be(1);
            textState.behaviors[0].order.Should().Be(null);
            textState.behaviors[0].triggers.Count.Should().Be(1);
            textState.behaviors[0].guardCode.Should().Be(null);
            textState.behaviors[0].actionCode.Trim().Should().Be("action_code(123);");
        }

        [Fact]
        public void DeIndentMultilineAction()
        {
            string input = @"
                $ORTHO_STATE
                event / { var += 3;
                    if (func(123))
                        stuff( func(8 * 2) );
                    if (true) {
                        a = ""hello there"";
                    }
                }
            ";
            var textState = parser.ParseStateLabel(input);
            textState.stateName.Should().Be("$ORTHO_STATE");
            textState.behaviors.Count.Should().Be(1);
            textState.behaviors[0].order.Should().Be(null);
            textState.behaviors[0].triggers.Count.Should().Be(1);
            textState.behaviors[0].guardCode.Should().Be(null);
            textState.behaviors[0].actionCode.Should().Be("var += 3;\r\n" +
                                                          "if (func(123))\r\n" +
                                                          "    stuff( func(8 * 2) );\r\n" +
                                                          "if (true) {\r\n" +
                                                          "    a = \"hello there\";\r\n" +
                                                          "}"
                                                          );
        }

        [Fact]
        public void EdgeDeIndentMultilineAction()
        {
            string input = @"
                event / { var += 3;
                    if (func(123))
                        stuff( func(8 * 2) );
                    if (true) {
                        a = ""hello there"";
                    }
                }
            ";
            var behaviors = parser.ParseEdgeLabel(input);
            behaviors.Count.Should().Be(1);
            behaviors[0].order.Should().Be(null);
            behaviors[0].triggers.Count.Should().Be(1);
            behaviors[0].guardCode.Should().Be(null);
            behaviors[0].actionCode.Should().Be("var += 3;\r\n" +
                                                "if (func(123))\r\n" +
                                                "    stuff( func(8 * 2) );\r\n" +
                                                "if (true) {\r\n" +
                                                "    a = \"hello there\";\r\n" +
                                                "}"
                                                );
        }
    }
}
