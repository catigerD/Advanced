package com.example.proxy;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by dengchong on 19-3-11.
 */
public abstract class HttpCallback<Result> implements ICallback {

    @Override
    public void onSuccess(String response) {
        Gson gson = new Gson();
        Class<?> clazz = analysisClass(this);
        Result result = (Result) gson.fromJson(response, clazz);
        onSuccess(result);
    }

    private Class<?> analysisClass(HttpCallback<Result> resultHttpCallback) {
        try {
            Type genericSuperclass = resultHttpCallback.getClass().getGenericSuperclass();
            Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
            return (Class<?>) actualTypeArguments[0];
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public void onFail(String error) {

    }

    public abstract void onSuccess(Result result);
}
