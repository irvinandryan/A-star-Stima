import java.util.*;

public class Node {
    private List<Node> neighbors;
    private String name;
    private Point coordinate;
    private int neighborCount;
    private boolean isVisited;

    public Node(String name, int x, int y){
        this.coordinate = new Point(x, y);
        this.neighbors = new ArrayList<Node>();
        this.name = name;
        this.neighborCount = 0;
        this.isVisited = false;
    }

    /***** GETTER/SETTER *****/
    public List<Node> getNeighbors() {
        return this.neighbors;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNeighborCount() {
        return this.neighborCount;
    }

    public boolean getIsVisited() {
        return this.isVisited;
    }

    public void setIsVisited(boolean visit) {
        this.isVisited = visit;
    }

    public Point getCoordinate() {
        return this.coordinate;
    }

    public void setCoordinate(int x, int y) {
        this.coordinate.setX(x);
        this.coordinate.setY(y);
    }

    /***** METHODS *****/
    public void addEdge(Node n) {
        this.neighbors.add(n);
        this.neighborCount++;
    }

    public void removeEdge(Node n) {
        this.neighbors.remove(n);
        this.neighborCount--;
    }

    public void nodeInfo() {
        System.out.printf("Tetangga dari %s adalah", this.name);
        for (Node node : this.neighbors) {
            System.out.printf(" %s", node.getName());
        }
        System.out.println("");
    }
}
