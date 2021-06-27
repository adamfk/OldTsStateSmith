using System;
using System.Collections.Generic;
using System.Text;
using Xunit;
using FluentAssertions;
using StateSmith;

namespace StateSmithTest
{
    public class NodeLabelTextParserTest
    {
        [Fact]
        public void TestStatemachineParse()
        {
            var parser = new NodeLabelTextParser();
            var possibleMatch = parser.TryMatchStatemachine("$STATEMACHINE:BurritoSm");
            possibleMatch.HasValue.Should().BeTrue();
            var (statemachineName, nextParseIndex) = possibleMatch.Value;
            statemachineName.Should().Be("BurritoSm");
            nextParseIndex.Should().Be(23);
        }
    }
}
