package com.arainko.redblacktrees


sealed class TreeNode {

    var parent: TreeNode = Empty
    var left: TreeNode = Empty
    var right: TreeNode = Empty
    var color: Color = Color.BLACK

    override fun toString(): String = when (this) {
        is Empty -> "EMPTY"
        is Node -> {
            val leftString = stringify(this.left)
            val rightString = stringify(this.right)
            "$leftString <- ${this.key} -> $rightString"
        }
    }

    operator fun component1(): TreeNode = parent
    operator fun component2(): TreeNode = left
    operator fun component3(): TreeNode = right
    operator fun component4(): Color = color


    private fun stringify(node: TreeNode): String =
            if (node is Node) "${node.key} (${node.color})" else "EMPTY"

    class Node(val key: Int) : TreeNode(), Comparable<Node> {
        override fun compareTo(other: Node): Int = this.key - other.key
    }

    object Empty : TreeNode()
}