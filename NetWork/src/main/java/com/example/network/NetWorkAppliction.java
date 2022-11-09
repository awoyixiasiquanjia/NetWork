package com.example.network;
import android.app.Application;
import android.content.Context;

import java.util.Objects;

public class NetWorkAppliction {
    private static Context mAppContext;
    private static String baseUrl;

    public static void init(Application appContext,String baseUrl) {
        Objects.requireNonNull(appContext,"appContext not null!");
        Objects.requireNonNull(baseUrl,"baseUrl not null!");
        if(mAppContext == null) {
            mAppContext = appContext;
        }
    }

    public static Context getAppContext() {
        return mAppContext;
    }

    public static String getBaseUrl(){
        return baseUrl;
    }
}
