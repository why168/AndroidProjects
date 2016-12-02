package com.github.why168.baseview.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.why168.baseview.R;

/**
 * ProfileRowView
 *
 * @author Edwin.Wu
 * @version 2016/5/28 21:27
 * @since JDK1.8
 */
public class ProfileRowView extends BaseRowView implements View.OnClickListener {

    private Context contenx;
    private ImageButton mWidgetRowActionBtn;
    private ImageView mWidgetRowIconImg;
    private TextView mWidgetRowNameLable;
    private TextView mWidgetRowIdLable;
    private RowProfileDescriptor descriptor;
    private OnRowClickListener listener;

    public ProfileRowView(Context context) {
        super(context);
        this.contenx = context;
        initializeView();
    }


    public ProfileRowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.contenx = context;
        initializeView();
    }

    public ProfileRowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.contenx = context;
        initializeView();
    }


    private void initializeView() {
        LayoutInflater.from(contenx).inflate(R.layout.widget_profile_row, this);
        setBackgroundResource(R.drawable.widgets_emo_group_selector);
        mWidgetRowActionBtn = (ImageButton) findViewById(R.id.mWidgetRowActionBtn);
        mWidgetRowIconImg = (ImageView) findViewById(R.id.mWidgetRowIconImg);
        mWidgetRowNameLable = (TextView) findViewById(R.id.mWidgetRowNameLable);
        mWidgetRowIdLable = (TextView) findViewById(R.id.mWidgetRowIdLable);

    }


    public void initializeData(BaseRowDescriptor descriptor, OnRowClickListener listener) {
        this.descriptor = (RowProfileDescriptor) descriptor;
        this.listener = listener;

    }

    public void notifyDataChangeed() {
        if (descriptor != null) {

            mWidgetRowIconImg.setBackgroundResource(R.mipmap.top);
            mWidgetRowActionBtn.setBackgroundResource(R.mipmap.bakchat_submenu_normal);
            mWidgetRowNameLable.setText(descriptor.detailLabel);
            mWidgetRowIdLable.setText(descriptor.label);
            if (descriptor.action != null) {
                setOnClickListener(this);
                setBackgroundResource(R.drawable.widgets_general_row_selector);
            } else {
                setBackgroundColor(Color.WHITE);
                mWidgetRowActionBtn.setVisibility(GONE);
            }

        } else {
            setVisibility(GONE);
        }
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onRowClick(descriptor.action);
        }
    }
}
