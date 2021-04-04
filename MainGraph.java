public class MainGraph {
    public static void main(String[] args) {
        Node b = new Node("B", 1, 2);
        Node a = new Node("A", 2, 3);
        Node c = new Node("C", 3, 4);
        Node d = new Node("D", 4, 5);
        Node e = new Node("E", 5, 6);
        Node f = new Node("F", 6, 7);
        Node g = new Node("G", 7, 8);
        Node h = new Node("H", 8, 9);

        Graph graph = new Graph();

        graph.addPair(a, b);
        graph.addPair(a, c);
        graph.addPair(a, d);
        graph.addPair(b, c);
        graph.addPair(b, e);
        graph.addPair(b, f);
        graph.addPair(c, f);
        graph.addPair(c, g);
        graph.addPair(d, g);
        graph.addPair(d, f);
        graph.addPair(e, h);
        graph.addPair(e, f);
        graph.addPair(f, h);

        graph.printGraph();
    }
}
