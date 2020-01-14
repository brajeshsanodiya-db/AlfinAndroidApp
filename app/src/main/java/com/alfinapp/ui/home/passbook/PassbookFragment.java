package com.alfinapp.ui.home.passbook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.alfinapp.R;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class PassbookFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        PassbookViewModel passbookViewModel = ViewModelProviders.of(this).get(PassbookViewModel.class);
        View root = inflater.inflate(R.layout.fragment_passbook, container, false);


        setUpPassbookPager(root);
        return root;
    }

    private void setUpPassbookPager(View root) {

        // Initializing view pager
        ViewPager viewPager = root.findViewById(R.id.passbook_pager);
        TabLayout tabs = root.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        viewPager.setAdapter(new PassbookPagerAdapter());
    }

    private List<String> monthList = new ArrayList<>();

    class PassbookPagerAdapter extends PagerAdapter {

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.pageritem_passbook, container, false);
            container.addView(layout);
            return layout;
        }

        @Override
        public int getCount() {
            return getMonthList().size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return getMonthList().get(position);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup collection, int position, @NotNull Object view) {
            collection.removeView((View) view);
        }

    }


    private List<String> getMonthList() {
        if (monthList.isEmpty()) {
            monthList.add("JAN");
            monthList.add("FEB");
            monthList.add("MAR");
            monthList.add("APR");
            monthList.add("MAY");
            monthList.add("JUN");
            monthList.add("JUL");
            monthList.add("AUG");
            monthList.add("SEP");
            monthList.add("OCT");
            monthList.add("NOV");
            monthList.add("DEC");
        }
        return monthList;
    }
}