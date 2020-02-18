package com.arainko.redblacktrees


import com.arainko.redblacktrees.helpers.NodeField
import com.arainko.redblacktrees.helpers.NodeField.*

class RedBlackTree() {

    lateinit var root: NodeField

    constructor(rootNode: Node) : this() {
        root = TreeNode(rootNode)
    }

    fun bstInsert(node: Node) {
        if (root is TreeNode) {
            (root as TreeNode).node
        }
    }

}