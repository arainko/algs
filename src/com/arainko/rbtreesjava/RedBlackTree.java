package com.arainko.rbtreesjava;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("SuspiciousNameCombination")
public class RedBlackTree {
    public Node root;
    public Node warden;

    public RedBlackTree() {
        warden = new Node(0);
        warden.isWarden = true;
        root = warden;
    }

    void bstInsert(Node node) {
        node.left = warden;
        node.right = warden;
        node.color = Color.RED;

        Node current = root;
        Node currentParent = null;

        while (current != warden) {
            currentParent = current;
            if (node.key < current.key) current = current.left;
            else current = current.right;
        }

        node.parent = currentParent;

        if (currentParent == null) root = node;
        else if (node.key < currentParent.key) currentParent.left = node;
        else currentParent.right = node;
    }

    void rotateLeft(Node rotationPoint) {
        Node y = rotationPoint.right;
        rotationPoint.right = y.left;

        if (y.left != warden) y.left.parent = rotationPoint;

        y.parent = rotationPoint.parent;

        if (rotationPoint.parent == null) this.root = y;
        else if (rotationPoint == rotationPoint.parent.left) rotationPoint.parent.left = y;
        else rotationPoint.parent.right = y;

        y.left = rotationPoint;
        rotationPoint.parent = y;
    }

    void rotateRight(Node rotationPoint) {
        Node y = rotationPoint.left;
        rotationPoint.left = y.right;

        if (y.right != warden) y.right.parent = rotationPoint;

        y.parent = rotationPoint.parent;

        if (rotationPoint.parent == null) this.root = y;
        else if (rotationPoint == rotationPoint.parent.right) rotationPoint.parent.right = y;
        else rotationPoint.parent.left = y;

        y.right = rotationPoint;
        rotationPoint.parent = y;
    }

    public void rbInsert(Node node) {
        bstInsert(node);
        Node uncle;

        while (node != root && node.parent.color == Color.RED) {
            if (node.parent == node.parent.parent.right) {
                uncle = node.parent.parent.left; // Left side Uncle
                if (uncle.color == Color.RED) { // Uncle is RED -> Recolor F, GF, U
                    uncle.color = Color.BLACK;
                    node.parent.color = Color.BLACK;
                    node.parent.parent.color = Color.RED;
                    node = node.parent.parent;
                } else { // Uncle is BLACK ->
                    if (node == node.parent.left) { // -> inserted node is a left child (TRIANGLE CASE) -> rotate Father
                        node = node.parent;
                        rotateRight(node);
                    }
                    node.parent.color = Color.BLACK; // -> (LINE CASE) -> rotate Grandfather and recolor F, GF
                    node.parent.parent.color = Color.RED;
                    rotateLeft(node.parent.parent);
                }
            } else {
                uncle = node.parent.parent.right; // Right side Uncle
                if (uncle.color == Color.RED) {
                    uncle.color = Color.BLACK;
                    node.parent.color = Color.BLACK;
                    node.parent.parent.color = Color.RED;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.right) {
                        node = node.parent;
                        rotateLeft(node);
                    }
                    node.parent.color = Color.BLACK;
                    node.parent.parent.color = Color.RED;
                    rotateRight(node.parent.parent);
                }
            }
        }
        root.color = Color.BLACK; // Basic case, if ROOT is RED recolor it
    }

    public int getMinDepth(Node node) {
        if (node == null) return -1;

        int leftDepth = getMinDepth(node.left);
        int rightDepth = getMinDepth(node.right);

        if (leftDepth < rightDepth) return ++leftDepth;
        else return ++rightDepth;
    }

    public int getMaxDepth(Node node) {
        if (node == null) return -1;

        int leftDepth = getMaxDepth(node.left);
        int rightDepth = getMaxDepth(node.right);

        if (leftDepth > rightDepth) return ++leftDepth;
        else return ++rightDepth;
    }

    public int getRedNodeCount(Node node) {
        int count = 0;
        if (node == null) return 0;

        count += getRedNodeCount(node.left);
        count += getRedNodeCount(node.right);

        if (node.color == Color.RED) count++;

        return count;
    }

    public void printTree() {
        if (root != null) innerPrintTree(root, 1);
    }

    private void innerPrintTree(Node visited, int level) {
        if (visited.left == warden && visited.right == warden)
            System.out.print("");
        else {
            System.out.println(visited.toString() + " LEVEL: " + level);
            if (visited.left != warden) innerPrintTree(visited.left, level+1);
            if (visited.right != warden) innerPrintTree(visited.right, level+1);
        }
    }

    public static void main(String[] args) {
        var smallerTestSample = List.of(38, 31, 22, 8, 20, 5, 10, 9, 21, 27, 29, 25, 28).stream()
                .map(Node::new)
                .collect(Collectors.toList());

        RedBlackTree smallerTree = new RedBlackTree();

        smallerTestSample.forEach(node -> {
            System.out.println("Inserting " + node.key);
            smallerTree.rbInsert(node);
            smallerTree.printTree();
            System.out.println();
        });

        System.out.println("SMALLER SAMPLE");
        System.out.println("Red count: " + smallerTree.getRedNodeCount(smallerTree.root));
        System.out.println("Min depth: " + smallerTree.getMinDepth(smallerTree.root));
        System.out.println("Max depth: " + smallerTree.getMaxDepth(smallerTree.root));

        var biggerTestSample = Arrays.stream(Utilities.generateAscendingArray(1000))
                .mapToObj(Node::new)
                .collect(Collectors.toList());

        RedBlackTree biggerTree = new RedBlackTree();

        biggerTestSample.forEach(biggerTree::rbInsert);

        System.out.println("\nBIGGER SAMPLE");
        System.out.println("Red count: " + biggerTree.getRedNodeCount(biggerTree.root));
        System.out.println("Min depth: " + biggerTree.getMinDepth(biggerTree.root));
        System.out.println("Max depth: " + biggerTree.getMaxDepth(biggerTree.root));
    }

}
