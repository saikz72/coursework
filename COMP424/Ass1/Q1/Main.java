package Q1;

public class Main {
	public static void main(String args[]) {
		int[][] arr = { { 1, 4,  2}, { 5, 3, 0  } };
		int [][] arr2 = {{1, 2, 0}, {5, 4, 3}};
		Node node = new Node(arr);
		Node goal = new Node(arr2);
		UnInformedSearch.breadthFirstSearch(node, goal);
	}
}
