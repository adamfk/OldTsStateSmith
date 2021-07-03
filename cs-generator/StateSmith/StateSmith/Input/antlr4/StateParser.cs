using Antlr4.Runtime;
using Antlr4.Runtime.Tree;
using System;
using System.Collections.Generic;
using System.Text;

namespace StateSmith.Input.antlr4
{
    public class StateParser
    {
        public TextState ParseStateLabel(string stateLabel)
        {
            Grammar1Parser parser = BuildParserForString(stateLabel);

            //FIXME detect and output all errors
            IParseTree tree = parser.state_defn();
            TextStateWalker walker = WalkTree(tree);
            walker.textState.tree = tree;
            walker.textState.behaviors = walker.behaviors;

            return walker.textState;
        }

        public List<TextBehavior> ParseEdgeLabel(string edgeLabel)
        {
            Grammar1Parser parser = BuildParserForString(edgeLabel);

            //FIXME detect and output all errors
            IParseTree tree = parser.nl_behaviors();
            TextStateWalker walker = WalkTree(tree);
            return walker.behaviors;
        }

        private static TextStateWalker WalkTree(IParseTree tree)
        {
            TextStateWalker walker = new TextStateWalker();
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
