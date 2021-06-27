using System;
using System.Collections.Generic;
using System.Xml;
using System.Xml.Linq;
using System.Linq;


namespace StateSmith.Input
{

    /// <summary>
    /// Some type of drawing node. Could end up being a Hsm State, a direction, a comment, ...
    /// This will originally come from yEd diagrams, but will eventually come from parsing draw.io diagrams.
    /// </summary>
    public class DiagramNode
    {
        public string id;
        public string label = "";
        public DiagramNode parent;
        public List<DiagramNode> children = new List<DiagramNode>();
    }
}
