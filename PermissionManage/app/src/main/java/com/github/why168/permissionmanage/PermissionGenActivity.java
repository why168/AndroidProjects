package com.github.why168.permissionmanage;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

/**
 * PermissionGen框架
 *
 * @author Edwin.Wu
 * @version 2017/3/17 23:19
 * @since JDK1.8
 */
public class PermissionGenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_gen);
    }

    public void openCamera(View view) {
        PermissionGen.needPermission(this, 200, Manifest.permission.CAMERA);
    }

    public void openGroup(View view) {
        PermissionGen
                .with(this)
                .addRequestCode(100)
                .permissions(
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.RECEIVE_SMS,
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_CONTACTS)
                .request();
    }


    @PermissionSuccess(requestCode = 100)
    public void openGroupSuccess() {
        Toast.makeText(this, "Group Permission Success", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, ContactActivity.class));
    }

    @PermissionFail(requestCode = 100)
    private void openGroupFail() {
        Toast.makeText(this, "Group permission is not granted", Toast.LENGTH_SHORT).show();
    }

    @PermissionSuccess(requestCode = 200)
    public void openCameraSuccess() {
        Toast.makeText(this, "Camera permission Success", Toast.LENGTH_SHORT).show();
    }

    @PermissionFail(requestCode = 200)
    public void openCameraFail() {
        Toast.makeText(this, "Camera permission is not granted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }
}
