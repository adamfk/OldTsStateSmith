using System.Collections.Generic;

namespace StateSmith.Compiler
{
    public class Behavior
    {
        public string yedId;

        public Vertex transitionTarget;
        public List<string> triggers = new List<string>();
        public double order;
        public string guardCode;
        public string actionCode;
    }
}
