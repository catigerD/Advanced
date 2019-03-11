package com.example.proxy;

import java.util.Map;

/**
 * Created by dengchong on 19-3-11.
 */
public class HttpHelper implements IHttpProcessor {

    private static final HttpHelper INSTANCE = new HttpHelper();

    private HttpHelper() {

    }

    public static final HttpHelper getInstance() {
        return INSTANCE;
    }

    private static IHttpProcessor mHttpProcessor;

    public static void init(IHttpProcessor httpProcessor) {
        mHttpProcessor = httpProcessor;
    }

    @Override
    public void post(String url, Map<String, String> params, ICallback callback) {
        mHttpProcessor.post(url, params, callback);
    }
}
