package com.arainko.regex

fun String.naive(regex: String) {
    for (i in this.length..regex.length)
        if (this[i]==regex[i+1])
            print(this[i])
}

fun main() {

}