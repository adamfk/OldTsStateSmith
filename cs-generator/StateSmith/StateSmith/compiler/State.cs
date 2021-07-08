namespace StateSmith.Compiler
{
    public class State : NamedVertex
    {
        public override void Accept(VertexVisitor visitor)
        {
            visitor.Visit(this);
        }
    }
}
