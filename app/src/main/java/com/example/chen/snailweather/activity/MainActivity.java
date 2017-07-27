package com.example.chen.snailweather.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.chen.snailweather.R;
import com.example.chen.snailweather.adapter.BottomadApter;
import com.example.chen.snailweather.adapter.NoticeAdter;
import com.example.chen.snailweather.bean.AddCityBean;
import com.example.chen.snailweather.bean.NoticeBean;
import com.example.chen.snailweather.db.ProvincesDB;
import com.example.chen.snailweather.util.HttpUtil;
import com.example.chen.snailweather.view.BottomRecyclerView;
import com.example.chen.snailweather.view.MyLayoutManager;
import com.jaeger.library.StatusBarUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * The type Main activity.
 */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public List<AddCityBean> data = new ArrayList<>();
    List<NoticeBean> Noticedata= new ArrayList<>();
    private BottomadApter adapter;
    private NoticeAdter mNoticeAdter;
    public String str[] = {"深圳", "上海", "广州", "北京", "杭州", "长沙", "重庆", "南京", "武汉"};
    public String URL="https://free-api.heweather.com/v5/forecast?city=长沙&key=bc0418b57b2d4918819d3974ac1285d9";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.mian_recycler_View1)
    RecyclerView mianRecyclerView1;
    @BindView(R.id.mian_recycler_View2)
    RecyclerView mianRecyclerView2;
    @BindView(R.id.mian_recycler_View)
    BottomRecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(MainActivity.this, ContextCompat.getColor(this, R.color.mycolor1), 0);
        initdata();
        initview();
        settoolbar();
        initokhttp();
    }

    private void initokhttp() {
        HttpUtil.setOkHttpRequest(URL, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.toString();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
               // JSON.parseObject(response, new NoticeBean().getHeWeather5());
            }
        });
    }


    private void initview() {
        mRecyclerView.setLayoutManager(new MyLayoutManager());
        adapter = new BottomadApter(data);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setSwitchListener(new BottomRecyclerView.SwitchListener() {
            @Override
            public void onSwitch(int realIndex) {
                Toast.makeText(MainActivity.this, "当前选择" + realIndex, Toast.LENGTH_SHORT).show();
            }
        });
        initRV();
    }

    private void initdata() {
        for (int i = 0; i < str.length; i++) {
            data.add(new AddCityBean(str[i]));
        }
    }

    private void settoolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void initRV() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
       //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        mNoticeAdter=new NoticeAdter(Noticedata);
        mianRecyclerView1.setAdapter(mNoticeAdter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar bottom_rv_item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
