package com.alfinapp.ui.login;

import androidx.annotation.Nullable;

/**
 * Data validation state of the login form.
 */
class LoginFormState {
    @Nullable
    private Integer phoneNumberError;
    @Nullable
    private Integer referralCodeError;
    private boolean isDataValid;

    LoginFormState(@Nullable Integer phoneNumberError, @Nullable Integer referralCodeError) {
        this.phoneNumberError = phoneNumberError;
        this.referralCodeError = referralCodeError;
        this.isDataValid = false;
    }

    LoginFormState(boolean isDataValid) {
        this.phoneNumberError = null;
        this.referralCodeError = null;
        this.isDataValid = isDataValid;
    }

    @Nullable
    Integer getUsernameError() {
        return phoneNumberError;
    }

    @Nullable
    Integer getPasswordError() {
        return referralCodeError;
    }

    boolean isDataValid() {
        return isDataValid;
    }
}
