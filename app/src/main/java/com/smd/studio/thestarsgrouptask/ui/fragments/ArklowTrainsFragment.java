package com.smd.studio.thestarsgrouptask.ui.fragments;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smd.studio.thestarsgrouptask.R;
import com.smd.studio.thestarsgrouptask.database.entity.TrainEntity;
import com.smd.studio.thestarsgrouptask.util.Constants;
import com.smd.studio.thestarsgrouptask.viewmodels.TrainListViewModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public class ArklowTrainsFragment extends Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private TrainListViewModel viewModel;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public ArklowTrainsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
        String trainList = getArguments().getString(Constants.UID_KEY);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TrainListViewModel.class);
        viewModel.init(trainList);
        viewModel.getTrains().observe(this, trains -> updateUI(trains));
    }

    private void updateUI(@Nullable List<TrainEntity> trains) {
        if (trains != null) {
            Log.e("TAG", "UPDATE UI");
            //TODO
            //this.username.setText(user.getName());
            //this.company.setText(user.getCompany());
            //this.website.setText(user.getBlog());
        }
    }

}
