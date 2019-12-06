package com.alfinapp.ui.appintro;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.alfinapp.R;

public class AppIntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_intro_activity);

        ViewPager viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                String message = "";
                if (position == 0) {
                    message = "Get Rewarded with Alfin Coins on your savings account balance and save money anywhere.";
                } else if (position == 1) {
                    message = "Use Alfin Coins to win exciting prizes for FREE every month";
                } else {
                    message = "Check your savings account Transaction without any Banking Password or ATM PIN. No need to Visit Bank Branched now.";
                }
                return AppIntroFragment.newInstance(message);
            }

            @Override
            public int getCount() {
                return 3;
            }
        });
    }
}
