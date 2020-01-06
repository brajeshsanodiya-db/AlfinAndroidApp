package com.alfinapp.ui.appintro;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.alfinapp.R;
import com.alfinapp.data.local.AlfinPreferences;
import com.alfinapp.ui.languageChoose.LanguageChoseFragment;
import com.alfinapp.ui.login.LoginPagerActivity;
import com.alfinapp.ui.views.NonSwipeableViewPager;
import com.alfinapp.utils.AlfinConstants;
import com.shuhart.bubblepagerindicator.BubblePageIndicator;

public class AppIntroActivity extends AppCompatActivity implements View.OnClickListener {
    int pagePosition = 0;
    private NonSwipeableViewPager viewPager;
    private final int PAGE_COUNT = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_intro_activity);

        startAppIntro();

    }

    private void startAppIntro() {
        /*View Init*/
        viewPager = findViewById(R.id.pager);
        TextView btnNext = findViewById(R.id.btn_next);
        BubblePageIndicator indicator = findViewById(R.id.indicator);
        btnNext.setOnClickListener(this::onClick);



        /*Page Change Listener*/
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pagePosition = position;
                if (position == PAGE_COUNT - 1) {
                    btnNext.setText(getString(R.string.join_alfin_msg));
                } else {
                    btnNext.setText(getString(R.string.next_str));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        /*Set Adapter*/
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return LanguageChoseFragment.newInstance();
                } else {
                    return AppIntroFragment.newInstance(position);
                }
            }

            @Override
            public int getCount() {
                return PAGE_COUNT;
            }
        });
        indicator.setViewPager(viewPager);
        viewPager.setCurrentItem(0);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_next) {
            if (pagePosition == 0) {
                String languageSelected = AlfinPreferences.getInstance(AppIntroActivity.this).getStringValue(AlfinConstants.AppPrefKeys.APP_LANGUAGE_DONE, "");
                if (!TextUtils.isEmpty(languageSelected)) {
                    viewPager.setCurrentItem(pagePosition + 1);
                } else {
                    Toast.makeText(AppIntroActivity.this, "Please chose language", Toast.LENGTH_SHORT).show();
                }
            } else {
                if (pagePosition < PAGE_COUNT - 1) {
                    viewPager.setCurrentItem(pagePosition + 1);
                } else {
//                    AlfinPreferences.getInstance(AppIntroActivity.this).setBooleanValue(AlfinConstants.AppPrefKeys.APP_INTRO_DONE, true);
                    startActivity(new Intent(AppIntroActivity.this, LoginPagerActivity.class));
                    finish();
                }
            }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        startAppIntro();
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        startAppIntro();
    }
}
