﻿using Antlr4.Runtime.Tree;
using System;
using System.Collections.Generic;
using System.Diagnostics.CodeAnalysis;
using System.Text;

namespace StateSmith.Input.antlr4
{

    public class DeIndentExpandVisitor : Grammar1BaseVisitor<int>
    {
        /// <summary>
        /// It is more efficient to just use a StringBuilder field rather than
        /// returning a string from the visit method and appending them to larger strings.
        /// </summary>
        private const int UNUSED = 0;

        private bool firstLineEndingFound = false;

        private int deIndentSize = 0;

        public StringBuilder stringBuilder = new StringBuilder();

        public override int VisitTerminal(ITerminalNode node)
        {
            if (node.Symbol != null)
            {
                Append(node.Symbol.Text);
            }
            return UNUSED;
        }

        public override int VisitExpandable_identifier([NotNull] Grammar1Parser.Expandable_identifierContext context)
        {
            Append(context.ohs()?.GetText() ?? "");
            Append(context.IDENTIFIER().GetText()); //FIXME put int expansions here

            return UNUSED;
        }

        public string DeIndent(string str)
        {
            if (str.Length < deIndentSize)
            {
                return str;
            }

            return str.Substring(deIndentSize);
        }

        public override int VisitLine_end_with_hs([NotNull] Grammar1Parser.Line_end_with_hsContext context)
        {
            var trailingSpace = context.ohs()?.GetText() ?? "";

            if (firstLineEndingFound == false)
            {
                deIndentSize = trailingSpace.Length;
                firstLineEndingFound = true;
            }

            Append(context.LINE_ENDER().GetText());
            Append(DeIndent(trailingSpace));

            return UNUSED;
        }

        private void Append(string str)
        {
            stringBuilder.Append(str);
        }
    }



}
