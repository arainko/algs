package com.arainko.trees

import com.arainko.trees.TreeNode.*

class Tree {
    var root: TreeNode? = null

    fun insert(node: TreeNode) {
        tailrec fun insert(node: TreeNode, parent: TreeNode): Unit = when {
            node < parent -> if (parent.left == null) parent.left = node else insert(node, parent.left!!)
            node > parent -> if (parent.right == null) parent.right = node else insert(node, parent.right!!)
            else -> when {
                    parent.switch == Switch.L && parent.left != null -> {
                        parent.switch = !parent.switch
                        insert(node, parent.left!!)
                    }
                    parent.switch == Switch.R && parent.right != null -> {
                        parent.switch = !parent.switch
                        insert(node, parent.left!!)
                    }
                    parent.switch == Switch.L && parent.left == null -> {
                        parent.switch = !parent.switch
                        parent.left = node
                    }
                    else -> {
                        parent.switch = !parent.switch
                        parent.right = node
                    }
                }
        }
        if (root == null) root = node
        else insert(node, root!!)
    }

    fun search(content: Int): TreeNode? {
        var parent = root
        while (parent != null && parent.content != content) {
            parent = if (content < parent.content) parent.left else parent.right
        }
        return parent
    }

    fun printTree() {
        fun innerPrintTree(visited: TreeNode, level: Int): Unit  {
            if (visited.left == null && visited.right == null) {
                print("")
            } else {
                println("$visited LEVEL: $level")
                visited.left?.let { innerPrintTree(it, level + 1) }
                visited.right?.let { innerPrintTree(it, level +1) }
            }
        }
        root?.let { innerPrintTree(it, 1) }
    }

   fun delete(key: Int) {
       fun innerDelete(parent: TreeNode?, content: Int): TreeNode? {
           val deletedNode: TreeNode?

           if (parent == null)
               return parent

           if (content < parent.content)
               parent.left = innerDelete(parent.left, content)
           else if (content > parent.content)
               parent.right = innerDelete(parent.right, content)
           else {
               if (parent.left == null) return parent.right
               else if (parent.right == null) return parent.left

               deletedNode = findOutermostLeft(parent.right!!)
               parent.content = deletedNode!!.content
               parent.right = innerDelete(parent.right, deletedNode.content)
           }
           return parent
       }
       innerDelete(root, key)
   }


//        if (node?.left == null && node?.right == null

    fun findOutermostLeft(node: TreeNode): TreeNode? {
        tailrec fun findOutermostLeft(currVisited: TreeNode): TreeNode =
                if (currVisited.left == null) currVisited else findOutermostLeft(currVisited.left!!)
        return findOutermostLeft(node)
    }

}

fun main() {
    val leafs = arrayOf(18, 18, 18, 30, 21, 19, 8, 22, 23, 5, 20, 26, 17)
    val tree = Tree()
    leafs.forEach {  tree.insert(TreeNode(it))  }
    println("Drzewo z powtorzonymi elementami:")
    tree.printTree()
    println("Szukanie node'a z contentem 20:")
    println(tree.search(20))
    println("Drzewo po usunieciu trzech duplikatow:")
    tree.delete(18)
    tree.delete(18)
    tree.delete(18)
    tree.printTree()
}