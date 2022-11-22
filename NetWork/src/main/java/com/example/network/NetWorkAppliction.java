package com.example.network;
import android.app.Application;
import android.content.Context;

import java.util.Objects;

public class NetWorkAppliction {
    private static Context mAppContext;
    private static String mbaseUrl;

    public static void init(Application appContext,String baseUrl) {
        Objects.requireNonNull(appContext,"appContext not null!");
        Objects.requireNonNull(baseUrl,"baseUrl not null!");
        mAppContext = appContext.getApplicationContext();
        mbaseUrl = baseUrl;
        NetWorkSPUtil.init(appContext,"network",appContext.MODE_PRIVATE);
    }

    public static Context getAppContext() {
        return mAppContext;
    }

    public static String getBaseUrl(){
        return mbaseUrl;
    }
}
