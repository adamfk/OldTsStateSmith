using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Reflection;
using Microsoft.CodeAnalysis;
using Microsoft.CodeAnalysis.CSharp;
using Microsoft.CodeAnalysis.Emit;
using StateSmith.Input.Expansions;

namespace StateSmith.output
{
    public class CodeCompilationResult
    {
        public object createdObject;
        public List<Diagnostic> failures;
    }


    //useful links:
    // https://github.com/tugberkugurlu/DotNetSamples/blob/0883fb2e8c723420663e2d60140ce7591c7b311a/csharp/RoslynCompileSample/RoslynCompileSample/Program.cs
    // https://laurentkempe.com/2019/02/18/dynamically-compile-and-run-code-using-dotNET-Core-3.0/

    public class ExternalCodeCompiler
    {
        public CodeCompilationResult CompileCode(string sourceCode, string typeName)
        {
            AssemblyCacher assemblyCacher = new AssemblyCacher(sourceCode);

            CodeCompilationResult codeCompilationResult = new CodeCompilationResult();

            var assemblyBytes = assemblyCacher.TryGetFromCache();

            if (assemblyBytes != null)
            {
                TryLoadAssemblyCreateObject(typeName, assemblyBytes, codeCompilationResult);
            }
            else
            {
                TryCompileLoadAndCache(sourceCode, typeName, assemblyCacher, codeCompilationResult);
            }

            return codeCompilationResult;
        }

        private static void TryCompileLoadAndCache(string sourceCode, string typeName, AssemblyCacher assemblyCacher, CodeCompilationResult codeCompilationResult)
        {
            byte[] assemblyBytes = CompileCodeToAssembly(sourceCode, codeCompilationResult);
            if (assemblyBytes != null)
            {
                TryLoadAssemblyCreateObject(typeName, assemblyBytes, codeCompilationResult);
            }

            assemblyCacher.Cache(assemblyBytes);
        }

        private static void TryLoadAssemblyCreateObject(string typeName, byte[] assemblyBytes, CodeCompilationResult codeCompilationResult)
        {
            try
            {
                Assembly assembly = Assembly.Load(assemblyBytes);
                Type type = assembly.GetType(typeName);
                codeCompilationResult.createdObject = Activator.CreateInstance(type);
            }
            catch (BadImageFormatException ex)
            {
                Console.WriteLine(ex);
            }
        }

        private static byte[] CompileCodeToAssembly(string sourceCode, CodeCompilationResult codeCompilationResult)
        {
            byte[] assemblyBytes = null;

            SyntaxTree syntaxTree = CSharpSyntaxTree.ParseText(sourceCode);

            string assemblyName = Path.GetRandomFileName();
            MetadataReference[] references = new MetadataReference[]
            {
                MetadataReference.CreateFromFile(typeof(object).Assembly.Location),
                MetadataReference.CreateFromFile(typeof(Console).Assembly.Location),
                MetadataReference.CreateFromFile(typeof(Enumerable).Assembly.Location),
                MetadataReference.CreateFromFile(typeof(UserExpansionScriptBase).Assembly.Location),
                MetadataReference.CreateFromFile(typeof(System.Runtime.AssemblyTargetedPatchBandAttribute).Assembly.Location),
                //MetadataReference.CreateFromFile(typeof(Microsoft.CSharp.RuntimeBinder.CSharpArgumentInfo).Assembly.Location),    //this one does not seem to be needed
            };

            CSharpCompilation compilation = CSharpCompilation.Create(
                assemblyName,
                syntaxTrees: new[] { syntaxTree },
                references: references,
                options: new CSharpCompilationOptions(OutputKind.DynamicallyLinkedLibrary));

            using var memoryStream = new MemoryStream();

            EmitResult emitResult = compilation.Emit(memoryStream);

            if (emitResult.Success)
            {
                memoryStream.Seek(0, SeekOrigin.Begin);
                assemblyBytes = memoryStream.ToArray();
            }
            else
            {
                IEnumerable<Diagnostic> failures = emitResult.Diagnostics.Where(diagnostic =>
                    diagnostic.IsWarningAsError ||
                    diagnostic.Severity == DiagnosticSeverity.Error);

                codeCompilationResult.failures = failures.ToList();
            }

            return assemblyBytes;
        }
    }
}
