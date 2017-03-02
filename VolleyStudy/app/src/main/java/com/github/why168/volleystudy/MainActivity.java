package com.github.why168.volleystudy;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient()
                .newBuilder()
                .addInterceptor(logging)
                .build();

//        HttpRequest$1();
//        HttpRequest$2();
//        HttpRequest$3();

        MultipartRequest$4();
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

    /**
     * post请求
     * 多类型上传
     * 文件+字符
     */
    void MultipartRequest$4() {
        try {
            //构造参数列表
            List<Part> partList = new ArrayList<Part>();
            partList.add(new StringPart("username", "edwin"));
            partList.add(new StringPart("email", "edwin.wu@gmail.com"));

            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/ic_launcher.png";
            partList.add(new FilePart("ic_launcher", new File(path)));

            //获取队列
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String url = "http://www.cnblogs.com/";
            //生成请求
            MultipartRequest profileUpdateRequest = new MultipartRequest(url,
                    partList.toArray(new Part[partList.size()]),
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("Edwin", "onResponse : " + response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("Edwin", "MultipartRequest : " + error.getMessage(), error);
                        }
                    }) {
                @Override
                public String getBodyContentType() {
                    return super.getBodyContentType();
                }

            };
            requestQueue.add(profileUpdateRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
