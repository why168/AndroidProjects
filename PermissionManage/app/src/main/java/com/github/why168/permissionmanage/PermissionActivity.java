package com.github.why168.permissionmanage;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.Manifest;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

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
 *
 * @author Edwin.Wu
 * @version 2017/3/17 11:59
 * @since JDK1.8
 */
public class PermissionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
    }

    public void callPhone(View view) {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            //TODO 权限申请处理
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.CALL_PHONE}, 1);
        } else {
            doCallPhone();
        }
    }

    private void doCallPhone() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + "10000"));
        startActivity(intent);
    }

    public void sdCardPermission(View view) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //TODO 权限申请处理
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
        } else {
            doSDCardPermission();
        }
    }

    private void doSDCardPermission() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request
                .Builder()
                .url("http://img.mmjpg.com/2015/350/3.jpg")
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody body = response.body();
                InputStream is = body.byteStream();
                int len = 0;
                byte[] bytes = new byte[2048];
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg";
                File file = new File(path);
                FileOutputStream fos = new FileOutputStream(file);
                while ((len = is.read(bytes)) != -1) {
                    fos.write(bytes, 0, len);
                    fos.flush();
                }

                Toast.makeText(PermissionActivity.this, "SDCard写入成功", Toast.LENGTH_SHORT).show();
                Log.e("Edwin", "SDCard写入成功");
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    doCallPhone();
                } else {
                    //TODO 提示用户权限未授予
                    Toast.makeText(PermissionActivity.this, "ACTION_CALL 权限未开启", Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    doSDCardPermission();
                } else {
                    //TODO 提示用户权限未授予
                    Toast.makeText(PermissionActivity.this, "WRITE_EXTERNAL_STORAGE 权限未开启", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
