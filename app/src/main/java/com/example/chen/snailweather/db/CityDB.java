package com.example.chen.snailweather.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by chen on 2017/7/26 0026.
 * 城市
 */
@Entity
public class CityDB {
    @Id(autoincrement = true)
    private Long id;

    @NotNull
    private String cityName;
    private int cityCode;
    private int provincesId;
    @Generated(hash = 2015038932)
    public CityDB(Long id, @NotNull String cityName, int cityCode,
            int provincesId) {
        this.id = id;
        this.cityName = cityName;
        this.cityCode = cityCode;
        this.provincesId = provincesId;
    }
    @Generated(hash = 1937452283)
    public CityDB() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCityName() {
        return this.cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public int getCityCode() {
        return this.cityCode;
    }
    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }
    public int getProvincesId() {
        return this.provincesId;
    }
    public void setProvincesId(int provincesId) {
        this.provincesId = provincesId;
    }
}
