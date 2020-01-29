package com.alfinapp.ui.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.alfinapp.R;
import com.alfinapp.data.local.AlfinPreferences;
import com.alfinapp.data.network.api.APIClient;
import com.alfinapp.data.network.api.APIInterface;
import com.alfinapp.data.network.api.ApiConstants;
import com.alfinapp.data.network.api.VolleyNetworkSingleton;
import com.alfinapp.data.network.model.ValidateOtpResponse;
import com.alfinapp.ui.views.NonSwipeableViewPager;
import com.alfinapp.ui.welcome.WelcomeActivity;
import com.alfinapp.utils.AlfinConstants;
import com.alfinapp.utils.NetworkStatus;
import com.alfinapp.utils.ToolsUtils;
import com.alfinapp.utils.listener.LoginCallbackListener;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Map;

public class LoginPagerActivity extends AppCompatActivity implements LoginCallbackListener {
    NonSwipeableViewPager nonSwipeableViewPager;
    private int PAGE_COUNT = 2;
    APIInterface apiInterface;
    private String mobileNumber;
    private String refferalCode;

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
    public void onSignup(String mobileNumber, String referralCode) {
        this.mobileNumber = mobileNumber;
        this.refferalCode = referralCode;
        callSignUp(mobileNumber, referralCode);
    }

    @Override
    public void onResendOtp() {
        callResendOtp(mobileNumber, refferalCode);
    }

    @Override
    public void onOtpVerify(String application, String otp) {
        callValidateOtp(application, mobileNumber, otp);

    }

    private void callSignUp(String mobileNumber, String referralCode) {
        if (NetworkStatus.getInstance().isConnected(LoginPagerActivity.this)) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("country_code", "IND");
                jsonObject.put("mobile", mobileNumber);
                jsonObject.put("referral_code", "ASD");
            } catch (Exception ignored) {

            }

            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, ApiConstants.SIGN_UP_URL, jsonObject,
                    response -> {
                        try {
                            signupResponse(response);
                        } catch (Exception ignored) {
                        }
                    }, error -> {
                signupResponse(null);

                if (error instanceof NetworkError) {
                    ToolsUtils.getToolsUtils().showToast(LoginPagerActivity.this, getResources().getString(R.string.no_network_error));
                } else {
                    ToolsUtils.getToolsUtils().showToast(LoginPagerActivity.this, getResources().getString(R.string.sorry_error_found_please_try_again_));
                }
            }) {
                @Override
                public Map<String, String> getHeaders() {
                    return ToolsUtils.getToolsUtils().getApiHeaders(LoginPagerActivity.this);
                }
            };
            jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(12000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VolleyNetworkSingleton.getInstance(LoginPagerActivity.this).addToRequestQueue(jsonObjReq);
        } else {
            ToolsUtils.getToolsUtils().showToast(LoginPagerActivity.this, getResources().getString(R.string.no_network_error));
        }
    }

    private void signupResponse(JSONObject response) {
        // after response
        nonSwipeableViewPager.setCurrentItem(1);
    }

    private void callResendOtp(String mobileNumber, String referralCode) {
        if (NetworkStatus.getInstance().isConnected(LoginPagerActivity.this)) {
            JSONObject jsonObject = new JSONObject();
            try {
//                jsonObject.put("country_code", "IND");
                jsonObject.put("phone_number", mobileNumber);
//                jsonObject.put("referral_code", referralCode);
            } catch (Exception ignored) {

            }

            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, ApiConstants.REQUEST_OTP_URL, jsonObject,
                    response -> {
                        try {
                            resendResponse(response);
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
                    return ToolsUtils.getToolsUtils().getApiHeaders(LoginPagerActivity.this);
                }
            };
            jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(12000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VolleyNetworkSingleton.getInstance(LoginPagerActivity.this).addToRequestQueue(jsonObjReq);
        } else {
            ToolsUtils.getToolsUtils().showToast(LoginPagerActivity.this, getResources().getString(R.string.no_network_error));
        }
    }

    private void resendResponse(JSONObject response) {
        ToolsUtils.getToolsUtils().showToast(LoginPagerActivity.this, "OTP successfully sent to your phone number");
    }


    private void callValidateOtp(String application, String mobileNumber, String otp) {
        if (NetworkStatus.getInstance().isConnected(LoginPagerActivity.this)) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("application", application);
                jsonObject.put("mobile", mobileNumber);
                jsonObject.put("otp", "1111");
            } catch (Exception ignored) {

            }

            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, ApiConstants.VALIDATE_OTP_URL, jsonObject,
                    response -> {
                        try {
                            otpResponse(response);
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
                    return ToolsUtils.getToolsUtils().getApiHeaders(LoginPagerActivity.this);
                }
            };
            jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(12000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VolleyNetworkSingleton.getInstance(LoginPagerActivity.this).addToRequestQueue(jsonObjReq);
        } else {
            ToolsUtils.getToolsUtils().showToast(LoginPagerActivity.this, getResources().getString(R.string.no_network_error));
        }
    }

    private void otpResponse(JSONObject response) {
        ValidateOtpResponse validateOtpResponse = new Gson().fromJson(response.toString(), ValidateOtpResponse.class);
        AlfinPreferences.getInstance(this).setStringValue(AlfinConstants.Authorization.AUTH_TOKEN, validateOtpResponse.getAccessToken());
        AlfinPreferences.getInstance(this).setStringValue(AlfinConstants.Authorization.REFRESH_TOKEN, validateOtpResponse.getRefreshToken());
        AlfinPreferences.getInstance(this).setStringValue(AlfinConstants.Authorization.TOKEN_EXPIRE_ON, validateOtpResponse.getExpiresIn());
        callUserProfile();
    }

    private void callUserProfile() {
        if (NetworkStatus.getInstance().isConnected(LoginPagerActivity.this)) {
            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, ApiConstants.GET_USER_URL, null,
                    response -> {
                        try {
                            parseUserProfile(response);
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
                    return ToolsUtils.getToolsUtils().getApiHeaders(LoginPagerActivity.this);
                }
            };
            jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(12000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VolleyNetworkSingleton.getInstance(LoginPagerActivity.this).addToRequestQueue(jsonObjReq);
        } else {
            ToolsUtils.getToolsUtils().showToast(LoginPagerActivity.this, getResources().getString(R.string.no_network_error));
        }

    }

    private void parseUserProfile(JSONObject response) {
        startActivity(new Intent(LoginPagerActivity.this, WelcomeActivity.class));
        finish();
    }
}
