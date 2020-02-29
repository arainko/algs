package com.arainko.rbtreesjava;


public class Node {
    public Node parent;
    public Node left;
    public Node right;
    public Color color = Color.BLACK;
    public int key;
    public boolean isWarden = false;

    public Node(int key) {
        this.key = key;
    }

    @Override
    public String toString() {
        String leftString;
        String rightString;

        if (left.isWarden) leftString = "LEAF";
            else leftString = left.key + " " + left.color.toString();

        if (right.isWarden) rightString = "LEAF";
            else rightString = right.key + " " + right.color.toString();

        return "(" + leftString + ")" + " <- " + this.key + " " + this.color.toString() + " -> " + "(" + rightString + ")";
    }

}
