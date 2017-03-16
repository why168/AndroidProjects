package com.github.why168.okhttpstudy;

import org.junit.Test;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * SimpleOkHttpUnitTest
 *
 * @author Edwin.Wu
 * @version 2017/3/16 01:01
 * @since JDK1.8
 */
public class SimpleOkHttpUnitTest {

    @Test
    public void okHttp$1() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://www.baidu.com")
                .header("User-Agent", "OkHttp Headers.java")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println("Result: " + response.isSuccessful());
        System.out.println("Server: " + response.header("Server"));
        System.out.println("ResponseBody: " + response.body().string());
    }

    @Test
    public void okHttp$2() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://www.baidu.com")
                .header("User-Agent", "OkHttp Headers.java")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("Exception: " + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("Result: " + response.isSuccessful());
                System.out.println("Server: " + response.header("Server"));
                System.out.println("ResponseBody: " + response.body().string());
            }
        });

    }
}
