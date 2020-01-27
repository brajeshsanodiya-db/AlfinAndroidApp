package com.alfinapp.utils.listener;

public interface LoginCallbackListener {
    void onLoginDone(String countryCode, String mobileNumber, String referralCode);

    void onOtpVerify(String application, String mobileNumber, String otp);
}
