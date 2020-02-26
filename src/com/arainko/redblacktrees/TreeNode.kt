package com.arainko.redblacktrees


sealed class TreeNode {

    var parent: TreeNode = Empty
    var left: TreeNode = Empty
    var right: TreeNode = Empty
    var color: Color = Color.BLACK

    override fun toString(): String = when (this) {
        is Empty -> "EMPTY"
        is Node -> {
            val leftString = if (left is Node) "${(left as Node).key}" else "EMPTY"
            val rightString = if (right is Node) "${(right as Node).key}" else "EMPTY"
            "$leftString <- ${this.key} -> $rightString"
        }
    }

    class Node(val key: Int) : TreeNode(), Comparable<Node> {
        override fun compareTo(other: Node): Int = this.key - other.key
    }

    object Empty : TreeNode()
}