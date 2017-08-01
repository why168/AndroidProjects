package com.github.why168.java

/**
 *
 * @author Edwin.Wu
 * @version 2017/8/1 09:54
 * @since  JDK1.8
 */
fun main(args: Array<String>) {

    val x = 0
    when (x) {
        0, 1 -> println("x == 0 or x == 1")
        else -> println("otherwise")
    }

    val s = "0"
    when (x) {
        parseInt(s) -> println("s encodes x")
        else -> println("s does not encode x")
    }

    val validNumbers = 0..1

    when (x) {
        in 1..10 -> println("x is in the range")
        in validNumbers -> println("x is valid")
        !in 10..20 -> println("x is outside the range")
        else -> println("none of the above")
    }

    println(hasPrefix("prefix"))

    val array = arrayOf("A", "B", "C", "D")
    for ((index, value) in array.withIndex()) {
        println("the element at $index is $value")
    }



}

fun hasPrefix(x: Any) = when (x) {
    is String -> x.startsWith("prefix")
    else -> false
}