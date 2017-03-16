package com.github.why168.volleystudy.study;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.why168.volleystudy.R;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * ThreadActivity
 *
 * @author Edwin.Wu
 * @version 2017/3/16 14:36
 * @since JDK1.8
 */
public class ThreadActivity extends AppCompatActivity {
    private Executor executor = Executors.newCachedThreadPool();
    private CacheDispatcher cacheDispatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

    }


    private class CacheDispatcher extends Thread {
        private volatile boolean mQuit = false;

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                    System.out.println(System.currentTimeMillis());
                } catch (Exception e) {
                    e.printStackTrace();
                    if (mQuit) {
                        return;
                    }
                    break;
                }
            }
        }

        public void quit() {
            mQuit = true;
            interrupt();
        }
    }

    public void thread$start(View view) {
        cacheDispatcher = new CacheDispatcher();
        cacheDispatcher.start();
    }

    public void thread$stop(View view) {
        cacheDispatcher.quit();
    }
}
