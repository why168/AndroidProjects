package com.github.why168.volleystudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.why168.volleystudy.study.RequestActivity;
import com.github.why168.volleystudy.study.T1Activity;
import com.github.why168.volleystudy.study.ThreadActivity;

/**
 * https://developer.android.com/training/volley/index.html
 * <p>
 * https://android-developers.googleblog.com/2011/09/androids-http-clients.html
 *
 * @author Edwin.Wu
 * @version 2017/2/28 23:13
 * @since JDK1.8
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Start$RequestActivity(View view) {
        startActivity(new Intent(this, RequestActivity.class));
    }

    public void Start$T1Activity(View view) {
        startActivity(new Intent(this, T1Activity.class));
    }

    public void Start$ThreadActivity(View view) {
        startActivity(new Intent(this, ThreadActivity.class));
    }
}
