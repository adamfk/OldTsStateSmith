using System;
using System.Collections.Generic;
using System.Text;
using Xunit;
using FluentAssertions;
using StateSmith;
using StateSmith.Compiler;
using StateSmith.compiler;

namespace StateSmithTest
{
    public class CompilerTest
    {
        private void AssertNode()
        {

        }

        [Fact]
        public void Test()
        {
            string filepath = "../../../../../../examples/specifications/Tiny1.graphml";

            Compiler compiler = new Compiler();
            compiler.CompileFile(filepath);

            compiler.rootVertices.Count.Should().Be(2);

            var sm = (Statemachine)compiler.rootVertices[0];

            var Tiny1 = compiler.GetVertex("Tiny1");
            var A = compiler.GetVertex("A");
            var B = compiler.GetVertex("B");
            var C2 = compiler.GetVertex("C2");

            Tiny1.name.Should().Be("Tiny1");
            Tiny1.children.Count.Should().Be(4);
            Tiny1.Child("A").Should().Be(A);
            Tiny1.Child("B").Should().Be(B);
            Tiny1.Child("C2").Should().Be(C2);

            Tiny1.yedId.Should().Be("n0");

            var Tiny1InitialState = Tiny1.ChildType<InitialState>();
            Tiny1InitialState.children.Count.Should().Be(0);
            Tiny1InitialState.yedId.Should().Be("n0::n1");
            Tiny1InitialState.behaviors.Should().BeEquivalentTo(
                new List<Behavior>()
                {
                    new Behavior()
                    {
                        transitionTarget = A,
                        actionCode = "initial_action();"
                    }
                });

            var expected = new Statemachine()
            {
                name = "Tiny1",
                nameIsGloballyUnique = true,
                yedId = "n0",
                children = new List<Vertex>()
                {
                    new InitialState()
                    {

                    },
                    new State()
                    {
                        name = "A",
                        yedId = "n0::n0",
                        behaviors = new List<Behavior>()
                        {
                            new Behavior()
                            {
                                triggers = new List<string>()
                                {
                                    "enter"
                                },
                                actionCode = "a_entry();",
                            },
                            new Behavior()
                            {
                                triggers = new List<string>()
                                {
                                    "EVENT1"
                                },
                                transitionTarget = B
                            }
                        }
                    },
                    new State()
                    {
                        name = "B",
                        yedId = "n0::n2",
                        behaviors = new List<Behavior>()
                        {
                            new Behavior()
                            {
                                triggers = new List<string>()
                                {
                                    "exit"
                                },
                                actionCode = "b_entry();",
                            },
                            new Behavior()
                            {
                                triggers = new List<string>()
                                {
                                    "EVENT2"
                                },
                                guardCode = "some_guard(123)",
                                transitionTarget = C2
                            }
                        }
                    },
                   new State()
                    {
                        name = "C2",
                        yedId = "n0::n3",
                        behaviors = new List<Behavior>()
                        {
                            new Behavior()
                            {
                                triggers = new List<string>()
                                {
                                    "EVENT2"
                                },
                                actionCode = "event2_stuff(\"abc\");",
                            },
                            new Behavior()
                            {
                                triggers = new List<string>()
                                {
                                    "EVENT11"
                                },
                                order = 1,
                                transitionTarget = A
                            }
                        }
                    }
                }
            };

        }
    }
}
