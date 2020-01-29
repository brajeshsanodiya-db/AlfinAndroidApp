package com.alfinapp.utils.listener;

public interface LoginCallbackListener {

    void onSignup(String mobileNumber, String referralCode);

    void onResendOtp();

    void onOtpVerify(String application, String otp);


}
