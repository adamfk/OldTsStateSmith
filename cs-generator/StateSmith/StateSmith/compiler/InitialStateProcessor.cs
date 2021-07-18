using System.Collections.Generic;

namespace StateSmith.Compiler
{
    public class InitialStateProcessor : DummyVertexVisitor
    {
        public override void Visit(InitialState initialState)
        {
            var parent = initialState.parent;

            var initialStateTransition = ValidateInitialState(initialState);

            // don't process simplification if this is the root initial state
            //TODO low. Create method to detect if parent is root/state machine.
            if (parent.parent == null)
            {
                return;
            }

            var newTarget = initialStateTransition.transitionTarget;

            var parentIncomingTransitions = new List<Behavior>(parent.incomingTransitions); //copy so that we can modify original
            foreach (var incomingTransition in parentIncomingTransitions)
            {
                //transitions to parent will be moved to transitions to initial state target

                //validate behavior variables. TODO low cleanup. Seems confusing.
                if (incomingTransition.transitionTarget != parent)
                {
                    throw new BehaviorValidationException(incomingTransition, "Inconsistent behavior 549846");
                }
                incomingTransition.actionCode += initialStateTransition.actionCode;

                incomingTransition.RetargetTo(newTarget);
            }

            parent.RemoveChild(initialState);
        }

        private static Behavior ValidateInitialState(InitialState initialState)
        {
            if (initialState.children.Count > 0)
            {
                throw new VertexValidationException(initialState, "Initial states vertices cannot contain children.");
            }

            var parent = initialState.parent;
            if (parent == null)
            {
                throw new VertexValidationException(initialState, "Initial states must have a parent state.");
            }

            if (initialState.behaviors.Count != 1)
            {
                throw new VertexValidationException(initialState, "Initial states must have exactly one behavior for now");
            }

            var behavior = initialState.behaviors[0];

            if (behavior.HasGuardCode())
            {
                throw new VertexValidationException(initialState, "Initial states cannot have guard code for now");
            }

            if (behavior.HasAtLeastOneTrigger())
            {
                throw new VertexValidationException(initialState, "Initial states cannot have triggers for now");
            }

            if (initialState.incomingTransitions.Count > 0)
            {
                throw new VertexValidationException(initialState, "Initial states cannot have any incoming transitions for now");
            }

            //TODO prevent initial state transition to parent or outside of parent
            //TODO need easy and fast method of checking state ancestry

            return behavior;
        }
    }
}
