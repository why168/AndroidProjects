package com.github.why168.okhttpstudy;

import org.junit.Test;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * SimpleOkHttp
 *
 * @author Edwin.Wu
 * @version 2017/3/16 01:01
 * @since JDK1.8
 */
public class SimpleOkHttp {

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
}
