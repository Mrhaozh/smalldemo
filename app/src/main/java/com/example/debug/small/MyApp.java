package com.example.debug.small;

import android.app.Application;

import org.xutils.x;

/**
 * Created by lenovo on 2018/3/6.
 */

public class MyApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(false);
    }
}
