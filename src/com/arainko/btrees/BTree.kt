package com.arainko.btrees

import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

typealias PersistedTree = ArrayList<Node>

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

class BTree(val filename: String) {
    val root: Node? = null
    var currentPosition: Int = 0

    init { PersistedTree().serialize(filename) }

    val nodeCount: Int
        get() = deserialize<PersistedTree>(filename).size

    fun fetchAt(index: Int): Node = deserialize<PersistedTree>(filename)[index]
    fun saveAt(position: Int, node: Node): Unit = deserialize<PersistedTree>(filename).run {
        add(position, node)
        serialize(filename)
    }

    fun build(height: Int, nodeCount: Int): Int {
        var klucz: Int = 0
        val w = Node().apply { keyCount = nodeCount }
        if (height == 0) {
            for (i in 0 until nodeCount) {
                w.childrenPosition[i] = -1
                w.keys[i] = klucz++
            }
            w.childrenPosition[nodeCount] = -1
            w.isLeaf = true
        } else {
            for (i in 0 until nodeCount) {
                w.childrenPosition[i] = build(height-1, nodeCount)
                w.keys[i] = klucz++
            }
            w.childrenPosition[nodeCount] = build(height-1, nodeCount)
            w.isLeaf = false
        }
        saveAt(currentPosition++, w)
        return currentPosition-1
    }

    fun search(position: Int, key: Int): Node? {
        val node = fetchAt(position)
        var i = 0
        while (i < node.keyCount && key > node.keys[i]) {
            i++
        }
        return when (key) {
            node.keys[i] -> node
            else -> if (node.isLeaf) null else search(node.childrenPosition[i], key)
        }
    }
    fun insert(key: Int): Nothing = TODO()
    fun delete(key: Int): Nothing = TODO()


}

fun main() {
    val tree = BTree("serializedTree")
    tree.build(3, 0)

    println(deserialize<PersistedTree>("serializedTree"))

}