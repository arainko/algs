package com.arainko.rbtreesjava;

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

    void rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != warden) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    void rotateRight(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != warden) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

//    void rbInsert(Node x) {
//        bstInsert(x);
//        Node y;
//        while (x != root && x.parent.color == Color.RED) {
//            if (x.parent == x.parent.parent.left) {
//                y = x.parent.parent.right;
//                if (y.color == Color.RED) {
//                    x.parent.color = Color.BLACK;
//                    y.color = Color.BLACK;
//                    x.parent.parent.color = Color.RED;
//                    x = x.parent.parent;
//                } else if (x == x.parent.right) {
//                    x = x.parent;
//                    rotateLeft(x);
//                } else {
//                    x.parent.color = Color.BLACK;
//                    x.parent.parent.color = Color.RED;
//                    rotateRight(x.parent.parent);
//                }
//            } else {
//                y = x.parent.left;
//                if (y.color == Color.RED) {
//                    x.parent.color = Color.BLACK;
//                    y.color = Color.BLACK;
//                    x.parent.parent.color = Color.RED;
//                    x = x.parent.parent;
//                } else if (x == x.parent.left) {
//                    x = x.parent;
//                    rotateRight(x);
//                } else {
//                    x.parent.color = Color.BLACK;
//                    x.parent.parent.color = Color.RED;
//                    rotateLeft(x.parent.parent);
//                }
//            }
//        }
//        root.color = Color.BLACK;
//    }

    public void rbInsert(Node k) {
        bstInsert(k);
//        if (k.parent.parent == null) return;
        Node u;

        while (k != root && k.parent.color == Color.RED) {
            if (k.parent == k.parent.parent.right) {
                u = k.parent.parent.left;
                if (u.color == Color.RED) {
                    u.color = Color.BLACK;
                    k.parent.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
                        k = k.parent;
                        rotateRight(k);
                    }
                    k.parent.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    rotateLeft(k.parent.parent);
                }
            } else {
                u = k.parent.parent.right;
                if (u.color == Color.RED) {
                    u.color = Color.BLACK;
                    k.parent.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
                        k = k.parent;
                        rotateLeft(k);
                    }
                    k.parent.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    rotateRight(k.parent.parent);
                }
            }
        }
        root.color = Color.BLACK;
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
        var nodes = List.of(38, 31, 22, 8, 20, 5, 10, 9, 21, 27, 29, 25, 28).stream()
                .map(Node::new)
                .collect(Collectors.toList());
        RedBlackTree tree = new RedBlackTree();
        nodes.forEach(tree::rbInsert);
//        tree.rotateLeft(tree.root);
        tree.printTree();
    }

}
