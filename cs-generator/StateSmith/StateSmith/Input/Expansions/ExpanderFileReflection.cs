using System;
using System.Collections.Generic;
using System.Text;
using System.Reflection;

namespace StateSmith.Input.Expansions
{

    public class ExpanderFileReflection
    {
        private Expander expander;

        public ExpanderFileReflection(Expander expander)
        {
            this.expander = expander;
        }

        public void AddAllExpansions(object userObject)
        {
            var fields = userObject.GetType().GetFields(BindingFlags.Public | BindingFlags.NonPublic | BindingFlags.Static | BindingFlags.Instance);
            AddFieldExpansions(userObject, fields);
        }

        private void AddFieldExpansions(object userObject, FieldInfo[] fields)
        {
            foreach (var field in fields)
            {
                ProcessField(userObject, field);
            }
        }

        private void ProcessField(object userObject, FieldInfo field)
        {
            //we only care about fields of type string
            if (field.FieldType != typeof(string))
            {
                return;
            }

            string expansionName = field.Name;
            string expansionOutput = (string)field.GetValue(userObject);
            var expansionNameAttributes = field.GetCustomAttributes(typeof(ExpansionNameAttribute), inherit: false);

            if (expansionNameAttributes.Length > 0)
            {
                ExpansionNameAttribute attribute = (ExpansionNameAttribute)expansionNameAttributes[0];
                expansionName = attribute.Name;
            }

            expander.AddVariableExpansion(name: expansionName, code: expansionOutput);
        }
    }
}
