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
 * NormalRowView
 *
 * @author Edwin.Wu
 * @version 2016/5/27 21:44
 * @since JDK1.8
 */
public class NormalRowView extends BaseRowView implements View.OnClickListener {
    private Context contenx;
    private ImageButton mWidgetRowActionBtn;
    private ImageView mWidgetRowIconImg;
    private TextView mWidgetRowLable;
    private RowDescriptor descriptor;
    private OnRowClickListener listener;

    public NormalRowView(Context context) {
        super(context);
        this.contenx = context;
        initializeView();
    }


    public NormalRowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.contenx = context;
        initializeView();
    }

    public NormalRowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.contenx = context;
        initializeView();
    }


    private void initializeView() {
        LayoutInflater.from(contenx).inflate(R.layout.widget_general_row, this);
        setBackgroundResource(R.drawable.widgets_emo_group_selector);
        mWidgetRowActionBtn = (ImageButton) findViewById(R.id.mWidgetRowActionBtn);
        mWidgetRowIconImg = (ImageView) findViewById(R.id.mWidgetRowIconImg);
        mWidgetRowLable = (TextView) findViewById(R.id.mWidgetRowLable);

    }

    public void initializeData(BaseRowDescriptor descriptor, OnRowClickListener listener) {
        this.descriptor = (RowDescriptor) descriptor;
        this.listener = listener;

    }

    public void notifyDataChangeed() {
        if (descriptor != null) {

            mWidgetRowIconImg.setBackgroundResource(descriptor.iconResId);
            mWidgetRowActionBtn.setBackgroundResource(R.mipmap.bakchat_submenu_normal);
            mWidgetRowLable.setText(descriptor.label);
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
