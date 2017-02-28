package github.why168.handlercourse;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

/**
 * Handler 教程
 * https://developer.android.com/training/multiple-threads/communicate-ui.html#Handler
 * <p>
 * https://developer.android.com/reference/android/os/Handler.html
 * <p>
 * http://mp.weixin.qq.com/s/eDjFF-zAr6STaJ7hKhIoDA
 * <p>
 * http://www.cnblogs.com/yw-ah/p/5830458.html
 * <p>
 * http://blog.csdn.net/wuleihenbang/article/details/17126371
 *
 * @author Edwin.Wu
 * @version 2017/2/23 19:06
 * @since JDK1.8
 */
public class MainActivity extends AppCompatActivity {
    private ImageView main_image_view;
    private Bitmap bitmapObject;
    private final MyHandler mHandler = new MyHandler(this);
    private static int count = 0;

    private static class MyHandler extends Handler {
        private WeakReference<MainActivity> activityWeakReference;

        private MyHandler(MainActivity activity) {
            super();
            activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MainActivity activity = activityWeakReference.get();
            if (activity != null) {
                switch (msg.what) {
                    case 0:
                        activity.main_image_view.setImageResource(R.mipmap.ic_launcher);
                        break;
                    case 100:
                        count++;
                        Log.e("Edwin", "count = " + count);
                        break;
                }
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_image_view = (ImageView) findViewById(R.id.main_image_view);

        bitmapObject = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        main_image_view.setImageBitmap(bitmapObject);


        boolean isUiThread = Looper.getMainLooper().getThread() == Thread.currentThread();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            isUiThread = Looper.getMainLooper().isCurrentThread();
        }
        Log.e("Edwin", "isUiThread = " + isUiThread);

        Message message = mHandler2.obtainMessage();
        message.what = 1;
        message.obj = 100;
        message.setTarget(mHandler2);
        message.sendToTarget();

        Message message2 = mHandler2.obtainMessage();
        message2.what = 0;
        message2.obj = 200;
        message2.sendToTarget();


        new LooperThread1().start();
        new LooperThread2().start();

//        Activity
    }

    Handler mHandler2 = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            //TODO 拦截消息，包装修改，再返回给上面
            int what = msg.what;
            if (what == 1) {
                msg.obj = "win";
                return false;
            } else {
                return true;
            }
        }
    }) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //TODO 处理消息
            Log.e("Edwin", "mHandler2---->currentThread:  " + Thread.currentThread());
            if (msg.what == 1)
                Log.e("Edwin", "msg = " + msg.obj);

        }
    };


    /**
     * 子线程中用Handler的方法一
     * 自定义与线程相关的Handler
     */
    class LooperThread1 extends Thread {
        public Handler mHandler;

        @Override
        public void run() {
            super.run();

            Looper.prepare();
            mHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    Log.e("Edwin", "LooperThread1---->what = " + msg.what + " ,currentThread:  " + Thread.currentThread());
                }
            };

            mHandler.sendEmptyMessage(1);
            Looper.loop();
            // TODO: 2017/2/27 Loop后面不能执行了
            mHandler.sendEmptyMessage(2);
            mHandler.sendEmptyMessage(3);
            mHandler.sendEmptyMessage(4);
            Looper.loop();
        }
    }

    /**
     * 子线程中用Handler的方法二
     * 自定义与线程相关的Handler
     */
    class LooperThread2 extends Thread {
        public Handler mHandler;

        @Override
        public void run() {
            super.run();

            mHandler = new Handler(Looper.getMainLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    Log.e("Edwin", "LooperThread2 ok");
                }
            };

            mHandler.sendEmptyMessage(1);
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);

        if (bitmapObject != null && !bitmapObject.isRecycled()) {
            bitmapObject.recycle();
            main_image_view.setImageBitmap(null);
            System.gc();
            Log.e("Edwin", "GC");
        }
    }
}
