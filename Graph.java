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
            n1.addEdge(n2,n2.getCoordinate().getEucledianDistance(n1.getCoordinate()));
        }
    }

    public void printGraph() {
        for (Node node : this.nodeList) {
            node.nodeInfo();
        }
    }

    public Node findNode(String name){
        for (Node node : nodeList) {
            if (node.getName() == name)
            {
                return node;
            }
        }
        return null;
    }

    public void AStar(Node start, Node target){
        PriorityQueue<Node> openList = new PriorityQueue<Node>();
        start.gn = Double.valueOf(0);
        start.fn = start.gn + start.hn(target);
        openList.add(start);

        while (!openList.isEmpty()){
            Node current = openList.peek();
            if (current.getName() == target.getName()) {
                //solution = current
                return;
            }

            for (Pair<Node,Double> neighbor : current.getNeighbors()) {
                Node nextNode = neighbor.getFirst();
                double newGn = current.gn.doubleValue() + neighbor.getSecond().doubleValue();

                if (!openList.contains(nextNode) && !nextNode.getIsVisited()) {
                    nextNode.pred = current;
                    nextNode.gn = Double.valueOf(newGn);
                    nextNode.fn = nextNode.gn + nextNode.hn(target);
                    openList.add(nextNode);
                } else {
                    if(newGn < nextNode.gn.doubleValue()){
                        nextNode.pred = current;
                        nextNode.gn = Double.valueOf(newGn);
                        nextNode.fn = nextNode.gn + nextNode.hn(target);

                        if(nextNode.getIsVisited()){
                            nextNode.setIsVisited(false);
                            openList.add(nextNode);
                        }
                    }
                }
            }
            openList.remove(current);
            current.setIsVisited(true);
        }
    }
}
