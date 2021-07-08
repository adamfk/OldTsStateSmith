namespace StateSmith.Compiler
{
    public class NamedVertex : Vertex
    {
        public string name;
        public bool nameIsGloballyUnique = false;

        public override void Accept(VertexVisitor visitor)
        {
            visitor.Visit(this);
        }
    }
}
