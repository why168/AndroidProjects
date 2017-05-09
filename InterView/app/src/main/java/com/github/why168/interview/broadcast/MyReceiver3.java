package com.github.why168.interview.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 动态注册
 *
 * @author Edwin.Wu
 * @version 2017/5/10 00:45
 * @since JDK1.8
 */
public class MyReceiver3 extends BroadcastReceiver {

    public MyReceiver3() {
        System.out.println("MyReceiver3 动态注册成功");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("MyReceiver3 收到消息...");
    }
}
