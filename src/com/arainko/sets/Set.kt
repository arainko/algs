package com.arainko.sets

class Set {
    fun findSet(x: Node): Node {
        if (x !== x.parent)
            x.parent = findSet(x.parent)
        return x.parent
    }

    fun union(x: Node, y: Node) {
        if (x.rank < y.rank)
            x.parent = y
        else {
            y.parent = x
            if (x.rank  == y.rank)
                x.rank += 1
        }
    }

    fun trace(node: Node): String {
        fun innerTrace(node: Node, trace: String): String =
                if (node.parent == node) "$trace${node.key}, rank: ${node.rank}"
                else innerTrace(node.parent, "$trace${node.key} -> ")
        return innerTrace(node, "")
    }
}

fun main() {
    val set = Set()
    val nodes = (0 until 10).map { Node(it) }

    println("[Union 0, 1]")
    set.union(set.findSet(nodes[0]), set.findSet(nodes[1]))
    nodes.forEach { println("Node ${it.key}: ${set.trace(it)}") }
    println()

    println("[Union 2, 3]")
    set.union(set.findSet(nodes[2]), set.findSet(nodes[3]))
    nodes.forEach { println("Node ${it.key}: ${set.trace(it)}") }
    println()

    println("[Union 1, 2]")
    set.union(set.findSet(nodes[1]), set.findSet(nodes[2]))
    nodes.forEach { println("Node ${it.key}: ${set.trace(it)}") }
    println()

    println("[Union 5, 6]")
    set.union(set.findSet(nodes[5]), set.findSet(nodes[6]))
    nodes.forEach { println("Node ${it.key}: ${set.trace(it)}") }
    println()

    println("[Union 7, 8]")
    set.union(set.findSet(nodes[7]), set.findSet(nodes[8]))
    nodes.forEach { println("Node ${it.key}: ${set.trace(it)}") }
    println()

    println("[Union 3, 5]")
    set.union(set.findSet(nodes[3]), set.findSet(nodes[5]))
    nodes.forEach { println("Node ${it.key}: ${set.trace(it)}") }
    println()

    println("[Union 0, 7]")
    set.union(set.findSet(nodes[0]), set.findSet(nodes[7]))
    nodes.forEach { println("Node ${it.key}: ${set.trace(it)}") }




}