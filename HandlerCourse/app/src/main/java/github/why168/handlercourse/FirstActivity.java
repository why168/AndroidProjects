package github.why168.handlercourse;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Handler主线程与子线程通讯
 * <p>
 * 轮询
 *
 * @author Edwin.Wu
 * @version 2017/2/28 13:40
 * @since JDK1.8
 */
public class FirstActivity extends AppCompatActivity {
    private TextView textView;
    private HandlerThread thread;
    private Handler threadHandler;
    private Handler mainHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Message message = new Message();
            threadHandler.sendMessageDelayed(message, 1000);
            Log.e("Edwin", "-----main handler-----");
            textView.setText("main handler");

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        textView = (TextView) findViewById(R.id.textView);

        thread = new HandlerThread("Handler Thread");
        thread.start();
        threadHandler = new Handler(thread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Message message = new Message();
                mainHandler.sendMessageDelayed(message, 1000);
                Log.e("Edwin", "-----thread handler-----");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("thread handler");
                    }
                });
            }
        };

    }

    public void startHandler(View view) {
        mainHandler.sendEmptyMessage(1);
    }

    public void stopHandler(View view) {
        threadHandler.removeCallbacksAndMessages(null);
        mainHandler.removeMessages(1);
    }
}
