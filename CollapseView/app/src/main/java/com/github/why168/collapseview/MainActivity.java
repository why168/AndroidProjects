package com.github.why168.collapseview;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.github.why168.collapseview.modle.CollapseBean;
import com.github.why168.collapseview.view.CollapseView;
import com.github.why168.collapseview.view.FlowLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * MainActivity
 *
 * @author Edwin.Wu
 * @version 2016/06/05 上午1:57
 * @since JDK1.8
 */
public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private ListView mListView;
    private List<CollapseBean> beanList = new ArrayList<>();
    private ImageView mImageView;
    private FlowLayout mFlowLayout;
    private ClickListenerImpl mClickListenerImpl;
    String[] tags = new String[]{"女朋友", "贤良淑德", "赞", "年轻美貌", "清纯", "温柔贤惠", "靓丽", "女神"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initCollapseView();
    }


    private void initCollapseView() {
        mContext = this;
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_view, null);

        mImageView = (ImageView) inflate.findViewById(R.id.imageView);
        mClickListenerImpl = new ClickListenerImpl();
        mImageView.setOnClickListener(mClickListenerImpl);
        mFlowLayout = (FlowLayout) inflate.findViewById(R.id.flowlayout);
        mListView = (ListView) findViewById(R.id.mListView);


        beanList.add(new CollapseBean("1", "女友", R.layout.expand_1));
        beanList.add(new CollapseBean("2", "妹子", R.layout.expand_2));
        beanList.add(new CollapseBean("3", "美女", R.layout.expand_3));

        mListView.setAdapter(new CollapseViewAdapter());
        mListView.addHeaderView(inflate);
    }

    private class ClickListenerImpl implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int textViewHeight = 80;
            ViewGroup.MarginLayoutParams marginLayoutParams =
                    new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, textViewHeight);
            marginLayoutParams.setMargins(30, 0, 30, 0);
            TextView textView = new TextView(mContext);
            textView.setLines(1);
            textView.setTextSize(20);
            textView.setPadding(25, 0, 25, 0);
            textView.setTextColor(Color.parseColor("#f58f98"));
            textView.setGravity(Gravity.CENTER);
            int index = (int) (Math.random() * tags.length);
            textView.setText(tags[index]);
            textView.setBackgroundResource(R.drawable.textview_backgroundresource);
            mFlowLayout.addView(textView, marginLayoutParams);
        }
    }

    private class CollapseViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return beanList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = new CollapseView(mContext);
            }
            CollapseBean collapseBean = beanList.get(position);

            ((CollapseView) convertView).setNumber(collapseBean.number);
            ((CollapseView) convertView).setTitle(collapseBean.title);
            ((CollapseView) convertView).setContent(collapseBean.resId);

            return convertView;
        }
    }
}
