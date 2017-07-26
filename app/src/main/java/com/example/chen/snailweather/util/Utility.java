package com.example.chen.snailweather.util;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.example.chen.snailweather.db.ProvincesDB;

import org.json.JSONArray;
import org.json.JSONException;


/**
 * @name SnailWeather
 * @class name：com.example.chen.snailweather.util
 * @anthor 陈海明 QQ:3508417
 * @time 2017/7/26 0026 21:58
 * @class 描述
 */
public class Utility {
    public static boolean handleProvincesRequest(String request){
        if (!TextUtils.isEmpty(request)){

            ProvincesDB provinces = JSON.parseObject(request, ProvincesDB.class);


        }
        return false;
    }
}
