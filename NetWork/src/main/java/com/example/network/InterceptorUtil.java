package com.example.network;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;


/**
 * @author sujinbiao
 * @date 2022/10/21
 * @description 打印网络请求的日志
 */
public class InterceptorUtil {
    public static String TAG = "----";
    //日志拦截器
    public static HttpLoggingInterceptor LogInterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.w(TAG, "log: " + message);
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY);//设置打印数据的级别
    }


    public static Interceptor BaseInterceptor(){
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                //修改请求头
                Request request = chain.request().newBuilder().addHeader("accessToken","16655613634921DDEpMnYrc").build();
                return chain.proceed(request);
            }
        };
    }




}
