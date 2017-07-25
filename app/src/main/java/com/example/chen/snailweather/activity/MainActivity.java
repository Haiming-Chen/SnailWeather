package com.example.chen.snailweather.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.chen.snailweather.R;
import com.example.chen.snailweather.adapter.BottomadApter;
import com.example.chen.snailweather.bean.AddCityBean;
import com.example.chen.snailweather.view.BottomRecyclerView;
import com.example.chen.snailweather.view.MyLayoutManager;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    List<AddCityBean> data = new ArrayList<>();
    BottomadApter adapter;
    String str[] = {"深圳", "上海", "广州", "北京", "杭州", "长沙", "重庆", "南京", "武汉"};

    @BindView(R.id.recycler_View)
    BottomRecyclerView mRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(MainActivity.this, ContextCompat.getColor(this, R.color.mycolor1), 0);
        initdata();
        initview();
        settoolbar();
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
    }

    private void initdata() {
        for (int i = 0; i < str.length; i++) {
            data.add(new AddCityBean(str[i]));
        }
    }

    private  void settoolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
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
