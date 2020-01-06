package com.alfinapp.ui.home.passbook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.alfinapp.R;


public class PassbookFragment extends Fragment {

    private PassbookViewModel passbookViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        passbookViewModel = ViewModelProviders.of(this).get(PassbookViewModel.class);
        View root = inflater.inflate(R.layout.fragment_passbook, container, false);
        return root;
    }
}