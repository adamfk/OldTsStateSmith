using StateSmith;
using System;
using System.IO;
using System.Text;
using Xunit;
using Xunit.Abstractions;

namespace StateSmithTest
{
    public class UnitTest1
    {
        ITestOutputHelper output;

        public UnitTest1(ITestOutputHelper output)
        {
            this.output = output;
        }

        string filepath = "../../../../../../examples/1/ExampleSm.graphml";

        [Fact]
        public void TestYedInputParser()
        {
            YedInputParser yedInputParser = new YedInputParser();
            yedInputParser.ReadFile();
        }

        [Fact]
        public void TestRipper()
        {
            var converter = new ConsoleCaptureConverter(output);
            Console.SetOut(converter);

            YedXmlRipper yedXmlRipper = new YedXmlRipper();
            yedXmlRipper.RipIt(filepath);
        }
    }

    //https://stackoverflow.com/a/47529356
    public class ConsoleCaptureConverter : TextWriter
    {
        ITestOutputHelper _output;
        public ConsoleCaptureConverter(ITestOutputHelper output)
        {
            _output = output;
        }
        public override Encoding Encoding
        {
            get { return Encoding.UTF8; }
        }
        public override void WriteLine(string message)
        {
            _output.WriteLine(message);
        }
        public override void WriteLine(string format, params object[] args)
        {
            _output.WriteLine(format, args);
        }
    }

}
