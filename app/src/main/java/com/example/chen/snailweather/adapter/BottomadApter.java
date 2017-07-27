package com.example.chen.snailweather.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.chen.snailweather.R;
import com.example.chen.snailweather.bean.AddCityBean;

import java.util.List;

/**
 * Created by chen on 2017/7/24 0024.
 */

public class BottomadApter extends BaseQuickAdapter<AddCityBean,BaseViewHolder> {

    public BottomadApter(@Nullable List<AddCityBean> data) {
        super(R.layout.bottom_rv_item, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, AddCityBean item) {
        helper.setText(R.id.tv1, item.getCity());
    }
}
