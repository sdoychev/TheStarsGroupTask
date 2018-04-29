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

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public abstract class TrainsFragment extends android.support.v4.app.Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private String stationName;
    private TextView stationLabel;
    private RecyclerViewAdapter recyclerViewAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.trains_fragment, container, false);
        ButterKnife.bind(this, view);
        stationLabel = view.findViewById(R.id.stationLabel);
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

        //View Model
        TrainListViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(TrainListViewModel.class);
        viewModel.init(stationName);
        viewModel.getTrains().observe(Objects.requireNonNull(getActivity()), trainEntityList -> recyclerViewAdapter.addItems(trainEntityList));
    }
}
