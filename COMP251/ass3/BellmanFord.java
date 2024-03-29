import java.util.*;

public class BellmanFord {

    private int[] distances = null;
    private int[] predecessors = null;
    private int source;

    class BellmanFordException extends Exception {
        public BellmanFordException(String str) {
            super(str);
        }
    }

    class NegativeWeightException extends BellmanFordException {
        public NegativeWeightException(String str) {
            super(str);
        }
    }

    class PathDoesNotExistException extends BellmanFordException {
        public PathDoesNotExistException(String str) {
            super(str);
        }
    }

    BellmanFord(WGraph g, int source) throws NegativeWeightException {
        /*


         * Constructor, input a graph and a source Computes the Bellman Ford algorithm
         * to populate the attributes distances - at position "n" the distance of node
         * "n" to the source is kept predecessors - at position "n" the predecessor of
         * node "n" on the path to the source is kept source - the source node
         *
         * If the node is not reachable from the source, the distance value must be
         * Integer.MAX_VALUE
         */
        distances = new int[g.getNbNodes()];
        predecessors = new int[g.getNbNodes()];
        this.source = source;
        // step 1: intialize graph
        for (int i = 0; i < g.getNbNodes(); ++i) {
            distances[i] = Integer.MAX_VALUE;
            predecessors[i] = -1001; // let -1001 represent null
        }
        // distance of source
        distances[source] = 0;
        ArrayList<Edge> edges = g.getEdges();

        // step 2: relax edges n - 1 times
        for (int i = 0; i < g.getNbNodes() - 1; ++i) {
            for (int j = 0; j < edges.size(); ++j) {
                int weight = edges.get(j).weight; // weight
                int u = edges.get(j).nodes[0]; // from
                int v = edges.get(j).nodes[1]; // to
                // to prevent integer overflow
                if (distances[u] != Integer.MAX_VALUE && distances[u] + weight < distances[v]) {
                    distances[v] = distances[u] + weight;
                    predecessors[v] = u;
                }
            }
        }
        // step 3: check for negative-weight cycles
        int j = 0;
        while (j < edges.size()) {
            int weight = edges.get(j).weight; // weight
            int u = edges.get(j).nodes[0]; // from
            int v = edges.get(j).nodes[1]; // to
            if (distances[u] + weight < distances[v]) {
                throw new NegativeWeightException("The graph contains a negative cycle.");
            }
            j++;
        }
    }

    public int[] shortestPath(int destination) throws PathDoesNotExistException {
        /*
         * Returns the list of nodes along the shortest path from the object source to
         * the input destination If not path exists an Error is thrown
         */
        if (this.source == destination) {
            return new int[] { 0 };
        }
        List<Integer> list = new ArrayList<Integer>();
        list.add(destination);
        int temp = destination;
        while (predecessors[temp] != -1001) {
            temp = predecessors[temp];
            list.add(temp);
        }
        int n = list.size();
        if (n == 1) {
            throw new PathDoesNotExistException("There is no path between " + this.source + " and " + destination);
        }
        int[] arr = new int[n];
        int idx = 0;
        int i = n - 1;
        while (i >= 0) {
            arr[idx] = list.get(i);
            i--;
            idx++;
        }
        return arr;
    }

    public void printPath(int destination) {
        /*
         * Print the path in the format s->n1->n2->destination if the path exists, else
         * catch the Error and prints it
         */
        try {
            int[] path = this.shortestPath(destination);
            for (int i = 0; i < path.length; i++) {
                int next = path[i];
                if (next == destination) {
                    System.out.println(destination);
                } else {
                    System.out.print(next + "-->");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {

        String file = args[0];
        WGraph g = new WGraph(file);
        try {
            BellmanFord bf = new BellmanFord(g, g.getSource());
            bf.printPath(g.getDestination());
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
