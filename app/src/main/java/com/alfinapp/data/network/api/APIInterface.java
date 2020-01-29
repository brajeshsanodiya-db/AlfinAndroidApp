package com.alfinapp.data.network.api;

import com.alfinapp.data.network.model.AppIntro;
import com.alfinapp.data.network.model.SignUpRequestInfo;
import com.alfinapp.data.network.model.UserProfileInfo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface APIInterface {
    @POST("auth/register/")
    Call<AppIntro> doPostSignUp(@Body SignUpRequestInfo signUpRequestInfo);

    @POST("auth/validate-otp/")
    Call<AppIntro> doPostValidateOTP(@Body AppIntro user);

    @POST("auth/request-otp/")
    Call<AppIntro> doPostRequestOTP(@Body AppIntro user);

    @GET("user/profile/")
    Call<UserProfileInfo> doGetUserProfile();

}

/*
//    @GET("/api/unknown")
//    Call<MultipleResource> doGetListResources();

    @POST("/api/users")
    Call<AppIntro> createUser(@Body AppIntro user);

    @POST("/api/users")
    Call<AppIntro> signUp(@Body AppIntro user);

//    @GET("/api/users?")
//    Call<UserList> doGetUserList(@Query("page") String page);
//
//    @FormUrlEncoded
//    @POST("/api/users?")
//    Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);*/