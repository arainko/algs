package com.arainko.rbtreesjava;


public class Node {
    public Node parent;
    public Node left;
    public Node right;
    public Color color = Color.BLACK;
    public int key;

    public Node(int key) {
        this.key = key;
    }

    @Override
    public String toString() {
        String leftString;
        String rightString;

        if (left == null) leftString = "LEAF";
            else leftString = left.key + "";

        if (right == null) rightString = "LEAF";
            else rightString = right.key + "";

        return "(" + leftString + ")" + " <- " + this.key + " -> " + "(" + rightString + ")";
    }
}
