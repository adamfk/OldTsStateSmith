using StateSmith.Input.antlr4;
using StateSmith.Input.Expansions;
using StateSmith.Input.Yed;
using System;
using System.Collections.Generic;
using System.Text;

namespace StateSmith.Compiler
{
    public class Compiler
    {
        public List<Vertex> rootVertices = new List<Vertex>();

        public void CompileFile(string filepath)
        {
            YedParser yedParser = new YedParser();
            Expander expander = new Expander();

            yedParser.Parse(filepath);
            List<Input.DiagramNode> rootNodes = yedParser.GetRootNodes();
            List<Input.DiagramEdge> edges = yedParser.GetEdges();

            foreach (var node in rootNodes)
            {
                rootVertices.Add(ProcessNode(node, parentVertex: null));
            }
        }

        private Vertex ProcessNode(Input.DiagramNode diagramNode, Vertex parentVertex)
        {
            if (diagramNode.label == null || diagramNode.label.Trim() == "")
            {
                return null;
            }

            LabelParser labelParser = new LabelParser();
            Node node = labelParser.ParseNodeLabel(diagramNode.label);
            Vertex thisVertex;

            switch (node)
            {
                default:
                    throw new ArgumentException("Unknown node: " + node);

                case NotesNode notesNode:
                    {
                        var noteVertex = new NotesVertex();
                        noteVertex.notes = notesNode.notes;
                        thisVertex = noteVertex;
                        break;
                    }

                case StateMachineNode stateMachineNode:
                    {
                        var sm = new Statemachine();
                        sm.name = stateMachineNode.name;
                        sm.nameIsGloballyUnique = true;
                        thisVertex = sm;
                        break;
                    }

                case StateNode stateNode:
                    {
                        if (stateNode is OrthoStateNode orthoStateNode)
                        {
                            var orthoState = new OrthoState();
                            thisVertex = orthoState;
                            orthoState.order = Double.Parse(orthoStateNode.order);
                            SetStateFromStateNode(stateNode, orthoState);
                        }
                        else
                        {
                            if (string.Equals(stateNode.stateName, "$initial_state", StringComparison.OrdinalIgnoreCase))
                            {
                                thisVertex = new InitialState();
                            }
                            else
                            {
                                var state = new State();
                                thisVertex = state;
                                SetStateFromStateNode(stateNode, state);
                            }
                        }

                        break;
                    }
            }

            thisVertex.yedId = diagramNode.id;

            if (parentVertex != null)
            {
                thisVertex.parent = parentVertex;
                parentVertex.children.Add(thisVertex);
            }

            foreach (var child in diagramNode.children)
            {
                ProcessNode(child, thisVertex);
            }

            return thisVertex;
        }

        private static void SetStateFromStateNode(StateNode stateNode, State state)
        {
            state.name = stateNode.stateName;
            state.nameIsGloballyUnique = stateNode.stateNameIsGlobal;
        }
    }
}
