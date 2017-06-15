package com.github.why168.mvpstudy.base;

/**
 * BaseView
 *
 * @author Edwin.Wu
 * @version 2017/5/27 16:13
 * @since JDK1.8
 */
public interface BaseView<T> {
    void setPresenter(T presenter);
}
