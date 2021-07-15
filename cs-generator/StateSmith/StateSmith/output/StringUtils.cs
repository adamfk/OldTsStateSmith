﻿using System;
using System.Collections.Generic;
using System.Text;
using System.Text.RegularExpressions;

namespace StateSmith.output
{
    public class StringUtils
    {
        public static string DeIndent(string str)
        {
            var match = Regex.Match(str, @"^\s*?([ \t]+)\S");

            if (!match.Success)
            {
                return str;
            }

            var indent = match.Groups[1];

            var r = new Regex("^" + indent, RegexOptions.Multiline);

            var output = r.Replace(str, "");

            return output;
        }

        public static string DeIndentTrim(string str)
        {
            var output = DeIndent(str);
            output = TrimLeadingHsAndFirstNewline(output);
            output = TrimTrailingNewLineAndHs(output);

            return output;
        }

        /// <summary>
        /// Trims trailing new line and horizontal white space
        /// </summary>
        /// <param name="output"></param>
        /// <returns></returns>
        private static string TrimTrailingNewLineAndHs(string output)
        {
            output = Regex.Replace(output, @"(\r\n|\r|\n)[ \t]*$", "");
            return output;
        }

        /// <summary>
        /// Trims leading horizontal space and first new line
        /// </summary>
        /// <param name="output"></param>
        /// <returns></returns>
        private static string TrimLeadingHsAndFirstNewline(string output)
        {
            output = Regex.Replace(output, @"^[ \t]*(\r\n|\r|\n)", "");
            return output;
        }
    }
}
