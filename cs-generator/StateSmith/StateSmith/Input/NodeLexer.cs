using System;
using System.Text.RegularExpressions;

namespace StateSmith.Input
{
    partial class DiagramToHsmConverter
    {
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

        public abstract class HsmNode
        {

        }

        public abstract class DirectiveNode : HsmNode
        {

        }

        public class StatemachineDirectiveNode : DirectiveNode
        {
            public string statemachineName;
        }

        public class StateNode : HsmNode
        {

        }

        public class NodeLexer
        {
            public HsmNode LexNode(DiagramNode node)
            {
                if (node.label.Trim().StartsWith("$"))
                {
                    return LexDirectiveNode(node);
                }
                else
                {
                    return LexStateNode(node);
                }
            }

            public StateNode LexStateNode(DiagramNode node)
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



            public DirectiveNode LexDirectiveNode(DiagramNode node)
            {
                var match = NodeLabelTextParser.TryMatchDirective(node.label);

                if (match.directiveName == null)
                {
                    throw new NodeParseException("HSM directive parse fail. Check syntax.", node);
                }

                switch (match.directiveName.ToUpper())
                {
                    case "$STATEMACHINE":
                        return LexStatemachineDirectiveNode(match, node);

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

            }

            public DirectiveNode LexStatemachineDirectiveNode(DirectiveMatchStruct directiveMatch, DiagramNode node)
            {
                var match = NodeLabelTextParser.TryParseColonWordEnd(directiveMatch.followingText);

                var statemachineName = match.matchedWord;

                if (match.followingText.Length != 0)
                {
                    throw new NodeParseException($"Invalid characters after `$STATEMACHINE:{statemachineName}`", node);
                }

                return new StatemachineDirectiveNode()
                {
                    statemachineName = statemachineName
                };
            }
        }
    }

}
