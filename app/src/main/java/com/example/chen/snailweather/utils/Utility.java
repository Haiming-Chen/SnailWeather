package com.example.chen.snailweather.utils;

/**
 * @name SnailWeather
 * @class name：com.example.chen.snailweather.util
 * @anthor 陈海明 QQ:3508417
 * @time 2017/7/26 0026 21:58
 * @class 描述
 */
public class Utility {
  /*  public static boolean handleProvincesRequest(String request){
        if (!TextUtils.isEmpty(request)){
            ProvincesDB provinces = JSON.parseObject(request, ProvincesDB.class);
            ProvincesDB mProvincesDB = new ProvincesDB(null,provinces.getProvincesName(),provinces.getProvincesCode());
            ProvincesDBDao mProvincesDBDao =GreenDaoManager.getmDaoSession().getProvincesDBDao();
            mProvincesDBDao.insert(mProvincesDB);
        }
        return false;
    }
    public static boolean handleCityRequest(String request){
        if (!TextUtils.isEmpty(request)){
            CityDB city = JSON.parseObject(request, CityDB.class);
            CityDB mCityDB = new CityDB(null,city.getCityName(),city.getCityCode(),city.getProvincesId());
            CityDBDao mCityDBDao =GreenDaoManager.getmDaoSession().getCityDBDao();
            mCityDBDao.insert(mCityDB);
        }
        return false;
    }

    public static boolean handleCountyRequest(String request){
        if (!TextUtils.isEmpty(request)){
            CountyDB county = JSON.parseObject(request, CountyDB.class);
            CountyDB mCountyDB = new CountyDB(null,county.getCountyName(),county.getCountyCode(),county.getCityId());
            CountyDBDao mCountyDBDao =GreenDaoManager.getmDaoSession().getCountyDBDao();
            mCountyDBDao.insert(mCountyDB);
        }
        return false;
    }*/
}
