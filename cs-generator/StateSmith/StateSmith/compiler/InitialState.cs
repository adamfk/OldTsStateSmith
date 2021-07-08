namespace StateSmith.Compiler
{
    public class InitialState : Vertex
    {
        public override void Accept(VertexVisitor visitor)
        {
            visitor.Visit(this);
        }
    }
}
