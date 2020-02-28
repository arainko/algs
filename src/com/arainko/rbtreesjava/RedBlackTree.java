package com.arainko.rbtreesjava;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("SuspiciousNameCombination")
public class RedBlackTree {
    public Node root;

    void bstInsert(Node node) {
        Node current = root;
        Node currentParent = null;

        while (current != null) {
            currentParent = current;
            if (node.key < current.key)
                current = current.left;
            else
                current = current.right;
        }

        node.parent = currentParent;

        if (currentParent == null)
            root = node;
        else if (node.key < currentParent.key)
            currentParent.left = node;
        else currentParent.right = node;
    }

    void rotateLeft(Node rotationPoint) {
        Node y = rotationPoint.right;
        rotationPoint.right = y.left;

        if (y.left != null)
            y.right.parent = rotationPoint;

        y.parent = rotationPoint.parent;

        if (rotationPoint.parent == null)
            root = y;
        else if (rotationPoint == rotationPoint.parent.left)
            rotationPoint.parent.left = y;
        else
            rotationPoint.parent.right = y;

        y.left = rotationPoint;
        rotationPoint.parent = y;
    }

    void rotateRight(Node rotationPoint) {
        Node y = rotationPoint.left;
        rotationPoint.left = y.right;

        if (y.right != null)
            y.right.parent = rotationPoint;

        y.parent = rotationPoint.parent;

        if (rotationPoint.parent == null)
            root = y;
        else if (rotationPoint == rotationPoint.parent.right)
            rotationPoint.parent.right = y;
        else
            rotationPoint.parent.left = y;

        y.right = rotationPoint;
        rotationPoint.parent = y;
    }

    void rbInsert(Node x) {
        bstInsert(x);
        Node y = null;
        while (x != root && x.parent.color == Color.RED) {
            if (x.parent == x.parent.parent.left) {
                y = x.parent.parent.right;
                if (y.color == Color.RED) {
                    x.parent.color = Color.BLACK;
                    y.color = Color.BLACK;
                    x.parent.parent.color = Color.RED;
                    x = x.parent.parent;
                } else if (x == x.parent.right) {
                    x = x.parent;
                    rotateLeft(x);
                } else {
                    x.parent.color = Color.BLACK;
                    x.parent.parent.color = Color.RED;
                    rotateRight(x.parent.parent);
                }
            } else {
                y = x.parent.left;
                if (y.color == Color.RED) {
                    x.parent.color = Color.BLACK;
                    y.color = Color.BLACK;
                    x.parent.parent.color = Color.RED;
                    x = x.parent.parent;
                } else if (x == x.parent.left) {
                    x = x.parent;
                    rotateRight(x);
                } else {
                    x.parent.color = Color.BLACK;
                    x.parent.parent.color = Color.RED;
                    rotateLeft(x.parent.parent);
                }
            }
        }
        root.color = Color.BLACK;
    }

    public void printTree() {
        if (root != null) innerPrintTree(root, 1);
    }

    private void innerPrintTree(Node visited, int level) {
        if (visited.left == null && visited.right == null)
            System.out.print("");
        else {
            System.out.println(visited.toString() + " LEVEL: " + level);
            if (visited.left != null) innerPrintTree(visited.left, level+1);
            if (visited.right != null) innerPrintTree(visited.right, level+1);
        }
    }

    public static void main(String[] args) {
        var nodes = List.of(38/*, 31, 22, 8, 20, 5, 10*/).stream()
                .map(Node::new)
                .collect(Collectors.toList());
        RedBlackTree tree = new RedBlackTree();
        nodes.forEach(tree::rbInsert);
        tree.rotateLeft(tree.root);
        tree.printTree();
    }

}
