using StateSmith.Input;
using System.Collections.Generic;
using System.Text;

namespace StateSmith.Input
{
    partial class DiagramToHsmConverter
    {
        private Hsm hsm;

        public void ConvertTree(DiagramNode root)
        {
            //nodes to parse: $GEN_INCLUDE
        }

        private void ProcessTopNode(DiagramNode topNode)
        {
            var parser = new NodeLabelTextParser();

        }


        public List<Hsm> Convert(List<DiagramNode> topNodes)
        {
            //only nodes allowed at top level are $NOTES and $STATEMACHINE

            foreach (var topNode in topNodes)
            {
                ProcessTopNode(topNode);
            }

            return null;
        }
    }

}
