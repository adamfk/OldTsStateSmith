using StateSmith.Input;
using System;
using System.Collections.Generic;
using System.Text;
using System.Text.RegularExpressions;

namespace StateSmith.Input
{

    class DiagramToHsmConverter
    {
        private Hsm hsm;

        public void ConvertTree(DiagramNode root)
        {
            //nodes to parse: $GEN_INCLUDE
        }

        private void ProcessTopNode(DiagramNode topNode)
        {
            var parser = new NodeLabelTextParser();

        }

        private void LexNode(DiagramNode node)
        {
            if (node.label.Trim().StartsWith("$"))
            {
                LexDirectiveNode(node);
            }
            else
            {
                LexStateNode(node);
            }
        }

        private void LexStateNode(DiagramNode node)
        {
            new Regex(@"
                ^
                \s*
                (?<state_name> \w+ )
                \s*
                (?:$|\r\n|\r|\n) # end of input or line
                (?<following_text> [\s\S]*)  # optional
            ", RegexOptions.IgnorePatternWhitespace);

            throw new NotImplementedException();
        }

        public class NodeParseException : Exception
        {
            public DiagramNode node;

            public NodeParseException(DiagramNode node) : base()
            {
                this.node = node;
            }

            public NodeParseException(string message, DiagramNode node) : base(message)
            {
                this.node = node;
            }

            public NodeParseException(string message, Exception innerException, DiagramNode node) : base(message, innerException)
            {
                this.node = node;
            }
        }

        private void LexDirectiveNode(DiagramNode node)
        {
            var match = NodeLabelTextParser.TryMatchDirective(node.label);

            if (match.directiveName == null)
            {
                throw new NodeParseException("HSM directive parse fail. Check syntax.", node);
            }

            switch(match.directiveName.ToUpper())
            {
                case "$STATEMACHINE":
                    throw new NotImplementedException();
                    break;

                default:
                    throw new NodeParseException($"Unknown HSM directive: '{match.directiveName}'. Check syntax.", node);
            }

            /*
            allowable directives: 
                $STATEMACHINE : <name>
                $INITIAL_STATE
                $NOTES
                $GEN
                $GEN_INCLUDE : <relative_path>
                $PARENT_ALIAS
                $ORTHO : <execution_order>
            */

            throw new NotImplementedException();
        }

        public List<Hsm> Convert(List<DiagramNode> topNodes)
        {
            //only nodes allowed at top level are $NOTES and $STATEMACHINE

            foreach (var topNode in topNodes)
            {
                ProcessTopNode(topNode);
            }

            return null;
        }
    }

}
