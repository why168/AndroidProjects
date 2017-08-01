package com.github.why168.kotlinlearning;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

/**
 * @author Edwin.Wu
 * @version 2017/7/31 17:41
 * @since JDK1.8
 */
public class UserSimple extends Activity {
    public void test1() {
        User user = User.create("天平", "广东", 21, "男", "敲代码", "没有个性签名", new Date());
        Log.e("@@", "onCreate: " + user.toString());
    }

    public void test2() {
        User user = User.create("天平", "广东", 21, "男", "敲代码", "没有个性签名", new Date());
        Intent bean = new Intent(this, null).putExtra("bean", user);
        startActivity(bean);

    }

    public void test3() {
        //json字符串
        String json = "{\"name\":\"天平\",\"addr\":\"广东\",\"age\":21,\"gender\":\"男\",\"hobby\":\"打代码\",\"sign\":\"签名\",\"date\":\"2017-3-13 14:36:19\"}";

        //初始化Gson
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(UserAdapterFactory.create()) //注册自定义的TypeAdapterFactory
                .setDateFormat("yyyy-MM-dd HH:mm:ss")   //设置json里面的Date格式
                .create();

        //开始解析
        User user = gson.fromJson(json, User.class);

        //输出结果
        Log.e("@@", "onCreate: " + user.toString());
    }

    private User updateSign(User user) {
        return User.create(user).sign("新签名").build();
    }
}
