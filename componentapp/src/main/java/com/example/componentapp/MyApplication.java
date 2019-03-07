package com.example.componentapp;

import android.app.Application;

import com.example.componentlibrary.IAppComponent;

/**
 * Created by dengchong on 19-3-7.
 */
public class MyApplication extends Application implements IAppComponent {

    @Override
    public void onCreate() {
        super.onCreate();
        initApplication(this);
    }

    @Override
    public void initApplication(Application application) {
        try {
            Class<?> aClass = Class.forName("com.example.componentmoduleapp.ComponentApplication");
            IAppComponent appComponent = (IAppComponent) aClass.newInstance();
            appComponent.initApplication(application);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
