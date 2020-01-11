package com.alfinapp.ui.home.profile;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alfinapp.R;

import java.util.Objects;

public class ProfileFragment extends Fragment implements View.OnClickListener, EditProfileDialogFragment.Listener {

    private ProfileViewModel mViewModel;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.profile_fragment, container, false);
        setToolbar(rootView);
        return rootView;
    }

    private void setToolbar(View rootView) {
        ImageView backButtonImageView = rootView.findViewById(R.id.back_button);
        backButtonImageView.setOnClickListener(this);
        TextView titleTextView = rootView.findViewById(R.id.text_view_title);
        rootView.findViewById(R.id.edit_profile).setOnClickListener(this);
        titleTextView.setText(Objects.requireNonNull(getContext()).getString(R.string.title_profile));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit_profile:
                showBottomSheet();
                break;

        }
    }

    private void showBottomSheet() {
        EditProfileDialogFragment itemListDialogFragment =  EditProfileDialogFragment.newInstance();
        itemListDialogFragment.show(getChildFragmentManager(), "");
    }

    @Override
    public void onItemClicked(int position) {

    }
}
