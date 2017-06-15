package com.github.why168.kotlinlearning

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).

 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {
    @Test
    @Throws(Exception::class)
    fun addition_isCorrect() {
        assertEquals(4, (2 + 2).toLong())
    }

    @Test
    fun text1() {
        //常量数组int[][][] arrs = new int[3][2][1];
        val arrs = Array(3) { Array(2) { IntArray(1) } }

        //空安全变量
        var str: String = "hello"
        var str1 = "word"
        //可为空字符串变量
        var str2: String? = null
    }

}