package com.github.why168.baseview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.github.why168.baseview.R;

import java.util.ArrayList;


/**
 * GroupView
 *
 * @author Edwin.Wu
 * @version 2016/5/27 22:42
 * @since JDK1.8
 */
public class GroupView extends LinearLayout {
    private ArrayList<BaseRowDescriptor> descriptors;
    private Context context;
    private OnRowClickListener lickListener;
    private String title;

    public GroupView(Context context) {
        super(context);
        initializeView(context);
    }

    public GroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView(context);
    }

    public GroupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeView(context);
    }


    private void initializeView(Context context) {
        this.context = context;
        setOrientation(VERTICAL);

        //LayoutInflater.from(context).inflate(resource,)


    }

    public void initializeData(GroupDescriptor descriptors, OnRowClickListener lickListener) {
        this.descriptors = descriptors.descriptors;
        this.title = descriptors.title;
        this.lickListener = lickListener;

    }

    public void notifyDataChangeed() {
        if (descriptors != null && descriptors.size() > 0) {
            BaseRowView row = null;
            View line = null;

            float density = context.getResources().getDisplayMetrics().density;
            LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = (int) (10 * density);

            BaseRowDescriptor descriptor = null;
            for (int i = 0; i < descriptors.size(); i++) {
                descriptor = descriptors.get(i);
                if (descriptor instanceof RowDescriptor) {
                    row = new NormalRowView(context);
                } else if (descriptor instanceof RowProfileDescriptor) {
                    row = new ProfileRowView(context);
                }
                row.initializeData(descriptor, lickListener);
                row.notifyDataChangeed();
                addView(row);
                if (i != descriptors.size() - 1) {
                    line = new View(context);
                    line.setBackgroundResource(R.color.widgets_general_row_line);
                    addView(line, params);
                }
            }
        } else {
            setVisibility(GONE);
        }
    }
}
