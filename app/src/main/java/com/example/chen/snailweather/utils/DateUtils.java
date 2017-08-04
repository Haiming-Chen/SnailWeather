package com.example.chen.snailweather.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 描述:日期工具类
 * 创建者:陈海明
 * 来源:复制来的...
 */

public class DateUtils {

    /**
     * 判断当前日期是星期几
     *
     * @param date 如"2016-08-30"
     * @return
     */
    public static String StringData(String date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String mWeek = "";
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            mWeek = "天";
        } else if (c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
            mWeek = "一";
        } else if (c.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
            mWeek = "二";
        } else if (c.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
            mWeek = "三";
        } else if (c.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
            mWeek = "四";
        } else if (c.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
            mWeek = "五";
        } else if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            mWeek = "六";
        }
        return "星期" + mWeek;
    }

    public static final String TODAY = "今天";
    public static final String YESTERDAY = "昨天";
    public static final String TOMORROW = "明天";
    public static final String BEFORE_YESTERDAY = "前天";
    public static final String AFTER_TOMORROW = "后天";
    public static final String SUNDAY = "星期日";
    public static final String MONDAY = "星期一";
    public static final String TUESDAY = "星期二";
    public static final String WEDNESDAY = "星期三";
    public static final String THURSDAY = "星期四";
    public static final String FRIDAY = "星期五";
    public static final String SATURDAY = "星期六";

    public static String getDateDetail(String date) {
        Calendar today = Calendar.getInstance();
        Calendar target = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            today.setTime(df.parse(getNowDateToStr()));
            today.set(Calendar.HOUR, 0);
            today.set(Calendar.MINUTE, 0);
            today.set(Calendar.SECOND, 0);
            target.setTime(df.parse(date));
            target.set(Calendar.HOUR, 0);
            target.set(Calendar.MINUTE, 0);
            target.set(Calendar.SECOND, 0);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        long intervalMilli = target.getTimeInMillis() - today.getTimeInMillis();
        int xcts = (int) (intervalMilli / (24 * 60 * 60 * 1000));
        return showDateDetail(xcts, target);

    }

    /**
     * 将日期差显示为日期或者星期
     *
     * @param xcts
     * @param target
     * @return
     */
    private static String showDateDetail(int xcts, Calendar target) {
        switch (xcts) {
            case 0:
                return TODAY;
            case 1:
                return TOMORROW;
            case 2:
                return AFTER_TOMORROW;
            case -1:
                return YESTERDAY;
            case -2:
                return BEFORE_YESTERDAY;
            default:
                int dayForWeek = 0;
                dayForWeek = target.get(Calendar.DAY_OF_WEEK);
                switch (dayForWeek) {
                    case 1:
                        return SUNDAY;
                    case 2:
                        return MONDAY;
                    case 3:
                        return TUESDAY;
                    case 4:
                        return WEDNESDAY;
                    case 5:
                        return THURSDAY;
                    case 6:
                        return FRIDAY;
                    case 7:
                        return SATURDAY;
                    default:
                        return null;
                }
        }
    }

    public static String getNowDateToStr() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static String dateToString(Date time) {
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ctime = formatter.format(time);

        return ctime;
    }
}
