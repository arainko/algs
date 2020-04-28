package com.arainko.sets

data class Node(var key: Int) {
    var parent: Node = this
    var rank: Int = 0
}