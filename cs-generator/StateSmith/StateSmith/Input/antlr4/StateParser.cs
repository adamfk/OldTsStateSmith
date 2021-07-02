using Antlr4.Runtime;
using Antlr4.Runtime.Tree;
using System;
using System.Collections.Generic;
using System.Text;

namespace StateSmith.Input.antlr4
{
    public class StateParser
    {
        public static TextState Parse(string stateLabel)
        {
            //https://github.com/antlr/antlr4/blob/master/doc/csharp-target.md

            ICharStream stream = CharStreams.fromString(stateLabel);
            ITokenSource lexer = new Grammar1Lexer(stream);
            ITokenStream tokens = new CommonTokenStream(lexer);
            Grammar1Parser parser = new Grammar1Parser(tokens);
            parser.BuildParseTree = true;
            IParseTree tree = parser.state_defn();
            TextStateWalker walker = new TextStateWalker();
            ParseTreeWalker.Default.Walk(walker, tree);
            walker.textState.tree = tree;

            return walker.textState;
        }
    }
}
