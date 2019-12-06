package com.alfinapp.data.network.api;


import com.alfinapp.data.network.model.AppIntroResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiHelper {

    @GET() Call<AppIntroResponse> getAppIntro(String url);


}
