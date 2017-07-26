package com.example.chen.snailweather.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by chen on 2017/7/26 0026.
 * 省份
 */
   @Entity
public class ProvincesDB {
    @Id(autoincrement = true)
    private Long id;

    @NotNull
    @Unique
    private String provincesName;
    private int provincesCode;
   @Generated(hash = 917886715)
   public ProvincesDB(Long id, @NotNull String provincesName, int provincesCode) {
       this.id = id;
       this.provincesName = provincesName;
       this.provincesCode = provincesCode;
   }
   @Generated(hash = 1306431896)
   public ProvincesDB() {
   }
   public Long getId() {
       return this.id;
   }
   public void setId(Long id) {
       this.id = id;
   }
   public String getProvincesName() {
       return this.provincesName;
   }
   public void setProvincesName(String provincesName) {
       this.provincesName = provincesName;
   }
   public int getProvincesCode() {
       return this.provincesCode;
   }
   public void setProvincesCode(int provincesCode) {
       this.provincesCode = provincesCode;
   }
}
