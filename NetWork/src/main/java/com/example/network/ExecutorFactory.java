package com.example.network;

/**
 * @author sujinbiao
 * @date 2022/10/21
 * @description  创建网络请求的工厂
 */
public class ExecutorFactory {
    public static RealExecutor creat (Class cls){
        RealExecutor mIexecutor =null;
         if (!RealExecutor.class.isAssignableFrom(cls)){
             throw  new RuntimeException("cls must implements iexecutor !!");
         }
         if (cls == RetrofitExecutor.class ){
             mIexecutor =  new RetrofitExecutor();
         }
         return mIexecutor;
    }

}
