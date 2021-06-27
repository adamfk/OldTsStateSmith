using StateSmith.Input;
using System;
using System.Collections.Generic;
using System.Text;
using System.Text.RegularExpressions;

namespace StateSmith.Input
{
    /// <summary>
    /// GenIncludes
    /// </summary>
    public class NodeLabelTextParser
    {
        private static readonly Regex statemachineRegex = new Regex(@"
            (?i)
            ^\s*
            [$]STATEMACHINE
            \s*
            :
            \s*
            (?<statemachine_name> \w+ )
            \s*
            (?:$|\r\n|\r|\n) # end of input or line
        ", RegexOptions.IgnorePatternWhitespace);


        private static readonly Regex directiveRegex = new Regex(@"
            ^
            \s*
            (?<directive_name> [$] \w+ )    # includes the $ to make searching for it easier in code
            \b
            (?<following_text> [\s\S]*)  # optional
        ", RegexOptions.IgnorePatternWhitespace);

        public static (string directiveName, string followingText) TryMatchDirective(string nodeLabelText)
        {
            Match match = directiveRegex.Match(nodeLabelText);
            if (!match.Success)
            {
                return default;
            }

            return (directiveName: match.Groups["directive_name"].Value,
                    followingText: nodeLabelText.Substring(match.Length));
        }


        public (string statemachineName, int nextParseIndex) TryMatchStatemachine(string nodeLabelText)
        {
            Match match = statemachineRegex.Match(nodeLabelText);
            if (!match.Success)
            {
                return default;
            }

            return (statemachineName: match.Groups["statemachine_name"].Value, 
                    nextParseIndex: match.Length);
        }
    }

}
