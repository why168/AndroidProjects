package com.github.why168.interview.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.github.why168.interview.constant.Constants;

public class MyReceiver1 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String broadcast_data = getResultData();

        //通过Bundle传递参数
        Bundle bundle = new Bundle();
        bundle.putString(Constants.BROADCAST_DATA, broadcast_data + " 数据被我修改了");
        setResultExtras(bundle);
        System.out.println("MyReceiver1 接受到消息, 收到: " + broadcast_data);

        abortBroadcast(); //中断广播，不会再响比它有优先级低得广播再传播下去了
    }
}
