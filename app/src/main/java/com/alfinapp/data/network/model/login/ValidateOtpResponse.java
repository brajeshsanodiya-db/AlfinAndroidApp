package com.alfinapp.data.network.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ValidateOtpResponse {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("access_token")
    @Expose
    private String accessToken;
    @SerializedName("refresh_token")
    @Expose
    private String refreshToken;
    @SerializedName("expires_in")
    @Expose
    private String expiresIn;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

}