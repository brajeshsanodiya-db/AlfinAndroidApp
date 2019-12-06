package com.alfinapp.ui.appintro;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alfinapp.R;

public class AppIntroFragment extends Fragment {

    private AppIntroViewModel mViewModel;
    private String messageText;

    public static AppIntroFragment newInstance(String message) {
        AppIntroFragment appIntroFragment = new AppIntroFragment();
        Bundle argument = new Bundle();
        argument.putString("message", message);
        appIntroFragment.setArguments(argument);
        return appIntroFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle argument = getArguments();
        messageText = argument.getString("message");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.app_intro_fragment, container, false);
        ;
        ((TextView) rootView.findViewById(R.id.message)).setText(messageText);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AppIntroViewModel.class);

        // TODO: Use the ViewModel
    }

}
