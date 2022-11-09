package com.example.network;


import com.google.gson.Gson;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofit extends RealExecutor {
    NetApi mNetApi;
    private MyRetrofit() {
        Retrofit mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkHttpClient()).baseUrl(NetWorkAppliction.getBaseUrl()).build();
         mNetApi = mRetrofit.create(NetApi.class);
    }

    private OkHttpClient getOkHttpClient() {
       return new OkHttpClient.Builder()
               .connectTimeout(Constant.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
               .readTimeout(Constant.HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS)//设置读取超时时间
               .writeTimeout(Constant.HTTP_WRITE_TIMEOUT, TimeUnit.MILLISECONDS)//设置写入超时时间
               .addInterceptor(InterceptorUtil.LogInterceptor())
               .addInterceptor(InterceptorUtil.BaseInterceptor()).build();
    }

    public static MyRetrofit getInstance(){
         return InnerClass.mMyRetrofit;
    }

    @Override
    public <T extends BaseResponseBean> void doGet(String url, Map<String, Object> maps, Class<T> cls, NetCallBack<T> callback) {
        request(mNetApi.getDynamicData(url),cls,callback);
    }

    @Override
    public <T extends BaseResponseBean> void doPost(String url, Map<String, Object> maps, Class<T> cls, NetCallBack<T> callback) {
        request( mNetApi.postDynamicData(url,maps),cls,callback);
    }


    private final static class InnerClass{
        static MyRetrofit mMyRetrofit= new MyRetrofit();
        private static MyRetrofit getHttpUtils(){
            return mMyRetrofit;
        }
    }


    private <T extends BaseResponseBean> void request(Observable observable, Class<T> cls, NetCallBack<T> callback){
        //这里可以放一个无网返回
        Objects.requireNonNull(cls,"cls  cannot null!!");
        observable.
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver() {

            @Override
            protected void onRequestStart() {
                 if (callback!=null){
                     callback.onRequestStart();
                 }
            }

            @Override
            protected void onSuccees(String ret) {
                if (callback != null) {
                    callback.onResponse(new Gson().fromJson(ret, cls));
                }
            }

            @Override
            protected void onFailure(String e) {
                if (callback!=null){
                    callback.onFailure(e);
                }

            }

            @Override
            protected void onRequestEnd() {
                super.onRequestEnd();
                if (callback!=null){
                    callback.onRequestEnd();
                }
           }
                });

    }


}
