package com.alfinapp.ui.home.home;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.alfinapp.R;
import com.alfinapp.ui.views.WrapContentHeightViewPager;
import com.shuhart.bubblepagerindicator.BubblePageIndicator;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;


public class HomeFragment extends Fragment implements View.OnClickListener {

    private WrapContentHeightViewPager viewPager;
    private float RATIO_SCALE = 1.90f;
    private View root;
    private NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        HomeViewModel homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);
        root.findViewById(R.id.toolbar_notification).setOnClickListener(this);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

    }

    @Override
    public void onResume() {
        super.onResume();
        setItemPager(root);

    }

    private void setItemPager(View root) {

        // Initializing view pager
        viewPager = root.findViewById(R.id.item_pager);

        viewPager.setClipToPadding(false);
        viewPager.setPageMargin(24);
        viewPager.setPadding(160, 0, 160, 0);
        viewPager.setOffscreenPageLimit(3);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                ViewGroup sampleFragment = ((HomePagerAdapter) Objects.requireNonNull(viewPager.getAdapter())).getRegisteredFragment(position);
                float scale = 1 - (positionOffset * RATIO_SCALE);
                sampleFragment.setScaleY(scale);
                sampleFragment.invalidate();
                if (position + 1 < viewPager.getAdapter().getCount()) {
                    sampleFragment = ((HomePagerAdapter) viewPager.getAdapter()).getRegisteredFragment(position + 1);
                    scale = positionOffset * RATIO_SCALE + (1 - RATIO_SCALE);
                    sampleFragment.setScaleY(scale);
                    sampleFragment.invalidate();
                }
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    ViewGroup fragment = ((HomePagerAdapter) Objects.requireNonNull(viewPager.getAdapter())).getRegisteredFragment(viewPager.getCurrentItem());
                    fragment.setScaleY(1);
                    fragment.invalidate();
                    if (viewPager.getCurrentItem() > 0) {
                        fragment = ((HomePagerAdapter) viewPager.getAdapter()).getRegisteredFragment(viewPager.getCurrentItem() - 1);
                        fragment.setScaleY(1 - RATIO_SCALE);
                        fragment.invalidate();
                    }

                    if (viewPager.getCurrentItem() + 1 < viewPager.getAdapter().getCount()) {
                        fragment = ((HomePagerAdapter) viewPager.getAdapter()).getRegisteredFragment(viewPager.getCurrentItem() + 1);
                        fragment.setScaleY(1 - RATIO_SCALE);
                    }
                }

            }
        });

        viewPager.setAdapter(new HomePagerAdapter());
        BubblePageIndicator indicator = root.findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.toolbar_notification) {
            navController.navigate(R.id.option_notification);
        }
    }

    class HomePagerAdapter extends PagerAdapter {
        SparseArray<ViewGroup> registeredFragments = new SparseArray<>();

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.pageritem_home, container, false);
            registeredFragments.put(position, layout);
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
        public void destroyItem(ViewGroup collection, int position, @NotNull Object view) {
            registeredFragments.remove(position);

            collection.removeView((View) view);
        }


        ViewGroup getRegisteredFragment(int position) {
            return registeredFragments.get(position);
        }
    }
}