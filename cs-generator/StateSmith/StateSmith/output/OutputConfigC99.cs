﻿using System;
using System.Collections.Generic;
using System.Text;
using System.Text.RegularExpressions;

namespace StateSmith.output
{
    public class SmGlueC
    {
        public string DeIndent(string str)
        {
            return StringUtils.DeIndent(str);
        }

        public string DeIndentTrim(string str)
        {
            return StringUtils.DeIndentTrim(str);
        }

        public virtual string h_file_top => DeIndent(@"
            // Autogenerated with StateSmith
        ");

        public virtual string c_file_top => @"
            // Autogenerated with StateSmith
        ";
    }

    public class SmGlueJs
    {
        public virtual string file_top => DeIndent(@"
            // Autogenerated with StateSmith
        ");

        public string DeIndent(string str)
        {
            return StringUtils.DeIndent(str);
        }

        public string DeIndentTrim(string str)
        {
            return StringUtils.DeIndentTrim(str);
        }
    }
}
