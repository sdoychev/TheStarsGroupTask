package com.smd.studio.thestarsgrouptask.ui.fragments;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smd.studio.thestarsgrouptask.R;
import com.smd.studio.thestarsgrouptask.adapters.RecyclerViewAdapter;
import com.smd.studio.thestarsgrouptask.util.Constants;
import com.smd.studio.thestarsgrouptask.viewmodels.TrainListViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public class ArklowTrainsFragment extends Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private RecyclerViewAdapter recyclerViewAdapter;

    public ArklowTrainsFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trains_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        configureDagger();
        configureViewModel();
    }

    private void configureDagger() {
        AndroidSupportInjection.inject(this);
    }

    private void configureViewModel() {
        String trainListArgument = null;
        if (getArguments() != null) {
            trainListArgument = getArguments().getString(Constants.UID_KEY);
        }

        recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<>());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyclerViewAdapter);

        TrainListViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(TrainListViewModel.class);
        viewModel.init(trainListArgument);
        viewModel.getTrains().observe(this, trainsList -> recyclerViewAdapter.addItems(trainsList));
    }
}
