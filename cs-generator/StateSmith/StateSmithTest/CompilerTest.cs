using System;
using System.Collections.Generic;
using System.Text;
using Xunit;
using FluentAssertions;
using StateSmith;
using StateSmith.Compiler;
using StateSmith.compiler;
using StateSmith.Input.Expansions;

namespace StateSmithTest
{
    public class CompilerTest
    {
        [Fact]
        public void Tiny1()
        {
            const string filepath = "../../../../../../examples/specifications/Tiny1.graphml";

            Compiler compiler = new Compiler();
            compiler.CompileFile(filepath);

            compiler.rootVertices.Count.Should().Be(2);

            var sm = (Statemachine)compiler.rootVertices[0];

            var Tiny1 = compiler.GetVertex("Tiny1");
            var A = compiler.GetVertex("A");
            var B = compiler.GetVertex("B");
            var C2 = compiler.GetVertex("C2");

            ///////////
            Tiny1.name.Should().Be("Tiny1");
            Tiny1.yedId.Should().Be("n0");
            Tiny1.children.Count.Should().Be(4);
            Tiny1.behaviors.Should().BeEmpty();

            ////////////
            var Tiny1InitialState = Tiny1.ChildType<InitialState>();
            Tiny1InitialState.children.Should().BeEmpty();
            Tiny1InitialState.yedId.Should().Be("n0::n1");
            Tiny1InitialState.behaviors.Should().BeEquivalentTo(
                new List<Behavior>()
                {
                    new Behavior()
                    {
                        transitionTarget = A,
                        actionCode = "initial_action();"
                    }
                }
            );

            ////////////
            Tiny1.Child("A").Should().Be(A);
            A.children.Should().BeEmpty();
            A.yedId.Should().Be("n0::n0");
            A.behaviors.Should().BeEquivalentTo(
                new List<Behavior>()
                {
                    new Behavior()
                    {
                        triggers = new List<string>(){ "enter" },
                        actionCode = "a_count += 1;"
                    },
                    new Behavior()
                    {
                        triggers = new List<string>(){ "EVENT1" },
                        transitionTarget = B
                    },
                }
            );

            ////////////
            Tiny1.Child("B").Should().Be(B);
            B.children.Should().BeEmpty();
            B.yedId.Should().Be("n0::n2");
            B.behaviors.Should().BeEquivalentTo(
                new List<Behavior>()
                {
                    new Behavior()
                    {
                        triggers = new List<string>() { "exit" },
                        actionCode = "b_exit();",
                    },
                    new Behavior()
                    {
                        triggers = new List<string>() { "EVENT2" },
                        guardCode = "some_guard(200)",
                        transitionTarget = C2
                    }
                }
            );


            ////////////
            Tiny1.Child("C2").Should().Be(C2);
            C2.children.Should().BeEmpty();
            C2.yedId.Should().Be("n0::n3");
            C2.behaviors.Should().BeEquivalentTo(
                new List<Behavior>()
                {
                    new Behavior()
                    {
                        triggers = new List<string>() { "EVENT2" },
                        actionCode = "set_mode(SAUCEY);",
                    },
                    new Behavior()
                    {
                        triggers = new List<string>() { "EVENT1" },
                        order = 1,
                        transitionTarget = A
                    }
                }
            );
        }


        #pragma warning disable IDE1006 // Naming Styles
        #pragma warning disable IDE0051 // Remove unused private members
        #pragma warning disable RCS1018 // Add accessibility modifiers (or vice versa).
        #pragma warning disable RCS1213 // Remove unused member declaration.
        #pragma warning disable IDE0044 // Add readonly modifier
        private class Tiny1Expansions : UserExpansionScriptBase
        {
            string a_count => AutoVarName;

            string set_mode(string mode) => $"set_mode(MODE_{mode})";

            string some_guard(string count)
            {
                int int_count = int.Parse(count);

                if (int_count > 100)
                {
                    int_count += 1000;
                }

                return $"some_guard({int_count})";
            }

            string b_exit()
            {
                return "b_exit_count++";
            }
        }
        #pragma warning restore IDE1006 // Naming Styles
        #pragma warning restore IDE0051 // Remove unused private members
        #pragma warning restore IDE0044 // Add readonly modifier
        #pragma warning restore RCS1018 // Add accessibility modifiers (or vice versa).
        #pragma warning restore RCS1213 // Remove unused member declaration.


        [Fact]
        public void ExpandedTiny1()
        {
            const string filepath = "../../../../../../examples/specifications/Tiny1.graphml";

            Compiler compiler = new Compiler();
            var expander = new Expander();
            ExpanderFileReflection expanderFileReflection = new ExpanderFileReflection(expander);
            expanderFileReflection.AddAllExpansions(new Tiny1Expansions());
            compiler.CompileFile(filepath);
            compiler.ExpandAllBehaviors(expander);

            compiler.rootVertices.Count.Should().Be(2);

            var sm = (Statemachine)compiler.rootVertices[0];

            var Tiny1 = compiler.GetVertex("Tiny1");
            var A = compiler.GetVertex("A");
            var B = compiler.GetVertex("B");
            var C2 = compiler.GetVertex("C2");

            ///////////
            Tiny1.name.Should().Be("Tiny1");
            Tiny1.yedId.Should().Be("n0");
            Tiny1.children.Count.Should().Be(4);
            Tiny1.behaviors.Should().BeEmpty();

            ////////////
            var Tiny1InitialState = Tiny1.ChildType<InitialState>();
            Tiny1InitialState.children.Should().BeEmpty();
            Tiny1InitialState.yedId.Should().Be("n0::n1");
            Tiny1InitialState.behaviors.Should().BeEquivalentTo(
                new List<Behavior>()
                {
                    new Behavior()
                    {
                        transitionTarget = A,
                        actionCode = "initial_action();"
                    }
                }
            );

            ////////////
            Tiny1.Child("A").Should().Be(A);
            A.children.Should().BeEmpty();
            A.yedId.Should().Be("n0::n0");
            A.behaviors.Should().BeEquivalentTo(
                new List<Behavior>()
                {
                    new Behavior()
                    {
                        triggers = new List<string>(){ "enter" },
                        actionCode = "sm->vars.a_count += 1;"
                    },
                    new Behavior()
                    {
                        triggers = new List<string>(){ "EVENT1" },
                        transitionTarget = B
                    },
                }
            );

            ////////////
            Tiny1.Child("B").Should().Be(B);
            B.children.Should().BeEmpty();
            B.yedId.Should().Be("n0::n2");
            B.behaviors.Should().BeEquivalentTo(
                new List<Behavior>()
                {
                    new Behavior()
                    {
                        triggers = new List<string>() { "exit" },
                        actionCode = "b_exit_count++;",
                    },
                    new Behavior()
                    {
                        triggers = new List<string>() { "EVENT2" },
                        guardCode = "some_guard(1200)",
                        transitionTarget = C2
                    }
                }
            );


            ////////////
            Tiny1.Child("C2").Should().Be(C2);
            C2.children.Should().BeEmpty();
            C2.yedId.Should().Be("n0::n3");
            C2.behaviors.Should().BeEquivalentTo(
                new List<Behavior>()
                {
                    new Behavior()
                    {
                        triggers = new List<string>() { "EVENT2" },
                        actionCode = "set_mode(MODE_SAUCEY);",
                    },
                    new Behavior()
                    {
                        triggers = new List<string>() { "EVENT1" },
                        order = 1,
                        transitionTarget = A
                    }
                }
            );
        }
    }
}
