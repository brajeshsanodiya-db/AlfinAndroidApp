package com.alfinapp.ui.login;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.alfinapp.R;
import com.alfinapp.utils.listener.LoginCallbackListener;
import com.mukesh.OtpView;

import java.util.Objects;

public class OtpVerifyFragment extends Fragment implements View.OnClickListener {

    private LoginCallbackListener loginCallbackListener;
    private OtpView otpView;

    public static Fragment newInstance() {
        return new OtpVerifyFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        loginCallbackListener = (LoginCallbackListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_otp_verify, container, false);
        rootView.findViewById(R.id.submit_layout).setOnClickListener(this);
        rootView.findViewById(R.id.resend_otp).setOnClickListener(this);
        otpView = rootView.findViewById(R.id.otpView);
        otpView.setOtpCompletionListener(this::checkOtpAndSubmit);
        otpView.setFocusableInTouchMode(true);
        return rootView;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.submit_layout) {
            checkOtpAndSubmit(Objects.requireNonNull(otpView.getText()).toString());
        } else if (view.getId() == R.id.resend_otp) {
            loginCallbackListener.onResendOtp();
        }
    }

    private void checkOtpAndSubmit(String otp) {
        if (TextUtils.isEmpty(otp)) {
            Toast.makeText(getContext(), "OTP is not valid !!", Toast.LENGTH_SHORT).show();
        } else {
            loginCallbackListener.onOtpVerify( "iddpKv0eW1ZW76htUK9NWcDyfNlHLaxeLTPbrm0u",otp);
        }
    }
}
