package com.github.why168.baseview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * BaseRowView
 *
 * @author Edwin.Wu
 * @version 2016/5/28 21:24
 * @since JDK1.8
 */
public abstract class BaseRowView extends LinearLayout {
    public BaseRowView(Context context) {
        super(context);

    }

    public BaseRowView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseRowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public abstract void initializeData(BaseRowDescriptor descriptors, OnRowClickListener listener);

    public abstract void notifyDataChangeed();
}
