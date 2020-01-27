package com.alfinapp.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpRequestInfo {

    @SerializedName("phone_number")
    @Expose
    private String mobile;
    @SerializedName("referral_code")
    @Expose
    private String joiningReferralCode;
    @SerializedName("country_code")
    @Expose
    private String countryCode;

    public SignUpRequestInfo(String mobile, String joiningReferralCode, String countryCode) {
        this.mobile = mobile;
        this.joiningReferralCode = joiningReferralCode;
        this.countryCode = countryCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getJoiningReferralCode() {
        return joiningReferralCode;
    }

    public void setJoiningReferralCode(String joiningReferralCode) {
        this.joiningReferralCode = joiningReferralCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

}