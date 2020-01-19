package com.alfinapp.data.network.api;

import com.alfinapp.data.network.model.AppIntro;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by anupamchugh on 09/01/17.
 */

public interface APIInterface {

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
//    Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);
}

