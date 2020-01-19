package com.alfinapp.ui.home.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.alfinapp.R;
import com.alfinapp.ui.views.WrapContentHeightViewPager;
import com.alfinapp.utils.ToolsUtils;
import com.shuhart.bubblepagerindicator.BubblePageIndicator;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class HomeFragment extends Fragment implements View.OnClickListener {

    private WrapContentHeightViewPager viewPager;
    private float RATIO_SCALE = 1.90f;
    private View root;
    private NavController navController;
    private int finalPaddingRequired;

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
        viewPager.setVisibility(View.VISIBLE);
        viewPager.setClipToPadding(false);
        viewPager.setPageMargin(24);
        viewPager.setPadding(160, 0, 160, 0);
        viewPager.setOffscreenPageLimit(3);
        addData();
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

    List<String> homePagerList = new ArrayList<>();

    private void addData() {
        homePagerList.clear();
        homePagerList.add("ABCD");
        homePagerList.add("ABCD");
        homePagerList.add("ABCD");
        homePagerList.add("ABCD");
        homePagerList.add("ABCD");
        homePagerList.add("ABCD");
    }

    class HomePagerAdapter extends PagerAdapter {

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.pageritem_home, container, false);
            TextView bidNowTextView = layout.findViewById(R.id.bid_now_text_view);
            bidNowTextView.setOnClickListener(view -> createContestBid());
            container.addView(layout);
            return layout;
        }

        @Override
        public int getCount() {
            return homePagerList.size();
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

    private void createContestBid() {
        final AlertDialog.Builder exitDialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = this.getLayoutInflater();
        @SuppressLint("InflateParams") View dialogView = inflater.inflate(R.layout.dialog_create_contest_bid, null);
        exitDialogBuilder.setView(dialogView);
        ImageView closeDialogImageView = dialogView.findViewById(R.id.close_dialog_image_view);
        TextView bidNowTextView = dialogView.findViewById(R.id.bid_now_text_view);

        final AlertDialog bidNowDialog = exitDialogBuilder.create();
        Objects.requireNonNull(bidNowDialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        bidNowDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        bidNowDialog.setCancelable(true);

        if (closeDialogImageView != null)
            closeDialogImageView.setOnClickListener(v -> bidNowDialog.dismiss());
        if (bidNowTextView != null)
            bidNowTextView.setOnClickListener(v -> bidNowDialog.dismiss());
        bidNowDialog.show();
    }

}