package com.alfinapp.ui.home.notification;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alfinapp.R;
import com.alfinapp.data.db.entity.NotificationInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NotificationFragment extends Fragment implements View.OnClickListener {

    private NotificationViewModel mViewModel;
    private RecyclerView notificationRecyclerView;

    public static NotificationFragment newInstance() {
        return new NotificationFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.notification_fragment, container, false);
        setToolbar(rootView);
        notificationRecyclerView = rootView.findViewById(R.id.notification_recycler_view);
        notificationRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        notificationRecyclerView.setAdapter(new NotificationAdapter(getNotificationData()));
        return rootView;
    }

    private void setToolbar(View rootView) {
        ImageView backButtonImageView = rootView.findViewById(R.id.back_button);
        backButtonImageView.setOnClickListener(this);
        TextView titleTextView = rootView.findViewById(R.id.text_view_title);
        titleTextView.setText(Objects.requireNonNull(getContext()).getString(R.string.title_notification));
    }

    private List<NotificationInfo> getNotificationData() {
        List<NotificationInfo> list = new ArrayList<>();
        list.add(new NotificationInfo("Welcome to Alfin, We have gifted you 1000 Alfin Coins for joining us", "5 mins ago"));
        list.add(new NotificationInfo("Welcome to Alfin, We have gifted you 1000 Alfin Coins for joining us", "5 mins ago"));
        list.add(new NotificationInfo("Welcome to Alfin, We have gifted you 1000 Alfin Coins for joining us", "5 mins ago"));
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
        if (view.getId() == R.id.back_button) {
            Navigation.findNavController(Objects.requireNonNull(getActivity()), view.getId()).navigateUp();
        }
    }

    private class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

        private List<NotificationInfo> list;

        NotificationAdapter(List<NotificationInfo> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new NotificationViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_notification, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
            holder.contentTextView.setText(list.get(holder.getAdapterPosition()).getContent());
            holder.timeTextView.setText(list.get(holder.getAdapterPosition()).getTime());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        private class NotificationViewHolder extends RecyclerView.ViewHolder {
            private TextView contentTextView, timeTextView;

            public NotificationViewHolder(@NonNull View itemView) {
                super(itemView);
                contentTextView = itemView.findViewById(R.id.content);
                timeTextView = itemView.findViewById(R.id.time);
            }
        }

    }

}
