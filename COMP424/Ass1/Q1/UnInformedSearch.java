package Q1;

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
	}
	
	public static void uniformSearch(Node initialState) {
		
	}
	
	public static void depthFirstSearch(Node initialState) {
		
	}
	
	public static void iterativeDepening(Node initialState) {
		
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
		List<Node> path = new ArrayList<Node>();
		while(node.getParent() != null) {
			path.add(node);
			node = node.getParent();
		}
		path.add(node);
		return path;
	}
	
	public static void printTrace(List<Node> path) {
		for(int i = path.size() - 1; i>= 0; --i) {
			Node node = path.get(i);
			node.print();
		}
	}
}
