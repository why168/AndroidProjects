package com.github.why168.databinding.activity;

import android.content.res.AssetManager;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.github.why168.databinding.R;
import com.github.why168.databinding.adapter.ListViewAdapter;
import com.github.why168.databinding.base.BaseActivity;
import com.github.why168.databinding.databinding.ActivityListBinding;
import com.github.why168.databinding.model.Bean;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

/**
 * List配套dataBinDing演示
 *
 * @author Edwin.Wu
 * @version 2016/11/28 00:15
 * @since JDK1.8
 */
public class ListActivity extends BaseActivity<ActivityListBinding> {
    private ListViewAdapter adapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_list;
    }

    @Override
    protected void setUpData() {
        ListView root = (ListView) b.getRoot();
        root.setAdapter(adapter = new ListViewAdapter(mContext, getAllData().getData()));
        root.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bean.DataBean data = (Bean.DataBean) parent.getItemAtPosition(position);
                Toast.makeText(view.getContext(), data.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 从Assets读取本地数据
     *
     * @return 实体类
     */
    public Bean getAllData() {
        Bean recyclerBean = null;
        AssetManager assets = getResources().getAssets();
        InputStream fis = null;
        try {
            fis = assets.open("data.json");
            byte[] buffer = new byte[100];
            int len = 0;
            StringBuffer sb = new StringBuffer();
            while ((len = fis.read(buffer)) > 0) {
                sb.append(new String(buffer, 0, len));
            }
            recyclerBean = new Gson().fromJson(sb.toString(), Bean.class);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return recyclerBean;
    }
}
