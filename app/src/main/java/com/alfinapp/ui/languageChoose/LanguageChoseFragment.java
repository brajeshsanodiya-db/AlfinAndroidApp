package com.alfinapp.ui.languageChoose;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.alfinapp.R;
import com.alfinapp.data.local.AlfinPreferences;
import com.alfinapp.ui.appintro.AppIntroActivity;
import com.alfinapp.ui.appintro.AppIntroViewModel;
import com.alfinapp.utils.AlfinConstants;
import com.alfinapp.utils.ToolsUtils;

public class LanguageChoseFragment extends Fragment implements View.OnClickListener {

    private LanguageChoseViewModel mViewModel;

    public static LanguageChoseFragment newInstance() {
        return new LanguageChoseFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.language_chose_fragment, container, false);
        TextView select_hindiTextView = rootView.findViewById(R.id.select_hindi);
        TextView select_englishTextView = rootView.findViewById(R.id.select_english);

        select_hindiTextView.setOnClickListener(this::onClick);
        select_englishTextView.setOnClickListener(this::onClick);

        if (AlfinPreferences.getInstance(getActivity()).getStringValue(AlfinConstants.AppPrefKeys.APP_LANGUAGE_DONE, "").equalsIgnoreCase(AlfinConstants.LocaleValue.HINDI)) {
            select_hindiTextView.setBackgroundResource(R.drawable.onboarding_language_select_bg);
            select_englishTextView.setBackgroundResource(R.drawable.onboarding_language_bg);
        } else if (AlfinPreferences.getInstance(getActivity()).getStringValue(AlfinConstants.AppPrefKeys.APP_LANGUAGE_DONE, "").equalsIgnoreCase(AlfinConstants.LocaleValue.ENGLISH)) {
            select_hindiTextView.setBackgroundResource(R.drawable.onboarding_language_bg);
            select_englishTextView.setBackgroundResource(R.drawable.onboarding_language_select_bg);
        } else {
            select_hindiTextView.setBackgroundResource(R.drawable.onboarding_language_bg);
            select_englishTextView.setBackgroundResource(R.drawable.onboarding_language_bg);
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LanguageChoseViewModel.class);

        // TODO: Use the ViewModel
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.select_hindi:
                AlfinPreferences.getInstance(getActivity()).setStringValue(AlfinConstants.AppPrefKeys.APP_LANGUAGE_DONE, AlfinConstants.LocaleValue.HINDI);
                ToolsUtils.getToolsUtils().setLocale(getContext(), AlfinConstants.LocaleValue.HINDI);
                resetActivityAgain();
                break;
            case R.id.select_english:
                AlfinPreferences.getInstance(getActivity()).setStringValue(AlfinConstants.AppPrefKeys.APP_LANGUAGE_DONE, AlfinConstants.LocaleValue.ENGLISH);
                ToolsUtils.getToolsUtils().setLocale(getContext(), AlfinConstants.LocaleValue.ENGLISH);
                resetActivityAgain();
                break;
        }


    }

    private void resetActivityAgain() {
        getActivity().recreate();
    }
}
