package com.github.why168.kotlinlearn.api

import io.reactivex.subscribers.DefaultSubscriber

/**
 *
 *
 * @author Edwin.Wu
 * @version 2018/2/23 下午6:18
 * @since JDK1.8
 */
abstract class RxSubscriber2<T : HttpResult<T>> : DefaultSubscriber<T>() {

    abstract fun onResponse(t: T)

    abstract fun onFailure(msg: String)
}