using StateSmith.Input.Expansions;
using StateSmith.output;

#pragma warning disable IDE1006 // Naming Styles
#pragma warning disable IDE0051 // Remove unused private members
#pragma warning disable RCS1018 // Add accessibility modifiers (or vice versa).
#pragma warning disable RCS1213 // Remove unused member declaration.
#pragma warning disable IDE0044 // Add readonly modifier

namespace StateSmithTest
{
    /// <summary>
    /// User expansions that apply to both javascript and C99
    /// </summary>
    public class CommonExpansions : UserExpansionScriptBase
    {
        string a_count => AutoVarName;

        string some_guard(string count)
        {
            int int_count = int.Parse(count);

            if (int_count > 100)
            {
                int_count += 1000;
            }

            return $"some_guard({int_count})";
        }

        string b_exit()
        {
            return "b_exit_count++";
        }
    }

    /// <summary>
    /// Glue configuration for generating C99 code
    /// </summary>
    public class OvenSmGlueC : SmGlueC
    {
        public override string h_file_top => DeIndent(@"
            /**
             * Blah blah blah
             */
            #include <stdbool.h>
            #include ""some_stuff.h""     //escaping double quotes is unfortunate, but not too bad for now
            ");

        //should auto deIndent
        public override string c_file_top => DeIndent(@"
            /**
             * Blah blah blah
             */
            #include <stdbool.h>
            #include ""my_stuff.h""     //escaping double quotes is unfortunate, but not too bad for now
            ");

        public class Expansions : CommonExpansions
        {
            string set_mode(string mode) => $"set_mode(MODE_{mode})";
        }

    }

    /// <summary>
    /// Glue configuration for generating javscript code
    /// </summary>
    public class OvenSmGlueJs : SmGlueJs
    {
        //should auto deIndent
        public override string file_top => DeIndent(@"
            /**
             * Blah blah blah
             */
            ");

        public class Expansions : CommonExpansions
        {
            string set_mode(string mode) => $"set_mode(SomeEnum.{mode})";
        }

    }
}

