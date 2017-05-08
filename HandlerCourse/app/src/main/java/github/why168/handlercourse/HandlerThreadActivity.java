package github.why168.handlercourse;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * HandlerThread
 *
 * @author Edwin.Wu
 * @version 2017/2/28 11:24
 * @since JDK1.8
 */
public class HandlerThreadActivity extends AppCompatActivity {
    private MyHandler handler;
    private HandlerThread thread;
    private TextView textView;

    static class MyHandler extends Handler {
        WeakReference<HandlerThreadActivity> weakReference;

        public MyHandler(HandlerThreadActivity activity) {
            this.weakReference = new WeakReference<HandlerThreadActivity>(activity);
        }

        public MyHandler(Looper looper, HandlerThreadActivity activity) {
            super(looper);
            this.weakReference = new WeakReference<HandlerThreadActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            HandlerThreadActivity handlerThreadActivity = weakReference.get();
            if (handlerThreadActivity != null) {
                Log.d("Edwin ", "message： " + msg.what + "  thread： " + Thread.currentThread().getName());
                handlerThreadActivity.textView.setText("handleMessage");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_thread);

        textView = (TextView) findViewById(R.id.textView);

        example();
    }

    private void example() {
        thread = new HandlerThread("Handler Thread") {
            @Override
            protected void onLooperPrepared() {
                super.onLooperPrepared();
                //TODO 子线程操作
                textView.setText("onLooperPrepared");
            }
        };
        thread.start();

        handler = new MyHandler(thread.getLooper(), HandlerThreadActivity.this);
        handler.sendEmptyMessage(1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //TODO 释放资源
        thread.quit();
        handler.removeCallbacksAndMessages(null);
    }
}
