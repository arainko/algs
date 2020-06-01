package com.arainko.graphs

import com.arainko.huffman.Filepath
import java.util.*

typealias AdjacencyMatrix = Array<IntArray>
typealias Edge = Pair<Int, Int>

fun readGraph(path: Filepath): AdjacencyMatrix = path.readFile()
        .lines()
        .drop(1)
        .map { line -> line.split(" ") }
        .map { line -> line.map { it.toInt() } }
        .map { line -> line.toIntArray() }
        .toTypedArray()

class Graph(adjacencyMatrix: AdjacencyMatrix) {

    // Pierwsza liczba w parze to wierzcholek z ktorego doszlismy do drugiego wierzcholka (drugiej liczby z pary)
    private val adjacencyList: Array<MutableList<Pair<Int, Int>>> = (adjacencyMatrix.indices)
            .map { mutableListOf<Pair<Int, Int>>() }
            .toTypedArray()

    init {
        adjacencyMatrix.forEachIndexed { index, curr ->
            curr.forEachIndexed { innerIndex, innerCurr -> if (innerCurr == 1) this + (index to innerIndex) }
        }
    }

    operator fun plus(indexToNode: Pair<Int, Int>) {
        adjacencyList[indexToNode.first].add(indexToNode)
    }

    infix fun bfs(start: Int): MutableList<Edge> {
        val visited = BooleanArray(adjacencyList.size) { false }
        val bindingForest = mutableListOf<Edge>()
        val nodeQueue = LinkedList<Int>()
        nodeQueue.add(start)
        visited[start] = true
        while (nodeQueue.isNotEmpty()) {
            val node = nodeQueue.poll()
            adjacencyList[node].forEach {
                val nextNode = it.second
                if (!visited[nextNode]) {
                    bindingForest.add(it)
                    visited[nextNode] = true
                    nodeQueue.add(nextNode)
                }
            }
        }
        return bindingForest
    }

    infix fun dfs(start: Int): MutableList<Edge> {
        val visited = BooleanArray(adjacencyList.size) { false }
        val bindingForest = mutableListOf<Edge>()
        fun innerDfs(curr: Int, visited: BooleanArray, bindingForest: MutableList<Edge>) {
            visited[curr] = true
            val adjacentNodes = adjacencyList[curr]
            adjacentNodes.forEach {
                val node = it.second
                if (!visited[node]) {
                    bindingForest.add(it)
                    innerDfs(node, visited, bindingForest)
                }
            }
        }
        innerDfs(start, visited, bindingForest)
        return bindingForest
    }

}

//fun BFS()

fun main() {
    println()
    val graphMatrix = readGraph(Filepath("graph.txt"))
    val graph = Graph(graphMatrix)
    println("BFS:")
    println(graph bfs 0)
    println()
    println("DFS:")
    println(graph dfs 0)
}