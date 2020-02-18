package com.arainko.redblacktrees.helpers

import com.arainko.redblacktrees.Node

sealed class NodeField {
    class TreeNode(var node: Node) : NodeField()
    object Empty : NodeField()
}