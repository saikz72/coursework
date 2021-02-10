import java.util.*;

public class UnInformedSearch {

	public static void breadthFirstSearch(Node initialState, Node goalState) {
		List<Node> path = new ArrayList<Node>();
		Queue<Node> frontier = new LinkedList<Node>();
		frontier.add(initialState);
		Set<String> seen = new HashSet<String>();	//keeps track of already seen states
		if(goalTest(initialState, goalState)) {
			path.add(initialState);
			printTrace(path);
			return;
		}
		while(!frontier.isEmpty()) {
			Node node = frontier.poll();
			seen.add(node.getStateID());
			List<Node> children = node.getChildren();
			for(Node child : children) {
				if(!seen.contains(child.getStateID())) {
					frontier.add(child);
				}
				if(goalTest(child, goalState)) {
					path.addAll(getPathToSolution(child));
					printTrace(path);
					return;
				}
			}
		}
		System.out.println("No solution exists!!!");
	}

	public static void uniformSearch(Node initialState, Node goalState) {
		breadthFirstSearch(initialState, goalState);
	}

	public static void depthFirstSearch(Node initialState, Node goalState) {
		List<Node> path = new ArrayList<Node>();
		Stack<Node> frontier = new Stack<Node>();
		frontier.add(initialState);
		Set<String> seen = new HashSet<String>();
		if(goalTest(initialState, goalState)) {
			path.add(initialState);
			printTrace(path);
			return;
		}
		while(!frontier.isEmpty()) {
			Node node = frontier.pop();
			seen.add(node.getStateID());
			List<Node> children = node.getChildren();
			for(Node child : children) {
				if(!seen.contains(child.getStateID())) {
					frontier.add(child);
				}
				if(goalTest(child, goalState)) {
					path.addAll(getPathToSolution(child));
					printTrace(path);
					return;
				}
			}
		}
		System.out.println("No solution exists!!!");
	}

	public static void iterativeDepening(Node initialState, Node goalState, int limit) {
		if(goalTest(initialState, goalState)) {
			List<Node> path = new ArrayList<Node>();
			path.add(initialState);
			printTrace(path);
			return;
		}
		for(int i = 0; i < limit; ++i) {
			Set<String> seen = new HashSet<String>();
			List<Node> path = new ArrayList<Node>();
			Stack<Node> frontier = new Stack<Node>();
			frontier.add(initialState);
			while(!frontier.isEmpty()) {
				Node node = frontier.pop();
				seen.add(node.getStateID());
				List<Node> children = node.getChildren();
				for(Node child : children) {
					if(!seen.contains(child.getStateID()) && child.getDepth() <= i){
						frontier.add(child);
					}
					if(goalTest(child, goalState)) {
						path.addAll(getPathToSolution(child));
						printTrace(path);
						return;
					}
				}				
			}
		}
		System.out.println("No solution exists!!!");

	}

	//checks if we have reach the goal state
	private static boolean goalTest(Node currentState, Node goalState) {
		for(int i = 0; i < currentState.getPuzzle().length; ++i) {
			for(int j = 0; j < currentState.getPuzzle()[i].length; ++j) {
				if(currentState.getPuzzle()[i][j] != goalState.getPuzzle()[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	public static List<Node> getPathToSolution(Node node){
		int c =0;
		List<Node> path = new ArrayList<Node>();
		while(node.getParent() != null) {
			path.add(node);
			node = node.getParent();
			++c;
		}
		path.add(node);
		System.out.println(c);
		return path;
	}

	public static void printTrace(List<Node> path) {
		for(int i = path.size() - 1; i>= 0; --i) {
			Node node = path.get(i);
			node.print();
		}
	}
}
