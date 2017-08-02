package com.example.chen.snailweather.Retrofit;

import com.example.chen.snailweather.bean.ForecastBean;
import com.example.chen.snailweather.bean.CurrentBean;
import com.example.chen.snailweather.bean.HistoryBean;
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

    @GET("v5/now?")
    Observable<Response<CurrentBean>>getNow(@Query("city") String city, @Query("key") String key);
    //LookUp?key=b31bf74eb7d14f968ce15bc64ad792a6&yue=1&ri=1&type=1&page=1&rows=5
    @GET("LookUp?")
    Observable<Response<HistoryBean>>getHistory(@Query("key") String key, @Query("yue") int yue,
                                                @Query("ri") int ri, @Query("type") int type,
                                                @Query("page") int page, @Query("rows") int rows);
}
