package com.arainko.redblacktrees.helpers


sealed class TreeNode {

    var parent: TreeNode = Empty
    var left: TreeNode = Empty
    var right: TreeNode = Empty
    lateinit var color: Color

    override fun toString(): String = if (this is Node) this.toString() else "Empty"

    class Node(val content: Int) : TreeNode(), Comparable<Node> {

        override fun compareTo(other: Node): Int = this.content - other.content

        override fun toString(): String = "Node($content)"
    }

    object Empty : TreeNode()
}