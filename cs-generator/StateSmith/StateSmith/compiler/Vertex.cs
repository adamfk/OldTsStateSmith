using StateSmith.compiler;
using System.Collections.Generic;

namespace StateSmith.Compiler
{
    public abstract class Vertex
    {
        public string DiagramId { get; set; }

        internal Vertex _parent;
        public Vertex Parent => _parent;

        internal List<Vertex> _children = new List<Vertex>();
        public IReadOnlyList<Vertex> Children => _children;

        internal List<Behavior> _behaviors = new List<Behavior>();
        public IReadOnlyList<Behavior> Behaviors => _behaviors;

        public HashList<string, NamedVertex> namedDescendants = new HashList<string, NamedVertex>();

        /// <summary>
        /// data structure may change
        /// </summary>
        internal List<Behavior> _incomingTransitions = new List<Behavior>();
        public IReadOnlyList<Behavior> IncomingTransitions => _incomingTransitions;

        public void AddBehavior(Behavior behavior)
        {
            _behaviors.Add(behavior);
        }

        public abstract void Accept(VertexVisitor visitor);


        internal void RemoveIncomingTransition(Behavior behavior)
        {
            _incomingTransitions.RemoveOrThrow(behavior);
        }

        internal void AddIncomingTransition(Behavior behavior)
        {
            if (behavior.transitionTarget != this)
            {
                throw new BehaviorValidationException(behavior, "Inconsistent data structure. Behavior target must match incoming target");
            }
            _incomingTransitions.Add(behavior);
        }

        public Behavior AddTransitionTo(Vertex target)
        {
            var behavior = new Behavior()
            {
                owningVertex = this,
                transitionTarget = target
            };
            _behaviors.Add(behavior);
            target._incomingTransitions.Add(behavior);

            return behavior;
        }
        public T AddChild<T>(T child) where T : Vertex
        {
            child._parent = this;
            _children.Add(child);
            return child;
        }

        public void RemoveChild(Vertex child)
        {
            _children.RemoveOrThrow(child);
            child._parent = null;

            foreach (var childBehavior in child._behaviors)
            {
                var target = childBehavior.transitionTarget;
                if (target != null)
                {
                    target._incomingTransitions.RemoveOrThrow(childBehavior);
                }
            }

            if (child._incomingTransitions.Count > 0)
            {
                throw new VertexValidationException(child, "cannot safely remove child as it still has incoming transitions");
            }
        }
    }
}
