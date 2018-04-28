package com.smd.studio.thestarsgrouptask.repositories;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.smd.studio.thestarsgrouptask.database.dao.TrainDao;
import com.smd.studio.thestarsgrouptask.database.entity.TrainEntity;
import com.smd.studio.thestarsgrouptask.network.TrainWebService;
import com.smd.studio.thestarsgrouptask.network.response.NetworkResponse;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class TrainRepository {

    private final TrainWebService webservice;
    private final TrainDao trainDao;
    private final Executor executor;

    @Inject
    public TrainRepository(TrainWebService service, TrainDao dao, Executor exec) {
        webservice = service;
        trainDao = dao;
        executor = exec;
    }

    public LiveData<List<TrainEntity>> getTrains(String stationName) {
        fetchTrainsFromRailApi(stationName);
        return trainDao.loadTrains(stationName);
    }

    private void fetchTrainsFromRailApi(String stationName) {
        webservice.getTrains(stationName).enqueue(new Callback<NetworkResponse>() {
            @Override
            public void onResponse(@NonNull Call<NetworkResponse> call, @NonNull Response<NetworkResponse> response) {
                Log.e("TAG", "Data refreshed from network");
                executor.execute(() -> {
                    if (response.body() != null) {
                        trainDao.removeTrains(stationName);
                        trainDao.insertAll(Objects.requireNonNull(response.body()).getTrains());
                    }
                });
            }

            @Override
            public void onFailure(@NonNull Call<NetworkResponse> call, @NonNull Throwable t) {
                Log.e("TAG", "Error when getting data from Network");
            }
        });
    }
}
