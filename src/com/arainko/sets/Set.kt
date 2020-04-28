package com.arainko.sets

class Set(key: Int) {
    var root: Node = Node(key)

    fun findSet(x: Node): Node {
        if (x !== x.parent)
            x.parent = findSet(x.parent)
        return x.parent
    }

    fun union(x: Node, y: Node) {
        if (x.rank < y.rank)
            x.parent = y
        else {
            y.parent = x
            if (x.rank  == y.rank)
                y.rank += 1
        }
    }

}