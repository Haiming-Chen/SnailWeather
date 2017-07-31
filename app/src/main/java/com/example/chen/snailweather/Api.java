package com.example.chen.snailweather;

import com.example.chen.snailweather.bean.ForecastBean;
import com.example.chen.snailweather.bean.WeatherBean;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 类名: Api
 * 作者: 陈海明
 * 时间: 2017-07-31 16:02
 * 描述: NULL
 */
public interface Api {
    @GET("v5/weather?")
    Observable<Response<WeatherBean>> getWeather(@Query("city") String city, @Query("key") String key);

    @GET("v5/forecast?")
    Observable<Response<ForecastBean>> getForecast(@Query("city") String city, @Query("key") String key);
}
