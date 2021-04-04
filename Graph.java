import java.util.*;

public class Graph {
    private List<Node> nodeList;
    private int size;

    public Graph() {
        nodeList = new ArrayList<Node>();
        this.size = 0;
    }

    /***** SETTER/GETTER *****/
    public List<Node> getNodeList() {
        return this.nodeList;
    }

    public int getSize() {
        return this.size;
    }

    /***** METHODS *****/
    public void addPair(Node n1, Node n2) {
        addToNodeList(n1);
        addToNodeList(n2);
        addNeighbor(n1, n2);
        addNeighbor(n2, n1);
    }

    private void addToNodeList(Node n) {
        if (!this.nodeList.contains(n)) {
            this.nodeList.add(n);
        }
    }

    private void addNeighbor(Node n1, Node n2) {
        if (!n1.getNeighbors().contains(n2)) {
            n1.addEdge(n2);
        }
    }

    public void printGraph() {
        for (Node node : this.nodeList) {
            node.nodeInfo();
        }
    }
}
