package com.arainko.lcs

data class LcsElement(var value: Int = 0, var sign: Sign = Sign.HORIZONTAL): Comparable<LcsElement> {
    override fun compareTo(other: LcsElement): Int = this.value - other.value
    override fun toString(): String = "$sign$value"
}