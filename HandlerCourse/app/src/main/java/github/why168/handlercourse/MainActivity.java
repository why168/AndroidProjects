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

    static class MyHandler extends Handler {
        private WeakReference<MainActivity> activityWeakReference;

        public MyHandler(MainActivity activity) {
            activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MainActivity activity = activityWeakReference.get();
            switch (msg.what) {
                case 0:
                    activity.main_image_view.setImageResource(R.mipmap.ic_launcher);
                    break;
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
            boolean isUiThreadNew = Looper.getMainLooper().isCurrentThread();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bitmapObject != null && !bitmapObject.isRecycled()) {
            bitmapObject.recycle();
            main_image_view.setImageBitmap(null);
            System.gc();
            Log.e("Edwin", "GC");
        }
    }
}
