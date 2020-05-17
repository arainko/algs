package com.arainko.regex

import com.arainko.huffman.Filepath
import kotlin.system.measureTimeMillis

infix fun String.naive(pattern: String) {
    val strippedText = this.filter { it != '\n' }
    val strippedPattern = pattern.filter { it != '\n' }
    var currentIndex = 0
    var lineCharIndex = 0
    var lineIndex = 0

    for (i in 0..strippedText.length-strippedPattern.length) {
        if (this[currentIndex] == '\n') {
            lineCharIndex = 0
            lineIndex++
            currentIndex++
        }
        if (strippedPattern == strippedText.slice(i until i+strippedPattern.length)) {
            println("Found match at line $lineIndex, position $lineCharIndex")
        }
        lineCharIndex++
        currentIndex++
    }
}

infix fun String.rabinKarp(pattern: String) {
    val alphabetSize = 256
    val oddNumber = 27077
    var hashPattern = 0
    var hashText = 0
    var hasher = 1

    val strippedText = this.filter { it != '\n' }
    val strippedPattern = pattern.filter { it != '\n' }
    var currentIndex = 0
    var lineCharIndex = 0
    var lineIndex = 0

    for (i in 0 until strippedPattern.length-1)
        hasher = (hasher * alphabetSize) % oddNumber

    for (i in strippedPattern.indices) {
        hashPattern = (alphabetSize * hashPattern + strippedPattern[i].toInt()) % oddNumber
        hashText = (alphabetSize * hashText + strippedText[i].toInt()) % oddNumber
    }

    for (i in 0..strippedText.length-strippedPattern.length) {
        if (this[currentIndex] == '\n') {
            lineCharIndex = 0
            lineIndex++
            currentIndex++
        }
        if (hashPattern == hashText)
            if (strippedPattern == strippedText.slice(i until i+strippedPattern.length)) {
                println("Found match at line $lineIndex, position $lineCharIndex")
            }

        if (i < strippedText.length - strippedPattern.length) {
            hashText = (alphabetSize * (hashText - strippedText[i].toInt() * hasher) +
                    strippedText[i+strippedPattern.length].toInt()) % oddNumber
            if (hashText < 0)
                hashText += oddNumber
        }
        lineCharIndex++
        currentIndex++
    }
}

infix fun String.knuthMorrisPratt(pattern: String) {
    fun prefix(pattern: String): IntArray {
        val pi = IntArray(pattern.length)
        var k = 0
        (1 until pattern.length).forEach { i ->
            while (k > 0 && pattern[k] != pattern[i])
                k = pi[k-1]
            if (pattern[k] == pattern[i])
                k++
            pi[i] = k
        }
        return pi
    }

    val strippedText = this.filter { it != '\n' }
    val strippedPattern = pattern.filter { it != '\n' }
    var currentIndex = 0
    var lineCharIndex = 0
    var lineIndex = 0
    var persistedLineIndex = 0
    var persistedCharIndex = 0
    val prefixCountArray = prefix(strippedPattern)
    var currentPrefixIndex = 0

    for (i in strippedText.indices) {
        if (this[currentIndex] == '\n') {
            lineCharIndex = 0
            lineIndex++
            currentIndex++
        }
        while (currentPrefixIndex > 0 && strippedPattern[currentPrefixIndex] != strippedText[i])
            currentPrefixIndex = prefixCountArray[currentPrefixIndex-1]
        if (strippedPattern[currentPrefixIndex] == strippedText[i])
            currentPrefixIndex++
        if (currentPrefixIndex == strippedPattern.length) {
            println("Found match at line $persistedLineIndex, position $persistedCharIndex")
            currentPrefixIndex = prefixCountArray[currentPrefixIndex-1]
        }
        if (currentPrefixIndex == 1) {
            persistedLineIndex = lineIndex
            persistedCharIndex = lineCharIndex
        }
        lineCharIndex++
        currentIndex++
    }
}

