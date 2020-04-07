package com.arainko.huffman

import com.arainko.huffman.Node.HuffmanTree.buildTree
import java.io.File


inline class Filepath(private val path: String) {
    fun readFile(): String = File(path).readText()
}

fun encodeText(text: String, encodedChars: Map<Char, String>): String =
        text.fold("") { acc, char ->
            "$acc${encodedChars[char]}"
        }

fun main() {
    val fileText = Filepath("src/com/arainko/huffman/testfile")
            .readFile()
    val occurrences = fileText
            .groupBy { char -> char }
            .mapValues { entry -> entry.value.size }
            .map { Node(it) }
            .toList()
            .toMutableList()
    val tree = buildTree(occurrences)

    val encodedChars = tree.encodedCharacters
    val encodedText = encodeText(fileText, encodedChars)
    val huffmanTriples = tree.huffmanTriples
    println(encodedText)
    huffmanTriples.forEach {
        println("Char: ${it.first}, Occurrences: ${it.second}, Code: ${it.third}")
    }
    println("Approx. bit count of original text: ${fileText.length*4}")
    println("Approx. bit count of encoded text: ${encodedText.length}")


}