package github.why168.handlercourse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Handler 教程
 *
 * @author Edwin.Wu
 * @version 2017/2/23 19:06
 * @since JDK1.8
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     *
     * https://developer.android.com/reference/android/os/Handler.html
     *
     * https://developer.android.com/training/multiple-threads/communicate-ui.html#Handler
     *
     *
     * getMainLooper（）

     返回应用程序的主循环，它存在于应用程序的主线程中。
     myLooper（）

     返回与当前线程相关联的Looper对象。如果调用线程没有与Looper关联，则返回null。
     至于getMainLooper（）是否有任何用途，我可以向你保证它真的是。如果你在后台线程上做一些代码，并想在UI线程上执行代码，例如更新UI，请使用以下代码：

     new Handler(Looper.getMainLooper()).post(new Runnable() {
     // execute code that must be run on UI thread
     });
     当然，还有其他方法实现。

     另一个用法是，如果你想检查当前执行的代码是否正在UI线程上运行，例如你想throw / assert：

     boolean isUiThread = Looper.getMainLooper().getThread() == Thread.currentThread();
     要么

     boolean isUiThread = Looper.getMainLooper().isCurrentThread();
     */
}
