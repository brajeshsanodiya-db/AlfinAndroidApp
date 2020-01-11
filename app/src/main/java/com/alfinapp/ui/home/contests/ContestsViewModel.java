package com.alfinapp.ui.home.contests;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.alfinapp.R;

public class ContestsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ContestsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public ContestWinnerAdapter getContestWinnerAdapter() {
        return new ContestWinnerAdapter();
    }

    public ActiveBidsAdapter getActiveBidsAdapter() {
        return new ActiveBidsAdapter();
    }

    class ContestWinnerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            return new ContestWinnerViewHolder(inflater.inflate(R.layout.rvitem_contest_winner, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 8;
        }

        class ContestWinnerViewHolder extends RecyclerView.ViewHolder {


            ContestWinnerViewHolder(View itemView) {
                super(itemView);

            }
        }

    }

    class ActiveBidsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            return new ActiveBidsViewHolder(inflater.inflate(R.layout.rvitem_active_bid, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 8;
        }

        class ActiveBidsViewHolder extends RecyclerView.ViewHolder {


            ActiveBidsViewHolder(View itemView) {
                super(itemView);

            }
        }
    }
}