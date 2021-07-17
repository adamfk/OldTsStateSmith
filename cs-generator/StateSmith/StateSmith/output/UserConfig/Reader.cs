using StateSmith.Input.Expansions;
using System;
using System.Collections.Generic;
using System.Reflection;
using System.Text;
using System.Linq;

#nullable enable

namespace StateSmith.output.UserConfig
{
    class Reader
    {
        Expander expander = new Expander();
        ExpanderFileReflection expanderFileReflection;
        
        public Reader()
        {
            expanderFileReflection = new ExpanderFileReflection(expander);
        }

        private void FindExpansionsFromFields(object configObject)
        {
            var type = configObject.GetType();

            var fieldInfos = type.GetFields(BindingFlags.Public | BindingFlags.NonPublic | BindingFlags.Static | BindingFlags.Instance)
                .Where(f => f.FieldType.IsSubclassOf(typeof(UserExpansionScriptBase)));

            foreach (var f in fieldInfos)
            {
                UserExpansionScriptBase? expansionObject = (UserExpansionScriptBase?)f.GetValue(configObject);
                AddExpansionsFromObject(expansionObject);
            }
        }

        private void AddExpansionsFromObject(UserExpansionScriptBase? expansionObject)
        {
            if (expansionObject != null)
            {
                expanderFileReflection.AddAllExpansions(expansionObject);
            }
        }

        private void FindExpansionsFromMethods(object configObject)
        {
            var type = configObject.GetType();

            var methodInfos = type.GetMethods(BindingFlags.Public | BindingFlags.NonPublic | BindingFlags.Static | BindingFlags.Instance)
                .Where(m => m.ReturnType.IsSubclassOf(typeof(UserExpansionScriptBase)) && m.GetParameters().Length == 0);

            foreach (var m in methodInfos)
            {
                UserExpansionScriptBase? expansionObject = (UserExpansionScriptBase?)m.Invoke(configObject, null);
                AddExpansionsFromObject(expansionObject);
            }
        }

        private void FindExpansionFromClasses(object configObject)
        {
            var type = configObject.GetType();

            var classes = type.GetNestedTypes()
                .Where(t => t.IsSubclassOf(typeof(UserExpansionScriptBase)));

            foreach (var classType in classes)
            {
                var ctor = classType.GetConstructor(Type.EmptyTypes);

                if (ctor != null)
                {
                    UserExpansionScriptBase? expansionObject = (UserExpansionScriptBase)ctor.Invoke(null);
                    AddExpansionsFromObject(expansionObject);
                }
            }
        }

        public void ReadObject(object configObject)
        {
            FindExpansionsFromFields(configObject);
            FindExpansionsFromMethods(configObject);
            FindExpansionFromClasses(configObject);

            var cLanguageSelected = true;

            if (configObject is IRenderConfigC configC && cLanguageSelected)
            {
                //FIXME finish
            }
            else
            {
                throw new ArgumentException("2134684"); //TODO exception
            }
        }
    }
}
