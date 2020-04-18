package com.arainko.btrees

import com.arainko.btrees.BTree.Companion.save
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

class BTree {
    val root: Node? = null

    fun buildTree(g: Int, n: Int) {

    }

    fun search(key: Int): Nothing = TODO()
    fun insert(key: Int): Nothing = TODO()
    fun delete(key: Int): Nothing = TODO()

    companion object {
        private const val BTREE = "serializedBTree"

        init { PersistedTree().serialize(BTREE) }

        val nodeCount: Int
            get() = deserialize<PersistedTree>(BTREE).size

        fun fetchAt(index: Int): Node = deserialize<PersistedTree>(BTREE)[index]
        fun Node.save(): Unit = deserialize<PersistedTree>(BTREE).run {
            add(this@save)
            serialize(BTREE)
        }

    }
}

fun main() {
    val node = Node(5)
    node.serialize("serializedNode")
    node.save()
    val deserialized = deserialize<Node>("serializedNode")
    println(deserialized)
}