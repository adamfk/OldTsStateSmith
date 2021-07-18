using System;
using System.Collections.Generic;
using System.Text;
using Xunit;
using FluentAssertions;
using StateSmith.Compiler;

namespace StateSmithTest.InitialStateProcessor
{
    public class SimpleSimplification
    {
        Compiler compiler;
        Statemachine root;
        InitialState root_initial;
        State s1;
        State s2;
        InitialState s2_initial;
        State s2_1;

        public SimpleSimplification()
        {
            compiler = new Compiler();
            BuildTestGraph();
            compiler.rootVertices = new List<Vertex>() { root };
        }

        [Fact]
        public void Test1()
        {
            compiler.SetupRoots();
            s2.IncomingTransitions.Count.Should().Be(1);
            s2.IncomingTransitions[0].owningVertex.Should().Be(s1);
            s2.Children.Count.Should().Be(2);
            s2_1.IncomingTransitions.Count.Should().Be(1);

            compiler.SimplifyInitialStates();
            s2.IncomingTransitions.Count.Should().Be(0);
            s2_1.IncomingTransitions.Count.Should().Be(1);
            s2_1.IncomingTransitions[0].owningVertex.Should().Be(s1);

            s2.Children.Count.Should().Be(1);
        }

        private void BuildTestGraph()
        {
            root = new Statemachine(name: "root");
            root_initial = root.AddChild(new InitialState());
            {
                s1 = root.AddChild(new State(name: "s1"));

                s2 = root.AddChild(new State(name: "s2"));
                {
                    s2_1 = s2.AddChild(new State(name: "s2_1"));
                    s2_initial = s2.AddChild(new InitialState());
                }
            }

            root_initial.AddTransitionTo(s1);
            s1.AddTransitionTo(s2);
            s2_initial.AddTransitionTo(s2_1);
            s2_1.AddTransitionTo(s1);
        }
    }

    public class ValidationTests
    {
        Compiler compiler;
        InitialState initialStateVertex;
        State s1;

        public ValidationTests()
        {
            compiler = new Compiler();
            var sm = BuildTestGraph();
            compiler.rootVertices = new List<Vertex>() { sm };
        }

        private Vertex BuildTestGraph()
        {
            var sm = new Statemachine(name: "root");

            s1 = sm.AddChild(new State(name: "s1"));

            initialStateVertex = sm.AddChild(new InitialState());
            initialStateVertex.AddTransitionTo(s1);

            return sm;
        }

        [Fact]
        public void Children()
        {
            initialStateVertex.AddChild(new State("s100"));
            ExpectValidationException(exceptionMessagePart: "children");
        }

        [Fact]
        public void Parent()
        {
            initialStateVertex._parent = null;
            ExpectValidationException(exceptionMessagePart: "parent");
        }

        [Fact]
        public void SingleBehavior()
        {
            initialStateVertex.AddTransitionTo(s1);
            ExpectValidationException(exceptionMessagePart: "exactly one behavior");
        }

        [Fact]
        public void GuardCode()
        {
            initialStateVertex.Behaviors[0].guardCode = "some_code()";
            ExpectValidationException(exceptionMessagePart: "guard code");
        }

        [Fact]
        public void Trigger()
        {
            initialStateVertex.Behaviors[0].triggers.Add("do");
            ExpectValidationException(exceptionMessagePart: "trigger");
        }

        [Fact]
        public void IncomingTransitions()
        {
            s1.AddTransitionTo(initialStateVertex);
            ExpectValidationException(exceptionMessagePart: "incoming");
        }

        private void ExpectValidationException(string exceptionMessagePart)
        {
            compiler.SetupRoots();

            Action action = () => compiler.SimplifyInitialStates();
            action.Should().Throw<VertexValidationException>()
                .Where(e => e.Message.Contains(exceptionMessagePart));
        }
    }
}
