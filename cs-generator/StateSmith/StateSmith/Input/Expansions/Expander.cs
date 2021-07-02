using System;
using System.Collections.Generic;
using System.Text;
using System.Reflection;

namespace StateSmith.Input.Expansions
{

    public class Expander
    {
        public Dictionary<string, string> variableExpansions = new Dictionary<string, string>();
        public Dictionary<string, string> methodExpansions = new Dictionary<string, string>();

        private void ThrowIfExpansionNameAlreadyUsed(string expansionName)
        {
            //todolow make custom exception
            if (variableExpansions.ContainsKey(expansionName))
            {
                throw new ArgumentException($"Expansion name `{expansionName}` already has a variable mapping");
            }

            if (methodExpansions.ContainsKey(expansionName))
            {
                throw new ArgumentException($"Expansion name `{expansionName}` already has a method mapping");
            }
        }

        private string ExpandCode(string name, string code)
        {
            code = code.Replace(UserExpansionScriptBase.AutoNameToken, name);
            return code;
        }

        public void AddVariableExpansion(string name, string code)
        {
            ThrowIfExpansionNameAlreadyUsed(name);
            code = ExpandCode(name, code);
            variableExpansions.Add(name, code);
        }
    }
}
