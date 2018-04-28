package com.smd.studio.thestarsgrouptask.repositories;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.smd.studio.thestarsgrouptask.App;
import com.smd.studio.thestarsgrouptask.database.dao.TrainDao;
import com.smd.studio.thestarsgrouptask.database.entity.TrainEntity;
import com.smd.studio.thestarsgrouptask.network.TrainWebService;
import com.smd.studio.thestarsgrouptask.network.response.NetworkResponse;
import com.smd.studio.thestarsgrouptask.util.Constants;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
        refreshTrain(stationName);                              // Refresh data from Irish Rail API (if possible)
        return trainDao.loadTrainsByStation(stationName);       // Return a LiveData directly from the database
    }

    private void refreshTrain(String stationName) {
        executor.execute(() -> {
            List<TrainEntity> trainEntityList = trainDao.loadTrainsList(stationName);
            if (trainEntityList != null && trainEntityList.size() > 0) {
                for (TrainEntity train : trainEntityList) {
                    // Check if train was fetched recently
                    boolean trainExpired = (trainDao.trainExpired(train.getTrainCode(), getMaxRefreshTime(new Date())) != null);
                    // If train has to be updated
                    if (trainExpired) {
                        // Update only selected train request is currently missing in the Irish Rail API,
                        // hence we update all the trains
                        fetchTrainsFromRailApi(webservice, stationName);
                    }
                }
            } else {
                fetchTrainsFromRailApi(webservice, stationName);
            }
        });
    }

    private Date getMaxRefreshTime(Date currentDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.SECOND, Constants.NEW_DATA_TIMEOUT);
        return cal.getTime();
    }

    private void fetchTrainsFromRailApi(TrainWebService service, String stationName) {
        service.getTrains(stationName).enqueue(new Callback<NetworkResponse>() {
            @Override
            public void onResponse(Call<NetworkResponse> call, Response<NetworkResponse> response) {
                Log.e("TAG", "DATA REFRESHED FROM NETWORK");
                Toast.makeText(App.context, "Data refreshed from network !", Toast.LENGTH_LONG).show();
                executor.execute(() -> {
                    List<TrainEntity> trains = null;
                    if (response.body() != null) {
                        trains = response.body().getTrains();
                    }
                    if (trains != null && trains.size() > 0) {
                        for (TrainEntity train : trains) {
                            train.setLastRefresh(new Date());
                            trainDao.insert(train);
                        }
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
