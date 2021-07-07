using System.Collections.Generic;

namespace StateSmith.Compiler
{
    public class Vertex
    {
        public string yedId;

        public Vertex parent;
        public List<Vertex> children = new List<Vertex>();
        public List<Behavior> behaviors = new List<Behavior>();
    }
}
