package com.example.network;


import java.util.Map;

/**
 * @author sujinbiao
 * @date 2022/10/21
 * @description  统一的网络请求工具类
 */

public class HttpUtils  implements Iexecutor {

    private static  String BaseUrl;

    public static String getBaseUrl() {
        return BaseUrl;
    }

    public static void setBaseUrl(String baseUrl) {
        BaseUrl = baseUrl;
    }

    private RealExecutor mIexecutor;

    private HttpUtils() {
         mIexecutor = ExecutorFactory.creat(RetrofitExecutor.class);
    }

    public static HttpUtils getInstance(){
      return  InnerClass.getHttpUtils();
    }

    public HttpUtils setShowLoading(boolean isShow){
        mIexecutor.setShowLoading(isShow);
        return InnerClass.mHttpUtils;
    }

    @Override
    public <T extends BaseResponseBean> void doGet(String url, Map<String, Object> maps, Class<T> cls,Callback<T> callback) {
        mIexecutor.doGet(url,maps,cls,callback);
    }

    @Override
    public <T extends BaseResponseBean> void doPost(String url, Map<String, Object> maps, Class<T> cls, Callback<T> callback) {
        mIexecutor.doPost(url,maps,cls,callback);
    }

    private final static class InnerClass{
        private static HttpUtils mHttpUtils= new HttpUtils();
        private static HttpUtils getHttpUtils(){
            return mHttpUtils;
        }
    }

}
