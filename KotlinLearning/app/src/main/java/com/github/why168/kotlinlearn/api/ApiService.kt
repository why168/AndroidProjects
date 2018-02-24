package com.github.why168.kotlinlearn.api

import com.github.why168.kotlinlearn.bean.HomeBean
import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * ApiService
 *
 * @author Edwin.Wu
 * @version 2018/2/23 上午10:53
 * @since JDK1.8
 */
interface ApiService {

    /**
     * 通用的get请求
     *
     * @param requestUrl 请求url
     * @return ResponseBody
     */
    @GET
    abstract fun getCommonRequest(@Url requestUrl: String): Flowable<ResponseBody>

    /**
     * 通用的post请求
     *
     * @param requestUrl 请求url
     * @return ResponseBody
     */
    @POST
    abstract fun postCommonRequest(@Url requestUrl: String): Flowable<ResponseBody>


    /**
     * 首页精选
     */
    @GET("v2/feed?")
    fun getFirstHomeData(@Query("num") num:Int): Flowable<HomeBean>
}