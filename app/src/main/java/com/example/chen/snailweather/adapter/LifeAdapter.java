package com.example.chen.snailweather.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.chen.snailweather.R;
import com.example.chen.snailweather.bean.LifeBean;

import java.util.List;

/**
 * 类名: LifeAdapter
 * 作者: 陈海明
 * 时间: 2017-08-01 09:13
 * 描述: NULL
 */
public class LifeAdapter extends BaseQuickAdapter<LifeBean, BaseViewHolder> {

    public LifeAdapter(@Nullable List<LifeBean> data) {
        super(R.layout.mian_rv_item2,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LifeBean item) {
        helper.setImageResource(R.id.suggestion_img,item.getImg_id())
        .setText(R.id.suggestion_txt1,item.getLess())
        .setText(R.id.suggestion_txt2,item.getDetailed());
    }
}
