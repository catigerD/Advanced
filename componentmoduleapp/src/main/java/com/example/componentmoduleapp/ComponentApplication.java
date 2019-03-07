package com.example.componentmoduleapp;

import android.app.Application;

import com.example.componentlibrary.IAppComponent;
import com.example.componentlibrary.ServiceFactory;

/**
 * Created by dengchong on 19-3-7.
 */
public class ComponentApplication extends Application implements IAppComponent {

    public static Application mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        initApplication(this);
    }

    @Override
    public void initApplication(Application application) {
        mApplication = application;
        ServiceFactory.getInstance().register(new ComponentService());
    }
}
