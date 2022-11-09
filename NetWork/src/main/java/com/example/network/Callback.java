package com.example.network;

public interface Callback<T> {

    void onResponse(T response);

    void onFailure(String msg);

    void onRequestStart();

    void onRequestEnd();

}
