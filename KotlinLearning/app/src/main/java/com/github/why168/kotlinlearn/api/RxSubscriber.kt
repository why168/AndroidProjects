package com.github.why168.kotlinlearn.api

import android.text.TextUtils
import com.github.why168.kotlinlearn.Constants
import com.github.why168.kotlinlearn.event.TokenEvent
import io.reactivex.subscribers.DefaultSubscriber
import org.greenrobot.eventbus.EventBus
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * RxSubscriber
 *
 * @author Edwin.Wu
 * @version 2018/2/23 下午3:30
 * @since JDK1.8
 */
public abstract class RxSubscriber<T : HttpResult<*>> : DefaultSubscriber<T>() {

    override fun onComplete() {
//        DialogHelper.stopProgressDlg()
    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
//        DialogHelper.stopProgressDlg()
        val errorMsg: String
        if (e is SocketTimeoutException
                || e is ConnectException
                || e is UnknownHostException) {
            errorMsg = Constants.ERROR_NETWORK_2
        } else if (e is HttpException) {
            val code = e.code()
            when (code) {
                401 -> {
                }
                403 -> {
                }
                404 -> {
                }
                405 -> {
                }
                500 -> {
                }
            }
            errorMsg = e.message!!
        } else {
            errorMsg = e.message!!
        }
        onFailure(errorMsg)
    }

    override fun onNext(t: T) {
        var msg = t.msg
        if (TextUtils.isEmpty(msg))
            msg = "请求失败"
        when (t.status) {
            Constants.HTTP_STATUS_0// 成功
            -> onResponse(t)
            Constants.HTTP_STATUS_1// 验证签名超时
            -> onFailure(msg)
            Constants.HTTP_STATUS_2// 验证签名失败
            -> onFailure(msg)
            Constants.HTTP_STATUS_3// 请求错误
            -> onFailure(msg)
            Constants.HTTP_STATUS_4// token过期
            -> EventBus.getDefault().post(TokenEvent.OUT_DATA)
            Constants.HTTP_STATUS_5// 登录失败
            -> onFailure(msg)
            Constants.HTTP_STATUS_6// 验证码发送失败
            -> onFailure(msg)
            Constants.HTTP_STATUS_7// 验证码发送过于频繁,1分钟同一个手机只能发一次
            -> onFailure(msg)
            Constants.HTTP_STATUS_8// 参数参数验证不通过
            -> onFailure(msg)
            Constants.HTTP_STATUS_9// 发现在其他设备登录
            -> EventBus.getDefault().post(TokenEvent.OTHER_DEVICE)
        }
    }

    abstract fun onResponse(t: T)

    abstract fun onFailure(msg: String?)
}