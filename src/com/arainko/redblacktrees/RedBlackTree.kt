package com.arainko.redblacktrees


import com.arainko.redblacktrees.TreeNode.*

class RedBlackTree {

    var root: TreeNode = Empty

    fun bstInsert(node: TreeNode) {
        tailrec fun insert(parent: Node, node: Node): Unit = when {
            node > parent -> if (parent.right is Empty) {
                node.parent = parent
                parent.right = node
            } else insert(parent.right as Node, node)

            else -> if (parent.left is Empty) {
                node.parent = parent
                parent.left = node
            } else insert(parent.left as Node, node)
        }
        if (root is Empty) root = node else insert(root as Node, node as Node)
    }

    fun rotateLeft(x: TreeNode) {
        val y = x.right
        x.right = y.left

        if (y.left is Node)
            y.left.parent = x

        y.parent = x.parent
        when {
            x.parent is Empty -> root = y
            x == x.parent.left -> x.parent.left = y
            else -> x.parent.right = y
        }
        y.left = x
        x.parent = y
    }

    fun rotateRight(x: TreeNode) {
        val y = x.left
        x.left = y.right

        if (y.right is Node)
            y.right.parent = x

        y.parent = x.parent
        when {
            x.parent is Empty -> root = y
            x == x.parent.right -> x.parent.right = y
            else -> x.parent.left = y
        }
        y.right = x
        x.parent = y
    }

    fun rbInsert(x: TreeNode) {
        bstInsert(x)
        x.color = Color.RED
        while (x != root && x.parent.color == Color.RED) {
            if (x.parent == x.parent.parent.left) {
                val y = x.parent.parent.right
                if (y.color == Color.RED) {
                    x.parent.color = Color.BLACK
                    y.color = Color.BLACK
                    x.parent.parent.color = Color.RED
                    x = x.parent.parent
                }
            }
        }
    }
}

fun main() {
    val tree = RedBlackTree()
    val node1 = Node(1)
    val node2 = Node(2)
    val node3 = Node(0)
    tree.bstInsert(node1)
    tree.bstInsert(node2)
    tree.bstInsert(node3)

    println(tree.root)
    println(tree.root.right)
    println(tree.root.left)
    println(tree.root.left.parent)
}