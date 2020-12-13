import java.util.*;
import java.io.File;

public class FordFulkerson {

	public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph) {
		ArrayList<Integer> path = new ArrayList<Integer>();
		ArrayList<Integer> stack = new ArrayList<Integer>();
		__init__(stack, path, source);
		pathDFSUtil(stack, path, source, destination, graph);
		return path;
	}

	public static void __init__(ArrayList<Integer> stack, List<Integer> path, Integer source) {
		stack.add(source);
		path.add(source);
	}

	public static List<Edge> sortedEdges(WGraph graph) {
		ArrayList<Edge> edges = new ArrayList<Edge>(graph.getEdges());
		Collections.sort(edges, new Comparator<Edge>() {
			public int compare(Edge e1, Edge e2) {
				return e2.weight.compareTo(e1.weight);
			}
		});
		return edges;
	}

	// verify node is not destination
	public static boolean isDestination(Integer v, Integer destination) {
		return v == destination ? true : false;
	}

	public static void pathDFSUtil(ArrayList<Integer> stack, List<Integer> path, Integer source, Integer destination,
			WGraph graph) {
		while (!stack.isEmpty()) {
			Integer node1 = stack.remove(0);
			if (isDestination(node1, destination)) {
				break;
			}
			List<Edge> sortedEdges = sortedEdges(graph);
			for (Edge e : sortedEdges) {
				if (isDestination(node1, e.nodes[0])) {
					Integer node2 = e.nodes[1];
					if (!path.contains(node2) && e.weight != 0) {
						__init__(stack, path, node2);
						break;
					}
				}
			}
		}
	}

	public static String fordfulkerson(WGraph graph) {
		String answer = "";
		int maxFlow = 0;
		Integer source = graph.getSource();
		Integer destination = graph.getDestination();
		WGraph resGraph = new WGraph(graph);
		__init__(graph);
		ArrayList<Integer> path = pathDFS(source, destination, resGraph);
		maxFlow = fordfulkersonUtil(graph, resGraph, maxFlow, source, destination, path);
		answer += maxFlow + "\n" + graph.toString();
		return answer;
	}

	public static void __init__(WGraph graph) {
		for (Edge e : graph.getEdges()) {
			e.weight = 0;
		}
	}

	public static boolean augPath(ArrayList<Integer> path, Integer source, WGraph graph) {
		return (path.contains(graph.getSource()) && path.size() == 1) ? false : true;
	}

	// if destination is in the path
	public static int bottleNeck(WGraph resGraph, ArrayList<Integer> path) {
		int bottleNeck = resGraph.getEdge(path.get(0), path.get(1)).weight;
		for (int i = 0; i < path.size() - 1; ++i) {
			if (resGraph.getEdge(path.get(i), path.get(i + 1)).weight < bottleNeck) {
				bottleNeck = resGraph.getEdge(path.get(i), path.get(i + 1)).weight;
			}
		}
		return bottleNeck;
	}

	public static int fordfulkersonUtil(WGraph graph, WGraph resGraph, int maxFlow, Integer source, Integer destination,
			ArrayList<Integer> path) {
		while (augPath(path, resGraph.getSource(), resGraph)) {
			if (path.contains(resGraph.getDestination())) {
				int bottleNeck = bottleNeck(resGraph, path);
				maxFlow += bottleNeck;
				for (int i = 0; i < path.size() - 1; ++i) {
					graph.getEdge(path.get(i), path.get(i + 1)).weight += bottleNeck;
					resGraph.getEdge(path.get(i), path.get(i + 1)).weight -= bottleNeck;
				}
			}
			if (!path.contains(resGraph.getDestination()) && augPath(path, resGraph.getSource(), resGraph)) {
				for (int i = 0; i < path.size() - 1; ++i) {
					resGraph.getEdge(path.get(i), path.get(i + 1)).weight = 0;
				}
			}
			path = pathDFS(source, destination, resGraph);
		}
		return maxFlow;
	}

	public static void main(String[] args) {
		String file = args[0];
		File f = new File(file);
		WGraph g = new WGraph(file);
		System.out.println(fordfulkerson(g));
	}
}
