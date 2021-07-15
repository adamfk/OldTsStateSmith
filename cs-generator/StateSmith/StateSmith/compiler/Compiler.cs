using StateSmith.Input.antlr4;
using StateSmith.Input.Expansions;
using StateSmith.Input.Yed;
using System;
using System.Collections.Generic;
using System.Text;
using System.Text.RegularExpressions;
using System.Linq;
using StateSmith.compiler;

namespace StateSmith.Compiler
{
    public class Compiler
    {
        public List<Vertex> rootVertices = new List<Vertex>();
        private List<string> eventNames = new List<string>();
        private Dictionary<Input.DiagramNode, Vertex> diagramVertexMap = new Dictionary<Input.DiagramNode, Vertex>();

        public void CompileFile(string filepath)
        {
            YedParser yedParser = new YedParser();

            yedParser.Parse(filepath);

            foreach (var node in yedParser.GetRootNodes())
            {
                rootVertices.Add(ProcessNode(node, parentVertex: null));
            }

            foreach (var edge in yedParser.GetEdges())
            {
                ProcessEdge(edge);
            }

            foreach (var v in rootVertices)
            {
                if (v is NamedVertex namedVertex)
                {
                    SetupDescendants(namedVertex);
                }
            }
        }

        public void AddEventName(string eventName)
        {
            // Don't worry about O(N) look up cost for now as we don't expect many events.
            if (eventNames.Contains(eventName) == false)
            {
                eventNames.Add(eventName);
            }
        }

        public List<NamedVertex> GetNamedVertices(string name)
        {
            return rootVertices.Descendants(name);
        }

        public NamedVertex GetVertex(string name)
        {
            return rootVertices.Descendant(name);
        }

        private void ProcessEdge(Input.DiagramEdge edge)
        {
            var sourceVertex = diagramVertexMap[edge.source];
            var targetVertex = diagramVertexMap[edge.target];

            LabelParser labelParser = new LabelParser();
            List<NodeBehavior> nodeBehaviors = labelParser.ParseEdgeLabel(edge.label);

            PrintAndThrowIfEdgeParseFail(edge, sourceVertex, targetVertex, labelParser);

            if (nodeBehaviors.Count == 0)
            {
                sourceVertex.behaviors.Add(new Behavior()
                {
                    transitionTarget = targetVertex
                });
            }

            foreach (var nodeBehavior in nodeBehaviors)
            {
                var behavior = ConvertBehavior(nodeBehavior);
                behavior.transitionTarget = targetVertex;
                sourceVertex.behaviors.Add(behavior);

                //FIXME I believe this code will fail if there is an edge to an unrecognized diagram node like an image.
            }
        }

        private static void PrintAndThrowIfEdgeParseFail(Input.DiagramEdge edge, Vertex sourceVertex, Vertex targetVertex, LabelParser labelParser)
        {
            if (labelParser.HasError())
            {
                string fromString = VertexPathDescriber.Describe(sourceVertex);
                string toString = VertexPathDescriber.Describe(targetVertex);
                string fullMessage = $"Failed parsing edge from `{fromString}` to `{toString}`. Diagram id:{edge.id}.\n";
                foreach (var error in labelParser.GetErrors())
                {
                    fullMessage += error.BuildMessage() + "\n";
                }

                Console.WriteLine(fullMessage);

                throw new ArgumentException(fullMessage);
            }
        }

        private static void VisitVertices<T>(Vertex vertex, Action<T> action) where T : Vertex
        {
            if (typeof(T).IsAssignableFrom(vertex.GetType()))
            {
                action((T)vertex);
            }

            foreach (var child in vertex.children)
            {
                VisitVertices<T>(child, action);
            }
        }

        private static void SetupDescendants(NamedVertex parentVertex)
        {
            VisitVertices<NamedVertex>(parentVertex, vertex => {
                //add this vertex to ancestors
                var parent = vertex.parent;
                while (parent != null)
                {
                    parent.namedDescendants.AddIfMissing(vertex.name, vertex);
                    parent = parent.parent;
                }
            });
        }

        public static void ExpandBehavior(Expander expander, Behavior behavior)
        {
            if (behavior.actionCode != null)
            {
                behavior.actionCode = ExpandingVisitor.ParseAndExpandCode(expander, behavior.actionCode);
            }

            if (behavior.guardCode != null)
            {
                behavior.guardCode = ExpandingVisitor.ParseAndExpandCode(expander, behavior.guardCode);
            }
        }

        public void ExpandAllBehaviors(Expander expander)
        {
            foreach (var root in rootVertices)
            {
                VisitVertices<Vertex>(root, vertex => {
                    foreach (var behavior in vertex.behaviors)
                    {
                        ExpandBehavior(expander, behavior);
                    }
                });
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
            PrintAndThrowIfNodeParseFail(diagramNode, parentVertex, labelParser);

            Vertex thisVertex;
            bool visitChildren = true;

            switch (node)
            {
                default:
                    throw new ArgumentException("Unknown node: " + node);

                case NotesNode notesNode:
                    {
                        var noteVertex = new NotesVertex();
                        noteVertex.notes = notesNode.notes;
                        thisVertex = noteVertex;
                        visitChildren = false;
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

                        ConvertBehaviors(thisVertex, stateNode);
                        break;
                    }
            }

            thisVertex.yedId = diagramNode.id;
            diagramVertexMap.Add(diagramNode, thisVertex);

            if (parentVertex != null)
            {
                thisVertex.parent = parentVertex;
                parentVertex.children.Add(thisVertex);
            }

            if (visitChildren)
            {
                foreach (var child in diagramNode.children)
                {
                    ProcessNode(child, thisVertex);
                }
            }

            return thisVertex;
        }

        private static void PrintAndThrowIfNodeParseFail(Input.DiagramNode diagramNode, Vertex parentVertex, LabelParser labelParser)
        {
            if (labelParser.HasError())
            {
                string parentPath = VertexPathDescriber.Describe(parentVertex);
                string fullMessage = $"Failed parsing node label. Parent path: `{parentPath}`.\n<label>\n{diagramNode.label}\n</label>\nDiagram id:`{diagramNode.id}`.\n";
                foreach (var error in labelParser.GetErrors())
                {
                    fullMessage += error.BuildMessage() + "\n";
                }

                Console.WriteLine(fullMessage);

                throw new ArgumentException(fullMessage);
            }
        }

        private void ConvertBehaviors(Vertex vertex, StateNode stateNode)
        {
            foreach (var nodeBehavior in stateNode.behaviors)
            {
                Behavior behavior = ConvertBehavior(nodeBehavior);

                vertex.behaviors.Add(behavior);
            }
        }

        private static Behavior ConvertBehavior(NodeBehavior nodeBehavior)
        {
            var behavior = new Behavior
            {
                actionCode = nodeBehavior.actionCode,
                guardCode = nodeBehavior.guardCode,
                triggers = nodeBehavior.triggers
            };

            if (nodeBehavior.order != null)
            {
                behavior.order = Double.Parse(nodeBehavior.order);
            }

            return behavior;
        }

        private static void SetStateFromStateNode(StateNode stateNode, State state)
        {
            state.name = stateNode.stateName;
            state.nameIsGloballyUnique = stateNode.stateNameIsGlobal;
        }
    }
}
