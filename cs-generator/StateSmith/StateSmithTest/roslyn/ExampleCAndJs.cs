﻿using StateSmith.Input.Expansions;
using StateSmith.output.UserConfig;
using System;
using System.Collections.Generic;
using System.Text;
using static StateSmith.output.StringUtils;

namespace StateSmithTest.roslyn
{
    public class OvenCommon : IHasEventList
    {
        public string EventList => @"
            do, event1, event2
        ";
    }

    /// <summary>
    /// Render configuration for generating C code. Must implement <see cref="IRenderConfigC"/>.
    /// </summary>
    public class OvenC : OvenCommon, IRenderConfigC
    {
        public string HFileTop => DeIndentTrim(@"
            /**
             * Blah blah blah
             */
            #include <stdbool.h>
            #include <stdint.h>
            #include ""some_stuff.h""     //escaping double quotes is unfortunate, but not too bad for now
            ");

        public string CFileTop => DeIndentTrim(@"
            /**
             * Blah blah blah
             */
            #include <stdbool.h>
            #include ""my_stuff.h""     //escaping double quotes is unfortunate, but not too bad for now
            ");

        public string VariableDeclarations => DeIndentTrim(@"
            uint16_t count = 0;
            bool flag = false;
            ");

        public class Expansions : CommonExpansions
        {
            public string set_mode(string mode) => $"set_mode(MODE_{mode})";
        }
    }



    /// <summary>
    /// configuration for generating javscript code
    /// </summary>
    public class OvenJs : OvenCommon, IRenderConfigJs
    {
        public string VariableDeclarations => DeIndentTrim(@"
            count = 0;
            flag = false;
            ");

        public class Expansions : CommonExpansions
        {
            public string set_mode(string mode) => $"set_mode(SomeEnum.{mode})";
        }
    }


    /// <summary>
    /// User expansions that apply to both javascript and C99
    /// </summary>
    public class CommonExpansions : UserExpansionScriptBase
    {
        public string a_count => AutoVarName;

        public string some_guard(string count)
        {
            int int_count = int.Parse(count);

            if (int_count > 100)
            {
                int_count += 1000;
            }

            return $"some_guard({int_count})";
        }

        public string b_exit()
        {
            return "b_exit_count++";
        }
    }
}
