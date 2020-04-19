package com.arainko.btrees

import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

//typealias PersistedTree = ArrayList<com.arainko.btrees.Node>

fun <T> T.serialize(filename: String) {
    val file = FileOutputStream(filename)
    val oos = ObjectOutputStream(file)
    oos.writeObject(this)
    oos.close()
    file.close()
}

@Suppress("UNCHECKED_CAST")
fun <T> deserialize(filename: String): T {
    val file = FileInputStream(filename)
    val ois = ObjectInputStream(file)
    val output = ois.readObject() as T
    ois.close()
    file.close()
    return output
}

class BTree(private val degree: Int) {
    var root: Node? = null

    private val maxKeyCount: Int
        get() = 2*degree-1

    private val maxChildrenCount: Int
        get() = 2*degree

    fun print() {
        if (root != null) root!!.print()
        println()
    }

    fun search(key: Int): Node? =
            if (root == null) null else root!!.search(key)

    fun insert(key: Int) = if (root == null) {
        root = Node(true)
        root!!.keys[0] = key
        root!!.keyCount = 1
    } else {
        if (root!!.keyCount == maxKeyCount) {
            val newNode = Node(false)
            newNode.children[0] = root
            newNode.splitChild(0, root)
            var i = 0
            if (newNode.keys[0] < key) {
                i++
            }
            newNode.children[i]!!.insertNonFull(key)
            root = newNode
        } else root!!.insertNonFull(key)
    }

}

fun main() {
    val tree = BTree(3)

    listOf(2, 43, 51, 22, 11, 4, 54, 23, 43, 30).forEach { tree.insert(it) }

    tree.root!! { println("Root: ${}") }
    tree.root!!.children.forEachIndexed { index, node ->
        println("Child $index: ${node?.keys?.fold("") { acc, key -> "$acc $key" }}, keyCount: ${node?.keyCount} ")
    }

    println(tree.search(51))
    println(tree.search(101))

}