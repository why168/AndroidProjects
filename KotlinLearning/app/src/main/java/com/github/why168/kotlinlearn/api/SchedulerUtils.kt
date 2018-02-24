package com.github.why168.kotlinlearn.api

/**
 *
 *
 * @author Edwin.Wu
 * @version 2018/2/24 上午11:30
 * @since JDK1.8
 */
object SchedulerUtils {

    fun <T> ioToMain(): IoMainScheduler<T> {
        return IoMainScheduler()
    }
}