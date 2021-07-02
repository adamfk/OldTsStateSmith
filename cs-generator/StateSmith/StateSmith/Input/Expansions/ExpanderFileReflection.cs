using System;
using System.Collections.Generic;
using System.Text;
using System.Reflection;
using System.Runtime.CompilerServices;

namespace StateSmith.Input.Expansions
{

    public class ExpanderFileReflection
    {
        private Expander expander;

        private HashSet<MethodInfo> propertyMethods = new HashSet<MethodInfo>();
        private HashSet<FieldInfo> propertyFields = new HashSet<FieldInfo>();

        public ExpanderFileReflection(Expander expander)
        {
            this.expander = expander;
        }

        public void AddAllExpansions(object userObject)
        {
            var fields = userObject.GetType().GetFields(BindingFlags.Public | BindingFlags.NonPublic | BindingFlags.Static | BindingFlags.Instance);
            AddFieldExpansions(userObject, fields);

            var propertyInfos = userObject.GetType().GetProperties(BindingFlags.Public | BindingFlags.NonPublic | BindingFlags.Static | BindingFlags.Instance);
            AddPropertyExpansions(userObject, propertyInfos);

            var methods = userObject.GetType().GetMethods(BindingFlags.Public | BindingFlags.NonPublic | BindingFlags.Static | BindingFlags.Instance);
            AddMethodExpansions(userObject, methods);
        }

        private void AddPropertyExpansions(object userObject, PropertyInfo[] propertyInfos)
        {
            foreach (var p in propertyInfos)
            {
                ProcessProperty(userObject, p);
            }
        }

        private void AddMethodExpansions(object userObject, MethodInfo[] methods)
        {
            foreach (var method in methods)
            {
                ProcessMethod(userObject, method);
            }
        }

        private void ProcessProperty(object userObject, PropertyInfo property)
        {
            if (property.PropertyType != typeof(string))
            {
                return; //only care about methods that return strings
            }

            MethodInfo getMethodInfo = property.GetGetMethod(nonPublic: true);
            if (getMethodInfo == null)
            {
                return; //only care about getter properties
            }

            propertyMethods.Add(getMethodInfo);

            
            string expansionName = property.Name;
            var expansionNameAttributes = property.GetCustomAttributes(typeof(ExpansionNameAttribute), inherit: false);

            if (expansionNameAttributes.Length > 0)
            {
                ExpansionNameAttribute attribute = (ExpansionNameAttribute)expansionNameAttributes[0];
                expansionName = attribute.Name;
            }

            string code = (string)getMethodInfo.Invoke(userObject, null);
            expander.AddVariableExpansion(expansionName, code);
        }

        private void ProcessMethod(object userObject, MethodInfo method)
        {
            if (method.ReturnType != typeof(string))
            {
                return; //only care about methods that return strings
            }

            if (propertyMethods.Contains(method))
            {
                return; //we don't want compiler generated methods for properties like `get_get_time`
            }

            if (method.DeclaringType == typeof(System.Object))
            {
                return;
            }

            string expansionName = method.Name;
            var expansionNameAttributes = method.GetCustomAttributes(typeof(ExpansionNameAttribute), inherit: false);

            if (expansionNameAttributes.Length > 0)
            {
                ExpansionNameAttribute attribute = (ExpansionNameAttribute)expansionNameAttributes[0];
                expansionName = attribute.Name;
            }

            expander.AddExpansionMethod(expansionName, userObject, method);
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
