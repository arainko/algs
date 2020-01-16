package com.arainko.trees

class TreeNode(var content: Int): Comparable<TreeNode> {
    var left: TreeNode? = null
    var right: TreeNode? = null
    var switch: Switch = Switch.L

    override fun compareTo(other: TreeNode): Int = this.content - other.content

    enum class Switch {
        L, R;
        operator fun not(): Switch = if (this == L) R else L
    }

    override fun toString(): String {
        return "(${left?.content}) <- $content -> (${right?.content})"
    }
}

fun main() {
    val node1 = TreeNode(2)
    val node2 = TreeNode(3)
    println(node1 <= node2)
    println()
}