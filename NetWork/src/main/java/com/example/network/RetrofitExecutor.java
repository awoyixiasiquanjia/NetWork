package com.example.network;

import java.util.Map;

public class RetrofitExecutor extends RealExecutor{

    @Override
    public <T extends BaseResponseBean> void doGet(String url, Map<String, Object> maps, Class<T> cls , NetCallBack<T> callback) {
           MyRetrofit.getInstance().doGet(url,maps,cls,callback);
    }

    @Override
    public <T extends BaseResponseBean> void doPost(String url, Map<String, Object> maps, Class<T> cls, NetCallBack<T> callback) {
          MyRetrofit.getInstance().doPost(url,maps,cls,callback);
    }

    @Override
    public void setShowLoading(boolean isShow) {
        MyRetrofit.getInstance().setShowLoading(isShow);
    }
}
