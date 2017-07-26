package com.example.chen.snailweather.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by chen on 2017/7/26 0026.
 * 县城
 */
@Entity
public class CountyDB {
    @Id(autoincrement = true)
    private Long id;

    @NotNull
    @Unique
    private String countyName;
    private int countyCode;
    private int cityId;

    @ToMany(referencedJoinProperty = "c_id")
    private CountyDB mCountyDB;  // 源实体

    @Generated(hash = 1264248947)
    public CountyDB(Long id, @NotNull String countyName, int countyCode,
            int cityId) {
        this.id = id;
        this.countyName = countyName;
        this.countyCode = countyCode;
        this.cityId = cityId;
    }
    @Generated(hash = 2020626505)
    public CountyDB() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCountyName() {
        return this.countyName;
    }
    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }
    public int getCountyCode() {
        return this.countyCode;
    }
    public void setCountyCode(int countyCode) {
        this.countyCode = countyCode;
    }
    public int getCityId() {
        return this.cityId;
    }
    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
