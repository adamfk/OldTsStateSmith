using StateSmith.compiler;
using System.Collections.Generic;

namespace StateSmith.Compiler
{
    public abstract class Vertex
    {
        public string DiagramId { get; set; }

        internal Vertex _parent;
        public Vertex Parent => _parent;

        /// <summary>
        /// data structure may change
        /// </summary>
        internal List<Vertex> _children = new List<Vertex>();
        public IReadOnlyList<Vertex> Children => _children;

        /// <summary>
        /// data structure may change
        /// </summary>
        internal List<Behavior> _behaviors = new List<Behavior>();
        public IReadOnlyList<Behavior> Behaviors => _behaviors;

        /// <summary>
        /// data structure may change
        /// </summary>
        internal HashList<string, NamedVertex> _namedDescendants = new HashList<string, NamedVertex>();

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

        public List<NamedVertex> DescendantsWithName(string name)
        {
            List<NamedVertex> list = new List<NamedVertex>();
            var matches = _namedDescendants.GetValuesOrEmpty(name);
            list.AddRange(matches);

            return list;
        }


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
            AddBehavior(behavior);
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

            foreach (var childBehavior in child.Behaviors)
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
