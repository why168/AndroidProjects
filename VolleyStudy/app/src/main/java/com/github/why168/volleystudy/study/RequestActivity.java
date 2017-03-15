package com.github.why168.volleystudy.study;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.github.why168.volleystudy.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.apache.OkApacheClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 *
 * 请求方式
 *
 * @author Edwin.Wu
 * @version 2017/3/15 12:05
 * @since JDK1.8
 */
public class RequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient()
                .newBuilder()
                .addInterceptor(logging)
                .build();

    }

    Request initializedRequest(final String tag) {
        return new StringRequest(Request.Method.GET,
                "http://ip.taobao.com/service/getIpInfo.php?ip=63.223.108.42",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Edwin", tag + " ---> response = " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Edwin", tag + " ---> VolleyError = " + error);
                    }
                });
    }

    /**
     * 请求方式一：
     * <p>
     * volley默认的请求方式
     * 2.3以后底层用的是HttpURLConnection
     */
    public void HttpRequest$1(View view) {
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(initializedRequest("HttpRequest$1"));
    }

    /**
     * 请求方式二：
     * 底层使用okHttp-apache
     */
    public void HttpRequest$2(View view) {
        OkApacheClient okApacheClient = new OkApacheClient();
        OkApacheClientStack clientStack = new OkApacheClientStack(okApacheClient);

        RequestQueue queue = Volley.newRequestQueue(this, clientStack);
        queue.add(initializedRequest("HttpRequest$2"));
    }

    /**
     * 请求方式三：
     * 底层使用okHttp-urlConnection
     */
    public void HttpRequest$3(View view) {
        OkHttpURLConnectionStack clientStack = new OkHttpURLConnectionStack();

        RequestQueue queue = Volley.newRequestQueue(this, clientStack);
        queue.add(initializedRequest("HttpRequest$3"));
    }


    /**
     * 请求方式四：
     * 多类型上传(文件+字符)
     */
    public void HttpRequest$4(View view) {
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
                            Log.e("Edwin", "HttpRequest$4 ---> onResponse = " + response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("Edwin", "HttpRequest$4 ---> MultipartRequest = " + error.getMessage(), error);
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
