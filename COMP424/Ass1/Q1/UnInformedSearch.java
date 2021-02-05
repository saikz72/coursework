
public class UnInformedSearch {
	
	public static void breadthFirstSearch(Node initialState) {
		
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
}
