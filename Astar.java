import java.util.ArrayList;
import java.util.List;

public class Astar {
    private List<Pair<Node,Double>> listH;
    private Graph G;
    private Node Start;
    private Node Dest;

    public Astar(Graph g){
        this.G = g;
    }

    public void setH(String dest){
        Node destNode = G.findNode(dest);
        listH = new ArrayList<Pair<Node,Double>>();
        for (Node node : G.getNodeList()) {
            double distance = node.getCoordinate().getEucledianDistance(destNode.getCoordinate());
            listH.add(new Pair<Node,Double>(node, distance));
        }
    }

    public Double H(Node N){
        for (Pair<Node,Double> pair : this.listH) {
            if (pair.getFirst() == N) 
            {
                return pair.getSecond();
            }
        }
        return null;
    }

    public Node search(String start, String dest){
        this.Start = G.findNode(start);
        this.Dest = G.findNode(dest);
        

        return null;
    }
}