package com.example.chen.snailweather.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.chen.snailweather.R;
import com.example.chen.snailweather.Retrofit.Api;
import com.example.chen.snailweather.Retrofit.RetrofitProvider;
import com.example.chen.snailweather.adapter.ForecastAdapter;
import com.example.chen.snailweather.adapter.LifeAdapter;
import com.example.chen.snailweather.bean.AddCityBean;
import com.example.chen.snailweather.bean.CurrentBean;
import com.example.chen.snailweather.bean.ForecastBean;
import com.example.chen.snailweather.bean.HourlyBean;
import com.example.chen.snailweather.bean.LifeBean;
import com.example.chen.snailweather.bean.WeatherBean;
import com.example.chen.snailweather.utils.GetImgIdUtils;
import com.example.chen.snailweather.utils.SPUtils;
import com.jaeger.library.StatusBarUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sunfusheng.marqueeview.MarqueeView;
import com.zaaach.citypicker.CityPickerActivity;

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
    TextView mainCity;//城市
    @BindView(R.id.mian_tmp)
    TextView mianTmp;//温度
    @BindView(R.id.cond_txt)
    TextView condTxt;//天气状况:多云
    @BindView(R.id.mian_api)
    TextView mianApi;//空气质量:空气优 pm2.5=30
    @BindView(R.id.main_dir)
    TextView mainDir;//风向
    @BindView(R.id.main_hum)
    TextView mainHum;//相对湿度
    @BindView(R.id.mian_fl)
    TextView mianFl;//体感温度
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.mian_recycler_View1)
    RecyclerView mianRecyclerView1;//3天的预报
    @BindView(R.id.mian_recycler_View2)
    RecyclerView mianRecyclerView2;//生活指数
    @BindView(R.id.marqueeView)
    MarqueeView marqueeView;//滚动文字
    @BindView(R.id.main_city1)
    TextView mainCity1;//toolbar滑动上去改变的城市
    @BindView(R.id.cond_img1)
    ImageView cond_img1;//天气状况
    @BindView(R.id.mian_tmp1)
    TextView mianTmp1;//温度
    @BindView(R.id.update_date)
    TextView updateDate;//更新时间
    @BindView(R.id.collapse_layout)
    CollapsingToolbarLayout collapseLayout;//天气状况背景
    @BindView(R.id.abl_bar)
    AppBarLayout ablBar;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;//下拉刷新
    @BindView(R.id.img_api)
    ImageView imgApi;//空气质量的图标
    @BindView(R.id.bottom_tv)
    TextView bottomTv;
    @BindView(R.id.bottom_tv1)
    TextView bottomTv1;
    @BindView(R.id.location)
    LinearLayout location;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.nsv_layout)
    NestedScrollView nsvLayout;


    private View include_toolbar1;
    private View include_toolbar2;
    private ForecastAdapter mNoticeAdter;
    private LifeAdapter mLifeAdapter;
    private List<AddCityBean> addCityData = new ArrayList<>();
    private String str[] = {"深圳", "上海", "广州", "北京", "杭州", "长沙", "重庆", "南京", "武汉"};
    private static String key = "3f1dbd931bfb4640bbea311eff0efe78";//天气的key
    private static final int REQUEST_CODE_ACCESS_COARSE_LOCATION = 1;
    private static final int REQUEST_CODE_PICK_CITY = 0;
    private Api api;
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    String address = null;
    String mCity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        StatusBarUtil.setTranslucentForImageView(this, 0, null);
        checkGps();
        settoolbar();
        initview();
        initLocation();
        initdata();
    }


    private void initview() {
        api = RetrofitProvider.get().create(Api.class);
        mianRecyclerView1.setLayoutManager(new LinearLayoutManager(this));
        mianRecyclerView2.setLayoutManager(new LinearLayoutManager(this));
        include_toolbar1 = (View) findViewById(R.id.include_toolbar1);
        include_toolbar2 = (View) findViewById(R.id.include_toolbar2);
        ablBar.addOnOffsetChangedListener(this);
        initRefreshLayout();
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(myListener);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent=new Intent();
                intent.setClass(MainActivity.this,CitylistActivity.class);
                startActivity(intent);*/
                //启动
                startActivityForResult(new Intent(MainActivity.this, CityPickerActivity.class),
                        REQUEST_CODE_PICK_CITY);
            }
        });
    }

    private void initdata() {
        mLocationClient.start();
        String condid1 = (String) SPUtils.get(MainActivity.this, "CONDID", "");
        collapseLayout.setBackgroundResource(GetImgIdUtils.getbgImgID(condid1));
        mCity = (String) SPUtils.get(this, "City", "");
        address = (String) SPUtils.get(this, "NAME", "");
        if (!address.isEmpty()) {
            getNow(address);
            getForecast(address);
        }
        if (!address.isEmpty()) {
            getWeather(mCity);
            getHourly(mCity);
        }
    }

    private void setAdddata() {
        for (int i = 0; i < str.length; i++) {
            addCityData.add(new AddCityBean(str[i]));
        }
    }

    private void initRefreshLayout() {
       /*   //设置 Header 为 Material风格
          refreshLayout.setRefreshHeader(new MaterialHeader(this).setShowBezierWave(true));
         //设置 Footer 为 球脉冲
          refreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));*/
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
                initdata();
            }
        });

    }

    private void initNowdata(String City, String locdate, String tmp, String cond, String condid, String Dir, String sc, String hum, String fl) {
        mainCity.setText(mCity + "" + address);//城市
        mainCity1.setText(address);
        String[] ldate = locdate.split(" ");//以A作为分割点,将字符串a分割为2个字符串数组分别为
        updateDate.setText("更新 " + ldate[1]);
        mianTmp.setText(tmp + "°");//温度
        mianTmp1.setText(tmp + "°");//温度
        condTxt.setText(cond);//状况
        cond_img1.setImageResource(GetImgIdUtils.getimgid(condid));//状况
        collapseLayout.setBackgroundResource(GetImgIdUtils.getbgImgID(condid));
        mainDir.setText(Dir);//风向
        mainHum.setText(hum + "%");//湿度
        mianFl.setText(fl + "°");//体感温度
        SPUtils.put(MainActivity.this, "CONDID", condid);
    }


    public void getHourly(String city) {
        api.getHourly(city, key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response<HourlyBean>>() {
                    @Override
                    public void accept(Response<HourlyBean> response) throws Exception {
                        List<HourlyBean.HeWeather5Bean.HourlyForecastBean> hourlyBeanList = response.body().getHeWeather5().get(0).getHourly_forecast();
                        List<String> hourlydata = new ArrayList<>();
                        for (int i = 0; i < hourlyBeanList.size(); i++) {
                            String[] hourldate = hourlyBeanList.get(i).getDate().split(" ");//以A作为分割点,将字符串a分割为2个字符串数组分别为
                            String str = "今天 " + hourldate[1] + "  " + hourlyBeanList.get(i).getCond().getTxt() + "  气温 " + hourlyBeanList.get(i).getTmp() +
                                    "°  湿度  " + hourlyBeanList.get(i).getHum() + "%";
                            Log.e(TAG, "accept: " + str);
                            hourlydata.add(str);
                        }
                        marqueeView.startWithList(hourlydata);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.w("Error", throwable);
                    }
                });
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
                        setSuggestiondata(getSuggestiondata(suggestionBean, 1), getSuggestiondata(suggestionBean, 0));
                        imgApi.setBackgroundResource(GetImgIdUtils.getAqiImgid(aqiBean.getCity().getQlty()));
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
            suggestdata.add(suggestionBean.getSport().getTxt());
            suggestdata.add(suggestionBean.getTrav().getTxt());
            suggestdata.add(suggestionBean.getUv().getTxt());

        } else {
            suggestdata.add(suggestionBean.getAir().getBrf());
            suggestdata.add(suggestionBean.getComf().getBrf());
            suggestdata.add(suggestionBean.getCw().getBrf());
            suggestdata.add(suggestionBean.getDrsg().getBrf());
            suggestdata.add(suggestionBean.getFlu().getBrf());
            suggestdata.add(suggestionBean.getSport().getBrf());
            suggestdata.add(suggestionBean.getTrav().getBrf());
            suggestdata.add(suggestionBean.getUv().getBrf());
        }
        return suggestdata;
    }


    private void setSuggestiondata(List<String> less, List<String> detailed) {
        List<LifeBean> life = new ArrayList<>();
        int img_id[] = {R.mipmap.air, R.mipmap.comf, R.mipmap.cw, R.mipmap.drsg,
                R.mipmap.flu, R.mipmap.sport, R.mipmap.trav, R.mipmap.uv,};
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
                        initNowdata(basic.getCity(), basic.getUpdate().getLoc(), now.getTmp(), now.getCond().getTxt()
                                , now.getCond().getCode(), now.getWind().getDir(), now.getWind().getSc(), now.getHum(), now.getFl());
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
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        Log.d(TAG, "verticalOffset=" + verticalOffset);
        int offset = Math.abs(verticalOffset);
        int total = appBarLayout.getTotalScrollRange();
        //int alpha = ((offset / total));//透明度变化速率
        // Log.e(TAG, "offset ="+offset );
        Float alpha = Float.valueOf(1 - offset / total);
        if (offset < 1000) {
            StatusBarUtil.setTranslucentForImageView(this, 0, null);
        }
        if (offset < 100) {
            mianApi.setVisibility(View.VISIBLE);
            imgApi.setVisibility(View.VISIBLE);

            bottomTv.setVisibility(View.GONE);
            bottomTv1.setVisibility(View.VISIBLE);
        } else {
            mianApi.setVisibility(View.GONE);
            imgApi.setVisibility(View.GONE);
            bottomTv.setVisibility(View.VISIBLE);
            bottomTv1.setVisibility(View.GONE);
        }
        if (offset <= total / 2) {
            include_toolbar1.setVisibility(View.VISIBLE);
            include_toolbar2.setVisibility(View.GONE);
            StatusBarUtil.setTranslucentForImageView(this, 0, null);
        } else {
            include_toolbar1.setVisibility(View.GONE);
            include_toolbar2.setVisibility(View.VISIBLE);
            StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.white), 112);
        }
    }

    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //Receive Location
            //经纬度
            //double lati = location.getLatitude();
            //double longa = location.getLongitude();
            if (!location.getDistrict().isEmpty()) {
                address = location.getDistrict();
            } else {
                address = location.getCity();
            }
            mCity = location.getCity();
            getNow(address);
            getForecast(address);
            getWeather(mCity);
            getHourly(mCity);
            SPUtils.put(MainActivity.this, "City", location.getCity());
            SPUtils.put(MainActivity.this, "NAME", address);
            //打印出当前位置
            Log.i("TAG", "location.getAddrStr()=" + location.getCity());
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("gcj02");
        //可选，默认gcj02，设置返回的定位结果坐标系
      /*  int span = 1000;
        option.setScanSpan(span);*/
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps
        mLocationClient.setLocOption(option);
    }

    /**
     * 开启位置权限
     */
    private void checkGps() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                //Android 6.0 动态申请权限
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_CODE_ACCESS_COARSE_LOCATION);

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_ACCESS_COARSE_LOCATION) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "位置权限已开启,开始定位", Toast.LENGTH_SHORT).show();
                mLocationClient.start();
            } else {
                Toast.makeText(this, "未开启位置权限,无法定位", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    //重写onActivityResult方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK){
            if (data != null){
                String city = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                getForecast(city);
                getHourly(city);
                getNow(city);
                getWeather(city);
            }
        }
    }
}
