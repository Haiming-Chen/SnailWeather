package com.example.chen.snailweather.utils;

import com.example.chen.snailweather.activity.mApplication;
import com.example.chen.snailweather.greendao.gen.DaoMaster;
import com.example.chen.snailweather.greendao.gen.DaoSession;

/**
 * @name SnailWeather
 * @class name：com.example.chen.snailweather.util
 * @anthor 陈海明 QQ:3508417
 * @time 2017/7/26 0026 22:21
 * @class 描述
 */
public class GreenDaoManager {


    private DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;

    private GreenDaoManager()
    {
        init();
    }

    /**
     * 静态内部类，实例化对象使用
     */
    private static class SingleInstanceHolder
    {
        private static final GreenDaoManager INSTANCE = new GreenDaoManager();
    }

    /**
     * 对外唯一实例的接口
     *
     * @return
     */
    public static GreenDaoManager getInstance()
    {
        return SingleInstanceHolder.INSTANCE;
    }

    /**
     * 初始化数据
     */
    private void init()
    {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(mApplication.getContext(),
                "city");
        mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }
    public DaoMaster getmDaoMaster()
    {
        return mDaoMaster;
    }
    public static DaoSession getmDaoSession()
    {
        return mDaoSession;
    }
    public DaoSession getNewSession()
    {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }

}
