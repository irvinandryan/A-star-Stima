using System;
using System.IO;
using System.Collections.Generic;

namespace AStar
{
    class Point
    {
        public int X { get; set; }
        public int Y { get; set; }

        public Point(){
            X = 0;
            Y = 0;
        }

        public Point(int x, int y){
            X = x;
            Y = y;
        }

        // public void setX(int x){
        //     this.X = x;
        // }

        // public void setY(int y){
        //     this.Y = y;
        // }

        // public int getX(){
        //     return this.X;
        // }

        // public int getY(){
        //     return this.Y;
        // }

        public void printPoint()
        {
            Console.Write("(");
            Console.Write(this.X);
            Console.Write(",");
            Console.Write(this.Y);
            Console.WriteLine(")");
        }

        public double getEucledianDistance(Point other){
            return Math.Sqrt(Math.Pow(X - other.X, 2) + Math.Pow(Y - other.Y, 2));
            //return Math.sqrt(Math.Pow(this.X - other.X, 2) + Math.Pow(this.Y - other.Y, 2));
        }
    }

    class Node : IComparable
    {
        public List<Edge> neighbors { get; }
        public String name { get; set; }
        public Point coordinate { get; set; }
        public int neighborCount => neighbors.Count;
        public bool isVisited { get; set; }
        public Node pred { get; set; }
        public double fn { get; set; }
        public double gn { get; set; }

        int IComparable.CompareTo(object obj)
        {
            Node n = (Node)obj;
            return fn.CompareTo(n.fn);
        }

        public Node(String n, int x, int y)
        {
            neighbors = new List<Edge>();
            name = n;
            coordinate = new Point(x, y);
            isVisited = false;
            pred = null;
            //fn = y;
        }

        public void addEdge(Node n, double d)
        {
            neighbors.Add(new Edge(n, d));
        }

        public void removeEdge(Node n)
        {
            foreach (Edge e in neighbors)
            {
                if (e.target.name == n.name)
                {
                    neighbors.Remove(e);
                    return;
                }
            }
        }

        public double hn(Node target)
        {
            return coordinate.getEucledianDistance(target.coordinate);
        }

        public void nodeInfo()
        {
            Console.Write($"Tetangga dari {name} adalah");
            foreach (var n in neighbors)
            {
                Console.Write($" {n.target.name}"); ;
            }
            Console.WriteLine("");
        }

        public void neighborInfo()
        {
            Console.WriteLine($"Tetangga dari {name} terdiri dari");
            foreach (var n in neighbors)
            {
                Console.WriteLine($"- {n.target.name} dengan jarak {n.weight}"); ;
            }
        }
    }

    class Edge
    {
        public Node target { get; set; }
        public double weight { get; set; }

        public Edge(Node n, double w)
        {
            target = n;
            weight = w;
        }
    }

    class Graph
    {
        public List<Node> nodeList { get; }
        public int size => nodeList.Count;

        public Graph()
        {
            nodeList = new List<Node>();
        }

        public void readFromFile(string filename)
        {
            // Baca file...
            string[] lines = File.ReadAllLines($"../../../{filename}");
            // Baca jumlah node yang akan dibaca
            int nodeCount = int.Parse(lines[0]);

            // Iterasi pembacaan setiap node
            for (int i = 1; i < nodeCount + 1; i++)
            {
                string line = lines[i];
                string[] lineItem = line.Split(" ");

                string name = lineItem[0];
                int x = int.Parse(lineItem[1]);
                int y = int.Parse(lineItem[2]);

                nodeList.Add(new Node(name, x, y));
            }

            // Pembacaan matrix
            int j = 0;
            for (int i = nodeCount + 1; i < lines.Length; i++)
            {
                string line = lines[i];
                string[] lineItem = line.Split(" ");

                for (int k = j; k < lineItem.Length; k++)
                {
                    if (int.Parse(lineItem[k]) != 0)
                    {
                        addPair(nodeList[j], nodeList[k]);
                    }
                }

                j++;
            }
        }

        public void addPair(Node n1, Node n2)
        {
            addToNodeList(n1);
            addToNodeList(n2);
            addNeighbor(n1, n2);
            addNeighbor(n2, n1);
        }

        public void printGraph()
        {
            foreach (Node node in nodeList)
            {
                node.nodeInfo();
            }
        }

        public Node findNode(String nm)
        {
            foreach (var node in nodeList)
            {
                if (node.name == nm)
                {
                    return node;
                }
            }

            return null;
        }

        public Node AStar(Node start, Node target)
        {
            unvisitAll();
            List<Node> openList = new List<Node>();
            start.gn = 0;
            start.fn = start.gn + start.hn(target);
            openList.Add(start);

            while (openList.Count != 0)
            {
                openList.Sort();
                Node current = openList[0];
                if (current.name == target.name)
                {
                    //solution = current
                    Console.WriteLine("**** FOUND ****");
                    return current;
                }

                foreach (Edge neighbor in current.neighbors)
                {
                    Node nextNode = neighbor.target;
                    double newGn = current.gn + neighbor.weight;

                    if (!openList.Contains(nextNode) && !nextNode.isVisited)
                    {
                        nextNode.pred = current;
                        nextNode.gn = newGn;
                        nextNode.fn = nextNode.gn + nextNode.hn(target);
                        openList.Add(nextNode);
                    }
                    else
                    {
                        if (newGn < nextNode.gn)
                        {
                            nextNode.pred = current;
                            nextNode.gn = newGn;
                            nextNode.fn = nextNode.gn + nextNode.hn(target);

                            if (nextNode.isVisited)
                            {
                                nextNode.isVisited = false ;
                                openList.Add(nextNode);
                            }
                        }
                    }
                }
                openList.Remove(current);
                current.isVisited = true;
            }

            return null;
        }

        public void printPath(Node target)
        {
            List<Node> path = new List<Node>();
            double totalGn = target.gn;
            path.Add(target);
            while (target.pred != null)
            {
                path.Add(target.pred);
                target = target.pred;
            }

            path.Reverse();
            foreach (var pathItem in path)
            {
                Console.Write(pathItem.name + " ");
            }

            Console.WriteLine($"\nDengan total jarak adalah {totalGn}");
        }

        /***** PRIVATE METHODS *****/
        private void addNeighbor(Node n1, Node n2)
        {
            foreach (var e in n1.neighbors)
            {
                if (e.target.name == n2.name)
                {
                    return;
                }
            }

            n1.addEdge(n2, n1.coordinate.getEucledianDistance(n2.coordinate));
        }

        private void addToNodeList(Node n)
        {
            if (!nodeList.Contains(n))
            {
                nodeList.Add(n);
            }
        }

        private void unvisitAll()
        {
            foreach (var node in nodeList)
            {
                node.isVisited = false;
            }
        }
    }
}