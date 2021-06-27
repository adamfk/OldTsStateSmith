using StateSmith.Input;
using System;
using System.Collections.Generic;
using System.Text;
using System.Text.RegularExpressions;

namespace StateSmith
{

    class HsmState
    {
        public DiagramNode diagramNode;
    }

    class HsmTransition
    {
        public DiagramEdge diagramEdge;
    }


    class Hsm
    {
        public HsmState rootState;
    }

}
