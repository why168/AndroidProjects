package com.github.why168.interview.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.why168.interview.R;
import com.github.why168.interview.broadcast.MyReceiver3;

/**
 * MyReceiver1
 * MyReceiver2
 *
 * @author Edwin.Wu
 * @version 2017/5/9 23:25
 * @since JDK1.8
 */
public class ReceiverActivity extends AppCompatActivity {
    private MyReceiver3 mReceiver3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);
        mReceiver3 = new MyReceiver3();
    }

    /**
     * 有序广播发送
     * sendOrderedBroadcast()
     *
     * @param view
     */
    public void onSendOrderedBroadcast(View view) {
        Intent intent = new Intent();
        intent.setAction("com.github.why168.interview.broadcast");
        sendOrderedBroadcast(intent, null, null, null, 0, "数据过来了", null);
    }

    /**
     * 无序广播发送
     * sendBroadcast()
     *
     * @param view
     */
    public void unSendOrderedBroadcast(View view) {
        Intent intent = new Intent();
        intent.setAction("com.github.why168.interview.broadcast");
        sendBroadcast(intent, null);
    }

    /**
     * 动态注册
     *
     * @param view
     */
    public void onDynamicBroadcast(View view) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.github.why168.interview.broadcast");
        registerReceiver(mReceiver3, intentFilter);
    }

    /**
     * 动态解除
     *
     * @param view
     */
    public void unDynamicBroadcast(View view) {
        unregisterReceiver(mReceiver3);
    }
}
