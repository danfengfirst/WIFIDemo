package com.danfeng.lucky.wifidemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by danfeng.wang on 2017/1/20.
 */

public class BaseApplication extends Application {
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext=getApplicationContext();

    }
    public static Context getmContext(){
        return mContext;
    }
}
