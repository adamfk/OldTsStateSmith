using StateSmith.Input.antlr4;
using StateSmith.Input.Expansions;
using StateSmith.Input.Yed;
using System;
using System.Collections.Generic;
using System.Text;

namespace StateSmith
{
    class Compiler
    {
        public void CompileFile(string filepath)
        {
            YedParser yedParser = new YedParser();
            Expander expander = new Expander();
            LabelParser stateParser = new LabelParser();

        }
    }
}
