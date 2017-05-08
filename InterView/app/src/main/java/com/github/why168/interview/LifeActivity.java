package com.github.why168.interview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * 生命周期
 *
 * @author Edwin.Wu
 * @version 2017/5/7 01:02
 * @since JDK1.8
 */
public class LifeActivity extends AppCompatActivity {
    private static String TAG = "LifeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("Edwin", TAG + " —> onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life);
    }

    public void onStartTask(View view) {
        Intent intent = new Intent(this, TaskActivity.class);
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
}