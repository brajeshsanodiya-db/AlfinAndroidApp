package com.alfinapp.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;

import android.content.Intent;
import android.os.Bundle;

import com.alfinapp.R;
import com.alfinapp.ui.views.NonSwipeableViewPager;
import com.alfinapp.ui.welcome.WelcomeActivity;
import com.alfinapp.utils.listener.LoginCallbackListener;

public class LoginPagerActivity extends AppCompatActivity implements LoginCallbackListener {
    NonSwipeableViewPager nonSwipeableViewPager;
    private int PAGE_COUNT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_pager);
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
    public void onLoginDone() {
        nonSwipeableViewPager.setCurrentItem(1);
    }

    @Override
    public void onOtpVerify() {
        startActivity(new Intent(LoginPagerActivity.this, WelcomeActivity.class));
        finish();
    }
}
