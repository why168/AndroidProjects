package com.github.why168.volleystudy.study;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.why168.volleystudy.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executor;

/**
 * 练习1
 *
 * @author Edwin.Wu
 * @version 2017/3/15 12:20
 * @since JDK1.8
 */
public class T1Activity extends AppCompatActivity {
    private TextView text;
    private Executor mResponsePoster;
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t1);
        text = (TextView) findViewById(R.id.text);

        mResponsePoster = new Executor() {
            @Override
            public void execute(@NonNull Runnable command) {
                handler.post(command);
            }
        };

        new CacheDispatcher().start();

    }

    private class CacheDispatcher extends Thread {
        @Override
        public void run() {
            //打开连接
            try {
                URL url = new URL("http://ip.taobao.com/service/getIpInfo.php?ip=63.223.108.42");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                if (200 == urlConnection.getResponseCode()) {
                    //得到输入流
                    InputStream is = urlConnection.getInputStream();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while (-1 != (len = is.read(buffer))) {
                        baos.write(buffer, 0, len);
                        baos.flush();
                    }
                    String data = baos.toString("utf-8");

                    mResponsePoster.execute(new ResponseDeliveryRunnable(urlConnection.getResponseCode(), data));
                } else {
                    mResponsePoster.execute(new ResponseDeliveryRunnable(urlConnection.getResponseCode(), "Error"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private class ResponseDeliveryRunnable implements Runnable {
        private final int code;
        private final String data;

        ResponseDeliveryRunnable(int code, String data) {
            this.code = code;
            this.data = data;

        }

        @Override
        public void run() {
            text.setText("code = " + code + "\n data = " + data);
        }
    }
}
