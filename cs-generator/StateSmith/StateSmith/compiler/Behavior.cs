using StateSmith.compiler;
using System.Collections.Generic;

#nullable enable

namespace StateSmith.Compiler
{
    public class Behavior
    {
        public const double DEFAULT_ORDER = 1e6;

        /// <summary>
        /// Only populated for transitions.
        /// </summary>
        public string? DiagramId { get; set; }

        public Vertex _owningVertex;
        public Vertex OwningVertex => _owningVertex;

        /// <summary>
        /// Allowed to be null
        /// </summary>
        internal Vertex? _transitionTarget;

        public Vertex? TransitionTarget => _transitionTarget;

        public List<string> triggers = new List<string>();
        public double order = DEFAULT_ORDER;
        public string? guardCode;
        public string? actionCode;

        public Behavior() { }

        public Behavior(Vertex owningVertex, Vertex? transitionTarget = null)
        {
            _owningVertex = owningVertex;

            if (transitionTarget != null)
            {
                _transitionTarget = transitionTarget;
                transitionTarget.AddIncomingTransition(this);
            }
        }

        public bool HasGuardCode()
        {
            return guardCode != null && guardCode.Trim().Length > 0; //trim not ideal for performance, but fine for now
        }

        public bool HasAtLeastOneTrigger()
        {
            return triggers.Count > 0;
        }

        /// <summary>
        /// Must have had an original target
        /// </summary>
        /// <param name="newTarget"></param>
        public void RetargetTo(Vertex newTarget)
        {
            var oldTarget = TransitionTarget;
            oldTarget!.RemoveIncomingTransition(this);

            _transitionTarget = newTarget;
            newTarget.AddIncomingTransition(this);
        }
    }
}
