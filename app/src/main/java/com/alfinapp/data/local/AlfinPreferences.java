package com.alfinapp.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

public class AlfinPreferences {

    public static final String NAME = "Alfin_Preference";
    private static AlfinPreferences instance = null;
    private SharedPreferences preferences = null;

    private AlfinPreferences(Context ctx) {


        if (ctx != null) {
            preferences = ctx.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        }
    }

    public static AlfinPreferences getInstance(@NonNull Context ctx) {
        if (instance == null) {
            instance = new AlfinPreferences(ctx);
        }
        return instance;
    }

    public void setIntValue(String key, int value) {
        if (preferences != null)
            preferences.edit().putInt(key, value).commit();
    }

    public int getIntValue(String key, int defaultValue) {
        if (preferences != null)
            return preferences.getInt(key, defaultValue);
        else
            return defaultValue;
    }

    public void setStringValue(String key, String value) {
        if (preferences != null)
            preferences.edit().putString(key, value).commit();
    }

    public String getStringValue(String key, String defaultValue) {
        if (preferences != null)
            return preferences.getString(key, defaultValue);
        else
            return defaultValue;
    }

    public void setBooleanValue(String key, Boolean value) {
        if (preferences != null)
            preferences.edit().putBoolean(key, value).commit();
    }

    public Boolean getBooleanValue(String key, Boolean defaultValue) {
        if (preferences != null)
            return preferences.getBoolean(key, defaultValue);
        else
            return defaultValue;
    }

    public void setFloatValue(String key, Float value) {
        if (preferences != null)
            preferences.edit().putFloat(key, value).commit();
    }

    public Float getFlotValue(String key, Float defaultValue) {
        if (preferences != null)
            return preferences.getFloat(key, defaultValue);
        else
            return defaultValue;
    }

    public void setLongValue(String key, Long value) {
        if (preferences != null)
            preferences.edit().putLong(key, value).commit();
    }

    public long getLongValue(String key, Long defaultValue) {
        if (preferences != null)
            return preferences.getLong(key, defaultValue);
        else
            return defaultValue;
    }

    public synchronized void saveDataIntoPreferences(Object dataObject, String key) {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(dataObject);
            SharedPreferences.Editor prefsEditor = preferences.edit();
            prefsEditor.putString(key, json);
            prefsEditor.commit();
        } catch (Exception ignored) {

        }

    }

    /**
     * Return the dataObject of type typeOfT class if successfull. if fail to get data from SharedPreferences then returns null
     */
    public synchronized <T> T getDataFromPreferences(String key, Type typeOfT) {
        T dataObject = null;
        try {
            Gson gson = new Gson();
            String jsonString = preferences.getString(key, "");
            dataObject = gson.fromJson(jsonString, typeOfT);
        } catch (Exception ignored) {
        }

        return dataObject;
    }

    public synchronized <T extends Object> T getObjectFromGsonString(
            String key, Class<T> objectClass) {
        try {
            String gsonString = preferences.getString(key, "");
            if (TextUtils.isEmpty(gsonString)) {
                return null;
            } else {
                return new GsonBuilder().create().fromJson(gsonString, objectClass);
            }
        } catch (Exception e) {
            return null;
        }
    }
}
