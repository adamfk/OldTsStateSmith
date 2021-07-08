namespace StateSmith.Compiler
{
    public class NotesVertex : Vertex
    {
        public string notes;

        public override void Accept(VertexVisitor visitor)
        {
            visitor.Visit(this);
        }
    }
}
