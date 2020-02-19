package com.arainko.redblacktrees


import com.arainko.redblacktrees.helpers.TreeNode
import com.arainko.redblacktrees.helpers.TreeNode.*

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