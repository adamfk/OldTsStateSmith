using System;
using System.Collections.Generic;
using System.Text;
using Xunit;
using FluentAssertions;
using StateSmith.Input.Expansions;

namespace StateSmithTest
{
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

    class ExpansionsExample : UserExpansionScriptBase
    {
        string time => get_time_;

        //`time` property also creates `get_time` method which prevents us from creating `get_time` property
        //so we use a custom attribute to set the name we want.
        [ExpansionName("get_time")]
        string get_time_ => "system_get_time()";

        string set_mode(string enum_name) => $"set_mode(ENUM_PREFIX_{enum_name})";
        
        string hit_count = "sm->vars." + AutoNameToken;   //`AutoNameToken` maps to name of field. Result: "sm->vars.hit_count"
        string jump_count => AutoVarName;

        string func() => "123";
    }

    public class ExpanderTest
    {
        [Fact]
        public void Test1()
        {
            Expander expander = new Expander();
            var userExpansions = new ExpansionsExample();
            ExpanderFileReflection expanderFileReflection = new ExpanderFileReflection(expander);

            expanderFileReflection.AddAllExpansions(userExpansions);

            expander.variableExpansions.Count.Should().Be(1);
            expander.variableExpansions["hit_count"].Should().Be("sm->vars.hit_count");
        }
    }
}
