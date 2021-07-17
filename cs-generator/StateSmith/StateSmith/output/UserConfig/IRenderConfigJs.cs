﻿using System;
using System.Collections.Generic;
using System.Text;
using System.Text.RegularExpressions;

namespace StateSmith.output.UserConfig
{
    public interface IRenderConfigJs
    {
        /// <summary>
        /// Whatever this property returns will be placed at the top of the rendered file.
        /// </summary>
        string FileTop => StringUtils.DeIndentTrim(@"
            // Autogenerated with StateSmith
        ");

        string VariableDeclarations => "";
    }
}