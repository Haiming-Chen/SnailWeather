package com.example.chen.snailweather.util;

import com.example.chen.snailweather.R;

/**
 * 类名: GetImgId
 * 作者: 陈海明
 * 时间: 2017-08-02 16:06
 * 描述: NULL
 */
public class GetImgId {
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

}
