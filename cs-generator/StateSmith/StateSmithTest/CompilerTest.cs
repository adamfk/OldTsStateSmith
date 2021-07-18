using System;
using System.Collections.Generic;
using System.Text;
using Xunit;
using FluentAssertions;
using StateSmith;
using StateSmith.Compiler;
using StateSmith.compiler;
using static StateSmithTest.VertexTestHelpers;
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
            Tiny1.DiagramId.Should().Be("n0");
            Tiny1.Children.Count.Should().Be(4);
            Tiny1.Behaviors.Should().BeEmpty();

            ////////////
            var Tiny1InitialState = Tiny1.ChildType<InitialState>();
            Tiny1InitialState.Children.Should().BeEmpty();
            Tiny1InitialState.DiagramId.Should().Be("n0::n1");
            {
                var owner = Tiny1InitialState;
                var behaviors = owner.Behaviors;
                behaviors.Count.Should().Be(1);
                behaviors[0].ShouldBeExactly(owningVertex: owner, transitionTarget: A, actionCode: "initial_action();");
            }


            ////////////
            Tiny1.Child("A").Should().Be(A);
            A.Children.Should().BeEmpty();
            A.DiagramId.Should().Be("n0::n0");
            {
                var owner = A;
                var behaviors = owner.Behaviors;
                behaviors.Count.Should().Be(2);
                behaviors[0].ShouldBeExactly(owningVertex: owner, triggers: TriggerList("enter"), actionCode: "a_count += 1;");
                behaviors[1].ShouldBeExactly(owningVertex: owner, transitionTarget: B, triggers: TriggerList("EVENT1"));
            }

            ////////////
            Tiny1.Child("B").Should().Be(B);
            B.Children.Should().BeEmpty();
            B.DiagramId.Should().Be("n0::n2");
            {
                var owner = B;
                var behaviors = owner.Behaviors;
                behaviors.Count.Should().Be(2);
                behaviors[0].ShouldBeExactly(owningVertex: owner, triggers: new List<string>() { "exit" }, actionCode: "b_exit();");
                behaviors[1].ShouldBeExactly(owningVertex: owner, transitionTarget: C2, triggers: TriggerList("EVENT2"), guardCode: "some_guard(200)");
            }


            ////////////
            Tiny1.Child("C2").Should().Be(C2);
            C2.Children.Should().BeEmpty();
            C2.DiagramId.Should().Be("n0::n3");
            {
                var owner = C2;
                var behaviors = owner.Behaviors;
                behaviors.Count.Should().Be(2);
                behaviors[0].ShouldBeExactly(owningVertex: owner, triggers: new List<string>() { "EVENT2" }, actionCode: "set_mode(SAUCEY);");
                behaviors[1].ShouldBeExactly(owningVertex: owner, transitionTarget: A, triggers: TriggerList("EVENT1"), order: 1);
            }
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
            Tiny1Expansions userExpansions = new Tiny1Expansions();
            userExpansions.varsPath = "sm->vars.";
            expanderFileReflection.AddAllExpansions(userExpansions);
            //FIXME add events
            //FIXME check for valid events in diagram
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
            Tiny1.DiagramId.Should().Be("n0");
            Tiny1.Children.Count.Should().Be(4);
            Tiny1.Behaviors.Should().BeEmpty();

            ////////////
            var Tiny1InitialState = Tiny1.ChildType<InitialState>();
            Tiny1InitialState.Children.Should().BeEmpty();
            Tiny1InitialState.DiagramId.Should().Be("n0::n1");
            {
                var owner = Tiny1InitialState;
                var behaviors = owner.Behaviors;
                behaviors.Count.Should().Be(1);
                behaviors[0].ShouldBeExactly(owningVertex: owner, transitionTarget: A, actionCode: "initial_action();");
            }


            ////////////
            Tiny1.Child("A").Should().Be(A);
            A.Children.Should().BeEmpty();
            A.DiagramId.Should().Be("n0::n0");
            {
                var owner = A;
                var behaviors = owner.Behaviors;
                behaviors.Count.Should().Be(2);
                behaviors[0].ShouldBeExactly(owningVertex: owner, triggers: TriggerList("enter"), actionCode: "sm->vars.a_count += 1;");
                behaviors[1].ShouldBeExactly(owningVertex: owner, transitionTarget:B, triggers: TriggerList("EVENT1"));
            }

            ////////////
            Tiny1.Child("B").Should().Be(B);
            B.Children.Should().BeEmpty();
            B.DiagramId.Should().Be("n0::n2");
            {
                var owner = B;
                var behaviors = owner.Behaviors;
                behaviors.Count.Should().Be(2);
                behaviors[0].ShouldBeExactly(owningVertex: owner, triggers: new List<string>() { "exit" }, actionCode: "b_exit_count++;");
                behaviors[1].ShouldBeExactly(owningVertex: owner, transitionTarget: C2, triggers: TriggerList("EVENT2"), guardCode: "some_guard(1200)");
            }


            ////////////
            Tiny1.Child("C2").Should().Be(C2);
            C2.Children.Should().BeEmpty();
            C2.DiagramId.Should().Be("n0::n3");
            {
                var owner = C2;
                var behaviors = owner.Behaviors;
                behaviors.Count.Should().Be(2);
                behaviors[0].ShouldBeExactly(owningVertex: owner, triggers: new List<string>() { "EVENT2" }, actionCode: "set_mode(MODE_SAUCEY);");
                behaviors[1].ShouldBeExactly(owningVertex: owner, transitionTarget: A, triggers: TriggerList("EVENT1"), order: 1);
            }
        }
    }
}