fun main() {
    val text = Filepath("text.txt").readFile()
    val pattern = Filepath("pattern.txt").readFile()
    val text1 = Filepath("text1.txt").readFile()
    val pattern1 = Filepath("pattern1.txt").readFile()

    println("FOR wzorzec.txt and tekst.txt")
    println("Naive: ${measureTimeMillis { text naive pattern }} miliseconds")
    println()
    println("Rabin-Karp: ${measureTimeMillis { text rabinKarp pattern }} miliseconds")
    println()
    println("Knuth-Morris-Pratt: ${measureTimeMillis { text knuthMorrisPratt  pattern }} miliseconds")

    println("\n\n\n")
    println("FOR wzorzec1.txt and tekst1.txt")
    println("Naive: ${measureTimeMillis { text1 naive pattern1 }} miliseconds")
    println()
    println("Rabin-Karp: ${measureTimeMillis { text1 rabinKarp pattern1 }} miliseconds")
    println()
    println("Knuth-Morris-Pratt: ${measureTimeMillis { text1 knuthMorrisPratt  pattern1 }} miliseconds")

/*
    FOR wzorzec.txt and tekst.txt
    Found match at line 4, position 20
    Found match at line 332, position 0
    Found match at line 1212, position 28
    Found match at line 1410, position 0
    Found match at line 1994, position 14
    Found match at line 2309, position 0
    Found match at line 3067, position 40
    Found match at line 3380, position 0
    Naive: 431 miliseconds

    Found match at line 4, position 20
    Found match at line 332, position 0
    Found match at line 1212, position 28
    Found match at line 1410, position 0
    Found match at line 1994, position 14
    Found match at line 2309, position 0
    Found match at line 3067, position 40
    Found match at line 3380, position 0
    Rabin-Karp: 15 miliseconds

    Found match at line 4, position 20
    Found match at line 332, position 0
    Found match at line 1212, position 28
    Found match at line 1410, position 0
    Found match at line 1994, position 14
    Found match at line 2309, position 0
    Found match at line 3067, position 40
    Found match at line 3380, position 0
    Knuth-Morris-Pratt: 22 miliseconds
*/

/*
    FOR wzorzec1.txt and tekst1.txt
    Found match at line 21, position 9
    Found match at line 61, position 9
    Found match at line 101, position 9
    Found match at line 121, position 9
    Found match at line 131, position 11
    Found match at line 216, position 7
    Found match at line 231, position 9
    Found match at line 241, position 11
    Found match at line 251, position 11
    Found match at line 271, position 13
    Found match at line 281, position 13
    Found match at line 311, position 13
    Found match at line 321, position 13
    Found match at line 371, position 11
    Found match at line 381, position 11
    Found match at line 416, position 11
    Found match at line 436, position 11
    Found match at line 461, position 11
    Found match at line 496, position 9
    Found match at line 511, position 9
    Found match at line 536, position 11
    Found match at line 566, position 11
    Found match at line 596, position 13
    Found match at line 626, position 11
    Found match at line 636, position 11
    Found match at line 676, position 9
    Found match at line 691, position 11
    Found match at line 716, position 13
    Found match at line 726, position 13
    Found match at line 766, position 11
    Found match at line 781, position 13
    Found match at line 796, position 13
    Found match at line 811, position 15
    Found match at line 826, position 17
    Found match at line 866, position 19
    Found match at line 911, position 19
    Found match at line 926, position 21
    Found match at line 941, position 21
    Found match at line 976, position 23
    Found match at line 1021, position 21
    Found match at line 1031, position 23
    Found match at line 1096, position 19
    Found match at line 1106, position 21
    Found match at line 1136, position 23
    Found match at line 1191, position 23
    Found match at line 1201, position 25
    Found match at line 1211, position 25
    Found match at line 1226, position 25
    Found match at line 1276, position 25
    Found match at line 1286, position 25
    Found match at line 1311, position 25
    Found match at line 1326, position 25
    Found match at line 1346, position 25
    Found match at line 1366, position 25
    Found match at line 1381, position 25
    Found match at line 1441, position 27
    Found match at line 1471, position 25
    Found match at line 1486, position 27
    Found match at line 1551, position 23
    Found match at line 1581, position 23
    Found match at line 1661, position 23
    Found match at line 1671, position 25
    Found match at line 1681, position 25
    Found match at line 1716, position 25
    Found match at line 1756, position 27
    Found match at line 1771, position 27
    Found match at line 1791, position 29
    Found match at line 1801, position 31
    Found match at line 1821, position 33
    Found match at line 1851, position 31
    Found match at line 1866, position 33
    Found match at line 1876, position 33
    Found match at line 1901, position 33
    Found match at line 1931, position 35
    Found match at line 1976, position 35
    Found match at line 2046, position 29
    Found match at line 2066, position 29
    Found match at line 2091, position 27
    Found match at line 2101, position 27
    Found match at line 2121, position 27
    Found match at line 2146, position 27
    Found match at line 2171, position 27
    Found match at line 2226, position 25
    Found match at line 2241, position 27
    Found match at line 2256, position 29
    Found match at line 2286, position 31
    Found match at line 2306, position 31
    Found match at line 2331, position 33
    Found match at line 2346, position 35
    Found match at line 2366, position 33
    Found match at line 2381, position 33
    Found match at line 2421, position 35
    Found match at line 2431, position 35
    Found match at line 2446, position 35
    Found match at line 2491, position 33
    Naive: 58 miliseconds

    Found match at line 21, position 9
    Found match at line 61, position 9
    Found match at line 101, position 9
    Found match at line 121, position 9
    Found match at line 131, position 11
    Found match at line 216, position 7
    Found match at line 231, position 9
    Found match at line 241, position 11
    Found match at line 251, position 11
    Found match at line 271, position 13
    Found match at line 281, position 13
    Found match at line 311, position 13
    Found match at line 321, position 13
    Found match at line 371, position 11
    Found match at line 381, position 11
    Found match at line 416, position 11
    Found match at line 436, position 11
    Found match at line 461, position 11
    Found match at line 496, position 9
    Found match at line 511, position 9
    Found match at line 536, position 11
    Found match at line 566, position 11
    Found match at line 596, position 13
    Found match at line 626, position 11
    Found match at line 636, position 11
    Found match at line 676, position 9
    Found match at line 691, position 11
    Found match at line 716, position 13
    Found match at line 726, position 13
    Found match at line 766, position 11
    Found match at line 781, position 13
    Found match at line 796, position 13
    Found match at line 811, position 15
    Found match at line 826, position 17
    Found match at line 866, position 19
    Found match at line 911, position 19
    Found match at line 926, position 21
    Found match at line 941, position 21
    Found match at line 976, position 23
    Found match at line 1021, position 21
    Found match at line 1031, position 23
    Found match at line 1096, position 19
    Found match at line 1106, position 21
    Found match at line 1136, position 23
    Found match at line 1191, position 23
    Found match at line 1201, position 25
    Found match at line 1211, position 25
    Found match at line 1226, position 25
    Found match at line 1276, position 25
    Found match at line 1286, position 25
    Found match at line 1311, position 25
    Found match at line 1326, position 25
    Found match at line 1346, position 25
    Found match at line 1366, position 25
    Found match at line 1381, position 25
    Found match at line 1441, position 27
    Found match at line 1471, position 25
    Found match at line 1486, position 27
    Found match at line 1551, position 23
    Found match at line 1581, position 23
    Found match at line 1661, position 23
    Found match at line 1671, position 25
    Found match at line 1681, position 25
    Found match at line 1716, position 25
    Found match at line 1756, position 27
    Found match at line 1771, position 27
    Found match at line 1791, position 29
    Found match at line 1801, position 31
    Found match at line 1821, position 33
    Found match at line 1851, position 31
    Found match at line 1866, position 33
    Found match at line 1876, position 33
    Found match at line 1901, position 33
    Found match at line 1931, position 35
    Found match at line 1976, position 35
    Found match at line 2046, position 29
    Found match at line 2066, position 29
    Found match at line 2091, position 27
    Found match at line 2101, position 27
    Found match at line 2121, position 27
    Found match at line 2146, position 27
    Found match at line 2171, position 27
    Found match at line 2226, position 25
    Found match at line 2241, position 27
    Found match at line 2256, position 29
    Found match at line 2286, position 31
    Found match at line 2306, position 31
    Found match at line 2331, position 33
    Found match at line 2346, position 35
    Found match at line 2366, position 33
    Found match at line 2381, position 33
    Found match at line 2421, position 35
    Found match at line 2431, position 35
    Found match at line 2446, position 35
    Found match at line 2491, position 33
    Rabin-Karp: 15 miliseconds

    Found match at line 21, position 9
    Found match at line 61, position 9
    Found match at line 101, position 9
    Found match at line 121, position 9
    Found match at line 131, position 11
    Found match at line 216, position 7
    Found match at line 231, position 9
    Found match at line 241, position 11
    Found match at line 251, position 11
    Found match at line 271, position 13
    Found match at line 281, position 13
    Found match at line 311, position 13
    Found match at line 321, position 13
    Found match at line 371, position 11
    Found match at line 381, position 11
    Found match at line 416, position 11
    Found match at line 436, position 11
    Found match at line 461, position 11
    Found match at line 496, position 9
    Found match at line 511, position 9
    Found match at line 536, position 11
    Found match at line 566, position 11
    Found match at line 596, position 13
    Found match at line 626, position 11
    Found match at line 636, position 11
    Found match at line 676, position 9
    Found match at line 691, position 11
    Found match at line 716, position 13
    Found match at line 726, position 13
    Found match at line 766, position 11
    Found match at line 781, position 13
    Found match at line 796, position 13
    Found match at line 811, position 15
    Found match at line 826, position 17
    Found match at line 866, position 19
    Found match at line 911, position 19
    Found match at line 926, position 21
    Found match at line 941, position 21
    Found match at line 976, position 23
    Found match at line 1021, position 21
    Found match at line 1031, position 23
    Found match at line 1096, position 19
    Found match at line 1106, position 21
    Found match at line 1136, position 23
    Found match at line 1191, position 23
    Found match at line 1201, position 25
    Found match at line 1211, position 25
    Found match at line 1226, position 25
    Found match at line 1276, position 25
    Found match at line 1286, position 25
    Found match at line 1311, position 25
    Found match at line 1326, position 25
    Found match at line 1346, position 25
    Found match at line 1366, position 25
    Found match at line 1381, position 25
    Found match at line 1441, position 27
    Found match at line 1471, position 25
    Found match at line 1486, position 27
    Found match at line 1551, position 23
    Found match at line 1581, position 23
    Found match at line 1661, position 23
    Found match at line 1671, position 25
    Found match at line 1681, position 25
    Found match at line 1716, position 25
    Found match at line 1756, position 27
    Found match at line 1771, position 27
    Found match at line 1791, position 29
    Found match at line 1801, position 31
    Found match at line 1821, position 33
    Found match at line 1851, position 31
    Found match at line 1866, position 33
    Found match at line 1876, position 33
    Found match at line 1901, position 33
    Found match at line 1931, position 35
    Found match at line 1976, position 35
    Found match at line 2046, position 29
    Found match at line 2066, position 29
    Found match at line 2091, position 27
    Found match at line 2101, position 27
    Found match at line 2121, position 27
    Found match at line 2146, position 27
    Found match at line 2171, position 27
    Found match at line 2226, position 25
    Found match at line 2241, position 27
    Found match at line 2256, position 29
    Found match at line 2286, position 31
    Found match at line 2306, position 31
    Found match at line 2331, position 33
    Found match at line 2346, position 35
    Found match at line 2366, position 33
    Found match at line 2381, position 33
    Found match at line 2421, position 35
    Found match at line 2431, position 35
    Found match at line 2446, position 35
    Found match at line 2491, position 33
    Knuth-Morris-Pratt: 38 miliseconds
*/
}

