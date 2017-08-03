package com.example.chen.snailweather.activity;

import android.app.Application;
import android.content.Context;

import com.example.chen.snailweather.utils.GreenDaoManager;

/**
 * @name SnailWeather
 * @class name：com.example.chen.snailweather.activity
 * @anthor 陈海明 QQ:3508417
 * @time 2017/7/26 0026 22:27
 * @class 描述
 */
public class mApplication extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        //greenDao全局配置,只希望有一个数据库操作对象
        GreenDaoManager.getInstance();
    }
    public static Context getContext() {
        return mContext;
    }
}
