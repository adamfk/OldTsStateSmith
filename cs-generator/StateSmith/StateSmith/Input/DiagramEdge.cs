using System;
using System.Collections.Generic;
using System.Xml;
using System.Xml.Linq;
using System.Linq;


namespace StateSmith.Input
{
    /// <summary>
    /// Some type of edge/arrow between nodes.
    /// This will originally come from yEd diagrams, but will eventually come from parsing draw.io diagrams.
    /// </summary>
    public class DiagramEdge
    {
        public string id;
        public DiagramNode source;
        public DiagramNode target;
        public string label = "";

        #region equals and hash
        public override bool Equals(object obj)
        {
            return obj is DiagramEdge edge &&
                   id == edge.id &&
                   EqualityComparer<DiagramNode>.Default.Equals(source, edge.source) &&
                   EqualityComparer<DiagramNode>.Default.Equals(target, edge.target) &&
                   label == edge.label;
        }

        public override int GetHashCode()
        {
            return HashCode.Combine(id, source, target, label);
        }

        #endregion equals and hash
    }
}
