using System.Diagnostics;

namespace StateSmith.Compiler
{
    //[DebuggerDisplay(Name = "{name}")]

    public class NamedVertex : Vertex
    {
        public string name;
        public bool nameIsGloballyUnique = false;

        public override void Accept(VertexVisitor visitor)
        {
            visitor.Visit(this);
        }

        public override string ToString()
        {
            return GetType().Name + ": " + name;
        }
    }
}
