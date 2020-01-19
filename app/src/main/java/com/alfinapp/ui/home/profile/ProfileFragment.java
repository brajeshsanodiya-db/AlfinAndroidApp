package com.alfinapp.ui.home.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.alfinapp.R;

import java.util.Objects;

public class ProfileFragment extends Fragment implements View.OnClickListener, EditProfileDialogFragment.Listener {

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
        rootView.findViewById(R.id.edit_profile).setOnClickListener(this);
        rootView.findViewById(R.id.terms_and_cond_layout).setOnClickListener(this);
        rootView.findViewById(R.id.privacy_policy_layout).setOnClickListener(this);
        rootView.findViewById(R.id.about_layout).setOnClickListener(this);
        rootView.findViewById(R.id.contact_us_layout).setOnClickListener(this);
        titleTextView.setText(Objects.requireNonNull(getContext()).getString(R.string.title_profile));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        ProfileViewModel mViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_button:
                navController.navigateUp();
                break;
            case R.id.edit_profile:
                showBottomSheet();
                break;
            case R.id.terms_and_cond_layout:
                navController.navigate(R.id.terms_and_cond_fragment);
                break;
            case R.id.privacy_policy_layout:
                navController.navigate(R.id.privacy_policy_fragment);
                break;
            case R.id.about_layout:
                navController.navigate(R.id.about_fragment);
                break;
            case R.id.contact_us_layout:
                navController.navigate(R.id.contact_us_fragment);
                break;


        }
    }

    private void showBottomSheet() {
        EditProfileDialogFragment itemListDialogFragment = EditProfileDialogFragment.newInstance();

        itemListDialogFragment.show(getChildFragmentManager(), "");
    }

    @Override
    public void onProfileUpdateClicked() {

    }
}
