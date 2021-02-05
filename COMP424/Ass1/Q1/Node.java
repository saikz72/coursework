package Q1;

import java.util.*;

public class Node {
    public static final int tile = 0; // empty tile in the puzzle
    private int[][] puzzle;
    private Node parent;
    private int costSoFar;
    private int depth;
    private List<Node> children;

    // constructor for a state
    public Node(int[][] puzzle) {
        this.puzzle = puzzle;
        children = new ArrayList<>();
        this.costSoFar = 0;
        this.depth = 0;
        this.parent = null;
    }

    // returns the puzzle
    public int[][] getPuzzle() {
        return this.puzzle;
    }

    // returns the depth of the node
    public int getDepth() {
        return depth;
    }

    // sets the depth value to the given parameter
    public void setDepth(int depth) {
        this.depth = depth;
    }

    // returns the path of the cose so far
    public int getCostSoFar() {
        return costSoFar;
    }

    // sets the value of the cost so far
    public void setCostSoFar(int costSoFar) {
        this.costSoFar = costSoFar;
    }

    // returns the parent of the current node
    public Node getParent() {
        return this.parent;
    }

    // sets the parent of the current node
    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getChildren() {
        Node n1 = moveUp(this.getPuzzle()); // moves the tile up
        Node n2 = moveDown(this.getPuzzle()); // moves the tile down
        Node n3 = moveLeft(this.getPuzzle()); // moves the tile left
        Node n4 = moveRight(this.getPuzzle()); // moves the tile right
        if (n1 != null) {
            n1.setParent(this);
            n1.setDepth(this.getDepth() + 1);
            n1.setCostSoFar(this.getCostSoFar() + 1);
            this.children.add(n1);
        }
        if (n2 != null) {
            n2.setParent(this);
            n2.setDepth(this.getDepth() + 1);
            n2.setCostSoFar(this.getCostSoFar() + 1);
            this.children.add(n2);
        }
        if (n3 != null) {
            n3.setParent(this);
            n3.setDepth(this.getDepth() + 1);
            n3.setCostSoFar(this.getCostSoFar() + 1);
            this.children.add(n3);
        }
        if (n4 != null) {
            n4.setParent(this);
            n4.setDepth(this.getDepth() + 1);
            n4.setCostSoFar(this.getCostSoFar() + 1);
            this.children.add(n4);
        }

        return children;
    }

    // need to check if the tile is in the second row, otherwise NOT possible to
    // move up
    private Node moveUp(int[][] puzzle) {
        for (int i = 0; i < puzzle[1].length; ++i) {
            if (puzzle[1][i] == 0) {
                // puzzle[1][i], puzzle[0][i],
                int[][] newPuzzle = swapPieces(1, i, 0, i, puzzle);
                return new Node(newPuzzle);
            }
        }
        // not possible to move up
        return null;
    }

    private Node moveDown(int[][] puzzle) {
        for (int i = 0; i < puzzle[0].length; ++i) {
            if (puzzle[0][i] == 0) {
                // puzzle[1][i], puzzle[0][i],
                int[][] newPuzzle = swapPieces(1, i, 0, i, puzzle);
                return new Node(newPuzzle);
            }
        }
        // not possible to move up
        return null;
    }

    private Node moveLeft(int[][] puzzle) {
        for (int i = 0; i < puzzle.length; ++i) {
            for (int j = 0; j < puzzle[i].length; ++j) {
                if (puzzle[i][j] == 0) {
                    if (i - 1 >= 0 && j - 1 >= 0) { // check if possible to move left
                        int[][] newPuzzle = swapPieces(i, j, i - 1, j - 1, puzzle);
                        return new Node(newPuzzle);
                    }
                }
            }
        }
        return null;
    }

    private Node moveRight(int[][] puzzle) {
        for (int i = 0; i < puzzle.length; ++i) {
            for (int j = 0; j < puzzle[i].length; ++j) {
                if (puzzle[i][j] == 0) {
                    if (i + 1 <= 3 && j + 1 >= 3) { // check if possible to move right
                        int[][] newPuzzle = swapPieces(i, j, i + 1, j + 1, puzzle);
                        return new Node(newPuzzle);
                    }
                }
            }
        }
        return null;
    }

    // tileI and tileJ represents the 0 tile's position
    // pieceI and pieceJ represents the piece we are swapping with the tile
    private int[][] swapPieces(int tileI, int tileJ, int pieceI, int pieceJ, int[][] puzzle) {
        int temp = puzzle[tileI][tileJ];
        puzzle[tileI][tileJ] = puzzle[pieceI][pieceJ];
        puzzle[pieceI][pieceJ] = temp;
        return puzzle;
    }

    public void print() {
        for (int i = 0; i < this.puzzle.length; ++i) {
            for (int j = 0; j < this.puzzle[i].length; ++j) {
                System.out.println(puzzle[i][j]);
            }
        }
    }
}
