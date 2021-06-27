using StateSmith.Input;
using System;
using System.Collections.Generic;
using System.Text;
using System.Text.RegularExpressions;

namespace StateSmith
{

    class HsmState
    {
        public DiagramNode diagramNode;
    }

    class HsmTransition
    {
        public DiagramEdge diagramEdge;
    }


    class Hsm
    {
        public HsmState rootState;
    }



    public class NodeLabelTextParser
    {
        //Find state machine nodes
        public static readonly Regex statemachineRegex = new Regex(@"
            (?i)
            ^\s*
            [$]STATEMACHINE
            \s*
            :
            \s*
            (?<statemachine_name> \w+ )
            \s*
            (?:$|\r|\n)
        ", RegexOptions.IgnorePatternWhitespace);

        public (string statemachineName, int nextParseIndex)? TryMatchStatemachine(string nodeLabelText)
        {
            Match match = statemachineRegex.Match(nodeLabelText);
            if (!match.Success)
            {
                return null;
            }

            return (match.Groups["statemachine_name"].Value, match.Length);
        }
    }

    class DiagramToHsmConverter
    {
        public List<Hsm> Convert(List<DiagramNode> diagramNodes)
        {
            //need to parse trees from each root
            return null;
        }
    }

}
