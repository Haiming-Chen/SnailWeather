package com.example.chen.snailweather.bean;

import java.util.List;

/**
 * Created by chen on 2017/7/27 0027.
 */

public class NoticeBean {

    private List<HeWeather5Bean> HeWeather5;

    public List<HeWeather5Bean> getHeWeather5() {
        return HeWeather5;
    }

    public void setHeWeather5(List<HeWeather5Bean> HeWeather5) {
        this.HeWeather5 = HeWeather5;
    }

    public static class HeWeather5Bean {
        /**
         * basic : {"city":"长沙","cnty":"中国","id":"CN101250101","lat":"28.19408989","lon":"112.98227692","update":{"loc":"2017-07-27 20:49","utc":"2017-07-27 12:49"}}
         * daily_forecast : [{"astro":{"mr":"09:34","ms":"22:15","sr":"05:47","ss":"19:21"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2017-07-27","hum":"46","pcpn":"0.0","pop":"0","pres":"1001","tmp":{"max":"40","min":"29"},"uv":"11","vis":"16","wind":{"deg":"144","dir":"东南风","sc":"微风","spd":"4"}},{"astro":{"mr":"10:30","ms":"22:51","sr":"05:48","ss":"19:21"},"cond":{"code_d":"100","code_n":"101","txt_d":"晴","txt_n":"多云"},"date":"2017-07-28","hum":"47","pcpn":"0.0","pop":"84","pres":"1003","tmp":{"max":"39","min":"29"},"uv":"9","vis":"20","wind":{"deg":"79","dir":"东北风","sc":"微风","spd":"4"}},{"astro":{"mr":"11:26","ms":"23:27","sr":"05:48","ss":"19:20"},"cond":{"code_d":"300","code_n":"101","txt_d":"阵雨","txt_n":"多云"},"date":"2017-07-29","hum":"54","pcpn":"3.8","pop":"97","pres":"1001","tmp":{"max":"37","min":"29"},"uv":"10","vis":"18","wind":{"deg":"359","dir":"北风","sc":"微风","spd":"8"}},{"astro":{"mr":"12:19","ms":"12:45","sr":"05:49","ss":"19:19"},"cond":{"code_d":"300","code_n":"101","txt_d":"阵雨","txt_n":"多云"},"date":"2017-07-30","hum":"64","pcpn":"2.8","pop":"69","pres":"999","tmp":{"max":"36","min":"30"},"uv":"10","vis":"19","wind":{"deg":"9","dir":"北风","sc":"微风","spd":"8"}},{"astro":{"mr":"13:12","ms":"00:02","sr":"05:49","ss":"19:19"},"cond":{"code_d":"300","code_n":"101","txt_d":"阵雨","txt_n":"多云"},"date":"2017-07-31","hum":"59","pcpn":"0.6","pop":"34","pres":"997","tmp":{"max":"35","min":"29"},"uv":"11","vis":"18","wind":{"deg":"314","dir":"西北风","sc":"微风","spd":"7"}},{"astro":{"mr":"14:04","ms":"00:39","sr":"05:50","ss":"19:18"},"cond":{"code_d":"300","code_n":"101","txt_d":"阵雨","txt_n":"多云"},"date":"2017-08-01","hum":"60","pcpn":"0.2","pop":"60","pres":"997","tmp":{"max":"33","min":"29"},"uv":"7","vis":"16","wind":{"deg":"325","dir":"西北风","sc":"微风","spd":"6"}},{"astro":{"mr":"14:56","ms":"01:18","sr":"05:50","ss":"19:17"},"cond":{"code_d":"300","code_n":"101","txt_d":"阵雨","txt_n":"多云"},"date":"2017-08-02","hum":"54","pcpn":"0.0","pop":"77","pres":"997","tmp":{"max":"35","min":"29"},"uv":"8","vis":"16","wind":{"deg":"320","dir":"西北风","sc":"微风","spd":"6"}}]
         * status : ok
         */

