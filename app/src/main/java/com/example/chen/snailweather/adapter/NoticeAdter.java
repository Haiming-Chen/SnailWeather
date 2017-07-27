package com.example.chen.snailweather.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.chen.snailweather.R;
import com.example.chen.snailweather.bean.NoticeBean;

import java.util.Calendar;
import java.util.List;

/**
 * Created by chen on 2017/7/27 0027.
 */

public class NoticeAdter extends BaseQuickAdapter<NoticeBean,BaseViewHolder> {

    private List<NoticeBean.HeWeather5Bean> HeWeather5;
    public NoticeAdter(@Nullable List<NoticeBean> data) {
        super(R.layout.mian_rv_item1, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NoticeBean item) {
        HeWeather5=item.getHeWeather5();
        for (int i = 0; i < HeWeather5.size(); i++) {
              helper.setText(R.id.time_zw, HeWeather5.get(i).getDaily_forecast().get(i).getDate())
                    .setText(R.id.date, HeWeather5.get(i).getDaily_forecast().get(i).getDate())
                    .setText(R.id.cond_txtd, HeWeather5.get(i).getDaily_forecast().get(i).getCond().getTxt_d())
                    .setText(R.id.cond_txtn, HeWeather5.get(i).getDaily_forecast().get(i).getCond().getTxt_d())
                    .setText(R.id.tmp_max, HeWeather5.get(i).getDaily_forecast().get(i).getTmp().getMax())
                    .setText(R.id.tmp_min, HeWeather5.get(i).getDaily_forecast().get(i).getTmp().getMin())
            ;
        }
    }


    public static String getTodayOrYesterday(long date) {//date 是存储的时间戳
        //所在时区时8，系统初始时间是1970-01-01 80:00:00，注意是从八点开始，计算的时候要加回去
        int offSet = Calendar.getInstance().getTimeZone().getRawOffset();
        long today = (System.currentTimeMillis()+offSet)/86400000;
        long start = (date+offSet)/86400000;
        long intervalTime = start - today;
        //-2:前天,-1：昨天,0：今天,1：明天,2：后天
        String strDes="";
        if(intervalTime==0){
            strDes= "今天";
        }else if(intervalTime==1){
            strDes="昨天";
        }
        else if(intervalTime==2){
            strDes= "后天";//昨天
        }
        return strDes;
    }
}
