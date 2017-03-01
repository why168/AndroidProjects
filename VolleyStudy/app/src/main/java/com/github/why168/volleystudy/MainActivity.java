package com.github.why168.volleystudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.why168.volley.Request;
import com.github.why168.volley.RequestQueue;
import com.github.why168.volley.Response;
import com.github.why168.volley.VolleyError;
import com.github.why168.volley.toolbox.OkApacheClientStack;
import com.github.why168.volley.toolbox.OkHttpURLConnectionStack;
import com.github.why168.volley.toolbox.StringRequest;
import com.github.why168.volley.toolbox.Volley;

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

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient()
                .newBuilder()
                .addInterceptor(logging)
                .build();

        HttpRequest$1();
        HttpRequest$2();
        HttpRequest$3();

    }


    /**
     * 请求方式一：
     * <p>
     * volley默认的请求方式
     * 2.3以后底层用的是HttpURLConnection
     */
    void HttpRequest$1() {
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(initializedRequest());
    }

    /**
     * 请求方式二：
     * 底层使用okHttp-apache
     */
    void HttpRequest$2() {
        OkApacheClient okApacheClient = new OkApacheClient();
        OkApacheClientStack clientStack = new OkApacheClientStack(okApacheClient);

        RequestQueue queue = Volley.newRequestQueue(this, clientStack);
        queue.add(initializedRequest());
    }

    /**
     * 请求方式三：
     * 底层使用okHttp-urlConnection
     */
    void HttpRequest$3() {
        OkHttpURLConnectionStack clientStack = new OkHttpURLConnectionStack();

        RequestQueue queue = Volley.newRequestQueue(this, clientStack);
        queue.add(initializedRequest());
    }


    Request initializedRequest() {
        return new StringRequest(Request.Method.GET,
                "http://www.aybrowser.com/sdk/partners/",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Edwin", "response = " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Edwin", "VolleyError = " + error);
                    }
                });
    }
}
