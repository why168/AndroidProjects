package com.github.why168.kotlinlearning;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.ryanharter.auto.value.parcel.ParcelAdapter;

import java.util.Date;

/**
 * @author Edwin.Wu
 * @version 2017/7/31 17:19
 * @since JDK1.8
 */
@AutoValue
public abstract class User implements Parcelable {
    public abstract String name();

    public abstract String address();

    public abstract int age();

    public abstract String gender();

    public abstract String hobby();

    public abstract String sign();

    //需要注解自定义的TypeAdapter
    @ParcelAdapter(com.github.why168.kotlinlearning.DateTypeAdapter.class)
    public abstract Date date();

    //创建User，内部调用的是AutoValue_User
    public static User create(String name, String address, int age, String gender, String hobby, String sign, Date date) {
        return new AutoValue_User(name, address, age, gender, hobby, sign, date);
    }

    //添加一个TypeAdapter<User>，这个TypeAdapter是Gson包里面的。
    public static TypeAdapter<User> typeAdapter(Gson gson) {
        // AutoValue_User.GsonTypeAdapter 需要先make一下module之后才会生成
        return new AutoValue_User
                .GsonTypeAdapter(gson);

    }

    //setter的时候传递当前的user过来，这里重新builder，再设置
    public static Builder create(User user) {
        return builder()
                .name(user.name())
                .address(user.address())
                .age(user.age())
                .gender(user.gender())
                .hobby(user.hobby())
                .date(user.date())
                .sign(user.sign());
    }


    public static Builder builder() {
        return new AutoValue_User.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder name(String name);

        public abstract Builder address(String address);

        public abstract Builder age(int age);

        public abstract Builder gender(String gender);

        public abstract Builder hobby(String hobby);

        public abstract Builder sign(String sign);

        public abstract Builder date(Date date);

        public abstract User build();
    }

}