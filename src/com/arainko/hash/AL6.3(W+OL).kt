package com.arainko.hash

import java.io.File

data class Record(val quantity: Int, val surname: String)

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

    //H(k,i) = (h(k) + i) mod m

    fun hash(num: Long, tryNo: Long, mod: Long): Long = ((num % mod) + tryNo) % mod

    fun insertAndGetTries(record: Record, array: Array<Record>): Long {
        val numberizedSurname = record.surname.convertToNumber()
        val size = array.size.toLong()
        tailrec fun insert(tryNo: Long): Long = when {
            array[hash(numberizedSurname, tryNo, size).toInt()].surname == "empty" -> {
                array[hash(numberizedSurname, tryNo, size).toInt()] = record
                tryNo
            } else -> insert(tryNo + 1)
        }
        return insert(0)
    }

    val surnameRecords = File("src/com/arainko/hash/surnames.txt").readLines()
            .map { Record(it.split(" ")[0].toInt(), it.split(" ")[1]) }

    val testArr: Array<Record> = Array(10) { Record(0, "empty") }

    val try1 = insertAndGetTries(surnameRecords[0], testArr)
    val try2 = insertAndGetTries(surnameRecords[1], testArr)
    val try3 = insertAndGetTries(surnameRecords[2], testArr)
    testArr.forEach {
        println(it)
    }
    println(try1)
    println(try2)
    println(try3)

    val percent50Array: Array<Record> = Array(40000) { Record(0, "empty") }
    val percent70Array: Array<Record> = Array(28600) { Record(0, "empty") }
    val percent90Array: Array<Record> = Array(22300) { Record(0, "empty") }

    val tries50: ArrayList<Long> = ArrayList()
    val tries70: ArrayList<Long> = ArrayList()
    val tries90: ArrayList<Long> = ArrayList()

    surnameRecords.forEach {
        tries50.add(insertAndGetTries(it, percent50Array))
        tries70.add(insertAndGetTries(it, percent70Array))
        tries90.add(insertAndGetTries(it, percent90Array))
    }

    val avg50 = tries50.average()
    val avg70 = tries70.average()
    val avg90 = tries90.average()

    println("50%: $avg50")
    println("70%: $avg70")
    println("90%: $avg90")


}