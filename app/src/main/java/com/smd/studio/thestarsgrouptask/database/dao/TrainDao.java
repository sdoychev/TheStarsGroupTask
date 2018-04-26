package com.smd.studio.thestarsgrouptask.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.smd.studio.thestarsgrouptask.database.entity.TrainEntity;

import java.util.Date;
import java.util.List;

@Dao
public interface TrainDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TrainEntity train);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<TrainEntity> trains);

    @Query("SELECT * FROM trains where stationFullName = :stationName")
    LiveData<List<TrainEntity>> loadTrainsByStation(String stationName);

    @Query("SELECT * FROM trains where stationFullName = :stationName")
    List<TrainEntity> loadTrainsList(String stationName);

    @Query("SELECT * FROM trains WHERE trainCode = :code AND lastRefresh < :lastRefreshMax LIMIT 1")
    TrainEntity trainExpired(String code, Date lastRefreshMax);
}
