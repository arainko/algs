package com.arainko.regex

infix fun String.naive(pattern: String) {
    val expressionLines = this.lines()
    val strippedPattern = pattern.filter { it != '\n' }
    expressionLines.forEachIndexed { index, line ->
        for (i in 0..line.length-strippedPattern.length)
            if (pattern == line.slice(i until i+strippedPattern.length))
                println("Found match at line $index, position $i")
    }
}

infix fun String.rabinKarp(pattern: String) {
    val oddNumber = 27077
    val alphabetSize = 128
    var patternHash = 0
    var expressionHash = 0
    var h = 1
//    h = (h * alphabetSize).pow
    val expressionLines = this.lines()
    val strippedPattern = pattern.filter { it != '\n' }

    expressionLines.forEachIndexed { index, line ->
        for (i in 0..line.length-strippedPattern.length)
            if (pattern == line.slice(i until i+strippedPattern.length))
                println("Found match at line $index, position $i")
    }

}

fun main() {
    "abababab" naive "abab"
}