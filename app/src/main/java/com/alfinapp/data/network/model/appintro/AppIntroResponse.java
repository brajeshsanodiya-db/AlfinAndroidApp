package com.alfinapp.data.network.model.appintro;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Lalit Goswami on 03/12/2019.
 */

public class AppIntroResponse {

    @Expose
    @SerializedName("app_intro")
    private List<AppIntro> appIntroList;

    public List<AppIntro> getAppIntroList() {
        return appIntroList;
    }
}
