package com.example.chen.snailweather.bean;

import java.util.List;

/**
 * 类名: HistoryBean
 * 作者: 陈海明
 * 时间: 2017-08-02 10:40
 * 描述: NULL
 */
public class HistoryBean {

    /**
     * total : 27
     * result : [{"year":2002,"month":1,"day":1,"title":"欧元正式进入流通","type":1},{"year":1999,"month":1,"day":1,"title":"欧元诞生","type":1},{"year":1998,"month":1,"day":1,"title":"我国与南非建立外交关系","type":1},{"year":1995,"month":1,"day":1,"title":"世界贸易组织成立","type":1},{"year":1994,"month":1,"day":1,"title":"欧洲经济区成立","type":1}]
     * error_code : 0
     * reason : Succes
     */

    private int total;
    private int error_code;
    private String reason;
    private List<ResultBean> result;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * year : 2002
         * month : 1
         * day : 1
         * title : 欧元正式进入流通
         * type : 1
         */

        private int year;
        private int month;
        private int day;
        private String title;
        private int type;

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
