package com.github.why168.permissionmanage;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * BaseActivity
 *
 * @author Edwin.Wu
 * @version 2017/3/17 11:59
 * @since JDK1.8
 */
public class PermissionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
    }

    public void callPhone(View view) {
        if (hasPermission(android.Manifest.permission.CALL_PHONE)) {
            doCallPhone();
        } else {
            requestPermission(PermissionsConstans.CALL_PHONE_CODE, android.Manifest.permission.CALL_PHONE);
        }
    }

    public void sdCardPermission(View view) {
        if (hasPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            doSDCardPermission();
        } else {
            requestPermission(PermissionsConstans.WRITE_STORAGE_CODE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
    }


    @Override
    protected void doCallPhone() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + "10000"));
        startActivity(intent);
    }

    @Override
    protected void doSDCardPermission() {
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

}
