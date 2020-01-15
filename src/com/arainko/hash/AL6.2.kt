package com.arainko.hash

import java.io.File


fun main() {
    operator fun String.invoke(i: Int): Long = this[i].toLong()

    fun String.convertToNumber(): Long {
        tailrec fun convert(input: String, output: Long = 0): Long = when {
            input.isEmpty() -> output
            input.length == 1 -> convert(input.drop(1), output xor (256 * input(0)))
            else -> convert(input.drop(2), output xor (input(0) * 256 + input(1)))
        }
        return convert(this)
    }

    fun linearHash(value: Long, mod: Long): Long = value % mod

    val numberizedSurnames = File("src/com/arainko/hash/3700.txt").readLines()
            .map { it.convertToNumber() }
            .toLongArray()
    val zeroArrNonFavorable = LongArray(1778)
    val zeroArrFavorable = LongArray(1777)

    numberizedSurnames.forEach { zeroArrNonFavorable[linearHash(it, 1778).toInt()]++ }
    numberizedSurnames.forEach { zeroArrFavorable[linearHash(it, 1777).toInt()]++ }

     val zeroPositionsNonFavorable = zeroArrNonFavorable
             .filter { it == 0L }
             .count()
    val zeroPositionsFavorable = zeroArrFavorable
            .filter { it == 0L }
            .count()
    val maxNonFavorable = zeroArrNonFavorable.max()
    val maxFavorable = zeroArrFavorable.max()
    val avgNonZeroNonFavorable = zeroArrNonFavorable
            .filter { it != 0L }
            .average()
    val avgNonZeroFavorable = zeroArrFavorable
            .filter { it != 0L }
            .average()
    println("Ilosc zerowych w NIEKORZYSTNEJ tablicy: $zeroPositionsNonFavorable")
    println("Ilosc zerowych w KORZYSTNEJ tablicy: $zeroPositionsFavorable")
    println("Maksymalna wartosc w NIEKORZYSTNEJ tablicy: $maxNonFavorable")
    println("Maksymalna wartosc w KORZYSTNEJ tablicy: $maxFavorable")
    println("Srednia z niezerowych w NIEKORZYSTNEJ tablicy: $avgNonZeroNonFavorable")
    println("Srednia z niezerowych w KORZYSTNEJ tablicy: $avgNonZeroFavorable")
}


