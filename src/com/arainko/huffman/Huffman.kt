package com.arainko.huffman

import java.io.File

inline class Filepath(private val path: String) {
    fun readFile(): String = File(path).readText()
}


class Huffman {

}

fun main() {
    val fileText = Filepath("src/com/arainko/huffman/testfile").readFile()
    val occurences = fileText
            .groupBy { char -> char }
            .mapValues { entry -> entry.value.size }

    println(occurences)
}