using System;

namespace StateSmith.Compiler
{
    public class BehaviorValidationException : Exception
    {
        public BehaviorValidationException(Behavior behavior, string message) : base(message)
        {
        }
    }
}
