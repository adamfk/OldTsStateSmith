namespace StateSmith.Compiler
{
    public class State : NamedVertex
    {
        public State()
        {

        }

        public State(string name)
        {
            this.name = name;
        }

        public override void Accept(VertexVisitor visitor)
        {
            visitor.Visit(this);
        }
    }
}
