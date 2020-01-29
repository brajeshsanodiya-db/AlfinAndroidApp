package com.alfinapp.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.alfinapp.R;
import com.alfinapp.data.local.AlfinPreferences;
import com.alfinapp.ui.appintro.AppIntroActivity;
import com.alfinapp.ui.home.HomeActivity;
import com.alfinapp.utils.AlfinConstants;
import com.alfinapp.utils.AppSignatureHelper;
import com.alfinapp.utils.ToolsUtils;

public class SplashActivity extends AppCompatActivity {
    AlfinPreferences alfinPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        alfinPreferences = AlfinPreferences.getInstance(SplashActivity.this);
        String selectedLanguage = alfinPreferences.getStringValue(AlfinConstants.AppPrefKeys.APP_LANGUAGE_DONE, AlfinConstants.LocaleValue.ENGLISH);
        if (!TextUtils.isEmpty(selectedLanguage)) {
            if (selectedLanguage.equalsIgnoreCase(AlfinConstants.LocaleValue.HINDI)) {
                ToolsUtils.getToolsUtils().setLocale(this, AlfinConstants.LocaleValue.HINDI);
            } else {
                ToolsUtils.getToolsUtils().setLocale(this, AlfinConstants.LocaleValue.ENGLISH);
            }
        }

        // Log.d("Hash=> ", "== " + new AppSignatureHelper(SplashActivity.this).getAppSignatures().toString());//TODO:: app signature for otp
        new Handler().postDelayed(this::init, 1000);
    }

    private void init() {
        boolean appIntroDone = alfinPreferences.getBooleanValue(AlfinConstants.AppPrefKeys.APP_INTRO_DONE, false);
        if (appIntroDone) {
            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
        } else {
            startActivity(new Intent(SplashActivity.this, AppIntroActivity.class));
        }
        finish();
    }
}
