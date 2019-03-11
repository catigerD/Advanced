package com.example.proxy;

/**
 * Created by dengchong on 19-3-11.
 */
public interface ICallback {

    void onSuccess(String result);

    void onFail(String error);
}