        private BasicBean basic;
        private String status;
        private List<DailyForecastBean> daily_forecast;

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

        public List<DailyForecastBean> getDaily_forecast() {
            return daily_forecast;
        }

        public void setDaily_forecast(List<DailyForecastBean> daily_forecast) {
            this.daily_forecast = daily_forecast;
        }

        public static class BasicBean {
            /**
             * city : 长沙
             * cnty : 中国
             * id : CN101250101
             * lat : 28.19408989
             * lon : 112.98227692
             * update : {"loc":"2017-07-27 20:49","utc":"2017-07-27 12:49"}
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
                 * loc : 2017-07-27 20:49
                 * utc : 2017-07-27 12:49
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

        public static class DailyForecastBean {
            /**
             * astro : {"mr":"09:34","ms":"22:15","sr":"05:47","ss":"19:21"}
             * cond : {"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"}
             * date : 2017-07-27
             * hum : 46
             * pcpn : 0.0
             * pop : 0
             * pres : 1001
             * tmp : {"max":"40","min":"29"}
             * uv : 11
             * vis : 16
             * wind : {"deg":"144","dir":"东南风","sc":"微风","spd":"4"}
             */

            private AstroBean astro;
            private CondBean cond;
            private String date;
            private String hum;
            private String pcpn;
            private String pop;
            private String pres;
            private TmpBean tmp;
            private String uv;
            private String vis;
            private WindBean wind;

            public AstroBean getAstro() {
                return astro;
            }

            public void setAstro(AstroBean astro) {
                this.astro = astro;
            }

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

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
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

            public TmpBean getTmp() {
                return tmp;
            }

            public void setTmp(TmpBean tmp) {
                this.tmp = tmp;
            }

            public String getUv() {
                return uv;
            }

            public void setUv(String uv) {
                this.uv = uv;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public WindBean getWind() {
                return wind;
            }

            public void setWind(WindBean wind) {
                this.wind = wind;
            }

            public static class AstroBean {
                /**
                 * mr : 09:34
                 * ms : 22:15
                 * sr : 05:47
                 * ss : 19:21
                 */

                private String mr;
                private String ms;
                private String sr;
                private String ss;

                public String getMr() {
                    return mr;
                }

                public void setMr(String mr) {
                    this.mr = mr;
                }

                public String getMs() {
                    return ms;
                }

                public void setMs(String ms) {
                    this.ms = ms;
                }

                public String getSr() {
                    return sr;
                }

                public void setSr(String sr) {
                    this.sr = sr;
                }

                public String getSs() {
                    return ss;
                }

                public void setSs(String ss) {
                    this.ss = ss;
                }
            }

            public static class CondBean {
                /**
                 * code_d : 100
                 * code_n : 100
                 * txt_d : 晴
                 * txt_n : 晴
                 */

                private String code_d;
                private String code_n;
                private String txt_d;
                private String txt_n;

                public String getCode_d() {
                    return code_d;
                }

                public void setCode_d(String code_d) {
                    this.code_d = code_d;
                }

                public String getCode_n() {
                    return code_n;
                }

                public void setCode_n(String code_n) {
                    this.code_n = code_n;
                }

                public String getTxt_d() {
                    return txt_d;
                }

                public void setTxt_d(String txt_d) {
                    this.txt_d = txt_d;
                }

                public String getTxt_n() {
                    return txt_n;
                }

                public void setTxt_n(String txt_n) {
                    this.txt_n = txt_n;
                }
            }

            public static class TmpBean {
                /**
                 * max : 40
                 * min : 29
                 */

                private String max;
                private String min;

                public String getMax() {
                    return max;
                }

                public void setMax(String max) {
                    this.max = max;
                }

                public String getMin() {
                    return min;
                }

                public void setMin(String min) {
                    this.min = min;
                }
            }

            public static class WindBean {
                /**
                 * deg : 144
                 * dir : 东南风
                 * sc : 微风
                 * spd : 4
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
