package com.github.why168.interview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.github.why168.interview.R;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";
    private boolean mIsExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("Edwin", TAG + " —> onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStartLife(View view) {
        Intent intent = new Intent(this, LifeActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
        startActivity(intent);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.e("Edwin", TAG + " —> onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onStart() {
        Log.e("Edwin", TAG + " —> onStart");
        super.onStart();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        Log.e("Edwin", TAG + " —> onPostCreate");
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        Log.e("Edwin", TAG + " —> onResume");
        super.onResume();
    }

    @Override
    protected void onPostResume() {
        Log.e("Edwin", TAG + " —> onPostResume");
        super.onPostResume();
    }

    @Override
    protected void onPause() {
        Log.e("Edwin", TAG + " —> onPause");
        super.onPause();
    }

    @Override
    protected void onRestart() {
        Log.e("Edwin", TAG + " —> onRestart");
        super.onRestart();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.e("Edwin", TAG + " —> onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        Log.e("Edwin", TAG + " —> onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.e("Edwin", TAG + " —> onDestroy");
        super.onDestroy();
    }

    /**
     * 双击返回键退出
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mIsExit) {
                this.finish();
            } else {
                Toast.makeText(this, "2秒内,再按一次退出", Toast.LENGTH_SHORT).show();
                mIsExit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mIsExit = false;
                    }
                }, 2000);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}