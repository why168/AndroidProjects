package com.github.why168.multichannelbuild;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 多渠道打包
 * <p>
 * 命令：
 * 1.gradle assembleRelease 打包
 * 2.jarsigner -verify -verbose -certs xx.apk 检测apk的签名信息
 *
 * @author Edwin.Wu
 * @version 2017/1/17 18:31
 * @since JDK1.8
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
