using System;
using AStar;
using System.Collections.Generic;

namespace AStarStima
{
    class Program
    {
        static void Main(string[] args)
        {
            var g = new Graph();

            bool invalidFileName = true;
            while (invalidFileName)
            {
                Console.Write("Silakan masukkan nama file: ");
                string input = Console.ReadLine();
                try
                {
                    g.readFromFile($"{input}");
                    invalidFileName = false; ;
                }
                catch (Exception)
                {
                    Console.WriteLine("Nama file tidak ditemukan!");
                }
            }

            g.printNodeList();
            Node start = null;
            Node target = null;

            while (start == null)
            {
                Console.Write("Silakan masukkan start node: ");
                string input = Console.ReadLine();
                start = g.findNode(input);
                if (start == null)
                {
                    Console.WriteLine("Input invalid!");
                }
            }
            while (target == null)
            {
                Console.Write("Silakan masukkan target node: ");
                string input = Console.ReadLine();
                target = g.findNode(input);
                if (target == null)
                {
                    Console.WriteLine("Input invalid!");
                }
            }

            g.printGraph();

            Node path = g.AStar(start, target);
            if (path != null)
            {
                g.printPath(path);
            }
            else
            {
                Console.WriteLine("Ga ketemu");
            }
        }
    }
}
