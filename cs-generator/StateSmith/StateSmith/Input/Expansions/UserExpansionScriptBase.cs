using System;
using System.Collections.Generic;
using System.Text;
using System.Reflection;
using System.Runtime.CompilerServices;

[assembly: InternalsVisibleTo("StateSmithTest")]

namespace StateSmith.Input.Expansions
{
    public class UserExpansionScriptBase
    {
        /// <summary>
        /// This value will be updated as necessary for target language and rendering engine
        /// </summary>
        internal string varsPath = null;

        /// <summary>
        /// This value will be updated as necessary for target language and rendering engine
        /// </summary>
        public string VarsPath => varsPath;


        /// <summary>
        /// The value of this property may change. Use normally, but don't hard code against the string value that it returns.
        /// </summary>
        public static string AutoNameToken => "$$_AutoNameToken_$$";

        /// <summary>
        /// This value will be updated as necessary for target language and rendering engine
        /// </summary>
        public string AutoVarName => VarsPath + AutoNameToken;
    }
}
