package com.alfinapp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

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
import com.alfinapp.data.network.model.login.ValidateOtpResponse;
import com.alfinapp.data.network.model.user.UserResponse;
import com.alfinapp.ui.views.NonSwipeableViewPager;
import com.alfinapp.ui.welcome.WelcomeActivity;
import com.alfinapp.utils.AlfinConstants;
import com.alfinapp.utils.NetworkStatus;
import com.alfinapp.utils.ToolsUtils;
import com.alfinapp.utils.listener.LoginCallbackListener;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

public class LoginPagerActivity extends AppCompatActivity implements LoginCallbackListener {
    private NonSwipeableViewPager nonSwipeableViewPager;
    private int PAGE_COUNT = 2;
    private APIInterface apiInterface;
    private String mobileNumber;
    private String refferalCode;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_pager);

        apiInterface = APIClient.getClient().create(APIInterface.class);
        initViews();

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

    private void initViews() {
        nonSwipeableViewPager = findViewById(R.id.login_pager);
        progressBar = findViewById(R.id.progress_bar);
    }

    @Override
    public void onSignup(String mobile, String referral) {
        progressBar.setVisibility(View.VISIBLE);

        new Handler().postDelayed(() -> {
            mobileNumber = mobile;
            refferalCode = referral;
            callSignUp(mobile, referral);
        }, 1000);
    }

    @Override
    public void onResendOtp() {
        progressBar.setVisibility(View.VISIBLE);

        new Handler().postDelayed(() -> {
            callResendOtp(mobileNumber, refferalCode);
        }, 1000);

    }

    @Override
    public void onOtpVerify(String application, String otp) {
        progressBar.setVisibility(View.VISIBLE);

        new Handler().postDelayed(() -> {
            callValidateOtp(application, mobileNumber, otp);
        }, 1000);


    }

    private void callSignUp(String mobileNumber, String referralCode) {
        if (NetworkStatus.getInstance().isConnected(LoginPagerActivity.this)) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("phone_number", mobileNumber);
                jsonObject.put("referral_code", "ASD");
                jsonObject.put("country_code", "91");
            } catch (Exception ignored) {

            }

            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, ApiConstants.SIGN_UP_URL, jsonObject,
                    response -> {
                        try {
                            signupResponse(response);
                        } catch (Exception ignored) {
                        }
                    }, error -> {
                progressBar.setVisibility(View.GONE);
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
        progressBar.setVisibility(View.GONE);

        // after response
        nonSwipeableViewPager.setCurrentItem(1);
    }

    private void callResendOtp(String mobileNumber, String referralCode) {
        if (NetworkStatus.getInstance().isConnected(LoginPagerActivity.this)) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("phone_number", mobileNumber);
            } catch (Exception ignored) {

            }

            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, ApiConstants.REQUEST_OTP_URL, jsonObject,
                    response -> {
                        try {
                            resendResponse(response);
                        } catch (Exception ignored) {
                        }
                    }, error -> {
                progressBar.setVisibility(View.GONE);
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
        progressBar.setVisibility(View.GONE);
        ToolsUtils.getToolsUtils().showToast(LoginPagerActivity.this, "OTP successfully sent to your phone number");
    }

    private void callValidateOtp(String application, String mobileNumber, String otp) {
        if (NetworkStatus.getInstance().isConnected(LoginPagerActivity.this)) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("application", application);
                jsonObject.put("phone_number", mobileNumber);
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
                progressBar.setVisibility(View.GONE);
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
        progressBar.setVisibility(View.GONE);
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
        UserResponse userResponse = new Gson().fromJson(response.toString(), UserResponse.class);
        startActivity(new Intent(LoginPagerActivity.this, WelcomeActivity.class));
        finish();
    }
}
