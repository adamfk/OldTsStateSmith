﻿using StateSmith.Compiler;
using System;
using System.Collections.Generic;
using System.Text;
using System.Text.RegularExpressions;
using System.Linq;

namespace StateSmith.compiler
{
    /// <summary>
    /// Helper extension methods
    /// </summary>
    public static class VertexLinqHelpers
    {
        public static List<NamedVertex> Descendants(this IEnumerable<Vertex> vertices, string name)
        {
            List<NamedVertex> list = vertices.OfType<NamedVertex>().Where(v => v.name == name).ToList();
            foreach (var v in vertices)
            {
                var matches = v.DescendantsWithName(name);
                list.AddRange(matches);
            }

            return list;
        }


        public static NamedVertex Descendant(this IEnumerable<Vertex> vertices, string name)
        {
            List<NamedVertex> list = vertices.Descendants(name);

            if (list.Count > 1)
            {
                throw new ArgumentException($"More than 1 named vertex found with name: `{name}`");
            }

            if (list.Count == 0)
            {
                throw new ArgumentException($"No named vertex found with name: `{name}`");
            }

            return list[0];
        }

        public static NamedVertex Descendant(this Vertex vertex, string name)
        {
            var matches = vertex.DescendantsWithName(name);

            ThrowIfNotSingle(name, matches);

            return matches[0];
        }

        private static void ThrowIfNotSingle(string name, List<NamedVertex> matches)
        {
            if (matches.Count > 1)
            {
                throw new ArgumentException($"More than 1 named vertex found with name: `{name}`");
            }

            if (matches.Count == 0)
            {
                throw new ArgumentException($"No named vertex found with name: `{name}`");
            }
        }

        private static void ThrowIfNotSingle<T>(List<T> matches) where T : Vertex
        {
            var typeName = typeof(T).FullName;
            if (matches.Count > 1)
            {
                throw new ArgumentException($"More than 1 vertex found with type `{typeName}`");
            }

            if (matches.Count == 0)
            {
                throw new ArgumentException($"No vertex found with type `{typeName}`");
            }
        }

        public static IEnumerable<T> Children<T>(this Vertex vertex) where T : Vertex
        {
            return vertex.Children.OfType<T>();
        }

        public static IEnumerable<NamedVertex> NamedChildren(this Vertex vertex, string name)
        {
            return vertex.Children.OfType<NamedVertex>().Where(v => v.name == name);
        }

        public static NamedVertex Child(this Vertex vertex, string name)
        {
             var list = vertex.Children.OfType<NamedVertex>().Where(v => v.name == name).ToList();
            ThrowIfNotSingle(name, list);
            return list[0];
        }

        public static T ChildType<T>(this Vertex vertex) where T : Vertex
        {
            var list = vertex.Children.OfType<T>().ToList();
            ThrowIfNotSingle<T>(list);
            return list[0];
        }
    }
}
