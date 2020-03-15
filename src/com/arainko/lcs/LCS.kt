package com.arainko.lcs

typealias ElementArray = Array<Array<LcsElement>>
typealias LengthIndex = Pair<Int, Int>

inline fun <reified T> arrayOfDim(dimX: Int, dimY: Int, noinline init: (Int) -> T): Array<Array<T>> =
        Array(dimY) { Array(dimX, init) }


fun lcsLength(verticalString: String, horizontalString: String): ElementArray {

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

fun maxLengthIndices(lcsArray: ElementArray): ArrayList<LengthIndex> {
    val output: ArrayList<LengthIndex> = ArrayList()
    for (i in lcsArray.size - 1 downTo 1) {
        for (j in lcsArray[0].size - 1 downTo 1) {
            if (lcsArray[i][j].value == lcsArray[lcsArray.size - 1][lcsArray[0].size - 1].value &&
                    lcsArray[i][j].sign == Sign.DIAGONAL)
                output.add(i to j)
        }
    }
    return output
}

fun printLcs(verticalString: String, horizontalString: String, lcsArray: ElementArray) {
    val indices = maxLengthIndices(lcsArray)
    fun printHelper(verticalString: String, horizontalString: String, lcsArray: ElementArray, i: Int, j: Int) {
        if (i == 0 || j == 0) return

        when (lcsArray[i][j].sign) {
            Sign.DIAGONAL -> {
                printHelper(verticalString, horizontalString, lcsArray, i - 1, j - 1)
                print(verticalString[i - 1])
            }
            Sign.VERTICAL -> printHelper(verticalString, horizontalString, lcsArray, i - 1, j)
            Sign.HORIZONTAL -> printHelper(verticalString, horizontalString, lcsArray, i, j - 1)
        }
    }
    indices.forEach {
        printHelper(verticalString, horizontalString, lcsArray, it.first, it.second)
    }

}


fun main() {
    val testString1 = "abcabcaa"
    val testString2 = "acbacba"
    val cos = lcsLength(testString1, testString2)
    printLcs(testString1, testString2, cos)
//    cos.forEach { println(it.toList()) }
}