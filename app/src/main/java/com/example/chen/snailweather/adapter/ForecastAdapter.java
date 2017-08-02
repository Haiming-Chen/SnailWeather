package com.example.chen.snailweather.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.chen.snailweather.R;
import com.example.chen.snailweather.bean.ForecastBean;
import com.example.chen.snailweather.util.GetImgId;

import java.util.Calendar;
import java.util.List;

/**
 * Created by chen on 2017/7/27 0027.
 */

public class ForecastAdapter extends BaseQuickAdapter<ForecastBean.HeWeather5Bean.DailyForecastBean, BaseViewHolder> {

    public ForecastAdapter(@Nullable List<ForecastBean.HeWeather5Bean.DailyForecastBean> data) {
        super(R.layout.mian_rv_item1, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ForecastBean.HeWeather5Bean.DailyForecastBean Daily) {
       // List<ForecastBean.HeWeather5Bean.DailyForecastBean> Daily=item.getDaily_forecast();
        String Cond_txt;
        if (TextUtils.equals(Daily.getCond().getTxt_d(),Daily.getCond().getTxt_n())){
            Cond_txt=Daily.getCond().getTxt_d();
        }else{
            Cond_txt=Daily.getCond().getTxt_d()+"转"+Daily.getCond().getTxt_n();
        }
        helper .setText(R.id.date,  Daily.getDate())
                .setImageResource(R.id.img_cond, GetImgId.getimgid(Daily.getCond().getCode_d()))
                .setText(R.id.cond, Cond_txt)
                .setText(R.id.tmp, Daily.getTmp().getMax()+"°"+"~"+Daily.getTmp().getMax()+"°");
    }

    public static String getTodayOrYesterday(long date) {//date 是存储的时间戳
        //所在时区时8，系统初始时间是1970-01-01 80:00:00，注意是从八点开始，计算的时候要加回去
        int offSet = Calendar.getInstance().getTimeZone().getRawOffset();
        long today = (System.currentTimeMillis() + offSet) / 86400000;
        long start = (date + offSet) / 86400000;
        long intervalTime = start - today;
        //-2:前天,-1：昨天,0：今天,1：明天,2：后天
        String strDes = "";
        if (intervalTime == 0) {
            strDes = "今天";
        } else if (intervalTime == 1) {
            strDes = "昨天";
        } else if (intervalTime == 2) {
            strDes = "后天";//昨天
        }
        return strDes;
    }
}
