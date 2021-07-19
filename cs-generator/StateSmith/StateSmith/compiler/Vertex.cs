using StateSmith.compiler;
using System.Collections.Generic;
using System.Diagnostics;

namespace StateSmith.Compiler
{
    public abstract class Vertex
    {
        public string DiagramId { get; set; }

        internal int _depth;
        public int Depth => _depth;

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
            if (behavior.TransitionTarget != this)
            {
                throw new BehaviorValidationException(behavior, "Inconsistent data structure. Behavior target must match incoming target");
            }
            _incomingTransitions.Add(behavior);
        }

        public Behavior AddTransitionTo(Vertex target)
        {
            var behavior = new Behavior(owningVertex: this, transitionTarget: target);
            AddBehavior(behavior);

            return behavior;
        }

        /// <summary>
        /// NOTE! Must manually update descendants after calling.
        /// </summary>
        /// <param name="child"></param>
        public T AddChild<T>(T child) where T : Vertex
        {
            if (child.Parent != null)
            {
                throw new VertexValidationException(child, "Cannot add a child that already has a parent");
            }

            child._parent = this;
            RenumberSubTreeDepth(child);
            _children.Add(child);
            return child;
        }

        protected void RenumberSubTreeDepth(Vertex subTreeRoot)
        {
            subTreeRoot._depth = Depth + 1;

            if (subTreeRoot.Children.Count == 0)
            {
                return;
            }

            LambdaVertexWalker walker = new LambdaVertexWalker
            {
                enterAction = v => v._depth = v.Parent.Depth + 1
            };

            walker.Walk(subTreeRoot);
        }

        /// <summary>
        /// NOTE! Must manually update descendants after calling.
        /// </summary>
        /// <param name="child"></param>
        public void RemoveChild(Vertex child)
        {
            _children.RemoveOrThrow(child);
            child._parent = null;

            foreach (var childBehavior in child.Behaviors)
            {
                var target = childBehavior.TransitionTarget;
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
