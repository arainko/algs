package com.arainko.btrees

import java.io.Serializable

data class Node(val degree: Int = 3): Serializable {
    val keys = Array<Int?>(degree*2-1) { null }
    val children = Array<Node?>(degree*2) { null }

    val isLeaf: Boolean
        get() = children.filterNotNull().isNotEmpty()

    val keyCount: Int
        get() = keys.size
}