package com.smd.studio.thestarsgrouptask.repositories;

import android.arch.lifecycle.LiveData;

import com.smd.studio.thestarsgrouptask.database.dao.TrainDao;
import com.smd.studio.thestarsgrouptask.database.entity.TrainEntity;
import com.smd.studio.thestarsgrouptask.network.TrainWebService;
import com.smd.studio.thestarsgrouptask.util.Constants;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

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
        /*
        executor.execute(() -> {
            List<TrainEntity> trainEntityList = trainDao.loadTrainsList(stationName);
            for (TrainEntity train : trainEntityList) {
                // Check if train was fetched recently
                boolean trainExpired = (trainDao.trainExpired(train.getTrainCode(), getMaxRefreshTime(new Date())) != null);
                // If train has to be updated
                if (trainExpired) {
                    webservice.getTrains(train.getStationCode()).enqueue(new Callback<List<TrainEntity>>() {
                        @Override
                        public void onResponse(Call<List<TrainEntity>> call, Response<List<TrainEntity>> response) {
                            Log.e("TAG", "DATA REFRESHED FROM NETWORK");
                            Toast.makeText(App.context, "Data refreshed from network !", Toast.LENGTH_LONG).show();
                            executor.execute(() -> {
                                List<TrainEntity> trains = response.body();
                                if (trains != null && trains.size() > 0) {
                                    for (TrainEntity train : trains) {
                                        train.setLastRefresh(new Date());
                                        trainDao.insert(train);
                                    }
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<List<TrainEntity>> call, Throwable t) {
                            Log.e("TAG", "Error when getting data from Network");
                        }
                    });
                }
            }
        });
        */
    }

    private Date getMaxRefreshTime(Date currentDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.SECOND, Constants.NEW_DATA_TIMEOUT);
        return cal.getTime();
    }
}
