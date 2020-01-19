package com.alfinapp.ui.login;

import android.content.Context;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.alfinapp.R;
import com.alfinapp.utils.listener.LoginCallbackListener;

public class LoginFragment extends Fragment implements View.OnClickListener {
    private LoginCallbackListener loginCallbackListener;
    private EditText phoneEditText;
    private EditText referralEditText;

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
        phoneEditText = rootView.findViewById(R.id.phone_number_edittext);
        referralEditText = rootView.findViewById(R.id.referral_code_ev);
        rootView.findViewById(R.id.button_layout).setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View view) {
        loginDataChanged(phoneEditText.getText().toString(), referralEditText.getText().toString());
    }

    public void loginDataChanged(String username, String referral) {
        if (!isPhoneNumberValid(username)) {
            Toast.makeText(getContext(), getString(R.string.invalid_phonenumber), Toast.LENGTH_SHORT).show();
        } else if (!isRefferalCodeValid(referral)) {
            Toast.makeText(getContext(), getString(R.string.invalid_reffaral), Toast.LENGTH_SHORT).show();
        } else {
            loginCallbackListener.onLoginDone();
        }
    }

    /**
     * @param phoneNumber
     * @return
     */
    private boolean isPhoneNumberValid(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }
        return Patterns.PHONE.matcher(phoneNumber).matches();
    }

    /**
     * @param referralCode
     * @return
     */
    private boolean isRefferalCodeValid(String referralCode) {
        return referralCode != null && referralCode.trim().length() > 5;
    }
}
