using System;
using System.Collections.Generic;
using System.Text;
using Xunit;
using FluentAssertions;
using StateSmith.Input;

namespace StateSmithTest
{
    public class NodeLabelTextParserTest
    {
        NodeLabelTextParser parser = new NodeLabelTextParser();

        [Fact]
        public void Good()
        {
            var match = parser.TryMatchStatemachine("$STATEMACHINE:BurritoSm");
            match.statemachineName.Should().Be("BurritoSm");
            match.nextParseIndex.Should().Be(23);
        }

        [Fact]
        public void GoodWhiteSpaceAndFollowingText()
        {
            const string label = @"
            $STATEMACHINE
            :
            SomeName
            This is the rest";
            var match = parser.TryMatchStatemachine(label);
            match.statemachineName.Should().Be("SomeName");
            label.Substring(match.nextParseIndex).Trim().Should().Be("This is the rest");
        }

        [Fact]
        public void BadSpaceAfterDollarSign()
        {
            var match = parser.TryMatchStatemachine("$ STATEMACHINE:BurritoSm");
            match.statemachineName.Should().BeNull();
            match.nextParseIndex.Should().Be(0);
        }


    }
}
