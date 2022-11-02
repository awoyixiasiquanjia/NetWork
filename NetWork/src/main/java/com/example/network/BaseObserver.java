package com.example.network;

import android.accounts.NetworkErrorException;

import androidx.annotation.CallSuper;

import org.json.JSONObject;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

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
            if (e instanceof ConnectException
                    || e instanceof TimeoutException
                    || e instanceof NetworkErrorException
                    || e instanceof UnknownHostException) {
                onFailure(e, true);
            } else {
                onFailure(e, false);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
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

    protected abstract void onFailure(Throwable e, boolean isNetWorkError) throws Exception;

    //对应后台的code码的处理
    @CallSuper
    protected void onCodeError(int errorCode)  {

    }

}
