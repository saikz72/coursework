
public class Main {
	public static void main(String args[]) {
		int[][] arr = { { 1, 4,  2}, { 5, 3, 0  } };
		int [][] arr2 = {{0,1, 2}, {5, 4, 3}};
		Node initialState = new Node(arr);
		Node goal = new Node(arr2);
		UnInformedSearch.depthFirstSearch(initialState, goal);
		UnInformedSearch.breadthFirstSearch(initialState, goal);
		UnInformedSearch.iterativeDepening(initialState, goal, 1000);
		UnInformedSearch.uniformSearch(initialState, goal);
	}
}
