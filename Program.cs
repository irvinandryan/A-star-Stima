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
            g.readFromFile("input.txt");
            //g.printGraph();

            foreach (var item in g.nodeList)
            {
                item.neighborInfo();
            }

            Console.WriteLine("\n----------------\n");

            Node path = g.AStar(g.findNode("Arad"), g.findNode("Bucharest"));
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
