package com.alfinapp.utils;


import android.content.Context;
import android.graphics.Typeface;

import java.lang.reflect.Field;

public final class FontsUtils {

    public static void setDefaultFont(Context context, String staticTypefaceFieldName, String fontAssetName) {
        try {
            if(context != null) {
                final Typeface regular = Typeface.createFromAsset(context.getAssets(), fontAssetName);
                replaceFont(staticTypefaceFieldName, regular);
            }
        } catch (Exception e) {
        }
    }

    protected static void replaceFont(String staticTypefaceFieldName, final Typeface newTypeface) {
        try {
            final Field staticField = Typeface.class.getDeclaredField(staticTypefaceFieldName);
            staticField.setAccessible(true);
            staticField.set(null, newTypeface);
        } catch (NoSuchFieldException e) {
        } catch (Exception e) {
        }
    }
}