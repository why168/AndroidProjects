package com.github.why168.java

/**
 *
 * @author Edwin.Wu
 * @version 2017/7/31 20:54
 * @since  JDK1.8
 */
fun main(args: Array<String>) {
    val oneMillion = 1_000_000
    val creditCardNumber = 1234_5678_9012_3456L
    val socialSecurityNumber = 999_99_9999L
    val hexBytes = 0xFF_EC_DE_5E
    val bytes = 0b11010010_01101001_10010100_10010010


    val b: Byte = 1 // OK, literals are checked statically
    val i: Int = b.toInt()

    val decimalDigitValue = decimalDigitValue('1')
    println(decimalDigitValue)

    println("\n-----------------\n")

    val asc = Array(5, { i -> (i * i).toString() })
    println(asc[4])

    val arrayOf = arrayOf(1, 23, 32)


    val str = "abcdefg"
    for (s in str) {
        print(s.toString().trimMargin())
    }
    val price = """
${'$'}9.99${'$'}${'$'}${'$'}
"""

    println(price)

}


fun decimalDigitValue(c: Char): Int {
    if (c !in '0'..'9')
        throw IllegalArgumentException("Out of range")
    return c.toInt() - '0'.toInt() // Explicit conversions to numbers
}
