package Q1;

import java.util.*;

public class Main {
    public static void main(String args[]) {
        int[][] arr = { { 1, 2, 3 }, { 4, 5, 0 } };
        Node node = new Node(arr);
        // node.print();
        List<Node> nodes = node.getChildren();
        int n = nodes.size();
        nodes.get(0).print();
        // System.out.println("break");
        // nodes.get(1).print();
        // // System.out.println("break");
        // // nodes.get(0).print();
        // // System.out.println("break");
        // // nodes.get(1).print();
        // System.out.println("break");
        // nodes.get(2).print();
    }
}
