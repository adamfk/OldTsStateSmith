using System;
using System.Collections.Generic;
using System.Text;

//suppress naming styles for expansions
#pragma warning disable IDE1006 // Naming Styles

namespace StateSmithTest
{
    class CustomExpansionAttribute : Attribute
    {
        private string Name;

        /// <summary>
        /// Extra control over how expansion will be generated.
        /// </summary>
        /// <param name="name"></param>
        public CustomExpansionAttribute(string name)
        {
            Name = name;
        }
    }

    /*
     A bit torn on direction. Using a property (like below) makes for easier reuse, but also defines a shadow function called `get_time()` which can conflict
        string time => "_get_time();";
        string get_time() => "system_get_time()";   //CLASHES with `time` property!

    Using fields:
        pros: simple, no name clashing
        cons: can't reference other expansions unless they are marked as static

            string time2 = "get_time();";
            string time3 = time2;   //fail!

            //more verbose
            static string time2 = "get_time();";
            static string time3 = time2;
    */

    //part of StateSmith assembly, not user code
    class UserExpansionScriptBase
    {
        public const string varsPath = "sm->vars.";
        public const string autoVarName = varsPath + "$$";  //probably better to be a property
    }

    class ExpansionsExample : UserExpansionScriptBase
    {
        //Don't use properties like below. They als 
        string time => "get_time()";

        [CustomExpansion("get_time()")]
        string _get_time() => "system_get_time()";

        string set_mode(string enum_name) => $"set_mode(ENUM_PREFIX_{enum_name})";
        
        string hit_count = "sm->vars.$$";   //`$$` maps to name of field. Result: "sm->vars.hit_count"
        string jump_count = autoVarName;


    }
}
