package com.arainko.btrees

class Node(val degree: Int = 3) {
    val keys: ArrayList<Int> = ArrayList(degree*2-1)
    val children: ArrayList<Node> = ArrayList(degree*2-1)
    val isLeaf: Boolean
        get() = children.isEmpty()
    val keyCount: Int
        get() = keys.size
}