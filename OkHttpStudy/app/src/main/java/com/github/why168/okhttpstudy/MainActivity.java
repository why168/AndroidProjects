package com.github.why168.okhttpstudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.net.HttpURLConnection;
import java.util.concurrent.ThreadPoolExecutor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void okHttp$1(View view) {
        SimpleOkHttp.okHttp$1();
    }

    public void okHttp$2(View view) {
        SimpleOkHttp.okHttp$2();
    }
}
