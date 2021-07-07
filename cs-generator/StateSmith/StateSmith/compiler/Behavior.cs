using System.Collections.Generic;

namespace StateSmith.Compiler
{
    public class Behavior
    {
        public const double DEFAULT_ORDER = 1e6;

        public string yedId;

        /// <summary>
        /// Allowed to be null
        /// </summary>
        public Vertex transitionTarget;
        public List<string> triggers = new List<string>();
        public double order = DEFAULT_ORDER;
        public string guardCode;
        public string actionCode;
    }
}
