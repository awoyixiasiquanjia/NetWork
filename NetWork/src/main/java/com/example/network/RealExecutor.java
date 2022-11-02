package com.example.network;

public abstract class RealExecutor implements Iexecutor {
    //是否显示加载进度条
    private boolean isShowLoading;
    public  void setShowLoading(boolean isShow){
        this.isShowLoading = isShow;
    };
    public boolean isShowLoading() {
        return isShowLoading;
    }
}
