package com.alfinapp.ui.appintro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.alfinapp.R;
import com.alfinapp.ui.views.fontTextView.LatoMediumTextView;

public class AppIntroFragment extends Fragment {

    private AppIntroViewModel mViewModel;
    private int position;

    private LatoMediumTextView message1, message2, message3;

    public static AppIntroFragment newInstance(int position) {
        AppIntroFragment appIntroFragment = new AppIntroFragment();
        Bundle argument = new Bundle();
        argument.putInt("position", position);
        appIntroFragment.setArguments(argument);
        return appIntroFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle argument = getArguments();
        position = argument.getInt("position");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.app_intro_fragment, container, false);
        message1 = rootView.findViewById(R.id.message1);
        message2 = rootView.findViewById(R.id.message2);
        message3 = rootView.findViewById(R.id.message3);
        if (position == 1) {
            message1.setVisibility(View.VISIBLE);
            message2.setVisibility(View.GONE);
            message3.setVisibility(View.GONE);
        } else if (position == 2) {
            message1.setVisibility(View.GONE);
            message2.setVisibility(View.VISIBLE);
            message3.setVisibility(View.GONE);
        } else {
            message1.setVisibility(View.GONE);
            message2.setVisibility(View.GONE);
            message3.setVisibility(View.VISIBLE);
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AppIntroViewModel.class);

        // TODO: Use the ViewModel
    }

}
