package com.github.why168.kotlinlearning;

import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;

/**
 * @author Edwin.Wu
 * @version 2017/7/31 17:30
 * @since JDK1.8
 */
@GsonTypeAdapterFactory
public abstract class UserAdapterFactory implements TypeAdapterFactory {

    // 静态工厂方式
    public static TypeAdapterFactory create() {
        return new AutoValueGson_UserAdapterFactory();
    }

}