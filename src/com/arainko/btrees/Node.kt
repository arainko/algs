package com.arainko.btrees

import java.io.Serializable

data class Node(val degree: Int = 3): Serializable {
    val keys = IntArray(degree*2-1)
    val childrenPosition = IntArray(degree*2)
    var keyCount: Int = 0
    var isLeaf: Boolean = true
}