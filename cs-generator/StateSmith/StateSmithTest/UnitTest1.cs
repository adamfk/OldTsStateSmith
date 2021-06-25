using StateSmith;
using StateSmith.Yed;
using System;
using System.IO;
using System.Text;
using Xunit;
using Xunit.Abstractions;
using FluentAssertions;
using System.Collections.Generic;

namespace StateSmithTest
{
    public class UnitTest1
    {
        ITestOutputHelper output;
        Dictionary<string, YedNode> nodeMap;
        YedParser parser = new YedParser();


        public UnitTest1(ITestOutputHelper output)
        {
            this.output = output;
        }

        string filepath = "../../../../../../examples/1/ExampleSm.graphml";

        private YedNode GetAndAssertNode(string id, string label, int childCount, YedNode parent)
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

        [Fact]
        public void TestParser()
        {
            //handy when printing what nodes are
            //var converter = new ConsoleCaptureConverter(output);
            //Console.SetOut(converter);

            parser.Parse(filepath);
            nodeMap = parser.nodeMap;

            var root = GetAndAssertNode(id: "n0", label: "$STATEMACHINE: ExampleSm", childCount: 3, parent: null);

            var ortho_repeat = GetAndAssertNode(id: "n0::n0", label: "$ORTHO : REPEAT", childCount: 3, parent: root);
            var ortho_basic = GetAndAssertNode(id: "n0::n1", label: "$ORTHO 1 : BASIC", childCount: 3, parent: root);
            var gen_include = GetAndAssertNode(id: "n0::n2", label: "$GEN_INCLUDE : \"./ExampleSm.def\"", childCount: 0, parent: root);

            var not_held = GetAndAssertNode(id: "n0::n0::n0", label:"NOT_HELD", childCount: 0, parent: ortho_repeat);
            var being_held = GetAndAssertNode(id: "n0::n0::n2", label:"BEING_HELD", childCount: 0, parent: ortho_repeat);
            var repeatInitialState = GetAndAssertNode(id: "n0::n0::n1", label: "$initial_state", childCount: 0, parent: ortho_repeat);

            var orthoBasicInitial = GetAndAssertNode(id: "n0::n1::n1", label: "$initial_state", childCount: 0, parent: ortho_basic);

            var notPressed = GetAndAssertNode(id: "n0::n1::n0", label: @"NOT_PRESSED
enter / debounced_at_ms = current_time_ms + 100
enter / time_held = 0", childCount: 0, parent: ortho_basic);

            var pressed = GetAndAssertNode(id: "n0::n1::n2", label: @"PRESSED
enter / output_event( PRESSED ); 
enter / is_pressed = true;
exit / output_event( RELEASED );
exit / is_pressed = false;", childCount: 2, parent: ortho_basic);

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
        }
    }

}
