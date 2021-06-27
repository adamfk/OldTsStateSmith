using StateSmith;
using StateSmith.Input.Yed;
using StateSmith.Input;
using System;
using System.IO;
using System.Text;
using Xunit;
using Xunit.Abstractions;
using FluentAssertions;
using System.Collections.Generic;

namespace StateSmithTest
{
    public class YedParserTest
    {
        ITestOutputHelper output;
        Dictionary<string, DiagramNode> nodeMap;
        YedParser parser = new YedParser();


        public YedParserTest(ITestOutputHelper output)
        {
            this.output = output;
        }

        private string filepath = "../../../../../../examples/1/ExampleSm.graphml";

        private DiagramNode GetAndAssertNode(string id, string label, int childCount, DiagramNode parent)
        {
            var node = nodeMap[id];
            node.label.Trim().Should().Be(label);
            node.children.Count.Should().Be(childCount);
            node.parent.Should().Be(parent);

            if (parent != null)
            {
                parent.children.Should().Contain(node);
            }

            return node;
        }

        private DiagramEdge BuildEdge(string id, DiagramNode source, DiagramNode target, string label)
        {
            return new DiagramEdge()
            {
                id = id,
                source = source,
                target = target,
                label = label
            };
        }

        [Fact]
        public void TestParser()
        {
            //handy when printing what nodes are
            //var converter = new ConsoleCaptureConverter(output);
            //Console.SetOut(converter);

            parser.Parse(filepath);
            nodeMap = parser.nodeMap;

            var root = GetAndAssertNode(id: "n0", label: "$STATEMACHINE: ExampleSm", childCount: 3, parent: null);

            var orthoRepeat = GetAndAssertNode(id: "n0::n0", label: "$ORTHO : REPEAT", childCount: 3, parent: root);
            var orthoBasic = GetAndAssertNode(id: "n0::n1", label: "$ORTHO 1 : BASIC", childCount: 3, parent: root);
            var genInclude = GetAndAssertNode(id: "n0::n2", label: "$GEN_INCLUDE : \"./ExampleSm.def\"", childCount: 0, parent: root);

            var notHeld = GetAndAssertNode(id: "n0::n0::n0", label:"NOT_HELD", childCount: 0, parent: orthoRepeat);
            var beingHeld = GetAndAssertNode(id: "n0::n0::n2", label:"BEING_HELD", childCount: 0, parent: orthoRepeat);
            var repeatInitialState = GetAndAssertNode(id: "n0::n0::n1", label: "$initial_state", childCount: 0, parent: orthoRepeat);

            var orthoBasicInitial = GetAndAssertNode(id: "n0::n1::n1", label: "$initial_state", childCount: 0, parent: orthoBasic);

            var notPressed = GetAndAssertNode(id: "n0::n1::n0", label: @"NOT_PRESSED
enter / debounced_at_ms = current_time_ms + 100
enter / time_held = 0", childCount: 0, parent: orthoBasic);

            var pressed = GetAndAssertNode(id: "n0::n1::n2", label: @"PRESSED
enter / output_event( PRESSED ); 
enter / is_pressed = true;
exit / output_event( RELEASED );
exit / is_pressed = false;", childCount: 2, parent: orthoBasic);

            var initialPress = GetAndAssertNode(id: "n0::n1::n2::n0", label: @"INITIAL_PRESS
entry / debounced_at_ms = current_time_ms + 100", childCount: 0, parent: pressed);

            var held = GetAndAssertNode(id: "n0::n1::n2::n1", label: @"HELD
enter / output_event( HELD ); 
enter / is_held = true;
do / time_held = time_in_state
exit / is_held = false;
exit / time_held = 0;", childCount: 2, parent: pressed);

            var heldInitialState = GetAndAssertNode(id: "n0::n1::n2::n1::n1", label: @"$initial_state", childCount: 0, parent: held);
            
            var heldLong = GetAndAssertNode(id: "n0::n1::n2::n1::n0", label: @"HELD_LONG
entry / output_event( HELD_LONG );
entry / is_held_long = true;
exit   / is_held_long = false;", childCount: 0, parent: held);

            ///////// test expected edges

            var expectedEdges = new List<DiagramEdge>()
            {
                BuildEdge("n0::n0::e0", source: notHeld, target: beingHeld, "[is_held]"),
                BuildEdge("n0::n0::e1", source: repeatInitialState, target: notHeld, ""),
                BuildEdge("n0::n0::e2", source: beingHeld, target: notHeld, "[is_not_pressed]"),
                BuildEdge("n0::n0::e3", source: beingHeld, target: beingHeld, "afterMs( repeat_delay ) / output_event( HELD_REPEAT )"),
                BuildEdge("n0::n1::e0", source: notPressed, target: initialPress, "[input == 1 && is_debounced( ) ]"),
                BuildEdge("n0::n1::e1", source: orthoBasicInitial, target: notPressed, ""),
                BuildEdge("n0::n1::e2", source: pressed, target: notPressed, "[input == 0 && is_debounced( ) ] "),
                BuildEdge("n0::n1::n2::e0", source: initialPress, target: held, "afterMs(500)"),
                BuildEdge("n0::n1::e3", source: initialPress, target: notPressed, @"[input == 0 && is_debounced( ) ] 
/ output_event(PRETAP)"),
                BuildEdge("n0::n1::n2::n1::e0", source: heldInitialState, target: heldLong, "afterMs(500)"),
            };

            parser.edges.Should().BeEquivalentTo(expectedEdges);
        }
    }

}
