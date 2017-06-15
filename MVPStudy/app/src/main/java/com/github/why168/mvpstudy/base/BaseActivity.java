package com.github.why168.mvpstudy.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * BaseActivity
 *
 * @author Edwin.Wu
 * @version 2017/5/27 16:02
 * @since JDK1.8
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        initializeView();
        initializeData(savedInstanceState);
    }

    @LayoutRes
    protected abstract int getLayoutResId();

    protected abstract void initializeView();

    protected abstract void initializeData(Bundle savedInstanceState);
}
