package com.example.network;


import java.util.Map;

public interface Iexecutor {

      <T extends BaseResponseBean> void doGet(String url,Map<String,Object> maps,Class<T> cls,Callback<T> callback);

      <T extends BaseResponseBean> void doPost(String url,Map<String,Object> maps,Class<T> cls,Callback<T> callback);

}