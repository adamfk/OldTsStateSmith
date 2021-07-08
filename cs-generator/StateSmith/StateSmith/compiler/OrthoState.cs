namespace StateSmith.Compiler
{
    public class OrthoState : State
    {
        public double order;

        public override void Accept(VertexVisitor visitor)
        {
            visitor.Visit(this);
        }
    }
}
