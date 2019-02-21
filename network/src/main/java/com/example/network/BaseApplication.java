package com.example.network;

import android.app.Application;

/**
 * Created by dengchong on 19-2-21.
 */
public class BaseApplication extends Application {
    public static Application application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        NetworkManager.getInstance().init(this);
    }
}
