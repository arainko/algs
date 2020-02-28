@file:Suppress("DuplicatedCode")

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

    fun rotateLeft(rotationPoint: TreeNode): Unit {
        val ogRightChild = rotationPoint.right
        rotationPoint.right = ogRightChild.left

        if (ogRightChild is Node)
            ogRightChild.left.parent = rotationPoint

        ogRightChild.parent = rotationPoint.parent
        when {
            rotationPoint.parent is Empty ->
                root = ogRightChild // rotation point is ROOT
            rotationPoint == rotationPoint.parent.left ->
                rotationPoint.parent.left = ogRightChild // rotation point is a left child
            else -> rotationPoint.parent.right = ogRightChild // rotation point is a right child
        }
        ogRightChild.left = rotationPoint
        rotationPoint.parent = ogRightChild
    }

    fun rotateRight(rotationPoint: TreeNode): Unit {
        val ogLeftChild = rotationPoint.left
        rotationPoint.left = ogLeftChild.right

        if (ogLeftChild is Node)
            ogLeftChild.right.parent = rotationPoint

        ogLeftChild.parent = rotationPoint.parent
        if (rotationPoint.parent is Empty) root = ogLeftChild // rotation point is ROOT
        else if (rotationPoint == rotationPoint.parent.right) rotationPoint.parent.right = ogLeftChild // rotation point is a right child
        else rotationPoint.parent.left = ogLeftChild // rotation point is a left child
        ogLeftChild.right = rotationPoint
        rotationPoint.parent = ogLeftChild
    }



//    fun rbInsert(node: TreeNode): Unit {
//        while (node != root && node.parent.color == Color.RED) {
//            if (node.parent == node.parent.parent.left) {
//                val y = node.parent.parent.right
//                if (y.color == Color.RED) {
//                    node = node.parent.parent
//                }
//            }
//        }
//    }

    fun printTree() {
        fun innerPrintTree(visited: TreeNode, level: Int): Unit {
            if (visited.left is Empty && visited.right is Empty) {
                print("")
            } else {
                println("$visited LEVEL: $level")
                if (visited.left is Node) innerPrintTree(visited.left, level + 1)
                if (visited.right is Node) innerPrintTree(visited.right, level + 1)
            }
        }
        if (root is Node) innerPrintTree(root, 1)
    }
}


fun main() {
    val tree = RedBlackTree()
    val exampleNodes = arrayListOf(38, 39, 40, 8, 20, 5, 10, 9, 21, 27, 29, 25, 28)
    exampleNodes.forEach { tree.bstInsert(Node(it)) }
    val (parent, left, right, color) = tree.root
    tree.rotateLeft(tree.root)
    tree.printTree()
}