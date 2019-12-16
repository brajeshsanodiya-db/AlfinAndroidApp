package com.alfinapp.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.alfinapp.R;
import com.alfinapp.ui.home.HomeActivity;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private LoginViewModel loginViewModel;
    private LinearLayout login_layout;
    private LinearLayout otp_layout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText phoneNumberEditText = findViewById(R.id.username);
        final EditText refferalEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final Button submitButton = findViewById(R.id.submit);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        login_layout = findViewById(R.id.login_layout);
        otp_layout = findViewById(R.id.otp_layout);
        loginButton.setEnabled(true);

        loginButton.setOnClickListener(this::onClick);
        submitButton.setOnClickListener(this::onClick);

        loginViewModel.getLoginFormState().observe(this, loginFormState -> {
            if (loginFormState == null) {
                return;
            }
            loginButton.setEnabled(loginFormState.isDataValid());
            if (loginFormState.getUsernameError() != null) {
                phoneNumberEditText.setError(getString(loginFormState.getUsernameError()));
            }
            if (loginFormState.getPasswordError() != null) {
                refferalEditText.setError(getString(loginFormState.getPasswordError()));
            }
        });

        loginViewModel.getLoginResult().observe(this, loginResult -> {
            if (loginResult == null) {
                return;
            }
            loadingProgressBar.setVisibility(View.GONE);
            if (loginResult.getError() != null) {
                showLoginFailed(loginResult.getError());
            }
            if (loginResult.getSuccess() != null) {
                updateUiWithUser(loginResult.getSuccess());
            }
            setResult(Activity.RESULT_OK);

            //Complete and destroy login activity once successful
            finish();
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(phoneNumberEditText.getText().toString(),
                        refferalEditText.getText().toString());
            }
        };
        phoneNumberEditText.addTextChangedListener(afterTextChangedListener);
        refferalEditText.addTextChangedListener(afterTextChangedListener);

        refferalEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loginViewModel.login(phoneNumberEditText.getText().toString(),
                        refferalEditText.getText().toString());
            }
            return false;
        });

    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
//                loadingProgressBar.setVisibility(View.VISIBLE);
                login_layout.setVisibility(View.GONE);
                otp_layout.setVisibility(View.VISIBLE);

//            loginViewModel.login(phoneNumberEditText.getText().toString(),
//                    refferalEditText.getText().toString());
                break;
            case R.id.submit:
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                finish();
                break;
        }

    }
}
