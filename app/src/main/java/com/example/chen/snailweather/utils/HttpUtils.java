package com.example.chen.snailweather.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by chen on 2017/7/26 0026.
 */

public class HttpUtils {
    public static void setOkHttpRequest(String address,okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
