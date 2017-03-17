package com.github.why168.permissionmanage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Android6.0以后 危险权限声明
 * <p>
 * https://developer.android.google.cn/guide/topics/security/permissions.html
 * <p>
 * http://www.cnblogs.com/why168888/p/5618650.html
 *
 * @author Edwin.Wu
 * @version 2017/3/17 11:03
 * @since JDK1.8
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
