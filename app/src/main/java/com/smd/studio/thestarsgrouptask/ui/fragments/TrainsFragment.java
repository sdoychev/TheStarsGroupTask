package com.smd.studio.thestarsgrouptask.ui.fragments;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smd.studio.thestarsgrouptask.R;
import com.smd.studio.thestarsgrouptask.adapters.RecyclerViewAdapter;
import com.smd.studio.thestarsgrouptask.util.Constants;
import com.smd.studio.thestarsgrouptask.viewmodels.TrainListViewModel;

import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public abstract class TrainsFragment extends android.support.v4.app.Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private String stationName;
    private RecyclerView recyclerView;
    private TextView stationLabel;
    private TextView emptyView;
    private RecyclerViewAdapter recyclerViewAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.trains_fragment, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        stationLabel = view.findViewById(R.id.stationLabel);
        emptyView = view.findViewById(R.id.emptyView);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            stationName = getArguments().getString(Constants.FRAGMENT_STATION_NAME);
        }
        AndroidSupportInjection.inject(this);
        setupTrainFragment();
    }

    private void setupTrainFragment() {
        //Station Label View
        stationLabel.setText(String.format(getString(R.string.trains_list_title), stationName));

        //Recycler View
        recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<>());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyclerViewAdapter);

        //No Train data available View
        recyclerViewAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                checkEmpty();
            }

            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                checkEmpty();
            }

            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                super.onItemRangeRemoved(positionStart, itemCount);
                checkEmpty();
            }

            void checkEmpty() {
                emptyView.setVisibility(recyclerViewAdapter.getItemCount() <= 0 ? View.VISIBLE : View.GONE);
            }
        });

        //View Model
        TrainListViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(TrainListViewModel.class);
        viewModel.init(stationName);
        viewModel.getTrains().observe(Objects.requireNonNull(getActivity()), trainEntityList -> recyclerViewAdapter.addItems(trainEntityList));
    }
}
