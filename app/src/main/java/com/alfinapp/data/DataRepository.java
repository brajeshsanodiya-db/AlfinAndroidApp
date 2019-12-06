package com.alfinapp.data;

import androidx.lifecycle.MutableLiveData;

import com.alfinapp.data.network.api.ApiClient;
import com.alfinapp.data.network.api.ApiConstants;
import com.alfinapp.data.network.api.ApiHelper;
import com.alfinapp.data.network.model.AppIntroResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DataRepository {
    private static final String TAG = DataRepository.class.getName();
    private static DataRepository dataRepository;
    private ApiHelper apiHelper;

    public static DataRepository getInstance() {
        if (dataRepository == null) {
            dataRepository = new DataRepository();
        }
        return dataRepository;
    }

    private DataRepository() {
        apiHelper = ApiClient.createService(ApiHelper.class);

    }

    /**
     * For AppIntro
     *
     * @param url
     * @param mutableLiveData
     * @return
     */
    public MutableLiveData<AppIntroResponse> getAppIntroList(String url, final MutableLiveData<AppIntroResponse> mutableLiveData) {
        apiHelper.getAppIntro(ApiConstants.APP_INTRO_URL).enqueue(new Callback<AppIntroResponse>() {
            @Override
            public void onResponse(Call<AppIntroResponse> call,
                                   Response<AppIntroResponse> response) {
                if (response.isSuccessful()) {
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<AppIntroResponse> call, Throwable t) {
                mutableLiveData.setValue(null);
            }
        });
        return mutableLiveData;
    }


}
