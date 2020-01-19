package com.alfinapp.ui.home.contests;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alfinapp.R;

import java.util.Objects;


public class ContestFragment extends Fragment implements View.OnClickListener {

    private ContestsViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(ContestsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_contest, container, false);

        setToolbar(root);

        /*Contest Winner*/
        RecyclerView contest_winner_rv = root.findViewById(R.id.contest_winner_rv);
        contest_winner_rv.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        contest_winner_rv.setAdapter(dashboardViewModel.getContestWinnerAdapter());

        /*Active Bids*/
        RecyclerView active_bids_rv = root.findViewById(R.id.active_bids_rv);
        active_bids_rv.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        active_bids_rv.setAdapter(dashboardViewModel.getActiveBidsAdapter());

        return root;
    }

    private NavController navController;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.nav_host_fragment);

    }

    private void setToolbar(View rootView) {
        ImageView backButtonImageView = rootView.findViewById(R.id.back_button);
        backButtonImageView.setOnClickListener(this);
        TextView titleTextView = rootView.findViewById(R.id.text_view_title);
        titleTextView.setText(Objects.requireNonNull(getContext()).getString(R.string.title_contest));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_button:
                navController.navigateUp();
                break;
        }
    }
}