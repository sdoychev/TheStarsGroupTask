package com.smd.studio.thestarsgrouptask.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.smd.studio.thestarsgrouptask.database.entity.TrainEntity;
import com.smd.studio.thestarsgrouptask.repositories.TrainRepository;

import java.util.List;

import javax.inject.Inject;

public class TrainListViewModel extends ViewModel {

    private LiveData<List<TrainEntity>> trainList;
    private TrainRepository trainRepo;

    @Inject
    public TrainListViewModel(TrainRepository trainRepository) {
        trainRepo = trainRepository;
    }

    public void init(String stationName) {
        if (trainList != null) {
            return;
        }
        trainList = trainRepo.getTrains(stationName);
    }

    public LiveData<List<TrainEntity>> getTrains() {
        return trainList;
    }
}
