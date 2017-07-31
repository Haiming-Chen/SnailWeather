package com.example.chen.snailweather.util;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 类名: RetrofitProvider
 * 作者: 陈海明
 * 时间: 2017-07-28 16:52
 * 描述: NULL
 */
public class RetrofitProvider {
    public static Retrofit get() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        return new Retrofit.Builder().baseUrl(Url.ENDPOINT)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}

