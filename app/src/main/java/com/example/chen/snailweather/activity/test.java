package com.example.chen.snailweather.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.chen.snailweather.R;

/**
 * Created by chen on 2017/8/1 0001.
 */

public class test extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {
    private final static String TAG = "AlipayActivity";
    private AppBarLayout abl_bar;
    private View tl_expand, tl_collapse;
    private int mMaskColor;
    private RecyclerView rv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMaskColor = getResources().getColor(R.color.mycolor1);
//		rv_content = (RecyclerView) findViewById(R.id.rv_content);
//		rv_content.setLayoutManager(new GridLayoutManager(this, 4));
//		rv_content.setAdapter(new LifeAdapter(this, LifeItem.getDefault()));

     /*   abl_bar = (AppBarLayout) findViewById(R.id.abl_bar);
        tl_expand = (View) findViewById(R.id.tl_expand);
        tl_collapse = (View) findViewById(R.id.tl_collapse);
       // v_expand_mask = (View) findViewById(R.id.v_expand_mask);
        //v_collapse_mask = (View) findViewById(R.id.v_collapse_mask);
        abl_bar.addOnOffsetChangedListener(this);*/
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        Log.d(TAG, "verticalOffset="+verticalOffset);
     /*   int offset = Math.abs(verticalOffset);
        int total = appBarLayout.getTotalScrollRange();
        int alphaIn = offset;
        int alphaOut = (200-offset)<0?0:200-offset;
        int maskColorIn = Color.argb(alphaIn, Color.red(mMaskColor),
                Color.green(mMaskColor), Color.blue(mMaskColor));
        int maskColorInDouble = Color.argb(alphaIn*2, Color.red(mMaskColor),
                Color.green(mMaskColor), Color.blue(mMaskColor));
        int maskColorOut = Color.argb(alphaOut*2, Color.red(mMaskColor),
                Color.green(mMaskColor), Color.blue(mMaskColor));
        if (offset <= total / 2) {
            tl_expand.setVisibility(View.VISIBLE);
            tl_collapse.setVisibility(View.GONE);

        } else {
            tl_expand.setVisibility(View.GONE);
            tl_collapse.setVisibility(View.VISIBLE);

        }*/
    }
}
