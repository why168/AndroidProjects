package com.github.why168.kotlinlearn.api

import com.github.why168.kotlinlearn.BuildConfig
import com.github.why168.kotlinlearn.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

/**
 * RetrofitUtil
 *
 * @author Edwin.Wu
 * @version 2018/2/23 上午11:30
 * @since JDK1.8
 */
open class RetrofitUtil {
    private var mRetrofit: Retrofit? = null
    private var mAPIService: ApiService? = null

    private fun getRetrofit(): Retrofit? {
        if (mRetrofit == null) {
            val builder = OkHttpClient.Builder()
            // Log Type
            val logging = HttpLoggingInterceptor()

            if (BuildConfig.DEBUG) {
                logging.level = HttpLoggingInterceptor.Level.BODY
            } else {
                logging.level = HttpLoggingInterceptor.Level.NONE
            }

            val client = builder
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .addNetworkInterceptor(logging)
                    .build()

            mRetrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build()

        }
        return mRetrofit
    }

    open fun getAPIService(): ApiService? {
        if (mAPIService == null) {
            mAPIService = getRetrofit()!!.create<ApiService>(ApiService::class.java)
        }
        return mAPIService
    }


}