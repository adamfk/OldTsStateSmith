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
}
