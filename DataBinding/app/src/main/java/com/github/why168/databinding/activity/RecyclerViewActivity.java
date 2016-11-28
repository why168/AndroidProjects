package com.github.why168.databinding.activity;

import android.content.res.AssetManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.github.why168.databinding.R;
import com.github.why168.databinding.adapter.RecyclerViewAdapter;
import com.github.why168.databinding.base.BaseActivity;
import com.github.why168.databinding.databinding.ActivityRecyclerViewBinding;
import com.github.why168.databinding.model.Bean;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

/**
 * RecyclerView配套dataBinDing演示
 *
 * @author Edwin.Wu
 * @version 2016/11/28 10:59
 * @since JDK1.8
 */
public class RecyclerViewActivity extends BaseActivity<ActivityRecyclerViewBinding> {
    private RecyclerViewAdapter adapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_recycler_view;
    }

    @Override
    protected void setUpData() {
        RecyclerView view = (RecyclerView) b.getRoot();
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter = new RecyclerViewAdapter(this, getAllData().getData()));
        adapter.setOnItemClickLister(new RecyclerViewAdapter.onItemClickLister() {
            @Override
            public void onItemClick(View itemView, Bean.DataBean employee, int position) {
                Toast.makeText(itemView.getContext(), employee.toString(), Toast.LENGTH_SHORT).show();
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
