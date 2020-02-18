package com.arainko.redblacktrees

import com.arainko.redblacktrees.helpers.NodeField.*
import com.arainko.redblacktrees.helpers.Color
import com.arainko.redblacktrees.helpers.NodeField

class Node(val content: Int) : Comparable<Node> {

    var parent: NodeField = Empty
    var leftChild: NodeField = Empty
    var rightChild: NodeField = Empty
    lateinit var color: Color

    override fun compareTo(other: Node): Int = this.content - other.content

    override fun toString(): String = "Node($content)"
}