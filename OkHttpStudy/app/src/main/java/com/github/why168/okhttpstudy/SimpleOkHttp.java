package com.github.why168.okhttpstudy;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.logging.HttpLoggingInterceptor;

/**
 * SimpleOkHttp
 *
 * @author Edwin.Wu
 * @version 2017/3/16$ 10:40$
 * @since JDK1.8
 */
class SimpleOkHttp {
    private static Dispatcher dispatcher;
    private final static OkHttpClient client = new OkHttpClient
            .Builder()
            .dispatcher(dispatcher = new Dispatcher())
            .readTimeout(500, TimeUnit.MILLISECONDS)
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();

    private final static Request request = new Request.Builder()
            .url("http://www.baidu.com")
            .header("User-Agent", "OkHttp Headers.java")
            .build();

    /**
     * Caused by: android.os.NetworkOnMainThreadException
     * 同步执行
     */
    static void okHttp$1() {
        try {
            Response response = client.newCall(request).execute();
            System.out.println("Result: " + response.isSuccessful());
            System.out.println("Server: " + response.header("Server"));
            System.out.println("ResponseBody: " + response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 异步执行
     */
    static void okHttp$2() {
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
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
        call.cancel();
    }
}
