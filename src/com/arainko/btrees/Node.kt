package com.arainko.btrees

class Node(val degree: Int = 3) {
    val keys: ArrayList<Int> = ArrayList()
    val children: ArrayList<Node> = ArrayList()
    val isLeaf: Boolean
        get() = children.isEmpty()
}