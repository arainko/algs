package com.arainko.redblacktrees


sealed class TreeNode {

    var parent: TreeNode = Empty
    var left: TreeNode = Empty
    var right: TreeNode = Empty
    var color: Color = Color.BLACK

    override fun toString(): String = if (this is Node) this.toString() else "Empty"

    class Node(val key: Int) : TreeNode(), Comparable<Node> {
        override fun compareTo(other: Node): Int = this.key - other.key
        override fun toString(): String = "Node($key)"
    }

    object Empty : TreeNode()
}