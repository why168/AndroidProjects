package com.github.why168.permissionmanage;

import android.Manifest;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.tbruyelle.rxpermissions.Permission;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.IOException;

import rx.functions.Action0;
import rx.functions.Action1;

/**
 * RxPermissionGen框架
 *
 * @author Edwin.Wu
 * @version 2017/3/19 14:17
 * @since JDK1.8
 */
public class RxPermissionActivity extends AppCompatActivity {
    private static final String TAG = "RxPermissionsSample";
    private Camera camera;
    private SurfaceView surfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.setLogging(true);

        setContentView(R.layout.activity_rx_permission);

        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);

        RxView.clicks(findViewById(R.id.enableCamera))
                // Ask for permissions when button is clicked
                .compose(rxPermissions.ensureEach(Manifest.permission.READ_EXTERNAL_STORAGE))
                .subscribe(new Action1<Permission>() {
                               @Override
                               public void call(Permission permission) {
                                   Log.i(TAG, "Permission result " + permission);
                                   if (permission.granted) {
                                       releaseCamera();
                                       camera = Camera.open(0);
                                       try {
                                           camera.setPreviewDisplay(surfaceView.getHolder());
                                           camera.startPreview();
                                       } catch (IOException e) {
                                           Log.e(TAG, "Error while trying to display the camera preview", e);
                                       }
                                   } else if (permission.shouldShowRequestPermissionRationale) {
                                       // Denied permission without ask never again
                                       Toast.makeText(RxPermissionActivity.this, "Denied permission without ask never again", Toast.LENGTH_SHORT).show();
                                   } else {
                                       // Denied perm`ission with ask never again
                                       // Need to go to the settings
                                       Toast.makeText(RxPermissionActivity.this, "Permission denied, can't enable the camera", Toast.LENGTH_SHORT).show();
                                   }
                               }
                           },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable t) {
                                Log.e(TAG, "onError", t);
                            }
                        },
                        new Action0() {
                            @Override
                            public void call() {
                                Log.i(TAG, "OnComplete");
                            }
                        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseCamera();
    }

    private void releaseCamera() {
        if (camera != null) {
            camera.release();
            camera = null;
        }
    }
}
