package com.arainko.btrees

class Node(private val isLeaf: Boolean, private val degree: Int = 3) {
    var keyCount: Int = 0
    val keys = IntArray(maxKeyCount)
    val children = arrayOfNulls<Node>(maxChildrenCount)

    private val maxKeyCount: Int
        get() = 2*degree-1

    private val maxChildrenCount: Int
        get() = 2*degree

    fun print() {
        var i = 0
        while (i < keyCount) {
            if (!isLeaf) children[i]!!.print()
            print("${keys[i]} ")
            i++
        }
        if (!isLeaf) children[i]!!.print()
    }

    fun search(key: Int): Node? {
        var i = 0
        while (i < keyCount && key > keys[i]) i++
        return when {
            keys[i] == key -> this
            isLeaf -> null
            else -> children[i]!!.search(key)
        }
    }

    fun insertNonFull(key: Int) {
        var i = keyCount-1
        if (isLeaf) {
            while (i >= 0 && keys[i] > key) {
                keys[i+1] = keys[i]
                i--
            }
            keys[i+1] = key
            keyCount++
        } else {
            while (i >= 0 && keys[i] > key)
                i--

            if (children[i+1]!!.keyCount == maxKeyCount) {
                splitChild(i+1, children[i+1])
                if (keys[i+1] < key)
                    i++
            }
            children[i+1]!!.insertNonFull(key)
        }
    }

    fun splitChild(i: Int, node: Node?) {
        val newNode = Node(node!!.isLeaf).apply { keyCount = degree-1 }

        for (j in 0 until degree-1)
            newNode.keys[j] = node.keys[j + degree]

        if (!node.isLeaf)
            for (j in 0 until degree)
                newNode.children[j] = node.children[j + degree]

        node.keyCount = degree - 1

        for (j in keyCount downTo i+1)
            children[j+1] = children[j]

        children[i+1] = newNode

        for (j in keyCount-1 downTo i)
            keys[j+1] = keys[j]

        keys[i] = node.keys[degree-1]

        keyCount += 1
    }

    override fun toString(): String {
        return "Node(isLeaf=$isLeaf, degree=$degree, keyCount=$keyCount, keys=${keys.contentToString()}, children=${children.contentToString()})"
    }
}