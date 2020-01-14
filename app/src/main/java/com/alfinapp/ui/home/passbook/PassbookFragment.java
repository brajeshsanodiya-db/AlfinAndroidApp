package com.alfinapp.ui.home.passbook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.alfinapp.R;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class PassbookFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        PassbookViewModel passbookViewModel = ViewModelProviders.of(this).get(PassbookViewModel.class);
        View root = inflater.inflate(R.layout.fragment_passbook, container, false);


        setUpPassbookPager(root);
        TextView alfinCoinTextView = root.findViewById(R.id.alfinCoin);
        TextView transactionHistoryTextView = root.findViewById(R.id.transactionHistory);
        alfinCoinTextView.setOnClickListener(view -> {
            alfinCoinTextView.setBackgroundResource(R.drawable.passbook_tab_select_bg);
            alfinCoinTextView.setTextColor(ContextCompat.getColor(getActivity(), R.color.passbook_select_color));
            transactionHistoryTextView.setBackgroundResource(R.drawable.passbook_tab_normal_bg);
            transactionHistoryTextView.setTextColor(ContextCompat.getColor(getActivity(), R.color.passbook_normal_color));
//            navController.navigate(R.id.alfinCoinFragment);
            changeFragment(1);
        });

        transactionHistoryTextView.setOnClickListener(view -> {
            transactionHistoryTextView.setBackgroundResource(R.drawable.passbook_tab_select_bg);
            transactionHistoryTextView.setTextColor(ContextCompat.getColor(getActivity(), R.color.passbook_select_color));
            alfinCoinTextView.setBackgroundResource(R.drawable.passbook_tab_normal_bg);
            alfinCoinTextView.setTextColor(ContextCompat.getColor(getActivity(), R.color.passbook_normal_color));
//            navController.navigate(R.id.transactionHistoryFragment);
            changeFragment(2);
        });
        return root;
    }

    private void changeFragment(int i) {
        Fragment fragment;
        if (i == 1) {
            fragment = new AlfinCoinFragment();
        } else {
            fragment = new TransactionHistoryFragment();
        }
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.passbook_navigation, fragment, fragment.toString());
        fragmentTransaction.commit();
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