package com.github.why168.kotlinlearning;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

/**
 * @author Edwin.Wu
 * @version 2017/7/31 17:53
 * @since JDK1.8
 */
@AutoValue
public abstract class Foo<A, B, C> {

    abstract A data();

    abstract List<B> dataList();

    abstract Map<String, List<C>> dataMap();

    public static <A, B, C> TypeAdapter<Foo<A, B, C>> typeAdapter(Gson gson, TypeToken<? extends Foo<A, B, C>> typeToken) {
        return new AutoValue_Foo.GsonTypeAdapter(gson, typeToken);
    }
}