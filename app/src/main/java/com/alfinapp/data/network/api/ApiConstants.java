package com.alfinapp.data.network.api;

public interface ApiConstants {
    //    String BASE_URL = "http://api.alfin.app/v1";
    String BASE_URL = "http://15.206.52.202:8000/api/";
    String SIGN_UP_URL = BASE_URL + "auth/register/";
    String VALIDATE_OTP_URL = BASE_URL + "auth/validate-otp/";
    String REQUEST_OTP_URL = BASE_URL + "auth/request-otp/";
    String GET_USER_URL = BASE_URL + "user/profile/";

}
