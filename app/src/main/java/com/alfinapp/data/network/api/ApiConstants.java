package com.alfinapp.data.network.api;

public interface ApiConstants {
    //    String BASE_URL = "https://alfin.app/api/";
    //    String BASE_URL = "https://15.206.52.202:8000/api/";


    String BASE_URL = "https://staging.alfin.app/api/";
    String SIGN_UP_URL = BASE_URL + "auth/register/";
    String VALIDATE_OTP_URL = BASE_URL + "auth/validate-otp/";
    String REQUEST_OTP_URL = BASE_URL + "auth/request-otp/";
    String GET_USER_URL = BASE_URL + "user/profile/";

}
