package com.github.why168.interview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.why168.interview.R;

/**
 * MyReceiver1
 * MyReceiver2
 *
 * @author Edwin.Wu
 * @version 2017/5/9 23:25
 * @since JDK1.8
 */
public class ReceiverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);
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
}
