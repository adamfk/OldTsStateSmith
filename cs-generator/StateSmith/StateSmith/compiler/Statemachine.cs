﻿using System.Collections.Generic;

namespace StateSmith.Compiler
{
    /// <summary>
    /// Allow state machines to be nested. Why? Allows you to test
    /// that section of the state machine design independently of the rest.
    /// Very helpful in large designs.
    /// </summary>
    public class Statemachine : NamedVertex
    {
        public Statemachine(string name) : base(name)
        {
        }

        public override void Accept(VertexVisitor visitor)
        {
            visitor.Visit(this);
        }
    }
}
