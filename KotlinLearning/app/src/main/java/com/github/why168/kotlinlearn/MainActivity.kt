package com.github.why168.kotlinlearn

import android.os.Bundle
import android.widget.Toast
import com.github.why168.kotlinlearn.api.APIWrapper
import com.github.why168.kotlinlearn.api.SchedulerUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun setUpData(savedInstanceState: Bundle?) {
        main_httpButton.setOnClickListener {
            Toast.makeText(MyApplication.mContext, "网络请求", Toast.LENGTH_LONG).show()

            APIWrapper
                    .getFirstHomeData(1)
                    .compose(SchedulerUtils.ioToMain())
                    .subscribe({ homeBean ->
                        main_txt.text = homeBean.toString()
                    }, { error ->
                        error.printStackTrace()
                    })
        }
    }
}

