package com.github.why168.interview.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.github.why168.interview.constant.Constants;

public class MyReceiver2 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle resultExtras = getResultExtras(true);
        String broadcast_data = resultExtras.getString(Constants.BROADCAST_DATA);
        System.out.println("MyReceiver2 接受到消息, 收到: " + broadcast_data);
    }
}
