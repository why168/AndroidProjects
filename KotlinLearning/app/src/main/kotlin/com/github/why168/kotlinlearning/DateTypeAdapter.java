package com.github.why168.kotlinlearning;

import android.os.Parcel;

import com.ryanharter.auto.value.parcel.TypeAdapter;

import java.util.Date;

/**
 * @author Edwin.Wu
 * @version 2017/7/31 17:43
 * @since JDK1.8
 */
public class DateTypeAdapter implements TypeAdapter<Date> {
    public Date fromParcel(Parcel in) {
        return new Date(in.readLong());
    }

    public void toParcel(Date value, Parcel dest) {
        dest.writeLong(value.getTime());
    }
}