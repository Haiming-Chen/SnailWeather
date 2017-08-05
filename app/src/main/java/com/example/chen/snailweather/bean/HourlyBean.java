package com.example.chen.snailweather.bean;

import java.util.List;

/**
 * 类名: HourlyBean
 * 作者: 陈海明
 * 时间: 2017-08-02 10:40
 * 描述: NULL
 */
public class HourlyBean {


    private List<HeWeather5Bean> HeWeather5;

    public List<HeWeather5Bean> getHeWeather5() {
        return HeWeather5;
    }

    public void setHeWeather5(List<HeWeather5Bean> HeWeather5) {
        this.HeWeather5 = HeWeather5;
    }

    public static class HeWeather5Bean {
        /**
         * basic : {"city":"深圳","cnty":"中国","id":"CN101280601","lat":"22.54700089","lon":"114.08594513","update":{"loc":"2017-08-05 08:50","utc":"2017-08-05 00:50"}}
         * hourly_forecast : [{"cond":{"code":"103","txt":"晴间多云"},"date":"2017-08-05 10:00","hum":"74","pop":"20","pres":"1005","tmp":"29","wind":{"deg":"177","dir":"南风","sc":"微风","spd":"9"}},{"cond":{"code":"100","txt":"晴"},"date":"2017-08-05 13:00","hum":"71","pop":"7","pres":"1003","tmp":"31","wind":{"deg":"181","dir":"南风","sc":"微风","spd":"13"}},{"cond":{"code":"100","txt":"晴"},"date":"2017-08-05 16:00","hum":"74","pop":"7","pres":"1001","tmp":"31","wind":{"deg":"189","dir":"南风","sc":"微风","spd":"12"}},{"cond":{"code":"100","txt":"晴"},"date":"2017-08-05 19:00","hum":"77","pop":"6","pres":"1002","tmp":"30","wind":{"deg":"188","dir":"南风","sc":"微风","spd":"12"}},{"cond":{"code":"100","txt":"晴"},"date":"2017-08-05 22:00","hum":"87","pop":"6","pres":"1004","tmp":"28","wind":{"deg":"202","dir":"西南风","sc":"微风","spd":"12"}}]
         * status : ok
         */

        private BasicBean basic;
        private String status;
        private List<HourlyForecastBean> hourly_forecast;

        public BasicBean getBasic() {
            return basic;
        }

        public void setBasic(BasicBean basic) {
            this.basic = basic;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<HourlyForecastBean> getHourly_forecast() {
            return hourly_forecast;
        }

        public void setHourly_forecast(List<HourlyForecastBean> hourly_forecast) {
            this.hourly_forecast = hourly_forecast;
        }

        public static class BasicBean {
            /**
             * city : 深圳
             * cnty : 中国
             * id : CN101280601
             * lat : 22.54700089
             * lon : 114.08594513
             * update : {"loc":"2017-08-05 08:50","utc":"2017-08-05 00:50"}
             */

            private String city;
            private String cnty;
            private String id;
            private String lat;
            private String lon;
            private UpdateBean update;

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCnty() {
                return cnty;
            }

            public void setCnty(String cnty) {
                this.cnty = cnty;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLon() {
                return lon;
            }

            public void setLon(String lon) {
                this.lon = lon;
            }

            public UpdateBean getUpdate() {
                return update;
            }

            public void setUpdate(UpdateBean update) {
                this.update = update;
            }

            public static class UpdateBean {
                /**
                 * loc : 2017-08-05 08:50
                 * utc : 2017-08-05 00:50
                 */

                private String loc;
                private String utc;

                public String getLoc() {
                    return loc;
                }

                public void setLoc(String loc) {
                    this.loc = loc;
                }

                public String getUtc() {
                    return utc;
                }

                public void setUtc(String utc) {
                    this.utc = utc;
                }
            }
        }

        public static class HourlyForecastBean {
            /**
             * cond : {"code":"103","txt":"晴间多云"}
             * date : 2017-08-05 10:00
             * hum : 74
             * pop : 20
             * pres : 1005
             * tmp : 29
             * wind : {"deg":"177","dir":"南风","sc":"微风","spd":"9"}
             */

            private CondBean cond;
            private String date;
            private String hum;
            private String pop;
            private String pres;
            private String tmp;
            private WindBean wind;

            public CondBean getCond() {
                return cond;
            }

            public void setCond(CondBean cond) {
                this.cond = cond;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getTmp() {
                return tmp;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public WindBean getWind() {
                return wind;
            }

            public void setWind(WindBean wind) {
                this.wind = wind;
            }

            public static class CondBean {
                /**
                 * code : 103
                 * txt : 晴间多云
                 */

                private String code;
                private String txt;

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class WindBean {
                /**
                 * deg : 177
                 * dir : 南风
                 * sc : 微风
                 * spd : 9
                 */

                private String deg;
                private String dir;
                private String sc;
                private String spd;

                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }
        }
    }
}
