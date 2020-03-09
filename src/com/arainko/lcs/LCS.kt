package com.arainko.lcs


inline fun <reified T> arrayOfDim(dimX: Int, dimY: Int, noinline init: (Int) -> T): Array<Array<T>> =
        Array(dimY) { Array(dimX, init) }


fun lcsLength(string1: String, string2: String): Array<Array<Element>> {
    val x = string1.toCharArray()
    val y = string2.toCharArray()
    val c = arrayOfDim(x.size,y.size) { Element() }

    for (i in 1 until x.size-1)
        for(j in 1 until y.size-1) {
            if (x[i] == y[j]) {
                c[i][j].value = c[i-1][j-1].value + 1
                c[i][j].sign = Sign.DIAGONAL
            } else if (c[i-1][j] >= c[i][j-1]) {
                c[i][j].value = c[i-1][j].value
                c[i][j].sign = Sign.VERTICAL
            } else {
                c[i][j].value = c[i][j-1].value
                c[i][j].sign = Sign.HORIZONTAL
            }
        }
    return c
}
fun printLcs(string: String, lcsArray: Array<Array<Element>>) {
    val charArray = string.toCharArray()
    fun helper(x: CharArray, lcsArray: Array<Array<Element>>, i: Int, j: Int) {
        if (i == 0 || j == 0)
            return

        when (lcsArray[i][j].sign) {
            Sign.DIAGONAL -> {
                helper(x, lcsArray, i-1, j-1)
                print(x[i])
            }
            Sign.VERTICAL -> helper(x, lcsArray, i-1, j)
            else -> helper(x,lcsArray, i, j-1)
        }
    }
    helper(charArray, lcsArray, lcsArray.size, lcsArray[0].size)
}



fun main() {
val cos = lcsLength("ukgdpr", "warka")
//    printLcs("warka".toCharArray(), cos, cos.size-1, cos[0].size-1)
}