package com.example.chen.snailweather.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chen.snailweather.R;
import com.example.chen.snailweather.Retrofit.Api;
import com.example.chen.snailweather.Retrofit.RetrofitProvider;
import com.example.chen.snailweather.adapter.BottomadApter;
import com.example.chen.snailweather.adapter.ForecastAdter;
import com.example.chen.snailweather.bean.AddCityBean;
import com.example.chen.snailweather.bean.CurrentBean;
import com.example.chen.snailweather.bean.ForecastBean;
import com.example.chen.snailweather.bean.WeatherBean;
import com.example.chen.snailweather.view.BottomRecyclerView;
import com.example.chen.snailweather.view.MyLayoutManager;
import com.jaeger.library.StatusBarUtil;
import com.sunfusheng.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * The type Main activity.
 */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public List<AddCityBean> data = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    @BindView(R.id.main_city)
    TextView mainCity;
    @BindView(R.id.mian_tmp)
    TextView mianTmp;
    @BindView(R.id.cond_txt)
    TextView condTxt;
    @BindView(R.id.mian_api)
    TextView mianApi;
    @BindView(R.id.main_dir)
    TextView mainDir;
    @BindView(R.id.mian_sc)
    TextView mianSc;
    @BindView(R.id.main_hum)
    TextView mainHum;
    @BindView(R.id.mian_fl)
    TextView mianFl;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.mian_recycler_View1)
    RecyclerView mianRecyclerView1;
    @BindView(R.id.mian_recycler_View2)
    RecyclerView mianRecyclerView2;
    @BindView(R.id.mian_recycler_View)
    BottomRecyclerView mRecyclerView;
    @BindView(R.id.marqueeView)
    MarqueeView marqueeView;

    private BottomadApter adapter;
    private ForecastAdter mNoticeAdter;
    public String str[] = {"深圳", "上海", "广州", "北京", "杭州", "长沙", "重庆", "南京", "武汉"};
    public static String key = "3f1dbd931bfb4640bbea311eff0efe78";
    Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(MainActivity.this, ContextCompat.getColor(this, R.color.mycolor1), 0);
        settoolbar();
        initview();
        initdata();
    }

    private void initview() {
        api = RetrofitProvider.get().create(Api.class);
        linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(new MyLayoutManager());
        adapter = new BottomadApter(data);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setSwitchListener(new BottomRecyclerView.SwitchListener() {
            @Override
            public void onSwitch(int realIndex) {
                getWeather(str[realIndex]);
                getForecast(str[realIndex]);
                getNow(str[realIndex]);
                Toast.makeText(MainActivity.this, "当前选择" + realIndex, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initdata() {
        getWeather("深圳");
        getNow("深圳");
        getForecast("深圳");
        for (int i = 0; i < str.length; i++) {
            data.add(new AddCityBean(str[i]));
        }

    }

    private void initNowdata(String City, String tmp, String cond, String Dir, String sc, String hum, String fl) {
        mainCity.setText(City);//城市
        mianTmp.setText(tmp + "°");//温度
        condTxt.setText(cond);//状况
        mainDir.setText(Dir);//风向
        mianSc.setText(sc);//风力
        mainHum.setText(hum + "%");//湿度
        mianFl.setText(fl + "°");//体感温度
    }

    public void getWeather(String city) {
        api.getWeather(city, key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response<WeatherBean>>() {
                    @Override
                    public void accept(Response<WeatherBean> response) throws Exception {
                        WeatherBean.HeWeather5Bean.AqiBean aqiBean = response.body().getHeWeather5().get(0).getAqi();
                        WeatherBean.HeWeather5Bean.SuggestionBean suggestionBean = response.body().getHeWeather5().get(0).getSuggestion();
                        mianApi.setText(StrQlty(aqiBean.getCity().getQlty()) + "  " + aqiBean.getCity().getPm25());//空气质量
                        setMarqueeView(suggestionBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.w("Error", throwable);
                    }
                });
    }

    private void setMarqueeView(WeatherBean.HeWeather5Bean.SuggestionBean suggestionBean) {
        List<String> info = new ArrayList<>();
        info.add(suggestionBean.getAir().getTxt());
        info.add(suggestionBean.getComf().getTxt());
        info.add(suggestionBean.getCw().getTxt());
        info.add(suggestionBean.getDrsg().getTxt());
        info.add(suggestionBean.getFlu().getTxt());
        info.add(suggestionBean.getTrav().getTxt());
        info.add(suggestionBean.getUv().getTxt());
        marqueeView.startWithList(info);
    }

    public String StrQlty(String qlty) {
        if (qlty.isEmpty())
            return null;
        if (qlty.length() == 1) {
            return qlty = "空气" + qlty;
        }
        return qlty;
    }

    public void getForecast(String city) {
        api.getForecast(city, key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response<ForecastBean>>() {
                    @Override
                    public void accept(Response<ForecastBean> response) throws Exception {
                        List<ForecastBean.HeWeather5Bean.DailyForecastBean> forecastBean =
                                response.body().getHeWeather5().get(0).getDaily_forecast();
                        mianRecyclerView1.setLayoutManager(linearLayoutManager);
                        mNoticeAdter = new ForecastAdter(forecastBean);
                        mianRecyclerView1.setAdapter(mNoticeAdter);
                        // Log.e("xxxxxxxxxxxxxx", forecastBean.get(0).getDaily_forecast().size()+"");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.w("Error", throwable);
                    }
                });
    }

    public void getNow(String city) {
        api.getNow(city, key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response<CurrentBean>>() {
                    @Override
                    public void accept(Response<CurrentBean> response) throws Exception {
                        CurrentBean.HeWeather5Bean.BasicBean basic = response.body().getHeWeather5().get(0).getBasic();
                        CurrentBean.HeWeather5Bean.NowBean now = response.body().getHeWeather5().get(0).getNow();
                        initNowdata(basic.getCity(), now.getTmp(), now.getCond().getTxt()
                                , now.getWind().getDir(), now.getWind().getSc(), now.getHum(), now.getFl());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.w("Error", throwable);
                    }
                });
    }


    private void settoolbar() {
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
