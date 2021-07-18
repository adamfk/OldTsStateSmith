using System.Collections.Generic;
using System.Text;

namespace StateSmith.Compiler
{
    public abstract class VertexVisitor
    {
        public abstract void Visit(Vertex v);
        public abstract void Visit(NamedVertex v);
        public abstract void Visit(State v);
        public abstract void Visit(OrthoState v);
        public abstract void Visit(Statemachine v);
        public abstract void Visit(NotesVertex v);
        public abstract void Visit(InitialState v);

        public static void VisitVertexChildren(Vertex v, VertexVisitor visitor)
        {
            //copy list so that we can remove children while iterating.
            //TODO Not ideal. What if larger scale changes made? requires thought.
            var childrenCopy = new List<Vertex>(v.children);
            foreach (var child in childrenCopy)
            {
                child.Accept(visitor);
            }
        }

        public virtual void VisitChildren(Vertex v)
        {
            VisitVertexChildren(v, this);
        }
    }



    public class DummyVertexVisitor : VertexVisitor
    {
        public override void Visit(Vertex v) { VisitChildren(v); }
        public override void Visit(NamedVertex v) { VisitChildren(v); }
        public override void Visit(OrthoState v) { VisitChildren(v); }
        public override void Visit(Statemachine v) { VisitChildren(v); }
        public override void Visit(NotesVertex v) { VisitChildren(v); }
        public override void Visit(InitialState v) { VisitChildren(v); }
        public override void Visit(State v) { VisitChildren(v); }
    }

    public abstract class OnlyVertexVisitor
    {
        public abstract void Visit(Vertex v);

        public void Visit(NamedVertex v)
        {
            Visit((Vertex)v);
        }

        public void Visit(State v)
        {
            Visit((Vertex)v);
        }

        public void Visit(OrthoState v)
        {
            Visit((Vertex)v);
        }
        public void Visit(Statemachine v)
        {
            Visit((Vertex)v);
        }
        public void Visit(NotesVertex v)
        {
            Visit((Vertex)v);
        }
        public void Visit(InitialState v)
        {
            Visit((Vertex)v);
        }
    }

    //public class LambdaVertexVisitor : VertexVisitor
    //{
    //    public abstract void Visit(Vertex v);
    //    public abstract void Visit(NamedVertex v);
    //    public abstract void Visit(State v);
    //    public abstract void Visit(OrthoState v);
    //    public abstract void Visit(Statemachine v);
    //    public abstract void Visit(NotesVertex v);
    //    public abstract void Visit(InitialState v);
    //}

    public abstract class NamedVisitor : VertexVisitor
    {
        public override void Visit(State v)
        {
            Visit((NamedVertex)v);
        }

        public override void Visit(OrthoState v)
        {
            Visit((NamedVertex)v);
        }

        public override void Visit(Statemachine v)
        {
            Visit((NamedVertex)v);
        }
    }

    public class ShortDescribingVisitor : NamedVisitor
    {
        public StringBuilder stringBuilder;

        public ShortDescribingVisitor()
        {
            stringBuilder = new StringBuilder();
        }

        public ShortDescribingVisitor(StringBuilder stringBuilder)
        {
            this.stringBuilder = stringBuilder;
        }

        public override void Visit(Vertex v)
        {
            throw new System.NotImplementedException();
        }

        public override void Visit(NamedVertex v)
        {
            stringBuilder.Append($"{v.GetType().Name}:{v.name}");
        }

        public override void Visit(NotesVertex v)
        {
            stringBuilder.Append("$Notes");
        }

        public override void Visit(InitialState v)
        {
            stringBuilder.Append("$InitialState");
        }

        public static void Describe(StringBuilder stringBuilder, Vertex vertex)
        {
            var visitor = new ShortDescribingVisitor(stringBuilder);
            vertex.Accept(visitor);
        }
    }

    public class VertexPathDescriber
    {
        public static string Describe(Vertex vertex)
        {
            if (vertex == null)
            {
                return "";
            }

            Stack<Vertex> reversedVertices = GetReversedPathVertices(vertex);

            StringBuilder stringBuilder = new StringBuilder();
            ShortDescribingVisitor visitor = new ShortDescribingVisitor(stringBuilder);

            string appender = "";

            while (reversedVertices.Count > 0)
            {
                stringBuilder.Append(appender);
                appender = ".";
                vertex = reversedVertices.Pop();
                vertex.Accept(visitor);
            }

            return stringBuilder.ToString();
        }

        private static Stack<Vertex> GetReversedPathVertices(Vertex vertex)
        {
            Stack<Vertex> reversedVertices = new Stack<Vertex>();

            while (vertex != null)
            {
                reversedVertices.Push(vertex);
                vertex = vertex.parent;
            }

            return reversedVertices;
        }
    }
}
