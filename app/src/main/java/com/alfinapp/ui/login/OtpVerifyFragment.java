package com.alfinapp.ui.login;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alfinapp.R;
import com.alfinapp.utils.listener.LoginCallbackListener;

public class OtpVerifyFragment extends Fragment implements View.OnClickListener {

    private LoginCallbackListener loginCallbackListener;

    public static Fragment newInstance() {
        return new OtpVerifyFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        loginCallbackListener = (LoginCallbackListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_otp_verify, container, false);
        rootView.findViewById(R.id.submit_layout).setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View view) {
        loginCallbackListener.onOtpVerify();
    }
}
