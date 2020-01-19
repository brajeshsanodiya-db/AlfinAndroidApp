package com.alfinapp.ui.login;

import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alfinapp.R;
import com.alfinapp.data.LoginRepository;
import com.alfinapp.data.Result;
import com.alfinapp.data.db.entity.LoggedInUser;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {
        // can be launched in a separate asynchronous job
        Result<LoggedInUser> result = loginRepository.login(username, password);

        if (result instanceof Result.Success) {
            LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getDisplayName())));
        } else {
            loginResult.setValue(new LoginResult(R.string.login_failed));
        }
    }

    /**
     *
     * @param username
     * @param password
     */
    public void loginDataChanged(String username, String password) {
        if (!isPhoneNumberValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_phonenumber, null));
        } else if (!isRefferalCodeValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_reffaral));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    /**
     *
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
     *
     * @param referralCode
     * @return
     */
    private boolean isRefferalCodeValid(String referralCode) {
        return referralCode != null && referralCode.trim().length() > 5;
    }
}
