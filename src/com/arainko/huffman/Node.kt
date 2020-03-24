package com.arainko.huffman

class Node(var char: Char, var count: Int): Comparable<Node> {
    var left: Node? = null
    var right: Node? = null

    constructor(entry: Map.Entry<Char, Int>) : this(entry.key, entry.value)
    constructor(entry: Pair<Char, Int>) : this(entry.first, entry.second)

    companion object {
        const val MARKER = '#'
        fun internalNode(left: Node, right: Node): Node =
                Node(MARKER, left.count+right.count).apply {
                    this.left = left
                    this.right = right
                }
    }

    override fun compareTo(other: Node): Int = this.count - other.count
    override fun toString(): String = "${if (char != MARKER) char else "INTERNAL"}, $count"
}