package com.github.why168.java

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

/**
 *
 * @author Edwin.Wu
 * @version 2017/7/31 19:23
 * @since  JDK1.8
 */
fun main(args: Array<String>) {

    val map = mapOf("a" to 1, "b" to 2, "c" to 3)
    println(map["b"])

    val files = File("kotlinJava").listFiles()
    println(files?.size ?: "empty")


    val stream = Files.newInputStream(Paths.get("/some/file.txt"))
    stream.buffered()
            .reader()
            .use { reader ->
                println(reader.readText())
            }

}

fun transform(color: String): Int {
    return when (color) {
        "Red" -> 0
        "Green" -> 1
        "Blue" -> 2
        else -> throw IllegalArgumentException("Invalid color param value")
    }
}


