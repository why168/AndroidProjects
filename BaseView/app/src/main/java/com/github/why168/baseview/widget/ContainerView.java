package com.github.why168.baseview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * ContainerView
 *
 * @author Edwin.Wu
 * @version 2016/5/27 23:49
 * @since JDK1.8
 */
public class ContainerView extends LinearLayout {
    private Context context;
    private OnRowClickListener listener;
    private ArrayList<GroupDescriptor> descriptors;

    public ContainerView(Context context) {
        super(context);
        initializeView(context);
    }

    public ContainerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView(context);
    }

    public ContainerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeView(context);
    }

    private void initializeView(Context context) {
        this.context = context;
        setOrientation(VERTICAL);
        setBackgroundResource(android.R.color.transparent);
    }

    public void initializeData(ArrayList<GroupDescriptor> descriptors, OnRowClickListener listener) {
        this.descriptors = descriptors;
        this.listener = listener;
    }

    public void notifyDataChanged() {
        if (descriptors != null && descriptors.size() > 0) {
            GroupView groupView = null;
            float density = context.getResources().getDisplayMetrics().density;
            LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            params.topMargin = (int) (20 * density);

            for (GroupDescriptor descriptor : descriptors) {
                groupView = new GroupView(context);
                groupView.initializeData(descriptor, listener);
                groupView.notifyDataChangeed();
                addView(groupView, params);
            }
            setVisibility(VISIBLE);
        } else {
            setVisibility(GONE);
        }
    }
}
