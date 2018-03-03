package com.github.why168.kotlinlearn.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

/**
 *
 * BaseActivity
 *
 * @author Edwin.Wu
 * @version 2018/2/22 下午11:01
 * @since JDK1.8
 */
open abstract class BaseActivity : AppCompatActivity() {
    protected var mContext: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        setContentView(getLayoutResId())
        setUpData(savedInstanceState)
    }


    @LayoutRes
    protected abstract fun getLayoutResId(): Int

    protected abstract fun setUpData(savedInstanceState: Bundle?)

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            if (item.itemId == android.R.id.home) {
                finish()
                return true
            }
        }
        return false
    }

    /**
     * 隐藏虚拟键盘
     *
     * @param editText editText
     */
    protected fun hideKeyboard(editText: EditText) {
        val imm = applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (imm.isActive) {
            imm.hideSoftInputFromWindow(editText.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
        editText.isCursorVisible = false
    }

    /**
     * 隐藏软键盘
     */
    protected fun hideKeyboard() {
        val view = currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}