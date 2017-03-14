package github.why168.handlercourse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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
 * @version 2017/3/14 15:38
 * @since JDK1.8
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Complex(View view) {
        startActivity(ComplexActivity.class);

    }

    public void First(View view) {
        startActivity(FirstActivity.class);

    }

    public void HandlerThread(View view) {
        startActivity(HandlerThreadActivity.class);

    }

    public void UpdateUI(View view) {
        startActivity(UpdateUIActivity.class);
    }

    public void startActivity(Class cls) {
        startActivity(new Intent(this, cls));
    }
}
