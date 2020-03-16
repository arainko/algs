package com.arainko.lcs

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.forEach
import kotlin.collections.plusAssign
import kotlin.collections.toList

typealias LcsTable = Array<Array<LcsElement>>
typealias LengthIndex = Pair<Int, Int>
fun <T> T.println() = println(this)

inline fun <reified T> arrayOfDim(dimX: Int, dimY: Int, noinline init: (Int) -> T): Array<Array<T>> =
        Array(dimY) { Array(dimX, init) }

fun lcsLength(verticalString: String, horizontalString: String): LcsTable {

    val elements = arrayOfDim(horizontalString.length + 1,
            verticalString.length + 1) { LcsElement() }

    for (i in 1 .. verticalString.length)
        for(j in 1 .. horizontalString.length)
            when {
                verticalString[i - 1] == horizontalString[j - 1] -> {
                    elements[i][j].value = elements[i-1][j-1].value + 1
                    elements[i][j].sign = Sign.DIAGONAL
                }
                elements[i-1][j] >= elements[i][j-1] -> {
                    elements[i][j].value = elements[i-1][j].value
                    elements[i][j].sign = Sign.VERTICAL
                }
                else -> {
                    elements[i][j].value = elements[i][j-1].value
                    elements[i][j].sign = Sign.HORIZONTAL
                }
            }

    return elements
}

fun maxLengthIndices(lcsTable: LcsTable): ArrayList<LengthIndex> {
    val output: ArrayList<LengthIndex> = ArrayList()
    val maxValue = lcsTable[lcsTable.size - 1][lcsTable[0].size - 1].value
    for (i in lcsTable.size - 1 downTo 1) {
        for (j in lcsTable[0].size - 1 downTo 1) {
            if (lcsTable[i][j].value == maxValue && lcsTable[i][j].sign == Sign.DIAGONAL)
                output += i to j
        }
    }
    return output
}

fun printLcs(verticalString: String, horizontalString: String, lcsTable: LcsTable) {
    val indices = maxLengthIndices(lcsTable)
    fun printHelper(verticalString: String, horizontalString: String, lcsTable: LcsTable, i: Int, j: Int) {
        if (i == 0 || j == 0) return

        when (lcsTable[i][j].sign) {
            Sign.DIAGONAL -> {
                printHelper(verticalString, horizontalString, lcsTable, i - 1, j - 1)
                print(verticalString[i - 1])
            }
            Sign.VERTICAL -> printHelper(verticalString, horizontalString, lcsTable, i - 1, j)
            Sign.HORIZONTAL -> printHelper(verticalString, horizontalString, lcsTable, i, j - 1)
        }
    }
    indices.forEach {
        printHelper(verticalString, horizontalString, lcsTable, it.first, it.second)
        println()
    }

}

fun printAllLcs(verticalString: String, horizontalString: String, lcsTable: LcsTable) {
    fun printAllLcsHelper(verticalString: String, horizontalString: String, lcsTable: LcsTable, i: Int, j: Int): MutableSet<String> {
        var output: MutableSet<String> = HashSet()

        if (i == 0 || j == 0) {
            output.add("")
            return output
        }

        if (verticalString[i-1] == horizontalString[j-1]) {
            val temp: Set<String> = printAllLcsHelper(verticalString, horizontalString, lcsTable,i-1, j-1)
            temp.forEach { output.add(it + verticalString[i-1]) }

        } else {
            if (lcsTable[i-1][j] >= lcsTable[i][j-1])
                output = printAllLcsHelper(verticalString, horizontalString, lcsTable, i-1, j)

            if (lcsTable[i][j-1] >= lcsTable[i-1][j]) {
                val temp: Set<String> = printAllLcsHelper(verticalString, horizontalString, lcsTable, i, j-1)
                output.addAll(temp)
            }
        }
        return output
    }
    printAllLcsHelper(verticalString, horizontalString, lcsTable, verticalString.length, horizontalString.length).forEach {
        println(it)
    }
}

fun main() {
    val testString1 = "abbaac"
    val testString2 = "bacbacba"
    val lcsTable = lcsLength(testString1, testString2)

    "LCS table: ".println()
    lcsTable.forEach { println(it.toList()) }

    "Normal LCS table traceback for all indices with max value: ".println()
    printLcs(testString1, testString2, lcsTable)

    "All LCS with recursive appending: ".println()
    printAllLcs(testString1, testString2, lcsTable)
}