package com.smd.studio.thestarsgrouptask.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smd.studio.thestarsgrouptask.R;
import com.smd.studio.thestarsgrouptask.database.entity.TrainEntity;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.TrainViewHolder> {

    private List<TrainEntity> trainList;

    public RecyclerViewAdapter(List<TrainEntity> trainEntityList) {
        this.trainList = trainEntityList;
    }

    @NonNull
    @Override
    public TrainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TrainViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.train_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TrainViewHolder holder, int position) {
        TrainEntity train = trainList.get(position);
        holder.itemTextView.setText(train.getDestination());
        holder.nameTextView.setText(train.getDirection());
        holder.dateTextView.setText(train.getOrigin());
        holder.itemView.setTag(train);
    }

    @Override
    public int getItemCount() {
        return trainList.size();
    }

    public void addItems(List<TrainEntity> newTrainList) {
        trainList = newTrainList;
        notifyDataSetChanged();
    }

    static class TrainViewHolder extends RecyclerView.ViewHolder {
        private TextView itemTextView;
        private TextView nameTextView;
        private TextView dateTextView;

        TrainViewHolder(View view) {
            super(view);
            itemTextView = (TextView) view.findViewById(R.id.trainNameView);
            nameTextView = (TextView) view.findViewById(R.id.trainDestView);
            dateTextView = (TextView) view.findViewById(R.id.trainOrigView);
        }
    }
}
