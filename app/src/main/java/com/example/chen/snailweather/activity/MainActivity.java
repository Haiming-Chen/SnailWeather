package com.example.chen.snailweather.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chen.snailweather.R;
import com.example.chen.snailweather.Retrofit.Api;
import com.example.chen.snailweather.Retrofit.RetrofitProvider;
import com.example.chen.snailweather.adapter.Bottom;
import com.example.chen.snailweather.adapter.ForecastAdapter;
import com.example.chen.snailweather.adapter.LifeAdapter;
import com.example.chen.snailweather.bean.AddCityBean;
import com.example.chen.snailweather.bean.CurrentBean;
import com.example.chen.snailweather.bean.ForecastBean;
import com.example.chen.snailweather.bean.LifeBean;
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

public class MainActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {
    private static final String TAG = "MainActivity";

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
    @BindView(R.id.main_city1)
    TextView mainCity1;
    @BindView(R.id.cond_txt1)
    TextView condTxt1;
    @BindView(R.id.mian_tmp1)
    TextView mianTmp1;

    private View tl_collapse;
    private View tl_expand;
    private AppBarLayout abl_bar;


    private Bottom mBottomAdapter;
    private ForecastAdapter mNoticeAdter;
    private LifeAdapter mLifeAdapter;
    private List<AddCityBean> addCityData = new ArrayList<>();
    private String str[] = {"深圳", "上海", "广州", "北京", "杭州", "长沙", "重庆", "南京", "武汉"};
    private static String key = "3f1dbd931bfb4640bbea311eff0efe78";
    private Api api;
    private int mMaskColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(MainActivity.this, ContextCompat.getColor(this, R.color.mycolor1), 0);
        settoolbar();
        initview();
        initdata();

        //initRefreshLayout();
    }

    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) {
        @Override
        public boolean canScrollVertically() {
            return true;
        }
    };
    LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this) {
        @Override
        public boolean canScrollVertically() {
            return true;
        }
    };

    private void initview() {
        api = RetrofitProvider.get().create(Api.class);
        mianRecyclerView1.setLayoutManager(linearLayoutManager);
        mianRecyclerView2.setLayoutManager(linearLayoutManager2);
        setAdddata();
        mMaskColor = ContextCompat.getColor(this, R.color.mycolor1);
        abl_bar = (AppBarLayout) findViewById(R.id.abl_bar);
        tl_expand = (View) findViewById(R.id.tl_expand);
        tl_collapse = (View) findViewById(R.id.tl_collapse);
        abl_bar.addOnOffsetChangedListener(this);

    }

    private void initdata() {
        getWeather("深圳");
        getNow("深圳");
        getForecast("深圳");
    }

    private void setAdddata() {
        for (int i = 0; i < str.length; i++) {
            addCityData.add(new AddCityBean(str[i]));
        }
        mRecyclerView.setLayoutManager(new MyLayoutManager());
        mBottomAdapter = new Bottom(addCityData);
        mRecyclerView.setAdapter(mBottomAdapter);
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

    private void initNowdata(String City, String tmp, String cond, String Dir, String sc, String hum, String fl) {
        mainCity.setText(City);//城市
        mainCity1.setText(City);
        mianTmp.setText(tmp + "°");//温度
        mianTmp1.setText(tmp + "°");//温度
        condTxt.setText(cond);//状况
        condTxt1.setText(cond);//状况
        mainDir.setText(Dir);//风向
        mianSc.setText(sc);//风力
        mainHum.setText(hum + "%");//湿度
        mianFl.setText(fl + "°");//体感温度
    }

    /*  private void initRefreshLayout(){
          refreshLayout.setOnRefreshListener(new OnRefreshListener() {
              @Override
              public void onRefresh(RefreshLayout refreshlayout) {
                  refreshlayout.finishRefresh(2000);
              }
          });
          refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
              @Override
              public void onLoadmore(RefreshLayout refreshlayout) {
                  refreshlayout.finishLoadmore(2000);
              }
          });
      }*/
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
                        marqueeView.startWithList(getSuggestiondata(suggestionBean, 0));
                        setSuggestiondata(getSuggestiondata(suggestionBean, 1), getSuggestiondata(suggestionBean, 0));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.w("Error", throwable);
                    }
                });
    }

    private List<String> getSuggestiondata(WeatherBean.HeWeather5Bean.SuggestionBean suggestionBean, int i) {
        List<String> suggestdata = new ArrayList<>();
        if (i == 0) {
            suggestdata.add(suggestionBean.getAir().getTxt());
            suggestdata.add(suggestionBean.getComf().getTxt());
            suggestdata.add(suggestionBean.getCw().getTxt());
            suggestdata.add(suggestionBean.getDrsg().getTxt());
            suggestdata.add(suggestionBean.getFlu().getTxt());
            suggestdata.add(suggestionBean.getTrav().getTxt());
            suggestdata.add(suggestionBean.getUv().getTxt());
        } else {
            suggestdata.add(suggestionBean.getAir().getBrf());
            suggestdata.add(suggestionBean.getComf().getBrf());
            suggestdata.add(suggestionBean.getCw().getBrf());
            suggestdata.add(suggestionBean.getDrsg().getBrf());
            suggestdata.add(suggestionBean.getFlu().getBrf());
            suggestdata.add(suggestionBean.getTrav().getBrf());
            suggestdata.add(suggestionBean.getUv().getBrf());
        }
        return suggestdata;
    }


    private void setSuggestiondata(List<String> less, List<String> detailed) {
        List<LifeBean> life = new ArrayList<>();
        int img_id[] = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,};
        for (int i = 0; i < img_id.length; i++) {
            life.add(new LifeBean(img_id[i], less.get(i), detailed.get(i)));
        }

        mLifeAdapter = new LifeAdapter(life);
        mianRecyclerView2.setAdapter(mLifeAdapter);
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
                        mNoticeAdter = new ForecastAdapter(forecastBean);
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

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        Log.d(TAG, "verticalOffset=" + verticalOffset);
        int offset = Math.abs(verticalOffset);
        int total = appBarLayout.getTotalScrollRange();
        int alphaIn = offset;
        int alphaOut = (200 - offset) < 0 ? 0 : 200 - offset;
        int maskColorIn = Color.argb(alphaIn, Color.red(mMaskColor),
                Color.green(mMaskColor), Color.blue(mMaskColor));
        int maskColorInDouble = Color.argb(alphaIn * 2, Color.red(mMaskColor),
                Color.green(mMaskColor), Color.blue(mMaskColor));
        int maskColorOut = Color.argb(alphaOut * 2, Color.red(mMaskColor),
                Color.green(mMaskColor), Color.blue(mMaskColor));
        if (offset <= total / 2) {
            tl_expand.setVisibility(View.VISIBLE);
            tl_collapse.setVisibility(View.GONE);

        } else {
            tl_expand.setVisibility(View.GONE);
            tl_collapse.setVisibility(View.VISIBLE);
        }
    }
}
