package com.github.why168.kotlinlearn.api

import com.github.why168.kotlinlearn.bean.HomeBean
import io.reactivex.Flowable
import okhttp3.ResponseBody

/**
 * APIWrapper
 *
 * @author Edwin.Wu
 * @version 2018/2/23 上午11:56
 * @since JDK1.8
 */
object APIWrapper : RetrofitUtil() {

    fun getCommonRequest(requestUrl: String): Flowable<ResponseBody> {
        return getAPIService()!!.getCommonRequest(requestUrl)
    }

    fun postCommonRequest(requestUrl: String): Flowable<ResponseBody> {
        return getAPIService()!!.postCommonRequest(requestUrl)
    }

    fun getFirstHomeData(num:Int): Flowable<HomeBean> {
        return getAPIService()!!.getFirstHomeData(num)
    }
}