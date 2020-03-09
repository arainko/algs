package com.arainko.lcs

data class Element(var value: Int = 0, var sign: Sign = Sign.HORIZONTAL): Comparable<Element> {
    override fun compareTo(other: Element): Int = this.value - other.value
}