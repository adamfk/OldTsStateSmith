using StateSmith.Input.Expansions;

public class ExampleExpansions123 : UserExpansionScriptBase
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
