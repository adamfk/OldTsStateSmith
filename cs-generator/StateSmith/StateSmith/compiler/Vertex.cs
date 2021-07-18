using StateSmith.compiler;
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

        public List<Behavior> incomingTransitions = new List<Behavior>();

        public abstract void Accept(VertexVisitor visitor);

        public Behavior AddTransitionTo(Vertex target)
        {
            var behavior = new Behavior()
            {
                owningVertex = this,
                transitionTarget = target
            };
            behaviors.Add(behavior);
            target.incomingTransitions.Add(behavior);

            return behavior;
        }
        public T AddChild<T>(T child) where T : Vertex
        {
            child.parent = this;
            children.Add(child);
            return child;
        }

        public void RemoveChild(Vertex child)
        {
            children.RemoveOrThrow(child);
            child.parent = null;

            foreach (var childBehavior in child.behaviors)
            {
                var target = childBehavior.transitionTarget;
                if (target != null)
                {
                    target.incomingTransitions.RemoveOrThrow(childBehavior);
                }
            }

            if (child.incomingTransitions.Count > 0)
            {
                throw new VertexValidationException(child, "cannot safely remove child as it still has incoming transitions");
            }
        }
    }
}
