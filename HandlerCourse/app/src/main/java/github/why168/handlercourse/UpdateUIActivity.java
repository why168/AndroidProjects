package github.why168.handlercourse;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * 非UI线程更新UI的4中方式
 *
 * @author Edwin.Wu
 * @version 2017/2/28 15:38
 * @since JDK1.8
 */
public class UpdateUIActivity extends AppCompatActivity {
    private Thread thread;
    private TextView textView;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            textView.setText("update ui");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_ui);
        textView = (TextView) findViewById(R.id.textView);
        textView.setText("update ui" + Thread.currentThread().getId());

        thread = Thread.currentThread();

        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO: 2017/2/28 更新UI的4中方式
                UpdateUI$1();
//                UpdateUI$2();
//                UpdateUI$3();
//                UpdateUI$4();
            }
        }).start();

        threadDemo();

    }

    private void threadDemo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                /**
                 * 在非UI线程创建Handler,因没有Looper对象会报RuntimeException：
                 * Can't create handler inside thread that has not called Looper.prepare()
                 * 如果初始化Looper.prepare();则不会
                 */
                Looper.prepare();
                Handler handler = new Handler();

                /**
                 * 在onResume之前可以在非UI线程中更新UI
                 * 因为ActivityThread里面的ContextImpl还没初始化checkThread()执行不了，所以不会出问题
                 * 但是在这里做毫时的操作会出现---->android.view.ViewRootImpl$CalledFromWrongThreadException
                 * Only the original thread that created a view hierarchy can touch its views.
                 */

//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

                textView.append("\n update ui" + Thread.currentThread().getId());
                textView.append("\n " + (thread == Thread.currentThread()));
            }
        }).start();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    private void UpdateUI$1() {
        handler.sendEmptyMessage(1);
    }

    private void UpdateUI$2() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView.setText("update ui");
            }
        });
    }

    private void UpdateUI$3() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText("update ui");
            }
        });
    }

    private void UpdateUI$4() {
        textView.post(new Runnable() {
            @Override
            public void run() {
                textView.setText("update ui");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
