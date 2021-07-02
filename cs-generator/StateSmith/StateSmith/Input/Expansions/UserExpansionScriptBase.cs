using System;
using System.Collections.Generic;
using System.Text;
using System.Reflection;

namespace StateSmith.Input.Expansions
{

    public class UserExpansionScriptBase
    {
        public static string VarsPath => "sm->vars.";

        /// <summary>
        /// The value of this property may change. Use normally, but don't hard code against the string value that it returns.
        /// </summary>
        public static string AutoNameToken => "$$_AutoNameToken_$$";
        public static string AutoVarName => VarsPath + AutoNameToken;
    }
}
