package com.alfinapp.ui.termsAndCondition;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.alfinapp.R;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class TermsAndCondiFragment extends Fragment implements View.OnClickListener {


    public TermsAndCondiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_privacy_policy, container, false);
        setToolbar(rootView);
        return rootView;
    }

    private void setToolbar(View rootView) {
        ImageView backButtonImageView = rootView.findViewById(R.id.back_button);
        backButtonImageView.setOnClickListener(this);
        TextView titleTextView = rootView.findViewById(R.id.text_view_title);
        titleTextView.setText(Objects.requireNonNull(getContext()).getString(R.string.title_terms_and_condition));
    }

    @Override
    public void onClick(View view) {

    }
}
