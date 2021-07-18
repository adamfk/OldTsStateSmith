﻿using System;
using System.Collections.Generic;
using System.Text;

namespace StateSmith.compiler
{
    public static class ExtensionMethods
    {
        public static void RemoveOrThrow<T>(this List<T> list, T item)
        {
            var success = list.Remove(item);
            if (!success)
            {
                throw new ArgumentException("List could not remove item: " + item);
            }
        }
    }
}
