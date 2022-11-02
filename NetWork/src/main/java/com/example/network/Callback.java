package com.example.network;

public interface Callback<T> {

    void onResponse(T response);

    void onFailure(Throwable e);

}
