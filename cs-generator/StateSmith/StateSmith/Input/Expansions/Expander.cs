using System;
using System.Collections.Generic;
using System.Text;
using System.Reflection;

namespace StateSmith.Input.Expansions
{

    public class Expander
    {
        private Dictionary<string, string> variableExpansions = new Dictionary<string, string>();
        private Dictionary<string, ExpansionMethod> methodExpansions = new Dictionary<string, ExpansionMethod>();

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

        private string ExpandCodeSpecialTokens(string name, string code)
        {
            code = code.Replace(UserExpansionScriptBase.AutoNameToken, name);
            return code;
        }

        public void AddVariableExpansion(string name, string code)
        {
            ThrowIfExpansionNameAlreadyUsed(name);
            code = ExpandCodeSpecialTokens(name, code);
            variableExpansions.Add(name, code);
        }

        public void AddExpansionMethod(string name, object userObject, MethodInfo method)
        {
            ThrowIfExpansionNameAlreadyUsed(name);
            methodExpansions.Add(name, new ExpansionMethod(name, userObject, method));
        }

        public string TryExpandVariableExpansion(string name)
        {
            if (variableExpansions.ContainsKey(name) == false)
            {
                return name;
            }

            return variableExpansions[name];
        }

        public string TryExpandMethodExpansion(string name, string[] arguments)
        {
            if (methodExpansions.ContainsKey(name) == false)
            {
                return name;
            }

            var method = methodExpansions[name];
            var code = method.Evaluate(arguments);
            code = ExpandCodeSpecialTokens(name, code);
            return code;
        }

        public string[] GetVariableNames()
        {
            var keys = new string[variableExpansions.Count];
            variableExpansions.Keys.CopyTo(keys, 0);
            return keys;
        }

        public bool HasMethodName(string name)
        {
            return methodExpansions.ContainsKey(name);
        }

        public string[] GetMethodNames()
        {
            var keys = new string[methodExpansions.Count];
            methodExpansions.Keys.CopyTo(keys, 0);
            return keys;
        }
    }
}
