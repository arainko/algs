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

    fun rotateLeft(rotationPoint: TreeNode): Unit = TODO()
    fun rotateRight(rotationPoint: TreeNode): Unit = TODO()
    fun rbInsert(node: TreeNode): Unit = TODO()

    fun printTree() {
        fun innerPrintTree(visited: TreeNode, level: Int): Unit {
            if (visited.left is Empty && visited.right is Empty) {
                print("")
            } else {
                println("$visited LEVEL: $level")
                if (visited.left !is Empty) innerPrintTree(visited.left, level + 1)
                if (visited.right !is Empty) innerPrintTree(visited.right, level + 1)
            }
        }
        if (root !is Empty) innerPrintTree(root, 1)
    }
}


fun main() {
    val tree = RedBlackTree()
    val exampleNodes = arrayListOf(38, 31, 22, 8, 20, 5, 10, 9, 21, 27, 29, 25, 28)
    exampleNodes.take(3).forEach { tree.bstInsert(Node(it)) }
    tree.printTree()
}