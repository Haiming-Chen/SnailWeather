package com.example.chen.snailweather.utils;

import com.example.chen.snailweather.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 类名: GetImgIdUtils
 * 作者: 陈海明
 * 时间: 2017-08-02 16:06
 * 描述: NULL
 */
public class GetImgIdUtils {
    public static int getimgid(String code) {
        int imgid = R.drawable.code_999;
        switch (code) {
            case "100":
                imgid=R.drawable.code_100;
                break;
            case "101":
                imgid=R.drawable.code_101;
                break;
            case "102":
                imgid=R.drawable.code_102;
                break;
            case "103":
                imgid=R.drawable.code_103;
                break;
            case "104":
                imgid=R.drawable.code_104;
                break;
            case "300":
                imgid=R.drawable.code_300;
                break;
            case "301":
                imgid=R.drawable.code_301;
                break;
            case "302":
                imgid=R.drawable.code_302;
                break;
            case "303":
                imgid=R.drawable.code_303;
                break;
            case "304":
                imgid=R.drawable.code_304;
                break;
            case "305":
                imgid=R.drawable.code_305;
                break;
            case "306":
                imgid=R.drawable.code_306;
                break;
            case "307":
                imgid=R.drawable.code_307;
                break;
            case "310":
                imgid=R.drawable.code_310;
                break;
            case "311":
                imgid=R.drawable.code_311;
                break;
            case "312":
                imgid=R.drawable.code_312;
                break;
            case "400":
                imgid=R.drawable.code_400;
                break;
            case "401":
                imgid=R.drawable.code_401;
                break;
            case "402":
                imgid=R.drawable.code_402;
                break;
            case "403":
                imgid=R.drawable.code_403;
                break;
            case "404":
                imgid=R.drawable.code_404;
                break;
            case "405":
                imgid=R.drawable.code_405;
                break;
            case "406":
                imgid=R.drawable.code_406;
                break;
            case "407":
                imgid=R.drawable.code_407;
                break;
        }
        return imgid;
    }

/*   晴 100 201
    雨300 301 302 303 304 305 306 307 308 309 310 311 312 313  14
    风200 202  203 204 205 206 207 208 209  210 211  212 213  13
    雪400 401 402 403 404 405 406 407          8
    云101 102 103
    霾 502 503 504 507 508   5
    其它104  500 501
    900 901 999*/
    public static int getbgImgID(String code){
            if (code.equals("100")||code.equals("201")){//晴
               if (getCurrentTime()){
                   return R.mipmap.weatherbg_sunshine_night;
               }else{
                   return R.mipmap.weatherbg_sunshine;
               }
            }else if (code.equals("300")||code.equals("301")||code.equals("300")||code.equals("301")||//雨
            code.equals("303")||code.equals("304")||code.equals("305")||code.equals("306")||
            code.equals("307")||code.equals("308")||code.equals("309")||code.equals("310")||
                    code.equals("312")||code.equals("313")){
                if (getCurrentTime()) {
                    return R.mipmap.weatherbg_rain_night;
                }else{
                    return R.mipmap.weatherbg_rain;
                }
            }else if (code.equals("200")||code.equals("202")||code.equals("203")||code.equals("204")//风
                    ||code.equals("205")||code.equals("206")||code.equals("207")||code.equals("208")||code.equals("209")||
                    code.equals("210")||code.equals("211")||code.equals("212")||code.equals("213")){
                if (getCurrentTime()) {
                    return R.mipmap.weatherbg_wind_night;
                }else{
                    return R.mipmap.weatherbg_wind;
                }
            }else if( code.equals("400")||code.equals("401")||code.equals("403")||code.equals("404")||//雪
                    code.equals("405")||code.equals("406")||code.equals("407")||code.equals("408")){
                if (getCurrentTime()) {
                    return R.mipmap.weatherbg_snow_night;
                }else{
                    return R.mipmap.weatherbg_snow;
                }
            }else if (code.equals("101")||code.equals("102")||code.equals("103")){//云
                if (getCurrentTime()) {
                    return  R.mipmap.weatherbg_pcloud_night;
                }else{
                    return  R.mipmap.weatherbg_pcloud;
                }
            }else if (code.equals("502")||code.equals("503")||code.equals("504")||code.equals("507")||//霾、沙尘
                    code.equals("508")){
                    if (getCurrentTime()) {
                        return  R.mipmap.weatherbg_haze_night;
                }else{
                        return  R.mipmap.weatherbg_haze;
                    }
            }else{
                    if (getCurrentTime()) {
                        return R.mipmap.weatherbg_cloud_night;
                }else{
                        return R.mipmap.weatherbg_cloud;
                    }

            }
    }
    //判断白天还是晚上
    public static boolean getCurrentTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        String hour= sdf.format(new Date());
        int k  = Integer.parseInt(hour)  ;
        if ((k>=0 && k<6) ||(k >=18 && k<24)){
            return true;
        } else {
            return false;
        }
    }

    public static int getAqiImgid(String str){
        int imgid=0;
        switch (str){
            case"优":
                imgid= R.mipmap.notification_pm_excellent_color;
               break;
            case"良":
                imgid= R.mipmap.notification_pm_good_color;
                break;
            case"轻度污染":
                imgid=  R.mipmap.notification_pm_light_color;
                break;
            case"中度污染":
                imgid=  R.mipmap.notification_pm_middle_color;
                break;
            case"重度污染":
                imgid=  R.mipmap.notification_pm_serve_color;
                break;
            case"严重污染":
                imgid=  R.mipmap.notification_pm_serve_color;
                break;
        }
       return imgid;
    }

}
