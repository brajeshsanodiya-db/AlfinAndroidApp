package com.alfinapp.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.alfinapp.data.local.AlfinPreferences;

import java.util.HashMap;
import java.util.Locale;

/**
 * * Created by Lalit Goswami on 03/12/2019.
 */

public class ToolsUtils {
    private static ToolsUtils toolsUtils;

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

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp      A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public float convertDpToPixel(Context context, float dp) {
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px      A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public float convertPixelsToDp(float px, Context context) {
        return px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public HashMap<String, String> getApiHeaders(Context context) {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        String authorization = AlfinPreferences.getInstance(context).getStringValue(AlfinConstants.Authorization.AUTH_TOKEN, "");
        if (!TextUtils.isEmpty(authorization))
            headers.put("Authorization", "Bearer " + authorization);
        return headers;
    }
}