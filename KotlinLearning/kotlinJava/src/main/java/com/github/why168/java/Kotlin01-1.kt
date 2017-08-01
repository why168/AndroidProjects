package com.github.why168.java;

/**
 * 入门
 * @author Edwin.Wu
 * @version 2017/7/31 14:38
 * @since  JDK1.8
 */
fun sum1(a: Int, b: Int): Int {
    return a + b
}

fun sum2(a: Int, b: Int) = a + b

fun printSum1(a: Int, b: Int): Unit {
    println("sum of $a and $b is ${a + b}")
}

fun printSum2(a: Int, b: Int) {
    println("sum of $a and $b is ${a + b}")
}

fun parseInt(str: String): Int? {
    return str.toIntOrNull()
}

fun printProduct(arg1: String, arg2: String) {
    val x = parseInt(arg1)
    val y = parseInt(arg2)
//     Using `x * y` yields error because they may hold nulls.
    if (x != null && y != null) {
        // x and y are automatically cast to non-nullable after null check
        println(x * y)
    } else {
        println("either '$arg1' or '$arg2' is not a number")
    }
}

fun getStringLength(obj: Any): Int? {
    if (obj is String) {
        // `obj` is automatically cast to `String` in this branch
        return obj.length
    }

    // `obj` is still of type `Any` outside of the type-checked branch
    return null
}


fun describe(obj: Any): String =
        when (obj) {
            1 -> "One"
            "Hello" -> "Greeting"
            is Long -> "Long"
            !is String -> "Not a string"
            else -> "Unknown"
        }


fun main(args: Array<String>) {
    println("sum1-> sum of 3 and 5 is " + sum1(3, 5))
    println("---------")
    println("sum2-> sum of 3 and 5 is " + sum2(3, 5))
    println("---------")
    printSum1(-1, 8)
    println("---------")
    printSum2(-1, 8)

    val a: Int = 1  // immediate assignment
    val b = 2   // `Int` type is inferred
    val c: Int  // Type required when no initializer is provided
    c = 3       // deferred assignment
    println("a = $a, b = $b, c = $c")

    println("---------")

    var a1 = 1
    // simple name in template:
    val s1 = "a is $a"
    a1 = 2
    // arbitrary expression in template:
    val s2 = "${s1.replace("is", "was")}, but now is $a"
    println(s2)

    println("---------")

    printProduct("6", "7")
    printProduct("a", "7")
    printProduct("a", "b")

    println("---------")

    fun printLength(obj: Any) {
        println("'$obj' string length is ${getStringLength(obj) ?: "... err, not a string"} ")
    }
    printLength("Incomprehensibilities")
    printLength(1000)
    printLength(listOf(Any()))
    println("---------")

    val items = listOf("apple", "banana", "kiwi")
    for (item in items) {
        println(item)
    }

    println("---------")
    for (index in items.indices) {
        println("item at $index is ${items[index]}")
    }
    println("---------")

    val items2 = listOf("apple", "banana", "kiwi")
    var index = 0
    while (index < items2.size) {
        println("item at $index is ${items2[index]}")
        index++
    }
    println("---------")

    val describe = describe("Hello")
    println(describe)
    println("---------")

    val x = 10
    val y = 9
    if (x in 1..y + 1) {
        println("fits in range")
    }

    println("---------")
    val list = listOf("a", "b", "c")

    if (-1 !in 0..list.lastIndex) {
        println("-1 is out of range")
    }
    if (list.size !in list.indices) {
        println(list.indices)
        println(list.size)
        println("---list size is out of valid list indices range too---")
    }

    println("\n-------t in 1..10---------")

    for (t in 1..10) {
        print(t.toString() + " ")
    }

    println("\n\n-----t in 10 downTo 1---------")

    for (t in 10 downTo 1) {
        print(t.toString() + " ")
    }

    println("\n\n------o in 9 downTo 0 step 3-------")

    for (o in 9 downTo 0 step 3) {
        print(o.toString() + " ")
    }

    println("\n\n------t in 1..10 step 2-------")

    for (t in 1..10 step 2) {
        print(t.toString() + " ")
    }
    println("\n\n-----t in 1..10 step 3--------")

    for (t in 1..10 step 3) {
        print(t.toString() + " ")
    }

    println("\n\n-----i in 1 until 10--------")

    for (i in 1 until 10) { // i in [1, 10), 10 is excluded
        print(i.toString() + " ")
    }

    println("\n\n-----val items3 = setOf(\"apple\", \"banana\", \"kiwi\")--------")

    val items3 = setOf("apple", "orange", "kiwi")
    when {
        "orange" in items3 -> println("juicy")
        "apple" in items3 -> println("apple is fine too")
    }

    println("\n\n-----val fruits = listOf(\"banana\", \"avocado\", \"apple\", \"kiwi\")--------")

    val fruits = listOf("banana", "avocado", "apple", "kiwi")
    fruits
            .filter { it.startsWith("a") }
            .sortedBy { it }
            .map { it.toUpperCase() }
            .forEach { println(it) }
}
