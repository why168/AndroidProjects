package com.github.why168.collapseview.modle;

import android.support.annotation.LayoutRes;

/**
 * CollapseBean
 *
 * @author Edwin.Wu
 * @version 2016/12/2 16:44
 * @since JDK1.8
 */
public class CollapseBean {
    public String number;
    public String title;
    @LayoutRes
    public int resId;

    public CollapseBean(String number, String title, @LayoutRes int resId) {
        this.number = number;
        this.title = title;
        this.resId = resId;
    }

    @Override
    public String toString() {
        return "CollapseBean{" +
                "number='" + number + '\'' +
                ", title='" + title + '\'' +
                ", resId=" + resId +
                '}';
    }
}
