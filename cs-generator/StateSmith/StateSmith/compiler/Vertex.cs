using System.Collections.Generic;

namespace StateSmith.Compiler
{
    public abstract class Vertex
    {
        public string yedId;

        public Vertex parent;
        public List<Vertex> children = new List<Vertex>();
        public List<Behavior> behaviors = new List<Behavior>();

        public HashList<string, NamedVertex> namedDescendants = new HashList<string, NamedVertex>();

        public abstract void Accept(VertexVisitor visitor);
    }
}
