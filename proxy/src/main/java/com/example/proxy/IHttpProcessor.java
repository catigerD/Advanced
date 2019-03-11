package com.example.proxy;

import java.util.Map;

/**
 * Created by dengchong on 19-3-11.
 */
public interface IHttpProcessor {
    void post(String url, Map<String, String> params, ICallback callback);
}
