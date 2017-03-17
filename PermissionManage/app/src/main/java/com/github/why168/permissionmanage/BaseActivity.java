package com.github.why168.permissionmanage;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * @author Edwin.Wu
 * @version 2017/3/17$ 14:12$
 * @since JDK1.8
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 为子类提供一个权限检查方法
     *
     * @param permissions
     * @return
     */
    public boolean hasPermission(String... permissions) {
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(this, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 权限请求申请
     *
     * @param requestCode 请求码
     * @param permissions 权限
     */
    public void requestPermission(int requestCode, String... permissions) {
        ActivityCompat.requestPermissions(this, permissions, requestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PermissionsConstans.WRITE_STORAGE_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    doSDCardPermission();
                } else {
                    //TODO 提示用户权限未授予
                    Toast.makeText(BaseActivity.this, "WRITE_EXTERNAL_STORAGE 权限未开启", Toast.LENGTH_SHORT).show();
                }
                break;
            case PermissionsConstans.CALL_PHONE_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    doCallPhone();
                } else {
                    //TODO 提示用户权限未授予
                    Toast.makeText(BaseActivity.this, "ACTION_CALL 权限未开启", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }


    /**
     * 默认的写SD权限处理
     */
    protected void doSDCardPermission() {
        //TODO
    }

    /**
     * 默认的打电话处理
     */
    protected void doCallPhone() {
        //TODO 
    }
}
