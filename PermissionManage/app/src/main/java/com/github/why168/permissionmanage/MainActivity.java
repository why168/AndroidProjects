package com.github.why168.permissionmanage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * Android6.0以后 危险权限声明
 * <p>
 * https://developer.android.google.cn/guide/topics/security/permissions.html
 * <p>
 * http://www.cnblogs.com/why168888/p/5618650.html
 * <p>
 * adb shell pm list permissions -d -g
 * <p>
 * ContextCompat.checkSelfPermission()
 * ActivityCompat.requestPermissions();
 * ActivityCompat.shouldShowRequestPermissionRationale()
 * <p>
 * 1.BaseActivity
 * 2.PermissionGenActivity
 *
 * @author Edwin.Wu
 * @version 2017/3/17 23:12
 * @since JDK1.8
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Permission$Activity(View view) {
        startActivity(new Intent(this, PermissionActivity.class));
    }

    public void PermissionGen$Activity(View view) {
        startActivity(new Intent(this, PermissionGenActivity.class));
    }

}
