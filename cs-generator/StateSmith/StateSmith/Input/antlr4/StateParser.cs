using Antlr4.Runtime;
using Antlr4.Runtime.Tree;
using System;
using System.Collections.Generic;
using System.Text;

namespace StateSmith.Input.antlr4
{
    public class StateParser
    {
        public Node ParseNodeLabel(string stateLabel)
        {
            Grammar1Parser parser = BuildParserForString(stateLabel);

            //FIXME detect and output all errors
            IParseTree tree = parser.node();
            NodeEdgeWalker walker = WalkTree(tree);
            walker.node.tree = tree;

            if (walker.node is StateNode stateNode)
            {
                stateNode.behaviors = walker.behaviors;
            }

            return walker.node;
        }

        public List<NodeBehavior> ParseEdgeLabel(string edgeLabel)
        {
            Grammar1Parser parser = BuildParserForString(edgeLabel);

            //FIXME detect and output all errors
            IParseTree tree = parser.nl_behaviors();
            NodeEdgeWalker walker = WalkTree(tree);
            return walker.behaviors;
        }

        private static NodeEdgeWalker WalkTree(IParseTree tree)
        {
            NodeEdgeWalker walker = new NodeEdgeWalker();
            ParseTreeWalker.Default.Walk(walker, tree);
            return walker;
        }

        private static Grammar1Parser BuildParserForString(string inputString)
        {
            ICharStream stream = CharStreams.fromString(inputString);
            ITokenSource lexer = new Grammar1Lexer(stream);
            ITokenStream tokens = new CommonTokenStream(lexer);
            Grammar1Parser parser = new Grammar1Parser(tokens)
            {
                BuildParseTree = true
            };

            return parser;
        }
    }
}
