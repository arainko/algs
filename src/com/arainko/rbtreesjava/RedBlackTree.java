package com.arainko.rbtreesjava;

public class RedBlackTree {
    public Node root;

//    Tree-insert(T,z)
//// wstawia węzeł z do drzewa T
//    x=T.root
//            y=NIL // y jest ojcem x
//while x!=NIL
//            y=x
//if z.key<x.key
//            x=x.left
//else x=x.right
//    z.p=y
//if y==NIL
//    T.root=z
//else if z.key<y.key
//    y.left=z
//else y.right=z

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

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(0);
        tree.bstInsert(node1);
        tree.bstInsert(node2);
        tree.bstInsert(node3);
        System.out.println(tree.root);
        System.out.println(tree.root.left);
        System.out.println(tree.root.right);
    }

}
