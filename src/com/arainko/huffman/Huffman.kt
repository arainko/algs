package com.arainko.huffman

import java.io.File

typealias Queue = MutableList<Node>

inline class Filepath(private val path: String) {
    fun readFile(): String = File(path).readText()
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


fun main() {
    val fileText = Filepath("src/com/arainko/huffman/testfile").readFile()
    val occurrences = fileText
            .groupBy { char -> char }
            .mapValues { entry -> entry.value.size }
            .map { Node(it) }
            .toList()
            .toMutableList()
    val node = buildTree(occurrences)

    println(node.left)
    println(node.right?.right?.right?.right)
}