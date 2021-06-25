﻿using System;
using System.Collections.Generic;
using System.Xml;
using System.Xml.Linq;
using System.Linq;


namespace StateSmith.Yed
{
    /// <summary>
    /// .
    /// </summary>
    public class YedEdge
    {
        public string id;
        public string sourceId;
        public string targetId;
        public string label = "";

        #region ctors
        public YedEdge() { }

        public YedEdge(string id, string sourceId, string targetId, string label)
        {
            this.id = id;
            this.sourceId = sourceId;
            this.targetId = targetId;
            this.label = label;
        }
        #endregion

        #region equals and hash
        public override bool Equals(object obj)
        {
            return obj is YedEdge edge &&
                   id == edge.id &&
                   sourceId == edge.sourceId &&
                   targetId == edge.targetId &&
                   label == edge.label;
        }

        public override int GetHashCode()
        {
            return HashCode.Combine(id, sourceId, targetId, label);
        }
        #endregion equals and hash
    }

    /// <summary>
    /// .
    /// </summary>
    public class YedNode
    {
        public string id;
        public string label = "";
        public bool groupIsCollapsed;
        public YedNode parent;
        public List<YedNode> children = new List<YedNode>();
    }

    /// <summary>
    /// .
    /// </summary>
    public class YedState
    {
        public bool isInitialState;
        public int orthogonalOrdering;
    }

    /// <summary>
    /// .
    /// </summary>
    public class YedParser
    {
        public Dictionary<string, YedNode> nodeMap = new Dictionary<string, YedNode>();
        public List<YedEdge> edges = new List<YedEdge>();

        private XmlTextReader reader;
        private YedNode currentNode = null;
        private YedEdge currentEdge = null;

        private bool groupNodeIsClosed = false;
        private string groupNodeLabel;
        private bool inGroupNode = false;

        public void Parse(string filepath)
        {
            //no using statement required
            reader = new XmlTextReader(filepath);
            while (reader.Read())
            {
                HandleRead();
            }
        }

        private void HandleRead()
        {
            if (reader.IsStartElement())
            {
                HandleStartElement();
            }
            else if (reader.NodeType == XmlNodeType.EndElement)
            {
                HandleEndElement();
            }
        }


        private void HandleStartElement()
        {
            switch (reader.LocalName)
            {
                case "node": NodeFound(); break;
                case "NodeLabel": NodeLabelFound(); break;
                case "GroupNode": GroupNodeEntered(); break;
                case "State": StateNodeFound(); break;
                case "edge": EdgeFound(); break;
                case "EdgeLabel": EdgeLabelFound(); break;
            }
        }

        private void EdgeLabelFound()
        {
            reader.MoveToContent();
            currentEdge.label = reader.ReadString();
        }

        private void EdgeFound()
        {
            currentEdge = new YedEdge();
            currentEdge.id = reader.GetAttribute("id");
            currentEdge.sourceId = reader.GetAttribute("source");
            currentEdge.targetId = reader.GetAttribute("target");
            edges.Add(currentEdge);
        }

        private void HandleEndElement()
        {
            switch (reader.LocalName)
            {
                case "GroupNode": GroupNodeExited(); break;
                case "node": NodeExited(); break;
                case "edge": currentEdge = null; break;
            }
        }


        private void NodeExited()
        {
            currentNode = currentNode.parent;
        }

        private void NodeFound()
        {
            var parentNode = currentNode;
            currentNode = new YedNode();
            currentNode.id = reader.GetAttribute("id");
            currentNode.parent = parentNode;
            nodeMap.Add(currentNode.id, currentNode);   //we should have the ID by now. Use it to add to map.

            if (parentNode != null)
            {
                parentNode.children.Add(currentNode);
            }
        }


        //<y:GroupNode> these do not nest so we don't need to worry about recursion
        private void GroupNodeEntered()
        {
            inGroupNode = true;
            groupNodeIsClosed = false;
        }

        private void GroupNodeExited()
        {
            //see comments in NodeLabelFound()
            if (groupNodeIsClosed == false)
            {
                currentNode.label = groupNodeLabel;
            }

            inGroupNode = false;
            groupNodeLabel = null;
            groupNodeIsClosed = false;
        }

        private void StateNodeFound()
        {
            groupNodeIsClosed = reader.GetAttribute("closed") == "true";
        }

        private void NodeLabelFound()
        {
            reader.MoveToContent();
            var label = reader.ReadString();

            //a regular non-group shape has a simple label
            if (!inGroupNode)
            {
                currentNode.label = label;
            }
            else
            {
                //a group-shape has a closed label and an open label. We only want the label for the opened shape.
                //track it in a temp variable and check whether it was for the open or closed shape upon group node exit
                groupNodeLabel = label;

                //<y:GroupNode>
                //  <y:NodeLabel alignment="left" autoSizePolicy="node_width" backgroundColor="#FFFF00" borderDistance="0.0" fontFamily="Dialog" fontSize="15" fontStyle="plain" hasLineColor="false" height="22.37646484375" horizontalTextPosition="center" iconTextGap="4" modelName="internal" modelPosition="t" textColor="#000000" verticalTextPosition="bottom" visible="true" width="1493.3883156948655" x="0.0" xml:space="preserve" y="0.0">     $STATEMACHINE: ExampleSm</y:NodeLabel>
                //  <y:State closed="false" closedHeight="50.0" closedWidth="50.0" innerGraphDisplayEnabled="false"/>
                //</y:GroupNode>
                //<y:GroupNode>
                //  <y:NodeLabel alignment="right" autoSizePolicy="node_width" backgroundColor="#EBEBEB" borderDistance="0.0" fontFamily="Dialog" fontSize="30" fontStyle="plain" hasLineColor="false" height="40.7529296875" horizontalTextPosition="center" iconTextGap="4" modelName="internal" modelPosition="t" textColor="#000000" verticalTextPosition="bottom" visible="true" width="366.20186772168097" x="0.0" xml:space="preserve" y="0.0">stuff</y:NodeLabel>
                //  <y:State closed="true" closedHeight="149.87888820521016" closedWidth="366.20186772168097" innerGraphDisplayEnabled="false"/>
                //</y:GroupNode>
            }
        }
    }
}
