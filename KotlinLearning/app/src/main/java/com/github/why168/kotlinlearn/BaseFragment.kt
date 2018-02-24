package com.github.why168.kotlinlearn

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 *
 *
 * @author Edwin.Wu
 * @version 2018/2/22 下午11:10
 * @since JDK1.8
 */
open abstract class BaseFragment : Fragment() {
    protected var mContext: Context? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = activity
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater?.inflate(getLayoutResId(), container, false)
        setUpData(view)

        return view
    }

    @LayoutRes
    protected abstract fun getLayoutResId(): Int

    protected abstract fun setUpData(view: View?)
}