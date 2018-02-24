package com.github.why168.kotlinlearn.api

/**
 *
 *
 * @author Edwin.Wu
 * @version 2018/2/23 下午3:30
 * @since JDK1.8
 */
open class HttpResult<T> {
    var status: Int = 0
    var msg: String? = null
    var data: T? = null
}