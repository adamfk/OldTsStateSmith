using StateSmith.compiler;
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
        public Vertex owningVertex;
        public List<string> triggers = new List<string>();
        public double order = DEFAULT_ORDER;
        public string guardCode;
        public string actionCode;

        public bool HasGuardCode()
        {
            return guardCode != null && guardCode.Trim().Length > 0; //trim not ideal for performance, but fine for now
        }

        public bool HasAtLeastOneTrigger()
        {
            return triggers.Count > 0;
        }

        public void RetargetTo(Vertex newTarget)
        {
            var oldTarget = transitionTarget;
            oldTarget.RemoveIncomingTransition(this);

            transitionTarget = newTarget;
            newTarget.AddIncomingTransition(this);
        }
    }
}
