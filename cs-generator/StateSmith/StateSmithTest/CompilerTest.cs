using System;
using System.Collections.Generic;
using System.Text;
using Xunit;
using FluentAssertions;
using StateSmith;
using StateSmith.Compiler;

namespace StateSmithTest
{
    public class CompilerTest
    {
        [Fact]
        public void Test()
        {
            string filepath = "../../../../../../examples/specifications/Tiny1.graphml";

            Compiler compiler = new Compiler();
            compiler.CompileFile(filepath);

            compiler.rootVertices.Count.Should().Be(2);

            var sm = (Statemachine)compiler.rootVertices[0];
            sm.name.Should().Be("Tiny1");

            //TODO edges
        }
    }
}
