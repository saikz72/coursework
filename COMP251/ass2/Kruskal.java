import java.util.*;

public class Kruskal {

    public static WGraph kruskal(WGraph g) {
        WGraph MST = new WGraph();
        ArrayList<Edge> edges = g.listOfEdgesSorted();
        int numNodes = g.getNbNodes(); // number of nodes in graph
        DisjointSets p = new DisjointSets(numNodes); // construct a disjoint data structure
        for (Edge e : edges) {
            if (IsSafe(p, e)) {
                p.union(e.nodes[0], e.nodes[1]);
                MST.addEdge(e);
            }
        }
        return MST;
    }

    public static Boolean IsSafe(DisjointSets p, Edge e) {
        int v1 = e.nodes[0];
        int v2 = e.nodes[1];
        return p.find(v1) != p.find(v2);
    }

    public static void main(String[] args) {

        String file = args[0];
        WGraph g = new WGraph(file);
        WGraph t = kruskal(g);
        System.out.println(t);

    }
}
