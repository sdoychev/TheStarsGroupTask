package com.smd.studio.thestarsgrouptask.adapters;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smd.studio.thestarsgrouptask.R;
import com.smd.studio.thestarsgrouptask.database.entity.TrainEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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
    @SuppressLint("SimpleDateFormat")
    public void onBindViewHolder(@NonNull TrainViewHolder holder, int position) {
        TrainEntity train = trainList.get(position);
        holder.codeTextView.setText(train.getTrainCode());
        holder.statusTextView.setText(train.getStatus());
        holder.originTextView.setText(train.getOrigin());
        holder.destinationTextView.setText(train.getDestination());
        holder.dueTextView.setText(String.valueOf(train.getDueIn()));
        holder.lateTextView.setText(String.valueOf(train.getLate()));
        if (Math.abs(train.getLate()) >= 5) {
            holder.lateTextView.setTextColor(Color.parseColor("#CC0000"));
        } else if (Math.abs(train.getLate()) <= 2) {
            holder.lateTextView.setTextColor(Color.parseColor("#009900"));
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        format.setTimeZone(TimeZone.getTimeZone("GMT+1"));
        SimpleDateFormat localeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        localeFormat.setTimeZone(TimeZone.getDefault());
        long minutesDifference = 0;
        try {
            Date serverDate = format.parse(train.getServerTime());
            Date currentDate = new Date();
            minutesDifference = (currentDate.getTime() - serverDate.getTime()) / 60000;
            if (minutesDifference < 0) {
                minutesDifference = 0;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.updateTextView.setText(String.valueOf(minutesDifference));
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
        private TextView codeTextView;
        private TextView statusTextView;
        private TextView originTextView;
        private TextView destinationTextView;
        private TextView dueTextView;
        private TextView lateTextView;
        private TextView updateTextView;

        TrainViewHolder(View view) {
            super(view);
            codeTextView = (TextView) view.findViewById(R.id.code);
            statusTextView = (TextView) view.findViewById(R.id.status);
            originTextView = (TextView) view.findViewById(R.id.origin);
            destinationTextView = (TextView) view.findViewById(R.id.destination);
            dueTextView = (TextView) view.findViewById(R.id.due);
            lateTextView = (TextView) view.findViewById(R.id.late);
            updateTextView = (TextView) view.findViewById(R.id.update);
        }
    }
}
