using Antlr4.Runtime;
using Antlr4.Runtime.Tree;
using System;
using System.Collections.Generic;
using System.IO;
using System.Text;

namespace StateSmith.Input.antlr4
{
    public class ErrorListener : IAntlrErrorListener<IToken>
    {
        public List<string> errors = new List<string>();

        public void SyntaxError(TextWriter output, IRecognizer recognizer, IToken offendingSymbol, int line, int charPositionInLine, string msg, RecognitionException e)
        {
            errors.Add(msg);
        }
    }

    public class LabelParser
    {
        public ErrorListener errorListener = new ErrorListener();

        public Node ParseNodeLabel(string stateLabel)
        {
            Grammar1Parser parser = BuildParserForString(stateLabel);

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
            IParseTree tree = parser.edge();
            NodeEdgeWalker walker = WalkTree(tree);
            return walker.behaviors;
        }

        private static NodeEdgeWalker WalkTree(IParseTree tree)
        {
            NodeEdgeWalker walker = new NodeEdgeWalker();
            ParseTreeWalker.Default.Walk(walker, tree);
            return walker;
        }

        private Grammar1Parser BuildParserForString(string inputString)
        {
            ICharStream stream = CharStreams.fromString(inputString);
            ITokenSource lexer = new Grammar1Lexer(stream);
            ITokenStream tokens = new CommonTokenStream(lexer);
            Grammar1Parser parser = new Grammar1Parser(tokens)
            {
                BuildParseTree = true
            };
            parser.Trace = true;
            parser.AddErrorListener(errorListener);

            return parser;
        }
    }
}
