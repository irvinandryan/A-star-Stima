import java.util.*;

public class Node {
    private List<Pair<Node,Double>> neighbors;
    private String name;
    private Point coordinate;
    private int neighborCount;
    private boolean isVisited;
    public Node pred;
    public Double fn;
    public Double gn;

    public Node(String name, int x, int y){
        this.coordinate = new Point(x, y);
        this.neighbors = new ArrayList<Pair<Node,Double>>();
        this.name = name;
        this.neighborCount = 0;
        this.isVisited = false;
        this.pred = null;
    }

    /***** GETTER/SETTER *****/
    public List<Pair<Node,Double>> getNeighbors() {
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
    public void addEdge(Node n, Double w) {
        this.neighbors.add(new Pair<Node,Double>(n, w));
        this.neighborCount++;
    }

    public void removeEdge(Node n) {
        for (Pair<Node,Double> pair : neighbors) {
            if (pair.getFirst().getName() == n.getName()){
                this.neighbors.remove(pair);
                this.neighborCount--;
            }
        }
    }

    public void nodeInfo() {
        System.out.printf("Tetangga dari %s adalah", this.name);
        for (Pair<Node,Double> pair : neighbors) {
            System.out.printf(" %s", pair.getFirst().getName());
        }
        System.out.println("");
    }

    public double hn(Node target){
        return this.getCoordinate().getEucledianDistance(target.getCoordinate());
    }
}
