package com.smd.studio.thestarsgrouptask.repositories;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.smd.studio.thestarsgrouptask.database.dao.TrainDao;
import com.smd.studio.thestarsgrouptask.database.entity.TrainEntity;
import com.smd.studio.thestarsgrouptask.network.TrainWebService;
import com.smd.studio.thestarsgrouptask.network.response.NetworkResponse;
import com.smd.studio.thestarsgrouptask.util.Constants;

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
        String stationCode;
        switch (stationName) {
            default:
            case Constants.ARKLOW_STATION_NAME:
                stationCode = Constants.ARKLOW_STATION_CODE;
                break;
            case Constants.SHANKILL_STATION_NAME:
                stationCode = Constants.SHANKILL_STATION_CODE;
                break;
        }
        webservice.loadTrains(stationCode).enqueue(new Callback<NetworkResponse>() {
            @Override
            public void onResponse(@NonNull Call<NetworkResponse> call, @NonNull Response<NetworkResponse> response) {
                Log.d(Constants.APP_TAG, "Data successfully fetched.");
                executor.execute(() -> {
                    if (response.body() != null) {
                        trainDao.removeTrains(stationName);
                        trainDao.insertAll(Objects.requireNonNull(response.body()).getTrains());
                    }
                });
            }

            @Override
            public void onFailure(@NonNull Call<NetworkResponse> call, @NonNull Throwable t) {
                Log.e(Constants.APP_TAG, "Error while fetching the data...");
            }
        });
    }
}
