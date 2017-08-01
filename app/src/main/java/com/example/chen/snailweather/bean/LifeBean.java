package com.example.chen.snailweather.bean;

/**
 * 类名: LifeBean
 * 作者: 陈海明
 * 时间: 2017-08-01 09:26
 * 描述: NULL
 */
public class LifeBean {
    int img_id;
    String  less;
    String  detailed;

    public LifeBean(int img_id, String less, String detailed) {
        this.img_id = img_id;
        this.less = less;
        this.detailed = detailed;
    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }

    public String getLess() {
        return less;
    }

    public void setLess(String less) {
        this.less = less;
    }

    public String getDetailed() {
        return detailed;
    }

    public void setDetailed(String detailed) {
        this.detailed = detailed;
    }
}
