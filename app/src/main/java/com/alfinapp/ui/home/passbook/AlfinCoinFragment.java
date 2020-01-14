package com.alfinapp.ui.home.passbook;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alfinapp.R;

public class AlfinCoinFragment extends Fragment {

    private AlfinCoinViewModel mViewModel;

    public static AlfinCoinFragment newInstance() {
        return new AlfinCoinFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.alfin_coin_fragment, container, false);
        mViewModel = ViewModelProviders.of(this).get(AlfinCoinViewModel.class);

        /*Contest Winner*/
        RecyclerView contest_winner_rv = root.findViewById(R.id.alfin_coin_history_recyclerview);
        contest_winner_rv.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        contest_winner_rv.setAdapter(mViewModel.getAlfinCoinHistoryAdapter());

        return root;
    }

}
