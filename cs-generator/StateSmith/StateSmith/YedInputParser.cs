using System;
using System.Collections.Generic;
using System.Xml;
using System.Xml.Linq;
using System.Linq;


namespace StateSmith
{
    public class YedInputParser
    {
        public void ReadFile()
        {
            var filepath = "../../../../../../examples/1/ExampleSm.graphml";

            var root = XElement.Load(filepath);

            var keys = root.Elements("key");

            XNamespace baseNamespace = "http://graphml.graphdrawing.org/xmlns";

            keys = root.Elements(baseNamespace + "key");

            //((XCData)(root.Descendants(baseNamespace + "data").ToList()[2].FirstNode)).Value
            //"statemachine_definition"

            //https://stackoverflow.com/questions/5501502/linq-to-xml-accessing-descendants-with-a-prefix
            XNamespace y = "http://www.yworks.com/xml/graphml";
            var ynl = root.Descendants(y + "NodeLabel").ToList();
        }

    }

    public class YedXmlNav
    {
        private XElement root;

        public void Load(string filepath)
        {
            root = XElement.Load(filepath);
        }


    }

    public class YedEdge
    {
        public YedNode source;
        public YedNode target;
        public string label;
    }

    public class YedNode
    {
        public string id;
        public string label;
        public bool groupIsCollapsed;
        public YedNode parent;
        public List<YedNode> children = new List<YedNode>();
    }

    public class YedState
    {
        public bool isInitialState;
        public int orthogonalOrdering;
    }

    public class YedXmlRipper
    {
        XmlTextReader reader;
        Dictionary<string, YedNode> nodeMap = new Dictionary<string, YedNode>();
        Dictionary<string, YedEdge> edgeMap = new Dictionary<string, YedEdge>();
        YedNode currentNode = null;

        bool groupNodeIsClosed = false;
        string groupNodeLabel;
        bool inGroupNode = false;

        public void RipIt(string filepath)
        {
            reader = new XmlTextReader(filepath);


            while (reader.Read())
            {
                HandleRead();
            }

            //Close the reader.
            reader.Close();
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
                case "graph": GraphFound(); break;
                case "node": NodeFound(); break;
                case "NodeLabel": NodeLabelFound(); break;
                case "GroupNode": GroupNodeEntered(); break;
                case "State": StateNodeFound(); break;
            }
        }

        private void HandleEndElement()
        {
            switch (reader.LocalName)
            {
                case "GroupNode": GroupNodeExited(); break;
                case "graph": GraphNodeExited(); break;
            }
        }

        private void GraphFound()
        {
            var parentNode = currentNode;
            currentNode = new YedNode();
            currentNode.parent = parentNode;

            if (parentNode != null)
            {
                parentNode.children.Add(currentNode);
            }
        }

        private void GraphNodeExited()
        {
            nodeMap.Add(currentNode.id, currentNode);   //we should have the ID by now. Use it to add to map.
            currentNode = currentNode.parent;
        }

        private void NodeFound()
        {
            currentNode.id = reader.GetAttribute("id");
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
            var label = reader.Value;

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
