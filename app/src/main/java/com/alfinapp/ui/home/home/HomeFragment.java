package com.alfinapp.ui.home.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.alfinapp.R;
import com.shuhart.bubblepagerindicator.BubblePageIndicator;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ViewPager viewPager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        setItemPager(root);


        return root;
    }

    private void setItemPager(View root) {
        if (viewPager == null) {

            // Initializing view pager
            viewPager = root.findViewById(R.id.item_pager);

            // Disable clip to padding
            viewPager.setClipToPadding(false);
            // set padding manually, the more you set the padding the more you see of prev & next page
            viewPager.setPadding(120, 0, 120, 0);
            // sets a margin b/w individual pages to ensure that there is a gap b/w them
            viewPager.setPageMargin(10);

        }

        viewPager.setAdapter(new PagerAdapter() {
            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.pageritem_home, container, false);
                container.addView(layout);
                return layout;
            }

            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }
            @Override
            public void destroyItem(ViewGroup collection, int position, Object view) {
                collection.removeView((View) view);
            }
        });
        BubblePageIndicator indicator = root.findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);
    }
}