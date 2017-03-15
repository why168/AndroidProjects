package com.github.why168.volleystudy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.android.internal.http.multipart.FilePart;
import com.android.internal.http.multipart.Part;
import com.android.internal.http.multipart.StringPart;
import com.github.why168.volley.Request;
import com.github.why168.volley.RequestQueue;
import com.github.why168.volley.Response;
import com.github.why168.volley.VolleyError;
import com.github.why168.volley.toolbox.MultipartRequest;
import com.github.why168.volley.toolbox.OkApacheClientStack;
import com.github.why168.volley.toolbox.OkHttpURLConnectionStack;
import com.github.why168.volley.toolbox.StringRequest;
import com.github.why168.volley.toolbox.Volley;
import com.github.why168.volleystudy.study.RequestActivity;
import com.github.why168.volleystudy.study.T1Activity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.apache.OkApacheClient;
import okhttp3.logging.HttpLoggingInterceptor;

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
}
