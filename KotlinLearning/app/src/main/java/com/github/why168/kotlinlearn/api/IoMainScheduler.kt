package com.github.why168.kotlinlearn.api

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *
 *
 * @author Edwin.Wu
 * @version 2018/2/24 上午11:32
 * @since JDK1.8
 */
class IoMainScheduler <T> : BaseScheduler<T>(Schedulers.io(), AndroidSchedulers.mainThread())