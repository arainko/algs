package com.arainko.lcs

enum class Sign {
    VERTICAL, DIAGONAL, HORIZONTAL;

    override fun toString(): String = when (this) {
        VERTICAL -> "|"
        DIAGONAL -> "\\"
        HORIZONTAL -> "-"
    }
}