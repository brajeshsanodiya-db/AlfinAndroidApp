package com.alfinapp.ui.login;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.alfinapp.R;
import com.alfinapp.utils.listener.LoginCallbackListener;

public class LoginFragment extends Fragment implements View.OnClickListener {
    private LoginCallbackListener loginCallbackListener;

    public LoginFragment() {
    }

    public static Fragment newInstance() {
        return new LoginFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        rootView.findViewById(R.id.button_layout).setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View view) {
        loginCallbackListener.onLoginDone();
    }
}
