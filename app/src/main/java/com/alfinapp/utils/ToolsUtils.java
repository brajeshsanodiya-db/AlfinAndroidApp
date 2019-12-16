package com.alfinapp.utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.util.Locale;

/**
 * * Created by Lalit Goswami on 03/12/2019.
 */

public class ToolsUtils {
    static ToolsUtils toolsUtils;

    private ToolsUtils() {
        // This tools class is not publicly instantiable
    }

    public static ToolsUtils getToolsUtils() {
        if (toolsUtils == null)
            toolsUtils = new ToolsUtils();
        return toolsUtils;
    }

    public void setLocale(Context context, String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }
}