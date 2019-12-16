package com.alfinapp;

import android.app.Application;

import com.alfinapp.utils.FontsUtils;

public class AlfinApplication extends Application {

    static AlfinApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        AlfinApplication.instance = this;

        setApplicationFont();
    }

    public static AlfinApplication getInstance() {
        return AlfinApplication.instance;
    }

    public void setApplicationFont() {
        FontsUtils.setDefaultFont(this, "MONOSPACE", "fonts/Lato-Medium.ttf");
    }
}
