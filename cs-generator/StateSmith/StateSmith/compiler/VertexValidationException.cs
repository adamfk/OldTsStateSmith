using System;

namespace StateSmith.Compiler
{
    public class VertexValidationException : Exception
    {
        public VertexValidationException(Vertex vertex, string message) : base(message)
        {
        }
    }
}
