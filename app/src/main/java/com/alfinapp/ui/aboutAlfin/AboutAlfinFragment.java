package com.alfinapp.ui.aboutAlfin;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alfinapp.R;
import com.alfinapp.data.db.entity.AboutAlfinInfo;
import com.alfinapp.ui.home.notification.NotificationViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AboutAlfinFragment extends Fragment implements View.OnClickListener {

    private NotificationViewModel mViewModel;
    private RecyclerView aboutAlfinRecyclerView;

    public static AboutAlfinFragment newInstance() {
        return new AboutAlfinFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_about_alfin, container, false);
        setToolbar(rootView);
        aboutAlfinRecyclerView = rootView.findViewById(R.id.about_recycler_view);
        aboutAlfinRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        aboutAlfinRecyclerView.setAdapter(new AboutAlfinAdapter(getAboutAlfinData()));
        return rootView;
    }

    private void setToolbar(View rootView) {
        ImageView backButtonImageView = rootView.findViewById(R.id.back_button);
        backButtonImageView.setOnClickListener(this);
        TextView titleTextView = rootView.findViewById(R.id.text_view_title);
        titleTextView.setText(Objects.requireNonNull(getContext()).getString(R.string.title_about_alfin));
    }

    private List<AboutAlfinInfo> getAboutAlfinData() {
        List<AboutAlfinInfo> list = new ArrayList<>();
        list.add(new AboutAlfinInfo("Get Rewarded with Alfin Coins on Your Savings Account Balance and Save Money anywhere", "Get Rewarded with Alfin Coins on Your Savings Account Balance and Save Money anywhere"));
        list.add(new AboutAlfinInfo("Use Alfin Coins to win exciting prizes for FREE every month", "Use Alfin Coins to win exciting prizes for FREE every month"));
        list.add(new AboutAlfinInfo("Check Your Savings Account Transactions without any Banking Password or ATM PIN. No need to Visit Bank Branches now.", "Check Your Savings Account Transactions without any Banking Password or ATM PIN. No need to Visit Bank Branches now."));
        return list;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NotificationViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onClick(View view) {

    }

    private class AboutAlfinAdapter extends RecyclerView.Adapter<AboutAlfinAdapter.AboutAlfinViewHolder> {

        private List<AboutAlfinInfo> list;

        AboutAlfinAdapter(List<AboutAlfinInfo> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public AboutAlfinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new AboutAlfinViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_about_alfin, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull AboutAlfinViewHolder holder, int position) {
            holder.indexTextView.setText(holder.getAdapterPosition() + 1);
            holder.titleTextView.setText(list.get(holder.getAdapterPosition()).getTitle());
            holder.subTitleTextView.setText(list.get(holder.getAdapterPosition()).getSubTitle());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        private class AboutAlfinViewHolder extends RecyclerView.ViewHolder {
            private TextView titleTextView, subTitleTextView, indexTextView;

            public AboutAlfinViewHolder(@NonNull View itemView) {
                super(itemView);
                indexTextView = itemView.findViewById(R.id.index_text_view);
                titleTextView = itemView.findViewById(R.id.about_us_title_textview);
                subTitleTextView = itemView.findViewById(R.id.about_us_subtitle_textview);
            }
        }

    }

}
