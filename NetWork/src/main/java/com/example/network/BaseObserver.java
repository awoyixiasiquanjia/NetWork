package com.example.network;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.CallSuper;

import org.json.JSONObject;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLHandshakeException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public abstract class BaseObserver implements Observer<ResponseBody> {

    @Override
    public void onSubscribe(Disposable d) {
        onRequestStart();
    }

    @Override
    public void onNext(ResponseBody responseBody) {
        try {
            String string = responseBody.string();
            JSONObject jsonObject = new JSONObject(string);
            Boolean code = jsonObject.getBoolean("result");
            if (code) {
                onSuccees(string);
            } else {
               // onCodeError(Integer.parseInt(code));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onError(Throwable e) {
        onRequestEnd();
        try {
            if (e instanceof SocketTimeoutException||e instanceof ConnectException) {
                onFailure("网络连接超时");
                Toast.makeText(NetWorkAppliction.getAppContext(),"网络连接超时",Toast.LENGTH_SHORT).show();
            }  else if (e instanceof SSLHandshakeException) {
                onFailure("安全证书异常");
                Toast.makeText(NetWorkAppliction.getAppContext(),"安全证书异常",Toast.LENGTH_SHORT).show();
            } else if (e instanceof HttpException) {
                onFailure("error:" + e.getMessage());
                Toast.makeText(NetWorkAppliction.getAppContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
            } else if (e instanceof UnknownHostException) {
                onFailure("网络异常，请检查您的网络状态");
                Toast.makeText(NetWorkAppliction.getAppContext(),"网络异常，请检查您的网络状态",Toast.LENGTH_SHORT).show();
            } else {
                onFailure(("error:" + e.getMessage()));
                Toast.makeText(NetWorkAppliction.getAppContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e2) {
            onFailure("网络异常，请检查您的网络状态");
            Toast.makeText(NetWorkAppliction.getAppContext(),"网络异常，请检查您的网络状态",Toast.LENGTH_SHORT).show();
            e2.printStackTrace();
        } finally {
            Log.w(InterceptorUtil.TAG, "netLog: " + e.getMessage());
        }
    }

    @Override
    public void onComplete() {
        onRequestEnd();
    }

    protected void onRequestEnd() {
    }

    protected void onRequestStart() {

    }

    protected abstract void onSuccees(String ret);

    protected abstract void onFailure(String msg);

    //对应后台的code码的处理
    @CallSuper
    protected void onCodeError(int errorCode)  {

    }

}
