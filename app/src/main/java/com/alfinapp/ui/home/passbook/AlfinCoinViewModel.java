package com.alfinapp.ui.home.passbook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.alfinapp.R;

public class AlfinCoinViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    public AlfinCoinHistoryAdapter getAlfinCoinHistoryAdapter() {
        return new AlfinCoinHistoryAdapter();
    }

    class AlfinCoinHistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            return new ContestWinnerViewHolder(inflater.inflate(R.layout.listitem_alfin_coin_history, parent, false));
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
}
