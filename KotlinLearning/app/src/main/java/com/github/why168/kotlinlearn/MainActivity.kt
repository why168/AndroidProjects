package com.github.why168.kotlinlearn

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.github.why168.kotlinlearn.api.APIWrapper
import com.github.why168.kotlinlearn.api.SchedulerUtils
import com.github.why168.kotlinlearn.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    @SuppressLint("SetJavaScriptEnabled", "SetTextI18n")
    override fun setUpData(savedInstanceState: Bundle?) {
        // setText
        main_txt.text = "KotlinLearn"
        main_txt.setOnClickListener({
            Toast.makeText(it.context, "KotlinLearn", Toast.LENGTH_SHORT).show()
        })

        main_httpButton.setOnClickListener {
            Toast.makeText(this, "网络请求", Toast.LENGTH_LONG).show()
            // HTTP
            APIWrapper
                    .getFirstHomeData(1)
                    .compose(SchedulerUtils.ioToMain())
                    .subscribe({ homeBean ->
                        Toast.makeText(this, "请求成功", Toast.LENGTH_LONG).show()
                        println(homeBean.toString())
                    }, { error ->
                        Toast.makeText(this, "请求失败", Toast.LENGTH_LONG).show()
                        error.printStackTrace()
                    })
        }


        // WebView
        val settings = main_WebView.settings
        settings.javaScriptEnabled = true
        main_WebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view!!.loadUrl(request!!.url.toString())
                }
                return true
            }
        }
        main_WebView.loadUrl("https://github.com/why168/JavaProjects/blob/master/Kotlinlang/README.md")
    }

    fun onClickTest(view: View) {
        Toast.makeText(this, "测试onClick", Toast.LENGTH_LONG).show()
    }
}


