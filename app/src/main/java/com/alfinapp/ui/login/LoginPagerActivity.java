package com.alfinapp.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;

import android.content.Intent;
import android.os.Bundle;

import com.alfinapp.R;
import com.alfinapp.data.network.api.APIClient;
import com.alfinapp.data.network.api.APIInterface;
import com.alfinapp.data.network.api.ApiConstants;
import com.alfinapp.data.network.api.VolleyNetworkSingleton;
import com.alfinapp.ui.views.NonSwipeableViewPager;
import com.alfinapp.ui.welcome.WelcomeActivity;
import com.alfinapp.utils.NetworkStatus;
import com.alfinapp.utils.ToolsUtils;
import com.alfinapp.utils.listener.LoginCallbackListener;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginPagerActivity extends AppCompatActivity implements LoginCallbackListener {
    NonSwipeableViewPager nonSwipeableViewPager;
    private int PAGE_COUNT = 2;
    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_pager);

        apiInterface = APIClient.getClient().create(APIInterface.class);
        nonSwipeableViewPager = findViewById(R.id.login_pager);
        /*Set Adapter*/
        nonSwipeableViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return LoginFragment.newInstance();
                } else {
                    return OtpVerifyFragment.newInstance();
                }
            }

            @Override
            public int getCount() {
                return PAGE_COUNT;
            }
        });
    }

    @Override
    public void onLoginDone(String countryCode, String mobileNumber, String referralCode) {
        makeSignUp(countryCode, mobileNumber, referralCode);

    }

    private void makeSignUp(String countryCode, String mobileNumber, String referralCode) {
        if (NetworkStatus.getInstance().isConnected(LoginPagerActivity.this)) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("country_code", countryCode);
                jsonObject.put("mobile", mobileNumber);
                jsonObject.put("joining_referral_code", referralCode);
            } catch (Exception ignored) {

            }

            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, ApiConstants.SIGN_UP_URL, jsonObject,
                    response -> {
                        try {
                            parseSignupResponse(response);
                        } catch (Exception ignored) {
                        }

                    }, error -> {

                if (error instanceof NetworkError) {
                    ToolsUtils.getToolsUtils().showToast(LoginPagerActivity.this, getResources().getString(R.string.no_network_error));
                } else {
                    ToolsUtils.getToolsUtils().showToast(LoginPagerActivity.this, getResources().getString(R.string.sorry_error_found_please_try_again_));

                }
            }) {
                @Override
                public Map<String, String> getHeaders() {
                    HashMap<String, String> headers = new HashMap<>();
                    headers.put("Content-Type", "application/json charset=utf-8");
                    headers.put("Accept", "application/json");
                    return headers;
                }
            };
            jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(12000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VolleyNetworkSingleton.getInstance(LoginPagerActivity.this).addToRequestQueue(jsonObjReq);
        } else {
            ToolsUtils.getToolsUtils().showToast(LoginPagerActivity.this, getResources().getString(R.string.no_network_error));
        }
    }

    private void parseSignupResponse(JSONObject response) {
        // after response
        nonSwipeableViewPager.setCurrentItem(1);
    }

    @Override
    public void onOtpVerify(String application, String mobileNumber, String otp) {
        makeValidateOtp(application, mobileNumber, otp);

    }

    private void makeValidateOtp(String application, String mobileNumber, String otp) {
        if (NetworkStatus.getInstance().isConnected(LoginPagerActivity.this)) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("application", application);
                jsonObject.put("mobile", mobileNumber);
                jsonObject.put("otp", otp);
            } catch (Exception ignored) {

            }

            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, ApiConstants.VALIDATE_OTP_URL, jsonObject,
                    response -> {
                        try {
                            parseOtpResponse(response);
                        } catch (Exception ignored) {
                        }

                    }, error -> {

                if (error instanceof NetworkError) {
                    ToolsUtils.getToolsUtils().showToast(LoginPagerActivity.this, getResources().getString(R.string.no_network_error));
                } else {
                    ToolsUtils.getToolsUtils().showToast(LoginPagerActivity.this, getResources().getString(R.string.sorry_error_found_please_try_again_));

                }
            }) {
                @Override
                public Map<String, String> getHeaders() {
                    HashMap<String, String> headers = new HashMap<>();
                    headers.put("Content-Type", "application/json charset=utf-8");
                    headers.put("Accept", "application/json");
                    return headers;
                }
            };
            jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(12000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VolleyNetworkSingleton.getInstance(LoginPagerActivity.this).addToRequestQueue(jsonObjReq);
        } else {
            ToolsUtils.getToolsUtils().showToast(LoginPagerActivity.this, getResources().getString(R.string.no_network_error));
        }
    }

    private void parseOtpResponse(JSONObject response) {
        getUserFromServer();
        startActivity(new Intent(LoginPagerActivity.this, WelcomeActivity.class));
        finish();
    }

    private void getUserFromServer() {

    }
}
