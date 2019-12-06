package com.alfinapp;

import android.app.Application;

public class App extends Application {

    static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        App.instance = this;
    }

    public static App getInstance() {
        return App.instance;
    }
}
