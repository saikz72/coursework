
public class BinaryTree {

	//instance variables
	BinaryTree left;
	BinaryTree right;
	bBall root;

	//parameters
	private static double total = 0;  
	private static double pSize = 0; 


	/*
	 * method to clear the balls from the display window
	 */
	public void removeAll(){
		if(left.root!=null){
			left.removeAll();	
		}
		root = null;
		if(right.right!=null){
			right.removeAll();
		}
	}
	/**
	 * method to stop the simulation 
	 */
	public void stopAll(){	
		if(left.root!=null)
			left.stopAll();
		root.interrupt();
		if(right.root!=null)
			right.stopAll();
	}
	/**
	 * method to reset the parameters pSize and total to zero
	 */
	public void reset() {
		total = 0;
		pSize = 0; 
	}
	/*
	 * method to insert all the randomly generated balls
	 * in a binary tree
	 */
	public void insertbBall(bBall input){
		if(root==null)
		{
			root = input;
			left = new BinaryTree();
			right = new BinaryTree();
		}else
		{
			if(input.bSize < root.bSize)
			{
				// insert to the left tree
				left.insertbBall(input);
			}
			else
			{
				// insert to the right tree
				right.insertbBall(input);
			}
		}
	}
	/**
	 * traverse_inorder method - recursively traverses tree in order and prints each node.
	 */
	public void traverseLMR()
	{
		// if there is any left
		// traverse left
		if(left.root != null)
			left.traverseLMR();
		// print the middle
		moveSort(root,root.bSize);
		// if there is any right
		// traverse right
		if(right.root != null)
			right.traverseLMR();
	}
	/*
	 * method to sort the balls in ascending order and displays 
	 * it on the window from left to right
	 */
	public void moveSort(bBall ball, double size) {

		int HEIGHT = 600;
		int SCALE = HEIGHT / 100; 

		// Reset total if method is called again

		if (pSize != size) {  	// if ball is a different size than the previous ball 			
			total += pSize;		// add previous balls width to total so balls appear next to each other 

			double ScrY = HEIGHT - (2 * size) * SCALE;
			double ScrX = 2 * (total) * SCALE;

			ball.moveTo(ScrX, ScrY); 		// move ball
		}
		else	{
			double ScrY = HEIGHT - (2 * size) * SCALE;
			double ScrX = 2 * (total) * SCALE;
			ball.moveTo(ScrX,ScrY);
		}  	// move ball off screen
		ball = null; 				// delete reference to ball

		pSize = size; 	

	}
	/*
	 * method to check if the simulation is running
	 * or otherwise
	 */
	public boolean isRunning(){

		if(left.root != null)
			if(left.isRunning())
				return true;
		if(root.isRunning)
			return true;

		if(right.root != null)
			if(right.isRunning())
				return true;

		return false;

	}

}

