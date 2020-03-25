package com.arainko.huffman
typealias Queue = MutableList<Node>

class Node(val char: Char, val count: Int): Comparable<Node> {
    var left: Node? = null
    var right: Node? = null

    constructor(entry: Map.Entry<Char, Int>) : this(entry.key, entry.value)
    constructor(pair: Pair<Char, Int>) : this(pair.first, pair.second)

    private fun searchTree(sideEffect: (Node, String) -> Unit) {
        fun searchTreeHelper(currNode: Node?, path: String): Unit = when (currNode?.char) {
            MARKER -> {
                searchTreeHelper(currNode.left, path + "0")
                searchTreeHelper(currNode.right, path + "1")
            }
            else -> sideEffect(currNode!!, path)
        }
        return searchTreeHelper(this, "")
    }

    val encodedCharacters: Map<Char, String>
        get() {
            val outputMap: MutableMap<Char, String> = HashMap()
            val sideEffect: (Node, String) -> Unit = { node, path -> outputMap[node.char] = path }
            searchTree(sideEffect)
            return outputMap.toMap()
        }

    val huffmanTriples: List<Triple<Char, Int, String>>
        get() {
            val outputList: MutableList<Triple<Char, Int, String>> = ArrayList()
            val sideEffect: (Node, String) -> Unit = { node, path -> outputList += Triple(node.char, node.count, path) }
            searchTree(sideEffect)
            return outputList.toList()
        }


    companion object HuffmanTree {
        const val MARKER = '#'
        private fun internalNode(left: Node, right: Node): Node =
                Node(MARKER, left.count+right.count).apply {
                    this.left = left
                    this.right = right
                }

        fun buildTree(q: Queue): Node {
            for (i in 1 until q.size) {
                val x = q.minBy { it.count }
                q.remove(x)
                val y = q.minBy { it.count }
                q.remove(y)
                val z = Node.internalNode(x!!, y!!)
                q.add(z)
            }
            return q.minBy { it.count }!!
        }
    }

    override fun compareTo(other: Node): Int = this.count - other.count
    override fun toString(): String = "${if (char != MARKER) char else "INTERNAL"}, $count"
}